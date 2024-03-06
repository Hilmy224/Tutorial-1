package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentTest {
    
    Map<String, String> paymentData;
    List<Product> products;
    Order order;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product2);

        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6",
                products, 1708560000L, "Safira Sudrijat");
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                    PaymentMethod.VOUCHER_CODE.getValue(), null, paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherStatusPending() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
        PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("904b780e-56c0-489d-828e-0f7b4ca79858", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherStatusSuccess() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
        PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, "SUCCESS");

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("904b780e-56c0-489d-828e-0f7b4ca79858", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherStatusRejected() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
        PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, "REJECTED");

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("904b780e-56c0-489d-828e-0f7b4ca79858", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherInvalidStatus() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
            PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, "MEOW");
        });

        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherNullStatus() {
        paymentData.put("voucherCode", "ESHOP1234DDD5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
            PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, null);
        });

        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryStatusPending() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
        PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("904b780e-56c0-489d-828e-0f7b4ca79858", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());

        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryStatusSuccess() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
        PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());

        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("904b780e-56c0-489d-828e-0f7b4ca79858", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliverydStatusRejected() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
        PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("904b780e-56c0-489d-828e-0f7b4ca79858", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryInvalidStatus() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                    PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryNullStatus() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                    PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherSuccess() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherRejected() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherInvalidStatus() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherNullStatus() {
        paymentData.put("voucherCode", "ESHOPDDD12345678");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliverySuccess() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryRejected() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryInvalidStatus() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryNullStatus() {
        paymentData.put("address", "KaliMantan");
        paymentData.put("deliveryFee", "8800");

        Payment payment = new Payment("904b780e-56c0-489d-828e-0f7b4ca79858",
                PaymentMethod.VOUCHER_CODE.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }
}
