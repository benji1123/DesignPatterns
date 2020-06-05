package Prototype;

import java.util.Arrays;

public class CloneableDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person ben = new Person(new String[]{"Ben", "Li"},
                new Address("SouthWood", 113),
                20);

        Person neb = (Person) ben.clone();
        neb.age = 500;

        System.out.println(ben + "\n" + neb);
    }
}

class Address implements Cloneable {
    private String street;
    private int houseNumber;

    public Address(String street, int houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(street, houseNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}

class Person implements Cloneable
{
    public String[] name;
    public Address address;
    public int age;

    public Person(String[] name, Address address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        /* Return a deep copy:
            - name (String array): arrays are passed by reference, so we need to use CLONE()
            - address (Address): instance of an object, so use CLONE()
            - age (int): integers are passed by value, NO need to CLONE
         */
        return new Person(name.clone(), (Address) address.clone(), age);
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
