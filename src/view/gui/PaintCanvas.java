package view.gui;

import model.interfaces.IShape;
import model.persistence.ShapeList;
import model.types.ShapeType;
import view.interfaces.DrawStrategy;
import view.render.DrawEllipse;
import view.render.DrawRectangle;
import view.render.DrawTriangle;

import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JComponent {

    private ShapeList shapeList;
    private DrawStrategy strategy;

    public PaintCanvas(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D) g;

        for (IShape shape : this.shapeList.getShapeList()) {
            var type = shape.getOpts().activeShape();
            if (type == ShapeType.RECTANGLE) {
                strategy = new DrawRectangle(shape, graphics2d);
                strategy.draw();
            } else if (type == ShapeType.TRIANGLE) {
                strategy = new DrawTriangle(shape, graphics2d);
                strategy.draw();
            } else if (type == ShapeType.ELLIPSE) {
                strategy = new DrawEllipse(shape, graphics2d);
                strategy.draw();
            } else {
                throw new IllegalArgumentException("Invalid Shape Type");
            }
        }
        this.repaint();
    }
}
