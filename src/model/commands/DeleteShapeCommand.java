package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Group;

import java.util.ArrayList;
import java.util.List;

public class DeleteShapeCommand implements ICommand, IUndoable {

    private final ShapeStore shapeStore;
    private final List<IShape> shapes;

    public DeleteShapeCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
        this.shapes = new ArrayList<>(shapeStore.getSelectedShapes());
    }

    @Override
    public void execute() {
        for (IShape shape : shapes) {
            removeShape(shape);
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
            addShape(shape);
        }
    }

    /*
        private recursive helper function to add shapes for groups and children
     */
    private void addShape(IShape shape) {
        if (shape instanceof Group group) {
            for (IShape child : group.getChildren()) {
                addShape(child);
            }
        }

        shapeStore.addShape(shape);
    }

    /*
        private recursive helper function to remove shapes for groups and children
     */
    private void removeShape(IShape shape) {
        if (shape instanceof Group group) {
            for (IShape child : group.getChildren()) {
                removeShape(child);
            }
        }

        shapeStore.removeShape(shape);
    }
}
