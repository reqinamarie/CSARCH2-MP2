package Simulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

import java.util.*;

public class SequenceController {
    public TextField txtSeq, txtRep;
    public boolean repValid = false, seqValid = false;

    @FXML
    public void initialize() {
        txtSeq.textProperty().addListener((obs, oldT, newT) -> checkValidSeq());
        txtRep.textProperty().addListener((obs, oldT, newT) -> checkValid());
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
        Pattern p = Pattern.compile("([\\dA-F]+||([\\dA-F]+-[\\dA-F]+))(,( )*([\\dA-F]+||([\\dA-F]+-[\\dA-F]+)))*");
        Matcher m = p.matcher(txtSeq.getText());
        Boolean b = m.matches();

        if (!b) {
            System.out.println(txtSeq);
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
        String[] txtSequence = txtSeq.getText().split(", ");

        // System.out.println(txtSeq.getText().split(","));
        // System.out.println(txtSequence.length);

        List<String> listData = Arrays.asList(txtSequence);
        ArrayList<String> stringData = new ArrayList<String>(listData);

        ArrayList<Integer> data = new ArrayList<Integer>();
        // Processing
        for (int i = 0; stringData.size() > i; i++) {
            if (stringData.get(i).contains("-")) {
                String[] a = stringData.get(i).split("-");
                List<Integer> range = IntStream.rangeClosed(parseInt(a[0]), (parseInt(a[1]))).boxed()
                        .collect(Collectors.toList());
                data.addAll(range);
            }

            else {
                data.add(parseInt(stringData.get(i)));
            }
        }

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }

        seq = new Sequence(data, loops);
        return seq;
    }
}
