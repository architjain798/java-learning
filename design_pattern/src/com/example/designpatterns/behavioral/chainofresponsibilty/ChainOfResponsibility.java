package com.example.designpatterns.behavioral.chainofresponsibilty;


// Abstract Handler
abstract class CashDispenser {
    protected CashDispenser nextDispenser;
    protected int denomination;
    
    public CashDispenser(int denomination) {
        this.denomination = denomination;
    }
    
    public void setNext(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }
    
    public void dispense(int amount) {
        if (amount >= denomination) {
            int num = amount / denomination;
            int remainder = amount % denomination;
            System.out.println("Dispensing " + num + " $" + denomination + " bills");
            
            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(remainder);
            } else if (remainder != 0) {
                System.out.println("Cannot dispense remaining amount: " + remainder);
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        } else {
            System.out.println("Cannot dispense amount: " + amount);
        }
    }
}

// Concrete Handlers for specific bill denominations
class HundredDollarDispenser extends CashDispenser {
    public HundredDollarDispenser() {
        super(100);
    }
}

class FiftyDollarDispenser extends CashDispenser {
    public FiftyDollarDispenser() {
        super(50);
    }
}

class TwentyDollarDispenser extends CashDispenser {
    public TwentyDollarDispenser() {
        super(20);
    }
}

class TenDollarDispenser extends CashDispenser {
    public TenDollarDispenser() {
        super(10);
    }
}

class FiveDollarDispenser extends CashDispenser {
    public FiveDollarDispenser() {
        super(5);
    }
}

class OneDollarDispenser extends CashDispenser {
    public OneDollarDispenser() {
        super(1);
    }
}

// ATM class
class ATM {
    private CashDispenser dispenseChain;
    
    public ATM() {
        // Create dispensers
        this.dispenseChain = new HundredDollarDispenser();
        CashDispenser fiftyDispenser = new FiftyDollarDispenser();
        CashDispenser twentyDispenser = new TwentyDollarDispenser();
        CashDispenser tenDispenser = new TenDollarDispenser();
        CashDispenser fiveDispenser = new FiveDollarDispenser();
        CashDispenser oneDispenser = new OneDollarDispenser();
        
        // Set up the chain (from highest to lowest denomination)
        dispenseChain.setNext(fiftyDispenser);
        fiftyDispenser.setNext(twentyDispenser);
        twentyDispenser.setNext(tenDispenser);
        tenDispenser.setNext(fiveDispenser);
        fiveDispenser.setNext(oneDispenser);
    }
    
    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount.");
            return;
        }
        
        System.out.println("Withdrawing $" + amount);
        dispenseChain.dispense(amount);
    }
}

// Client code
public class ChainOfResponsibility {
    public static void main(String[] args) {
        ATM atm = new ATM();
        
        System.out.println("Example 1:");
        atm.withdraw(378);
        
        System.out.println("\nExample 2:");
        atm.withdraw(210);
        
        System.out.println("\nExample 3:");
        atm.withdraw(193);
    }
}

