package Builder;

import java.util.ArrayList;
import java.util.Collections;

public class HtmlBuilderDemo {
    public static void main(String[] args) {
        HtmlBuilder hb = new HtmlBuilder("ol");
        hb.addChild("li", "Ben Li")
                .addChild("li", "Rhiannon Safira")
                .addChild("li", "Paige Moon");
        System.out.println(hb);
    }
}


class HtmlElement
{
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public HtmlElement() {
    }

    private String toStringImpl(int indent)
    {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if(this.text != null && !this.text .isEmpty())
        {
            sb.append(String.join("", Collections.nCopies((indent+1) * indentSize, " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement e : this.elements)
        {
            sb.append(e.toStringImpl(indent + 1));
        }
        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}


class HtmlBuilder
{
    private HtmlElement root = new HtmlElement();
    private String rootName;

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public HtmlBuilder addChild(String name, String text)
    {
        HtmlElement e = new HtmlElement(name, text);
        root.elements.add(e);
        return this; // fluent builder
    }

    @Override
    public String toString()
    {
        return root.toString();
    }
}