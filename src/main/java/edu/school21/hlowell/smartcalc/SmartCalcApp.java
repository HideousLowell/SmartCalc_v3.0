package edu.school21.hlowell.smartcalc;

import edu.school21.hlowell.smartcalc.core.ModelFactory;
import edu.school21.hlowell.smartcalc.core.ViewModelFactory;
import edu.school21.hlowell.smartcalc.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartCalcApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(stage, vmf);
        viewHandler.start();
    }

}


