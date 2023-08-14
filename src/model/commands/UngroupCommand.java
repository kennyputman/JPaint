package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Group;

import java.util.ArrayList;
import java.util.List;

public class UngroupCommand implements ICommand, IUndoable {

    private final ShapeStore shapeStore;
    private final List<IShape> selectedGroups;

    public UngroupCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
        this.selectedGroups = new ArrayList<>();
    }


    @Override
    public void execute() {
        var selectedShapes = shapeStore.getSelectedShapes();

        for(IShape shape: selectedShapes){
            if(shape instanceof Group group){
                shapeStore.removeShape(group);
                selectedGroups.add(group);
            }
        }
    }

    @Override
    public void redo() {
        for(IShape group: selectedGroups){
            shapeStore.removeShape(group);
        }
    }

    @Override
    public void undo() {
        for(IShape group: selectedGroups){
            shapeStore.addShape(group);
        }
    }
}
