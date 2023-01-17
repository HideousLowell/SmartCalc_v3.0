package edu.school21.hlowell.smartcalc.model;

import edu.school21.hlowell.smartcalc.model.jna.FunctionsNative;
import edu.school21.hlowell.smartcalc.pojo.ValueArea;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class CalcModel {

    private Double xValue;
    private StringProperty mathProblem = new SimpleStringProperty("");
    private StringProperty result = new SimpleStringProperty("");
    private FunctionsNative cLangLibrary;
    private final ObservableList<XYChart.Data<Number, Number>> chartsXY = FXCollections.observableArrayList();

    public CalcModel() {
        try {
            cLangLibrary = new FunctionsNative("libs/libfunctions.so");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String mathProblem) {
        this.mathProblem.set(mathProblem);
        String replaced = mathProblem.replace("x", String.valueOf(xValue));
        result.set(cLangLibrary.getResultString(replaced));
    }

    public void updateChartsXY(ValueArea valueArea) {
        chartsXY.clear();
        for (double x = valueArea.getMinX(); x < valueArea.getMaxX(); x += valueArea.getStep()) {
            try {
                String replaced = mathProblem.get().replace("x", String.valueOf(x));
                String localResult = cLangLibrary.getResultString(replaced);
                double result = Double.parseDouble(localResult);
                if (result > valueArea.getMaxY() || result < valueArea.getMinY())
                    continue;
                chartsXY.add(new XYChart.Data<>(x, Double.parseDouble(localResult)));
            } catch (NumberFormatException ignore) {
            }
        }
    }

    public void setMathProblem(String mathProblem) {
        this.mathProblem.set(mathProblem);
    }

    public StringProperty resultProperty() {
        return result;
    }
}
