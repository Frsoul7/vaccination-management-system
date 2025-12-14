package app.ui.gui;

import app.dto.VaccRecordsDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordVaccineAdministrationGUI implements Initializable {
    @FXML
    private Button btnSaveFinalRecords;
    @FXML
    private Label lblFinalRecords;

    private VaccineAdministrationGUI mainGUI;

    public RecordVaccineAdministrationGUI() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void associateParentGUI(VaccineAdministrationGUI mainGUI){
        this.mainGUI=mainGUI;
    }

    public void writeLabelFinalRecordInfo(VaccRecordsDTO vaccRecords) {
        lblFinalRecords.setText(vaccRecords.toString());
    }

    @FXML
    private void btnSaveFinalRecordsAction(ActionEvent event) throws IOException {
        mainGUI.saveRecordsPermanently();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
