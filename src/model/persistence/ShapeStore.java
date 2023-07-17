package model.persistence;

import model.interfaces.IMoveObserver;
import model.interfaces.ISubject;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeStore implements ISubject {

    private final List<IShape> shapeList;
    private final List<IMoveObserver> observers;

    public ShapeStore() {
        this.shapeList = new ArrayList<>();
        this.observers = new ArrayList<>();
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
    public void registerObserver(IMoveObserver shape) {
        this.observers.add(shape);
    }

    @Override
    public void clearObservers() {
        this.observers.clear();
    }

    @Override
    public void moveObservers(int xD, int yD) {
        for (IMoveObserver observer: observers) {
            observer.move(xD, yD);
        }
    }
}
