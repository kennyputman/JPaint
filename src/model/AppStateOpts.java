package model;

import model.types.MouseMode;
import model.types.ShapeColor;
import model.types.ShapeShadingType;
import model.types.ShapeType;

public record AppStateOpts(
        ShapeType activeShape,
        ShapeColor activePrimaryColor,
        ShapeColor activeSecondaryColor,
        ShapeShadingType activeShapeShadingType,
        MouseMode activeMouseMode
) { }
