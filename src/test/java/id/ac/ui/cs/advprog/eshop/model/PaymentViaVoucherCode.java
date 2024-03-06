package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentViaVoucherCodeCodeTest {
    List<Product> products;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        products.add(product2);

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
    }

    @Test
    void testCreatePaymentSuccessfulVoucherCode() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new PaymentByVoucherCodeCode("d5104f2a-dbac-11ee-a506-0242ac120002", orders.get(1), "VOUCHER_CODE", paymentDataVoucher);
        assertSame(orders.get(1), payment.getOrder());
        assertEquals("d5104f2a-dbac-11ee-a506-0242ac120002", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(paymentDataVoucher, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherWithStatus() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOPDDD12345678");

        PaymentByVoucherCode paymentVoucherCode = new PaymentByVoucherCode("bcb22408-dbac-11ee-a506-0242ac120002", orders.get(0),
                "VOUCHER_CODE", paymentDataVoucher, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), paymentVoucherCode.getOrder());
        assertEquals("bcb22408-dbac-11ee-a506-0242ac120002", paymentVoucherCode.getId());
        assertEquals("VOUCHER_CODE", paymentVoucherCode.getMethod());
        assertEquals(paymentDataVoucher, paymentVoucherCode.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), paymentVoucherCode.getStatus());
    }

    @Test
    void testCreatePaymentFailedProperLengthVoucherCode() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOPDDD12345678");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucherCode("ac8d3be4-dbac-11ee-a506-0242ac120002",orders.get(1),
                    "VOUCHER_CODE", paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentFailedProperNumericalLengthVoucherCode() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOPDDD12345678");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucherCode("ac8d3be4-dbac-11ee-a506-0242ac120002",orders.get(1),
                    "VOUCHER_CODE", paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentInvalidVoucherTooMuchNumerical() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP26117142138");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucherCode("ac8d3be4-dbac-11ee-a506-0242ac120002",orders.get(1),
                    "VOUCHER_CODE", paymentDataVoucher);
        });
    }
}