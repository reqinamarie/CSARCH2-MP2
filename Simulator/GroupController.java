package Simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class GroupController {

    public ArrayList<SequenceController> sControllers;

    public Button btnRemoveSeq;
    public VBox vbSeqGroup;
    public TextField txtGroupRep;

    public boolean validRep = true;
    public int nMMBlocks, blockSize;
    public String inputType, errorMessage = "";

    @FXML
    public void initialize() {
        sControllers = new ArrayList<>();
        txtGroupRep.textProperty().addListener((obs, newV, oldV) -> isGroupRepValid());
    }

    public void initData(int mm, int b) throws IOException {
        this.nMMBlocks = mm;
        this.blockSize = b;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sequence.fxml"));
        Parent sequence = loader.load();
        ((SequenceController) loader.getController()).initData(nMMBlocks, blockSize);

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
        ((SequenceController) loader.getController()).initData(nMMBlocks, blockSize);
        ((SequenceController) loader.getController()).setInputType(inputType);
    }

    @FXML
    public void removeSequence() throws IOException {
        int nSeq = vbSeqGroup.getChildren().size();

        if (nSeq <= 1) {
            return;
        } else if (nSeq == 2) {
            btnRemoveSeq.setDisable(true);
        }

        vbSeqGroup.getChildren().remove(nSeq - 1);
        sControllers.remove(nSeq - 1);
    }

    public String isValid() {
        if (!validRep)
            return errorMessage;

        for (SequenceController s : sControllers) {
            if (!s.isValid().isEmpty()) {
                return s.errorMessage;
            }
        }

        errorMessage = "";
        return errorMessage;
    }

    public Group getGroup() {

        Sequence[] seqGroup = new Sequence[sControllers.size()];
        int count = 0;

        for (SequenceController s : sControllers) {
            seqGroup[count] = s.getSequence();
            count++;
        }

        return new Group(seqGroup, parseInt(txtGroupRep.getText()));
    }

    private void isGroupRepValid() {
        try {
            if (parseInt(txtGroupRep.getText()) > 0) {
                txtGroupRep.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
                validRep = true;
                return;
            }

            errorMessage = "ERROR: Group repetition must be an integer greater than 0.";
            txtGroupRep.setStyle("-fx-text-box-border: red;");
            validRep = false;
        } catch (Exception e) {
            errorMessage = "ERROR: Group repetition must be an integer greater than 0.";
            txtGroupRep.setStyle("-fx-text-box-border: red;");
            validRep = false;
        }
    }

    public void setInputType(String type) {
        this.inputType = type;

        for (SequenceController s: sControllers) {
            s.setInputType(type);
        }
    }
}
