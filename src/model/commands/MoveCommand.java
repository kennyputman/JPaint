package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Point;

public class MoveCommand implements IUndoable, ICommand {

    private final int xD;
    private final int yD;

    private final ShapeStore shapeStore;

    public MoveCommand(Point startPoint, Point endPoint, ShapeStore shapeStore) {
        this.xD = endPoint.x() - startPoint.x();
        this.yD = endPoint.y() - startPoint.y();
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {

        shapeStore.moveObservers(xD, yD);
    }

    @Override
    public void redo() {
        shapeStore.moveObservers(xD, yD);
    }

    @Override
    public void undo() {
        shapeStore.moveObservers(-xD, -yD);
    }
}
