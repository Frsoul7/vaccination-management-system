package app.domain.model;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.shared.EmployeeRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private static Employee employee;
    private static Employee employee2;

    @BeforeEach
    void setUp() throws OperationCanceledByUserException {
        String name = "Employee to test";
        String name2 = "Employee to test two";
        String address = "Address to test";
        String address2 = "Address to test two";
        long phoneNumber = 965493674;
        long phoneNumber2 = 867304393;
        String email = "emailToTes@fds.pt";
        String email2 = "emailToTes2@fds.pt";
        long citizenCardNumber = 27835928;
        long citizenCardNumber2 = 86780342;
        EmployeeRoles role = EmployeeRoles.ROLE_NURSE;
        employee = new Employee(name, address, phoneNumber, email, citizenCardNumber, role);
        employee2 = new Employee(name2, address2, phoneNumber2, email2, citizenCardNumber2, role);
    }

    @Test
    void testEquals_SameObject() {
        Employee employeeToTest = employee;
        assertTrue(employee.equals(employeeToTest));
    }

    @Test
    void testEquals_Null() {
        Employee employeeToTest = null;
        assertFalse(employee.equals(employeeToTest));
    }

    @Test
    void testEquals_DifferentType() {
        String employeeToTest = "new";
        assertFalse(employee.equals(employeeToTest));
    }

    @Test
    void testEquals_SameValues() throws OperationCanceledByUserException {
        Employee employeeToTest = new Employee(employee);
        assertTrue(employee.equals(employeeToTest));
    }

    @Test
    void testEquals_DifferentValues() throws OperationCanceledByUserException {
        Employee employeeToTest = new Employee(employee2);
        assertFalse(employee.equals(employeeToTest));
    }

}