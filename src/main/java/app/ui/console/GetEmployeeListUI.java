package app.ui.console;

import app.controller.GetEmployeeListController;
import app.dto.EmployeeDTO;
import app.domain.shared.EmployeeRoles;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GetEmployeeListUI implements Runnable {

    /**
     * Get employee list controller instance
     */
    private final GetEmployeeListController controller;

    /**
     * Get employee list UI constructor
     */
    public GetEmployeeListUI() {
        controller = new GetEmployeeListController();
    }


    /**
     * Method used to run the get employee list UI
     */
    public void run() {
        Utils.showText("\n##### Please select the employee role #####");

        if(selectAndShowData()) {
            Utils.showText("\ninfo: the list was shown with success");
        }else{
            Utils.showText("\n[warning] operation failed");
        }
    }

    /**
     * Method used to read the input from the user
     *
     * @return true if all the data is filled correctly
     */
    private boolean selectAndShowData() {
        int option = 0;

        String emplRoleStringSelected = "";

        List<String> options = new ArrayList<String>();

        for(EmployeeRoles opt : EmployeeRoles.values()) {
            options.add(opt.getDescription());
        }

        do {
            option = Utils.showAndSelectIndex(options, "\nList of Employee Roles:");

            if((option >= 0) && (option < options.size())) {
                emplRoleStringSelected = options.get(option);
                option = -1;
            }
        } while(option != -1);

        Utils.showText("\nSelected employee role: " + emplRoleStringSelected);


        List<EmployeeDTO> lEmployeesDTO = controller.getEmployeesListByRole(emplRoleStringSelected);
        if(!lEmployeesDTO.isEmpty()){
            for(EmployeeDTO emplDto:lEmployeesDTO){
                System.out.println(emplDto.toString());
            }
            return true;
        }else{
            Utils.showText("\ninfo: list is empty");
            return false;
        }
    }
}
