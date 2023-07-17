package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IMoveObserver;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import model.shapes.Point;

public class SelectCommand implements ICommand {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final ShapeStore shapeStore;

    public SelectCommand(Point startPoint, Point endPoint, ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
        this.x = Math.min(startPoint.x(), endPoint.x());
        this.y = Math.min(startPoint.y(), endPoint.y());
        this.width = Math.abs(startPoint.x() - endPoint.x());
        this.height = Math.abs(startPoint.y() - endPoint.y());
    }

    @Override
    public void execute() {
        shapeStore.clearObservers();
        for (IShape shape : shapeStore.getShapeList()) {
            if (detectCollision(shape)) {
                shapeStore.registerObserver((IMoveObserver) shape);
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
