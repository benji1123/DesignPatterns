package Factory;

public class FactoryClassDemo {
    public static void main(String[] args) {
        Point3 cp = Point3.Factory.newCartesianPoint(10, 10);
        Point3 pp = Point3.Factory.newPolarPoint(1, 1);
    }
}

class Point3
{
    private double x, y;

    private Point3(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    static class Factory
    {
        public static Point3 newCartesianPoint(double x, double y)
        {
            return new Point3(x, y);
        }

        public static Point3 newPolarPoint(double rho, double theta)
        {
            return new Point3(rho*Math.cos(theta), rho*Math.sin(theta));
        }
    }
}

// external Factory requires a PUBLIC point3 constructor, which MAY or MAY NOT be okay.

//class PointFactory
//{
//    public static Point3 newCartesianPoint(double x, double y)
//    {
//        return new Point3(x, y);
//    }
//
//    public static Point3 newPolarPoint(double x, double y)
//    {
//        return new Point3(rho*Math.cos(theta), rho*Math.sin(theta));
//    }
//}
