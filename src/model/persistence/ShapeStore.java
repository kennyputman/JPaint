package model.persistence;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.interfaces.ISubject;
import model.types.ShapeSelection;

import java.util.ArrayList;
import java.util.List;

public class ShapeStore implements ISubject {

    private static ShapeStore instance;

    private final List<IShape> shapeList;

    /*
        The observers are currently held in a single list for a single type of even 'move'
        If another event was added this would need to be updated to a Map<String, List<IObserver>>
     */
    private final List<IObserver> observers;
    private final List<IShape> clipboard;

    private ShapeStore() {
        this.shapeList = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.clipboard = new ArrayList<>();
    }

    public static ShapeStore getInstance(){
        if(instance == null){
            instance = new ShapeStore();
        }

        return instance;
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
    public void registerObserver(IObserver shape) {
        this.observers.add(shape);
    }

    @Override
    public void clearObservers() {
        this.observers.clear();
    }

    public void clearSelections(){
        for (IShape shape : shapeList) {
            shape.setShapeSelection(ShapeSelection.NOT_SELECTED);
        }
    }

    @Override
    public void moveObservers(int xD, int yD) {
        for (IObserver observer : observers) {
            observer.move(xD, yD);
        }
    }

    @Override
    public void removeObserver(IObserver shape) {
        observers.remove(shape);
    }

    public void copyShapes(){
        for(IObserver observer: observers){
            clipboard.add((IShape)observer);
        }
    }

    public List<IShape> getSelectedShapes(){
        return observers.stream().map(s -> (IShape) s).toList();
    }


    public List<IShape> getClipboard() {
        return this.clipboard;
    }

    public void clearClipboard(){
        this.clipboard.clear();
    }
}
