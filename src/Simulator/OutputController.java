package Simulator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OutputController {
    public Label lblCacheHit, lblCacheMiss, lblMissPenalty, lblAvgTime, lblTotalTime;
    public Button btnExport;
    public TableView<TableRow> tblCache;
    // public TableColumn<TableRow, String> colBlock, colData;

    private Cache cache;
    private float missPenalty, avgAccessTime, totalAccessTime;

    public void initData(Cache c, float missPenalty, float aveTime, float totalTime) {
        this.cache = c;
        this.missPenalty = missPenalty;
        this.avgAccessTime = aveTime;
        this.totalAccessTime = totalTime;

        // this.colData.setCellValueFactory(cell -> new SimpleIntegerProperty());
        // this.colBlock.setCellValueFactory(cell -> new SimpleStringProperty(new
        // String("456")));
        // tblCache.getItems().addAll(IntStream.rangeClosed(0, c.getSize() - 1), "column
        // 2");
        displayData();
    }

    private void populateTable() {

        ObservableList<TableRow> rows = FXCollections.observableArrayList();
        int index = 0;
        for (int data : cache.getContent()) {
            rows.add(new TableRow(index, data));
            index++;
        }

        TableColumn<TableRow, String> colBlock = new TableColumn<>("Block");
        colBlock.setCellValueFactory(new PropertyValueFactory<>("blockNo"));

        TableColumn<TableRow, String> colData = new TableColumn<>("Data");
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        tblCache.setItems(rows);
        tblCache.getColumns().addAll(colBlock, colData);
    }

    private void displayData() {
        lblCacheHit.setText(Integer.toString(cache.getHits()));
        lblCacheMiss.setText(Integer.toString(cache.getMiss()));
        lblMissPenalty.setText(Float.toString(missPenalty));
        lblAvgTime.setText(Float.toString(avgAccessTime));
        lblTotalTime.setText(Float.toString(totalAccessTime));

        populateTable();
    }
}
