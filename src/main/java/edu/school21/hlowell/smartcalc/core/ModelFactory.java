package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import edu.school21.hlowell.smartcalc.model.LoanModel;
import edu.school21.hlowell.smartcalc.model.history.HistoryModel;

public class ModelFactory {

    private CalcModel calcModel;
    private HistoryModel historyModel;
    private LoanModel loanModel;

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

    public LoanModel getLoanModel() {
        if (loanModel == null)
            loanModel = new LoanModel();
        return loanModel;
    }

}
