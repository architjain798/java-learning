package com.example.designpatterns.creational.decorator;

public class DecoratorDesignPatternExample {
    public static void main(String[] args) {
        BasicPlan kartikPolicy = new BasePolicy();
        System.out.println(kartikPolicy.policyDetails() + " - Premium: " + kartikPolicy.getPremium());

        BasicPlan ajayPolicy = new BasePolicy();
        ajayPolicy = new AccidentalBenefit(ajayPolicy);
        System.out.println(ajayPolicy.policyDetails() +" - Premium "+ajayPolicy.getPremium());

    }
}

interface BasicPlan {
    int getPremium();
    String policyDetails();
}

class BasePolicy implements BasicPlan {
    @Override
    public int getPremium() {
        return 1000;
    }

    @Override
    public String policyDetails() {
        return "Base Policy ";
    }
}

abstract class PolicyDecorator implements BasicPlan{
    BasicPlan basicPlan;

    PolicyDecorator(BasicPlan basicPlan) {
        this.basicPlan = basicPlan;
    }
    
    @Override
    public int getPremium() {
        return this.basicPlan.getPremium();
    }

    @Override
    public String policyDetails() {
        return this.basicPlan.policyDetails();
    }

}


class AccidentalBenefit extends PolicyDecorator {

    AccidentalBenefit(BasicPlan basicPlan) {
        super(basicPlan);
    }

    @Override
    public int getPremium() {
        return this.basicPlan.getPremium()+400;
    }

    @Override
    public String policyDetails() {
        return this.basicPlan.policyDetails()+"Extra cover for Accidental Cover";
    }
    
}