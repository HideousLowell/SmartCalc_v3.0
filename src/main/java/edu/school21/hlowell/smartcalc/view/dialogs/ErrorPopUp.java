package edu.school21.hlowell.smartcalc.view.dialogs;

import javafx.scene.control.Alert;

public class ErrorPopUp extends Alert {

    public ErrorPopUp(String errorMsg) {
        super(Alert.AlertType.ERROR);
        setTitle("Error");
        setHeaderText("Check your input");
        setContentText(errorMsg);
        showAndWait();
    }

}
