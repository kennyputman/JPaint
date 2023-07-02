package view;

import model.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;

public interface IUndoable {

    public void createShape(Point start, Point end, ShapeType type, IShapeFactory shapeFactory);

    public IShape getShape();
    public void redo();
    public void undo();
}
