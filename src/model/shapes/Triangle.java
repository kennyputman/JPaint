package model.shapes;

import model.AppStateOpts;
import model.interfaces.IShape;

public class Triangle implements IShape {

    int[] xCoordinates;
    int[] yCoordinates;
    int n = 3;

    private final AppStateOpts appStateOpts;

    public Triangle(int[] xCoordinates, int[] yCoordinates, AppStateOpts appStateOpts) {
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.appStateOpts = appStateOpts;
    }


    public int[] getXCoord() {
        return xCoordinates;
    }

    public int[] getYCoord() {
        return yCoordinates;
    }

    public int getN() {
        return n;
    }

    @Override
    public AppStateOpts getOpts(){
        return appStateOpts;
    }
}
