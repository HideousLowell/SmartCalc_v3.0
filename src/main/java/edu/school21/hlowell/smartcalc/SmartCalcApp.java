package edu.school21.hlowell.smartcalc;

import edu.school21.hlowell.smartcalc.core.ModelFactory;
import edu.school21.hlowell.smartcalc.core.ViewHandler;
import edu.school21.hlowell.smartcalc.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class SmartCalcApp extends Application {

    @Override
    public void start(Stage stage)   {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(stage, vmf);
        viewHandler.start();
    }

}


