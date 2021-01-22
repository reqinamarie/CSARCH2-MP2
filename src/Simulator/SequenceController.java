package Simulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import static java.lang.Integer.parseInt;

public class SequenceController {
    public TextField txtSeq, txtRep;
    public boolean isValid;

    @FXML
    public void initialize() {
        isValid = false;
        txtSeq.textProperty().addListener((obs, oldT, newT) -> checkValid(txtSeq, newT));
        txtRep.textProperty().addListener((obs, oldT, newT) -> checkValid(txtRep, newT));
    }

    public void checkValid(TextField field, String text) {
        try {
            parseInt(text);
            field.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
            isValid = true;
        } catch (Exception e) {
            field.setStyle("-fx-text-box-border: red;");
            isValid = false;
        }
    }

    public void getSequence() {
        //  make object na pala siguro dito;  change return type
    }
}
