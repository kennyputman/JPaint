package model.persistence;

import model.interfaces.IMoveable;
import model.interfaces.IPublisher;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeStore implements IPublisher {

    private final List<IShape> shapeList;
    private List<IMoveable> subscribers;

    public ShapeStore() {
        this.shapeList = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }


    public void addShape(IShape shape) {

        shapeList.add(shape);
    }

    public void removeShape(IShape shape) {
        shapeList.remove(shape);
    }

    public List<IShape> getShapeList() {
        return shapeList;
    }


    @Override
    public void subscribe(IMoveable shape) {
        this.subscribers.add(shape);
    }

    @Override
    public void unsubscribe(IMoveable shape) {
        this.subscribers.remove(shape);
    }

    @Override
    public void clearSubscribers() {
        this.subscribers.clear();
    }

    @Override
    public void moveSubscribers(int xD, int yD) {
        for (IMoveable sub: subscribers) {
            sub.move(xD, yD);
        }
    }
}
