package Builder;

public class StringBuilderDemo
{
    public static void main(String[] args)
    {
        // concatenation OK for small bits
        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");

        // use StringBuilder for harder ones (unordered list)
        String[] words = {"hello", "world"};
        StringBuilder sb = new StringBuilder();     // piece-wise component builder
        sb.append("<ul>\n");
        for(String word : words)
        {
            sb.append(String.format("    <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        // sb.build()   :   StringBuilder does not require us to build()
        System.out.println(sb);
    }
}
