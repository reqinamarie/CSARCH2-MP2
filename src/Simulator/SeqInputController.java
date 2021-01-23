package Simulator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SeqInputController {
    @FXML
    public TextField txtNumGroups;
    public VBox vbSequences;
    public VBox vbWindow;

    public ArrayList<TextField> textFields;

    public Button btnSimulate;
    public Button btnAddSeq1;

    public ArrayList<GroupController> gControllers;
    public ComboBox cmbSeqType;

    private Cache cache;
    private Memory memory;
    private String readType;

    @FXML
    public void initialize() throws NullPointerException {
        gControllers = new ArrayList<>();
        cmbSeqType.setItems(FXCollections.observableArrayList("Blocks", "Addresses"));
        cmbSeqType.getSelectionModel().selectFirst();

        vbSequences.focusedProperty().addListener(returnChangeListener());
        txtNumGroups.focusedProperty().addListener(returnChangeListener());
        btnSimulate.focusedProperty().addListener(returnChangeListener());
    }

    public void initData(Cache c, Memory m, String r) {
        this.cache = c;
        this.memory = m;
        this.readType = r;
    }

    @FXML
    public void createGroups(ActionEvent event) {
        try {
            List<Node> children = vbSequences.getChildren();
            int nGroups = parseInt((txtNumGroups.getText())), currSize = children.size();

            for (int i = currSize; i < nGroups; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("group sequence.fxml"));
                Parent root = loader.load();

                gControllers.add(loader.getController());
                ((GroupController) loader.getController()).initData(memory.getMMSize(), cache.getBlockSize());
                ((GroupController) loader.getController()).setInputType((String) cmbSeqType.getValue());
                children.add(root);
            }

            for (int i = currSize; i > nGroups; i--) {
                children.remove(i - 1);
                gControllers.remove(i - 1);
            }

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        for (TextField tf : textFields) {
            if (tf.getText().isEmpty()) {
                btnSimulate.setDisable(true);
                return;
            }
        }

        btnSimulate.setDisable(false);
    }

    public ArrayList<Group> getAllGroups() {

        ArrayList<Group> allGroups = new ArrayList<Group>();
        for (GroupController g : gControllers) {
            allGroups.add(g.getGroup());
        }

        return allGroups;
    }

    public void isValid() {
        if (gControllers.size() == 0)
            return;

        for (GroupController g : gControllers) {
            if (!g.isValid()) {
                btnSimulate.setDisable(true);
                return;
            }
        }

        btnSimulate.setDisable(false);
    }

    private void simulate() {
        ArrayList<Group> seqGroups = getAllGroups();

        for (Group g : seqGroups) {

            Sequence[] currGroup = g.getSequences();

            for (int i = 0; i < currGroup.length; i++) {

                Sequence currSequence = currGroup[i];

                for (int j = 0; j <= currSequence.getLoop(); j++)

                    for (int data : currSequence.getData()) {
                        cache.fetch(data);
                    }
            }
        }
    }

    private ChangeListener<Boolean> returnChangeListener() {
        return new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                isValid();
            }
        };
    }

    public void goToNext() throws IOException {
        try {
            simulate();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("output page.fxml"));
            Parent seqInputParent = loader.load();
            Scene seqInputScene = new Scene(seqInputParent);

            OutputController o = loader.getController();
            o.initData(cache, getMissPenalty(), getAveAccessTime(), getTotalAccessTime());

            Stage window = (Stage) (vbWindow).getScene().getWindow();
            window.setScene(seqInputScene);
            window.show();
            window.sizeToScene();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getMissPenalty() {
        float missPenalty;

        if (this.readType.equals("Load Through"))
            missPenalty = this.cache.getAccessTime() + this.memory.getAccessTime();
        else
            missPenalty = (2 * this.cache.getAccessTime()) + (this.memory.getAccessTime() * this.cache.getBlockSize());

        return missPenalty;
    }

    public float getAveAccessTime() {
        float aveAccessTime;
        int hits = this.cache.getHits();
        int miss = this.cache.getMiss();

        aveAccessTime = ((hits/(hits+miss))*this.cache.getAccessTime()) + ((miss/(hits+miss))*getMissPenalty());

        return aveAccessTime;
    }

    public float getTotalAccessTime() {
        float totalAccessTime;
        int hits = this.cache.getHits();
        int miss = this.cache.getMiss();

        if (this.readType.equals("Load Through")) {
            totalAccessTime =   (hits * this.cache.getBlockSize() * this.cache.getAccessTime()) + 
                                (miss * this.cache.getBlockSize() * this.memory.getAccessTime()) +
                                (miss * this.cache.getAccessTime());
        }
        else {
            totalAccessTime = (hits * this.cache.getBlockSize() * this.cache.getAccessTime()) + 
                                (miss * this.cache.getBlockSize() * (this.cache.getAccessTime() + this.memory.getAccessTime())) +
                                (miss * this.cache.getAccessTime());
        }

        return totalAccessTime;        
    }

    public void inputTypeChanged() {
        for (GroupController g: gControllers) {
            g.setInputType((String) cmbSeqType.getValue());
        }
    }
}
