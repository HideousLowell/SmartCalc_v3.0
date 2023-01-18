package edu.school21.hlowell.smartcalc.view.history;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class HistoryController {

    private ObservableList<String> tableData = FXCollections.observableArrayList();
    private HistoryViewModel historyViewModel;

    public void init(Stage stage, HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
        historyViewModel.bindConnect(tableData);
        TableView<String> tableView = new TableView<>(tableData);
        TableColumn<String, String> tableColumn = new TableColumn<>("History of operations");
        tableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
        tableView.getColumns().add(tableColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getSelectionModel().getSelectedCells().addListener(this::selectEntry);
        Scene scene = new Scene(tableView);
        stage.setScene(scene);
    }

    private void selectEntry(ListChangeListener.Change<? extends TablePosition> c) {
        c.getList().stream().findFirst().ifPresent(tablePosition -> {
            int row = tablePosition.getRow();
            Object cell = tablePosition.getTableColumn().getCellData(row);
            historyViewModel.updateMainField((String) cell);
        });
    }

}
