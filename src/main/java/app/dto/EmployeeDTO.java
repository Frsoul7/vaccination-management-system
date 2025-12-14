package app.dto;

import app.domain.shared.EmployeeRoles;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

public class EmployeeDTO {
    /***
     * Employee id
     */
    private long id;
    /**
     * Employee name
     */
    private String name;

    /**
     * Employee street address
     */
    private String address;

    /**
     * Employee phone number
     */
    private long phoneNumber;

    /***
     * Employee email
     */
    private String email;

    /**
     * Citizen Card Number
     */
    private long citizenCardNumber;

    /***
     * Employee Role
     */
    private EmployeeRoles employeeRole;

    /**
     * EmployeeDTO constructor
     *
     * @param id
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param citizenCardNumber
     * @param employeeRole
     */
    @ExcludeFromJacocoGeneratedReport
    public EmployeeDTO(long id, String name, String address, long phoneNumber, String email, long citizenCardNumber,
                       EmployeeRoles employeeRole) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
        this.employeeRole = employeeRole;
    }

    /***
     * Get ID of the employee
     * @return ID of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public long getId() {
        return id;
    }

    /***
     * Get Name of employee
     * @return Name of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public String getName() {
        return name;
    }

    /**
     * Get Address of the employee
     *
     * @return Address of the employee
     */
    @ExcludeFromJacocoGeneratedReport
    public String getAddress() {
        return address;
    }

    /***
     * Get Phone number of employee
     * @return Phone number of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /***
     * Get Email of employee
     * @return Email of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public String getEmail() {
        return email;
    }

    /***
     * Get Citizen card number of employee
     * @return Citizen card number of employee
     */
    @ExcludeFromJacocoGeneratedReport
    public long getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /***
     * Get role of the employee
     * @return Role of the employee
     */
    @ExcludeFromJacocoGeneratedReport
    public EmployeeRoles getEmployeeRole() {
        return employeeRole;
    }

    /**
     * Gives back the Person's text description
     *
     * @return full description of the Person
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return String.format("Id: %010d | Name: %s | Street address: %s | Phone number: %d | Email: %s | Citizen " +
                             "card number: %d | Role: %s%n", this.getId(), this.getName(), this.getAddress(),
                             this.getPhoneNumber(), this.getEmail(), this.getCitizenCardNumber(),
                             this.getEmployeeRole().getDescription());
    }
}
