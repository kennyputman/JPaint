package view.gui;

import model.interfaces.IShape;
import model.persistence.ShapeStore;
import model.types.ShapeType;
import view.interfaces.DrawStrategy;
import view.render.DrawEllipse;
import view.render.DrawGroup;
import view.render.DrawRectangle;
import view.render.DrawTriangle;

import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JComponent {

    private ShapeStore shapeStore;
    private DrawStrategy strategy;

    public PaintCanvas(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D) g;

        for (IShape shape : this.shapeStore.getShapeList()) {
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
            } else if (type == ShapeType.GROUP) {
                strategy = new DrawGroup(shape, graphics2d);
                strategy.draw();
            }else {
                throw new IllegalArgumentException("Invalid Shape Type");
            }
        }
        this.repaint();
    }
}
