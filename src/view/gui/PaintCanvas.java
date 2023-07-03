package view.gui;

import model.ShapeType;
import model.interfaces.IShape;
import model.persistence.ShapeList;

import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JComponent {

    private ShapeList shapeList;

    public PaintCanvas(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D) g;

        // Draw all shapes here

        for (IShape shape : this.shapeList.getShapeList()) {

            if (shape.getShapeType() == ShapeType.RECTANGLE) {
                graphics2d.setColor(Color.GREEN);
                graphics2d.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());

                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(Color.BLUE);
                graphics2d.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());

                Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                graphics2d.setStroke(stroke);
                graphics2d.setColor(Color.BLACK);
                graphics2d.drawRect(shape.getX() - 5, shape.getY() - 5, shape.getWidth() + 10, shape.getHeight() + 10);
            }

        }

        this.repaint();
        // Once these are set you can use paintCanvas.Repaint


    }
}
