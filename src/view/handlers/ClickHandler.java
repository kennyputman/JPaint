package view.handlers;

import model.Point;
import model.ShapeType;
import model.interfaces.IShape;
import model.interfaces.IShapeFactory;
import view.Command;
import view.CommandHistory;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    PaintCanvas paintCanvas;
    IShapeFactory shapeFactory;
    private Point startPoint;
    private Point endPoint;

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

        endPoint = new Point(e.getX(), e.getY());



        Command command = new Command();

        command.execute(startPoint, endPoint, ShapeType.RECTANGLE, shapeFactory, paintCanvas);
        CommandHistory.add(command);


    }

}
