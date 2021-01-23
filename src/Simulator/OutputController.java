package Simulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Arrays;
import java.util.List;

public class OutputController {
    public Label lblCacheHit, lblCacheMiss, lblMissPenalty, lblAvgTime, lblTotalTime;
    public Button btnExport;
    public TableView tblCache;
    public TableColumn colBlock, colData;

    @FXML
    public void initialize() {
        populateTable();
    }

    private void populateTable() {
        /*
        for (int i = 0; i < 3; i++) {
            tblCache.getItems().add( object );
        }

         */
    }
}
