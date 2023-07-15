package model.shapes;

import model.AppStateOpts;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;


public class ShapeFactory implements IShapeFactory {


    @Override
    public IShape createRectangle(Point start, Point end, AppStateOpts appStateOpts) {
        var x = Math.min(start.x(), end.x());
        var y = Math.min(start.y(), end.y());
        var width = Math.abs(start.x() - end.x());
        var height = Math.abs(start.y() - end.y());

        return new Rectangle(x,y,height,width, appStateOpts);
    }

    @Override
    public IShape createTriangle(Point start, Point end, AppStateOpts stateOptions) {


        // TODO fix this logic, doesn't draw in all directions sometimes just a line
        var xCoord = new int[3];
        var yCoord = new int[3];

        xCoord[0] = start.x();
        xCoord[1] = end.x();
        xCoord[2] = Math.min(start.x(), end.x());

        yCoord[0] = start.y();
        yCoord[1] = end.y();
        yCoord[2] = Math.max(start.y(), end.y());

        return new Triangle(xCoord, yCoord, stateOptions);


    }

    @Override
    public IShape createEllipse(Point start, Point end, AppStateOpts stateOpts) {
        var x = Math.min(start.x(), end.x());
        var y = Math.min(start.y(), end.y());
        var width = Math.abs(start.x() - end.x());
        var height = Math.abs(start.y() - end.y());

        return new Ellipse(x,y,height,width, stateOpts);
    }


}
