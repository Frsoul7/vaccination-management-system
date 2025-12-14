package app.domain.model.store;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.PasswordGenerator;
import app.domain.model.SNSUser;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Notifications;
import app.domain.shared.EmployeeRoles;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt> Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José
 * Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */

public class EmployeeStore implements Constants, Serializable {

    /***
     * Instance of the List of employees
     */
    private List<Employee> employeeList;


    /***
     * Constructor initializing instances of new lists of employees
     * @param
     */

    public EmployeeStore() {
        this.employeeList = new ArrayList<>();
    }


    public EmployeeStore(EmployeeStore employeeStore) {
        this.employeeList = employeeStore.getEmployeesList();
    }

    /***
     * Method registerEmployee used to register employee
     * @param name              - name of the employee
     * @param address           - address of the employee
     * @param phoneNumber       - phone number of the employee
     * @param email             - email address of the employee
     * @param citizenCardNumber - citizen card number of the employee
     * @param employeeRole      - employee role
     * @return a new employee created
     */
    public Employee registerEmployee(String name, String address, long phoneNumber, String email,
                                     long citizenCardNumber, EmployeeRoles employeeRole)
            throws OperationCanceledByUserException {
        return new Employee(name, address, phoneNumber, email, citizenCardNumber, employeeRole);
    }

    /**
     * Method used to add an employee
     *
     * @param employee - Employee Object
     *
     * @return boolean (true if the employee has been created and, false if not)
     */

    public boolean saveEmployee(Employee employee, AuthFacade authFacade) {
        if(!authFacade.existsUser(employee.getEmail()) && !this.validateEmployee(employee)) {
            try {
                String pw = PasswordGenerator.getPassword();
                Notifications.sendEmployeePassword(employee.getName(), employee.getEmail(), pw,
                                                   employee.getEmployeeRole().getRoleId());
                if(authFacade.addUserWithRole(employee.getName(), employee.getEmail(), pw,
                                              employee.getEmployeeRole().getRoleId())) {
                    employee.setId();
                    return addEmployee(employee);
                } else {
                    return false;
                }
            }
            catch(RuntimeException|IOException ex) {
                return false;
            }
        }
        return false;
    }

    /***
     * Method used to add an employee to the employee's list
     * @param employee      - Employee Object
     * @return boolean (true if has been added, false if not)
     */
    public boolean addEmployee(Employee employee) {
        return employeeList.add(employee);
    }


    /***
     * Get the employee final list
     * @return the employee list
     */
    @ExcludeFromJacocoGeneratedReport
    public List<Employee> getEmployeesList() {
        return new ArrayList<>(employeeList);
    }

    /***
     * Method isEmployeeAlreadyExist checks the existence of a specific employee in an employee's list
     * @param employee - Employee object
     * @return true is a certain unique attribute is found in an existent employee, false if not
     */
    public boolean validateEmployee(Employee employee) {
        try {
            if(employee == null) {
                return true;
            }
            List<Employee> listEmployee = this.getEmployeesList();
            if(!listEmployee.isEmpty()) {
                for(Employee emp : listEmployee) {
                    if(employee.equalsUniqueAttributes(emp)) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch(NullPointerException e) {
            return true;
        }
    }


    public boolean addEmployees(EmployeeStore employeeStore) throws OperationCanceledByUserException {
        if(!employeeStore.getEmployeesList().isEmpty()) {
            for(Employee empl : employeeStore.employeeList) {
                new Employee(empl);
            }
            return true;
        }
        return false;
    }


    /***
     * Get the employee by its email
     * @param email         - email of the employee
     * @return the employee if true, null if false
     */
    @ExcludeFromJacocoGeneratedReport

    //todo:observar este fluxo
    public Employee getEmployeeByEmail(String email) throws OperationCanceledByUserException {
        List<Employee> listEmpl = this.getEmployeesList();
        for(Employee obj : listEmpl) {
            if(obj.getEmail().equalsIgnoreCase(email)) {
                return new Employee(obj.getName(), obj.getAddress(), obj.getPhoneNumber(), obj.getEmail(),
                                    obj.getCitizenCardNumber(), obj.getEmployeeRole());
            }
        }
        return null;
    }


    /***
     * Get the employee list by the role of the employee
     * @param emplRoleStringSelected        - employee role
     * @return the employee list by the roles of the employees
     */


    //todo:observar este fluxo
    public List<Employee> getEmployeesListByRole(String emplRoleStringSelected) {
        List<Employee> lEmplByRole = new ArrayList<>();
        List<Employee> listEmp = employeeList;

        for(Employee empl : listEmp) {
            if(empl.getEmployeeRole().getDescription().equals(emplRoleStringSelected)) {
                lEmplByRole.add(empl);
            }
        }
        return lEmplByRole;
    }


}
