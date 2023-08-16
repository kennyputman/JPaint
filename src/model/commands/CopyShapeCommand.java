package model.commands;

import model.interfaces.ICommand;
import model.persistence.ShapeStore;

public class CopyShapeCommand implements ICommand {

    private final ShapeStore shapeStore = ShapeStore.getInstance();

    public CopyShapeCommand() {
    }

    @Override
    public void execute() {
        this.shapeStore.clearClipboard();
        this.shapeStore.copyShapes();
    }
}
