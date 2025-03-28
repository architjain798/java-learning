package com.example.designpatterns.creational.builder;


// static nested class
class Outer {

    static int x = 10;
    static int y = 20;

    static class Inner {

        void show() {
            System.out.println(x);
            System.out.println(y);
        }

        static void check(){
            System.out.println("hello from static inner class");
        }
        
    }

    public static void main(String[] args) {
        System.out.println(x);
        System.out.println(y);

        Inner i = new Inner();
        i.show();

        Inner.check();
    }
}


// non static nested class

class OuterNS {
    int x = 10;
    int y = 20;


    class InnerNonStatic {
        void show(){
            System.out.println("hello from inner non static class");
            System.out.println(x);
            System.out.println(y);
        }

        static void check(){
            System.out.println("hello from non static method of non static class");
        }
    }
}

public class NestedDemo {

    public static void main(String[] args) {

        // Static Nested class
        // System.out.println(Outer.x);
        // System.out.println(Outer.y);

        // Outer.Inner i = new Outer.Inner();
        // i.show();

        // Outer.Inner.check();

        // Non static nested class
        OuterNS obj = new OuterNS();
        System.out.println(obj.x);

        // this is invalid and not possible
        //obj.InnerNonStatic.check();
        // trying to access static method of a non static inner class


        OuterNS.InnerNonStatic innerObj = obj.new InnerNonStatic();
        innerObj.show();

    }
}
