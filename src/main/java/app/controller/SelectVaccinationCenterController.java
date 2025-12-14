package app.controller;

import app.dto.VaccinationCenterDTO;
import app.mappers.VaccinationCenterMapper;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import pt.isep.lei.esoft.auth.UserSession;

import java.util.List;

public class SelectVaccinationCenterController {

    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;
    /**
     * Instance of vaccination center mapper
     */
    private final VaccinationCenterMapper vcMapper;
    /**
     * Instance of vaccination center store
     */
    private VaccinationCenterStore vcStore;
    /**
     * Instance of Employee
     */
    private Employee empl;

    /**
     * Instance of Employee store
     */
    private EmployeeStore eStore;

    /**
     * Instance of User Session
     */
    private UserSession uSession;


    /**
     * Empty SelectVaccinationCenter constructor with default values
     */
    public SelectVaccinationCenterController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.vcMapper = new VaccinationCenterMapper();

    }

    /**
     * xx
     *
     * @return
     */
    public List<VaccinationCenterDTO> getVaccinationCenters() {
        vcStore = company.getVaccinationCenterStore();
        List<VaccinationCenter> lVaccinationCenters = vcStore.getVaccinationCenterList();
        return vcMapper.toDTO(lVaccinationCenters);
    }


}
