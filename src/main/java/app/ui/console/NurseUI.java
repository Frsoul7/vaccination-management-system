package app.ui.console;

import app.controller.App;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable {

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;

    /**
     * Instance of the app
     */
    private final App app;

    /***
     * NurseUI constructor
     * @param vaccinationCenterId
     */
    public NurseUI(int vaccinationCenterId) {
        app = App.getInstance();
        this.vaccinationCenterId = vaccinationCenterId;
    }

    /***
     * Method to run the app and show the Nurse menu list with options
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Consult waiting room list for SNS Users ",
                                 new ConsultSnsUsersInWaitingRoomUI(vaccinationCenterId)));
        options.add(new MenuItem("Record SNS User administration of a vaccine ",
                                 new RecordAdministrationOfAVaccineToSnsUserUI(vaccinationCenterId)));
//        options.add(new MenuItem("Record SNS User adverse reactions ",
//                                 new ShowTextUI("You have chosen SNS User record adverse reactions")));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n# # Nurse Menu: # #");

            if((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while(option != -1);

        //logout if "0-Cancel"
        app.doLogout();

    }
}
