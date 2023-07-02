package main;

import controller.JPaintController;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;
import model.shapes.ShapeFactory;
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
        IShapeFactory shapeFactory = new ShapeFactory();


        //Won't need to change this
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState);

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas, shapeFactory));
    }
}
