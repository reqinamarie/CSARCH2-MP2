package Simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GroupController {

    public ArrayList<SequenceController> sControllers;

    public Button btnRemoveSeq;
    public VBox vbSeqGroup;

    @FXML
    public void initialize() throws IOException{
        sControllers = new ArrayList<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sequence.fxml"));
        Parent sequence = loader.load();

        vbSeqGroup.getChildren().add(sequence);
        sControllers.add(loader.getController());
    }

    @FXML
    public void addSequence() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sequence.fxml"));
        Parent sequence = loader.load();

        vbSeqGroup.getChildren().add(sequence);
        sControllers.add(loader.getController());
        btnRemoveSeq.setDisable(false);

        int index = vbSeqGroup.getChildren().size() - 1;
        Group parentSeq = ((Group) vbSeqGroup.getChildren().get(index));

        /*
        ((TextField) (parentSeq.getChildren().get(1))).textProperty().addListener((obs, oldVal, newVal) -> checkIfEnableSimulate());
        ((TextField) (parentSeq.getChildren().get(2))).textProperty().addListener((obs, oldVal, newVal) -> checkIfEnableSimulate());


        //btnSimulate.setDisable(true);
        textFields.add((TextField) parentSeq.getChildren().get(1));
        textFields.add((TextField) parentSeq.getChildren().get(2));

        System.out.println(textFields.size());

     */
    }

    @FXML
    public void removeSequence() throws IOException {
        int nSeq = vbSeqGroup.getChildren().size();

        if (nSeq <= 1) {
            return;
        } else if (nSeq == 2) {
            btnRemoveSeq.setDisable(true);
        }

        vbSeqGroup.getChildren().remove(nSeq-1);
        sControllers.remove(nSeq-1);
    }

    public boolean isValid() {
        for (SequenceController s: sControllers) {
            if (!s.isValid())
                return false;
        }

        return true;
    }

    public String getGroupSeq() {
        // make object dito;  change na lang din yung return type

        //Array
        /*
        for (SequenceController s: sControllers) {
            s.getSequence()  // add niyo na lang sa array ??  idk di ko alam kung ano objects niyo
        }
        */
        return null;
    }
}
