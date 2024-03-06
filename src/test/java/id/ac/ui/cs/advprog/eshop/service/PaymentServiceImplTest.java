package id.ac.ui.cs.advprog.eshop.service;


import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("67fe6efd-d1ae-446e-932d-f08d031cc996",
                products, 1708570000L, "Desmond Sudrajat");
        orders.add(order2);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPDDD12345678");
        Payment payment1 = new Payment("b37ae664-86c0-4812-979f-4affe7c37a43", "VOUCHER_CODE", orders.get(0), paymentData, PaymentStatus.PENDING.getValue());
        payments.add(payment1);

        paymentData = new HashMap<>();
        paymentData.put("address", "kaliMantan");
        paymentData.put("deliveryFee", "8800");
        Payment payment2 = new Payment("040c6ac5-3056-4547-867e-a95a65311b5e", "CASH_ON_DELIVERY", orders.get(0), paymentData, PaymentStatus.PENDING.getValue());
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).save(any(Payment.class));
        payment1 = paymentService.addPayment(payment1.getOrder(), "VOUCHER_CODE", payment1.getPaymentData());

        Payment payment2 = payments.get(1);
        doReturn(payment2).when(paymentRepository).save(any(Payment.class));
        payment2 = paymentService.addPayment(payment2.getOrder(), "CASH_ON_DELIVERY", payment2.getPaymentData());

        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment findResult = paymentService.getPayment(payment1.getId());

        assertEquals(payment1.getId(),findResult.getId() );
        assertEquals(payment1.getMethod(), findResult.getMethod() );
        assertEquals(payment1.getStatus(), findResult.getStatus() );

        doReturn(payment2).when(paymentRepository).findById(payment2.getId());
        findResult = paymentService.getPayment(payment2.getId());

        assertEquals(payment2.getId(),findResult.getId() );
        assertEquals(payment2.getMethod(), findResult.getMethod() );
        assertEquals(payment2.getStatus(), findResult.getStatus() );
    }

    @Test
    void testSetStatusSuccessful() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOPDDD12345678");
        Payment payment1 = new Payment("05b6687d-763a-4f29-9e85-144f31337282", "VOUCHER_CODE", orders.get(0), paymentData);

        assertEquals(PaymentStatus.PENDING.getValue(),payment1.getStatus());
        paymentService.setStatus(payment1, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment1.getStatus());
        paymentService.setStatus(payment1, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(),payment1.getStatus());
    }

    @Test
    void testUpdateOrderStatusWhenPaymentSuccess() {
        Order order = new Order("de60c34c-d731-46c7-8e21-64bd3c331f6f", products, 1708560000L, "Mono Sudrajat");
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment(UUID.randomUUID().toString(), "CASH_ON_DELIVERY", order, paymentData, PaymentStatus.PENDING.getValue());

        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusFailed() {
        assertThrows(IllegalArgumentException.class, ()->
                paymentService.setStatus(payments.get(0), "MEOW")
        );
    }

    @Test
    void testUpdateOrderStatusWhenPaymentRejected() {
        Order order = new Order("de60c34c-d731-46c7-8e21-64bd3c331f6f", products, 1708560000L, "Mono Sudrajat");
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment(UUID.randomUUID().toString(), "BANK", order, paymentData, PaymentStatus.PENDING.getValue());

        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testGetPaymentIfFound() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment paymentFound = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), paymentFound.getId());
        assertEquals("VOUCHER",paymentFound.getMethod());
        assertEquals(payment.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("invalid ID");

        Payment payment = paymentService.getPayment("invalid ID");
        assertNull(payment);
    }

    @Test
    void testGetAllPayment() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> resultPayments = paymentService.getAllPayment();

        assertNotNull(resultPayments);
        assertEquals(payments.size(), resultPayments.size());
        assertTrue(resultPayments.containsAll(payments));
    }
}