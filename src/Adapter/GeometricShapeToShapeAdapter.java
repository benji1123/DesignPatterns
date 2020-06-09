package Adapter;

/* (adapter) use a geometric-shape as a Shape-obj */

public class GeometricShapeToShapeAdapter implements Shape {

    GeometricShape geoShape;

    public GeometricShapeToShapeAdapter(GeometricShape geoShape)
    {
        this.geoShape = geoShape;
    }

    @Override
    public void draw() {
        geoShape.drawShape();
    }

    @Override
    public void resize() {
        System.out.println(description() + " cannot be resize");
    }

    @Override
    public String description() {
        if (geoShape instanceof Triangle)
            return "triangle object";
        return "unknown object";
    }

    @Override
    public boolean isHide() {
        return false;
    }
}
