package model.persistence;

import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeList {

    private List<IShape> shapeList;

    public ShapeList() {
        this.shapeList =new ArrayList<>();
    }

    public void add(IShape shape){

        shapeList.add(shape);
    }

    public void remove(IShape shape){
        shapeList.remove(shape);
    }

    public List<IShape> getShapeList() {
        return shapeList;
    }
}
