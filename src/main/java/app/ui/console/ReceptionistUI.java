package app.ui.console;

import app.controller.App;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;

    /**
     * Instance of the app
     */
    private final App app;

    /***
     * Instance of Receptionist UI
     * @param vaccinationCenterId
     */
    public ReceptionistUI(int vaccinationCenterId) {
        app = App.getInstance();
        this.vaccinationCenterId = vaccinationCenterId;
    }

    /***
     * Method to run the app and show the Receptionist menu list with options
     */
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("SNS User Register ", new RegisterSNSUserUI()));
//        options.add(new MenuItem("SNS User Vaccination Schedulling ",
//                                 new ShowTextUI("You have chosen SNS User Vaccination Schedulling.")));


        options.add(new MenuItem("SNS User Check-in (Arrival register) ",
                               new RegisterArrivalOfSnsUserToTakeVaccineUI(vaccinationCenterId)));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n# # Receptionist Menu: # #");

            if((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while(option != -1);

        //logout if "0-Cancel"
        app.doLogout();
    }

}
