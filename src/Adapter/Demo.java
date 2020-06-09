package Adapter;

/* adapter design pattern tutorial:
* https://dzone.com/articles/adapter-design-pattern-in-java */

public class Demo {
    public static void main(String[] args)
    {
        Drawing canvas = new Drawing();
        canvas.addShape(new Rectangle());

        // use the adapter
        GeometricShapeToShapeAdapter triangle = new GeometricShapeToShapeAdapter(new Triangle());
        canvas.addShape(triangle);
        canvas.draw();
    }
}
