package Singleton;

import java.io.File;
import java.io.IOException;

public class StaticBlockSingletonDemo {
    public static void main(String[] args) {
        StaticBlockSingleton s = StaticBlockSingleton.getInstance();
        System.out.println(s.getInstance());
    }
}

class StaticBlockSingleton
{
    private static StaticBlockSingleton instance;
    private static int value;

    private StaticBlockSingleton() throws IOException {
        System.out.println("singleton is init'ing");
        File.createTempFile("", "."); // error due to bad file-suffix
    }

    static {
        // is it safe to use a static block to catch exceptions?
        try
        {
            instance = new StaticBlockSingleton();
        }
        catch (Exception e)
        {
            System.err.println("failed to create singleton");
        }
        value = 70;
    }

    public static StaticBlockSingleton getInstance()
    {
        return instance;
    }

}