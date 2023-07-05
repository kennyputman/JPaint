package model.interfaces;

import model.types.ShapeColor;
import model.types.ShapeShadingType;
import model.types.ShapeType;
import model.types.MouseMode;
import view.interfaces.IDialogChoice;

public interface IDialogProvider {

    IDialogChoice<ShapeType> getChooseShapeDialog();

    IDialogChoice<ShapeColor> getChoosePrimaryColorDialog();

    IDialogChoice<ShapeColor> getChooseSecondaryColorDialog();

    IDialogChoice<ShapeShadingType> getChooseShadingTypeDialog();

    IDialogChoice<MouseMode> getChooseStartAndEndPointModeDialog();
}
