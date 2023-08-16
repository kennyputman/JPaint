package model.commands;

import model.AppStateOpts;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Point;
import model.shapes.ShapeFactory;
import model.types.ShapeType;

public class CreateShapeCommand implements ICommand, IUndoable {

    private final IShapeFactory shapeFactory = new ShapeFactory();
    private final ShapeStore shapeStore = ShapeStore.getInstance();
    AppStateOpts appStateOpts;
    private IShape createdShape;
    private final Point start;
    private final Point end;


    public CreateShapeCommand(
            Point start, Point end, AppStateOpts appState) {
        this.start = start;
        this.end = end;
        this.appStateOpts = appState;
    }

    @Override
    public void execute() {
        ShapeType type = this.appStateOpts.activeShape();

        if (type == ShapeType.RECTANGLE) {
            createdShape = shapeFactory.createRectangle(start, end, appStateOpts);
        } else if (type == ShapeType.TRIANGLE) {
            createdShape = shapeFactory.createTriangle(start, end, appStateOpts);
        } else if (type == ShapeType.ELLIPSE) {
            createdShape = shapeFactory.createEllipse(start, end, appStateOpts);
        } else {
            throw new RuntimeException("Error: Shape type cannot be found");
        }

        shapeStore.addShape(createdShape);
    }

    @Override
    public void redo() {
        shapeStore.addShape(createdShape);
    }

    @Override
    public void undo() {
        shapeStore.removeShape(createdShape);}
}
