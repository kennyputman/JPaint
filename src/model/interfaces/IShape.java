package model.interfaces;

import model.types.ShapeType;

public interface IShape {
    int getX();

    int getY();

    int getHeight();

    int getWidth();

    ShapeType getShapeType();
}
