package edu.school21.hlowell.smartcalc.view.history;

import edu.school21.hlowell.smartcalc.model.HistoryModel;
import edu.school21.hlowell.smartcalc.view.calc.CalcViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;

public class HistoryViewModel {

    private final CalcViewModel calcViewModel;
    private final HistoryModel historyModel;

    public HistoryViewModel(HistoryModel historyModel, CalcViewModel calcViewModel) {
        this.calcViewModel = calcViewModel;
        this.historyModel = historyModel;
    }

    public void updateMainField(String str) {
        calcViewModel.setMathProblem(str);
    }

    public void deleteHistory() {
        historyModel.clear();
    }

    public void bindConnect(ObservableList<String> history) {
        Bindings.bindContent(history, historyModel.getHistoryData());
    }
}
