package Simulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;


public class SeqInputController {
    @FXML
    public TextField txtNumGroups;
    public VBox vbSequences;
    public VBox vbSeqGroup1;

    public TextField txtSeq1;
    public TextField txtRep1;
    public ArrayList<TextField> textFields;

    public Button btnSimulate;
    public Button btnAddSeq1;

    public ArrayList<GroupController> gControllers;


    @FXML
    public void initialize() throws NullPointerException {
        gControllers = new ArrayList<>();

        /*
        textFields = new ArrayList<TextField>();
        try {
            btnAddSeq1.fire();
            txtSeq1.textProperty().addListener((obs, oldVal, newVal) -> checkIfEnableSimulate());
            txtRep1.textProperty().addListener((obs, oldVal, newVal) -> checkIfEnableSimulate());
            textFields.add(txtSeq1);
            textFields.add(txtRep1);
        } catch (Exception e) {}
         */
    }

    @FXML
    public void createGroups(ActionEvent event) {
        try {
            List<Node> children = vbSequences.getChildren();
            int nGroups = parseInt((txtNumGroups.getText())),
                currSize = children.size();

            for (var i = currSize; i < nGroups; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("group sequence.fxml"));
                Parent root = loader.load();

                gControllers.add(loader.getController());
                children.add(root);
            }

            for (var i = currSize; i > nGroups; i--) {
                children.remove(i-1);
                gControllers.remove(i-1);
            }

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.sizeToScene();
        } catch (Exception e) {
            System.out.println("NOT A NUMBER");
            e.printStackTrace();
            return;
        }
    }

    @FXML
    public void checkIfEnableSimulate() {
        System.out.println(textFields.size());
        for (TextField tf: textFields) {
            if (tf.getText().isEmpty()) {
                btnSimulate.setDisable(true);
                return;
            }
        }

        btnSimulate.setDisable(false);
    }

    public void getAllSequences() {
        //  kayo na bahala lolll

        /*
        for (GroupController g: gControllers) {
            g.getGroupSeq;
        }
         */
    }
}
