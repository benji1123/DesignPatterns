package SOLID;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* Notes: Separate Responsibility : don't make god-classes. */

public class SRP
{
    public static void main(String[] args) throws Exception {
        Journal j = new Journal();
        j.addEntry("started Java Design Patterns");
        j.addEntry("played Wizard101");

        Persistence p = new Persistence();
        String filename = "c:/users/benji/Desktop/SRP.txt";
        p.saveToFile(j,filename, true);
    }
}

class Journal
{
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text)
    {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index)
    {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    // SRP is broken below
    public void save(String filename) throws Exception {}
    public void load(Journal journal, String filename) {}
    public void load(Journal journal, URL url) {}
}

class Persistence
{
    public void saveToFile(Journal journal,
       String filename, boolean overwrite) throws Exception
    {
        if(overwrite || new File(filename).exists())
            try (PrintStream out = new PrintStream(filename))
            {
                out.println(journal.toString());
            }
    }

    public void load(Journal journal, String filename) {}
    public void load(Journal journal, URL url) {}
}
