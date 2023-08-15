package model.dialogs;

import model.types.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChooseShapeDialog implements IDialogChoice<ShapeType> {
    private final IApplicationState applicationState;

    public ChooseShapeDialog(IApplicationState applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Shape";
    }

    @Override
    public String getDialogText() {
        return "Select a shape from the menu below:";
    }

    @Override
    public ShapeType[] getDialogOptions() {
        return Stream.of(ShapeType.values())
                .filter(type -> type != ShapeType.GROUP)
                .toArray(ShapeType[]::new);
    }

    @Override
    public ShapeType getCurrentSelection() {
        return applicationState.getActiveShapeType();
    }
}
