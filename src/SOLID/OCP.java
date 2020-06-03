package SOLID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

/* Open-Close: extending the functionality of a working piece of code
   should not require modifying the original code, but having the new code
   inherit properties of the original code, such that it can be used directly. */

/* Specification (enterprise design pattern):

 */

public class OCP {
    public static void main(String[] args)
    {
        BetterFilter colorFilter = new BetterFilter();

        Product p1 = new Product("red shirt", Color.RED, Size.YUGE);
        Product p2 = new Product("blue shirt", Color.BLUE, Size.SMALL);
        Product p3 = new Product("green shirt", Color.GREEN, Size.MEDIUM);

        List<Product> shirts = List.of(p1, p2, p3);
        colorFilter.filter(shirts, new ColorSpecification(Color.RED))
                .forEach(p -> System.out.println("- " + p.name + " is red"));
    }
}

enum Size
{
    SMALL, MEDIUM, LARGE, YUGE
}

enum Color
{
    RED, GREEN, BLUE
}

class Product
{
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter
{
    /* filter product-list using given product-specifications */

    public Stream<Product> filterByColor(List<Product> products, Color color)
    {
        return products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size)
    {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color)
    {
        return products.stream().filter(p -> p.size == size && p.color == color);
    }
    // state space explosion
    // 3 criteria = 7 methods for every filter-spec combination
}

// Applying open close principle

interface Specification<T>      // <T> = "generic class"
{
    boolean isSatisfied(T item);
}

interface Filter<T>     // <T> = "generic class"
{
    Stream<T> filter(List<T>items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product>
{
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item)
    {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product>
{
    private Size size;

    @Override
    public boolean isSatisfied(Product item)
    {
        return item.size == size;
    }
}

class BetterFilter implements Filter<Product>
{
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}