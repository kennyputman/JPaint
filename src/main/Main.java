package main;

import controller.JPaintController;
import model.persistence.ApplicationState;
import model.persistence.ShapeStore;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.handlers.ClickHandler;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {

        PaintCanvas paintCanvas = new PaintCanvas();

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState, paintCanvas);

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas, appState));
    }
}