package view.handlers;

import model.commands.CommandHistory;
import model.commands.CreateMoveCommand;
import model.commands.CreateSelectCommand;
import model.commands.CreateShapeCommand;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import model.shapes.Point;
import view.gui.PaintCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private ShapeStore shapeStore;
    private ApplicationState applicationState;

    public ClickHandler(PaintCanvas p, ShapeStore shapeStore, ApplicationState applicationState) {
        this.paintCanvas = p;
        this.shapeStore = shapeStore;
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

        switch (state) {
            case DRAW -> {
                CreateShapeCommand createShapeCommand = new CreateShapeCommand(
                        startPoint,
                        endPoint,
                        paintCanvas,
                        shapeStore,
                        applicationState
                );

                createShapeCommand.execute();
                CommandHistory.add(createShapeCommand);
            }
            case SELECT -> {
                CreateSelectCommand createSelectCommand = new CreateSelectCommand(
                        startPoint,
                        endPoint,
                        shapeStore
                );

                createSelectCommand.execute();
                paintCanvas.repaint();
            }
            case MOVE -> {

                CreateMoveCommand createMoveCommand = new CreateMoveCommand(
                        startPoint,
                        endPoint,
                        shapeStore
                );
                createMoveCommand.execute();
                CommandHistory.add(createMoveCommand);
                paintCanvas.repaint();
            }

        }
    }


}

