package app.ui.console;

import app.controller.ImportLegacySystemDataController;
import app.domain.model.ImportedDataInformation;
import app.domain.model.VaccineType;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.dto.ImportedDataInformationDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportLegacySystemDataUI implements Runnable {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;


    private String timeCriteria;
    private String orderCriteria;
    private String filePath;

    private ImportLegacySystemDataController importLegacySystemDataController;


    public ImportLegacySystemDataUI(int vaccinationCenterId) {
        this.importLegacySystemDataController = new ImportLegacySystemDataController(vaccinationCenterId);
        this.vaccinationCenterId = vaccinationCenterId;
    }

    public void run() {

        Utils.showText("\n# # Import data from a legacy system# #");

        if(fillData()) {
            showData();

            if(Utils.confirm("Do you want to confirm the filled data? (Y/N): ")) {
                try {
                    if(this.importLegacySystemDataController.importLegacyData(filePath, timeCriteria, orderCriteria)) {
                        Utils.showText("Legacy data imported with success!");

                        List<ImportedDataInformationDTO> data =
                                this.importLegacySystemDataController.getImportedLegacyData();

                        if(orderCriteria.equals("Descending order")) {

                            for(int i = data.size() - 1; i >= 0; i--) {

                                Utils.showText(data.get(i).toString());
                            }
                        } else {
                            for(int i = 0; i < data.size(); i++) {

                                Utils.showText(data.get(i).toString());
                            }

                        }

                    } else {
                        Utils.showText("Failed importing legacy data!");

                    }
                }
                catch(RuntimeException ex) {
                    Utils.showText(ex.getMessage());
                }
            } else {
                Utils.showText("Data not imported!");
            }
        } else {
            Utils.showText("[warning] Failed importing legacy data, please try again!");
        }
    }

    /***
     * Method used to read the input from the user
     * @return true if all the data is filled correctly
     */
    private boolean fillData() {

        try {
            Utils.showText("All fields with '*' are mandatory\n");

            filePath = Utils.readLineFromConsole("Full path for CSV file *: ");
            while(importLegacySystemDataController.validateIfFileExist(filePath)) {
                filePath = Utils.readLineFromConsole("[error] - Invalid content\nPlease try again: ");
            }

            // Select criterias
            int option = 0;
            timeCriteria = "";

            List<String> criterias = new ArrayList<>();
            criterias.add("Arrival Time");
            criterias.add("Leaving Time");
            do {
                option = Utils.showAndSelectIndexWithoutCancel(criterias, "\nChoose sorting by arrival time or by the" +
                                                                          " center leaving time:");

                if((option >= 0) && (option < criterias.size())) {
                    timeCriteria = criterias.get(option);
                    option = -1;
                }
            } while(option != -1);

            option = 0;
            orderCriteria = "";

            List<String> orderCriterias = new ArrayList<>();
            orderCriterias.add("Ascending order");
            orderCriterias.add("Descending order");
            do {
                option = Utils.showAndSelectIndexWithoutCancel(orderCriterias, "\nChoose sorting by ascending or " +
                                                                               "descending order:");

                if((option >= 0) && (option < orderCriterias.size())) {
                    orderCriteria = orderCriterias.get(option);
                    option = -1;
                }
            } while(option != -1);

        }
        catch(OperationCanceledByUserException ex) {
            Utils.showText("Operation cancelled by user");
            return false;
        }
        return true;
    }


    /***
     * Method that shows the information which was inserted, for user confirmation
     */
    private void showData() {
        Utils.showText(String.format(
                "\nThe file path selected is: %s\nThe sorting time criteria is: %s\nThe sorting " + "order criteria " +
                "is: %s", filePath, timeCriteria, orderCriteria));
    }
}
