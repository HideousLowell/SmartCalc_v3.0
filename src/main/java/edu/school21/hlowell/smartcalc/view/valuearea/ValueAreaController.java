package edu.school21.hlowell.smartcalc.view.valuearea;

import edu.school21.hlowell.smartcalc.core.ViewHandler;
import edu.school21.hlowell.smartcalc.view.chart.ChartViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ValueAreaController {

    private ViewHandler viewHandler;

    @FXML
    private TextField maxXfield;

    @FXML
    private TextField minXfield;

    @FXML
    private TextField minYfield;

    @FXML
    private TextField maxYfield;

    @FXML
    private TextField stepField;

    private ChartViewModel chartViewModel;

    public void init(ViewHandler viewHandler, ChartViewModel chartViewModel) {
        this.chartViewModel = chartViewModel;
        this.viewHandler = viewHandler;
        minXfield.textProperty().bindBidirectional(chartViewModel.getMinX());
        maxXfield.textProperty().bindBidirectional(chartViewModel.getMaxX());
        minYfield.textProperty().bindBidirectional(chartViewModel.getMinY());
        maxYfield.textProperty().bindBidirectional(chartViewModel.getMaxY());
        stepField.textProperty().bindBidirectional(chartViewModel.getStepField());
    }

    @FXML
    public void onOkButtonClick() {
        viewHandler.openCalcView();
        try {
            chartViewModel.update();
            viewHandler.openChartView();
        } catch (RuntimeException e) {
            viewHandler.popError("Unable to convert value");
        }
    }

    @FXML
    public void onCancelButtonClick() {
        viewHandler.openCalcView();
    }
}

