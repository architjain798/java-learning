package com.example.designpatterns.creational.builder;

class Student {

    private final String firstName;
    private final String lastName;
    private final int age;

    public Student(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + age;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
       if(this == obj ) return true;

       return false;
    }

}

record MyEmployee(String firstName,String lastName,int age) {
}

public class Test {

    public static void main(String[] args) {
        var obj1 = new Student(26, "archit", "jain");
        var obj2 = new Student(30, "ritika", "jain");
        System.out.println(obj1.getFirstName());

        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));

        var obj3 = new MyEmployee("archit", "jain", 26);
        System.out.println(obj3);

        var obj4 = new MyEmployee("archit", "jain", 26);
        System.out.println(obj3.equals(obj4));
    }
}
