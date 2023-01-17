package edu.school21.hlowell.smartcalc.core;

import edu.school21.hlowell.smartcalc.view.calc.CalcController;
import edu.school21.hlowell.smartcalc.view.chart.ChartController;
import edu.school21.hlowell.smartcalc.view.dialogs.ErrorPopUp;
import edu.school21.hlowell.smartcalc.view.history.HistoryController;
import edu.school21.hlowell.smartcalc.view.valuearea.ValueAreaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ViewHandler {

    private final Stage mainStage;
    private final ViewModelFactory vmf;

    public void start() throws IOException {
        openCalcView();
        mainStage.show();
    }

    public void openCalcView() {
        try {
            FXMLLoader loader = new FXMLLoader(ViewHandler.class.getClassLoader().getResource("main.fxml"));
            Scene scene = new Scene(loader.load());
            CalcController controller = loader.getController();
            controller.init(this, vmf.getCalcViewModel(), vmf.getHistoryViewModel());
            mainStage.setTitle("SmartCalc v3.0");
            mainStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openChartView() {
        try {
            FXMLLoader loader = new FXMLLoader(ViewHandler.class.getClassLoader().getResource("charts.fxml"));
            Scene scene = new Scene(loader.load());
            Stage chartStage = new Stage();
            ChartController controller = loader.getController();
            controller.init(vmf.getChartViewModel());
            chartStage.initModality(Modality.APPLICATION_MODAL);
            chartStage.setScene(scene);
            chartStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openHistoryView() {
        HistoryController historyController = new HistoryController();
        Stage historyStage = new Stage();
        historyController.init(historyStage, vmf.getHistoryViewModel());
        historyStage.initModality(Modality.APPLICATION_MODAL);
        historyStage.show();
    }

    public void openValueAreaDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(ViewHandler.class.getClassLoader().getResource("valuearea.fxml"));
            Scene scene = new Scene(loader.load());
            ValueAreaController controller = loader.getController();
            controller.init(this, vmf.getChartViewModel());
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLoanCalc() {

    }



    public void popError(String error) {
        new ErrorPopUp(error);
    }

}
