package model.shapes;

import model.Point;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;


/**
 * Abstract factory for IShape interface
 *
 */
public class ShapeFactory implements IShapeFactory {
    @Override
    public IShape createRectangle(Point startPoint, Point endPoint) {
        var x = Math.min(startPoint.x(), endPoint.x());
        var y = Math.min(startPoint.y(), endPoint.y());
        var width = Math.abs(startPoint.x() - endPoint.x());
        var height = Math.abs(startPoint.y() - endPoint.y());

        return new Rectangle(x,y,height,width);
    }
}
