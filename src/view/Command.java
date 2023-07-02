package view;

import model.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import view.gui.PaintCanvas;
import view.interfaces.IUndoable;

import java.awt.*;

public class Command implements IUndoable {

    // FIXME this is a hack look to creating this in a constructor or something rather than a createShape
    private IShape shape;
    ShapeType shapeType;

    @Override
    public void execute(Point start, Point end, ShapeType type, IShapeFactory shapeFactory, PaintCanvas paintCanvas) {
        this.shapeType = type;
        Graphics2D graphics2d = (Graphics2D) paintCanvas.getGraphics();

        if(type == ShapeType.RECTANGLE){

            shape = shapeFactory.createRectangle(start, end);
        }

        paintCanvas.paint(graphics2d);

    }

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }

    @Override
    public IShape getShape() {
        return shape;
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }



}
