package model.interfaces;

import model.AppStateOpts;
import model.types.ShapeType;

public interface IShape {
    int getX();

    int getY();

    int getHeight();

    int getWidth();

    AppStateOpts getOpts();
}
