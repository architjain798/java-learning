package com.example.designpatterns.structural.adapter;

public class AdapterPatternExample {
    AshokIt as = new AshokIt();
    as.setPayemnt("UPI");
    as.setDetails("45645@axis");

    Paytm obj = =PaymentAdapter.convertBillDeskToPaytm(as);
    

}

interface BillDesk {

    void setPayemnt(String paymentType);

    String getPayment();

    void setDetails(String data);

    String getDetails();
}

interface Paytm {

    void setTypeOfPayemnt(String paymentType);

    String getTypeOfPaymentType();

    void providesDetails(String data);

    String getDetails();
}

class PaymentAdapter implements Paytm {

    static BillDesk billDesk = null;

    PaymentAdapter(BillDesk billDesk) {
        this.billDesk = billDesk;
    }

    static Paytm convertBillDeskToPaytm(BillDesk b) {
        // Paytm p = new Paytm();

        p.setTypeOfPayemnt(b.getPayment());
        p.providesDetails(b.getDetails());
        return p;
    }

    @Override
    public void setTypeOfPayemnt(String paymentType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTypeOfPayemnt'");
    }

    @Override
    public String getTypeOfPaymentType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTypeOfPaymentType'");
    }

    @Override
    public void providesDetails(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'providesDetails'");
    }

    @Override
    public String getDetails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetails'");
    }

}

class AshokIt implements BillDesk {

    String paymentType = null;
    String data = null;

    @Override
    public void setPayemnt(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String getPayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPayment'");
    }

    @Override
    public void setDetails(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDetails'");
    }

    @Override
    public String getDetails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDetails'");
    }

}
