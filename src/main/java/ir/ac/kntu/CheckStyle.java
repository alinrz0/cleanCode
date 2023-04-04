package ir.ac.kntu;

import java.util.regex.Pattern;

public class CheckStyle {
    public boolean isUpperCamelCase(String name) {
        return Pattern.matches("[A-Z].*", name.trim());
    }

    public boolean islowerCamelCase(String name) {
        return Pattern.matches("[a-z].*", name.trim());
    }

    public boolean isMoreThanTwoLetter(String name) {
        return name.length() >= 2;
    }

    public int numberOfSemicolons(String line) {
        int numberOfSemicolons = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ';') {
                numberOfSemicolons++;
            }
        }
        return numberOfSemicolons;
    }
}
