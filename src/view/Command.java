package view;

import model.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;

import java.awt.*;

public class Command implements IUndoable {

    // FIXME this is a hack look to creating this in a constructor or something rather than a createShape
    private IShape shape;
    ShapeType shapeType;

    @Override
    public void createShape(Point start, Point end, ShapeType type, IShapeFactory shapeFactory) {
        this.shapeType = type;

        if(type == ShapeType.RECTANGLE){

            shape = shapeFactory.createRectangle(start, end);
        }

    }

    @Override
    public IShape getShape() {
        return shape;
    }


    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }
}
