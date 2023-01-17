package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.pojo.ValueArea;
import edu.school21.hlowell.smartcalc.view.calc.CalcViewModel;
import edu.school21.hlowell.smartcalc.view.chart.ChartViewModel;
import lombok.Getter;

@Getter
public class ViewModelFactory {

    private final CalcViewModel calcViewModel;
    private final ChartViewModel chartViewModel;
    private ValueArea valueArea;

    public ViewModelFactory(ModelFactory modelFactory) {
        calcViewModel = new CalcViewModel(modelFactory.getCalcModel());
        chartViewModel = new ChartViewModel(modelFactory.getCalcModel());
    }

}
