package com.example.designpatterns.structural.adapter;

public class AdapterPatternExample {
    public static void main(String[] args) {
        AshokIt ashok = new AshokIt();
        ashok.setPayment("UPI");
        ashok.setDetails("45645@axis");

        Paytm paytm = new PaymentAdapter(ashok); // Adapter used here

        System.out.println("Payment Type: " + paytm.getTypeOfPaymentType());
        System.out.println("Payment Details: " + paytm.getDetails());
    }
}

// ✅ Target interface (what the client expects)
interface Paytm {
    void setTypeOfPayemnt(String paymentType);
    String getTypeOfPaymentType();
    void providesDetails(String data);
    String getDetails();
}

// ✅ Adaptee interface (incompatible third-party system)
interface BillDesk {
    void setPayment(String paymentType);
    String getPayment();
    void setDetails(String data);
    String getDetails();
}

// ✅ Concrete implementation of Adaptee
class AshokIt implements BillDesk {
    private String paymentType;
    private String data;

    @Override
    public void setPayment(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String getPayment() {
        return this.paymentType;
    }

    @Override
    public void setDetails(String data) {
        this.data = data;
    }

    @Override
    public String getDetails() {
        return this.data;
    }
}

// ✅ Adapter: Converts BillDesk to Paytm
class PaymentAdapter implements Paytm {
    private final BillDesk billDesk;

    public PaymentAdapter(BillDesk billDesk) {
        this.billDesk = billDesk;
    }

    @Override
    public void setTypeOfPayemnt(String paymentType) {
        billDesk.setPayment(paymentType);
    }

    @Override
    public String getTypeOfPaymentType() {
        return billDesk.getPayment();
    }

    @Override
    public void providesDetails(String data) {
        billDesk.setDetails(data);
    }

    @Override
    public String getDetails() {
        return billDesk.getDetails();
    }
}
