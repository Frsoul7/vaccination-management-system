package app.domain.model;

import app.controller.SerializationController;
import app.domain.model.store.*;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Paulo Maio <pam@isep.ipp.pt> Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José
 * Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */

public class Company {

    /***
     * Designation of the company
     */
    private final String designation;

    /***
     * Instance of the Authface
     */
    private AuthFacade authFacade;

    // Stores
    /***
     * Store that contains all the SNS users
     */
    private SNSUserStore snsuserStore;
    /***
     * Store that contains all the Employees
     */
    private EmployeeStore employeeStore;
    /***
     * Store that contains all the Vaccine types
     */
    private VaccineTypeStore vaccineTypeStore;
    /***
     * Store that contains all the Vaccination centers
     */
    private VaccinationCenterStore vcStore;
    /***
     * Store that contains all the Vaccines
     */
    private VaccineStore vStore;


    private SerializationController serializationController;

    /***
     * Defines a designation for the company
     * @param designation - designation of the company
     */
    @ExcludeFromJacocoGeneratedReport
    public Company(String designation) {
        if(StringUtils.isBlank(designation)) {
            throw new IllegalArgumentException("Designation cannot be blank.");
        }
        this.authFacade = new AuthFacade();
        this.designation = designation;

        this.snsuserStore = new SNSUserStore();
        this.employeeStore = new EmployeeStore();
        this.vaccineTypeStore = new VaccineTypeStore();
        this.vcStore = new VaccinationCenterStore();
        this.vStore = new VaccineStore();


        //delete information in existing file because of append
        //uncomment to delete file content but data in authfacade serialization will be lost
        /*
        try {
            FileWriter fileOutput = new FileWriter("SnsUsersEmailsAndPasswords.txt", false);
            fileOutput.close();
            FileWriter fileOutput2 = new FileWriter("EmployeeEmailsAndPasswords.txt", false);
            fileOutput2.close();
            FileWriter fileOutput3 = new FileWriter("SchedulerNotification.txt", false);
            fileOutput3.close();
        }
        catch(IOException e) {
            Utils.showText(e.getMessage());
        }*/
    }

    /***
     * Get the designation of the company
     * @return designation of the company
     */
    @ExcludeFromJacocoGeneratedReport
    public String getDesignation() {
        return designation;
    }

    @ExcludeFromJacocoGeneratedReport
    public SerializationController getSerializationController() {
        return serializationController;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setSerializationController() {
        this.serializationController = new SerializationController();
    }

    /**
     * Get the authFacade
     *
     * @return the authFacade session
     */
    @ExcludeFromJacocoGeneratedReport
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /***
     * Get the employee store
     * @return the employee store
     */
    @ExcludeFromJacocoGeneratedReport
    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    /***
     * Set the employee store
     *
     */
    @ExcludeFromJacocoGeneratedReport
    public void setEmployeeStore(EmployeeStore employeeStore) {
        this.employeeStore = new EmployeeStore(employeeStore);
    }

    /***
     * Get the vaccine type store
     * @return the vaccine type store
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineTypeStore getVaccineTypeStore() {
        return this.vaccineTypeStore;
    }

    /***
     * Set the vaccine type store
     *
     */
    @ExcludeFromJacocoGeneratedReport
    public void setVaccineTypeStore(VaccineTypeStore vaccineTypeStore) {
        this.vaccineTypeStore = new VaccineTypeStore(vaccineTypeStore);
    }

    /***
     * Get the vaccine store
     * @return the vaccine store
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccineStore getVaccineStore() {
        return this.vStore;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setVaccineStore(VaccineStore vaccineStore) {
        this.vStore = new VaccineStore(vaccineStore);
    }

    /***
     * Get the Sns User store
     * @return the Sns user store
     */
    @ExcludeFromJacocoGeneratedReport
    public SNSUserStore getSnsUserStore() {
        return this.snsuserStore;
    }

    /***
     * Set the Sns User store
     *
     */
    @ExcludeFromJacocoGeneratedReport
    public void setSnsUserStore(SNSUserStore snsUserStore) {
        this.snsuserStore = new SNSUserStore(snsUserStore);
    }

    /***
     * Get the vaccination center store
     * @return the vaccination center store
     */
    @ExcludeFromJacocoGeneratedReport
    public VaccinationCenterStore getVaccinationCenterStore() {
        return this.vcStore;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setVaccinationCenterStore(VaccinationCenterStore vaccinationCenterStore) {
        this.vcStore = new VaccinationCenterStore(vaccinationCenterStore);
    }


}
