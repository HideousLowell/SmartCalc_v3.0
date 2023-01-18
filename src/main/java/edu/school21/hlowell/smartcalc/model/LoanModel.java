package edu.school21.hlowell.smartcalc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class LoanModel {

    private final ObservableList<Double> result = FXCollections.observableArrayList();

    public void annuityPayment(double amount, int term, double rate) {
        double monthlyRate = (rate / 100.0) / 12;
        int termsInMonths = term * 12;
        double monthlyPayment = (monthlyRate * amount) / (1 - Math.pow((1 + monthlyRate), -termsInMonths));
        double amountToReturn = monthlyPayment * term;
        double overpayment = amountToReturn - amount;
        result.clear();
        result.addAll(Arrays.asList(amountToReturn, overpayment, monthlyPayment));
    }

    public void difPayment(double amount, int term, double rate) {
        rate /= 1200;
        double monthlyPercent = amount * rate;
        double monthlyPayment = amount / term + monthlyPercent;
        double lastMonthPayment = monthlyPayment;
        double amountToReturn = monthlyPayment;
        double loanBalance = amount;
        for (int i = term; i > 1; i--) {
            loanBalance = loanBalance - lastMonthPayment + monthlyPercent;
            monthlyPercent = loanBalance * rate;
            lastMonthPayment = amount / term + loanBalance * rate;
            amountToReturn += lastMonthPayment;
        }
        double overpayment = amountToReturn - amount;
        result.clear();
        result.addAll(Arrays.asList(amountToReturn, overpayment, monthlyPayment, lastMonthPayment));
    }

    public ObservableList<Double> getResult() {
        return result;
    }
}
