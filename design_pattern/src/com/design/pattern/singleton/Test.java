package com.design.pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

    public static void main(String[] args) throws Exception {
        SingletonDesignPattern obj1 = SingletonDesignPattern.getInstance();
        SingletonDesignPattern obj2 = SingletonDesignPattern.getInstance();

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());

        // Breaking the singleton using serlization
        // if we want to prevent it then override the 
        FileOutputStream fout = new FileOutputStream("C:\\Users\\archi\\OneDrive\\Desktop\\sample.txt");
        ObjectOutputStream outStream = new ObjectOutputStream(fout);
        outStream.writeObject(obj1);

        FileInputStream fin = new FileInputStream("C:\\Users\\archi\\OneDrive\\Desktop\\sample.txt");
        ObjectInputStream inStream = new ObjectInputStream(fin);
        SingletonDesignPattern myObj = (SingletonDesignPattern) inStream.readObject();
        
        System.out.println(myObj.hashCode());


        // Breaking the singletion using Cloning
        SingletonDesignPattern obj3 = (SingletonDesignPattern)obj2.clone();
        System.out.println(obj3.hashCode());


    }
}
