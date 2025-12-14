package app.ui.console;

import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/***
 * @author Paulo Maio <pam@isep.ipp.pt>
 *         Edgar Moreira <1010100@isep.ipp.pt>
 *         Fernando Ribeiro <1060064@isep.ipp.pt>
 *         José Silva <1060568@isep.ipp.pt>
 *         Pedro Gomes <1060588@isep.ipp.pt>
 */

public class AdminUI implements Runnable {

    /***
     * Instance of Administrator UI
     */
    public AdminUI() {
    }

    /***
     * Method to run the app and show the Administrator menu list with options
     */
    @ExcludeFromJacocoGeneratedReport
    public void run() {
        List<MenuItem> options = new ArrayList<>();

        options.add(new MenuItem("Register an Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Register a Vaccination Center", new RegisterVaccinationCenterUI()));
        options.add(new MenuItem("Register a Vaccine's Type", new SpecifyVaccineTypeUI()));
        options.add(new MenuItem("Register a Vaccine", new SpecifyVaccineUI()));
        options.add(new MenuItem("Get the List of Employees", new GetEmployeeListUI()));
        options.add(new MenuItem("Import SNS Users from CSV", new ImportCSVSNSUserUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n##### ADMINISTRATOR MENU #####");

            if((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while(option != -1);
    }
}
