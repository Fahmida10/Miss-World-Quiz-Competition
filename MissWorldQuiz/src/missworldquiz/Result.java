package missworldquiz;

import java.io.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;


public class Result extends MissWorldQuiz { 
    
    //Declaring all the variables
    private Stage subStage = new Stage();
    private final Pane p;
    
    //Arrays and ArrayLists
    private LinkedList<Question> qList = new LinkedList<>();
    private LinkedList<Contestant> cList = new LinkedList<>();
    private final String answers[] = new String[25];
    private final String results[] = new String[25];
    
    //Text Files
    private final File myf = new File("./src/TextFiles","answers.txt");
    private final File myf2 = new File("./src/TextFiles","scores.txt");
    
    //Background Picture
    private final File bgf = new File("./src/OtherImages","bgResultAnalysis.jpg");
    private final ImageView imgBG;
    
    //Variables for Results Table
    private Label finalResult[] = new Label[25];
    private Label tableHeader = new Label("");
    private Label tableFooter = new Label("");
    private Label resultScore = new Label("");
    
    //Variables for score
    private final ArrayList<Integer> scoreArray = new ArrayList<>(); 
    int score = 0;
    
    //Buttons, Combobox, Int, and Labels
    private final Button btnHome, btnAnalysis, btnExit;
    private int choiceContestant;
    private ComboBox contestantList;
    private final Label fName;
    
    public Result() throws IOException,FileNotFoundException{
        //labels and buttons
        fName = new Label("Select Candidate");
        fName.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        fName.setTextFill(Color.WHITE);
        fName.setLayoutX(10);
        fName.setLayoutY(15);

        //creating linked list of contestants
        createContestantList();
        MyParam.setContList(cList);
        int n = cList.size();
        
        //Getting the names from text file
        String names[] = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = cList.get(i).getName();
        }
        
        //Contestant List ComboBox
        contestantList = new ComboBox();
        contestantList = new ComboBox(FXCollections.observableArrayList(names));
        contestantList.setPromptText("Contestants Names");
        contestantList.setLayoutX(150);
        contestantList.setLayoutY(10);
        
        //Positioning Table Header
        tableHeader.setLayoutX(10);
        tableHeader.setLayoutY(30);
        
        //Positioning Table Footer
        tableFooter.setLayoutX(10);
        tableFooter.setLayoutY(590);
        
        //Positioning Result Score 
        resultScore.setLayoutX(270);
        resultScore.setLayoutY(610);
        
        //Functionality of showing results based on name
        contestantList.setOnAction(e -> {
            try {
                tableHeader.setText("");
                tableFooter.setText("");
                resultScore.setText("");

                for (int j = 0; j < 25; j++) {
                    finalResult[j].setText("");
                }

                for (int i = 0; i < n; i++) {
                    if (contestantList.getValue().toString().equalsIgnoreCase(cList.get(i).getName())) {
                        choiceContestant = i;
                        break;
                    }
                }
                    
                displayResults();  
            } 
            catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
        });
        
        //Button for going back to home page 
        btnHome = new Button("Home Page");
        btnHome.setLayoutX(370);
        btnHome.setLayoutY(10);
        btnHome.setStyle("-fx-base: green");
        btnHome.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnHome.setTextFill(Color.WHITE);
        btnHome.setPrefWidth(100);
        btnHome.setPrefHeight(30);
        btnHome.setOnAction(e -> {
            try {
                this.start(subStage);           //Linking to the Main Page
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });

        //Button for Analysis page 
        btnAnalysis = new Button("Analysis");
        btnAnalysis.setLayoutX(480);
        btnAnalysis.setLayoutY(10);
        btnAnalysis.setStyle("-fx-base: green");
        btnAnalysis.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnAnalysis.setTextFill(Color.WHITE);
        btnAnalysis.setPrefWidth(100);
        btnAnalysis.setPrefHeight(30);
        btnAnalysis.setOnAction(e -> {
            try {
                saveToFile(score);
                subStage.hide();
                new Analysis();             //Link to go to Analysis
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });    
        
        //Button for Exiting
        btnExit = new Button("Exit");
        btnExit.setLayoutX(590);
        btnExit.setLayoutY(10);
        btnExit.setStyle("-fx-base: red");
        btnExit.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnExit.setTextFill(Color.WHITE);
        btnExit.setPrefWidth(100);
        btnExit.setPrefHeight(30); 
        btnExit.setOnAction(e -> {
            try {
                new FileWriter(myf).close();        //Erasing Contestant Answers
                new FileWriter(myf2).close();       //Erasing scores
                System.exit(0);                     //Exiting the Program
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });

        //background Image
        Image bgImg = new Image(bgf.toURI().toString());
        imgBG = new ImageView(bgImg);
        imgBG.setFitWidth(700);
        imgBG.setFitHeight(640);
        
        //Setting up the Pane
        p = new Pane();
        p.getChildren().addAll(imgBG,fName,contestantList,tableHeader,tableFooter,resultScore);
        p.getChildren().addAll(btnHome,btnAnalysis,btnExit);
        
        //Adding the Table data into the pane
        for (int i = 0; i < 25; i++) {
            finalResult[i] = new Label();
            p.getChildren().add(finalResult[i]);
        }
        
        //Setting the Scene
        Scene mainScene2 = new Scene(p,700,640);
        subStage.setScene(mainScene2);          //Setting Stage after Continue button is pressed
        subStage.show();                        //Showing the mainStage  
    }
    
    private void createContestantList() {
        //Declaring all the variables
        Scanner sfile;
        String name;
        char answers[] = new char[25];
        Contestant cont;
        
        //Try Statement
        try {
            sfile = new Scanner(myf);                //Scanning File
            String aLine = sfile.nextLine();        //Going through the File
            Scanner sline = new Scanner(aLine);     //Scanning Line

            //While Loop to run as long as there is a line
            while (sfile.hasNextLine()) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");        //Using Delimiter to get values
                name = sline.next();            //Assigning the name
                
                //For loop for go through all the other values
                for (int i = 0; i < 25; i++) {
                    answers[i] = sline.next().charAt(0);
                }
                
                sline.close();              //Closing Scanner
                
                //Adding to Array
                cont = new Contestant(name, answers, 20);
                cList.add(cont);
            }
            
            sfile.close();             //Closing Scanner
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + myf + " not found!");
        }
    }
    
    public void displayResults() throws FileNotFoundException {
        //Declaring the Variables
        int x = 10;
        int y = 80;
        int right = 0;
        String status;
        Integer num;
        String res;
        String ans;
        qList = MyParam.getQuesList();
        
        //For loop to get all Results
        for (int i = 0; i < 25; i++) {
            results[i] = String.valueOf(cList.get(choiceContestant).getAnswers(i));
        }
        
        //For loop to get all Answers
        for (int i = 0; i < 25; i++) {
            answers[i] = String.valueOf(qList.get(i).getAnswer());
        }
        
        //Try Statement
        try {
            //For loop to go through Array
            for (int i = 0; i < 25; i++) {

                //IF statement to check answer correct or not
                if (results[i].equals(answers[i])) {
                    status = "Correct";
                    right++;
                } else {
                    status = "Wrong";
                }

                num = i + 1;            //Adding number of correct answer

                //Assigning answer and result to variables
                res = results[i];
                ans = answers[i];
                
                //Printing Values
                String output = String.format("%-5s%-5s%-5s%-35s%-5s%-30s%-5s%-10s%s\n", "||", num, "||", res, "||", ans, "||", status, "||");
                String header = "======================================================================================================\n"
                        + String.format("%-5s%-5s%-5s%-35s%-5s%-30s%-5s%-10s%s\n", "||", "No.", "||", "Your Answer", "||", "Correct Answer", "||", "Result", "||"
                                + "\n======================================================================================================");

                tableHeader.setText("\n" + header);
                tableHeader.setFont(Font.font("consolas", FontWeight.BOLD, 12));
                tableHeader.setTextFill(Color.WHITE);

                finalResult[i].setText("\n" + output);
                finalResult[i].setFont(Font.font("consolas", 12));
                finalResult[i].setLayoutX(x);
                finalResult[i].setLayoutY(y);
                finalResult[i].setTextFill(Color.WHITE);
                y += 20;

                tableFooter.setText("======================================================================================================");
                tableFooter.setFont(Font.font("consolas", FontWeight.BOLD, 12));
                tableFooter.setTextFill(Color.WHITE);
            }  
            
            //Getting Percentage
            score = right * 4;
            scoreArray.add(score);
            cList.get(choiceContestant).setScore(score);            //Saving score
            resultScore.setText("Your Score: "+ score  + "%");      //Printing score
            resultScore.setFont(Font.font("Verdana",FontWeight.BOLD, 20));         //Setting Font
            resultScore.setTextFill(Color.RED);                 //Setting Font Color
        } 
        catch (NullPointerException npe) {
            System.out.println(npe);
        }
    }
    
    private void saveToFile( int s) {
        try {
            FileWriter fw = new FileWriter(myf2, true);
            PrintWriter pw = new PrintWriter(fw);
            
            //Printing the percentages
            pw.print(":");          //Printing a Colon after each answer
            pw.print(s + ":");
            pw.println();
            
            //Closing the printer
            pw.close();
        } 
        catch (IOException e) {
            System.out.println("MY ERROR : " + e);
        }
    }
}
