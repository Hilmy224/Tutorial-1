package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductQuantity(2);
        product.setProductName("Sampo Cap Bambang");
        products.add(product);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("67fe6efd-d1ae-446e-932d-f08d031cc996",
                products, 1708570000L, "Desmond Sudrajat");
        orders.add(order2);
        Order order3 = new Order("de60c34c-d731-46c7-8e21-64bd3c331f6f",
                products, 1708570000L, "Mono Sudrajat");
        orders.add(order3);

        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOPDDD12345678");
        Payment voucher = new Payment("b37ae664-86c0-4812-979f-4affe7c37a43", "VOUCHER_CODE", orders.get(0), paymentDataVoucher, PaymentStatus.PENDING.getValue());
        payments.add(voucher);

        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Akses UI");
        paymentDataCashOnDelivery.put("deliveryFee", "9000");

        Payment cashOnDelivery = new Payment("040c6ac5-3056-4547-867e-a95a65311b5e", "CASH_ON_DELIVERY", orders.get(0), paymentDataCashOnDelivery, PaymentStatus.PENDING.getValue());
        payments.add(cashOnDelivery);
    }

    @Test
    void testSaveSuccess() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testAddPaymentDuplicatedId() {
        Payment payment1 = payments.get(1);
        paymentRepository.save(payment1);

        Payment payment2 = new Payment(payment1.getId(), payment1.getMethod(), payment1.getOrder(), payment1.getPaymentData(), PaymentStatus.PENDING.getValue());
        assertThrows(IllegalStateException.class, () -> paymentRepository.save(payment2));
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertSame(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("invalidId");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> allPayments = paymentRepository.getAllPayments();
        assertEquals(payments.size(), allPayments.size());
    }
}