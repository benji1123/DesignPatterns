package Singleton;

public class MonostateDemo {
    public static void main(String[] args) {
        CEO steve = new CEO();
        steve.setName("Steve");
        steve.setAge(55);

        // will be be pre-initialized to Steve's data
        CEO unknown = new CEO();
        System.out.println(unknown);

        // changing unknown's data similarly changes Steve
        unknown.setName("unknown");
        unknown.setAge(0);
        System.out.println(steve);
    }
}

class CEO
// companies usually have a SINGLE CEO
{
    /* all data is static, so CEO-instances are identical */
    public static String name;
    public static int age;

    public CEO() {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CEO.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        CEO.age = age;
    }

    @Override
    public String toString() {
        return "age: " + CEO.age
                + "\nname: " + CEO.name;
    }
}