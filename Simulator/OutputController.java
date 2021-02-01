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

/*for diaalog box */
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

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
            dialog("Successfully exported results to \"results.txt\".");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            dialog("An error occurred. Could not export results.");
        }
    }

    public void dialog(String prompt) {
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Export Results");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(prompt);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        
        dialog.showAndWait();
    /*
        //Creating a vbox to hold the button and the label
        HBox pane = new HBox(15);
        //Setting the space between the nodes of a HBox pane
        pane.setPadding(new Insets(50, 150, 50, 60));
        pane.getChildren().addAll(txt, button);
        //Creating a scene object
        Scene scene = new Scene(new Group(pane), 595, 250, Color.BEIGE);
        stage.setTitle("Dialog");
        stage.setScene(scene);
        stage.show();
    */
    }
}
