package Singleton;

import java.util.HashMap;

public class MultitonDemo {
    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);  // 1 instances so far
        Printer aux = Printer.get(Subsystem.AUXILLARY); // 2 instances so far
        Printer fb = Printer.get(Subsystem.FALLBACK);   // 3 instances so far

        Printer main2 = Printer.get(Subsystem.PRIMARY); // subsystem already exists
    }
}

enum Subsystem
{
    PRIMARY,
    AUXILLARY,
    FALLBACK
}

class Printer
{
    private static HashMap<Subsystem, Printer> instances = new HashMap<>();
    private static int printerCount = 0;

    private Printer(Subsystem ss)
    {
        System.out.println((++printerCount) + " instances so far");
    }

    public static Printer get(Subsystem ss)
    {
        if (instances.containsKey(ss))
        {
            System.out.println("subsystem already exists");
            return instances.get(ss);
        }

        Printer instance = new Printer(ss);
        instances.put(ss, instance);
        return instance;
    }
}