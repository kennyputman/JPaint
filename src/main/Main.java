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
            FIX: Copy and paste for Groups
            FIX: manage state on group children when deleting and such

            TODO: Singleton for shapeStore
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