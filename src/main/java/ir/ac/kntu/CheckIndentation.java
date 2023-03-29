package ir.ac.kntu;

public class CheckIndentation {
    private int numberOfGiumes=0;
    private int space =numberOfGiumes*4;
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
    public int getSpace(){
        return space;
    }
}
