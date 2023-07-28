package model.interfaces;

import model.AppStateOpts;
import model.types.ShapeSelection;

public interface IShape {

    AppStateOpts getOpts();

    int getX();

    int getY();

    int getHeight();

    int getWidth();

    void move(int xD, int yD);

    ShapeSelection getShapeSelection();

    void setShapeSelection(ShapeSelection shapeSelection);

    /*
        Implements a shallow copy of IShape
     */
    IShape copy();

}
