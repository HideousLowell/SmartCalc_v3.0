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
    private final StringProperty minX = new SimpleStringProperty("-10");
    private final StringProperty maxX = new SimpleStringProperty("10");
    private final StringProperty minY = new SimpleStringProperty("-10");
    private final StringProperty maxY = new SimpleStringProperty("10");
    private final StringProperty stepField = new SimpleStringProperty("0.1");
    private final StringProperty mathProblem = new SimpleStringProperty();

    private final ValueArea valueArea = new ValueArea();
    private final ObservableList<XYChart.Data<Number, Number>>
            chartsXY = FXCollections.observableArrayList();

    public ChartViewModel(CalcModel calcModel) {
        this.calcModel = calcModel;
        Bindings.bindContent(chartsXY, calcModel.getChartsXY());
        mathProblem.bind(calcModel.getMathProblem());
    }

    public void update() throws RuntimeException {
        valueArea.setMinX(Double.parseDouble(minX.get()));
        valueArea.setMaxX(Double.parseDouble(maxX.get()));
        valueArea.setMinY(Double.parseDouble(minY.get()));
        valueArea.setMaxY(Double.parseDouble(maxY.get()));
        valueArea.setStep(Double.parseDouble(stepField.get()));
        calcModel.updateChartsXY(valueArea);
    }
}
