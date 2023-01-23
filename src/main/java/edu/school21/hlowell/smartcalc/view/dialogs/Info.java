package edu.school21.hlowell.smartcalc.view.dialogs;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Info extends Alert {

    public Info() {
        super(AlertType.INFORMATION);
        setTitle("Info");
        setHeaderText(null);
        String content = "If you see this information, most likely you opened the menu tab and saw the rest of the available interfaces.\n" +
                "You can build graphs, use a credit calculator.\n" +
                "The history of operations is also available to you, it is clickable, i promise.\n" +
                "Of course, you can clear the history so that no one finds out about your operations. Incognito mod in progress, hehe.\n" +
                "You can enter an x variable in the field and when you click on \"=\" you will be prompted to enter the value of the variable.\n" +
                "Either you can build a graph, with or without a variable.\n" +
                "Enjoy :3";
        setContentText(content);
        showAndWait();
    }

}
