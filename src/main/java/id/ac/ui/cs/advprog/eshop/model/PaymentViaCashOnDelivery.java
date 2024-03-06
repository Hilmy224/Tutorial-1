package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentViaCashOnDelivery extends Payment {
    public PaymentViaCashOnDelivery(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public PaymentViaCashOnDelivery(String id, Order order, String method, Map<String, String > paymentData) {
        super(id, method, order, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {

    }
}
