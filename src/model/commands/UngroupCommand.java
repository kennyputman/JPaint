package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Group;

import java.util.List;

public class UngroupCommand implements ICommand {

    ShapeStore shapeStore;

    public UngroupCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {
        List<IShape> shapes =  this.shapeStore.getSelectedShapes();

        for(IShape shape: shapes){
            if(shape instanceof Group){
                shapeStore.removeShape(shape);
            }
        }
    }

}
