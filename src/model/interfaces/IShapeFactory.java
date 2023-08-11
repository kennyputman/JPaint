package model.interfaces;

import model.AppStateOpts;
import model.shapes.Point;


public interface IShapeFactory {
    IShape createRectangle(Point startPoint, Point endPoint, AppStateOpts stateOpts);


    IShape createGroup(Point startPoint, Point endPoint, AppStateOpts stateOpts);

    IShape createTriangle(Point start, Point end, AppStateOpts stateOpts);

    IShape createEllipse(Point start, Point end, AppStateOpts stateOpts);
}
