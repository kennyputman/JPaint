package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import model.shapes.Point;

public class CreateMoveCommand implements IUndoable, ICommand {

    private Point startPoint;
    private Point endPoint;
    private ShapeList selectedShapes;

    public CreateMoveCommand(Point startPoint, Point endPoint,ShapeList selectedShapes) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.selectedShapes = selectedShapes;
    }

    @Override
    public void execute() {
        for(IShape shape: selectedShapes.getShapeList()){
            int xD = endPoint.x() - startPoint.x();
            int yD = endPoint.y() - startPoint.y();
            shape.move(xD,yD);
        }

    }

    @Override
    public void redo() {
        for(IShape shape: selectedShapes.getShapeList()){
            int xD = endPoint.x() - startPoint.x();
            int yD = endPoint.y() - startPoint.y();
            shape.move(xD,yD);
        }
    }

    @Override
    public void undo() {
        for(IShape shape: selectedShapes.getShapeList()){
            int xD = startPoint.x() - endPoint.x();
            int yD = startPoint.y() -endPoint.y();
            shape.move(xD,yD);
        }
    }
}
