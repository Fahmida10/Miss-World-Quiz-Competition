package missworldquiz;

public class Contestant {

    private String name;
    private char answers[] = new char[25];
    private int score;

    public Contestant( String n, char a[],  int s) {
        name = n;
        answers = a.clone();
        score = s;
    }
    
    public String getName() {
        return name;
    }

    public char getAnswers(int no) {
        return answers[no];
    }

    public void setName(String n) {
        name = n;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }
}