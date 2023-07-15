package view.gui;

import model.shapes.Ellipse;
import model.shapes.Triangle;
import model.shapes.Rectangle;
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
                var rect = (Rectangle) shape;
                graphics2d.setColor(opts.activePrimaryColor().color());
                graphics2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(opts.activeSecondaryColor().color());
                graphics2d.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

                Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                graphics2d.setStroke(stroke);
                graphics2d.setColor(Color.BLACK);
                graphics2d.drawRect(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
            }

            if (opts.activeShape() == ShapeType.TRIANGLE){

                var tri = (Triangle) shape;
                graphics2d.setColor(opts.activePrimaryColor().color());
                graphics2d.fillPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());

                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(opts.activeSecondaryColor().color());
                graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());

                Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                graphics2d.setStroke(stroke);
                graphics2d.setColor(Color.BLACK);
                graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());
            }

            if (opts.activeShape() == ShapeType.ELLIPSE){
                var rect = (Ellipse) shape;
                graphics2d.setColor(opts.activePrimaryColor().color());
                graphics2d.fillOval(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(opts.activeSecondaryColor().color());
                graphics2d.drawOval(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

                Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
                graphics2d.setStroke(stroke);
                graphics2d.setColor(Color.BLACK);
                graphics2d.drawOval(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
            }
        }

        this.repaint();
        // Once these are set you can use paintCanvas.Repaint


    }
}
