package app.interfaces;

public interface Constants {
    String ROLE_SNSUSER = "SNS USER";
    String PARAMS_FILENAME = "config.properties";
    String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    String PARAMS_TASK_RUN_PEOLPE_VACCINATED = "TaskRun.PeopleVaccinated";
    /***
     * Default value for Gender
     */
    String DEFAULT_VALUE_GENDER = "";
    /***
     * Default value for vaccination center designation when it's a mass type
     */
    String DEFAULT_VALUE_VACCINATION_CENTER_DESIGNATION = "";
    /***
     * Default value for Phone Number
     */
    long DEFAULT_VALUE_PHONE_NUMBER = -1;
    /***
     * Default value for SNS User Number
     */
    long DEFAULT_VALUE_SNS_USER_NUMBER = -1;
    /***
     * Default value for Fax Number
     */
    long DEFAULT_VALUE_FAX_NUMBER = -1;
    /***
     * Default value for Slot Duration
     */
    int DEFAULT_VALUE_SLOTDURATION_NUMBER = -1;
    /***
     * Default value for Max Number of Vaccines per Slot
     */
    int DEFAULT_VALUE_MAXVACPERSLOT_NUMBER = -1;
    /***
     * Defult value for Citizen Card Number
     */
    long DEFAULT_VALUE_CITIZEN_CARD_NUMBER = -1;
    /***
     * Default value for vaccine id
     */
    int DEFAULT_VALUE_IDVACCINE_NUMBER = -1;
    /***
     * Default value for number of doses
     */
    int DEFAULT_VALUE_NUMBERDOSES_NUMBER = -1;
    /***
     * Default value for vaccine dosage
     */
    int DEFAULT_VALUE_VACCINEDOSAGE_NUMBER = -1;
    /***
     * Default value for time interval
     */
    int DEFAULT_VALUE_TIME_INTERVAL = -1;

    /***
     * Max length of name
     */
    int MAX_LENGTH_NAME = 60;
    /***
     * Max length of address
     */
    int MAX_LENGTH_ADDRESS = 100;
    /***
     * Max length of designation of Vaccination Center
     */
    int MAX_LENGTH_HEALTHCAREDESIGNATION = 100;
    /***
     * Size of Citizen Card Number
     */
    int SIZE_CITIZEN_CARD_NUMBER = 8;
    /***
     * Size of Phone Number
     */
    int SIZE_PHONE_NUMBER = 9;
    /***
     * Size of Phone Number
     */
    int SIZE_SNS_USER_NUMBER = 9;
    /***
     * Size of Fax Number
     */
    int SIZE_FAX_NUMBER = 9;
    /***
     * Size of Slot Duration
     */
    int MAX_SLOTDURATION_NUMBER = 60;
    /***
     * Size of Max number of Vaccines per Slot
     */
    int MAX_MAXVACPERSLOT_NUMBER = 10000;
    /***
     * ID of vaccine
     */
    int SIZE_IDVACCINE = 6;
    /***
     * Number of doses of a vaccine
     */
    int SIZE_NUMBERDOSES = 6;

    /***
     * Max size for time interval
     */
    int SIZE_TIMEINTERVAL = 4;
    /***
     * Max length of designation
     */
    int MAX_LENGTH_DESIGNATION = 60;
    /***
     * Default value for vaccine dosage
     */
    int SIZE_VACCINEDOSAGE = 4;
    /***
     * Default value for vaccine dosage
     */
    int SIZE_NUMBEROFDOSESADM = 1;
    /***
     * Default value for time interval between doses
     */
    int SIZE_INTERVALBETWEENDOSES = 3;
    /***
     * Gender Male
     */
    String GENDER_MALE = "Male";
    /***
     *  Gender Female
     */
    String GENDER_FEMALE = "Female";
    /***
     * Valor a usar para Cancel (necessary to be numeric)
     */
    String VALUE_TO_CANCEL = "0";
    /***
     * Define length of password
     */
    int PASSWORD_NUMBER_OF_CHARACTERS = 7;
    /***
     * Define number of upper case characters in password
     */
    int PASSWORD_NUMBER_OF_CHARACTERS_WITH_UPPER_CASE = 3;
    /***
     * Define number of digits in password
     */
    int PASSWORD_NUMBER_OF_CHARACTERS_WITH_DIGITS = 2;
    /***
     * Define the constant for covid type description
     */
    String COVID_VACCINE_TYPE_DESCRIPTION = "Covid-19";


    String FILE_NAME_EMP = "serFiles\\Employee.ser";

    String FILE_NAME_SNSUSER = "serFiles\\SnsUser.ser";

    String FILE_NAME_VACCINE = "serFiles\\Vaccine.ser";

    String FILE_NAME_VACCINATION_CENTER = "serFiles\\VaccinationCenter.ser";

    String FILE_NAME_VACCINE_TYPE = "serFiles\\VaccineType.ser";
    String FILE_NAME_SNS_US_EMAIL_PW = "serFiles\\SnsUsersEmailsAndPasswords.ser";
    String FILE_NAME_EMPLOYEE_EMAIL_PW = "serFiles\\EmployeeEmailsAndPasswords.ser";

    /***
     * Delimitator for CSV file with header
     */
    String CSV_DELIMITATOR_WITH_HEADER = ";";
    /***
     * Delimitator for CSV file without header
     */
    String CSV_DELIMITATOR_WITHOUT_HEADER = ",";
    int CSV_IDX_NAME = 0;
    int CSV_IDX_GENDER = 1;
    int CSV_IDX_BIRTH_DATE = 2;
    int CSV_IDX_ADDRESS = 3;
    int CSV_IDX_PHONE_NUMBER = 4;
    int CSV_IDX_EMAIL = 5;
    int CSV_IDX_SNS_USER_NUMBER = 6;
    int CSV_IDX_CITIZEN_CARD_NUMBER = 7;

    String ERROR_INVALID_ARGUMENT_RECEIVED = "Invalid argument received.";
    String INFO_IMPORT_WITH_SUCCESS = "Imported with success.";
    String INFO_WRONG_NUMBER_ATTRIBUTES_MORE = "Wrong number of attributes (more than expected).";
    String INFO_WRONG_NUMBER_ATTRIBUTES_LESS = "Wrong number of attributes (less than expected).";
    String INFO_IMPORT_WITHOUT_SUCCESS = "Imported without success.";
    String INFO_IMPORT_FILE_FIRST = "Please, import a file first.";
}
