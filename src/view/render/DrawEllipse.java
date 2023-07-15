package view.render;

import model.interfaces.IShape;
import model.shapes.Ellipse;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawEllipse implements DrawStrategy {
    @Override
    public void draw(IShape shape, Graphics2D graphics2d) {
        var opts = shape.getOpts();
        var rect = (Ellipse) shape;

        graphics2d.setColor(opts.activePrimaryColor().color());
        graphics2d.fillOval(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(opts.activeSecondaryColor().color());
        graphics2d.drawOval(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawOval(rect.getX() - 5, rect.getY() - 5, rect.getWidth() + 10, rect.getHeight() + 10);
    }
}
