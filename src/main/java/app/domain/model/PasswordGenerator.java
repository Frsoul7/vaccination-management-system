package app.domain.model;

import app.interfaces.Constants;
import app.interfaces.ExcludeFromJacocoGeneratedReport;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;

/**
 * * @init_author Edgar Moreira <1010100@isep.ipp.pt>
 */
public class PasswordGenerator implements Constants {

    /***
     * Generate a random password following the rules
     * @return passwordd generated
     */
    public static String getPassword() {
        org.passay.PasswordGenerator generator = new org.passay.PasswordGenerator();

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(PASSWORD_NUMBER_OF_CHARACTERS_WITH_UPPER_CASE);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(PASSWORD_NUMBER_OF_CHARACTERS_WITH_DIGITS);

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);

        CharacterData specialChars = new CharacterData() {
            @Override
            @ExcludeFromJacocoGeneratedReport
            public String getErrorCode() {
                return "ERROR_CODE";
            }

            @Override
            @ExcludeFromJacocoGeneratedReport
            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule specialRule = new CharacterRule(specialChars);

        return generator.generatePassword(PASSWORD_NUMBER_OF_CHARACTERS, upperCaseRule, digitRule, lowerCaseRule,
                                          specialRule);
    }
}
