package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import model.shapes.Point;
import model.shapes.ShapeFactory;
import model.types.ShapeType;
import view.gui.PaintCanvas;

public class CreateShapeCommand implements ICommand, IUndoable {

    private final ShapeType type;
    private final IShapeFactory shapeFactory = new ShapeFactory();
    private final PaintCanvas paintCanvas;
    private final ShapeList shapeList;
    private IShape createdShape;
    private Point start;
    private Point end;

    public CreateShapeCommand(
            Point start, Point end, ShapeType type, PaintCanvas paintCanvas, ShapeList shapeList) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
    }

    @Override
    public void execute() {

        if (type == ShapeType.RECTANGLE) {
            createdShape = shapeFactory.createRectangle(start, end);
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
