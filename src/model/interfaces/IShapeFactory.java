package model.interfaces;

import model.shapes.Point;


public interface IShapeFactory {
    public IShape createRectangle(Point startPoint, Point endPoint);
}
