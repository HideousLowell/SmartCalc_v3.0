package edu.school21.hlowell.smartcalc.view.loan;

import edu.school21.hlowell.smartcalc.core.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoanController {

    private ViewHandler viewHandler;
    private LoanViewModel loanViewModel;

    @FXML
    private Label amountToReturnField;

    @FXML
    private Label monthlyPaymentField;

    @FXML
    private Label overpaymentField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField rateField;

    @FXML
    private TextField termField;

    @FXML
    private ChoiceBox<String> paymentType;

    @FXML
    void calculate() {
        try {
            loanViewModel.calculate(paymentType.getValue());
        } catch (RuntimeException e) {
            viewHandler.popError(e.getMessage());
        }
    }

    public void init(ViewHandler viewHandler, LoanViewModel loanViewModel) {
        this.viewHandler = viewHandler;
        this.loanViewModel = loanViewModel;
        amountField.textProperty().bindBidirectional(loanViewModel.amountProperty());
        rateField.textProperty().bindBidirectional(loanViewModel.rateProperty());
        termField.textProperty().bindBidirectional(loanViewModel.termProperty());
        amountToReturnField.textProperty().bind(loanViewModel.amountToReturnProperty());
        monthlyPaymentField.textProperty().bind(loanViewModel.monthlyPaymentProperty());
        overpaymentField.textProperty().bind(loanViewModel.overpaymentProperty());
        paymentType.getItems().addAll(loanViewModel.ANNUITY, loanViewModel.DIFFERENTIAL);
        paymentType.setValue(loanViewModel.ANNUITY);
    }

    @FXML
    public void backToMain() {
        viewHandler.openCalcView();
    }

}
