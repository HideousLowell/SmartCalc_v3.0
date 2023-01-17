package edu.school21.hlowell.smartcalc.view.calc;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import edu.school21.hlowell.smartcalc.model.history.HistoryModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CalcViewModel {

    private final CalcModel calcModel;
    private final StringProperty mainField = new SimpleStringProperty("");
    private final HistoryModel historyModel;
    private DoubleProperty xValue;

    public CalcViewModel(CalcModel cm, HistoryModel hm) {
        calcModel = cm;
        historyModel = hm;
        mainField.bindBidirectional(calcModel.resultProperty());
    }

    public StringProperty mainFieldProperty() {
        return mainField;
    }

    public void calculate() {
        if (mainField.get().indexOf('x') != -1) {
            if (xValue == null)
               throw new IllegalArgumentException("Enter the x value");
        }
        historyModel.save(mainField.get());
        calcModel.update(mainField.get());
        xValue = null;
    }

    public void setXValue(String x) throws NumberFormatException {
        xValue = new SimpleDoubleProperty(Double.parseDouble(x));
        calcModel.setXValue(xValue.getValue());
    }

    public void saveMathProblem() {
        calcModel.setMathProblem(mainField.get());
    }

    public void setMathProblem(String string) {
        mainField.set(string);
        calcModel.setMathProblem(mainField.get());
    }

}
