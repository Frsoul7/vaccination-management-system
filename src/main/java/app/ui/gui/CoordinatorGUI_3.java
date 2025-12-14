package app.ui.gui;

import app.controller.CheckDailyFullyVaccinatedController;
import app.controller.ImportLegacySystemDataController;
import app.domain.model.CountByDate;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Validations;
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

public class CoordinatorGUI_3 implements Initializable {

    @FXML
    private TextField startingPeriodDate;

    @FXML
    private TextField endingPeriodDate;

    @FXML
    private Button AnalyzeButton;

    @FXML
    private Button BackButton;

    @FXML
    private ListView ShowTableView;

    @FXML
    private Label wrongPeriod;

    private ImportLegacySystemDataController importLegacySystemDataController;
    private int vaccinationCenterId;

    private List<CountByDate> lstatistics;

    private Window stage;

    private CheckDailyFullyVaccinatedController checkDailyFullyVaccinatedController;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vaccinationCenterId = (int)resourceBundle.getObject("");
        this.importLegacySystemDataController = new ImportLegacySystemDataController(vaccinationCenterId);

    }

    @FXML
    private void AnalyzeStatistics(ActionEvent event) {
        // Clear previous results and error messages
        ShowTableView.getItems().clear();
        wrongPeriod.setText("");

        try {
            DateCustom startDate = new DateCustom(startingPeriodDate.getText());
            DateCustom endDate = new DateCustom(endingPeriodDate.getText());
            checkDailyFullyVaccinatedController = new CheckDailyFullyVaccinatedController(vaccinationCenterId);

            if(Validations.isPeriodValid(startDate, endDate) && startDate != null && endDate != null) {
                // Show immediate feedback
                wrongPeriod.setText("Analyzing data, please wait...");
                AnalyzeButton.setDisable(true);
                
                DateCustom actualDate = new DateCustom(startDate);
                lstatistics = new ArrayList<>();
                int dayCount = 0;
                int maxDays = 730; // Maximum 2 years to prevent infinite loops

                while(actualDate.compareTo(endDate) <= 0 && dayCount < maxDays) {
                    // System.out.println(actualDate.toDayMonthYearFormat());
                    CountByDate newCount = new CountByDate(new DateCustom(actualDate));
                    newCount.setCount(checkDailyFullyVaccinatedController.getNumberDailyOfFullyVaccinated(actualDate));
                    //  System.out.println(newCount.getDate() );
                    lstatistics.add(newCount);
                    actualDate.addDays(1);
                    dayCount++;
                }

                // Re-enable button and clear loading message
                AnalyzeButton.setDisable(false);
                wrongPeriod.setText("");

                if(dayCount >= maxDays) {
                    wrongPeriod.setText("Date range too large. Please select a shorter period.");
                } else if(lstatistics.isEmpty()) {
                    wrongPeriod.setText("No data found. Please import CSV first.");
                } else {
                    for(CountByDate row : lstatistics) {
                        System.out.println(row.getDate() + " " + row.getCount());
                        ShowTableView.getItems().add(row.getDate() + " - Fully Vaccinated: " + row.getCount());
                    }
                }

            } else {
                wrongPeriod.setText("The inserted period is not valid");
            }
        } catch(Exception e) {
            AnalyzeButton.setDisable(false);
            wrongPeriod.setText("Data not available, choose other dates");
            e.printStackTrace();
        }
    }

    @FXML
    private void BackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/coordinatorGUI_0.fxml"));
        ResourceBundle rb = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if (key.isEmpty()) return vaccinationCenterId;
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
    private void exportFile(ActionEvent event) throws IOException {

        Writer writer = null;
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV " + "File", "*.csv"));
            File file = fc.showSaveDialog(this.stage);
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Date,NumberOfFullyVaccinated\n");
            for(CountByDate row : lstatistics) {
                writer.write(row.getDate() + "," + row.getCount() + "\n");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
    }

}


