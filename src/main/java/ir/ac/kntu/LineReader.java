package ir.ac.kntu;

import java.util.regex.Pattern;

public class LineReader {
    private String line;
    private boolean package0 = true;
    private boolean import0 = true;
    private boolean checkSpace = true;
    private boolean default0 = true;
    private boolean comment = true;
    private int default1;
    CheckIndentation checkIndentation = new CheckIndentation();
    private String[] declarations = { "int", "float", "boolean", "double", "String",
            "char", "long", "byte", "void" };

    public void setLine(String line) {
        this.line = line;
    }

    public int spaceCalculator(String line) {
        int numberOfSpace = 0;
        while (line.length() > 0 && line.length() > numberOfSpace
                && line.charAt(numberOfSpace) == ' ') {
            numberOfSpace++;
        }
        return numberOfSpace;
    }

    private void multiLineComment1(String line) {
        if (Pattern.matches("[/][*].*", line.trim())) {
            comment = false;
        }

    }

    private void multiLineComment2(String line) {
        if (line.contains("*/")) {
            comment = true;
        }
    }

    public void checker() {
        multiLineComment1(line);
        if (!Pattern.matches("[/][/].*", line.trim()) && comment) {
            CheckStyle checkStyle = new CheckStyle();
            packageChecker();
            importChecker();
            classChecker(checkStyle);
            lineChecker(checkStyle);
            nameChecker(checkStyle);
            switchChecker();
            spaceChecker();
            defaultChecker();
            whileChecker();
            forChecker();
            ifChecker();
        }
        multiLineComment2(line);
    }

    private void packageChecker() {
        if (Pattern.matches("package .*", line.trim())) {
            if (!package0) {
                System.out.println("//package must be in first line");
            }
        } else {
            package0 = false;
        }
    }

    private void importChecker() {
        if (Pattern.matches("import .*", line.trim())) {
            if (!import0) {
                System.out.println("//import must come after package");
            }
        }
    }

    private void classChecker(CheckStyle checkStyle) {
        if (Pattern.matches("public class.*", line.trim())) {
            import0 = false;
            if (!checkStyle.isUpperCamelCase(line.trim().substring(13))) {
                System.out.println("//the name of class should be UpperCamelCase");
            }
        }
    }

    private void lineChecker(CheckStyle checkStyle) {
        if (checkStyle.numberOfSemicolons(line) > 1 && !line.contains("for")) {
            System.out.println("//split this line to "
                    + checkStyle.numberOfSemicolons(line) + " line");
        }
        if (line.length() > 80) {
            System.out.println("//length of line should not be more than 80 char\n//suggestion to break code:");
            functionBreacker(line);
        }
    }

    private void nameChecker(CheckStyle checkStyle) {
        for (String declaration : declarations) {
            if (line.contains(declaration + " ")) {
                if (!checkStyle
                        .islowerCamelCase(line.substring(line.indexOf(declaration) + declaration.length() + 1))) {
                    System.out.println("//name of method should be lowerCamelCase");
                }
                if (!Pattern.matches("[a-zA-Z][a-zA-Z].*",
                        line.substring(line.indexOf(declaration) + declaration.length() + 1))
                        && !Pattern.matches("[f][o][r][ ].*[(].*[)][ ]*[{]", line.trim())) {
                    System.out.println("//name of method should more than two letter");
                }
            }

            if (line.contains(declaration + "[]")) {
                if (!checkStyle
                        .islowerCamelCase(line.substring(line.indexOf(declaration) + declaration.length() + 3))) {
                    System.out.println("//name of method should be lowerCamelCase");
                }
                if (!Pattern.matches("[a-zA-Z][a-zA-Z].*",
                        line.substring(line.indexOf(declaration) + declaration.length() + 3))
                        && !Pattern.matches("[f][o][r][ ]*[(].*[)][ ]*[{]", line.trim())) {
                    System.out.println("//name of method should more than two letter");
                }
            }
        }
    }

    private void spaceChecker() {
        if ((line.trim().length() > 0 && line.trim().substring(0, 1).equals("}"))
                || (!default0 && !Pattern.matches("[s][w][i][t][c][h][ ]*[(].*[)][ ]*[{]", line.trim()))) {
            if (checkSpace == true && !line.equals("")
                    && checkIndentation.getSpace() - 4 != spaceCalculator(line)) {
                System.out.println("//number of space should be "
                        + (checkIndentation.getSpace() - 4));
            }
        } else if (checkSpace == true && !line.equals("")
                && checkIndentation.getSpace() != spaceCalculator(line)) {
            System.out.println("//number of space should be "
                    + checkIndentation.getSpace());
        }
        checkIndentation.calculateNumberOfGiumes(line);
        checkIndentation.calculateSpace();
        if (line.length() == 0) {
            checkSpace = true;
        } else if (line.substring(line.length() - 1).equals(";")
                || line.substring(line.length() - 1).equals("}")
                || line.substring(line.length() - 1).equals("{")
                || line.substring(line.length() - 1).equals(":")) {
            checkSpace = true;
        } else {
            checkSpace = false;
        }
    }

    private void whileChecker() {
        if (line.trim().length() > 4 && line.trim().substring(0, 5).equals("while")) {
            if (!Pattern.matches("[w][h][i][l][e][ ]?[(].*[)][ ]?[{]", line.trim())) {
                System.out.println("//while have wrong structure");
            }
        }
    }

    private void forChecker() {
        if (line.trim().length() > 2 && line.trim().substring(0, 3).equals("for")) {
            if (!Pattern.matches("[f][o][r][ ]?[(].*[)][ ]?[{]", line.trim())) {
                System.out.println("//for have wrong structure");
            }
        }
    }

    private void ifChecker() {
        if (line.trim().length() > 6 && line.trim().substring(0, 7).equals("else if")) {
            if (!Pattern.matches("[}][e][l][s][e][ ][i][f][ ]?[(].*[)][ ]?[{]", line.trim())) {
                System.out.println("//else-if have wrong structure");
            }
        } else if (line.trim().length() > 3 && line.trim().substring(0, 4).equals("else")) {
            if (!Pattern.matches("[}][e][l][s][e][ ]?[(].*[)][ ]?[{]", line.trim())) {
                System.out.println("//else have wrong structure");
            }
        } else if (line.trim().length() > 1 && line.trim().substring(0, 2).equals("if")) {
            if (!Pattern.matches("[i][f][ ]?[(].*[)][ ]?[{]", line.trim())) {
                System.out.println("//if have wrong structure");
            }
        }
    }

    private void switchChecker() {
        if (line.trim().length() > 5 && line.trim().substring(0, 6).equals("switch")) {
            default0 = false;
            default1 = checkIndentation.getSpace();
            if (!Pattern.matches("[s][w][i][t][c][h][ ]?[(].*[)][ ]?[{]", line.trim())) {
                System.out.println("//switch have wrong structure");
            }
        }
    }

    private void defaultChecker() {
        if (!default0) {
            if (Pattern.matches("[d][e][f][a][u][l][t].*", line.trim())) {
                default0 = true;
            }
            if (default1 == checkIndentation.getSpace()) {
                default0 = true;
                System.out.println("//switch should have default");
            }
        }
    }

    private String spaceOfFunction1() {
        String line1 = "";
        if (line.contains("(")) {
            for (int i = 0; i < line.indexOf("(") + 1; i++) {
                line1 += " ";
            }
        } else if (line.contains("=")) {
            for (int i = 0; i < line.indexOf("=") + 1; i++) {
                line1 += " ";
            }
        }
        return line1;
    }

    private String spaceOfFunction2() {
        String line1 = "";
        for (int i = 0; i < spaceCalculator(line) + 1; i++) {
            line1 += " ";
        }
        return line1;
    }

    public void functionBreacker(String line) {
        String[] logicalExpressions = { ",", "+", "-", "/", "*", "||", "&&" };
        String lastIndex = null;

        if (line.length() < 80) {
            System.out.println("//" + spaceOfFunction1() + line.trim());
        } else {
            int max = 0;
            for (int i = 0; i < 7; i++) {
                if (line.substring(0, 80).contains(logicalExpressions[i])
                        && line.lastIndexOf(logicalExpressions[i]) > max) {
                    max = line.lastIndexOf(logicalExpressions[i]);
                    lastIndex = logicalExpressions[i];
                }
            }
        }
        if (line.length() > 80 && lastIndex != null) {
            if (lastIndex.equals(",")) {
                System.out.println("//" + line.substring(0, line.substring(0, 80)
                        .lastIndexOf(lastIndex) + 1));
                String line1 = spaceOfFunction2();
                line1 = line1 + line.substring(line.substring(0, 80).lastIndexOf(lastIndex) + 1);
                functionBreacker(line1);
            } else {
                System.out.println("//" + line.substring(0, line.substring(0, 80).lastIndexOf(lastIndex)));
                String line1 = spaceOfFunction2();
                line1 = line1 + line.substring(line.substring(0, 80).lastIndexOf(lastIndex));
                functionBreacker(line1);
            }
        }

    }

}
