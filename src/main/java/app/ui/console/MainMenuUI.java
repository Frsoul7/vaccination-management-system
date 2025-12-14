package app.ui.console;

import app.controller.App;
import app.controller.SerializationController;
import app.domain.model.Company;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/***
 * @author Paulo Maio <pam@isep.ipp.pt>
 *         Edgar Moreira <1010100@isep.ipp.pt>
 *         Fernando Ribeiro <1060064@isep.ipp.pt>
 *         José Silva <1060568@isep.ipp.pt>
 *         Pedro Gomes <1060588@isep.ipp.pt>
 */

public class MainMenuUI implements Constants {
    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;


    public MainMenuUI() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        company.setSerializationController();
    }

    @ExcludeFromJacocoGeneratedReport
    public void run() throws IOException {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Load Data", new LoadDataUi()));
        options.add(new MenuItem("Save Data", new SaveDataUi()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\nWELCOME TO THE COVID MANAGEMENT APP by WAGMI\n\n##### MAIN " +
                                                       "MENU #####");

            if((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while(option != -1);



    }


}
