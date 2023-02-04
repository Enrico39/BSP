package bookshopparthenope;



import bookshopparthenope.Model.UserManagement.Customer;
import bookshopparthenope.Model.UserManagement.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterController{


    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField registerCognome;

    @FXML
    private TextField registerEmail;

    @FXML
    private TextField registerNome;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerUsername;

    @FXML
    private Label wrongSignup;
    FxmlController controller=new FxmlController();
    @FXML
    void registerUser(ActionEvent event) throws SQLException, ClassNotFoundException, InterruptedException, IOException {

        if (registerPassword.getText().length() < 5) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Password troppo breve");
        }else if (registerNome.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un nome valido");
        } else if (registerCognome.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un cognome valido");
       } else if (!registerEmail.getText().contains("@")) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci una mail valida");
        } else if (registerUsername.getText().length() < 1) {
            wrongSignup.setVisible(true);
            wrongSignup.setText("Inserisci un username valido");
        }else {
            Customer cliente = new Customer(registerUsername.getText(), registerPassword.getText());
            cliente.setEmail(registerEmail.getText());
            cliente.setSurname(registerCognome.getText());
            cliente.setName(registerNome.getText());
            if(DBService.insertNewCustomer(cliente)){
                switchToLogin(event);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Esito Registrazione");
                alert.setHeaderText(cliente.getUsername()+", registrazione completata con successo!");
                alert.setContentText("Effettua il Log-in per accedere al catalogo.");
                alert.showAndWait();
            }else {
                wrongSignup.setVisible(true);
                wrongSignup.setText("Username non disponibile.");
            }


        }

    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        controller.login(root,loginButton);
    }



}
