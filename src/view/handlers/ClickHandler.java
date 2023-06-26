package view.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Point;

public class ClickHandler extends MouseAdapter {


    // redefine these into point classes
    private Point startPoint;
    private Point endPoint;


    public ClickHandler() {}

    @Override
    public void mousePressed(MouseEvent e) {

        startPoint = new Point(e.getX(), e.getY());


        System.out.println(startPoint.x() + "," + startPoint.y());

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());

        var x = Math.max(startPoint.x(), endPoint.x());
        var y = Math.max(startPoint.y(), endPoint.y());
        var width = Math.abs(startPoint.x() - endPoint.x());
        var height = Math.abs(startPoint.y() - endPoint.y());

        System.out.printf("FillRect(%d,%d,%d,%d)\n", x,y,width,height);

    }
}
