package app.controller;

import app.domain.model.SNSUser;
import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.model.store.SNSUserStore;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.Validations;
import app.interfaces.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImportCSVSNSUserController extends ImportCSVController implements Constants {

    private final int NUMBER_OF_ATTRIBUTES = 8;
    private static final String HEADER_DELIMITER = ";";
    private static final String DEFAULT_HEADER =
            "Name;Gender;BirthDate;Address;PhoneNumber;Email;SNSUSerNumber;CitizenCardNumber";

    public ImportCSVSNSUserController() throws IllegalArgumentException {
        super();
    }

    @Override
    public String defineDelimiter() {
        return this.getFileDelimiter();
    }

    @Override
    public int defineNumberOfAttributes() {
        return NUMBER_OF_ATTRIBUTES;
    }

    @Override
    public int defineInitialLine() {
        if(this.getDelimiter().equalsIgnoreCase(CSV_DELIMITATOR_WITH_HEADER)) {
            return 1;
        } else {
            this.setHeader(DEFAULT_HEADER.split(HEADER_DELIMITER));
            return 0;
        }
    }

    @Override
    public boolean importLine(String[] line) {
        String name, address, gender, email;
        DateCustom birthdate;
        long phoneNumber, snsUserNumber, citizenCardNumber;

        try {
            name = line[CSV_IDX_NAME].trim();
            address = line[CSV_IDX_ADDRESS].trim();
            gender = getGender(line[CSV_IDX_GENDER].trim());
            email = line[CSV_IDX_EMAIL].trim();

            if(!Validations.isDateFormatValid(line[CSV_IDX_BIRTH_DATE], true, false)) {
                throw new IllegalArgumentException(("Invalid birth date."));
            } else {
                birthdate = new DateCustom(line[CSV_IDX_BIRTH_DATE]);
            }

            try {
                phoneNumber = Long.parseLong(line[CSV_IDX_PHONE_NUMBER]);
            }
            catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid phone number.");
            }

            try {
                snsUserNumber = Long.parseLong(line[CSV_IDX_SNS_USER_NUMBER]);
            }
            catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid sns user number.");
            }

            try {
                citizenCardNumber = Long.parseLong(line[CSV_IDX_CITIZEN_CARD_NUMBER]);
            }
            catch(NumberFormatException e) {
                throw new IllegalArgumentException("Invalid citizen card number");
            }
        }
        catch(OperationCanceledByUserException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        SNSUserStore snsUStore = this.getCompany().getSnsUserStore();

        try {
            if(!snsUStore.validateSnsUser(snsUserNumber)) {
                SNSUser snsUser =
                        snsUStore.registerSNSUser(name, address, gender, phoneNumber, email, citizenCardNumber,
                                                  snsUserNumber, birthdate);
                return snsUStore.saveSnsUser(snsUser, this.getAuthFacade());
            } else {
                throw new IllegalArgumentException("Sns User already exists.");
            }
        }
        catch(OperationCanceledByUserException e) {
            return false;
        }
    }

    private String getFileDelimiter() throws IllegalArgumentException {
        try {
            Scanner readFile = new Scanner(this.getFile());
            String lineFile = readFile.nextLine();
            int countDelimitatorWithoutHeader = StringUtils.countMatches(lineFile, CSV_DELIMITATOR_WITHOUT_HEADER);
            int countDelimitatorWithHeader = StringUtils.countMatches(lineFile, CSV_DELIMITATOR_WITH_HEADER);
            readFile.close();

            if(countDelimitatorWithHeader != 0 && countDelimitatorWithoutHeader == 0) {
                return CSV_DELIMITATOR_WITH_HEADER;
            } else if(countDelimitatorWithHeader == 0 && countDelimitatorWithoutHeader != 0) {
                return CSV_DELIMITATOR_WITHOUT_HEADER;
            } else {
                throw new IllegalArgumentException("CSV File with wrong content.");
            }
        }
        catch(FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid file path.");
        }
    }

    private static String getGender(String gender) {
        final String MASCULINO = "Masculino";
        final String FEMININO = "Feminino";
        if(gender.equalsIgnoreCase(MASCULINO)) {
            return GENDER_MALE;
        } else if(gender.equalsIgnoreCase(FEMININO)) {
            return GENDER_FEMALE;
        } else {
            return gender;
        }
    }

}
