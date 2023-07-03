package model.interfaces;

import model.ShapeType;

public interface IShape {
    int getX();
    int getY();
    int getHeight();
    int getWidth();

    ShapeType getShapeType();
}
