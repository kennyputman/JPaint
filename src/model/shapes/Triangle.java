package model.shapes;

import model.AppStateOpts;
import model.interfaces.IObserver;
import model.interfaces.IShape;

import java.util.Arrays;

public class Triangle implements IShape, IObserver {

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

    @Override
    public int getX() {
        return Arrays.stream(xCoordinates).min().orElseThrow();
    }

    @Override
    public int getY() {
        return Arrays.stream(yCoordinates).min().orElseThrow();
    }

    @Override
    public int getHeight() {
        var minY = Arrays.stream(yCoordinates).min().orElseThrow();
        var maxY = Arrays.stream(yCoordinates).max().orElseThrow();

        return maxY - minY;
    }

    @Override
    public int getWidth() {
        var minX = Arrays.stream(xCoordinates).min().orElseThrow();
        var maxX = Arrays.stream(xCoordinates).max().orElseThrow();

        return maxX - minX;
    }

    @Override
    public void Update(int xD, int yD) {
        for (int i = 0; i < 3; i++) {
            xCoordinates[i] = xCoordinates[i] + xD;
        }

        for (int i = 0; i < 3; i++) {
            yCoordinates[i] = yCoordinates[i] + yD;
        }
    }
}
