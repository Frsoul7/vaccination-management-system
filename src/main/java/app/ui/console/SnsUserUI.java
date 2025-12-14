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

public class SnsUserUI implements Runnable {

    /***
     * Instance of SNS User UI
     */
    @ExcludeFromJacocoGeneratedReport
    public SnsUserUI() {
    }

    /***
     * Method to run the app and show the SNS User menu list with options
     */
    @ExcludeFromJacocoGeneratedReport
    @Override
    public void run() {


        List<MenuItem> options = new ArrayList<>();

        options.add(new MenuItem("Schedule a new vaccine", new ScheduleAVaccineUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n# # Sns User Menu: # #");

            if((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while(option != -1);
    }

}
