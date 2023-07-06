package view.handlers;

import model.commands.CommandHistory;
import model.commands.CreateShapeCommand;
import model.persistence.ShapeList;
import model.shapes.Point;
import model.types.ShapeType;
import view.gui.PaintCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private ShapeList shapeList;

    public ClickHandler(PaintCanvas p, ShapeList shapeList) {
        this.paintCanvas = p;
        this.shapeList = shapeList;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());

        CreateShapeCommand createShapeCommand = new CreateShapeCommand(
                startPoint,
                endPoint,
                ShapeType.RECTANGLE,
                paintCanvas,
                shapeList
        );

        createShapeCommand.execute();
        CommandHistory.add(createShapeCommand);
    }

}
