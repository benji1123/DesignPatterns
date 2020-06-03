package SOLID;

/* Interface Segregation: don't make god-interfaces. */

public class ISP {
}

class Document
{
    String text;
    String title;

    public Document(String text, String title)
    {
        this.title = title;
        this.text = text;
    }
}

interface machine {
    /* this interface has too many features */
    void print(Document d);
    void fax(Document d);
    void scan(Document d);
}

interface printer
{
    void print(Document d);
}

interface scanner
{
    void scan(Document d);
}

interface fax
{
    void fax(Document d);
}

class Printer implements printer{
    /* using the original interface would force us to implement unwanted functions (scan, fax) */
    @Override
    public void print(Document d) {
    }
}

interface MultiFunctionMachine extends printer, fax, scanner {}

class photocopier implements printer, scanner, fax{
    /* Extend all 3 interfaces
    * could also implement `MultiFunctionMachine`
    * */

    @Override
    public void print(Document d) {
    }

    @Override
    public void scan(Document d) {
    }

    @Override
    public void fax(Document d) {
    }
}