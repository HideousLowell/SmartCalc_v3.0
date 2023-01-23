package edu.school21.hlowell.smartcalc.view.valuearea;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ValueAreaViewModel {

    private final StringProperty minX = new SimpleStringProperty("-10");
    private final StringProperty maxX = new SimpleStringProperty("10");
    private final StringProperty minY = new SimpleStringProperty("-10");
    private final StringProperty maxY = new SimpleStringProperty("10");
    private final StringProperty stepField = new SimpleStringProperty("0.1");
    private final ValueArea valueArea = new ValueArea();

    private final CalcModel calcModel;

    public void update() throws RuntimeException {
        valueArea.setMinX(Double.parseDouble(minX.get()));
        valueArea.setMaxX(Double.parseDouble(maxX.get()));
        valueArea.setMinY(Double.parseDouble(minY.get()));
        valueArea.setMaxY(Double.parseDouble(maxY.get()));
        valueArea.setStep(Double.parseDouble(stepField.get()));
        calcModel.updateChartsXY(valueArea);
    }
}
