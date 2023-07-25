package missworldquiz;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class PopUp {

    private final String popMessage;
    private final Stage stage;
    
    //pop up constructor
    public PopUp(String message, Stage s){
        popMessage = message;
        stage = s;
    }

    //Creating pop up label
    private Popup createPopup(String message) {
        Popup popup = new Popup();
        popup.setAutoFix(true);
        Label labPopup = new Label(message);
        labPopup.setStyle("-fx-text-fill:black;-fx-font-size:2em;-fx-background-color: #F0F8FF;-fx-border-width: 5px;-fx-border-color: black;");
        popup.getContent().add(labPopup);
        
        return popup;
    }
    
    //show the pop up message
    public void showPopupMessage() {
        Popup popup = createPopup(popMessage);
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(stage.getX() + stage.getWidth() - popup.getWidth());
                popup.setY(stage.getY() + 25);
            }
        });
        
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(e -> popup.hide());
        popup.show(stage);
        delay.play();
    }
}