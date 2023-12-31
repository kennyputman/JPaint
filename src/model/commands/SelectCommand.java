package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import model.shapes.Group;
import model.shapes.Point;
import model.types.ShapeSelection;


public class SelectCommand implements ICommand {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final ShapeStore shapeStore = ShapeStore.getInstance();

    public SelectCommand(Point startPoint, Point endPoint) {
        this.x = Math.min(startPoint.x(), endPoint.x());
        this.y = Math.min(startPoint.y(), endPoint.y());
        this.width = Math.abs(startPoint.x() - endPoint.x());
        this.height = Math.abs(startPoint.y() - endPoint.y());
    }

    @Override
    public void execute() {
        shapeStore.clearObservers();
        for (IShape shape : shapeStore.getShapeList()) {
            // clears the current list of shapes so none or SELECTED
            shape.setShapeSelection(ShapeSelection.NOT_SELECTED);

            if (detectCollision(shape)) {
                if (shape instanceof Group group) {
                    for (IShape child : group.getChildren()) {
                        child.setShapeSelection(ShapeSelection.NOT_SELECTED);
                    }
                }
                shapeStore.registerObserver((IObserver) shape);
                shape.setShapeSelection(ShapeSelection.SELECTED);
            }
        }
    }

    private boolean detectCollision(IShape shape) {
        return
                x < (shape.getX() + shape.getWidth()) &&
                        x + width > shape.getX() &&
                        y < shape.getY() + shape.getHeight() &&
                        y + height > shape.getY();
    }
}
