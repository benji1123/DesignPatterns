package Singleton;

public class LazySingletonDemo {
    public static void main(String[] args) {

    }
}

class LazySingleton
{
    private LazySingleton() {}

    private static class Impl
    {
        /* Inner class created only when getInstance() method is called */
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public LazySingleton getInstance()
    {
        return Impl.INSTANCE;
    }
}
