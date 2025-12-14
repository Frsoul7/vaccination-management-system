package app.ui.console;

import app.controller.App;
import app.controller.SerializationController;
import app.domain.model.Company;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

public class SaveDataUi implements Runnable, Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    private final SerializationController serCont;

    public SaveDataUi() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.serCont = company.getSerializationController();
    }

    public void run() {

        //Save data
        if(Utils.confirm("Do you want to save all data? (Y/N): ")) {

            serCont.saveData(FILE_NAME_EMP, company.getEmployeeStore(), "Employees");
            serCont.saveData(FILE_NAME_SNSUSER, company.getSnsUserStore(), "Sns Users");
            serCont.saveData(FILE_NAME_VACCINE_TYPE, company.getVaccineTypeStore(), "Vaccine Type");
            serCont.saveData(FILE_NAME_VACCINE, company.getVaccineStore(), "Vaccine");
            serCont.saveData(FILE_NAME_VACCINATION_CENTER, company.getVaccinationCenterStore(), "Vaccination Centers");
            serCont.saveDataEmployeesSnsUserAuthFacade(FILE_NAME_SNS_US_EMAIL_PW, "SnsUsersEmailsAndPasswords");
            serCont.saveDataEmployeesSnsUserAuthFacade(FILE_NAME_EMPLOYEE_EMAIL_PW, "EmployeeEmailsAndPasswords");

        } else {
            Utils.showText("Data not saved!");
        }
    }

}
