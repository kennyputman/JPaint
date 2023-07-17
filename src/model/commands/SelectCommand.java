package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IMoveable;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import model.shapes.Point;

public class SelectCommand implements ICommand {

    private Point startPoint;
    private Point endPoint;
    private int x;
    private int y;
    private int width;
    private int height;
    private ShapeStore shapeStore;

    public SelectCommand(Point startPoint, Point endPoint, ShapeStore shapeStore) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeStore = shapeStore;
        this.x = Math.min(startPoint.x(), endPoint.x());
        this.y = Math.min(startPoint.y(), endPoint.y());
        this.width = Math.abs(startPoint.x() - endPoint.x());
        this.height = Math.abs(startPoint.y() - endPoint.y());
    }

    @Override
    public void execute() {
        shapeStore.clearSubscribers();
        for(IShape shape: shapeStore.getShapeList()){
            if(detectCollision(shape)){
                shapeStore.subscribe((IMoveable) shape);
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
