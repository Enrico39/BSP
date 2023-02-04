package bookshopparthenope.Model.BookManagement;

import java.sql.SQLException;

//Classe Padre per libro
public abstract class Book {
  protected String subcategory;

  public Book (String subcategory) {
    this.subcategory = subcategory;
  }
  public abstract String getCategory();
  public abstract void insertFactoryBook() throws SQLException, ClassNotFoundException;

    public abstract Book sBook() ;
}
