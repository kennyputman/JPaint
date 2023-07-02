package view.interfaces;

import model.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import view.gui.PaintCanvas;

public interface IUndoable {

    // QUESTION should createShape and getters be in this? seems wrong.
    public void execute(Point start, Point end, ShapeType type, IShapeFactory shapeFactory, PaintCanvas p);

    public IShape getShape();

    public ShapeType getShapeType();
    public void redo();
    public void undo();
}
