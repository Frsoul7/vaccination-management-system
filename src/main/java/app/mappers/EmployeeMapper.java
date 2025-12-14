package app.mappers;

import app.dto.EmployeeDTO;
import app.domain.model.Employee;
import app.domain.shared.EmployeeRoles;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    /**
     * Method to protect List<Employee> listEmployee with DTO
     *
     * @param listEmployee
     *
     * @return
     */
    public List<EmployeeDTO> toDTO(List<Employee> listEmployee) {

        List<EmployeeDTO> listEmployeeDTO = new ArrayList<>();

        for(Employee obj : listEmployee) {
            long id = obj.getId();
            String name = obj.getName();
            String address = obj.getAddress();
            long phoneNumber = obj.getPhoneNumber();
            String email = obj.getEmail();
            long citizenCardNumber = obj.getCitizenCardNumber();
            EmployeeRoles employeeRole = obj.getEmployeeRole();

            listEmployeeDTO.add(
                    new EmployeeDTO(id, name, address, phoneNumber, email, citizenCardNumber, employeeRole));
        }
        return listEmployeeDTO;
    }
}
