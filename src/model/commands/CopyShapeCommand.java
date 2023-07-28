package model.commands;

import model.interfaces.ICommand;
import model.persistence.ShapeStore;

public class CopyShapeCommand implements ICommand {

    private ShapeStore shapeStore;

    // TODO may want to copy shapes into some form persistence in this class
    public CopyShapeCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {
        this.shapeStore.copyShapes();
    }
}
