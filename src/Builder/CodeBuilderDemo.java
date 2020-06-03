package Builder;

import java.util.ArrayList;

public class CodeBuilderDemo {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person");
        cb.addField("name", "String");
        cb.addField("age", "int");
        System.out.println(cb);
    }

}

class CodeBuilder
{
    private String className;
    private Field root = new Field();

    public CodeBuilder(String className)
    {
        this.className = className;
        root.name = className;
        root.type = "public class";
    }

    public CodeBuilder addField(String name, String type)
    {
        Field f = new Field(type, name);
        root.subfields.add(f);
        return this; // fluent builder
    }

    @Override
    public String toString()
    {
        return root.toString();
    }
}

class Field
{
    public String type;
    public String name;
    public ArrayList<Field> subfields = new ArrayList<>();
    private final String newLine = System.lineSeparator();

    public Field(String type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public Field(){
    }

    private String toStringImpl()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s%s", type, name, newLine));
        sb.append(String.format("{%s", newLine));

        String indent = "  ";
        for(Field s : subfields)
        {
            sb.append(String.format("%spublic %s %s;%s", indent, s.type, s.name, newLine));
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString()
    {
        return this.toStringImpl();
    }
}
