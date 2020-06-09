package Adapter;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("drawing rectangle");
    }

    @Override
    public void resize() {
        System.out.println("resizing rectangle");
    }

    @Override
    public String description() {
        return "rectangle object";
    }

    @Override
    public boolean isHide() {
        return false;
    }
}
