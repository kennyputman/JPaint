package view.render;

import model.AppStateOpts;
import model.interfaces.IShape;
import model.shapes.Ellipse;
import model.shapes.Rectangle;
import model.types.ShapeSelection;
import model.types.ShapeShadingType;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawRectangle implements DrawStrategy {

    private final Graphics2D graphics2d;
    private final IShape shape;

    public DrawRectangle(IShape shape, Graphics2D graphics2d) {
        this.graphics2d = graphics2d;
        this.shape = shape;
    }
    @Override
    public void draw() {
        var opts = shape.getOpts();
        var rect = (Rectangle) shape;
        var shadingType = opts.activeShapeShadingType();

        if(shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN){
            setFill(rect, opts);
           setOutline(rect, opts);
        } else if(shadingType == ShapeShadingType.FILLED_IN){
            setFill(rect, opts);
        } else if (shadingType == ShapeShadingType.OUTLINE) {
            setOutlineOnly(rect, opts);
        } else {
            throw new IllegalArgumentException("Invalid ShadeType");
        }

        if(rect.getShapeSelection() == ShapeSelection.SELECTED){
            setSelectionOutline(rect);
        }
    }

    private void setFill(Rectangle rect, AppStateOpts opts){
        graphics2d.setColor(opts.activePrimaryColor().color());
        graphics2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    private void setOutline(Rectangle rect, AppStateOpts opts){
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(opts.activeSecondaryColor().color());
        graphics2d.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    private void setOutlineOnly(Rectangle rect, AppStateOpts opts){
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(opts.activePrimaryColor().color());
        graphics2d.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

    }

    private void setSelectionOutline(Rectangle rect){
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
    }
}
