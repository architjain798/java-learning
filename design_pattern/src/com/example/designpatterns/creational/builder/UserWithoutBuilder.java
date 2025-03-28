package com.example.designpatterns.creational.builder;

public class UserWithoutBuilder {

    private String firstName; // required
    private String lastName; // required
    private int age; //non-required
    private long mobile; //non-required

    public UserWithoutBuilder(String firstName, String lastName, int age, long mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.mobile = mobile;
    }

    public UserWithoutBuilder(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserWithoutBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserWithoutBuilder(String firstName, String lastName, long mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public long getMobile() {
        return mobile;
    }

    public static void main(String[] args) {
        UserWithoutBuilder obj = new UserWithoutBuilder("archit", "jain");
        System.out.println(obj.getFirstName());

    }
}
