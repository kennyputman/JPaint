package view.render;

import model.interfaces.IShape;
import model.shapes.Ellipse;
import model.types.ShapeShadingType;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawEllipse implements DrawStrategy {
    @Override
    public void draw(IShape shape, Graphics2D graphics2d) {
        var opts = shape.getOpts();
        var ellipse = (Ellipse) shape;
        var shadingType = opts.activeShapeShadingType();

        if(shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN){
            graphics2d.setColor(opts.activePrimaryColor().color());
            graphics2d.fillOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());

            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(opts.activeSecondaryColor().color());
            graphics2d.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());

            Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
            graphics2d.setStroke(stroke);
            graphics2d.setColor(Color.BLACK);
            graphics2d.drawOval(ellipse.getX() - 5, ellipse.getY() - 5, ellipse.getWidth() + 10, ellipse.getHeight() + 10);
        } else if(shadingType == ShapeShadingType.FILLED_IN){
            graphics2d.setColor(opts.activePrimaryColor().color());
            graphics2d.fillOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
        } else if (shadingType == ShapeShadingType.OUTLINE) {

            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(opts.activeSecondaryColor().color());
            graphics2d.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());

            Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
            graphics2d.setStroke(stroke);
            graphics2d.setColor(Color.BLACK);
            graphics2d.drawOval(ellipse.getX() - 5, ellipse.getY() - 5, ellipse.getWidth() + 10, ellipse.getHeight() + 10);
        } else {
            throw new IllegalArgumentException("Invalid ShadeType");
        }


    }
}
