package view.render;

import model.AppStateOpts;
import model.interfaces.IShape;
import model.shapes.Group;
import model.types.ShapeSelection;
import model.types.ShapeType;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawGroup implements DrawStrategy {

    private final Graphics2D graphics2d;
    private final Group shape;
    private DrawStrategy strategy;

    public DrawGroup(IShape shape, Graphics2D graphics2d) {
        this.graphics2d = graphics2d;
        this.shape = (Group) shape;
    }

    @Override
    public void draw() {
        var opts = shape.getOpts();

        if(shape.getShapeSelection() == ShapeSelection.SELECTED){
            setSelectionOutline(shape);
        }

        for (IShape shape : shape.getChildren()) {
            var type = shape.getOpts().activeShape();
            if (type == ShapeType.RECTANGLE) {
                strategy = new DrawRectangle(shape, graphics2d);
                strategy.draw();
            } else if (type == ShapeType.TRIANGLE) {
                strategy = new DrawTriangle(shape, graphics2d);
                strategy.draw();
            } else if (type == ShapeType.ELLIPSE) {
                strategy = new DrawEllipse(shape, graphics2d);
                strategy.draw();
            } else if (type == ShapeType.GROUP) {
                strategy = new DrawGroup(shape, graphics2d);
                strategy.draw();
            }else {
                throw new IllegalArgumentException("Invalid Shape Type");
            }
        }
    }

    private void setSelectionOutline(IShape rect){
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
    }
}
