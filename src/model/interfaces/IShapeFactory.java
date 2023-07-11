package model.interfaces;

import model.AppStateOpts;
import model.shapes.Point;


public interface IShapeFactory {
    public IShape createRectangle(Point startPoint, Point endPoint, AppStateOpts stateOpts);
}
