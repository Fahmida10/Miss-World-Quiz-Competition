package missworldquiz;


import java.util.Optional;
import javafx.scene.control.*;

public class Buttons {
    
    public boolean confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Confirm all details?");
        alert.setHeaderText("Confirmation");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
           return true;
        }
        
        return false;
    }
    
    public void reset(TextField... args) {   // function of reset button
        for (TextField arg : args) {
            arg.clear();
        }
    }
}
