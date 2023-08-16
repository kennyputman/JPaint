package model.interfaces;

import model.types.MouseMode;
import model.types.ShapeColor;
import model.types.ShapeShadingType;
import model.types.ShapeType;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    MouseMode getActiveMouseMode();
}
