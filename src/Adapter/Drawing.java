package Adapter;

import java.util.ArrayList;
import java.util.List;

/* (client) the application */

public class Drawing
{
    List<Shape> shapes = new ArrayList<Shape>();

    public Drawing()
    {
        super();
    }

    public Drawing addShape(Shape shape)
    {
        shapes.add(shape);
        return this;
    }

    public List<Shape> getShapes()
    {
        return new ArrayList<Shape>(shapes);
    }

    public void draw()
    {
        if (shapes.isEmpty())
        {
            System.out.println("Nothing to draw");
        } else {
            shapes.stream().forEach(shape -> shape.draw());
        }
    }

    public void resize()
    {
        if (shapes.isEmpty())
        {
            System.out.println("Nothing to resize");
        } else {
            shapes.stream().forEach(shape -> shape.resize());
        }
    }
}
