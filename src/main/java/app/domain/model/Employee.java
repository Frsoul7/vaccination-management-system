package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.utils.Validations;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import app.domain.shared.EmployeeRoles;

import java.io.Serializable;


/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public class Employee extends Person {

    /***
     * Employee id
     */
    private long id;

    /***
     * Employee Role
     */
    private EmployeeRoles employeeRole;

    /***
     * Total of employees instantiated
     */
    private static long totalEmployees = 0;


    /***
     * Employee constructor with all attributes
     *
     * @param name              String - name of the employee
     * @param address           String - address of the employee
     * @param phoneNumber       long - phone number of the employee
     * @param email             String - email of the employee
     * @param citizenCardNumber String - Citizen card number
     * @param employeeRole      Role - Role of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public Employee(String name, String address, long phoneNumber, String email, long citizenCardNumber,
                    EmployeeRoles employeeRole) throws OperationCanceledByUserException {
        super(name, address, phoneNumber, email, citizenCardNumber);
        setEmployeeRole(employeeRole);
    }

    @ExcludeFromJacocoGeneratedReport
    public Employee(Employee otherEmpl) throws OperationCanceledByUserException {
        super(otherEmpl.getName(), otherEmpl.getAddress(), otherEmpl.getPhoneNumber(), otherEmpl.getEmail(),
              otherEmpl.getCitizenCardNumber());
        this.employeeRole = otherEmpl.getEmployeeRole();
        this.id = otherEmpl.getId();
        if(totalEmployees < this.id) {
            totalEmployees = id;
        }
    }

    /***
     * Get ID of the employee
     * @return ID of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public long getId() {
        return this.id;
    }

    /***
     * Get role of the employee
     * @return Role of the employee
     */
    @ExcludeFromJacocoGeneratedReport
    public EmployeeRoles getEmployeeRole() {
        return this.employeeRole;
    }

    /***
     * private method to define Employee ID
     */
    @ExcludeFromJacocoGeneratedReport
    public void setId() {
        if(this.id == 0) {
            totalEmployees++;
            if(Validations.isEmployeeIdValid(totalEmployees)) {
                this.id = totalEmployees;
            } else {
                throw new IllegalArgumentException("The id is not valid!");
            }
        }
    }

    /***
     * Set Role of the Employee
     * @param employeeRole - Role of the Employee
     */
    @ExcludeFromJacocoGeneratedReport
    public void setEmployeeRole(EmployeeRoles employeeRole) {
        if(Validations.isEmployeeRoleValid(employeeRole)) {
            this.employeeRole = employeeRole;
        } else {
            throw new IllegalArgumentException("The role is not valid!");
        }

    }

    /***
     * Gives back the employee partial description
     * @return Partial description of the employee
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("%nID: %010d%sRole: %s%n", this.getId(), super.toString(),
                             this.getEmployeeRole().getDescription());
    }

    /***
     * Compare the Employee with the received object
     *
     * @param otherObject the object to compare with Employee
     * @return true if the received object represents "otherEmployee" like the Employee itself, otherwise returns false
     */
    @Override
    public boolean equals(Object otherObject) {
        if(!super.equals(otherObject)) {
            return false;
        }
        Employee otherEmployee = (Employee)otherObject;
        return id == otherEmployee.id && employeeRole.equals(otherEmployee.employeeRole);
    }

    /***
     * Get total number of  Employees created until the current moment
     * @return Total of employees created
     */
    @ExcludeFromJacocoGeneratedReport
    public static long getTotalEmployees() {
        return totalEmployees;
    }


}
