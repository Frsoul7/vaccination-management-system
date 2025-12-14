package app.ui.gui;

import app.controller.ImportLegacySystemDataController;
import app.domain.algorithms.MergeSort;
import app.domain.model.ImportedDataInformation;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import app.dto.ImportedDataInformationDTO;
import app.ui.console.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CoordinatorGUI_1 implements Initializable {

    @FXML
    private Button importCSVFileButton;
    @FXML
    private Label FileNotLoaded;
    @FXML
    private TableView ShowList;
    @FXML
    private RadioButton RadioArrivalTime;
    @FXML
    private RadioButton RadioLeavingTime;
    @FXML
    private RadioButton RadioAscending;
    @FXML
    private RadioButton RadioDescending;
    @FXML
    private Button SortButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button LogoutButton;
    private Window stage;
    @FXML
    private ToggleGroup time;
    @FXML
    private ToggleGroup ascdesc;


    private List<ImportedDataInformationDTO> data = null;

    private ImportLegacySystemDataController importLegacySystemDataController;

    private int vaccinationCenterId;

    //private ImportCSVUI;

    /**
     * Method to initialize the scene "Coordinator Menu"
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaccinationCenterId = (int)resourceBundle.getObject("");

        this.importLegacySystemDataController = new ImportLegacySystemDataController(vaccinationCenterId);

    }


    @FXML
    public void ImportCSV(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV " + "File", "*.csv"));
        File file = fc.showOpenDialog(this.stage);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));


            if(this.importLegacySystemDataController.importLegacyData(file.getAbsolutePath(), "Arrival Time", "")) {

                data = this.importLegacySystemDataController.getImportedLegacyData();

                TableColumn Name = new TableColumn("Name");
                Name.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn snsUserNumber = new TableColumn("SNS User Number");
                snsUserNumber.setCellValueFactory(new PropertyValueFactory<>("snsUserNumber"));

                TableColumn designation = new TableColumn("Designation");
                designation.setCellValueFactory(new PropertyValueFactory<>("designation"));

                TableColumn vaccineName = new TableColumn("Vaccine Name");
                vaccineName.setCellValueFactory(new PropertyValueFactory<>("vaccineName"));

                TableColumn doseStep = new TableColumn("Dose Step");
                doseStep.setCellValueFactory(new PropertyValueFactory<>("doseStep"));

                TableColumn lotNumber = new TableColumn("Lot Number");
                lotNumber.setCellValueFactory(new PropertyValueFactory<>("lotNumber"));

                TableColumn scheduledDate = new TableColumn("Scheduled Date");
                scheduledDate.setCellValueFactory(new PropertyValueFactory<>("scheduledDate"));

                TableColumn scheduledTimeHour = new TableColumn("Scheduled TimeHour");
                scheduledTimeHour.setCellValueFactory(new PropertyValueFactory<>("scheduledTimeHour"));

                TableColumn arrivalDate = new TableColumn("Arrival Date");
                arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));

                TableColumn arrivalTimeHour = new TableColumn("Arrival Time Hour");
                arrivalTimeHour.setCellValueFactory(new PropertyValueFactory<>("arrivalTimeHour"));

                TableColumn nurseAdministrationDate = new TableColumn("Nurse Administration Date");
                nurseAdministrationDate.setCellValueFactory(new PropertyValueFactory<>("nurseAdministrationDate"));

                TableColumn nurseAdministrationTimeHour = new TableColumn("Nurse Administration Time Hour");
                nurseAdministrationTimeHour.setCellValueFactory(
                        new PropertyValueFactory<>("nurseAdministrationTimeHour"));

                TableColumn leavingDate = new TableColumn("Leaving Date");
                leavingDate.setCellValueFactory(new PropertyValueFactory<>("leavingDate"));

                TableColumn leavingTimeHour = new TableColumn("Leaving Time Hour");
                leavingTimeHour.setCellValueFactory(new PropertyValueFactory<>("leavingTimeHour"));

                ShowList.getColumns()
                        .addAll(Name, snsUserNumber, designation, vaccineName, doseStep, lotNumber, scheduledDate,
                                scheduledTimeHour, arrivalDate, arrivalTimeHour, nurseAdministrationDate,
                                nurseAdministrationTimeHour, leavingDate, leavingTimeHour);


                for(ImportedDataInformationDTO row : data) {
                    ShowList.getItems().add(row);

                }

                FileNotLoaded.setText("File loaded correctly");

            } else {
                FileNotLoaded.setText("File not loaded correctly");
            }


        }
        catch(FileNotFoundException|NullPointerException ex) {
            FileNotLoaded.setText("File not loaded correctly!");
            // throw new RuntimeException(ex);
        }

    }

    @FXML
    private void BackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/coordinatorGUI_0.fxml"));
        ResourceBundle rb = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if (key.isEmpty()) {
                    return vaccinationCenterId;
                }
                return null;
            }
            @Override
            public java.util.Enumeration<String> getKeys() {
                return java.util.Collections.emptyEnumeration();
            }
        };
        loader.setResources(rb);
        Parent root = loader.load();
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SessionLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Welcome2UI.fxml"));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void sortByMerge(ActionEvent eventSort) {
        // Check if radio buttons are selected
        if(time.getSelectedToggle() == null || ascdesc.getSelectedToggle() == null) {
            FileNotLoaded.setText("Please select sort options first!");
            return;
        }
        
        boolean selectedTime = false; // Leaving time: ON
        boolean selectedAscDesc = false; // Descending : ON

        if(time.getSelectedToggle().equals(RadioArrivalTime)) {
            selectedTime = true; // Arrival time: ON
        }

        if(ascdesc.getSelectedToggle().equals(RadioAscending)) {
            selectedAscDesc = true; // Ascending : ON
        }

        List<ImportedDataInformation> tempList = new ArrayList<ImportedDataInformation>();

        for(ImportedDataInformationDTO row : data) {
            ImportedDataInformation dataTemp =
                    new ImportedDataInformation(row.getName(), row.getSnsUserNumber(), row.getDesignation(),
                                                row.getVaccineName(), row.getDoseStep(), row.getLotNumber(),
                                                row.getScheduledDate(), row.getScheduledTimeHour(),
                                                row.getArrivalDate(), row.getArrivalTimeHour(),
                                                row.getNurseAdministrationDate(), row.getNurseAdministrationTimeHour(),
                                                row.getLeavingDate(), row.getLeavingTimeHour());

            tempList.add(dataTemp);


        }
        if(data != null) {
            MergeSort mergeSort = new MergeSort();
            if(selectedTime) {
                mergeSort.sortByArrivalTime(tempList);
            } else {
                mergeSort.sortByLeavingTime(tempList);
            }

            ShowList.getItems().clear();

            if(selectedAscDesc) {
                for(ImportedDataInformation row : tempList) {
                    ShowList.getItems().add(row);

                }
            }else{
                for(int i=tempList.size()-1; i>=0; i--){
                    ShowList.getItems().add(tempList.get(i));
                }
            }
            ShowList.refresh();
        } else {

            FileNotLoaded.setText("Need to import data first!");
        }

    }

}
