package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.model.CalcModel;

public class ModelFactory {

    private CalcModel calcModel;

    public CalcModel getCalcModel() {
        if (calcModel == null)
            calcModel = new CalcModel();
        return calcModel;
    }
}
