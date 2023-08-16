package controller;

import model.commands.*;
import model.interfaces.IApplicationState;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvas paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvas paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.UNDO, this::undo);
        uiModule.addEvent(EventName.REDO, this::redo);
        uiModule.addEvent(EventName.COPY, this::copy);
        uiModule.addEvent(EventName.PASTE, this::paste);
        uiModule.addEvent(EventName.DELETE, this::delete);
        uiModule.addEvent(EventName.GROUP, this::group);
        uiModule.addEvent(EventName.UNGROUP, this::ungroup);
    }

    private void undo() {
        CommandHistory.undo();
    }

    private void redo() {
        CommandHistory.redo();
    }

    private void copy() {

        CopyShapeCommand command = new CopyShapeCommand();
        command.execute();
        paintCanvas.repaint();
    }

    private void paste() {

        PasteShapeCommand command = new PasteShapeCommand();
        CommandHistory.add(command);
        command.execute();
        paintCanvas.repaint();

    }

    private void delete() {
        DeleteShapeCommand command = new DeleteShapeCommand();
        CommandHistory.add(command);
        command.execute();
        paintCanvas.repaint();
    }


    private void group() {
        GroupCommand command = new GroupCommand();
        CommandHistory.add(command);
        command.execute();
        paintCanvas.repaint();
    }

    private void ungroup() {
        UngroupCommand command = new UngroupCommand();
        CommandHistory.add(command);
        command.execute();
        paintCanvas.repaint();
    }
}
