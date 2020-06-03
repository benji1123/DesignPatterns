package SOLID;

/* Liskov Substitution : we can replace objects of a parent class
   with objects of a child class without breaking the program
 */

public class LSP {
    static void helper(Rectangle r)
    {
        int width = r.getWidth();
        r.setHeight(10);
        // area = width * 10
        System.out.println("Expect area =  " + (width*10) + ", got " + r.getArea());
    }

    public static void main(String[] args)
    {
        Rectangle r = new Rectangle(10, 4);
        helper(r);
        Rectangle s = new Square(5);
        helper(s); // BUG: returns "Expects area = 50, got 100"

        // using Factory to mitigate issue
        RectangleFactory factory = new RectangleFactory();
        Rectangle square = factory.newSquare(10);
        System.out.println("\nAmmended code returns:");
        helper(square);
    }
}

class Rectangle
{
    protected int width;
    protected int height;   // protected = visible to package and sub-classes

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return this.width * this.height;
    }

    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}

class Square extends Rectangle
{
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setHeight(width);
        super.setWidth(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}


// Solving issue with Factory Design Pattern

class RectangleFactory
{
    public static Rectangle newSquare (int side)
    {
        return new Rectangle(side, side);
    }

    public static Rectangle newRectangle(int width, int height)
    {
        return new Rectangle(width, height);
    }

}
