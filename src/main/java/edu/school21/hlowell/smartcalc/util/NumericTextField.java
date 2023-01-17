package edu.school21.hlowell.smartcalc.util;

import javafx.scene.control.TextField;

public class NumericTextField {
    public static void numericOnly(final TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

}
