package edu.school21.hlowell.smartcalc.view.chart;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import edu.school21.hlowell.smartcalc.view.valuearea.ValueArea;
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
    private final StringProperty mathProblem = new SimpleStringProperty();

    private final ObservableList<XYChart.Data<Number, Number>>
            chartsXY = FXCollections.observableArrayList();

    public ChartViewModel(CalcModel calcModel) {
        this.calcModel = calcModel;
        Bindings.bindContent(chartsXY, calcModel.getChartsXY());
        mathProblem.bind(calcModel.getMathProblem());
    }
}
