package Simulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

import java.util.*;

public class SequenceController {
    public TextField txtSeq, txtRep;
    public boolean repValid = true, seqValid = false;

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
        Pattern p = Pattern.compile("(\\d+||(\\d+-\\d+))(,( )*(\\d+||(\\d+-\\d+)))*");
        Matcher m = p.matcher(txtSeq.getText());
        Boolean b = m.matches();

        if (!b) {
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
        } else return false;
    }

    public Sequence getSequence() {
        Sequence seq = null;
        int loops = parseInt(txtRep.getText());
        String[] txtSequence = txtSeq.getText().split(", ");
        
        System.out.println(txtSequence);
        
        //ArrayList<String> data = Arrays.asList(txtSeq);
        
        //draft range splitter
        /*
        for (int i=0; data.size(); i++){
            if (data.get(i).contains("-")){
                String[] a = data.get(i).split("-");
                // a[0] contains start range; a[1] contains end range
            }
        }
        */

        //seq = new Sequence(data, loops);
        return seq;
    }
}
