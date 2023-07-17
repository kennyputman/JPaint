package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Point;

public class MoveCommand implements IUndoable, ICommand {

    private Point startPoint;
    private Point endPoint;
    private ShapeStore shapeStore;

    public MoveCommand(Point startPoint, Point endPoint, ShapeStore shapeStore) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {
        int xD = endPoint.x() - startPoint.x();
        int yD = endPoint.y() - startPoint.y();
        shapeStore.moveSubscribers(xD, yD);

    }

    @Override
    public void redo() {
            int xD = endPoint.x() - startPoint.x();
            int yD = endPoint.y() - startPoint.y();
            shapeStore.moveSubscribers(xD, yD);
    }

    @Override
    public void undo() {
            int xD = startPoint.x() - endPoint.x();
            int yD = startPoint.y() -endPoint.y();
            shapeStore.moveSubscribers(xD, yD);
    }
}
