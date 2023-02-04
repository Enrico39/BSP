package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class ArteemusicaBook extends Book {
  public ArteemusicaBook(String subcategory) {
    super(subcategory); }

  public ArteemusicaBook sBook(){
    return switch (subcategory) {
      case "Storia dell'arte" -> new Storiadellarte();
      case "Calligrafia" -> new Calligrafia();
      case "Disegno" -> new Disegno();
      case "Fashion Design" -> new Fashiondesign();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Arte e Musica"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}
