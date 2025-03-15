package com.design.pattern.singleton;

public class FactoryDesignPattern {

    public static void main(String[] args) {
        Factory f = new Factory();
        Notification n = f.createNotification("sms");
        n.notifi();
    }
}

interface Notification {
    public void notifi();
}

class SMSNotification implements Notification {

    @Override
    public void notifi() {
        System.out.println("SMS Notification Triggered");
    }

}

class EmailNotification implements Notification {

    @Override
    public void notifi() {
        System.out.println("Email Notification Triggered");
    }

}

class Factory {

    public Notification createNotification(String notificationType) {
        if (notificationType.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        } else if (notificationType.equalsIgnoreCase("Email")) {
            return new EmailNotification();
        }
        return null;
    }
    
}
