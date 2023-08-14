package model.shapes;

import model.AppStateOpts;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.types.ShapeSelection;

public class Ellipse implements IShape, IObserver {

    private int x;
    private int y;
    private final int height;
    private final int width;
    private final AppStateOpts appStateOpts;
    private ShapeSelection shapeSelection;
    private IShape parent = null;

    public Ellipse(int x, int y, int height, int width, AppStateOpts appStateOpts) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.appStateOpts = appStateOpts;
        this.shapeSelection = ShapeSelection.NOT_SELECTED;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void move(int xD, int yD) {
        this.x = x + xD;
        this.y = y + yD;
    }

    @Override
    public ShapeSelection getShapeSelection() {
        return shapeSelection;
    }

    @Override
    public void setShapeSelection(ShapeSelection shapeSelection) {
        this.shapeSelection = shapeSelection;
    }

    @Override
    public IShape copy() {
        return new Ellipse(x,y, height,width,appStateOpts);
    }

    @Override
    public IShape getParent() {
        return parent;
    }

    @Override
    public void setParent(IShape parent) {
        this.parent = parent;
    }

    public AppStateOpts getOpts(){
        return appStateOpts;
    }
}
