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

import static java.lang.Integer.parseInt;

public class GroupController {

    public ArrayList<SequenceController> sControllers;

    public Button btnRemoveSeq;
    public VBox vbSeqGroup;
    public TextField txtGroupRep;

    public boolean validRep = true;

    @FXML
    public void initialize() throws IOException{
        sControllers = new ArrayList<>();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sequence.fxml"));
        Parent sequence = loader.load();

        vbSeqGroup.getChildren().add(sequence);
        sControllers.add(loader.getController());
        txtGroupRep.textProperty().addListener((obs, newV, oldV) -> isGroupRepValid());
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
        if (!validRep)
            return false;

        for (SequenceController s : sControllers) {
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

    private void isGroupRepValid() {
        try {
            if (parseInt(txtGroupRep.getText()) > 0) {
                txtGroupRep.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
                validRep = true;
                return;
            }

            txtGroupRep.setStyle("-fx-text-box-border: red;");
            validRep = false;
        } catch (Exception e) {
            txtGroupRep.setStyle("-fx-text-box-border: red;");
            validRep = false;
        }
    }
}
