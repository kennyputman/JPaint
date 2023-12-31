package view.handlers;

import model.AppStateOpts;
import model.commands.CommandHistory;
import model.commands.CreateShapeCommand;
import model.commands.MoveCommand;
import model.commands.SelectCommand;
import model.persistence.ApplicationState;
import model.shapes.Point;
import view.gui.PaintCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    PaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private ApplicationState applicationState;

    public ClickHandler(PaintCanvas p, ApplicationState applicationState) {
        this.paintCanvas = p;
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

                var appStateOpts = new AppStateOpts(
                        applicationState.getActiveShapeType(),
                        applicationState.getActivePrimaryColor(),
                        applicationState.getActiveSecondaryColor(),
                        applicationState.getActiveShapeShadingType()
                );

                CreateShapeCommand createShapeCommand = new CreateShapeCommand(
                        startPoint,
                        endPoint,
                        appStateOpts
                );

                createShapeCommand.execute();
                CommandHistory.add(createShapeCommand);
                paintCanvas.repaint();
            }
            case SELECT -> {
                SelectCommand createSelectCommand = new SelectCommand(
                        startPoint,
                        endPoint
                );

                createSelectCommand.execute();
                paintCanvas.repaint();
            }
            case MOVE -> {

                MoveCommand createMoveCommand = new MoveCommand(
                        startPoint,
                        endPoint
                );
                createMoveCommand.execute();
                CommandHistory.add(createMoveCommand);
                paintCanvas.repaint();
            }
        }
    }
}

