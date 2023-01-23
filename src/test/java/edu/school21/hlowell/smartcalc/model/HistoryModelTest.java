package edu.school21.hlowell.smartcalc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class HistoryModelTest {

    HistoryModel historyModel = new HistoryModel("src/test/resources/history.txt");

    @Test
    void save() {
        String mathProblem = "2+2";
        historyModel.clear();
        historyModel.save(mathProblem);
        Optional<String> result = historyModel.getHistoryData().stream().findFirst();
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(mathProblem, result.get());
    }

}