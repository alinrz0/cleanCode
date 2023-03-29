package ir.ac.kntu;

import java.util.regex.Pattern;

public class LineReader {
    private String line;
    private boolean package0=true;
    private boolean import0=true;
    private boolean checkSpace=true;
    CheckIndentation checkIndentation=new CheckIndentation();
    private String[] declarations={"int","float","boolean","double","String","char","long","byte","void"};
    public void setLine(String line){
        this.line=line;
    }
    public int spaceCalculator(String line){
        int numberOfSpace=0;
        while(line.length()>0&&line.length()>numberOfSpace&& line.charAt(numberOfSpace)==' '){
            numberOfSpace++;
        }
        return numberOfSpace;
    }
    public void checker(){
        CheckStyle checkStyle=new CheckStyle();
        if(Pattern.matches("package .*", line.trim())){
            if(!package0){
                System.out.println("//package must be in first line");
            }
        }else{
            package0=false;
        }
        if(Pattern.matches("import .*", line.trim())){
            if(!import0){
                System.out.println("//import must come after package");
            }
        }
        if(Pattern.matches("public class.*", line.trim())){
            import0=false;
            if(!checkStyle.isUpperCamelCase(line.trim().substring(13))){
                System.out.println("//the name of class should be UpperCamelCase");
            }
        }
        if(checkStyle.numberOfSemicolons(line)>1&&!line.contains("for")){
            System.out.println("//split this line to "+checkStyle.numberOfSemicolons(line)+" line");
        }
        if(line.length()>80){
            System.out.println("//length of line should not be more than 80 char");
        }
        for(String declaration:declarations){
            if(line.contains(declaration+" ")){
                if(!checkStyle.islowerCamelCase(line.substring(line.indexOf(declaration)+declaration.length()+1))){
                    System.out.println("//name of method should be lowerCamelCase");
                }
                if(!Pattern.matches("[a-zA-Z][a-zA-Z].*", line.substring(line.indexOf(declaration)+declaration.length()+1))&&!line.contains("for")){
                    System.out.println("//name of method should more than two letter");
                }
            }

            if(line.contains(declaration+"[]")){
                if(!checkStyle.islowerCamelCase(line.substring(line.indexOf(declaration)+declaration.length()+3))){
                    System.out.println("//name of method should be lowerCamelCase");
                }
                if(!Pattern.matches("[a-zA-Z][a-zA-Z].*", line.substring(line.indexOf(declaration)+declaration.length()+3))&&!line.contains("for")){
                    System.out.println("//name of method should more than two letter");
                }
            }
        }
        

        if(line.trim().length()>0&&line.trim().substring(0, 1).equals("}")){
            if(checkSpace==true&&!line.equals("")&& checkIndentation.getSpace()-4!=spaceCalculator(line)){
                System.out.println("//number of space should be "+(checkIndentation.getSpace()-4));
            }
        }else if(checkSpace==true&&!line.equals("")&& checkIndentation.getSpace()!=spaceCalculator(line)){
            System.out.println("//number of space should be "+checkIndentation.getSpace());
        }
        checkIndentation.calculateNumberOfGiumes(line);
        checkIndentation.calculateSpace();
        if(line.length()==0){
            checkSpace=true;
        }else if(line.substring(line.length()-1).equals(";")||line.substring(line.length()-1).equals("}")||line.substring(line.length()-1).equals("{")){
            checkSpace=true;
        }else{
            checkSpace=false;
        }
        if(line.contains("while")){
            if(!Pattern.matches("[w][h][i][l][e][(].*[)][{]", line.trim())){
                System.out.println("while have wrong structure");
            }
        }
        
    }


}
