package edu.school21.hlowell.smartcalc.view.calc;

import edu.school21.hlowell.smartcalc.core.ViewHandler;
import edu.school21.hlowell.smartcalc.view.dialogs.ChooseXDialog;
import edu.school21.hlowell.smartcalc.view.history.HistoryViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
public class CalcController {

    @FXML
    private TextField mainField;

    private ViewHandler viewHandler;
    private CalcViewModel calcViewModel;
    private HistoryViewModel historyViewModel;

    public void init(ViewHandler viewHandler, CalcViewModel cvm, HistoryViewModel hvm) {
        this.viewHandler = viewHandler;
        this.calcViewModel = cvm;
        this.historyViewModel = hvm;
        formatMainField();
        mainField.textProperty().bindBidirectional(calcViewModel.mainFieldProperty());
    }

    private void formatMainField() {
        mainField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty())
                mainField.setText("0");
            else if (newValue.length() == 2 && newValue.startsWith("0"))
                mainField.setText(String.valueOf(newValue.charAt(1)));
        });
    }

    @FXML
    void keyPressed(KeyEvent event) {
        if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER)
            calculate();
    }

    @FXML
    private void onSimpleCalcButtonsHandler(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        addText(value);
    }

    @FXML
    private void onFuncButtonsHandler(ActionEvent event) {
        onSimpleCalcButtonsHandler(event);
        addText("(");
    }

    public void addText(String s) {
        if (mainField.textProperty().get().equals("0")) mainField.textProperty().set(s);
        else mainField.textProperty().set(mainField.textProperty().get() + s);
    }

    @FXML
    private void onCButtonHandler() {
        String chopped = StringUtils.chop(mainField.textProperty().get());
        mainField.textProperty().set(chopped);
    }

    @FXML
    private void onACButtonHandler() {
        mainField.textProperty().set("0");
        mainField.requestFocus();
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
    private void drawChart() {
        calcViewModel.saveMathProblem();
        viewHandler.openValueAreaDialog();
    }

    @FXML
    private void showHistory() {
        viewHandler.openHistoryView();
    }

    @FXML
    private void clearHistory() {
        historyViewModel.deleteHistory();
    }

    @FXML
    private void showInfo() {
        viewHandler.openInfoView();
    }

    @FXML
    private void openLoanCalc() {
        viewHandler.openLoanView();
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
