package edu.school21.hlowell.smartcalc.model.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.io.*;
import java.net.URL;

@Getter
public class HistoryModel {

    private final String FILENAME = "history.txt";
    private final ObservableList<String> historyData = FXCollections.observableArrayList();
    private File file;

    public HistoryModel() {
        URL url = HistoryModel.class.getClassLoader().getResource(FILENAME);
        if (url == null) {
            printErrorMessage();
            return;
        }
        file = new File(url.getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready())
                historyData.add(br.readLine());
        } catch (IOException ignore) {
            printErrorMessage();
        }
    }

    public void save(String line) {
        historyData.add(line);
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
            printWriter.println(line);
        } catch (FileNotFoundException ignore) {
            printErrorMessage();
        }
    }

    public void clear() {
        historyData.clear();
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
        } catch (FileNotFoundException ignore) {
            printErrorMessage();
        }
    }

    private void printErrorMessage() {
        System.err.printf("History file \"%s\" not found in resources%n", FILENAME);
    }
}
