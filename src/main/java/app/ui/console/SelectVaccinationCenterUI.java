package app.ui.console;

import app.controller.App;
import app.controller.SelectVaccinationCenterController;
import app.dto.VaccinationCenterDTO;
import app.domain.shared.EmployeeRoles;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class SelectVaccinationCenterUI implements Runnable {

    /***
     * Employee role
     */
    private final EmployeeRoles role;

    /***
     * Vaccination Center Id attribute
     */
    private int vaccinationCenterId;

    /***
     * Vaccination Center Id attribute by default
     */
    private final int VACCINATION_CENTER_ID_BY_DEFAULT = 0;

    /**
     * Instance of the app
     */
    private final App app;

    /***
     * Select Vaccination Center Controller instance
     */
    private final SelectVaccinationCenterController controller;


    /***
     * Select Vaccination Center UI constructor
     * @param role
     */
    public SelectVaccinationCenterUI(EmployeeRoles role) {
        controller = new SelectVaccinationCenterController();
        this.role = role;
        vaccinationCenterId = VACCINATION_CENTER_ID_BY_DEFAULT;
        app = App.getInstance();
    }

    /***
     * Method used to run the Select Vaccination Center UI
     */
    public void run() {
        Utils.showText("\n# # Please, select your Vaccination Center # #");

        if(selectData()) {
            Utils.showText("\nInfo: Vaccination Center was selected with success");

            if(role.equals(EmployeeRoles.ROLE_NURSE)) {
                new NurseUI(vaccinationCenterId).run();
            } else if(role.equals(EmployeeRoles.ROLE_RECEPTIONIST)) {
                new ReceptionistUI(vaccinationCenterId).run();
            } else if(role.equals(EmployeeRoles.ROLE_CENTERCOORNIDATOR)) {
                new CenterCoordinatorUI(vaccinationCenterId).run();
            }
        } else {
            Utils.showText("[warning] Vaccination Center was not properly selected, please try again!");
        }
    }

    /***
     * Method used to show the Vaccination Centers and pick one
     * @return selection of one vaccination center
     */
    private boolean selectData() {

        String vaccinationCenterNameSelected = "";

        List<String> options = new ArrayList<>();
        List<VaccinationCenterDTO> lVaccinationCenters = controller.getVaccinationCenters();
        if(!lVaccinationCenters.isEmpty()) {
            for(VaccinationCenterDTO vcDTO : lVaccinationCenters) {
                options.add(vcDTO.toString());
            }

            int optionSelected;
            do {
                optionSelected = Utils.showAndSelectIndex(options, "\nList of Vaccination Centers:");
                if(optionSelected == -1) {
                    //logout if "0-Cancel"
                    app.doLogout();
                    return false;
                }
                if((optionSelected >= 0) && (optionSelected < options.size())) {

                    vaccinationCenterNameSelected = options.get(optionSelected);
                    vaccinationCenterId = lVaccinationCenters.get(optionSelected).getVaccinationCenterId();
                    optionSelected = -1;
                }
            } while(optionSelected != -1);

            Utils.showText("\nSelected vaccination Center: " + vaccinationCenterNameSelected);

            return true;
        }
        Utils.showText("[warning] Vaccination center list is empty, please contact your Administrator!");
        return false;
    }

}
