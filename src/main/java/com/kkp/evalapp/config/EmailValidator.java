package com.kkp.evalapp.config;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidator {

    public static boolean isValidEmailAddress(String email) {
        boolean isValid = true;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException e) {
            isValid = false;
        }
        return isValid;
    }

    public static void main(String[] args) {
        String email = "test@example.com";
        boolean isValidEmail = isValidEmailAddress(email);
        System.out.println("Is email valid? " + isValidEmail);
    }
}
