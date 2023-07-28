package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;

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
        for(IShape shape: shapes){
            shapeStore.removeShape(shape);
        }

    }

    @Override
    public void redo() {
        for(IShape shape: shapes){
            shapeStore.removeShape(shape);
        }
    }

    @Override
    public void undo() {
        for(IShape shape: shapes){
            shapeStore.addShape(shape);
        }
    }
}
