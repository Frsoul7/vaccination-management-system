package app.controller;

import app.domain.model.Company;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.*;
import app.domain.serializationFiles.FileReadAndSave;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SerializationController implements Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    private FileReadAndSave fileReadAndSave;

    public SerializationController() {
        this.app = App.getInstance();
        this.company = app.getCompany();

        fileReadAndSave = new FileReadAndSave();
    }

    public void readFiles() {
        try {

            EmployeeStore employeeStore = fileReadAndSave.readFile(FILE_NAME_EMP);
            SNSUserStore snsUserStore = fileReadAndSave.readFile(FILE_NAME_SNSUSER);
            VaccineTypeStore vaccineTypeStore = fileReadAndSave.readFile(FILE_NAME_VACCINE_TYPE);
            VaccineStore vaccineStore = fileReadAndSave.readFile(FILE_NAME_VACCINE);
            VaccinationCenterStore vaccinationCenterStore = fileReadAndSave.readFile(FILE_NAME_VACCINATION_CENTER);
            List<String> snsUsEmailPw = fileReadAndSave.readFile(FILE_NAME_SNS_US_EMAIL_PW);
            List<String> emplEmailPw = fileReadAndSave.readFile(FILE_NAME_EMPLOYEE_EMAIL_PW);

            if(employeeStore != null) {
                company.setEmployeeStore(employeeStore);
                company.getEmployeeStore().addEmployees(employeeStore);
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_EMP) ));
            }

            if(snsUserStore != null) {
                company.setSnsUserStore(snsUserStore);
                company.getSnsUserStore().addSnsUsersAndAllOtherObjects(snsUserStore);
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_SNSUSER)));
            }

            if(vaccineTypeStore != null) {
                company.setVaccineTypeStore(vaccineTypeStore);
                company.getVaccineTypeStore().addVaccineType(vaccineTypeStore);
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_VACCINE_TYPE)));
            }

            if(vaccineStore != null) {
                company.setVaccineStore(vaccineStore);
                company.getVaccineStore().addVaccinesAndAdm(vaccineStore);
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_VACCINE)));
            }
            if(vaccinationCenterStore != null) {
                company.setVaccinationCenterStore(vaccinationCenterStore);
                company.getVaccinationCenterStore()
                       .addVaccinationCentersAndAllObjectsAssociated(vaccinationCenterStore);
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_VACCINATION_CENTER)));
            }

            if(snsUsEmailPw != null && !snsUsEmailPw.isEmpty()) {
                AuthFacade aut = company.getAuthFacade();
                for(int i = 2; i < snsUsEmailPw.size(); i = i + 3) {
                    aut.addUserWithRole(snsUsEmailPw.get(i - 2), snsUsEmailPw.get(i - 1), snsUsEmailPw.get(i),
                                        ROLE_SNSUSER);
                }
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_SNS_US_EMAIL_PW)) );
            }

            if(emplEmailPw != null && !emplEmailPw.isEmpty()) {
                AuthFacade aut = company.getAuthFacade();
                for(int i = 3; i < emplEmailPw.size(); i = i + 4) {
                    aut.addUserWithRole(emplEmailPw.get(i - 3), emplEmailPw.get(i - 2), emplEmailPw.get(i - 1),
                                        emplEmailPw.get(i));
                }
                Utils.showText(String.format("%s loaded with success", getFileName(FILE_NAME_EMPLOYEE_EMAIL_PW)));
            }
            
            // After loading all serialized data, ensure bootstrap vaccination centers exist
            app.ensureBootstrapData();

        }
        catch(OperationCanceledByUserException|RuntimeException e) {
            Utils.showText(e.getMessage());
        }
    }


    public <T> boolean saveFile(String fileName, T store) {
        return fileReadAndSave.saveFile(fileName, store);
    }

    public <T> void saveData(String fileName, T store, String information) {
        if(this.saveFile(fileName, store)) {
            Utils.showText(String.format("%s information saved with success", information));
        } else {
            Utils.showText(String.format("Fails saving %s information", information));
        }
    }

    public void saveDataEmployeesSnsUserAuthFacade(String fileName, String information) {
        File file = new File(information + ".txt");
        List<String> emailPass = new ArrayList<>();

        if(file.length() != 0 || file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] lineParts = line.split(" ");
                    emailPass.add(lineParts[1]);
                }
                sc.close();
            }
            catch(IOException e) {
                Utils.showText(String.format("Error reading file %s.txt", information));
            }
            saveData(fileName, emailPass, information);
        }
    }


    public String getFileName(String path) {
        String[] aux = path.split("\\\\");
        return aux[aux.length - 1];
    }
}
