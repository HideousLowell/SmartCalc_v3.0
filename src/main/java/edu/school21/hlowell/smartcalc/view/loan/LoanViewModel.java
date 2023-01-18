package edu.school21.hlowell.smartcalc.view.loan;

import edu.school21.hlowell.smartcalc.model.LoanModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoanViewModel {

    private final LoanModel loanModel;
    public final String ANNUITY = "Annuity";
    public final String DIFFERENTIAL = "Differential";

    private StringProperty amount = new SimpleStringProperty("");
    private StringProperty rate = new SimpleStringProperty("");
    private StringProperty term = new SimpleStringProperty("");
    private StringProperty amountToReturn = new SimpleStringProperty("");
    private StringProperty overpayment = new SimpleStringProperty("");
    private StringProperty monthlyPayment = new SimpleStringProperty("");

    private final ObservableList<Double> result = FXCollections.observableArrayList();

    public LoanViewModel(LoanModel loanModel) {
        this.loanModel = loanModel;
        Bindings.bindContent(result, loanModel.getResult());
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public StringProperty rateProperty() {
        return rate;
    }

    public StringProperty termProperty() {
        return term;
    }

    public void calculate(String payment) throws NumberFormatException {
        double amountValue = Double.parseDouble(amount.getValue());
        int termValue = Integer.parseInt(term.getValue());
        if (termValue < 1) throw new RuntimeException("Minimal term is 1");
        double rateValue = Integer.parseInt(rate.getValue());
        switch (payment) {
            case ANNUITY:
                loanModel.annuityPayment(amountValue, termValue, rateValue);
                monthlyPayment.set(String.format("%.2f", result.get(2)));
                break;
            case DIFFERENTIAL:
                loanModel.difPayment(amountValue,termValue, rateValue);
                monthlyPayment.set(String.format("%.2f ... %.2f", result.get(2), result.get(3)));
                break;
        }
        amountToReturn.set(String.format("%.2f", result.get(0)));
        overpayment.set(String.format("%.2f", result.get(1)));
    }

    public StringProperty amountToReturnProperty() {
        return amountToReturn;
    }


    public StringProperty monthlyPaymentProperty() {
        return monthlyPayment;
    }

    public StringProperty overpaymentProperty() {
        return overpayment;
    }

}
