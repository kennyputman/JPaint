package model.shapes;

import model.AppStateOpts;
import model.interfaces.IObserver;
import model.interfaces.IShape;

public class Rectangle implements IShape, IObserver {
    private int x;
    private int y;
    private final int height;
    private final int width;
    private final AppStateOpts appStateOpts;

    public Rectangle(int x, int y, int height, int width, AppStateOpts appStateOpts) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.appStateOpts = appStateOpts;
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
    public void Update(int xD, int yD) {
        this.x = x + xD;
        this.y = y + yD;
    }

    public AppStateOpts getOpts(){
        return appStateOpts;
    }
}
