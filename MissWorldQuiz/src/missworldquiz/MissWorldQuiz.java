package missworldquiz;

import javafx.application.Application;
import java.util.*;
import java.io.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.stage.*;

//E:\University\Year 1\Sem 2\Advanced Programming\MissWorldQuiz\src\missworldquiz\Images\Tunisia\Picture1.jpg

public class MissWorldQuiz extends Application{
    
    //Textfields, Buttons, Comboboxes, Strings
    private TextField txtID,Password;
    private Label labelID,labelUser,labelPass,labelCountry;
    private Button btnReset,btnLogin,btnContinue,btnExit;
    private ComboBox CountryCombo,UsernameCombo;
    private String username,ID;
    private ImageView imgV1,imgV2,imgV3;
    
    //Pane, MainScene
    private Pane p;
    private Scene mainScene;
    
    //Background Image
    private final File bgf = new File("./src/OtherImages","bgLogin.jpg");
    private ImageView imgBG;
    
    @Override
    public void start(Stage mainStage) throws FileNotFoundException, IOException{
        
    //----------------------Start of Entry Form---------------------------------
        //Setting up mainStage and Pane
        mainStage.setTitle("Miss World Quiz");
        p = new Pane();
        
        //Instantiating the Buttons
        Buttons btns = new Buttons();
        Users user = new Users();
        
        //Label ID
        labelID = new Label("ID");
        labelID.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        labelID.setTextFill(Color.BLACK);
        labelID.setLayoutX(140);
        labelID.setLayoutY(30);
        
        //TextField ID
        txtID = new TextField();
        txtID.setLayoutX(230);
        txtID.setLayoutY(25);
        txtID.setPromptText("Enter ID");
        txtID.setPrefHeight(30);
        txtID.setPrefWidth(170);
        
        //Label username
        labelUser = new Label("Name");
        labelUser.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        labelUser.setTextFill(Color.BLACK);
        labelUser.setLayoutX(140);
        labelUser.setLayoutY(70);
        
        //Textfield Username
        UsernameCombo = new ComboBox();
        InputStream is = getClass().getResourceAsStream("/TextFiles/contestant.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                Scanner aLine = new Scanner(line);
                aLine.useDelimiter(":");
                String name = aLine.next();
                String password = aLine.next();
                
                //Add Item
                UsernameCombo.getItems().add(name);

                sb.append(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        UsernameCombo.setLayoutX(230);
        UsernameCombo.setLayoutY(65);
        UsernameCombo.setValue("Select Username"); 
        UsernameCombo.setPrefWidth(170);
        UsernameCombo.setPrefHeight(30);
        
        //Label Password
        labelPass = new Label("Password");
        labelPass.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        labelPass.setTextFill(Color.BLACK);
        labelPass.setLayoutX(140);
        labelPass.setLayoutY(110);
        
        //TextField Password
        Password = new PasswordField();
        Password.setLayoutX(230);
        Password.setLayoutY(105);
        Password.setPromptText("Enter Password");
        Password.setPrefHeight(30);
        Password.setPrefWidth(170);
        
        //Label Country
        labelCountry = new Label("Country");
        labelCountry.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        labelCountry.setTextFill(Color.BLACK);
        labelCountry.setLayoutX(140);
        labelCountry.setLayoutY(150);
        
        //ComboBox Country
        CountryCombo = new ComboBox();
        CountryCombo.getItems().addAll(
            "Tonga",
            "Trinidad and Tobago",
            "Tunisia",
            "Turkey",
            "Turkmenistan"
        );
        CountryCombo.setLayoutX(230);
        CountryCombo.setLayoutY(145);
        CountryCombo.setValue("Select Country"); 
        CountryCombo.setPrefWidth(170);
        CountryCombo.setPrefHeight(30);
        CountryCombo.setOnAction(e -> {
            user.setCountry(CountryCombo.getValue().toString());
            
            if(CountryCombo.getValue().equals("Tonga")){
                //Button Image 1
                Image img1 = new Image(getClass().getResourceAsStream("/Images/Tonga/Picture1.jpeg"));
                imgV1 = new ImageView(img1);
                imgV1.setLayoutX(90);
                imgV1.setLayoutY(270);
                imgV1.setFitWidth(120);
                imgV1.setFitHeight(120);
                p.getChildren().addAll(imgV1);
                
                //Button Image 2
                Image img2 = new Image(getClass().getResourceAsStream("/Images/Tonga/Picture2.jpeg"));
                imgV2 = new ImageView(img2);
                imgV2.setLayoutX(220);
                imgV2.setLayoutY(270);
                imgV2.setFitWidth(120);
                imgV2.setFitHeight(120);
                p.getChildren().addAll(imgV2);
                
                //Button Image 3
                Image img3 = new Image(getClass().getResourceAsStream("/Images/Tonga/Picture3.jpeg"));
                imgV3 = new ImageView(img3);
                imgV3.setLayoutX(350);
                imgV3.setLayoutY(270);
                imgV3.setFitWidth(120);
                imgV3.setFitHeight(120);
                p.getChildren().addAll(imgV3);
            }
            else if(CountryCombo.getValue().equals("Trinidad and Tobago")){
                //Button Image 1
                Image img1 = new Image(getClass().getResourceAsStream("/Images/Trinidad/Picture1.jpeg"));
                imgV1 = new ImageView(img1);
                imgV1.setLayoutX(90);
                imgV1.setLayoutY(270);
                imgV1.setFitWidth(120);
                imgV1.setFitHeight(120);
                p.getChildren().addAll(imgV1);
                
                //Button Image 2
                Image img2 = new Image(getClass().getResourceAsStream("/Images/Trinidad/Picture2.jpeg"));
                imgV2 = new ImageView(img2);
                imgV2.setLayoutX(220);
                imgV2.setLayoutY(270);
                imgV2.setFitWidth(120);
                imgV2.setFitHeight(120);
                p.getChildren().addAll(imgV2);
                
                //Button Image 3
                Image img3 = new Image(getClass().getResourceAsStream("/Images/Trinidad/Picture3.jpeg"));
                imgV3 = new ImageView(img3);
                imgV3.setLayoutX(350);
                imgV3.setLayoutY(270);
                imgV3.setFitWidth(120);
                imgV3.setFitHeight(120);
                p.getChildren().addAll(imgV3);
            }
            else if(CountryCombo.getValue().equals("Tunisia")){
                //Button Image 1
                Image img1 = new Image(getClass().getResourceAsStream("/Images/Tunisia/Picture1.jpg"));
                imgV1 = new ImageView(img1);
                imgV1.setLayoutX(90);
                imgV1.setLayoutY(270);
                imgV1.setFitWidth(120);
                imgV1.setFitHeight(120);
                p.getChildren().addAll(imgV1);
                
                //Button Image 2
                Image img2 = new Image(getClass().getResourceAsStream("/Images/Tunisia/Picture2.jpg"));
                imgV2 = new ImageView(img2);
                imgV2.setLayoutX(220);
                imgV2.setLayoutY(270);
                imgV2.setFitWidth(120);
                imgV2.setFitHeight(120);
                p.getChildren().addAll(imgV2);
                
                //Button Image 3
                Image img3 = new Image(getClass().getResourceAsStream("/Images/Tunisia/Picture3.jpg"));
                imgV3 = new ImageView(img3);
                imgV3.setLayoutX(350);
                imgV3.setLayoutY(270);
                imgV3.setFitWidth(120);
                imgV3.setFitHeight(120);
                p.getChildren().addAll(imgV3);
            }
            else if(CountryCombo.getValue().equals("Turkey")){
                //Button Image 1
                Image img1 = new Image(getClass().getResourceAsStream("/Images/Turkey/Picture1.jpeg"));
                imgV1 = new ImageView(img1);
                imgV1.setLayoutX(90);
                imgV1.setLayoutY(270);
                imgV1.setFitWidth(120);
                imgV1.setFitHeight(120);
                p.getChildren().addAll(imgV1);
                
                //Button Image 2
                Image img2 = new Image(getClass().getResourceAsStream("/Images/Turkey/Picture2.jpeg"));
                imgV2 = new ImageView(img2);
                imgV2.setLayoutX(220);
                imgV2.setLayoutY(270);
                imgV2.setFitWidth(120);
                imgV2.setFitHeight(120);
                p.getChildren().addAll(imgV2);
                
                //Button Image 3
                Image img3 = new Image(getClass().getResourceAsStream("/Images/Turkey/Picture3.jpeg"));
                imgV3 = new ImageView(img3);
                imgV3.setLayoutX(350);
                imgV3.setLayoutY(270);
                imgV3.setFitWidth(120);
                imgV3.setFitHeight(120);
                p.getChildren().addAll(imgV3);
            }
            else if(CountryCombo.getValue().equals("Turkmenistan")){
                //Button Image 1
                Image img1 = new Image(getClass().getResourceAsStream("/Images/Turkmenistan/Picture1.jpeg"));
                imgV1 = new ImageView(img1);
                imgV1.setLayoutX(90);
                imgV1.setLayoutY(270);
                imgV1.setFitWidth(120);
                imgV1.setFitHeight(120);
                p.getChildren().addAll(imgV1);
                
                //Button Image 2
                Image img2 = new Image(getClass().getResourceAsStream("/Images/Turkmenistan/Picture2.jpeg"));
                imgV2 = new ImageView(img2);
                imgV2.setLayoutX(220);
                imgV2.setLayoutY(270);
                imgV2.setFitWidth(120);
                imgV2.setFitHeight(120);
                p.getChildren().addAll(imgV2);
                
                //Button Image 3
                Image img3 = new Image(getClass().getResourceAsStream("/Images/Turkmenistan/Picture3.jpeg"));
                imgV3 = new ImageView(img3);
                imgV3.setLayoutX(350);
                imgV3.setLayoutY(270);
                imgV3.setFitWidth(120);
                imgV3.setFitHeight(120);
                p.getChildren().addAll(imgV3);
            }  
        });
        
        //Button Reset
        btnReset = new Button("Reset"); 
        btnReset.setStyle("-fx-base: red");
        btnReset.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnReset.setTextFill(Color.WHITE);
        btnReset.setPrefWidth(100);
        btnReset.setPrefHeight(30);
        btnReset.setLayoutY(190);
        btnReset.setLayoutX(130);
        btnReset.setOnAction(e -> {
            btns.reset(txtID,Password);
            CountryCombo.setValue("Select Country");
            UsernameCombo.setValue("Select Username");
            imgV1.setImage(null);
            imgV2.setImage(null);
            imgV3.setImage(null);
            btnContinue.setDisable(true);
        });
        
        //Button Submit 
        btnLogin = new Button("Login");
        btnLogin.setLayoutX(240);
        btnLogin.setLayoutY(190);
        btnLogin.setStyle("-fx-base: green");
        btnLogin.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnLogin.setTextFill(Color.WHITE);
        btnLogin.setPrefWidth(100);
        btnLogin.setPrefHeight(30);  
        btnLogin.setOnAction(e -> {  
            try { 
                if (btns.confirmation()) {
                    if(user.CheckString(UsernameCombo.getValue().toString())) {
                        if(user.checkPassword(Password.getText(),UsernameCombo.getValue().toString())){
                            if(user.CheckNumber(txtID)) {
                                if(!CountryCombo.getSelectionModel().isEmpty()){
                                    user.loginSuccessful();
                                    btnContinue.setDisable(false);
                                }
                                else{
                                    user.chooseCountry();
                                    btnContinue.setDisable(true);
                                }
                            }
                            else {
                                user.invalidID();        // Alerts if user inputs characters inside the numeric fields
                                btnContinue.setDisable(true);
                            }    
                        }                      
                        else{
                            user.invalidPassword();     //Alerts user if user inputs wrong password
                            btnContinue.setDisable(true);
                        } 
                    }                        
                    else {
                        user.invalidUsername();        // Alert created if user inputs numbers inside the string fields
                    }
                }
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        });
        
        //Button Continue
        btnContinue = new Button("Continue");
        btnContinue.setLayoutX(350);
        btnContinue.setLayoutY(190);
        btnContinue.setStyle("-fx-base: green");
        btnContinue.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnContinue.setTextFill(Color.WHITE);
        btnContinue.setDisable(true);
        btnContinue.setPrefWidth(100);
        btnContinue.setPrefHeight(30);
        
        //Exit Button
        btnExit = new Button("Exit"); 
        btnExit.setLayoutX(240); 
        btnExit.setLayoutY(230); 
        btnExit.setStyle("-fx-base: Red");
        btnExit.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        btnExit.setTextFill(Color.WHITE);
        btnExit.setPrefWidth(100);
        btnExit.setPrefHeight(30);
        btnExit.setOnAction(e -> {
            System.exit(0);                     //Exiting the Program
        });
        
        //background Image
        Image bgImg = new Image(bgf.toURI().toString());
        imgBG = new ImageView(bgImg);
        imgBG.setFitWidth(550);
        imgBG.setFitHeight(400);
        
        //Scene Structure
        p.getChildren().addAll(imgBG,labelUser,UsernameCombo,labelCountry,CountryCombo,btnLogin);
        p.getChildren().addAll(btnReset,btnContinue,btnExit,labelID,txtID,labelPass,Password);
        mainScene = new Scene(p, 550, 400);
        mainStage.setScene(mainScene);
        mainStage.show();
    //-------------------------End of Entry Form--------------------------------
    
    //-----------------------Start of Test Form---------------------------------
        
        //Using Event handler so username and ID only set after the validation
        btnContinue.setOnAction(e -> {                    
            
            //Setting the ID and Username
            username = UsernameCombo.getSelectionModel().getSelectedItem().toString();
            ID = txtID.getText();
            user.setUser(username);
            user.setID(ID);
            
            //Calling the Quiz View
            new QuizView();
            mainStage.hide();
        
        });
        
    //-------------------End of Test Form---------------------------------------
    }
        
    public static void main(String[] args) {
        launch(args);
    }
}
