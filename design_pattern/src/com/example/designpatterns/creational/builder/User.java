package com.example.designpatterns.creational.builder;

public class User {

    // required fields
    private String firstName;
    private String lastName;

    // non-required fields
    private int age;
    private long mobile;

    private User(UserBuilder ub) {
        this.firstName = ub.firstName;
        this.lastName = ub.lastName;
        this.age = ub.age;
        this.mobile = ub.mobile;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", mobile=" + mobile + "]";
    }

    static class UserBuilder {

        // required fields
        private String firstName;
        private String lastName;

        // non-required fields
        private int age;
        private long mobile;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder mobile(long mobile) {
            this.mobile = mobile;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

}

class TestBuilder {

    public static void main(String[] args) {
        User u1 = new User.UserBuilder("archit","jain").age(26).mobile(921030).build();
        System.out.println(u1);

        User u2 = new User.UserBuilder("ajay", "jain").mobile(9878).build();
        System.out.println(u2);


        StringBuilder builder= new StringBuilder("temp");
        String data = builder.append("friend").append("none").toString();
    }
}

// when we direct sout the obj 
// it print the package@className
// for that we can override the toString() method
