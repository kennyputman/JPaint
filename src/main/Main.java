package main;

import controller.JPaintController;
import model.interfaces.IShapeFactory;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.shapes.ShapeFactory;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import view.handlers.ClickHandler;

public class Main {
    public static void main(String[] args){

        /* TODO

            - Draw Rectangles, Ellipses, and Triangles
            - Draw shapes with various colors
            - Draw shapes with various shading types
                - Outline Only – Only shape outline will be drawn. Use Primary
                Color to draw this.
                - Filled-In – Only the inside of the shape will be drawn – there will
                be no visible outline. Use Primary Color to draw this.
                - Outline and Filled-In – Both the inside and the outline will be
                drawn. Use Primary Color for the inside and Secondary Color for
                the outline.
            - Select a shape.
                - In Select mouse mode, select any shapes that are touched by the
                invisible bounding box created by clicking and dragging to select.
                You can use (and share on D2L) a Collision detection algorithm
                that you find. The selection can be imprecise; when selecting,
                assume any shape (e.g. ellipse or triangle) has an invisible
                bounding box that surrounds the shape. You can use that
                bounding box for your collision detection calculation (this is much
                easier for you!).
                - If you click a single point on a shape while in Select mode, that
                shape should be selected. If you click a single point on the canvas
                or select an empty area, the selected shapes should be
                deselected This is the default behavior for collision detection and
                shouldn’t require any modification – this is easier for you!
                - You should be able to click and drag into any part of a shape to
                select it – it does not need to be completely surrounded
                - At this point, nothing visible has to happen.
                - Move a shape
                - In Move Mouse Mode, clicking and dragging will offset any
                Selected shapes by the amount your mouse moves.
                - Moving should not deselect any shapes
                - Undo/Redo Move
                - Have at least two design patterns implemented
                Grading Notes:
                - The ability to move a shape is dependent on the ability to select a
                shape.
                - Shape selection must include the ability to click and drag to select
                multiple shapes at once. You should not be able to click on shapes one
                at a time to select
                - You can move by clicking and dragging anywhere on the screen, you
                don’t need to click and drag on the highlighted shape(s)
         */

        /* NOTE

            State Pattern to manage mouse click between create shape and select shape
            Observer pattern to manage updating multiple shapes at once?
            Strategy pattern to dictate shape drawing to another thing
         */

        /* NOTE
            LECTURE NOTES

            - Select functionality
                - don't need to create outline for check in 2

         */

        ShapeList shapeList = new ShapeList();
        //Will need to modify this
        PaintCanvas paintCanvas = new PaintCanvas(shapeList);

        //Won't need to change this
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        new JPaintController(uiModule, appState);

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas, shapeList, appState));
    }
}
