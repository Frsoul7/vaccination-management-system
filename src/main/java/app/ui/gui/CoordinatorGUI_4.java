package app.ui.gui;

import app.controller.PerformanceAnalysisController;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Validations;
import app.dto.PerformanceAnalysisDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CoordinatorGUI_4 implements Initializable {

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeIntervalField;

    @FXML
    private Button AnalyzeButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private ListView<String> ResultsListView;

    @FXML
    private Label errorMessage;

    private PerformanceAnalysisController performanceAnalysisController;
    private int vaccinationCenterId;

    /**
     * Initialize the scene
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaccinationCenterId = (int) resourceBundle.getObject("");
        this.performanceAnalysisController = new PerformanceAnalysisController(vaccinationCenterId);
    }

    /**
     * Analyze Performance button action
     * @param event
     */
    @FXML
    private void AnalyzePerformance(ActionEvent event) {
        // Clear previous results and error messages
        ResultsListView.getItems().clear();
        errorMessage.setText("");

        try {
            String date = dateField.getText().trim();
            String timeIntervalStr = timeIntervalField.getText().trim();

            // Validate inputs
            if (date.isEmpty() || timeIntervalStr.isEmpty()) {
                errorMessage.setText("Please fill in all fields");
                return;
            }

            // Validate date format
            if (!Validations.isDateFormatValid(date, true, true)) {
                errorMessage.setText("Invalid date format. Use DD/MM/YYYY");
                return;
            }

            // Validate time interval is a number
            int timeInterval;
            try {
                timeInterval = Integer.parseInt(timeIntervalStr);
                if (timeInterval <= 0) {
                    errorMessage.setText("Time interval must be positive");
                    return;
                }
            } catch (NumberFormatException e) {
                errorMessage.setText("Time interval must be a number");
                return;
            }

            // Validate data exists
            if (!performanceAnalysisController.validateData(date, timeInterval)) {
                errorMessage.setText("No data available for selected date");
                return;
            }

            // Show processing message
            errorMessage.setText("Analyzing data, please wait...");
            AnalyzeButton.setDisable(true);

            // Perform analysis
            if (performanceAnalysisController.analyseThePerformanceOfACenter(date, timeInterval)) {
                PerformanceAnalysisDTO result = performanceAnalysisController.getPerformanceAnalysis();

                // Display results
                ResultsListView.getItems().add("=== PERFORMANCE ANALYSIS RESULTS ===");
                ResultsListView.getItems().add("");
                ResultsListView.getItems().add("Date: " + result.getDate());
                ResultsListView.getItems().add("Time Interval: " + result.getTimeInterval() + " minutes");
                ResultsListView.getItems().add("Center Opening Hour: " + result.getOpenHour());
                ResultsListView.getItems().add("");
                ResultsListView.getItems().add("--- Input List (Arrivals - Departures) ---");
                
                int[] differences = result.getDifferenceBetweenArrLeav();
                for (int i = 0; i < differences.length; i++) {
                    String timeSlot = calculateTimeSlot(result.getOpenHour(), result.getTimeInterval(), i);
                    ResultsListView.getItems().add(timeSlot + ": " + differences[i]);
                }

                ResultsListView.getItems().add("");
                ResultsListView.getItems().add("--- WORST PERFORMANCE PERIOD ---");
                ResultsListView.getItems().add("Maximum Sum: " + result.getMaxSum());
                ResultsListView.getItems().add("Period: " + result.getLessEffectivePeriod()[0] + 
                                                " to " + result.getLessEffectivePeriod()[1]);
                ResultsListView.getItems().add("");
                ResultsListView.getItems().add("Contiguous Sublist: " + Arrays.toString(result.getContiguousSubListWithMaximumSum()));
                ResultsListView.getItems().add("");
                ResultsListView.getItems().add("This period had the most clients waiting");
                ResultsListView.getItems().add("(highest backlog in vaccination process)");

                errorMessage.setText("");
            } else {
                errorMessage.setText("Analysis failed. Please try again.");
            }

        } catch (Exception e) {
            errorMessage.setText("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            AnalyzeButton.setDisable(false);
        }
    }

    /**
     * Calculate time slot string based on open hour and interval
     */
    private String calculateTimeSlot(String openHourStr, int interval, int index) {
        try {
            String[] parts = openHourStr.split(":");
            int openHour = Integer.parseInt(parts[0]);
            int openMin = Integer.parseInt(parts[1]);
            
            int totalMinutes = openHour * 60 + openMin + (interval * index);
            int hour = (totalMinutes / 60) % 24;
            int min = totalMinutes % 60;
            
            int endTotalMinutes = totalMinutes + interval;
            int endHour = (endTotalMinutes / 60) % 24;
            int endMin = endTotalMinutes % 60;
            
            return String.format("%02d:%02d-%02d:%02d", hour, min, endHour, endMin);
        } catch (Exception e) {
            return "Slot " + index;
        }
    }

    /**
     * Back to menu
     * @param event
     * @throws IOException
     */
    @FXML
    private void BackToMenu(ActionEvent event) throws IOException {
        VaccinationCenterIdResourceBundle vcid = new VaccinationCenterIdResourceBundle();
        vcid.setVaccinationCenterId(vaccinationCenterId);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/coordinatorGUI_0.fxml"), vcid);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Logout
     * @param event
     * @throws IOException
     */
    @FXML
    private void SessionLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Welcome2UI.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
