package bookshopparthenope;
import bookshopparthenope.Model.UserManagement.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML
    private Text cognome;
    @FXML
    private Text email;
    @FXML
    private Text nome;

    @FXML
    private Text username;
    @FXML
    private Button cartButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button logoutButton;
    Parent root;
    FxmlController control =new FxmlController();



    @FXML
    void Logout(ActionEvent event)  throws IOException {
        control.logout(root,logoutButton);
    }


    @FXML
    void switchToCart(ActionEvent event)  throws IOException {
        control.cart(root,event);
    }

    @FXML
    void switchToHome(ActionEvent event)  throws IOException {
        control.home(root,event);

    }




}
