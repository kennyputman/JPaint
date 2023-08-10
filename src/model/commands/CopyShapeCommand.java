package model.commands;

import model.interfaces.ICommand;
import model.persistence.ShapeStore;

public class CopyShapeCommand implements ICommand {

    private ShapeStore shapeStore;

    public CopyShapeCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {
        this.shapeStore.copyShapes();
    }
}
