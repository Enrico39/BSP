package bookshopparthenope;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private Parent root;


    @FXML
    private TableView<ObservableList> tableBook;

    @FXML
    private Label dettagioAnno;

    @FXML
    private Label dettaglioAutore;

    @FXML
    private Text dettaglioDescrizione;

    @FXML
    private Label dettaglioPagine;

    @FXML
    private Label dettaglioPrezzo;

    @FXML
    private Label dettaglioQuantita;
    @FXML
    private AnchorPane dettagli;

    @FXML
    private Button logoutButton;
    @FXML
    private Button profileButton;
    FxmlController control =new FxmlController();
    @FXML
    private Button cerca;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private ComboBox<String> comboSottocategoria;

    @FXML
    void Logout(ActionEvent event) throws IOException {
        control.logout(root,logoutButton);
    }

    @FXML
    void switchToProfile(ActionEvent event) throws IOException {
        control.profile(root,event);
    }
    @FXML
    void switchToCart(ActionEvent event)  throws IOException {
        control.cart(root,event);
    }









    @FXML
    void cerca(ActionEvent event) throws SQLException {
        String cat = comboCategoria.getValue();
        String scat = comboSottocategoria.getValue();
        System.out.println(cat + scat);
        tableBook.getColumns().clear();
    if (scat=="Storia dell'arte")
        scat="Storia dell''arte";

        addcolumn(scat);
        addrow(scat);
    }

    public void addcolumn(String scat) {
        try {
            ResultSet rs = DBService.showBooksToUser(scat);

            for (int i = 0; i <  rs.getMetaData().getColumnCount();  i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setResizable(false);
                if (i==0 || i==1){
                col.setPrefWidth(140);}
                else
                    col.setPrefWidth(80);
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableBook.getColumns().removeAll(col);
                tableBook.columnResizePolicyProperty();
                tableBook.getColumns().addAll(col);


            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }


    void addrow(String scat){
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = DBService.showBooksToUser(scat);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                 data.add(row);

            }

            tableBook.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NullPointerException e){
            System.out.println("non sono presenti libri " + scat);
        }



    }


    private ObservableList<ObservableList> data;




        @FXML
        void setCategoria (ActionEvent event){

        }

        @FXML
        void setSottocategoria (ActionEvent event){

        }

        String[] comboCategorie = {"Letteratura", "Arte e Musica", "Biografia", "Fumetti", "Computer e Tech", "Cucina", "Educazione", "Intrattenimento", "Business"};

        @Override
        public void initialize (URL arg0, ResourceBundle arg1){
            comboCategoria.getItems().addAll(comboCategorie);
            comboCategoria.setOnAction(this::getChoice);
        }

        public String categoriaScelta;
        public String sottoCategoriaScelta;
        public void getChoice (ActionEvent event){

            if (comboSottocategoria.isDisabled()) {
                comboSottocategoria.setDisable(false);
            }
            categoriaScelta = comboCategoria.getValue();

            switch (categoriaScelta) {
                case "Letteratura":
                    String[] sLetteratura = {"Antologie", "Classici", "Contemporanei", "Lingue Straniere", "Letterature"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sLetteratura);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Arte e Musica":
                    String[] sArte = {"Storia dell'arte", "Calligrafia", "Disegno", "Fashion Design"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sArte);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Biografia":
                    String[] sBiografia = {"Etnica e Culturale", "Europea", "Storica", "Personaggi Famosi e Leader", "Militare"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sBiografia);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Fumetti":
                    String[] sFumetti = {"Comici", "Marvel", "Misteriosi", "DC", "Fantasy"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sFumetti);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Computer e Tech":
                    String[] sComputer = {"Apple", "CAD", "Certificazioni", "Informatica", "Database"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sComputer);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Cucina":
                    String[] sCucina = {"Asiatica", "Cucina Calda", "BBQ", "Arti Culinarie", "Dolci"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sCucina);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Educazione":
                    String[] sEducazione = {"Almanacchi", "Atlanti e Mappe", "Cataloghi", "Scolastici"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sEducazione);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Intrattenimento":
                    String[] sIntrattenomento = {"Rompicapo", "Barzellette", "Giochi", "Film"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sIntrattenomento);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                case "Business":
                    String[] sBusiness = {"Carriera", "Economia", "Finanza", "Industria", "Internazionale"};
                    comboSottocategoria.getItems().clear();
                    comboSottocategoria.getItems().addAll(sBusiness);
                    comboSottocategoria.setOnAction(this::getChoice2);
                    cerca.setDisable(true);
                    break;
                default:
                    System.out.println("error");
                    cerca.setDisable(true);
                    break;
            }
        }
        public void getChoice2 (ActionEvent event){
            sottoCategoriaScelta = comboSottocategoria.getValue();
            cerca.setDisable(false);
        }







    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            dettagioAnno.setText("aaa");
            dettagli.setVisible(true);
        }
    }



    }

