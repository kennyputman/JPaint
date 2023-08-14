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
import java.util.List;


public class GroupCommand implements ICommand, IUndoable {

    private IShape group;
    private final ShapeStore shapeStore;
    private final IShapeFactory shapeFactory = new ShapeFactory();

    public GroupCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
    }

    @Override
    public void execute() {
        var points = getStartEndPoints();

        // default options for bounding box on a group
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

        addGroupToShapeStore();
    }

    @Override
    public void redo() {
        addGroupToShapeStore();
    }

    @Override
    public void undo() {
        shapeStore.removeShape(group);

        // reruns the selection command for the group members since selection is not addded to command history
        Point[] points = getStartEndPoints();
        SelectCommand command = new SelectCommand(points[0], points[1], shapeStore);
        command.execute();
    }


    /**
     * - clears the selected shapes and observers from the shape store
     * <br> - Sets the shape store selection and observer to the current grouping
     * <br> - this will override any other observers and selections to only the group that is being created
     */
    private void addGroupToShapeStore(){

        // clears the previous selections and observers from the shape store
        shapeStore.clearSelections();
        shapeStore.clearObservers();


        // sets the selected shapes and observers to only the group and not its children
        group.setShapeSelection(ShapeSelection.SELECTED);
        shapeStore.registerObserver((IObserver) group);

        shapeStore.addShape(group);
    }

    /**
     * Iterates through the selected shapes to find the minimum and maximum x,y positions
     * @return     Point[start, end] - start point is (minX, minY) end point is (maxX, maxY)
     */
    private Point[] getStartEndPoints(){
        List<IShape> selectedShapes = shapeStore.getSelectedShapes();

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

        int xMin = Collections.min(xCoords) - 10;
        int yMin = Collections.min(yCoords) - 10;
        int xMax = Collections.max(xCoords) + 10;
        int yMax = Collections.max(yCoords) +10;

        Point start = new Point(xMin, yMin);
        Point end = new Point(xMax, yMax);

        return new Point[]{start,end};
    }


}
