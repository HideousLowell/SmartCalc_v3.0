package edu.school21.hlowell.smartcalc.model;

import edu.school21.hlowell.smartcalc.view.valuearea.ValueArea;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.sin;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcModelTest {

    CalcModel calcModel = new CalcModel();

    @ParameterizedTest(name = "{0} = {1}")
    @CsvFileSource(resources = {"/calc_equals.csv"})
    public void testCalcAssertEquals(String mathProblem, String expectedAnswer) {
        calcModel.calculate(mathProblem);
        String result = calcModel.getResult().get();
        assertEquals(expectedAnswer, result);
    }

    @ParameterizedTest(name = "{0} = {2} when x = {1}")
    @CsvFileSource(resources = {"/calc_equals_with_x.csv"})
    public void testCalcAssertEqualsWithX(String mathProblem, double x, String expectedAnswer) {
        calcModel.setXValue(x);
        calcModel.calculate(mathProblem);
        String result = calcModel.getResult().get();
        assertEquals(expectedAnswer, result);
    }

    @Test
    void testChartsCalc() {
        ValueArea valueArea = new ValueArea(-100, 100, -100, 100, 1);
        calcModel.setMathProblem("sin(x)");
        calcModel.updateChartsXY(valueArea);
        List<Double> list = calcModel.getChartsXY()
                .stream().map(XYChart.Data::getYValue)
                .map(Number::doubleValue)
                .collect(Collectors.toList());
        int i =0;
        for (double x = valueArea.getMinX();
             x < valueArea.getMaxX() && i < list.size();
             i++, x += valueArea.getStep()) {
            Assertions.assertEquals(sin(x), list.get(i), 0.001);
        }
    }
}