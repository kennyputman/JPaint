package model.shapes;

import model.AppStateOpts;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;


public class ShapeFactory implements IShapeFactory {


    @Override
    public IShape createRectangle(Point startPoint, Point endPoint, AppStateOpts appStateOpts) {
        var x = Math.min(startPoint.x(), endPoint.x());
        var y = Math.min(startPoint.y(), endPoint.y());
        var width = Math.abs(startPoint.x() - endPoint.x());
        var height = Math.abs(startPoint.y() - endPoint.y());

        return new Rectangle(x,y,height,width, appStateOpts);
    }
}
