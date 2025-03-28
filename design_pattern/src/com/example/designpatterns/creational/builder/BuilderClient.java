package com.example.designpatterns.creational.builder;

class Pizza {

    private String baseType;
    private int size;
    private boolean isTomatoto;
    private boolean iSCheese;
    private boolean isOnion;

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isTomatoto() {
        return isTomatoto;
    }

    public void setTomatoto(boolean isTomatoto) {
        this.isTomatoto = isTomatoto;
    }

    public boolean isiSCheese() {
        return iSCheese;
    }

    public void setiSCheese(boolean iSCheese) {
        this.iSCheese = iSCheese;
    }

    @Override
    public String toString() {
        return "Pizza [baseType=" + baseType + ", size=" + size + ", isTomatoto=" + isTomatoto + ", iSCheese="
                + iSCheese + ", isOnion=" + isOnion + "]";
    }

    public boolean isOnion() {
        return isOnion;
    }

    public void setOnion(boolean isOnion) {
        this.isOnion = isOnion;
    }

}

interface PizzaBuilder {

    PizzaBuilder addTomoto();

    PizzaBuilder addOnion();

    PizzaBuilder addCheese();

    Pizza build();
}

class DominosPizza implements PizzaBuilder {

    private Pizza pizza;

    public DominosPizza(String baseType, int size) {
        pizza = new Pizza();
        pizza.setBaseType(baseType);
        pizza.setSize(size);
    }

    @Override
    public PizzaBuilder addTomoto() {
        pizza.setTomatoto(true);
        return this;
    }

    @Override
    public PizzaBuilder addOnion() {
        pizza.setOnion(true);
        return this;
    }

    @Override
    public PizzaBuilder addCheese() {
        pizza.setiSCheese(true);
        return this;
    }

    @Override
    public Pizza build() {
        return pizza;
    }

}

public class BuilderClient {

    public static void main(String[] args) {
        var pizzaObj = new DominosPizza("thin curt", 2).addCheese().build();
        System.out.println(pizzaObj);
    }
}
