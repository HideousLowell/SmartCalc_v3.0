package edu.school21.hlowell.smartcalc.view.chart;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import edu.school21.hlowell.smartcalc.pojo.ValueArea;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;

@Getter
public class ChartViewModel {

    private final CalcModel calcModel;
    private final StringProperty minX = new SimpleStringProperty();
    private final StringProperty maxX = new SimpleStringProperty();
    private final StringProperty stepField = new SimpleStringProperty();

    private final ValueArea valueArea = new ValueArea();
    private final ObservableList<XYChart.Data<Number, Number>>
            chartsXY = FXCollections.observableArrayList();

    public ChartViewModel(CalcModel calcModel) {
        this.calcModel = calcModel;
        Bindings.bindContent(chartsXY, calcModel.getChartsXY());
    }

    public void update() throws RuntimeException {
        valueArea.setMinX(Double.parseDouble(minX.get()));
        valueArea.setMaxX(Double.parseDouble(maxX.get()));
        valueArea.setStep(Double.parseDouble(stepField.get()));
        calcModel.update(valueArea);
    }
}
