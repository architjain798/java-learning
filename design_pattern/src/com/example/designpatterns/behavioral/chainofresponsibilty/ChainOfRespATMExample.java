package com.example.designpatterns.behavioral.chainofresponsibilty;

import java.util.Scanner;

public class ChainOfRespATMExample {

    DispenceChain d;

    public ChainOfRespATMExample() {
        this.d = new Notes2000Rack();
        DispenceChain d1 = new Notes500Rack();
        DispenceChain d2 = new Notes100Rack();
        d.chain(d1);
        d1.chain(d2);
    }

    public static void main(String[] args) {
        ChainOfRespATMExample c = new ChainOfRespATMExample();

        while (true) {
            System.out.println("please enter amount to be withdrawn");
            Scanner sc = new Scanner(System.in);
            int amount = sc.nextInt();
            if (amount != 0) {
                if (amount % 100 != 0) {
                    System.out.println("Please enter multiple of hundred");
                    return;
                } else {
                    c.d.dispence(new Currency(amount));
                }
            }
        }
    }
}

class Currency {

    public Currency(int amount) {
        this.amount = amount;
    }
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

interface DispenceChain {

    void dispence(Currency currency);

    void chain(DispenceChain nextChain);
}

class Notes2000Rack implements DispenceChain {

    DispenceChain c;

    @Override
    public void dispence(Currency currency) {
        int noOf2000Notes = currency.getAmount() / 2000;
        System.out.println("Dispencing--" + noOf2000Notes + "--2000 notes");

        int remainder = currency.getAmount() % 2000;
        if (remainder != 0) {
            this.c.dispence(new Currency(remainder));
        }
    }

    @Override
    public void chain(DispenceChain d) {
        this.c = d;
    }

}

class Notes500Rack implements DispenceChain {

    DispenceChain c;

    @Override
    public void dispence(Currency currency) {
        int noOf500Notes = currency.getAmount() / 500;
        System.out.println("Dispencing--" + noOf500Notes + "--500 notes");

        int remainder = currency.getAmount() % 500;
        if (remainder != 0) {
            this.c.dispence(new Currency(remainder));
        }
    }

    @Override
    public void chain(DispenceChain d) {
        this.c = d;
    }

}

class Notes100Rack implements DispenceChain {

    DispenceChain c;

    @Override
    public void dispence(Currency currency) {
        int noOf1000Notes = currency.getAmount() / 100;
        System.out.println("Dispencing--" + noOf1000Notes + "--100 notes");

        int remainder = currency.getAmount() % 100;
        if (remainder != 0) {
            this.c.dispence(new Currency(remainder));
        }
    }

    @Override
    public void chain(DispenceChain d) {
        this.c = d;
    }

}
