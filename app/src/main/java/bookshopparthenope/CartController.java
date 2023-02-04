package bookshopparthenope;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class CartController {



    @FXML
    private Label titoloLibro;
    @FXML
    private Button homeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button profileButton;
    Parent root;
    FxmlController controller=new FxmlController();
    @FXML
    void logout(ActionEvent event)  throws IOException {
        controller.logout(root,logoutButton);
    }

    @FXML
    void switchToHome(ActionEvent event)  throws IOException {
        controller.home(root,event);
    }

    @FXML
    void switchToProfile(ActionEvent event)  throws IOException {
        controller.profile(root,event);
    }

}

