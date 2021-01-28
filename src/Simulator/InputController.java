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
import javafx.scene.control.Label;

import javax.swing.*;
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
    public List<TextField> tfSizes;
    public Label lblError;

    public Button btnNextPage;
    public boolean tfValid = false;

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
       tfSizes = Arrays.asList(txtBlockSize, txtMMSize, txtCacheSize);

       for (TextField tf: textFields) {
           tf.textProperty().addListener((obs, oldVal, newVal) -> {isValid(tf, newVal); checkIfEnableNext();});
       }
    }

    @FXML
    public void nextButtonClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sequence input.fxml"));
        Parent seqInputParent = loader.load();

        SeqInputController siController = loader.getController();

        Scene seqInputScene = new Scene(seqInputParent);

        siController.initData(createCache(), createMemory(), cmbLoad.getValue(), seqInputScene);
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
                tfValid = false;
                return;
            }
        }
        lblError.setText("");
        btnNextPage.setDisable(false);
        tfValid = true;
    }

    private boolean isValid(TextField field, String text) {
        try {
            if (parseInt(text) > 0) {
                if (tfSizes.contains(field)) {
                    if (!validateSizes())
                        return false;
                    if (!base2(field))
                        return false;
                }
                field.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
                return true;
            }
            lblError.setText("ERROR: Invalid input. Please enter a natural number.");
            field.setStyle("-fx-text-box-border: red;");
            return false;
        } catch (Exception e) {
            lblError.setText("ERROR: Group repetition must be an integer greater than 0.");
            field.setStyle("-fx-text-box-border: red;");
            return false;
        }
    }

    public boolean base2(TextField field){
        int x = parseInt(field.getText());
        if ((x != 0) && ((x & (x - 1)) == 0))
            return true;
        
        lblError.setText("ERROR: Value should be of base 2.");
        field.setStyle("-fx-text-box-border: red;");
        btnNextPage.setDisable(true);
        return false;
    }

    public boolean validateSizes() {
        boolean err = false;

        if (!(txtBlockSize.getText().isEmpty())){
            int bs = parseInt(txtBlockSize.getText());

            //block size <= mm size
            if (!(txtMMSize.getText().isEmpty()) && cmbMMType.getValue().equals("Words") && bs > parseInt(txtMMSize.getText())){
                err = true;
                lblError.setText("ERROR: Main memory is smaller than block size.");
                txtMMSize.setStyle("-fx-text-box-border: red;");
            } else {
                txtMMSize.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
            }

            //block size <= cache size
            if (!(txtCacheSize.getText().isEmpty()) && cmbCacheType.getValue().equals("Words") && bs > parseInt(txtCacheSize.getText())){
                err = true;
                lblError.setText("ERROR: Cache memory is smaller than block size.");
                txtCacheSize.setStyle("-fx-text-box-border: red;");
            } else {
                txtCacheSize.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
            }
        }
/*
        //cache size should be < mm size
        if (!(txtMMSize.getText().isEmpty()) && !(txtCacheSize.getText().isEmpty())){
            Cache c = createCache();
            Memory m = createMemory();
            if(c.getNumBlocks() >= m.getMMSize()){
                err = true;
                // lblError
                txtMMSize.setStyle("-fx-text-box-border: red;");
                txtCacheSize.setStyle("-fx-text-box-border: red;");
            }
        }
 */
        if (err) {
            btnNextPage.setDisable(true);
            return false;
        }

        if (tfValid) {
            lblError.setText("");
            btnNextPage.setDisable(false);
        }
        return true;
    }

    private Memory createMemory() {
        if (btnNextPage.isDisabled())
            return null;

        int mmSize = parseInt(txtMMSize.getText()),
            blockSize = parseInt(txtBlockSize.getText());

        if (cmbMMType.getValue().equals("Blocks")) {
            return new Memory(mmSize, parseInt(txtMMTime.getText()));
        } else {
            return new Memory(mmSize, blockSize, parseInt(txtMMTime.getText()));
        }
    }

    private Cache createCache() {
        if (btnNextPage.isDisabled())
            return null;

        int cacheSize = parseInt(txtCacheSize.getText()),
            blockSize = parseInt(txtBlockSize.getText());

        if (cmbCacheType.getValue().equals("Blocks")) {
            return new Cache(cacheSize, blockSize, cacheSize, parseInt(txtCacheTime.getText()));
        } else {
            return new Cache(cacheSize, blockSize, parseInt(txtCacheTime.getText()));
        }
    }

}
