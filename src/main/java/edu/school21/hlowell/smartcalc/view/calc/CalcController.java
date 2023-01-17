package edu.school21.hlowell.smartcalc.view.calc;

import edu.school21.hlowell.smartcalc.core.ViewHandler;
import edu.school21.hlowell.smartcalc.view.dialogs.ChooseXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CalcController {

    @FXML
    private TextField mainField;

    private ViewHandler viewHandler;
    private CalcViewModel calcViewModel;

    public void init(ViewHandler viewHandler, CalcViewModel calcViewModel) {
        this.viewHandler = viewHandler;
        this.calcViewModel = calcViewModel;
        mainField.textProperty().bindBidirectional(calcViewModel.mathProblemProperty());
    }

    @FXML
    void keyPressed(KeyEvent event) {
        if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER)
            calculate();
    }

    @FXML
    private void onSimpleCalcButtonsHandler(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        calcViewModel.addText(value);
    }

    @FXML
    private void onFuncButtonsHandler(ActionEvent event) {
        onSimpleCalcButtonsHandler(event);
        calcViewModel.addText("(");
    }

    @FXML
    private void onCButtonHandler() {
        calcViewModel.deleteLastChar();
    }

    @FXML
    private void onACButtonHandler() {
        calcViewModel.setZero();
    }

    @FXML
    private void calculate() {
        try {
            calcViewModel.calculate();
        } catch (IllegalArgumentException e) {
            chooseX();
            calculate();
        }
    }

    @FXML
    private void onChartButtonClick() {
        calcViewModel.saveMathProblem();
        viewHandler.openValueAreaDialog();
    }

    public void chooseX() {
        try {
            ChooseXDialog chooseXDialog = new ChooseXDialog();
            chooseXDialog.getXFromUser().ifPresent(calcViewModel::setXValue);
        } catch (NumberFormatException e) {
            viewHandler.popError(e.getMessage() + " is unable to parse");
        }
    }

}
