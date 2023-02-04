package bookshopparthenope;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.BookFactory;
import bookshopparthenope.Model.UserManagement.DBService;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;


public class AggiuntaLibro implements Initializable{
  @FXML
  private   TextField anno;

  @FXML
  private Button annulla;

  @FXML
  private   TextField autore;


  @FXML
  private   TextArea descrizione;

  @FXML
  private Button inserisciLibroButton;

  @FXML
  private   TextField isbn;

  @FXML
  private   TextField pagine;

  @FXML
  private  TextField prezzo;

  @FXML
  private   TextField quantita;



  @FXML
  public TextField titolo;



  static String tit;
  static String aut;
  static String ann;
  static String pag;

  static int quantita_int;
  static  String isb;
  static float prezzo_float;
  static String desc;
  @FXML
  void insertBook(ActionEvent event) throws SQLException, ClassNotFoundException, ParseException {

     tit= titolo.getText();
     aut=autore.getText();
     ann=anno.getText();
     pag=pagine.getText();
     quantita_int = Integer.parseInt(quantita.getText());
     isb=isbn.getText();
    String price = prezzo.getText().replace(',', '.');
     prezzo_float = Float.parseFloat(price);
     desc= descrizione.getText();

    String cat=categoria.getValue();
    String scat=sottocategoria.getValue();
    BookFactory libro= new BookFactory();
    Book libroBook = libro.createBook(cat, scat);
    Book sLibrobook = libroBook.sBook();
    sLibrobook.insertFactoryBook();
    //System.out.println(libroBook.getCategory());

  }

  public static String getTitolo(){
    return tit;
  }
  public static String getAutore(){
    return aut;
  }

  public static String getAnno(){
    return ann;
  }

  public static String getPagine(){
    return pag;
  }
  public static int getQuantita(){
    return quantita_int;
  }


  public static String getIsbn(){
    return isb;
  }



  public static Float getPrice(){

    return prezzo_float;
  }


  public static String getDescription(){
   return desc;
  }


  @FXML
  public void cancel(ActionEvent event) {
    Stage stage = (Stage) annulla.getScene().getWindow();
    stage.close();
  }




  @FXML
  private ComboBox<String> categoria;

  @FXML
  private ComboBox<String> sottocategoria;
  public String categoriaScelta;
  public String sottoCategoriaScelta;
  private String[] categorie = {"Letteratura","Arte e Musica","Biografia","Fumetti","Computer e Tech","Cucina","Educazione","Intrattenimento","Business"};
  @FXML
  public void setSottocategoria(ActionEvent event) {

     }



  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    categoria.getItems().addAll(categorie);
    categoria.setOnAction(this::getChoice);


  }

public void getChoice(ActionEvent event) {
    if (sottocategoria.isDisabled()){
      sottocategoria.setDisable(false);
    }
         categoriaScelta = categoria.getValue();
        switch (categoriaScelta){
          case "Letteratura":
            String[] sLetteratura = {"Antologie","Classici","Contemporanei","Lingue Straniere","Letterature"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sLetteratura);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Arte e Musica":
            String[] sArte = {"Storia dell'arte","Calligrafia","Disegno","Fashion Design"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sArte);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Biografia":
            String[] sBiografia = {"Etnica e Culturale","Europea","Storica","Personaggi Famosi e Leader","Militare"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sBiografia);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Fumetti":
            String[] sFumetti = {"Comici","Marvel","Misteriosi","DC","Fantasy"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sFumetti);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Computer e Tech":
            String[] sComputer= {"Apple","CAD","Certificazioni","Informatica","Database"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sComputer);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Cucina":
            String[] sCucina = {"Asiatica","Cucina Calda","BBQ","Arti Culinarie","Dolci"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sCucina);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Educazione":
            String[] sEducazione = {"Almanacchi","Atlanti e Mappe","Cataloghi","Scolastici"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sEducazione);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Intrattenimento":
            String[] sIntrattenomento = {"Rompicapo","Barzellette","Giochi","Film"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sIntrattenomento);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          case "Business":
            String[] sBusiness = {"Carriera","Economia","Finanza","Industria","Internazionale"};
            sottocategoria.getItems().clear();
            sottocategoria.getItems().addAll(sBusiness);
            sottocategoria.setOnAction(this::getChoice2);
            break;
          default:
            System.out.println("error");
            break;
        }


        }

  public void getChoice2(ActionEvent event) {
    sottoCategoriaScelta = sottocategoria.getValue();
  }

}


