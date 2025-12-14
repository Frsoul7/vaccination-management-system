package app.domain.model;

import app.interfaces.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordGeneratorTest implements Constants {

    @Test
    void getPassword_OK() {
        assertTrue(isPasswordValid(PasswordGenerator.getPassword()));
    }

    private boolean isPasswordValid(String password) {
        int countUppercase = 0, countDigits = 0;

        if(password == null) {
            return false;
        } else if(password.length() != PASSWORD_NUMBER_OF_CHARACTERS) {
            return false;
        }

        for(int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if(Character.isUpperCase(c)) {
                countUppercase++;
            } else if(Character.isDigit(c)) {
                countDigits++;
            }
        }

        return countDigits >= PASSWORD_NUMBER_OF_CHARACTERS_WITH_DIGITS &&
               countUppercase >= PASSWORD_NUMBER_OF_CHARACTERS_WITH_UPPER_CASE;
    }

}