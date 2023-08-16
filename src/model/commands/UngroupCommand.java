package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Group;

import java.util.ArrayList;
import java.util.List;

public class UngroupCommand implements ICommand, IUndoable {

    private final List<Group> selectedGroups;
    private final ShapeStore shapeStore = ShapeStore.getInstance();

    public UngroupCommand() {
        this.selectedGroups = new ArrayList<>();
    }


    @Override
    public void execute() {
        var selectedShapes = shapeStore.getSelectedShapes();

        for(IShape shape: selectedShapes){
            if(shape instanceof Group group){
                for(IShape child: group.getChildren()){
                    child.setParent(null);
                    shapeStore.addShape(child);
                }
                shapeStore.removeShape(group);
                selectedGroups.add(group);
            }
        }
    }

    @Override
    public void redo() {
        for(Group group: selectedGroups){
            for(IShape child: group.getChildren()){
                child.setParent(null);
                shapeStore.addShape(child);
            }
            shapeStore.removeShape(group);
        }
    }

    @Override
    public void undo() {
        for(Group group: selectedGroups){
            for(IShape child: group.getChildren()){
                child.setParent(group);
                shapeStore.removeShape(child);
            }
            shapeStore.addShape(group);
        }
    }
}
