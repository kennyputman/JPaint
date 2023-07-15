package view.render;

import model.interfaces.IShape;
import model.shapes.Triangle;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawTriangle implements DrawStrategy {
    @Override
    public void draw(IShape shape, Graphics2D graphics2d) {
        var opts = shape.getOpts();
        var tri = (Triangle) shape;

        graphics2d.setColor(opts.activePrimaryColor().color());
        graphics2d.fillPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());

        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(opts.activeSecondaryColor().color());
        graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());    }
}
