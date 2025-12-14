package app.ui.console;

import app.controller.ImportLegacySystemDataController;
import app.controller.PerformanceAnalysisController;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.dto.ImportedDataInformationDTO;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalysisUI implements Runnable, Constants {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;


    private PerformanceAnalysisController performanceAnalysisController;

    private String date;

    private int timeInterval;

    public PerformanceAnalysisUI(int vaccinationCenterId) {
        this.performanceAnalysisController = new PerformanceAnalysisController(vaccinationCenterId);
        this.vaccinationCenterId = vaccinationCenterId;
    }

    public void run() {

        Utils.showText("\n# # Analyze the performance of a center# #");

        if(fillData()) {

            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                try {
                    if(performanceAnalysisController.analyseThePerformanceOfACenter(date, timeInterval)) {
                        Utils.showText("Successful analysis of the performance of the center!");

                        Utils.showText(performanceAnalysisController.getPerformanceAnalysis().toStringData());
                        Utils.showText(performanceAnalysisController.getPerformanceAnalysis().toStringContinuousSublist());
                        Utils.showText(performanceAnalysisController.getPerformanceAnalysis().toString());
                    } else {
                        Utils.showText("Failure during performance analysis!");
                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("Analysis canceled by the user!");
            }
        } else {
            Utils.showText("[warning] Failure during performance analysis, please try again!");
        }
    }

    /***
     * Method used to read the input from the user
     * @return true if all the data is filled correctly
     */
    private boolean fillData() {

        try {
            Utils.showText("All fields with '*' are mandatory\n");

            // date: input & validation
            date = Utils.readLineFromConsole("Insert the date that you intend to analyse (E.g.: dd/mm/yyyy):" + " ");
            while(!Validations.isDateFormatValid(date, true, true)) {
                date = Utils.readLineFromConsole("[error] - Invalid date\nPlease try again: ");
            }

            // time interval: input & validation
            timeInterval = Utils.readIntegerFromConsole("Insert the time interval*: ", DEFAULT_VALUE_TIME_INTERVAL);
            while(!Validations.isLengthValidLesserThen(timeInterval, true, true, SIZE_TIMEINTERVAL)) {
                timeInterval = Utils.readIntegerFromConsole("[error] - Invalid time interval\nPlease try again: ",
                                                            DEFAULT_VALUE_NUMBERDOSES_NUMBER);
            }
            return performanceAnalysisController.validateData(date, timeInterval);
        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText("Operation cancelled by user");
            return false;
        }
    }

    /***
     * Method that shows the information which was inserted, for user confirmation
     */
    private void showData() {
        Utils.showText(String.format("Date to analyse: %s\nSelected time interval: %d", date, timeInterval));
    }
}
