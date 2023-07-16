package view.handlers;

import model.commands.CommandHistory;
import model.commands.CreateSelectCommand;
import model.commands.CreateShapeCommand;
import model.persistence.ApplicationState;
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
    private ApplicationState applicationState;

    public ClickHandler(PaintCanvas p, ShapeList shapeList, ApplicationState applicationState) {
        this.paintCanvas = p;
        this.shapeList = shapeList;
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endPoint = new Point(e.getX(), e.getY());

        var state = applicationState.getActiveMouseMode();

        switch(state){
            case DRAW -> {
                CreateShapeCommand createShapeCommand = new CreateShapeCommand(
                        startPoint,
                        endPoint,
                        paintCanvas,
                        shapeList,
                        applicationState
                );

                createShapeCommand.execute();
                CommandHistory.add(createShapeCommand);
            }
            case SELECT -> {
                CreateSelectCommand createSelectCommand = new CreateSelectCommand(
                        startPoint,
                        endPoint,
                        shapeList
                );

                createSelectCommand.execute();
                paintCanvas.repaint();
            }
            case MOVE -> {
                System.out.println("move " + startPoint + "," + endPoint);
            }
        }


    }

}
