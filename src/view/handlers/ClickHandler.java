package view.handlers;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Point;
import view.gui.PaintCanvas;

public class ClickHandler extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    PaintCanvas paintCanvas;

    public ClickHandler(PaintCanvas p) {
        this.paintCanvas = p;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        Graphics2D graphics2d = (Graphics2D) paintCanvas.getGraphics();

        endPoint = new Point(e.getX(), e.getY());

        var x = Math.min(startPoint.x(), endPoint.x());
        var y = Math.min(startPoint.y(), endPoint.y());
        var width = Math.abs(startPoint.x() - endPoint.x());
        var height = Math.abs(startPoint.y() - endPoint.y());

        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(x, y, width, height);

        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(x, y, width, height);

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(x-5, y-5, width+10, height+10);

    }

}
