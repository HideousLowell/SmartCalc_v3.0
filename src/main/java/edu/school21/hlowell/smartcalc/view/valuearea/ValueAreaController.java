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

    private ValueAreaViewModel viewModel;

    public void init(ViewHandler viewHandler, ValueAreaViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewHandler = viewHandler;
        minXfield.textProperty().bindBidirectional(viewModel.getMinX());
        maxXfield.textProperty().bindBidirectional(viewModel.getMaxX());
        minYfield.textProperty().bindBidirectional(viewModel.getMinY());
        maxYfield.textProperty().bindBidirectional(viewModel.getMaxY());
        stepField.textProperty().bindBidirectional(viewModel.getStepField());
    }

    @FXML
    public void onOkButtonClick() {
        viewHandler.openCalcView();
        try {
            viewModel.update();
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

