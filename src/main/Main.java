package main;

import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import view.handlers.ClickHandler;

public class Main {
    public static void main(String[] args){

        //Will need to modify this
        PaintCanvas paintCanvas = new PaintCanvas();


        //Won't need to change this
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState);

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas));
    }
}
