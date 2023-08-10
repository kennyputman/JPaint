package model.commands;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeStore;
import model.shapes.Ellipse;
import model.shapes.Rectangle;
import model.shapes.Triangle;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PasteShapeCommand implements ICommand, IUndoable {

    private final ShapeStore shapeStore;
    private final List<IShape> pastedShapes;

    public PasteShapeCommand(ShapeStore shapeStore) {
        this.shapeStore = shapeStore;
        pastedShapes = new ArrayList<>();

        try {
            initializePastedShapes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void initializePastedShapes() throws InstanceNotFoundException {
        for (IShape shape : shapeStore.getClipboard()) {
            IShape copy;
            if (shape instanceof Triangle triangle) {
                copy = triangle.copy();
            } else if (shape instanceof Rectangle rect) {
                copy = rect.copy();
            } else if (shape instanceof Ellipse ellipse) {
                copy = ellipse.copy();
            } else {
                throw new InstanceNotFoundException("Shape not found");
            }


            // TODO this only moves based on original subsequent copies overlap
            copy.move(15, 15);
            pastedShapes.add(copy);
        }
    }

    @Override
    public void execute() {
        for (IShape observer : pastedShapes) {
            shapeStore.addShape(observer);
        }

    }

    @Override
    public void redo() {
        for (IShape observer : pastedShapes) {
            shapeStore.addShape(observer);
        }
    }

    @Override
    public void undo() {
        for (IShape observer : pastedShapes) {
            shapeStore.removeShape(observer);
        }
    }
}