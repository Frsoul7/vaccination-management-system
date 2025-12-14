package app.controller;

import app.dto.EmployeeDTO;
import app.mappers.EmployeeMapper;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.store.EmployeeStore;

import java.util.List;

public class GetEmployeeListController {
    /**
     * Instance of the app
     */
    private final App app;

    /**
     * Instance of the company
     */
    private final Company company;

    /**
     * Instance of employee store
     */
    private EmployeeStore eStore;

    /**
     * Instance of employee mapper
     */
    private final EmployeeMapper emplMapper;

    /**
     * GetEmployeeListController constructor with default values
     */
    public GetEmployeeListController() {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.emplMapper = new EmployeeMapper();
    }

    /**
     * Method used to get the list of Employees by role with DTO
     *
     * @param emplRoleStringSelected
     *
     * @return
     */
    public List<EmployeeDTO> getEmployeesListByRole(String emplRoleStringSelected) {
        eStore = company.getEmployeeStore();
        List<Employee> listEmployee = eStore.getEmployeesListByRole(emplRoleStringSelected);
        return emplMapper.toDTO(listEmployee);
    }
}
