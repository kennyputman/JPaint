package model.interfaces;

import model.Point;

public interface IShapeFactory {
    public IShape createRectangle(Point startPoint, Point endPoint);
}
