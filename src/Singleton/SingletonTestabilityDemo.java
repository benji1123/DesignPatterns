package Singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class SingletonTestabilityDemo {
    public static void main(String[] args) {
        Database cityDB = SingletonDatabase.getInstance();
        int populationToronto = cityDB.getPopulation("Toronto");
        System.out.println("Toronto's fake pop: " + populationToronto);

        // junit
        Testability.isSingletonTest();
        Testability.singletonTotalPopulationTest();
    }
}

interface Database
{
    int getPopulation(String name);
}

class SingletonDatabase implements Database
{
    private Hashtable<String, Integer> populations = new Hashtable<String, Integer>();
    private static int instanceCount = 0;
    public static int getCount() {return instanceCount;}

    private SingletonDatabase(){
        instanceCount++;
        try {
            File dataOnPopulation = new File(
                    SingletonTestabilityDemo.class.getProtectionDomain()
                            .getCodeSource().getLocation().getPath()
            );
            Path fullPath = Paths.get(dataOnPopulation.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            // put (city, population) pairs into HashTable
            int CITY = 0, POPULATION = 1;
            Iterables.partition(lines, 2)
                    .forEach(kv -> populations.put(
                            kv.get(CITY).trim(),
                            Integer.parseInt(kv.get(POPULATION))
                    ));
        } catch (IOException e)
        {
            System.err.println(e);
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance()
    {
        return INSTANCE;
    }

    public int getPopulation(String city)
    {
        return INSTANCE.populations.get(city);
    }
}

class DummyDatabase implements Database
{
    private Dictionary<String, Integer>  data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

class ConfigurableRecordFinder
{
    private Database database;

    public ConfigurableRecordFinder(Database database)
    {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for(String name : names)
        {
            result += database.getPopulation(name);
        }
        return result;
    }
}

class Testability
{
    @Test
    public static void isSingletonTest()
    {
        SingletonDatabase db1 = SingletonDatabase.getInstance();
        SingletonDatabase db2 = SingletonDatabase.getInstance();
        assertSame(db1, db2);
        assertEquals(1, SingletonDatabase.getCount());
        System.out.println("test1");
    }

    @Test
    public static void singletonTotalPopulationTest()
    {
        SingletonDatabase db = SingletonDatabase.getInstance();
        List<String> capitals = List.of("Toronto", "DC", "Beijing");
        ConfigurableRecordFinder c = new ConfigurableRecordFinder(db);
        int result = c.getTotalPopulation(capitals);
        assertEquals(result, 500);
        System.out.println("test2");
    }
}