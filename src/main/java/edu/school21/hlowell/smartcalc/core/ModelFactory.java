package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.model.CalcModel;
import edu.school21.hlowell.smartcalc.model.LoanModel;
import edu.school21.hlowell.smartcalc.model.HistoryModel;

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
            historyModel = new HistoryModel("src/main/resources/history.txt");
        return historyModel;
    }

    public LoanModel getLoanModel() {
        if (loanModel == null)
            loanModel = new LoanModel();
        return loanModel;
    }

}
