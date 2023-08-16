package model.shapes;

import model.AppStateOpts;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.persistence.ShapeStore;
import model.types.ShapeSelection;

import java.util.ArrayList;
import java.util.List;

public class Group implements IShape, IObserver {

    private final int height;
    private final int width;
    private final AppStateOpts appStateOpts;
    List<IShape> children = new ArrayList<>();
    private int x;
    private int y;
    private ShapeSelection shapeSelection;
    private IShape parent = null;

    public Group(int x, int y, int height, int width, AppStateOpts appStateOpts) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.appStateOpts = appStateOpts;
        this.shapeSelection = ShapeSelection.NOT_SELECTED;
    }

    public void addChildren(ShapeStore store) {
        children.addAll(store.getSelectedShapes());

        // All drawing and rendering of children is handled at the group level.
        // This removes the children from the store and resets their selected tag
        for(IShape child: children){
            store.removeShape(child);
            child.setShapeSelection(ShapeSelection.NOT_SELECTED);
        }

        for (IShape child : children) {
            if (child.getParent() == null) {
                child.setParent(this);
            }
        }
    }

    public List<IShape> getChildren() {
        return children;
    }

    @Override
    public AppStateOpts getOpts() {
        return appStateOpts;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void move(int xD, int yD) {

        this.x = x + xD;
        this.y = y + yD;

        for (IShape child : children) {
            if (child.getParent() == this) {
                child.move(xD, yD);
            }
        }
    }

    @Override
    public ShapeSelection getShapeSelection() {
        return this.shapeSelection;
    }

    @Override
    public void setShapeSelection(ShapeSelection shapeSelection) {
        this.shapeSelection = shapeSelection;

        for (IShape child : children) {
            child.setShapeSelection(ShapeSelection.NOT_SELECTED);
        }
    }

    @Override
    public IShape copy() {

        var copy = new Group(x,y,height, width, appStateOpts);

        List<IShape> childrenCopies = new ArrayList<>();

        for(IShape child: children){
            IShape childCopy = child.copy();
            childCopy.setParent(copy);
            childrenCopies.add(childCopy);
        }

        copy.children.addAll(childrenCopies);
        return copy;
    }

    @Override
    public IShape getParent() {
        return parent;
    }

    @Override
    public void setParent(IShape parent) {
        this.parent = parent;
    }
}
