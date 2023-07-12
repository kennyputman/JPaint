package model.commands;

import model.AppStateOpts;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.shapes.Point;
import model.shapes.ShapeFactory;
import model.types.ShapeType;
import view.gui.PaintCanvas;

public class CreateShapeCommand implements ICommand, IUndoable {

    private final IShapeFactory shapeFactory = new ShapeFactory();
    private final PaintCanvas paintCanvas;
    private final ShapeList shapeList;
    private IShape createdShape;
    private Point start;
    private Point end;
    ApplicationState applicationState;


    public CreateShapeCommand(
            Point start, Point end, PaintCanvas paintCanvas, ShapeList shapeList, ApplicationState appState) {
        this.start = start;
        this.end = end;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.applicationState = appState;
    }

    @Override
    public void execute() {
        var type = this.applicationState.getActiveShapeType();

        var stateOptions = new AppStateOpts(
                applicationState.getActiveShapeType(),
                applicationState.getActivePrimaryColor(),
                applicationState.getActiveSecondaryColor(),
                applicationState.getActiveShapeShadingType(),
                applicationState.getActiveMouseMode());

        // TODO create shapes ellipse and triangle
        if (type == ShapeType.RECTANGLE) {
            createdShape = shapeFactory.createRectangle(start, end, stateOptions);
        } else if (type == ShapeType.TRIANGLE) {
            throw new UnsupportedOperationException();
        } else if (type == ShapeType.ELLIPSE) {
            throw new UnsupportedOperationException();
        } else {
            throw new RuntimeException("Error: Shape type cannot be found");
        }

        shapeList.add(createdShape);
        paintCanvas.repaint();

    }

    @Override
    public void redo() {
        shapeList.add(createdShape);
        paintCanvas.repaint();

    }

    @Override
    public void undo() {
        shapeList.remove(createdShape);
        paintCanvas.repaint();
    }
}
