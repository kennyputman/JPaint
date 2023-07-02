package view.handlers;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Point;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import model.shapes.Rectangle;
import view.gui.PaintCanvas;

public class ClickHandler extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    PaintCanvas paintCanvas;
    IShapeFactory shapeFactory;

    public ClickHandler(PaintCanvas p, IShapeFactory shapeFactory) {
        this.paintCanvas = p;
        this.shapeFactory = shapeFactory;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        Graphics2D graphics2d = (Graphics2D) paintCanvas.getGraphics();

        endPoint = new Point(e.getX(), e.getY());

        IShape r = shapeFactory.createRectangle(startPoint, endPoint);

        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());

        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(r.getX()-5, r.getY()-5, r.getWidth()+10, r.getHeight()+10);

    }

}
