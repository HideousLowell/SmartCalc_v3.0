package edu.school21.hlowell.smartcalc.model;

import edu.school21.hlowell.smartcalc.model.jna.FunctionsNative;
import edu.school21.hlowell.smartcalc.pojo.ValueArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

@Setter
@Getter
public class CalcModel {

    private Double xValue;
    private String mathProblem;
    private String result;
    private final FunctionsNative model;
    private final ObservableList<XYChart.Data<Number, Number>> chartsXY = FXCollections.observableArrayList();
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public CalcModel() {
        try {
            model = new FunctionsNative("libs/libfunctions.so");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addListener(PropertyChangeListener listener) {
            changeSupport.addPropertyChangeListener(listener);
    }

    public void calculate(String mathProblem) {
        String replaced = mathProblem.replace("x", String.valueOf(xValue));
        result = model.getResultString(replaced);
    }

    public void update(ValueArea valueArea) {
        chartsXY.clear();
        for (double x = valueArea.getMinX(); x < valueArea.getMaxX(); x += valueArea.getStep()) {
            try {
                String replaced = mathProblem.replace("x", String.valueOf(x));
                String localResult = model.getResultString(replaced);
                chartsXY.add(new XYChart.Data<>(x, Double.parseDouble(localResult)));
            } catch (NumberFormatException ignore) {
            }
        }
        changeSupport.firePropertyChange("ChartsData", null, chartsXY);
    }

}
