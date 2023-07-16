package model.persistence;

import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeStore {

    private final List<IShape> shapeList;
    private final List<IShape> selectedShapes;

    public ShapeStore() {
        this.shapeList = new ArrayList<>();
        this.selectedShapes = new ArrayList<>();
    }


    public void addSelectedShape(IShape shape) {
        this.selectedShapes.add(shape);
    }

    public void removeSelectedShape(IShape shape) {
        selectedShapes.remove(shape);
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

    public List<IShape> getSelectedShapes() {
        return selectedShapes;
    }

    public void clearSelectedShapes() {
        selectedShapes.clear();
    }
}
