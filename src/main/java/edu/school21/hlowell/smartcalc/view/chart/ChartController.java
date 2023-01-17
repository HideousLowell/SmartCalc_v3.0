package edu.school21.hlowell.smartcalc.view.chart;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartController {

    @FXML
    private LineChart<Number, Number> funcChart;

    public void init(ChartViewModel chartViewModel) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>(chartViewModel.getChartsXY());
        series.setName(chartViewModel.getMathProblem().get());
        funcChart.getData().add(series);
    }

}
