package model.shapes;

import model.ShapeType;
import model.interfaces.IShape;

public class Rectangle implements IShape {
    private final int x;
    private final int y;
    private final int height;
    private final int width;

    public Rectangle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
    }
}
