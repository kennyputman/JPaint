package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;

import java.util.ArrayList;
import java.util.List;

public class DeleteShapeCommand implements ICommand, IUndoable {

    private final List<IShape> shapes;
    private final ShapeStore shapeStore = ShapeStore.getInstance();

    public DeleteShapeCommand() {
        this.shapes = new ArrayList<>(shapeStore.getSelectedShapes());
    }

    @Override
    public void execute() {
        for (IShape shape : shapes) {
            shapeStore.removeShape(shape);
        }
    }

    @Override
    public void redo() {
        for (IShape shape : shapes) {
            shapeStore.removeShape(shape);
        }
    }

    @Override
    public void undo() {
        for (IShape shape : shapes) {
            shapeStore.addShape(shape);
        }
    }
}
