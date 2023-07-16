package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import model.shapes.Point;

public class CreateSelectCommand implements ICommand, IUndoable {

    private Point startPoint;
    private Point endPoint;
    private int x;
    private int y;
    private int width;
    private int height;
    private ShapeList shapeList;
    private ShapeList selectedShapes = new ShapeList();

    public CreateSelectCommand(Point startPoint, Point endPoint, ShapeList shapeList) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeList = shapeList;
        this.x = Math.min(startPoint.x(), endPoint.x());
        this.y = Math.min(startPoint.y(), endPoint.y());
        this.width = Math.abs(startPoint.x() - endPoint.x());
        this.height = Math.abs(startPoint.y() - endPoint.y());
    }

    @Override
    public void execute() {
        for(IShape shape: shapeList.getShapeList()){
            if(detectCollision(shape)){
                selectedShapes.add(shape);
            }
        }
    }

    private boolean detectCollision(IShape shape) {
        return
                x < (shape.getX() + shape.getWidth()) &&
                x + width > shape.getX() &&
                y < shape.getY() + shape.getHeight() &&
                y + height > shape.getY();
    }

    public ShapeList getSelectedShapes(){
        return this.selectedShapes;
    }

    @Override
    public void redo() {
        // FIXME should selectedShapes be a global static?
    }

    @Override
    public void undo() {
        // FIXME should selectedShapes be a global static?
    }
}
