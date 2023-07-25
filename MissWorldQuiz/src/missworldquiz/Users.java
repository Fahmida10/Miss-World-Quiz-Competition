package missworldquiz;

import java.util.*;
import java.io.*;
import javafx.scene.control.*;

public class Users {
    private String msg;
    private static String ID;
    private static String username;
    private static String Country;
    
    //Set Username
    public void setUser(String u){
        username = u;
    }
    
    //Set ID
    public void setID(String i){
        ID = i;
    }
    
    //Get ID
    public static String getID(){
        return ID;
    }
    
    //Get Username
    public static String getUsername(){
        return username;
    }
        
    public void setCountry(String c){
        Country = c;
    }
    
    public static String getCountry(){
        return Country;
    }
    
    public boolean CheckNumber(TextField... args) {     // Checks whether user enters any characaters in the numeric textfield
        ArrayList<Boolean> checkDouble = new ArrayList<Boolean>();
        boolean return_value;
        
        for (TextField arg : args) {
            if(!(arg.getText().matches("[0-9]+"))) {
                checkDouble.add(Boolean.FALSE);
            }
            else {
                checkDouble.add(Boolean.TRUE);
            }
        }
        
        if(checkDouble.contains(Boolean.FALSE)) {
            return_value = false;
        }
        
        else {
            return_value = true;
        }
        
        return return_value;
    }  
    
    public boolean CheckString(String... args) { // Check textfields whether user enters any numeric value in the string textfield
        ArrayList<Boolean> checkText = new ArrayList<Boolean>();
        boolean return_value;
        
        for (String arg : args) {
            if(!(arg.matches("[a-zA-Z_]+"))) {
                checkText.add(Boolean.FALSE);
            }
            else {
                checkText.add(Boolean.TRUE);
            }
        }
        
        if(checkText.contains(Boolean.FALSE)) {
            return_value = false;
        }
        
        else {
            return_value = true;
        }
        
        return return_value;
    }  
    
    public boolean checkPassword(String pass, String user) throws IOException{
        boolean isValid = false;
        
        InputStream is = getClass().getResourceAsStream("/TextFiles/contestant.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                Scanner aLine = new Scanner(line);
                aLine.useDelimiter(":");
                String username = aLine.next();
                String password = aLine.next();
                
                
                if(username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)){
                    isValid = true;
                    break;
                }
                else
                    isValid = false;
                
                
                sb.append(line);
                line = br.readLine();
            }
        } 
        catch (IOException ex) {
            System.out.println(ex);
        } finally {
            br.close();
        }
        
        return isValid;
    }
    
    //Showing the invalid password alert
    public void invalidPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        msg = "Please Enter the correct Password";
        alert.setContentText(msg);
        alert.setHeaderText("Invalid input");
        
        alert.show();        
    }
    
    //Showing alert for invalid username
    public void invalidUsername(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        msg = "Please Enter a Valid Username";
        alert.setContentText(msg);
        alert.setHeaderText("Invalid input");
        
        alert.show();
    }
    
    //Showing alert for invalid ID
    public void invalidID(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        msg = "Please Enter a Valid ID";
        alert.setContentText(msg);
        alert.setHeaderText("Invalid input");
        
        alert.show();
    }
    
    //Showing alert for no Country Chosen
    public void chooseCountry(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        msg = "Please Choose a Country";
        alert.setContentText(msg);
        alert.setHeaderText("Invalid input");
        
        alert.show();
    }
    
    //Showing alert for login successful
    public void loginSuccessful(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        msg = "Login is Successful";
        alert.setContentText(msg);
        alert.setHeaderText("Congratulations");
        
        alert.show();
    }

}
