package model.commands;

import jdk.jshell.spi.ExecutionControl;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shapes.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.persistence.ShapeList;
import view.gui.PaintCanvas;

public class CreateShapeCommand implements ICommand, IUndoable {

    private IShape createdShape;
    private Point start;
    private Point end;
    private final ShapeType type;
    private final IShapeFactory shapeFactory;
    private final PaintCanvas paintCanvas;
    private final ShapeList shapeList;

    public CreateShapeCommand(
            Point start, Point end, ShapeType type,
            IShapeFactory shapeFactory, PaintCanvas paintCanvas, ShapeList shapeList) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.shapeFactory = shapeFactory;
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
    }

    @Override
    public void execute() {

        if(type == ShapeType.RECTANGLE){
            createdShape = shapeFactory.createRectangle(start, end);
        } else if(type == ShapeType.TRIANGLE){
            throw new UnsupportedOperationException();
        }else if(type == ShapeType.ELLIPSE){
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
