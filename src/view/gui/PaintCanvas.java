package view.gui;

import model.types.ShapeType;
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

        // TODO extract this into a strategy pattern
        for (IShape shape : this.shapeList.getShapeList()) {
            var opts = shape.getOpts();
            if (opts.activeShape() == ShapeType.RECTANGLE) {
                graphics2d.setColor(opts.activePrimaryColor().color());
                graphics2d.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());

                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(opts.activeSecondaryColor().color());
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
