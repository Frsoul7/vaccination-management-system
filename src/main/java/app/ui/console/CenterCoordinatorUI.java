package app.ui.console;

import app.controller.App;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterCoordinatorUI implements Runnable {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;

    /**
     * Instance of the app
     */
    private final App app;

    /***
     * CenterCoordinatorUI constructor
     * @param vaccinationCenterId
     */
    public CenterCoordinatorUI(int vaccinationCenterId) {
        app = App.getInstance();
        this.vaccinationCenterId = vaccinationCenterId;
    }

    /***
     * Method to run the app and show the Center Coordinator menu list with options
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Check and export vaccination statistics ",
                                 new CheckDailyFullyVaccinatedUI(this.vaccinationCenterId)));
        options.add(new MenuItem("Analyze the performance of a center ",
                                 new PerformanceAnalysisUI(this.vaccinationCenterId)));
        options.add(new MenuItem("Import data from a legacy system ",
                                 new ImportLegacySystemDataUI(this.vaccinationCenterId)));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n# # Center Coordinator Menu: # #");

            if((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while(option != -1);

        //logout if "0-Cancel"
        app.doLogout();
    }
}
