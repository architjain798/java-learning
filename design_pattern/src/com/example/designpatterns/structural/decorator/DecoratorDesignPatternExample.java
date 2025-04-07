package com.example.designpatterns.structural.decorator;

public class DecoratorDesignPatternExample {

    public static void main(String[] args) {
        BasicPlan kartikPolicy = new BasePolicy();
        System.out.println(kartikPolicy.policyDetails() + " - Premium: " + kartikPolicy.getPremium());

        BasicPlan ajayPolicy = new BasePolicy();
        ajayPolicy = new AccidentalBenefit(ajayPolicy);
        ajayPolicy = new CriticalIllnessBenefit(ajayPolicy);
        System.out.println(ajayPolicy.policyDetails() + " - Premium " + ajayPolicy.getPremium());

        BasicPlan mohanPolicy = new BasePolicy();
        mohanPolicy = new CriticalIllnessBenefit(mohanPolicy);
        System.out.println(mohanPolicy.policyDetails() + " - Premium " + mohanPolicy.getPremium());
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

abstract class PolicyDecorator implements BasicPlan {

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
        return super.getPremium() + 400;
    }

    @Override
    public String policyDetails() {
        return super.policyDetails() + "/ Extra cover for Accidental Cover /";
    }

}

class CriticalIllnessBenefit extends PolicyDecorator {

    public CriticalIllnessBenefit(BasicPlan basicPlan) {
        super(basicPlan);
    }

    @Override
    public int getPremium() {
        return super.getPremium() + 500;
    }

    @Override
    public String policyDetails() {
        return super.policyDetails() + "/ Extra cover for Critical Illness Benefit Cover /";
    }

}

// The decorator pattern is a design pattern that allows behaviour to be added to an individual object, dynamically, without
// affecting the behaviour of other objects within the same class. The decorator pattern is often useful for adhering to
// the Single Responsibility Principle, as it allows functionality to be divided between classes with unique areas of concern
// Decorator patterns allow a user to add new functionality to an existing object without altering its structure. So, there is no
// change to the original class
// Decorator design patterns create decorator classes, which wrap the original class and supply additional functionality by
// keeping the class methods' signature unchanged.
// Decorator design patterns are most frequently used for applying single responsibility principles since we divide the
// functionality into classes with unique areas of concern
// +
// Decorator design pattern is useful in providing runtime modification abilities and hence more flexible. It's easy to maintain
// and extend when the amount of choices are more
// Decorator pattern is used a lot in Java IO classes, like FileReader, BufferedReader, etc.
