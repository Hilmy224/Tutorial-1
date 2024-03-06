package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
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
    void testCreatePaymentByCashOnDeliverySuccessful() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "kaliMantan");
        paymentDataCashOnDelivery.put("deliveryFee", "8800");

        Payment payment = new PaymentViaCashOnDelivery("f828f9bc-a9fc-4ad0-a04b-15da1529a0fc", orders.get(0), PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery);
        assertSame(orders.get(0), payment.getOrder());
        assertEquals(paymentDataCashOnDelivery, payment.getPaymentData());
        assertEquals("f828f9bc-a9fc-4ad0-a04b-15da1529a0fc", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
    }

    @Test
    void testCreatePaymentCashOnDeliveryWithStatus() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "kaliMantan");
        paymentDataCashOnDelivery.put("deliveryFee", "8800");

        PaymentViaCashOnDelivery PaymentCashOnDelivery = new PaymentViaCashOnDelivery("f828f9bc-a9fc-4ad0-a04b-15da1529a0fc",orders.get(0), PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), PaymentCashOnDelivery.getOrder());
        assertEquals("f828f9bc-a9fc-4ad0-a04b-15da1529a0fc", PaymentCashOnDelivery.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), PaymentCashOnDelivery.getMethod());
        assertEquals(paymentDataCashOnDelivery, PaymentCashOnDelivery.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), PaymentCashOnDelivery.getStatus());
    }

    @Test
    void testCreatePaymentFailedEmptyAddress() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "");
        paymentDataCashOnDelivery.put("deliveryFee", "8800");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentViaCashOnDelivery("f828f9bc-a9fc-4ad0-a04b-15da1529a0fc", orders.get(1),PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery);
        });
    }

    @Test
    void testCreatePaymentFailedEmptyDeliveryFee() {
        Map<String, String> paymentDataCashOnDelivery = new  HashMap<>();
        paymentDataCashOnDelivery.put("address", "kaliMantan");
        paymentDataCashOnDelivery.put("deliveryFee", "");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentViaCashOnDelivery("f828f9bc-a9fc-4ad0-a04b-15da1529a0fc", orders.get(1),PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery);
        });
    }
}