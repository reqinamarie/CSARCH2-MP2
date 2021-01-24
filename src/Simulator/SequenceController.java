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
    public String inputType;

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

            txtRep.setStyle("-fx-text-box-border: red;");
            repValid = false;
        } catch (Exception e) {
            txtRep.setStyle("-fx-text-box-border: red;");
            repValid = false;
        }
    }

    public void checkValidSeq() {
        Pattern p = Pattern.compile("(\\d+||(\\d+-\\d+))(,( )*(\\d+||(\\d+-\\d+)))*");
        Matcher m = p.matcher(txtSeq.getText());
        Boolean b = m.matches();

        if (!b || txtSeq.getText().isEmpty()) {
            txtSeq.setStyle("-fx-text-box-border: red;");
            seqValid = false;
            return;
        }

        if (getSequence() == null) {
            txtSeq.setStyle("-fx-text-box-border: red;");
            seqValid = false;
            return;
        }

        txtSeq.setStyle("-fx-text-box-border: lightgray; -fx-focus-color: lightgray;");
        seqValid = true;
    }

    public Boolean isValid() {
        if (repValid && seqValid) {
            return true;
        } else
            return false;
    }

    public Sequence getSequence() {
        Sequence seq;
        int loops = parseInt(txtRep.getText());
        String[] txtSequence = txtSeq.getText().split(",");
        Boolean blocks = (inputType.equals("Blocks"));

        // System.out.println(txtSeq.getText().split(","));
        // System.out.println(txtSequence.length);

        List<String> listData = Arrays.asList(txtSequence);
        ArrayList<String> stringData = new ArrayList<String>(listData);

        ArrayList<Integer> data = new ArrayList<Integer>();
        // Processing
        for (int i = 0; stringData.size() > i; i++) {
            stringData.set(i, stringData.get(i).trim());
            if (stringData.get(i).contains("-")) {
                String[] a = stringData.get(i).split("-");
                List<Integer> range = IntStream.rangeClosed(parseInt(a[0]), (parseInt(a[1]))).boxed()
                        .collect(Collectors.toList());
                data.addAll(range);
            }

            else {
                data.add(parseInt(stringData.get(i)));
            }

            if (blocks && data.get(i) > nMMBlocks)
                return null;
            else if (!blocks && data.get(i) > (nMMBlocks * blockSize))
                return null;
        }

        if (!blocks) {
            for (int i = 0; i < data.size(); i++) {
                data.set(i, data.get(i) / blockSize);
            }
        }

        seq = new Sequence(data, loops);
        return seq;
    }

    public void setInputType(String type) {
        this.inputType = type;
    }
}
