package edu.school21.hlowell.smartcalc.view.dialogs;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class ChooseXDialog extends TextInputDialog {

    public ChooseXDialog() {
        setHeaderText("X variable detected");
        setContentText("Please enter the X value:");
    }

    public Optional<String> getXFromUser() {
        return showAndWait();
    }
}
