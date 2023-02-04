package bookshopparthenope;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlController {


    public void logout(Parent root, Button actionButton) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/login.fxml"));
        Stage window = (Stage) actionButton.getScene().getWindow();
        window.setScene(new Scene(root,650,450));
        window.centerOnScreen();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Log-out effettuato!");
        alert.showAndWait();
    }

    public void profile(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/profilo.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(Parent root, Button actionButton) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/login.fxml"));
        Stage window = (Stage) actionButton.getScene().getWindow();
        window.setScene(new Scene(root,650,450));
    }
    public void effettuaLogin(Parent root,Stage stage, Scene scene,ActionEvent event,String fxmlFile)throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    public void home(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cart(Parent root, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/carrello.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void openInsert(Parent root, Button actionButton) throws IOException {
        root= FXMLLoader.load(getClass().getResource("/bookshopparthenope/gui/inserimentoLibro.fxml"));
        //Stage window = (Stage) actionButton.getScene().getWindow();
        //window.setScene(new Scene(root,671,444));
        //stage.centerOnScreen();
        Stage stage = new Stage();
        stage.setTitle("Inserimento Libro");
        stage.setScene(new Scene(root, 671,444));
        stage.centerOnScreen();
        stage.show();
        stage.setResizable(false);
        Image icon = new Image("bookshopparthenope/gui/images/logoDesktop.png");
        stage.getIcons().add(icon);
        // Hide this current window (if this is what you want)
    }

}
