package ir.ac.kntu;

public class CheckIndentation {
    private int numberOfGiumes = 0;
    private int space = 0;

    public void calculateNumberOfGiumes(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                numberOfGiumes++;
            }
            if (line.charAt(i) == '}') {
                numberOfGiumes--;
            }
        }
        isGiumesUse(line);
    }

    public void calculateSpace() {
        space = numberOfGiumes * 4;
    }

    public int getSpace() {
        return space;
    }

    private void isGiumesUse(String line) {
        if (line.contains("\"")) {
            calculateNumberOfGiumes1(line.substring(line.indexOf("\""),
                    line.lastIndexOf("\"")));
        }
        if (line.contains("\'")) {
            calculateNumberOfGiumes1(line.substring(line.indexOf("\'"),
                    line.lastIndexOf("\'")));
        }
    }

    private void calculateNumberOfGiumes1(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                numberOfGiumes--;
            }
            if (line.charAt(i) == '}') {
                numberOfGiumes++;
            }
        }
    }
}
