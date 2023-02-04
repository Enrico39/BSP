package bookshopparthenope.Model.BookManagement;

import bookshopparthenope.Model.BookManagement.Category.*;

//PATTERN FACTORY PER CREAZIONE LIBRO
public class BookFactory{
  public Book createBook(String category, String subcategory) {
    return switch (category) {
      case "Letteratura" -> new LetteraturaBook(subcategory);
      case "Arte e Musica" -> new ArteemusicaBook(subcategory);
      case "Biografia" -> new BiografiaBook(subcategory);
      case "Business" -> new BusinessBook(subcategory);
      case "Fumetti" -> new FumettiBook(subcategory);
      case "Computer e Tech" -> new ComputeretechBook(subcategory);
      case "Cucina" -> new CucinaBook(subcategory);
      case "Educazione" -> new EducazioneBook(subcategory);
      case "Intrattenimento" -> new IntrattenimentoBook(subcategory);
      default -> null;
    };
  }
}
