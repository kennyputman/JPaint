package view.render;

import model.interfaces.IShape;
import model.shapes.Rectangle;
import model.types.ShapeShadingType;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawRectangle implements DrawStrategy {

    @Override
    public void draw(IShape shape, Graphics2D graphics2d) {
        var opts = shape.getOpts();
        var rect = (Rectangle) shape;
        var shadingType = opts.activeShapeShadingType();

        if(shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN){
            graphics2d.setColor(opts.activePrimaryColor().color());
            graphics2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(opts.activeSecondaryColor().color());
            graphics2d.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

            Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
            graphics2d.setStroke(stroke);
            graphics2d.setColor(Color.BLACK);
            graphics2d.drawRect(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
        } else if(shadingType == ShapeShadingType.FILLED_IN){
            graphics2d.setColor(opts.activePrimaryColor().color());
            graphics2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        } else if (shadingType == ShapeShadingType.OUTLINE) {

            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(opts.activeSecondaryColor().color());
            graphics2d.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

            Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
            graphics2d.setStroke(stroke);
            graphics2d.setColor(Color.BLACK);
            graphics2d.drawRect(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
        } else {
            throw new IllegalArgumentException("Invalid ShadeType");
        }


    }
}
