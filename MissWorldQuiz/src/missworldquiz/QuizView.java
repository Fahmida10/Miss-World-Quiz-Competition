package missworldquiz;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class QuizView extends MissWorldQuiz {
    
    //Getting text files used 
    private final File myf = new File("./src/TextFiles", "inputdata.txt"); 
    private final File myf2 = new File("./src/TextFiles", "answers.txt"); 
    
    //Getting background image
    private final File bgf = new File("./src/OtherImages","bgQuiz.jpg");
    private final ImageView imgBG;
    
    //Country Flags
    private final File Tonga = new File("./src/Flags","Tonga.jpg");
    private final File Turkmenistan = new File("./src/Flags","Turkmenistan.jpg");
    private final File Turkey = new File("./src/Flags","Turkey.jpg");
    private final File Tunisia = new File("./src/Flags","Tunisia.jpg");
    private final File Trinidad = new File("./src/Flags","Trinidad.jpg");
    private Image img;
    private final ImageView imgV;
    
    //Labels,int,ImageView, and RadioButton for displaying questions
    private int totQues = 0;
    private int activeQ = 1;
    private final Label labNameDesc, labQuesNo, labQues, labName;
    private final ImageView imgQues, imgA, imgB, imgC, imgD; 
    private final Label labA, labB, labC, labD;
    private final RadioButton radChoice1, radChoice2, radChoice3, radChoice4;
    private final ToggleGroup grpChoices;
    private final LinkedList<Question> quesList = new LinkedList<>();
    private String userCountry;
    
    //Buttons
    private final Button btnHome, btnPrev, btnNext, btnSubmit, btnExit;
    
    //Panes
    private final Pane p1,p2;
    
    //Timer
    private long minutes, seconds;
    private long totalSec = 300;
    private final Label labTimer;
    private final Timer timer = new Timer();
    
    //Music File
    private String musicFile;
    private Media mediaFile;
    private MediaPlayer mdPlayer;
    
    //Instantiating the Radio Buttons
    Stage mainStage = new Stage();
    
    Question ques;
    
    public QuizView() {
        
        //Flag
        userCountry = Users.getCountry();
        
        switch(userCountry){
            case "Tonga":
                img = new Image(Tonga.toURI().toString());
                break;
            case "Turkmenistan":
                img = new Image(Turkmenistan.toURI().toString());
                break;
            case "Tunisia":
                img = new Image(Tunisia.toURI().toString());
                break;
            case "Trinidad and Tobago":
                img = new Image(Trinidad.toURI().toString());
                break;
            case "Turkey":
                img = new Image(Turkey.toURI().toString());
                break;
        } 
        
        imgV = new ImageView(img);
        imgV.setFitWidth(130);
        imgV.setFitHeight(80);
        imgV.setLayoutX(230);
        imgV.setLayoutY(20);
        
        //Label Name
        labNameDesc = new Label("Contestant Name"); 
        labNameDesc.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        labNameDesc.setTextFill(Color.BLACK);
        labNameDesc.setLayoutX(30); 
        labNameDesc.setLayoutY(30);
        
        //Label for Timer
        labTimer = new Label("Time Remaining"); 
        labTimer.setTextFill(Color.BLACK);
        labTimer.setLayoutX(30); 
        labTimer.setLayoutY(50);
        
        //Label for Name
        labName = new Label(Users.getUsername()); 
        labName.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        labName.setTextFill(Color.BLACK);
        labName.setLayoutX(160);
        labName.setLayoutY(30); 
        
        //Label for Question Number
        labQuesNo = new Label(""); 
        labQuesNo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        labQuesNo.setTextFill(Color.BLACK);
        labQuesNo.setLayoutX(25); 
        labQuesNo.setLayoutY(75); 
        labQuesNo.setStyle("-fx-font-size: 12pt;-fx-font-weight:bold;");
        
        //Label for Question
        labQues = new Label(""); 
        labQues.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        labQues.setTextFill(Color.BLACK);
        labQues.setLayoutX(25); 
        labQues.setLayoutY(100); 
        labQues.setStyle("-fx-font-size: 12pt;-fx-font-weight:bold;");
        
        //Image for Question type 2
        imgQues = new ImageView();
        imgQues.setLayoutX(25);
        imgQues.setLayoutY(75);
        imgQues.setFitHeight(200);
        imgQues.setFitWidth(230);
        
        //Image A
        imgA = new ImageView();
        imgA.setLayoutX(100);
        imgA.setLayoutY(75);
        imgA.setFitHeight(100);
        imgA.setFitWidth(180);
        
        //Image B
        imgB = new ImageView();
        imgB.setLayoutX(100);
        imgB.setLayoutY(185);
        imgB.setFitHeight(100);
        imgB.setFitWidth(180);
        
        //Image C
        imgC = new ImageView();
        imgC.setLayoutX(100);
        imgC.setLayoutY(295);
        imgC.setFitHeight(100);
        imgC.setFitWidth(180);
        
        //Image D
        imgD = new ImageView();
        imgD.setLayoutX(100);
        imgD.setLayoutY(405);
        imgD.setFitHeight(100);
        imgD.setFitWidth(180);
        
        //Label A
        labA = new Label("A");
        labA.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        labA.setTextFill(Color.BLACK);
        labA.setLayoutX(25);
        
        //Radio Button 1
        radChoice1 = new RadioButton("");
        radChoice1.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        radChoice1.setTextFill(Color.BLACK);
        radChoice1.setLayoutX(50);
        
        //Label B
        labB = new Label("B");
        labB.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        labB.setTextFill(Color.BLACK);
        labB.setLayoutX(25);
        
        //Radio Button 2
        radChoice2 = new RadioButton("");
        radChoice2.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        radChoice2.setTextFill(Color.BLACK);
        radChoice2.setLayoutX(50);
        
        //Label C
        labC = new Label("C");
        labC.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        labC.setTextFill(Color.BLACK);
        labC.setLayoutX(25);
        
        //Radio Button 3
        radChoice3 = new RadioButton("");
        radChoice3.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        radChoice3.setTextFill(Color.BLACK);
        radChoice3.setLayoutX(50);
        
        //Label D
        labD = new Label("D");
        labD.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        labD.setTextFill(Color.BLACK);
        labD.setLayoutX(25);
        
        //Radio Button 4
        radChoice4 = new RadioButton("");
        radChoice4.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        radChoice4.setTextFill(Color.BLACK);
        radChoice4.setLayoutX(50);
        
        //Assigning the radio button to toggle group 
        grpChoices = new ToggleGroup();
        radChoice1.setToggleGroup(grpChoices); 
        radChoice2.setToggleGroup(grpChoices); 
        radChoice3.setToggleGroup(grpChoices); 
        radChoice4.setToggleGroup(grpChoices);
        
        //Question Pane
        p1 = new Pane(); 
        p1.setLayoutX(25); 
        p1.setLayoutY(75); 
        p1.getChildren().addAll(imgQues,labA,imgA,radChoice1,labB,imgB);
        p1.getChildren().addAll(radChoice2,labC,imgC,radChoice3,labD,imgD,radChoice4);
        
        //Home Button
        btnHome = new Button("Home Page"); 
        btnHome.setLayoutX(460); 
        btnHome.setLayoutY(10); 
        btnHome.setStyle("-fx-base: green");
        btnHome.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnHome.setTextFill(Color.WHITE);
        btnHome.setPrefWidth(100);
        btnHome.setPrefHeight(30); 
        btnHome.setOnAction(e ->{
            try {
                this.start(mainStage);
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        //Previous Button
        btnPrev = new Button("Prev"); 
        btnPrev.setLayoutX(350); 
        btnPrev.setLayoutY(260); 
        btnPrev.setStyle("-fx-base: green");
        btnPrev.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnPrev.setTextFill(Color.WHITE);
        btnPrev.setPrefWidth(100);
        btnPrev.setPrefHeight(30); 
        btnPrev.setDisable(true);
        
        //Next Button 
        btnNext = new Button("Next"); 
        btnNext.setLayoutX(350); 
        btnNext.setLayoutY(310); 
        btnNext.setStyle("-fx-base: green");
        btnNext.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnNext.setTextFill(Color.WHITE);
        btnNext.setPrefWidth(100);
        btnNext.setPrefHeight(30);
        
        //Submit Button
        btnSubmit = new Button("Submit"); 
        btnSubmit.setLayoutX(350); 
        btnSubmit.setLayoutY(360); 
        btnSubmit.setStyle("-fx-base: orange");
        btnSubmit.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnSubmit.setTextFill(Color.WHITE);
        btnSubmit.setPrefWidth(100);
        btnSubmit.setPrefHeight(30);

        //Exit Button
        btnExit = new Button("Exit"); 
        btnExit.setLayoutX(350); 
        btnExit.setLayoutY(410); 
        btnExit.setStyle("-fx-base: Red");
        btnExit.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnExit.setTextFill(Color.WHITE);
        btnExit.setPrefWidth(100);
        btnExit.setPrefHeight(30);
        btnExit.setOnAction(e -> {
            try {
                new FileWriter(myf2).close();        //Erasing Contestant Answers
                System.exit(0);                     //Exiting the Program
            } 
            catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        //Reading the questions from the text file
        readFromFile();
        MyParam.setQuesList(quesList);
        
        //Action Handlers for all the radio buttons
        radChoice1.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, true); 
            quesList.get(activeQ-1).setSelected(1, false); 
            quesList.get(activeQ-1).setSelected(2, false); 
            quesList.get(activeQ-1).setSelected(3, false);
            MyParam.setChoice(activeQ - 1, 'A');
        });
        
        radChoice2.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false); 
            quesList.get(activeQ-1).setSelected(1, true); 
            quesList.get(activeQ-1).setSelected(2, false); 
            quesList.get(activeQ-1).setSelected(3, false);
            MyParam.setChoice(activeQ - 1, 'B');
        });
        
        radChoice3.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false); 
            quesList.get(activeQ-1).setSelected(1, false); 
            quesList.get(activeQ-1).setSelected(2, true); 
            quesList.get(activeQ-1).setSelected(3, false);
            MyParam.setChoice(activeQ - 1, 'C');
        });
        
        radChoice4.setOnAction(e -> {
            quesList.get(activeQ-1).setSelected(0, false); 
            quesList.get(activeQ-1).setSelected(1, false); 
            quesList.get(activeQ-1).setSelected(2, false); 
            quesList.get(activeQ-1).setSelected(3, true);
            MyParam.setChoice(activeQ - 1, 'D');
        });
        
        //Setting Next button disabled if the total questions is 1
        if (totQues == 1)
            btnNext.setDisable(true);
        
        //Action Handler for the Next button 
        btnNext.setOnAction(e -> {
            activeQ++;
            btnPrev.setDisable(false);
            if (activeQ == totQues)
                btnNext.setDisable(true);
            reloadQues();
        });
        
        //Action Handler for the Previous Button
        btnPrev.setOnAction(e -> {
            activeQ--;
            btnNext.setDisable(false);
            if (activeQ == 1)
                btnPrev.setDisable(true);
            reloadQues();
        });
        
        //Action Handler for the Submit Button
        btnSubmit.setOnAction(e -> {
            appendToFile(); 
            mainStage.hide();
            try {
                new Result();
                timer.cancel();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        //Loading the question
        reloadQues();
        
        //background Image
        Image bgImg = new Image(bgf.toURI().toString());
        imgBG = new ImageView(bgImg);
        imgBG.setFitWidth(570);
        imgBG.setFitHeight(600);
        
        //Creating the scene
        p2 = new Pane();
        p2.getChildren().addAll(imgBG,imgV,labNameDesc,labTimer,labName); 
        p2.getChildren().addAll(labQuesNo,labQues,p1,btnHome,btnNext,btnPrev,btnSubmit,btnExit); 
        Scene mainScene2 = new Scene(p2,570,600);       

        mainStage.setScene(mainScene2);          //Setting Stage after Continue button is pressed
        mainStage.show();                        //Showing the mainStage  
        
        //Starting the timer
        setTimer(mainStage);
        
    }
    
    public void reloadQues(){
        //Setting Text Labels for Question Number and Question
        labQuesNo.setText("Question " + Integer.toString(activeQ) + " / " + totQues); 
        labQues.setText(quesList.get(activeQ-1).getTheQues()); 
        
        //Setting Text for Radio Button
        radChoice1.setText(quesList.get(activeQ-1).getChoice(0)); 
        radChoice2.setText(quesList.get(activeQ-1).getChoice(1)); 
        radChoice3.setText(quesList.get(activeQ-1).getChoice(2)); 
        radChoice4.setText(quesList.get(activeQ-1).getChoice(3)); 
        
        //Setting Image Question as null
        imgQues.setImage(null);
        
        //Setting Image A,B,C,D as null
        imgA.setImage(null);
        imgB.setImage(null);
        imgC.setImage(null);
        imgD.setImage(null);
        
        //Setting the functionality for type 1 questions
        if (quesList.get(activeQ-1).getType() == 1) {
            labA.setLayoutY(100);
            radChoice1.setLayoutY(100);
            
            labB.setLayoutY(200);
            radChoice2.setLayoutY(200);
            
            labC.setLayoutY(300);
            radChoice3.setLayoutY(300);
            
            labD.setLayoutY(400);
            radChoice4.setLayoutY(400);
        }
        
        //Setting the functionality for type 2 questions
        if (quesList.get(activeQ-1).getType() == 2) {
            Image img = new Image(getClass().getResourceAsStream("/QuizImages/" + quesList.get(activeQ- 1).getQuesPic())); 
            imgQues.setImage(img);
            
            labA.setLayoutY(300); 
            radChoice1.setLayoutY(300); 
            
            labB.setLayoutY(340); 
            radChoice2.setLayoutY(340); 
            
            labC.setLayoutY(380); 
            radChoice3.setLayoutY(380); 
            
            labD.setLayoutY(420); 
            radChoice4.setLayoutY(420);
        }
        
        //Setting the functionality for type 3 questions
        if (quesList.get(activeQ-1).getType() == 3) {
            Image img1 = new Image(getClass().getResourceAsStream("/QuizImages/" + quesList.get(activeQ- 1).getChoice(0)));
            
            Image img2 = new Image(getClass().getResourceAsStream("/QuizImages/" + quesList.get(activeQ- 1).getChoice(1)));
            
            Image img3 = new Image(getClass().getResourceAsStream("/QuizImages/" + quesList.get(activeQ- 1).getChoice(2)));
            
            Image img4 = new Image(getClass().getResourceAsStream("/QuizImages/" + quesList.get(activeQ- 1).getChoice(3)));
            
            imgA.setImage(img1);
            imgB.setImage(img2);
            imgC.setImage(img3);
            imgD.setImage(img4);
            
            labA.setLayoutY(75);
            radChoice1.setLayoutY(75);
            radChoice1.setText("");
            
            labB.setLayoutY(185);
            radChoice2.setLayoutY(185);
            radChoice2.setText("");
            
            labC.setLayoutY(295);
            radChoice3.setLayoutY(295);
            radChoice3.setText("");
            
            labD.setLayoutY(405);
            radChoice4.setLayoutY(405);
            radChoice4.setText("");
        }
        
        //Getting the selected option
        radChoice1.setSelected(quesList.get(activeQ- 1).getSelected(0));
        radChoice2.setSelected(quesList.get(activeQ- 1).getSelected(1));
        radChoice3.setSelected(quesList.get(activeQ- 1).getSelected(2));
        radChoice4.setSelected(quesList.get(activeQ- 1).getSelected(3));
    }
    
    private void readFromFile(){
        
        //Declaring the variables
        Scanner sfile;
        int type;
        char answer;
        String theQues;
        String choices[] = new String[4];
        String quesPic;
        
        //Try Statement 
        try {
            sfile = new Scanner(myf);                       //Initializing Scanner
            String aLine = sfile.nextLine();                //Assigning each line to string
            Scanner sline = new Scanner(aLine);             //Scanner for each line
            totQues = Integer.parseInt(sline.next());       //Getting the total number of Questions

            //For loop to go through the test file
            for (int k = 1; k <= totQues; k++) {
                aLine = sfile.nextLine();
                sline = new Scanner(aLine);
                sline.useDelimiter(":");
                type = Integer.parseInt(sline.next());
                answer = sline.next().charAt(0);
                theQues = sline.next();
                quesPic = "";
                
                //Assigning Qeustion Picture for type 2 questions
                if (type == 2)
                    quesPic = sline.next();
                
                //Getting the options
                choices[0] = sline.next();
                choices[1] = sline.next();
                choices[2] = sline.next();
                choices[3] = sline.next();
                
                //Closing the scanner
                sline.close();
                
                //Storing the values in an array
                ques = new Question(type, answer, theQues, choices, quesPic);
                quesList.add(ques);
            }
            
            //Closing the scanner
            sfile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File to read " + myf + " notfound!"); 
        }
    }
    
    public void appendToFile(){
        
        //Try Statement
        try {
            String selected;                        //Declaring Variables
            PrintWriter pw = new PrintWriter(new FileWriter(myf2, true));    //Initializing PrintWriter
            pw.println();
            pw.print(Users.getUsername());         //Printing the Username
            pw.print(":");          //Printing a Colon after each answer

            //For loop for the storing of each answer to the questions
            for(int i = 0; i < 25; i++) {
                selected = "null";
                for (int j = 0; j < 4; j++) {
                    //Retreiving the selected option and saving it into the text file
                    if (quesList.get(i).getSelected(j)) {
                        switch(j){
                            case 0:
                                selected = "A";
                                break;
                            case 1:
                                selected = "B";
                                break;
                            case 2:
                                selected = "C";
                                break;
                            case 3:
                                selected = "D";
                                break;
                        }
                    }
                }
                pw.print(selected + ":");
                
            }
            pw.close();                 //Closing PrintWriter
            pw.print("\n");             //Printing New Line
        }
        catch (IOException e) {
        } 
    }
    
    //method to format how the time is displayed
    private static String format(long value){
        if (value<10){
            return 0+""+value;      //putting a 0 ahead if minutes less than 10
        }
        else
            return ""+value;        //Leaving the minutes as it is
    }
	
    //method to print the timer label
    public void printTime(Stage mainStage){
        //Declaring the minutes and seconds
        minutes = TimeUnit.SECONDS.toMinutes(totalSec);
        seconds = totalSec - (minutes*60); 
        
        //Printing in the timer
        labTimer.setText("TIMER : " + format(minutes) + " : " + format(seconds));
        labTimer.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        
        //Play Music at the start
        if(totalSec == 300){
            musicFile = "./src/Audio/countdown.wav"; 
            mediaFile = new Media(new File(musicFile).toURI().toString());
            mdPlayer = new MediaPlayer(mediaFile);
            mdPlayer.play();
        }
        
        if(totalSec == 30 || totalSec == 1){
            musicFile = "./src/Audio/alert.wav"; 
            mediaFile = new Media(new File(musicFile).toURI().toString());
            mdPlayer = new MediaPlayer(mediaFile);
            mdPlayer.play();
        }
        
        //Formatting the timer at 30 seconds
        if(totalSec<=30){
            labTimer.setTextFill(Color.web("#ff0000", 0.8));
        }
        
        //Showing the alert at 30 seconds
        if(totalSec==30){
            PopUp popup = new PopUp("\t30 seconds left.\t\n\tHurry UP!\t", mainStage);
            popup.showPopupMessage();
        }
        
        //Reducing the seconds
        totalSec --;
    }
    
    //method to set what the timer will do
    public void setTimer(Stage mainStage){
        
        //Starting the timer
        TimerTask task = new TimerTask(){
            @Override 	//overriding method 'run'
            public void run(){
                
                Platform.runLater(() -> {
                    //Printing the timer
                    printTime(mainStage);
                    
                    //when time is up, candidate cannot select anything else
                    if(totalSec<=0){
                        //Setting timer at 00:00
                        labTimer.setText("T I M E R : 00:00");
                        
                        //Setting all radio button to disables
                        radChoice1.setDisable(true);
                        radChoice2.setDisable(true);
                        radChoice3.setDisable(true);
                        radChoice4.setDisable(true);
                        
                        //Setting the next and previous buttons as disabled
                        btnPrev.setDisable(true);
                        btnNext.setDisable(true);
                        
                        //The popupMessage of Time up
                        PopUp popup = new PopUp(" Time Up\n Please Press Submit\n To Proceed To Results!\t", mainStage);
                        popup.showPopupMessage();
                        
                        //Stopping the timer
                        timer.cancel();
                    }
                });  
            } 
        };
        
        //scheduling timer to change every second (1000ms)
        timer.schedule(task, 0, 1000);
    }
    
}
