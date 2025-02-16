package com.design.pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SingletonDesignPattern obj1 = SingletonDesignPattern.getInstance();
        SingletonDesignPattern obj2 = SingletonDesignPattern.getInstance();

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());

        FileOutputStream fout = new FileOutputStream("C:\\Users\\archi\\OneDrive\\Desktop\\sample.txt");
        ObjectOutputStream outStream = new ObjectOutputStream(fout);
        outStream.writeObject(obj1);

        FileInputStream fin = new FileInputStream("C:\\Users\\archi\\OneDrive\\Desktop\\sample.txt");
        ObjectInputStream inStream = new ObjectInputStream(fin);
        SingletonDesignPattern myObj = (SingletonDesignPattern) inStream.readObject();
        System.out.println(myObj.hashCode());

    }
}
