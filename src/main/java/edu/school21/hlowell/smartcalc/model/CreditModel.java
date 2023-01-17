package edu.school21.hlowell.smartcalc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class CreditModel {

    private final ObservableList<Double> annuity = FXCollections.observableArrayList();
    private final ObservableList<Double> differentiated = FXCollections.observableArrayList();

    public static List<Double> annuityPayment(double amount, int term, double rate) {
        rate /= (100 * 12);
        double monthlyPayment = amount * (rate / (1 - pow(1 + rate, -term)));
        double amountToReturn = monthlyPayment * term;
        double overpayment = amountToReturn - amount;
        return Arrays.asList(amountToReturn, overpayment, monthlyPayment);
    }

    public static List<Double> difPayment(double amount, int term, double rate) {
        rate /= (100 * 12);
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
        return Arrays.asList(amountToReturn, overpayment, monthlyPayment, lastMonthPayment);
    }
}
