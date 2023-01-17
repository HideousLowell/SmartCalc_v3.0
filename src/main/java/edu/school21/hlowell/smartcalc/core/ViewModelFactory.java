package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.view.calc.CalcViewModel;
import edu.school21.hlowell.smartcalc.view.chart.ChartViewModel;
import edu.school21.hlowell.smartcalc.view.history.HistoryViewModel;
import lombok.Getter;

@Getter
public class ViewModelFactory {

    private final CalcViewModel calcViewModel;
    private final ChartViewModel chartViewModel;
    private final HistoryViewModel historyViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        calcViewModel = new CalcViewModel(modelFactory.getCalcModel(), modelFactory.getHistoryModel());
        chartViewModel = new ChartViewModel(modelFactory.getCalcModel());
        historyViewModel = new HistoryViewModel(modelFactory.getHistoryModel(), calcViewModel);
    }

}
