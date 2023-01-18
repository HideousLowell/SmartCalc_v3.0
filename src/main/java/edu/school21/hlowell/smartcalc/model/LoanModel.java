package edu.school21.hlowell.smartcalc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class LoanModel {

    private final ObservableList<Double> result = FXCollections.observableArrayList();

    public void annuityPayment(double amount, double term, double rate) {
        term *= 12;
        double monthlyRate = (rate / 100.0) / 12;
        double monthlyPayment = (monthlyRate * amount) / (1 - Math.pow((1 + monthlyRate), -term));
        double amountToReturn = monthlyPayment * term;
        double overPayment = amountToReturn - amount;
        result.clear();
        result.addAll(Arrays.asList(amountToReturn, overPayment, monthlyPayment));
    }

    public void difPayment(double amount, double term, double rate) {

        term *= 12;
        double firstMonthPayment = 0;
        double lastMonthPayment = 0;
        double overPayment = 0;
        double totalPayment = 0;

        for (int monthCount = 0; monthCount < term; monthCount++) {
            double currentAmount = amount - (amount / term) * monthCount;
            double monthlyPayment = amount / term + currentAmount * rate / 1200;
            double localOverPayment = currentAmount * rate / 1200;
            if (monthCount == 0)
                firstMonthPayment = monthlyPayment;
            lastMonthPayment = monthlyPayment;
            overPayment += localOverPayment;
            totalPayment += monthlyPayment;
        }
        result.clear();
        result.addAll(Arrays.asList(totalPayment, overPayment, firstMonthPayment, lastMonthPayment));
    }

    public ObservableList<Double> getResult() {
        return result;
    }
}
