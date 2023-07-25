package missworldquiz;

import java.io.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;


public class Analysis extends MissWorldQuiz{
    
    //Text Files
    private File myf = new File("./src/TextFiles","answers.txt");
    private File myf2 = new File("./src/TextFiles","scores.txt");
    
    //Background Picture
    private final File bgf = new File("./src/OtherImages","bgResultAnalysis.jpg");
    private final ImageView imgBG;
    
    //MainStage, MainScene, and Pane
    private Stage mainStage = new Stage();
    private final Scene mainScene;
    private final Pane p;
    
    //Labels, Buttons, Double, and Int
    private final Label labTitle, labMean, labMax, labMin, labMode, labMedian, labSD;
    private final Button btnExit, btnHome, btnResult, btnAnalyse;
    private double mean, median, standardDev;
    private int max, min, mode;
    
    //Scores ArrayList
    ArrayList<Integer> candidateScore = new ArrayList<>();  //create arrayList
    
    
    public Analysis() throws IOException{
        
        //Button for Exiting
        btnExit = new Button("Exit");
        btnExit.setLayoutX(480);
        btnExit.setLayoutY(550);
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
        
        //Button for going back to home page 
        btnHome = new Button("Home Page");
        btnHome.setLayoutX(330);
        btnHome.setLayoutY(20);
        btnHome.setStyle("-fx-base: green");
        btnHome.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnHome.setTextFill(Color.WHITE);
        btnHome.setPrefWidth(100);
        btnHome.setPrefHeight(30); 
        btnHome.setOnAction(e -> {
            try {
                mainStage.hide();                //Hiding Current Stage
                this.start(mainStage);           //Linking to the Main Page        
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        //Button for going back to the results page
        btnResult = new Button("Back to Result");
        btnResult.setLayoutX(450);
        btnResult.setLayoutY(20);
        btnResult.setStyle("-fx-base: green");
        btnResult.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnResult.setTextFill(Color.WHITE);
        btnResult.setPrefWidth(100);
        btnResult.setPrefHeight(30); 
        btnResult.setOnAction(e -> {
            try {
                new Result();           //Link to go back to results page
                mainStage.hide();       //Hiding Current Stage
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        //Button to show analyses
        btnAnalyse = new Button("Analyse");
        btnAnalyse.setLayoutX(500);
        btnAnalyse.setLayoutY(450);
        btnAnalyse.setStyle("-fx-pref-width: 75px;");  
        btnResult.setStyle("-fx-base: green");
        btnResult.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnResult.setTextFill(Color.WHITE);
        btnResult.setPrefWidth(100);
        btnResult.setPrefHeight(30); 
        
        //Reading from the scores.txt
        readFromFile();
        
        //Label for Title
        labTitle = new Label("Below is the statistical analysis for all candidates: ");
        labTitle.setTextFill(Color.WHITE);
        labTitle.setLayoutX(25);
        labTitle.setLayoutY(75);
        labTitle.setStyle("-fx-font-size: 15pt;-fx-font-weight:bold;");
        
        //Label for maximum score
        setMax();
        labMax = new Label();
        labMax.setText("Maximum Score : " + String.valueOf(max) + "%");
        labMax.setTextFill(Color.WHITE);
        labMax.setLayoutX(25);
        labMax.setLayoutY(125);
        labMax.setStyle("-fx-font-size: 11pt;-fx-font-weight:bold;");
        
        //Label for minimum score
        setMin();
        labMin = new Label();
        labMin.setText("Minimum Score : " + String.valueOf(min) + "%");
        labMin.setTextFill(Color.WHITE);
        labMin.setLayoutX(25);
        labMin.setLayoutY(200);
        labMin.setStyle("-fx-font-size: 11pt;-fx-font-weight:bold;");
        
        //Label for mean score
        setMean();
        labMean = new Label();
        labMean.setText("Mean Score : " + String.valueOf((int) mean) + "%");
        labMean.setTextFill(Color.WHITE);
        labMean.setLayoutX(25);
        labMean.setLayoutY(275);
        labMean.setStyle("-fx-font-size: 11pt;-fx-font-weight:bold;");
        
        //Label for mode
        mode = setMode();
        labMode = new Label();
        labMode.setText("Mode Score : " + String.valueOf(mode) + "%");
        labMode.setTextFill(Color.WHITE);
        labMode.setLayoutX(25);
        labMode.setLayoutY(350);
        labMode.setStyle("-fx-font-size: 11pt;-fx-font-weight:bold;");
        
        //Label for Median
        setMedian();
        labMedian = new Label();
        labMedian.setText("Median Score : " + String.valueOf((int) median) + "%");
        labMedian.setTextFill(Color.WHITE);
        labMedian.setLayoutX(25);
        labMedian.setLayoutY(425);
        labMedian.setStyle("-fx-font-size: 11pt;-fx-font-weight:bold;");
        
        //Label for Standard Deviation
        standardDev = setStandardDev();
        labSD = new Label();
        labSD.setText("Standard Deviation : " + String.valueOf((int) standardDev) + "%");
        labSD.setTextFill(Color.WHITE);
        labSD.setLayoutX(25);
        labSD.setLayoutY(500);
        labSD.setStyle("-fx-font-size: 11pt;-fx-font-weight:bold;");
        
        //background Image
        Image bgImg = new Image(bgf.toURI().toString());
        imgBG = new ImageView(bgImg);
        imgBG.setFitWidth(600);
        imgBG.setFitHeight(600);
        
        //Setting up the Paneand adding nodes to pane
        p = new Pane();
        p.getChildren().addAll(imgBG, btnExit, btnHome, btnResult, labTitle, labMean);
        p.getChildren().addAll(labMax, labMin, labMode, labMedian, labSD);
        
        //Setting up the scene and stage
        mainScene = new Scene(p,600,600);
        mainStage.setScene(mainScene);
        mainStage.show();
    
    }
    
    //getMax
    private void setMax() {
        max = candidateScore.get(0);        //Assigning the first value to max
        
        for (int i = 1; i < candidateScore.size(); i++) {    //if array length is more than 1, it will start going through the array
            if (candidateScore.get(i) > max) {         // if current maximum value is lesser than next value, it will replace
                max = candidateScore.get(i);
            }
        }
    }
    
    //getMin
    private void setMin() {
        min = candidateScore.get(0);        //Assigning the first value to min
        
        for (int i = 1; i < candidateScore.size(); i++) { //if array length is more than 1, start going through the array
            if (candidateScore.get(i) < min) {         // if value is lesser than current minimum, it will replace
                min = candidateScore.get(i);
            }
        }
    }
       
    //getMean
    private void setMean() {
        mean = candidateScore.get(0);   //Assigning the first value to mean
        double sum = 0;                 //initialize sum
        
        for (int i = 0; i < candidateScore.size(); i++) {  //counts the array
            sum = sum + candidateScore.get(i);             // adds up all the values 
        }
        mean = (sum) / (candidateScore.size()); //total divides by the number of elements
    }
    
    //getMode
    private int setMode() {
        mode = candidateScore.get(0);       //Assigning the first value to mode
        int maxCount = 0;                   //initialize maxCount
        
        for (int i = 0; i < candidateScore.size(); i++) {
            int value = candidateScore.get(i);
            int count = 1;          //count the number of values
            for (int j = 0; j < candidateScore.size(); j++) {
                if (candidateScore.get(j) == value) {      //if the number is the same, it will add
                    count++;
                    if (count > maxCount) {  //if count is more than maxCount, count will be the mode value
                        mode = value;
                        maxCount = count;
                    }
                }   
            }
        }
        
        return mode;
    }      
    
    //getMedian
    private double setMedian() {
        median = candidateScore.get(0);     //Assigning first value to median
        Collections.sort(candidateScore);   //sort array
        int total = candidateScore.size(); //count total element in an array
        
        if (total % 2 == 0) { //check if array is even
            int middle = candidateScore.get(total / 2) + candidateScore.get(total / 2 - 1);
            median = ((double) middle) / 2; //calculate average of array
        } 
        else {
            median = (double) candidateScore.get(candidateScore.size() / 2); //get the middle value
        }
        return median;
}
    
    // getStandardDeviation
    private double setStandardDev() {
        standardDev = candidateScore.get(0);        //Assigning first value to standard Dev
        double sum = 0;                             //Initializing sum
        double newSum = 0;                          //Initializing newSum

        for (int i = 0; i < candidateScore.size(); i++) {      //sum of all integers
            sum = sum + candidateScore.get(i);
        }
        
        double mean = (sum) / (candidateScore.size());     //calculating the mean by dividing length of array and sum of all integers

        for (int j = 0; j < candidateScore.size(); j++) {
            newSum = newSum + ((candidateScore.get(j) - mean) * (candidateScore.get(j) - mean));     //get new sum by substracting mean and square the result
        }
        
        double squaredDiff = (newSum) / (candidateScore.size());     // get squared difference by dividing sum by array length 
        standardDev = (Math.sqrt(squaredDiff));     //squareroot of squared difference

        return standardDev;
    }
    
    //Reading From File
    private void readFromFile() {
        Scanner sfile;  //scan the file
        int marks;      //Initializing marks
        
        try {
            sfile = new Scanner(myf2);  //scans analysis file
            while (sfile.hasNextLine()) { //scans the context of the file
                
                //Reading through the file
                String aLine = sfile.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(":");
                
                while (sline.hasNext()) {
                    marks = Integer.parseInt(sline.next());  //read marks
                    candidateScore.add(marks);  //add marks into candidateScore
                }
                
                sline.close();          //Closing Scanner
            }
            
            sfile.close();              //Closing Scanner
        } 
        catch (FileNotFoundException e) { 
            //if file not found, it will print not found
            System.out.println("File to read " + myf2 + " not found!"); 
        }
    }

}



    


