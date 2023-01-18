package edu.school21.hlowell.smartcalc.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanModelTest {

    private final LoanModel loanModel = new LoanModel();

    @Test
    void annuityPaymentTest() {
        double amount = 150000;
        double term = 7;
        double rate = 17;
        loanModel.annuityPayment(amount, term, rate);
        List<Double> result = new ArrayList<>(loanModel.getResult());
        List<Double> expected = Arrays.asList(257491.14, 107491.14, 3065.37);
        IntStream.range(0, result.size()).forEach(i -> assertEquals(expected.get(i), result.get(i), 0.01));
    }

    @Test
    void difPaymentTest() {
        double amount = 150000;
        double term = 7;
        double rate = 17;
        loanModel.difPayment(amount, term, rate);
        List<Double> result = new ArrayList<>(loanModel.getResult());
        List<Double> expected = Arrays.asList(240312.5, 90312.5, 3910.71, 1811.01);
        IntStream.range(0, result.size()).forEach(i -> assertEquals(expected.get(i), result.get(i), 0.01));
    }
}