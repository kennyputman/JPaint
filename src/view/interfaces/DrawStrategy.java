package view.interfaces;

import model.interfaces.IShape;

import java.awt.*;

public interface DrawStrategy {

    void draw(IShape shape, Graphics2D graphics2d);
}
