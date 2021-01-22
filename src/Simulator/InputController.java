package Simulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class InputController {

    public ComboBox<String> cmbMMType;
    public ComboBox<String> cmbCacheType;
    public ComboBox<String> cmbLoad;

    public TextField txtBlockSize;
    public TextField txtMMSize;
    public TextField txtMMTime;
    public TextField txtCacheSize;
    public TextField txtCacheTime;
    public List<TextField> textFields;

    public Button btnNextPage;

    @FXML
    public void initialize() {
        ObservableList<String> types = FXCollections.observableArrayList("Blocks", "Words");
        cmbMMType.setItems(types);
        cmbCacheType.setItems(types);
        cmbMMType.getSelectionModel().selectFirst();
        cmbCacheType.getSelectionModel().selectFirst();

        ObservableList<String> loads = FXCollections.observableArrayList("Load Through", "Non Load Through");
        cmbLoad.setItems(loads);
        cmbLoad.getSelectionModel().selectFirst();

       textFields = Arrays.asList(txtBlockSize, txtMMSize, txtMMTime, txtCacheSize, txtCacheTime);

       for (TextField tf: textFields) {
           tf.textProperty().addListener((obs, oldVal, newVal) -> checkIfEnableNext());
       }
    }

    @FXML
    public void nextButtonClicked(ActionEvent event) throws IOException {
        Parent seqInputParent = FXMLLoader.load(getClass().getResource("sequence input.fxml"));
        Scene seqInputScene = new Scene(seqInputParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(seqInputScene);
        window.show();
        window.sizeToScene();
    }

    @FXML
    public void checkIfEnableNext() {

        for (TextField tf: textFields) {
            if (tf.getText().isEmpty() || !isValid(tf, tf.getText())) {
                btnNextPage.setDisable(true);
                return;
            }
        }

        btnNextPage.setDisable(false);
    }

    private boolean isValid(TextField field, String text) {
        try {
            parseInt(text);
            field.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
            return true;
        } catch (Exception e) {
            field.setStyle("-fx-text-box-border: red;");
            return false;
        }
    }


}