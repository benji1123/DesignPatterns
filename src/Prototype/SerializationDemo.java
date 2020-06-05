package Prototype;

import org.apache.commons.lang3.SerializationUtils;
import java.io.Serializable;
import java.util.Arrays;

public class SerializationDemo {
    public static void main(String[] args) {
        Person3 p = new Person3(new String[]{"Ben"}, new Address3("North", 5), 500);
        Person3 p2 = SerializationUtils.roundtrip(p);
        p2.age = 777;
        System.out.println(p);
        System.out.println(p2);
    }
}

class Address3 implements Serializable {
    private String street;
    private int houseNumber;

    public Address3(String street, int houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}

class Person3 implements Serializable
{
    public String[] name;
    public Address3 address;
    public int age;

    public Person3(String[] name, Address3 address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
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
