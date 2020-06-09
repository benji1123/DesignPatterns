package Singleton;

public class BasicSingletonDemo {
    public static void main(String[] args) {
        BasicSingleton s = BasicSingleton.getInstance();
        s.setValue(100);
        System.out.println(s.getValue());
    }
}

class BasicSingleton
{
    private static BasicSingleton INSTANCE = new BasicSingleton();
    private int value = 0;

    public static BasicSingleton getInstance()
    {
        return INSTANCE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private BasicSingleton() {
    }
}

