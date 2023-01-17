package edu.school21.hlowell.smartcalc.view.calc;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class CalcViewModel {

    private final CalcModel calcModel;
    private final StringProperty mathProblem = new SimpleStringProperty("");
    private DoubleProperty xValue;

    public StringProperty mathProblemProperty() {
        return mathProblem;
    }

    public void calculate() {
        if (mathProblem.get().indexOf('x') != -1) {
            if (xValue == null)
               throw new IllegalArgumentException("Enter the x value");
        }
        calcModel.calculate(mathProblem.get());
        mathProblem.setValue(calcModel.getResult());
        xValue = null;
    }

    public void setXValue(String x) throws NumberFormatException {
        xValue = new SimpleDoubleProperty(Double.parseDouble(x));
        calcModel.setXValue(xValue.getValue());
    }

    public void saveMathProblem() {
        calcModel.setMathProblem(mathProblem.get());
    }

    public void setZero() {
        mathProblem.set("0");
    }

    public void deleteLastChar() {
        String chopped = StringUtils.chop(mathProblem.get());
        mathProblem.set(chopped.equals("") ? "0" : chopped);
    }

    public void addText(String s) {
        if (mathProblem.get().equals("0")) mathProblem.set("");
        mathProblem.set(mathProblem.get() + s);
    }

}
