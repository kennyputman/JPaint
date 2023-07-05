package view.handlers;

import model.shapes.Point;
import model.types.ShapeType;
import model.commands.CommandHistory;
import model.commands.CreateShapeCommand;
import model.interfaces.IShapeFactory;
import model.persistence.ShapeList;
import view.gui.PaintCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    PaintCanvas paintCanvas;
    IShapeFactory shapeFactory;
    private Point startPoint;
    private Point endPoint;
    private ShapeList shapeList;

    public ClickHandler(PaintCanvas p, IShapeFactory shapeFactory, ShapeList shapeList) {
        this.paintCanvas = p;
        this.shapeFactory = shapeFactory;
        this.shapeList = shapeList;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());

        // FIXME update shape type so it isn't hardcoded
        CreateShapeCommand createShapeCommand = new CreateShapeCommand(
                startPoint, endPoint, ShapeType.RECTANGLE, shapeFactory, paintCanvas, shapeList);
        createShapeCommand.execute();
        CommandHistory.add(createShapeCommand);


    }

}
