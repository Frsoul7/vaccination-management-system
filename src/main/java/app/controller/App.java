package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.PerformanceRecordsStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.utils.Configurations;
import app.domain.model.utils.DateCustom;
import app.domain.shared.EmployeeRoles;
import app.interfaces.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Paulo Maio <pam@isep.ipp.pt> Edgar Moreira <1010100@isep.ipp.pt> Fernando Ribeiro <1060064@isep.ipp.pt> José
 * Silva <1060568@isep.ipp.pt> Pedro Gomes <1060588@isep.ipp.pt>
 */

public class App implements Constants {

    /***
     * Instances the company
     */
    private final Company company;
    /***
     * Instances the Authfacade
     */
    private final AuthFacade authFacade;

    /***
     * Method to instance the App, with the properties of the company and its corresponding authFacade
     */
    private App() {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    /***
     * Instances the company
     * @return company
     */
    public Company getCompany() {
        return this.company;
    }

    /***
     * Get the current user session
     * @return current user session
     */
    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    /***
     * method to do login to the users (employees or SNS users), to access specific User Interface
     * @param email - email of the user to attempt the log in
     * @param pwd   - password of the user to attempt the log in
     * @return true if login is successful
     */
    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    /***
     * Method to do logout on the session
     */
    public void doLogout() {
        this.authFacade.doLogout();
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2

    /***
     * Get the properties/definitions/designation of the company
     * @return proprieties of the company
     */
    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try {
            InputStream in = new FileInputStream(PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex) {

        }
        return props;
    }

    /***
     * Starts the App, connects with authFacade to perform login
     */
    private void bootstrap() {
        this.authFacade.addUserRole(ROLE_SNSUSER, ROLE_SNSUSER);
        this.authFacade.addUserRole(EmployeeRoles.ROLE_ADMIN.getRoleId(), EmployeeRoles.ROLE_ADMIN.getDescription());
        this.authFacade.addUserRole(EmployeeRoles.ROLE_RECEPTIONIST.getRoleId(),
                                    EmployeeRoles.ROLE_RECEPTIONIST.getDescription());
        this.authFacade.addUserRole(EmployeeRoles.ROLE_NURSE.getRoleId(), EmployeeRoles.ROLE_NURSE.getDescription());
        this.authFacade.addUserRole(EmployeeRoles.ROLE_CENTERCOORNIDATOR.getRoleId(),
                                    EmployeeRoles.ROLE_CENTERCOORNIDATOR.getDescription());

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",
                                        EmployeeRoles.ROLE_ADMIN.getRoleId());
        this.authFacade.addUserWithRole("SW Developer", "p@p.pt", "123", EmployeeRoles.ROLE_ADMIN.getRoleId());
        this.authFacade.addUserWithRole("SNS User", "u@u.pt", "123", ROLE_SNSUSER);
        this.authFacade.addUserWithRole("Receptionist", "r@r.pt", "123", EmployeeRoles.ROLE_RECEPTIONIST.getRoleId());
        this.authFacade.addUserWithRole("Nurse", "n@n.pt", "123", EmployeeRoles.ROLE_NURSE.getRoleId());
        this.authFacade.addUserWithRole("Center Coordinator", "c@c.pt", "123",
                                        EmployeeRoles.ROLE_CENTERCOORNIDATOR.getRoleId());

        // Bootstrap vaccination centers (dev seed data)
        bootstrapVaccinationCenters();

        // Schedule task
/*        try {
            String[] timeToExecute = Configurations.getTaskRunPeopleVaccinated().split(":");
            int hour = Integer.parseInt(timeToExecute[0]);
            int minutes = Integer.parseInt(timeToExecute[1]);
            DateCustom dateToday = new DateCustom(DateCustom.getActualDate());
            Calendar dateToExecute = Calendar.getInstance();
            dateToExecute.set(dateToday.getYear(), dateToday.getMonth(), dateToday.getDay(), hour, minutes);
            Timer timer = new Timer();
            timer.schedule(new TimeTaskPeopleVaccinatedInDay(), dateToExecute.getTime());
        }
        catch(IOException|ClassNotFoundException|InstantiationException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/
    }

    /***
     * Singleton class - can have only one object (an instance of the class) at a time.
     */
    private static App singleton = null;

    /***
     * Get the instance of the App
     * @return singleton class
     */
    public static App getInstance() {
        if(singleton == null) {
            synchronized(App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    /**
     * Bootstrap vaccination centers with seed data for development
     * Only creates centers if they don't already exist (checked after serialization loads)
     */
    private void bootstrapVaccinationCenters() {
        try {
            // Get vaccination center store from company
            VaccinationCenterStore vcStore = this.company.getVaccinationCenterStore();
            
            // Only bootstrap if no vaccination centers exist yet
            // This prevents duplicates when serialized data is loaded
            if (!vcStore.getVaccinationCenterList().isEmpty()) {
                return; // Centers already exist, skip bootstrap
            }
            
            // Create TimeHour objects for opening and closing times (08:00 - 20:00)
            app.domain.model.utils.TimeHour openHour = new app.domain.model.utils.TimeHour(8, 0);
            app.domain.model.utils.TimeHour closeHour = new app.domain.model.utils.TimeHour(20, 0);
            
            // Default values for all centers
            int slotDuration = 30; // 30 minutes per slot
            int maxVaccinesPerSlot = 10; // 10 vaccines per slot
            
            // 1. Centro Vacinação do Porto (Mass Vaccination Center)
            VaccinationCenter vc1 = vcStore.registerVaccinationCenter(
                "Centro Vacinação do Porto",
                "Rua de Santa Catarina, 4000-447 Porto",
                220123456L,
                "porto@sns.pt",
                220123457L,
                "www.sns-porto.pt",
                openHour,
                closeHour,
                slotDuration,
                maxVaccinesPerSlot,
                "Mass Vaccination Center"
            );
            vcStore.addVaccinationCenter(vc1);
            
            // 2. Centro Vacinação Gondomar (Mass Vaccination Center)
            VaccinationCenter vc2 = vcStore.registerVaccinationCenter(
                "Centro Vacinação Gondomar",
                "Av. da República, 4420-208 Gondomar",
                224634500L,
                "gondomar@sns.pt",
                224634501L,
                "www.sns-gondomar.pt",
                openHour,
                closeHour,
                slotDuration,
                maxVaccinesPerSlot,
                "Mass Vaccination Center"
            );
            vcStore.addVaccinationCenter(vc2);
            
            // 3. Centro Vacinação Hospital da Trofa (Healthcare Center) - name shortened to fit 30 char limit
            VaccinationCenter vc3 = vcStore.registerVaccinationCenter(
                "Centro Vacinação da Trofa",
                "Rua Castro, 4785-589 Trofa",
                252410330L,
                "trofa@sns.pt",
                252410331L,
                "www.sns-trofa.pt",
                openHour,
                closeHour,
                slotDuration,
                maxVaccinesPerSlot,
                "Healthcare Center",
                "ARSE"
            );
            vcStore.addVaccinationCenter(vc3);
            
            // 4. Centro Vacinação Vila Nova de Gaia (Mass Vaccination Center)
            VaccinationCenter vc4 = vcStore.registerVaccinationCenter(
                "Centro Vacinação de Gaia",
                "Av. João XXIII, 4400-181 Vila Nova de Gaia",
                223770700L,
                "gaia@sns.pt",
                223770701L,
                "www.sns-gaia.pt",
                openHour,
                closeHour,
                slotDuration,
                maxVaccinesPerSlot,
                "Mass Vaccination Center"
            );
            vcStore.addVaccinationCenter(vc4);
            
            System.out.println("✓ Bootstrap: 4 vaccination centers created successfully");
            
        } catch (Exception e) {
            // Silent catch - bootstrap data creation failure shouldn't prevent app startup
            System.out.println("Warning: Failed to bootstrap vaccination centers - " + e.getMessage());
        }
    }
    
    /**
     * Public method to re-bootstrap vaccination centers after serialization loads
     * Can be called from SerializationController after loading data
     */
    public void ensureBootstrapData() {
        bootstrapVaccinationCenters();
    }

/*    private static class TimeTaskPeopleVaccinatedInDay extends TimerTask {
        public void run() {
            PerformanceRecordsStore perfRecordsStore = new PerformanceRecordsStore();
            int total = perfRecordsStore.getTotalNumberOfPeopleVaccinated(DateCustom.getActualDate());
            System.out.printf("######## %02d ########", total);
        }
    }*/
}
