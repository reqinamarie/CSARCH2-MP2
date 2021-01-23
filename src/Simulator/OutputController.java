package Simulator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OutputController {
    public Label lblCacheHit, lblCacheMiss, lblMissPenalty, lblAvgTime, lblTotalTime;
    public Button btnExport;
    public TableView tblCache;
    public TableColumn colBlock, colData;

    private Cache cache;
    private float missPenalty, avgAccessTime, totalAccessTime;


    public void initData(Cache c, float missPenalty, float aveTime, float totalTime) {
        this.cache = c;
        this.missPenalty = missPenalty;
        this.avgAccessTime = aveTime;
        this.totalAccessTime = totalTime;

        displayData();
    }

    private void populateTable() {
        /*
        for (int i = 0; i < 3; i++) {
            tblCache.getItems().add( object );
        }

         */
    }

    private void displayData() {
        // lblCacheHit
        // lblCacheMiss
        lblMissPenalty.setText(Float.toString(missPenalty));
        lblAvgTime.setText(Float.toString(avgAccessTime));
        lblTotalTime.setText(Float.toString(totalAccessTime));

        populateTable();
    }
}
