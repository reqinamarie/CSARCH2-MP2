package Simulator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.util.Arrays;
import java.util.List;

public class OutputController {
    public Label lblCacheHit, lblCacheMiss, lblMissPenalty, lblAvgTime, lblTotalTime;
    public Button btnExport;
    public TableColumn colBlock, colData;

    private void populateTable() {
        List<String> blocks = Arrays.asList("B1", "B2", "B3");
        List<Integer> data = Arrays.asList(1, 2, 3);

        colBlock.setUserData(blocks);
        colData.setUserData(data);
    }
}
