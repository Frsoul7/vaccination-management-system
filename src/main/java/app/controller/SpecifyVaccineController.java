package app.controller;

import app.domain.model.AdministrationProcess;
import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import app.ui.console.utils.Utils;

import java.util.List;

/**
 * @authors Fernando Ribeiro <1060064@isep.ipp.pt> José Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */
public class SpecifyVaccineController implements Constants {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of vaccine store
     */
    private final VaccineStore vStore;

    /**
     * Instance of vaccine
     */
    private Vaccine vac;

    /**
     * Instance of administration process
     */
    private AdministrationProcess admProc;

    /**
     * Instance of vaccine type store
     */
    private final VaccineTypeStore vTStore;


    private List<VaccineType> lVacType;


    public SpecifyVaccineController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.vTStore = company.getVaccineTypeStore();
        this.vStore = company.getVaccineStore();
    }


    public Vaccine getVac(){return new Vaccine(vac);}


    public boolean specifyVaccine(String name, String lotNumber, int id, String brand, String vaccineType,
                                  String ageGroup, int numberDoses, int vaccineDosage) {
        try {
            this.vac = vStore.specifyVaccine(name, lotNumber, id, brand, vaccineType, ageGroup, numberDoses,
                                             vaccineDosage);
            return true;
        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }


    public boolean specifyAdministrationProcess(String ageGroup, int numberOfDoses, int[] vaccineDosage,
                                                int[] timeIntervalBetweenDoses) {
        try {
            this.admProc =
                    vac.specifyAdministrationProcess(ageGroup, numberOfDoses, vaccineDosage, timeIntervalBetweenDoses,
                                                     vac.getAgeGroup());
            return true;
        }
        catch(RuntimeException|OperationCanceledByUserException ex) {
            Utils.showText(ex.getMessage());
            return false;
        }
    }

    public List<VaccineType> getVaccinesTypes() {
        return lVacType = vTStore.getVaccineTypesList();
    }

    public boolean saveVaccine() {
        return this.vStore.saveVaccine(this.vac);
    }

    public boolean saveAdministrationProcess() {
        return this.vac.saveAdministrationProcess(this.admProc);
    }

    public boolean deleteVaccine() {
        return this.vStore.deleteVaccine(vac);
    }

    /**
     * Method to get vaccine information
     *
     * @return String
     */
    public String vaccineInformation() {
        return this.vac.toString();
    }


    /**
     * Method to get vaccine administration information
     *
     * @return String
     */
    public String vaccineAdministrationProcessInformation() {
        return this.admProc.toString();
    }

    /**
     * Method to get administration process list information
     *
     * @return String
     */
    public List<AdministrationProcess> getVaccineAdministrationProcessList() {
        return this.vac.getAdmProcList();
    }

}
