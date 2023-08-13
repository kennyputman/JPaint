package model.commands;

import model.AppStateOpts;
import model.interfaces.*;
import model.persistence.ShapeStore;
import model.shapes.Group;
import model.shapes.Point;
import model.shapes.ShapeFactory;
import model.types.ShapeColor;
import model.types.ShapeSelection;
import model.types.ShapeShadingType;
import model.types.ShapeType;

import java.util.ArrayList;
import java.util.Collections;


public class GroupCommand implements ICommand, IUndoable {

    ShapeStore shapeStore;
    private final IShapeFactory shapeFactory = new ShapeFactory();
    IShape group;

    public GroupCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {
        var points = getStartEndPoints();

        var appState = new AppStateOpts(
                ShapeType.RECTANGLE,
                ShapeColor.BLACK,
                ShapeColor.BLACK,
                ShapeShadingType.OUTLINE
        );


        group = shapeFactory.createGroup(points[0], points[1],appState);

        // Have to cast it to group in order to access class specific method outside IShape interface
        if(group instanceof Group casted){
            casted.addChildren(shapeStore);
        }

        shapeStore.clearObservers();
        for (IShape shape : shapeStore.getShapeList()) {
            shape.setShapeSelection(ShapeSelection.NOT_SELECTED);
        }
        shapeStore.registerObserver((IObserver) group);
        group.setShapeSelection(ShapeSelection.SELECTED);


        shapeStore.addShape(group);
    }

    @Override
    public void redo() {
        shapeStore.addShape(group);
    }

    @Override
    public void undo() {
        shapeStore.removeShape(group);}

    private Point[] getStartEndPoints(){
        var selectedShapes = shapeStore.getSelectedShapes();

        var xCoords = new ArrayList<Integer>();
        var yCoords = new ArrayList<Integer>();

        for (IShape shape : selectedShapes) {
            int xL = shape.getX();
            int yL = shape.getY();
            int xH = shape.getX() + shape.getWidth();
            int yH = shape.getY() + shape.getHeight();
            xCoords.add(xL);
            xCoords.add(xH);
            yCoords.add(yL);
            yCoords.add(yH);
        }

        var xMin = Collections.min(xCoords) - 10;
        var yMin = Collections.min(yCoords) - 10;
        var xMax = Collections.max(xCoords) + 10;
        var yMax = Collections.max(yCoords) +10;

        Point start = new Point(xMin, yMin);
        Point end = new Point(xMax, yMax);

        return new Point[]{start,end};
    }
}
