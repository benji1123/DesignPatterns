package Factory;

public class FactoryAsMember {
    public static void main(String[] args) {
        Point2 cp = Point2.newCartesianPoint2(5, 10);
        Point2 pp = Point2.newPolarCoordinate(1, 1);
        System.out.println(cp + "\n" + pp);
    }
}

class Point2
{
    private double x, y;

    private Point2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    // static members are accessible before instantiating a class
    public static Point2 newCartesianPoint2(double x, double y)
    {
        return new Point2(x, y);
    }

    public static Point2 newPolarCoordinate(double rho, double theta)
    {
        return new Point2(rho*Math.cos(theta), rho*Math.sin(theta));
    }

    @Override
    public String toString() {
        return "Point2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

