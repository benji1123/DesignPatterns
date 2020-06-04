package Factory;

public class NonFactoryDemo {
    public static void main(String[] args) {
        Point p = new Point(1, 2, CoordinateSystem.CARTESIAN);
        System.out.println(p);
    }
}

enum CoordinateSystem
{
    CARTESIAN, POLAR
}

class Point
{
    private double x, y;

    protected Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // not a Factory
    public Point(double a,
                 double b, // names don't show intent
                 CoordinateSystem cs) // rely on selector
    {
        switch(cs)
        {
            case CARTESIAN:
                this.x = a;
                this.y = b;
                break;
            case POLAR:
                this.x = a * Math.cos(b);
                this.y = a * Math.sin(b);
                break;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}