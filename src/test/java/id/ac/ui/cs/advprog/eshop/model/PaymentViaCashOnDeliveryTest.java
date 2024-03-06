package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PaymentViaCashOnDeliveryTest {
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

        oorders = new ArrayList<>();
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
    void testCreatePaymentByCashOnDeliverySuccessful() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "kaliMantan");
        paymentDataCashOnDelivery.put("deliveryFee", "8800");

        Payment payment = new PaymentViaCashOnDelivery("02657834-7df4-4ad4-b164-4bb6be61cf7f", orders.get(0), "CASH_ON_DELIVERY", paymentDataCashOnDelivery);
        assertSame(orders.get(0), payment.getOrder());
        assertEquals(paymentDataCashOnDelivery, payment.getPaymentData());
        assertEquals("02657834-7df4-4ad4-b164-4bb6be61cf7f", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
    }

    @Test
    void testCreatePaymentCashOnDeliveryWithStatus() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "kaliMantan");
        paymentDataCashOnDelivery.put("deliveryFee", "8800");

        PaymentViaCashOnDelivery PaymentCashOnDelivery = new PaymentViaCashOnDelivery("02657834-7df4-4ad4-b164-4bb6be61cf7f",orders.get(0), "CASH_ON_DELIVERY", paymentDataCashOnDelivery, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), PaymentCashOnDelivery.getOrder());
        assertEquals("02657834-7df4-4ad4-b164-4bb6be61cf7f", PaymentCashOnDelivery.getId());
        assertEquals("CASH_ON_DELIVERY", PaymentCashOnDelivery.getMethod());
        assertEquals(paymentDataCashOnDelivery, PaymentCashOnDelivery.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), PaymentCashOnDelivery.getStatus());
    }

    @Test
    void testCreatePaymentFailedEmptyAddress() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "");
        paymentDataCashOnDelivery.put("deliveryFee", "8800");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentViaCashOnDelivery("02657834-7df4-4ad4-b164-4bb6be61cf7f", orders.get(1),"CASH_ON_DELIVERY", paymentDataCashOnDelivery);
        });
    }

    @Test
    void testCreatePaymentFailedEmptyDeliveryFee() {
        Map<String, String> paymentDataCashOnDelivery = new  HashMap<>();
        paymentDataCashOnDelivery.put("address", "kaliMantan");
        paymentDataCashOnDelivery.put("deliveryFee", "");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentViaCashOnDelivery("02657834-7df4-4ad4-b164-4bb6be61cf7f", orders.get(1),"CASH_ON_DELIVERY", paymentDataCashOnDelivery);
        });
    }
}