package app.domain.shared;

import app.interfaces.ExcludeFromJacocoGeneratedReport;

/**
 * @authors Pedro Gomes <1060588@isep.ipp.pt>
 */
@ExcludeFromJacocoGeneratedReport
public enum EmployeeRoles {

    ROLE_ADMIN("ADMINISTRATOR"), ROLE_NURSE("NURSE"), ROLE_RECEPTIONIST("RECEPTIONIST"),
    ROLE_CENTERCOORNIDATOR("CENTER COORDINATOR"), ROLE_DEFAULT("#role not assigned#");

    private final String description;

    EmployeeRoles(String description) {
        this.description = description;
    }


    public String getDescription() {
        return this.description;
    }

    public String getRoleId() {
        return name();
    }

    /**
     * Obtain Role ID of a Role Description
     *
     * @param roleDescription - Role Description wanted
     *
     * @return Role ID related
     */
    public static EmployeeRoles getRoleIdFromDescription(String roleDescription) {
        for(EmployeeRoles r : EmployeeRoles.values()) {
            if(r.getDescription().equalsIgnoreCase(roleDescription)) {
                return r;
            }
        }
        return EmployeeRoles.ROLE_DEFAULT;
    }

    /**
     * Validate if role is valid
     *
     * @param roleDescription - Role to validate
     *
     * @return true if valid, false if not
     */

    public static boolean isRoleDescriptionValid(String roleDescription) {
        if(roleDescription == null) {
            return false;
        }

        for(EmployeeRoles r : EmployeeRoles.values()) {
            if(r.getDescription().equalsIgnoreCase(roleDescription)) {
                return true;
            }
        }

        return false;
    }

}
