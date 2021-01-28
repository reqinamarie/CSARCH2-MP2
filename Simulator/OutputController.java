package Simulator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

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

    /*
    public void snapshot() throws Exception {
        try
        {
            Robot awt_robot = new Robot();
            BufferedImage Entire_Screen = awt_robot.createScreenCapture(new Rectangle(435,115, 500, 480));
            //BufferedImage Entire_Screen = awt_robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(Entire_Screen, "PNG", new File("Entire_Screen.png"));
        }
        catch(java.awt.AWTException awte)
        {
            awte.printStackTrace();
        }
        
    }
    */

    public void createFile() {
        //write
        try {
            FileWriter myWriter = new FileWriter("result.txt");
            myWriter.write("Cache Hits:      " + cache.getHits() + "\n");
            myWriter.write("Cache Misses:    " + cache.getMiss() + "\n");
            myWriter.write("Miss Penalty:    " + this.missPenalty + "\n");
            myWriter.write("Average Memory Access Time: " + this.avgAccessTime + "\n");
            myWriter.write("Total Memory Access Time:   " + this.totalAccessTime + "\n\n\n");

            myWriter.write("     Block     " + "|" + "     Data     \n");
            int index = 0;
            for (int data : cache.getContent()) {
                myWriter.write("       " + index + "       " + "|" + "       " + data + "        \n");
                index++;
            }
            myWriter.close();
            System.out.println("Successfully exported results.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
