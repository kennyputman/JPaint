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

        /*
            TODO: Composite Pattern for grouping
            TODO: Singleton for shapeStore
            TODO: update vars for code readability
         */

        ShapeStore shapeStore = new ShapeStore();
        PaintCanvas paintCanvas = new PaintCanvas(shapeStore);

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState, shapeStore, paintCanvas);

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas, shapeStore, appState));
    }
}