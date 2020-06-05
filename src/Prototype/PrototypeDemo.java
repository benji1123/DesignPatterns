package Prototype;

import java.util.Arrays;

/* Utilize a Copy Constructor */

public class PrototypeDemo {
    public static void main(String[] args) {
        Person2 ben = new Person2(new String[]{"ben"}, new Address2("notyj", 3), 33);
        Person2 cory = new Person2(ben);
        cory.name = new String[]{"cory"};
        cory.age = 666;
        System.out.println(ben);
        System.out.println(cory);
    }
}

class Address2 {
    private String street;
    private int houseNumber;

    public Address2(String street, int houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Address2(Address2 a)
    {
        this(a.street, a.houseNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}

class Person2
{
    public String[] name;
    public Address2 address;
    public int age;

    public Person2(String[] name, Address2 address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Person2(Person2 p)
    {
        this(p.name, new Address2(p.address), p.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + Arrays.toString(name) +
                ", address=" + address +
                ", age=" + age +
                '}';
    }
}