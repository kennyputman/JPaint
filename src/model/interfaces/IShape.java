package model.interfaces;

import model.AppStateOpts;
import model.types.ShapeSelection;

public interface IShape {

    AppStateOpts getOpts();

    int getX();

    int getY();

    int getHeight();

    int getWidth();

    ShapeSelection getShapeSelection();

    void setShapeSelection(ShapeSelection shapeSelection);

}
