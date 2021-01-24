package Simulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

import java.util.*;

public class SequenceController {
    public TextField txtSeq, txtRep;
    public boolean repValid = true, seqValid = false;
    public int nMMBlocks, blockSize;
    public String inputType, errorMessage = " ";

    @FXML
    public void initialize() {
        txtSeq.textProperty().addListener((obs, oldT, newT) -> checkValidSeq());
        txtRep.textProperty().addListener((obs, oldT, newT) -> checkValid());
    }

    public void initData(int mm, int b) {
        this.nMMBlocks = mm;
        this.blockSize = b;
    }

    public void checkValid() {
        try {
            if (parseInt(txtRep.getText()) > 0) {
                txtRep.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
                repValid = true;
                return;
            }

            errorMessage = "ERROR: Sequence repetition must be an integer greater than 0.";
            txtRep.setStyle("-fx-text-box-border: red;");
            repValid = false;
        } catch (Exception e) {
            errorMessage = "ERROR: Sequence repetition must be an integer greater than 0.";
            txtRep.setStyle("-fx-text-box-border: red;");
            repValid = false;
        }
    }

    public void checkValidSeq() {
        Pattern p = Pattern.compile("(\\d+|(\\d+-\\d+))(,\\s*(\\d+|(\\d+-\\d+)))*");
        Matcher m = p.matcher(txtSeq.getText());
        Boolean b = m.matches();

        if (!repValid)
            return;

        if (txtSeq.getText().trim().isEmpty()) {
            errorMessage = "ERROR: Sequence input cannot be empty";
            txtSeq.setStyle("-fx-text-box-border: red;");
            seqValid = false;
            return;
        }

        if (!b) {
            errorMessage = "ERROR: Improper syntax. (Integers only; comma delimited; dash for range representation.)";
            txtSeq.setStyle("-fx-text-box-border: red;");
            seqValid = false;
            return;
        }

        if (getSequence() == null) {
            //errorMessage = "ERROR: Input values must be within the range of declared Main Memory size.)";
            txtSeq.setStyle("-fx-text-box-border: red;");
            seqValid = false;
            return;
        }

        txtSeq.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
        seqValid = true;
    }

    public String isValid() {
        if (repValid && seqValid) {
            errorMessage = "";
        }

        return errorMessage;
    }

    public Sequence getSequence() {
        Sequence seq;
        int loops = parseInt(txtRep.getText());
        String[] txtSequence = txtSeq.getText().split("[,\\s][\\s]*");
        //Boolean blocks = (inputType.equals("Blocks"));

        int toBlocks = 1;
        if (inputType.equals("Addresses"))
            toBlocks = blockSize;

        List<String> listData = Arrays.asList(txtSequence);
        ArrayList<String> stringData = new ArrayList<String>(listData);

        ArrayList<Integer> data = new ArrayList<Integer>();
        // Processing

        try {
            for (int i = 0; stringData.size() > i; i++) {
                if (stringData.get(i).contains("-")) {
                    String[] a = stringData.get(i).split("-");
                    List<Integer> range = IntStream.rangeClosed(parseInt(a[0]) / toBlocks, (parseInt(a[1])) / toBlocks).boxed()
                            .collect(Collectors.toList());
                    data.addAll(range);
                } else {
                    data.add(parseInt(stringData.get(i)) / toBlocks);
                }

                if (data.get(data.size()-1) >= nMMBlocks) {
                    errorMessage = "ERROR: Input values must be within the range of declared Main Memory size.)";
                    return null;
                }
            }

            seq = new Sequence(data, loops);
            return seq;
        } catch (Exception e) {
            errorMessage = "ERROR: Invalid range input";
            return null;
        }

    }

    public void setInputType(String type) {
        this.inputType = type;
    }
}
