package model.shapes;

import model.AppStateOpts;
import model.interfaces.IObserver;
import model.interfaces.IShape;
import model.types.ShapeSelection;

import java.util.ArrayList;
import java.util.List;

/*
    TODO: Group Composite
        -  implements all of the methods from IShape
 */
public class Group implements IShape, IObserver {

    private int x;
    private int y;
    private final int height;
    private final int width;
    private final AppStateOpts appStateOpts;
    private ShapeSelection shapeSelection;

    List<IShape> children = new ArrayList<>();

    public Group(int x, int y, int height, int width, AppStateOpts appStateOpts) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.appStateOpts = appStateOpts;
        this.shapeSelection = ShapeSelection.NOT_SELECTED;
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

        for(IShape child: children){
            child.move(xD, yD);
        }
    }

    @Override
    public ShapeSelection getShapeSelection() {
        return this.shapeSelection;
    }

    @Override
    public void setShapeSelection(ShapeSelection shapeSelection) {
        this.shapeSelection = shapeSelection;
    }

    @Override
    public void update(int xD, int yD) {
        this.x = x + xD;
        this.y = y + yD;
    }

    // TODO need to fix copy()
    @Override
    public IShape copy() {
        return new Rectangle(x,y,height,width,appStateOpts);
    }
}
