package app.ui.gui;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.dto.VaccAdministrationDosageDTO;
import app.ui.console.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowDosageAndTypeTimeGUI implements Initializable {

    @FXML
    private Button btnSaveTime;
    @FXML
    private Label lbWarningMessage;

    @FXML
    private Label lblVaccineDosageInfo;

    @FXML
    private TextField txtVaccAdministrationTime;

    private VaccineAdministrationGUI mainGUI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void associateParentGUI(VaccineAdministrationGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @FXML
    private void btnSaveTimeAction(ActionEvent event) throws IOException {
        String time = txtVaccAdministrationTime.getText();
        try {
            if(Validations.isHourFormatValid(time, true, true)) {
                lbWarningMessage.setText(" ");
                mainGUI.setVaccAdministratrionTime(time);
                ((Node)event.getSource()).getScene().getWindow().hide();
            } else {
                lbWarningMessage.setText("Warning! Time format is not correct!\nPlease try again!");
            }
        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return;
        }


    }

    @FXML
    private void onEnter(ActionEvent event) throws IOException {
        btnSaveTimeAction(event);
    }

    public void writeLabelDosageInformation(VaccAdministrationDosageDTO dosageInfo){
        lblVaccineDosageInfo.setText(dosageInfo.toString());
    }
}
