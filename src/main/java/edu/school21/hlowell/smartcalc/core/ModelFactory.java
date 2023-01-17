package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import edu.school21.hlowell.smartcalc.model.history.HistoryModel;

public class ModelFactory {

    private CalcModel calcModel;
    private HistoryModel historyModel;

    public CalcModel getCalcModel() {
        if (calcModel == null)
            calcModel = new CalcModel();
        return calcModel;
    }

    public HistoryModel getHistoryModel() {
        if (historyModel == null)
            historyModel = new HistoryModel();
        return historyModel;
    }

}
