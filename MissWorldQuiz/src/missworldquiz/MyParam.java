package missworldquiz;


import java.util.LinkedList;

public class MyParam {

    private static char choices[] = new char[25];
    private static LinkedList<Question> quesList = new LinkedList<Question>();
    private static LinkedList<Contestant> contList = new LinkedList<Contestant>();

    public static void setQuesList(LinkedList<Question> q) {
        quesList = q;
    }

    public static void setContList(LinkedList<Contestant> c) {
        contList = c;
    }

    public static LinkedList<Question> getQuesList() {
        return quesList;
    }

    public static void setChoice(int n, char s) {
        choices[n] = s;
    }

}
