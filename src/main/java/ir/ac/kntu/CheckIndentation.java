package ir.ac.kntu;

public class CheckIndentation {
    private int numberOfGiumes=0;
    private int space =0;
    public void calculateNumberOfGiumes(String line){
        for(int i=0;i<line.length();i++){
            if(line.charAt(i)=='{'){
                numberOfGiumes++;
            }
            if(line.charAt(i)=='}'){
                numberOfGiumes--;
            }
        }
    }
    public void calculateSpace(){
        space=numberOfGiumes*4;
    }
    public int getSpace(){
        return space;
    }
    public int getSpacee(){
        return numberOfGiumes;
    }
}
