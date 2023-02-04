package bookshopparthenope;

import bookshopparthenope.Model.UserManagement.Admin;
import bookshopparthenope.Model.UserManagement.DBService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminPanelController  implements Initializable {

    @FXML
    private TableColumn<?, ?> ISBN;

    @FXML
    private TableColumn<?, ?> annoLibro;

    @FXML
    private TableColumn<?, ?> autoreLibro;

    @FXML
    private TableColumn<?, ?> categoriaLibro;

    @FXML
    private Button inserisciLibroButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<ObservableList> adminTableBook;
    private ObservableList<ObservableList> data;


    @FXML
    private Parent root;

    public AdminPanelController() {
    }

    @FXML
    void switchToInsert(ActionEvent event)  throws IOException {
        FxmlController controller=new FxmlController();
        controller.openInsert(root,inserisciLibroButton);
    }

    FxmlController control =new FxmlController();

    @FXML
    void logout(ActionEvent event) throws IOException {
        control.logout(root,logoutButton);
    }
















    public void addcolumn() {
        try {
            ResultSet rs = DBService.showBooksToAdmin();

            for (int i = 0; i <  rs.getMetaData().getColumnCount();  i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());


                if (i==0){
                    col.setPrefWidth(200);}
                else
                    col.setPrefWidth(100);
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                 adminTableBook.columnResizePolicyProperty();
                adminTableBook.getColumns().addAll(col);


            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }


    void addrow(){
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = DBService.showBooksToAdmin();

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);

            }

            adminTableBook.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException e){
            System.out.println("non sono presenti libri " );
        }

        adminTableBook.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bookTitle = (String) newSelection.get(0);

            }
        });
}
    @FXML
    private Label errorLabel;

String bookTitle;

    @FXML
    void deleteBook(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (bookTitle==null){
            System.out.println("seleziona prima un libro");
            errorLabel.setVisible(true);
        }
        else{DBService.eliminaLibro(bookTitle);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Libro Eliminato");
            alert.setHeaderText(bookTitle+" eliminato correttamente!");
            alert.showAndWait();
            bookTitle=null;
            if(errorLabel.isVisible())
                errorLabel.setVisible(false);
            data.clear();
            addrow();

        }
    }


    @FXML
    void refresh(ActionEvent event) {
        data.clear();
        addrow();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        addcolumn();
        addrow();

    }


}

