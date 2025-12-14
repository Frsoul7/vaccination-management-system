package app.ui.gui;

import app.domain.model.utils.DateCustom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SelectDateGUI implements Initializable {
    @FXML
    private Button btnAcceptDate;
    @FXML
    private DatePicker dpSelectDate;

    private DateCustom date;

    private VaccineAdministrationGUI mainGUI;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void associateParentGUI(VaccineAdministrationGUI mainGUI){
        this.mainGUI=mainGUI;
    }

    @FXML
    private void dpSelectDateAction(ActionEvent event) throws IOException {
       String pattern="dd/MM/yyyy";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        date= new DateCustom(dpSelectDate.getValue().format(dateFormatter));
        mainGUI.setVaccDaministrationDate(date);
    }




    @FXML
    private void btnAcceptDateAction(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
