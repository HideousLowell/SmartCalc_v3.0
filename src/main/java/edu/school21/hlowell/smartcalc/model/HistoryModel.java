package edu.school21.hlowell.smartcalc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.io.*;

public class HistoryModel {

    private final String filename;

    @Getter
    private final ObservableList<String> historyData = FXCollections.observableArrayList();
    private final File file;

    public HistoryModel(String filename)  {
        this.filename = filename;
        file = new File(filename);
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
        System.err.printf("History file \"%s\" not found in resources%n", filename);
    }

}
