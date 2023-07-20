package view.render;

import model.AppStateOpts;
import model.interfaces.IShape;
import model.shapes.Triangle;
import model.types.ShapeShadingType;
import view.interfaces.DrawStrategy;

import java.awt.*;

public class DrawTriangle implements DrawStrategy {

    private final Graphics2D graphics2d;
    private final IShape shape;

    public DrawTriangle(IShape shape, Graphics2D graphics2d) {
        this.graphics2d = graphics2d;
        this.shape = shape;
    }

    @Override
    public void draw() {
        var opts = shape.getOpts();
        var tri = (Triangle) shape;

        var shadingType = opts.activeShapeShadingType();

        if (shadingType == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            setFill(tri, opts);
            setOutline(tri, opts);
        } else if (shadingType == ShapeShadingType.FILLED_IN) {
            setFill(tri, opts);
        } else if (shadingType == ShapeShadingType.OUTLINE) {
            setOutlineOnly(tri, opts);
        } else {
            throw new IllegalArgumentException("Invalid ShadeType");
        }
    }

    private void setFill(Triangle tri, AppStateOpts opts){
        graphics2d.setColor(opts.activePrimaryColor().color());
        graphics2d.fillPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());
    }

    private void setOutline(Triangle tri, AppStateOpts opts){
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(opts.activeSecondaryColor().color());
        graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());

//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());
    }

    private void setOutlineOnly(Triangle tri, AppStateOpts opts){
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(opts.activePrimaryColor().color());
        graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());

//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawPolygon(tri.getXCoord(), tri.getYCoord(), tri.getN());
    }
}
