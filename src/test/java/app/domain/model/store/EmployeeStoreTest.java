
package app.domain.model.store;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.shared.EmployeeRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStoreTest {

    private String name;
    private String address;
    private long phoneNumber;
    private long phoneNumberInvalid;
    private String email;
    private long citizenCardNumber;
    private EmployeeRoles empRole;
    private EmployeeStore empStore;



    @BeforeEach
    void setUp() {
        name = "Nome Novo Employee";
        address = "Nova Moradas, 123";
        phoneNumber = 768933111;
        phoneNumberInvalid = 768933;
        email = "novoEmail@new.pt";
        citizenCardNumber = 17423858;
        empRole = EmployeeRoles.ROLE_ADMIN;
        empStore = new EmployeeStore();
    }


    @Test
    void registerEmployee_OK() {
        try {
            Employee emp = empStore.registerEmployee(name, address, phoneNumber, email, citizenCardNumber, empRole);
        }
        catch(OperationCanceledByUserException|IllegalArgumentException e) {
            fail();
        }

    }

    @Test
    void registerEmployee_NOKInvalidPhoneNumber() {
        try {
            Employee emp = empStore.registerEmployee(name, address, phoneNumberInvalid, email, citizenCardNumber, empRole);
            fail();
        }
        catch(OperationCanceledByUserException e) {
            fail();
        } catch(IllegalArgumentException i){
            assertEquals(i.getMessage(), "The phone number is not valid!");
        }
    }

}