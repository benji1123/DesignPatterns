package Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EnumBasedSingletonDemo {
    public static void main(String[] args) throws Exception {
        /* serialization does not compromise the singleton */

        String filename = "myfile.bin";
        EnumBasedSingleton e = EnumBasedSingleton.INSTANCE;
        saveToFile(e, filename);
        EnumBasedSingleton e2 = readFromFile(filename);
        e2.setValue(777);

        System.out.println("e: " + e.getValue());
        System.out.println("e2: " + e2.getValue());
    }

    static void saveToFile(EnumBasedSingleton e, String filename) throws Exception
    {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(e);
        }
    }

    static EnumBasedSingleton readFromFile(String filename) throws Exception
    {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            return (EnumBasedSingleton) in.readObject();
        }
    }
}

enum EnumBasedSingleton
{
    INSTANCE;

    EnumBasedSingleton()
    {
        value = 32;
    }

    int value;

    public int getValue()
    {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}