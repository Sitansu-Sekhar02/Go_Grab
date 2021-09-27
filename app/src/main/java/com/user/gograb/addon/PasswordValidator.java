package com.user.gograb.addon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator{

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "^([a-zA-Z0-9_-]){8,99}$";//"^[\\pL\\pN]+$";
            //"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20})";

    public PasswordValidator(){
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    /**
     * Validate password with regular expression
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(final String password){

        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}