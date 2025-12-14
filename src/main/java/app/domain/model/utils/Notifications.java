package app.domain.model.utils;

import app.domain.model.SNSUser;
import app.domain.model.VaccineScheduler;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@ExcludeFromJacocoGeneratedReport
public class Notifications {


    public static void sendSNSUserPassword(String name, String email, String password) throws IOException {
        writeToFile(name, email, password, "SnsUsersEmailsAndPasswords.txt");
    }

    public static void sendEmployeePassword(String name, String email, String password, String role)
            throws IOException {
        writeToFile(name, email, password, role, "EmployeeEmailsAndPasswords.txt");
    }

    public static void sendSMSSchedulerNotification(VaccineScheduler scheduler, long phoneNumber) throws IOException {
        FileWriter fileOutput = new FileWriter(("SchedulerNotification.txt"), true);
        PrintWriter out = new PrintWriter(fileOutput);
        out.printf(("Phone number: %d\nSNS User number: %d\nVaccine Type designation: %s\nVaccination Center name: " +
                    "%s\nDate:" + " %s\nSchedule Time: %s"), phoneNumber, scheduler.getSnsUserNumber(),
                   scheduler.getVaccineTypeDesignation(), scheduler.getVaccinationCenterName(),
                   scheduler.getDate().toString(), scheduler.getSchedulerTime().toString());

        out.close();
        fileOutput.close();
    }

    public static void sendSMSEndOfRecoveryTimeNotification(SNSUser snsUser) throws IOException {
        FileWriter fileOutput = new FileWriter(("EndOfRecoveryTimeNotification.txt"), true);
        PrintWriter out = new PrintWriter(fileOutput);
        out.printf(("Dear %s with sns user number %d, whe are pleased to inform that your recovery time has ended. " +
                    "You may leave the recovery room. Thank you for your attention!\n\n"), snsUser.getName(),
                   snsUser.getSnsUserNumber());

        out.close();
        fileOutput.close();
    }

    private static void writeToFile(String name, String email, String password, String fileName) throws IOException {

        FileWriter fileOutput = new FileWriter((fileName), true);
        PrintWriter out = new PrintWriter(fileOutput);
        out.printf("Name: %s%nEmail: %s%nPassword: %s%n", name, email, password);
        out.close();
        fileOutput.close();

    }

    private static void writeToFile(String name, String email, String password, String role, String fileName)
            throws IOException {

        FileWriter fileOutput = new FileWriter((fileName), true);
        PrintWriter out = new PrintWriter(fileOutput);
        out.printf("Name: %s%nEmail: %s%nPassword: %s%nRole: %s%n", name, email, password, role);
        out.close();
        fileOutput.close();

    }


}
