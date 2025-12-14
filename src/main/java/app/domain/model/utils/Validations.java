package app.domain.model.utils;

import app.domain.model.exceptions.OperationCanceledByUserException;
import app.domain.shared.EmployeeRoles;
import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;

import java.io.File;
import java.util.Calendar;
import java.util.regex.Pattern;

/***
 * @authors Edgar Moreira <1010100@isep.ipp.pt>
 *          Fernando Ribeiro <1060064@isep.ipp.pt>
 *          José Silva <1060568@isep.ipp.pt>
 *          Pedro Gomes <1060588@isep.ipp.pt>
 */

public abstract class Validations implements Constants {

    /***
     * Method to verify if a String "Name" is valid
     * @param name              - name to be verified if valid
     * @param mandatory         - check if field is mandatory (true if mandatory, false if not)
     * @param acceptCancel      - parameter to cancel the operation when true (zero), does nothing if false.
     * @return true if string name inserted is valid; false if it is not
     * @throws OperationCanceledByUserException
     */
    public static boolean isNameValid(String name, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(name == null || name.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && name.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }
        return name.matches("(^[A-zÀ-ú](?=.{0,29}$)[\\D.A-zÀ-ú]*(?:\\h+[A-zÀ-ú][A-zÀ-ú]*)*$)");
    }

    /***
     * Method to verify if gender is valid ("male" or "female")
     * @param gender       - gender to validate
     * @param mandatory    - is the field mandatory (true) or not (false)
     * @param acceptCancel - parameter to cancel the operation when true (zero), does nothing if false.
     * @return true if gender inserted is valid, false if not
     * @throws OperationCanceledByUserException
     */
    public static boolean isGenderValid(String gender, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(gender == null || gender.length() == 0) {
            return !mandatory;
        } else if(gender.equalsIgnoreCase(GENDER_MALE) || gender.equalsIgnoreCase(GENDER_FEMALE)) {
            return true;
        } else if(acceptCancel && gender.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }
        return false;
    }

    /***
     * Method to verify if a string is valid according to the rule defined
     * @param text          - text to be verified
     * @param mandatory     - is the field mandatory (true) or not (false)
     * @param acceptCancel  - is possible to cancel with zero (true) or not (false)
     * @param maxLength     - maximum length of the text allowed
     * @return true if String length is valid, false if not
     */
    public static boolean isStringLengthValid(String text, boolean mandatory, boolean acceptCancel, int maxLength)
            throws OperationCanceledByUserException {
        if(text == null || text.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && text.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }
        return text.length() <= maxLength;
    }

    /***
     * Method to verify if is a valid email
     * @param email        - email to verify if valid
     * @param mandatory    - is the field mandatory (true) or not (false)
     * @param acceptCancel - is possible to cancel with zero (true) or not (false)
     * @return - true if email inserted is valid, false if not
     * @throws OperationCanceledByUserException
     */
    public static boolean isEmailFormatValid(String email, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(email == null || email.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && email.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }
        String emailRegex =
                "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z" + "]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    /***
     * Verify if Birthdate is valid and if is of DateCustom type
     * @param birthDate         - Birthdate of a user inserted
     * @return true if birthdate is valid; false if it is not
     */
    public static boolean isBirthDateValid(DateCustom birthDate) {
        if(birthDate == null) {
            return false;
        }

        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        DateCustom todayDateCustom = new DateCustom(todayDay, todayMonth, todayYear);
        DateCustom oldestBirthDatePossibleDateCustom = new DateCustom(1, 1, 1900);

        return !birthDate.isBigger(todayDateCustom) && birthDate.isBigger(oldestBirthDatePossibleDateCustom);
    }

    public static boolean isPeriodValid(DateCustom beginDate, DateCustom endDate) {
        if(beginDate == null || endDate == null) {
            return false;
        }

        return endDate.compareTo(beginDate) >= 0;
    }

    /***
     * Method to verify if a length of a number of type long inserted is valid
     * @param number            - number to be verified
     * @param mandatory         - is the field mandatory (true) or not (false)
     * @param acceptCancel      - is possible to cancel with zero (true) or not (false)
     * @param sizeValue         - valid size which defines if the number has length valid or not
     * @return true if the length of the number is valid; false if it is not
     * @throws OperationCanceledByUserException
     */
    public static boolean isLengthValidEquals(long number, boolean mandatory, boolean acceptCancel, int sizeValue)
            throws OperationCanceledByUserException {
        if(acceptCancel && number == 0) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        } else if(!mandatory && number == 0) {
            return true;
        } else {
            return String.valueOf(number).length() == sizeValue;
        }
    }

    /***
     * Method to verify if a length of a number of type integer inserted is valid
     * @param number            - number to be verified
     * @param mandatory         - is the field mandatory (true) or not (false)
     * @param acceptCancel      - is possible to cancel with zero (true) or not (false)
     * @param sizeValue         - valid size which defines if the number has length valid or not
     * @return true if the length of the number is valid; false if it is not
     * @throws OperationCanceledByUserException
     */
    public static boolean isLengthValidLesserThen(int number, boolean mandatory, boolean acceptCancel, int sizeValue)
            throws OperationCanceledByUserException {
        if(acceptCancel && number == 0) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        } else if(!mandatory && number == 0) {
            return true;
        } else {
            return String.valueOf(number).length() <= sizeValue && number > 0;
        }
    }

    /***
     * Method to verify if a integer value is valid
     * @param value         - value/number to be verified if is valid
     * @param maxValue      - parameter which serves as limit for the value to be verified
     * @return true if the value is valid; false if it is not
     */
    public static boolean isValueValid(int value, boolean mandatory, boolean acceptCancel, int maxValue)
            throws OperationCanceledByUserException {
        if(acceptCancel && value == 0) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        } else if(!mandatory && value == 0) {
            return true;
        } else {
            return value > 0 && value <= maxValue;
        }
    }

    /***
     * Method to verify if the number of doses of a vaccine to be administered is valid
     * @param numberDoses       - number of doses defined for a vaccine (max. of doses considered is 6)
     * @return valid if the number of doses is valid; false if it is not
     */
    public static boolean isNumberDosesValidAdm(int numberDoses, boolean mandatory, boolean acceptCancel, int maxLength)
            throws OperationCanceledByUserException {
        if(acceptCancel && numberDoses == 0) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        } else if(!mandatory && numberDoses == 0) {
            return true;
        } else {
            return String.valueOf(numberDoses).length() <= maxLength && numberDoses > 0 && numberDoses < 6;
        }
    }

    /***
     * Method to verify if the time interval between doses of a certain vaccine is valid
     * @param timeInterval      - Time interval between 2 consecutive administrations of a specific vaccine
     * @return true if the time interval is valid; false if it is not
     */
    public static boolean isTimeIntervalBetweenDosesValid(int timeInterval, boolean mandatory, boolean acceptCancel,
                                                          int maxLength) throws OperationCanceledByUserException {
        if(acceptCancel && timeInterval == 0) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        } else if(!mandatory && timeInterval == 0) {
            return true;
        } else {
            return (String.valueOf(timeInterval).length() <= maxLength) && timeInterval <= 366 && timeInterval > 0;
        }


    }

    /***
     * Method to verify if the filepath of a file to be loaded by the system is valid
     * @param filePath      - filepath of the file specified by the user
     * @param isFile        - verification if filepath takes to a valid file to be loaded
     * @return true if the filepath is valid; false if it is not
     */
    public static boolean isFilePathValid(String filePath, boolean isFile, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(acceptCancel && filePath.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }

        File tempFile = new File(filePath);
        return tempFile.exists() && tempFile.isFile() == isFile && tempFile.length() != 0;
    }

    public static boolean isFilePathNewCSV(String filePath) {
        if(filePath == null || filePath.isEmpty() || filePath.length() < 5) {
            return false;
        } else {
            if(!filePath.substring(filePath.length() - 4).equalsIgnoreCase(".csv")) {
                return false;
            }
        }
        File tempFile = new File(filePath);
        return !tempFile.exists();
    }

    /***
     * Method to verify if a format of a date is valid (e.g. dd/mm/yyyy)
     * @param date              - date to be verified
     * @param mandatory         - is the field mandatory (true) or not (false)
     * @param acceptCancel      - is possible to cancel with zero (true) or not (false)
     * @return true if the format of date is valid; false if it is not
     * @throws OperationCanceledByUserException
     */
    public static boolean isDateFormatValid(String date, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(date == null || date.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && date.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }

        date = removeSpace(date);

        //checks if the number of "/" is valid
        if(countingDelimiters(date, '/') != 2) {
            return false;
        }

        String[] dateParts = date.split("/");
        try {
            int dd = Integer.parseInt(dateParts[0]);
            int mm = Integer.parseInt(dateParts[1]);
            int yyyy = Integer.parseInt(dateParts[2]);

            return (dd < 100 && mm < 100 && yyyy < 10000 && yyyy > 999) ;

        }
        catch(NumberFormatException ex) {
            return false;
        }
    }

    /***
     * Verifies if an Hour format is valid
     * @param hour          - hour to be verified if valid
     * @return true if the format of hour is valid; false if it is not
     */
    public static boolean isHourFormatValid(String hour, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(hour == null || hour.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && hour.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }

        int hourInt = -1;
        int minutesInt = -1;

        hour = removeSpace(hour);

        if(countingDelimiters(hour, ' ') == 1 && countingDelimiters(hour, ':') == 1 && hour.charAt(2) == ':' &&
           hour.charAt(5) == ' ') {

            String[] hourElements = hour.split(":| ");

            try {
                hourInt = Integer.parseInt(hourElements[0]);
                minutesInt = Integer.parseInt(hourElements[1]);
            }
            catch(NumberFormatException ex) {
                return false;
            }

            String indicator = hourElements[2];

            return (indicator.compareToIgnoreCase("am") == 0 || indicator.compareToIgnoreCase("pm") == 0) &&
                   (hourInt >= 1 && hourInt <= 12) && (minutesInt >= 0 && minutesInt <= 59);

        }

        return false;
    }

    /***
     * Method to verify if a specified age group is valid
     * @param ageGroup          - age group specified by the user
     * @return true if the age group is valid; false if it is not
     */
    public static boolean isAgeGroupValid(String ageGroup, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {

        if(ageGroup == null || ageGroup.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && ageGroup.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }

        ageGroup = removeSpace(ageGroup);

        if(countingDelimiters(ageGroup, ',') != 1) {
            return false;
        }

        String[] ageGroupParts = ageGroup.split(",");
        try {
            // check if the first and second parts are integers
            int numI = Integer.parseInt(ageGroupParts[0]);
            int numS = Integer.parseInt(ageGroupParts[1]);

            return (numI >= 1 && numI <= 120 && numS >= 1 && numS <= 120 && numI < numS);

        }
        catch(NumberFormatException ex) {
            return false;
        }
    }

    /***
     * Method to verify if a lot number of a vaccine is valid, according to the requirements
     * @param lotNumber     - lot number of a vaccine
     * @return true if the lot number is valid; false if it is not
     */
    public static boolean isLotNumberValid(String lotNumber, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(lotNumber == null || lotNumber.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && lotNumber.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }

        lotNumber = removeSpace(lotNumber);

        if(countingDelimiters(lotNumber, '-') != 1) {
            return false;
        }

        String[] lotNumberParts = lotNumber.split("-");
        try {
            // check if the second part is an integer
            // check if the first part have five alphanumeric characters
            int num = Integer.parseInt(lotNumberParts[1]);
            return (num >= 0 && num <= 99) &&
                   (lotNumberParts[0].length() == 5 && lotNumberParts[0].matches("([A-Z0-9a-z]*)"));
        }
        catch(NumberFormatException ex) {
            return false;
        }
    }

    /***
     * Method to verify if is a website address is valid
     * @param website       - Website address to be verified if valid
     * @return true if the website address is valid; false if it is not
     */
    public static boolean isWebSiteFormatValid(String website, boolean mandatory, boolean acceptCancel)
            throws OperationCanceledByUserException {
        if(website == null || website.length() == 0) {
            return !mandatory;
        } else if(acceptCancel && website.equalsIgnoreCase(VALUE_TO_CANCEL)) {
            throw new OperationCanceledByUserException("#info# Operation canceled by user");
        }
        // controller of first element
        int j = 0;
        // controller for the remaining elements
        int k = 0;

        // remove the spaces in beginning and end
        website = removeSpace(website);

        //check if the number of dots is valid
        int dotCount = countingDelimiters(website, '.');

        // if the number of dots is valid
        if(dotCount == 2 || dotCount == 3) {
            //split by dots
            String[] websiteParts = website.split("[.]");
            if((dotCount == 2 && websiteParts.length == 3) || (dotCount == 3 && websiteParts.length == 4)) {

                for(int i = 0; i < websiteParts.length; i++) {
                    //check if the first parts corresponds to www
                    if(websiteParts[i].compareToIgnoreCase("www") == 0 && i == 0) {
                        j = 1;
                    }
                    if(websiteParts[i].isEmpty()) {
                        k = 1;
                    }
                }
            } else {
                return false;
            }
        }
        return k == 0 && j == 1;
    }

    /***
     * Method to remove spaces from Strings
     * @param something         - a random String
     * @return the String after being trimmed and the spaces are removed
     */
    @ExcludeFromJacocoGeneratedReport
    private static String removeSpace(String something) {
        return something.trim();
    }

    /***
     * Method to count how many delimiters exist in a String
     * @param something         - a random String inserted by user
     * @param delimiter         - the char type of the delimited (e.g. " / " or " - ")
     * @return the counter Count of the number of delimiters found on the String
     */
    @ExcludeFromJacocoGeneratedReport
    private static int countingDelimiters(String something, char delimiter) {

        int Count = 0;
        for(int i = 0; i < something.length(); i++) {
            if(something.charAt(i) == delimiter) {
                Count++;
            }
        }
        return Count;
    }

    /***
     * Method to very if the role of the employee is valid
     * @param employeeRole          - role of the employee inserted in the system
     * @return true if the role of the employee is valid; false if it is not
     */
    public static boolean isEmployeeRoleValid(EmployeeRoles employeeRole) {
        try {
            return EmployeeRoles.isRoleDescriptionValid(employeeRole.getDescription());
        }
        catch(Exception e) {
            return false;
        }
    }

    /***
     * Method to verify if the Employee identifier (ID) is valid
     * @param id           - Identifier (ID) of the employee
     * @return true if identifier is different from zero; false if it is zero
     */
    public static boolean isEmployeeIdValid(long id) {
        return id != 0;
    }

    public static boolean validateNumberOfAttributes(String[] data, int numberOfAttributes) {
        return data.length == numberOfAttributes;
    }

}

