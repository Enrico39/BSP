package bookshopparthenope.Model.UserManagement;

import java.sql.*;
import java.util.Random;

/**
 * classe di utility per implementare l'interazione con il database
 */


public class DBService {
    public static final int MYSQL_DUPLICATE_PK = 1062;

    public static ResultSet logInAdmin(String username, String password) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String query = "SELECT * FROM admin where username='" + username + "'and password ='" + password + "'";

        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }

    public static ResultSet logInCustomer(String username, String password) throws ClassNotFoundException, SQLException {

        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String query = "SELECT * FROM customer where username='" + username + "'and password ='" + password + "'";

        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }

    public static boolean insertNewCustomer(Customer cliente) throws ClassNotFoundException, SQLException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        String apice = "'";
        String virgola = ",";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String query = "INSERT INTO customer(username,name,surname,email,password) values ( '" +
                cliente.getUsername() + apice + virgola + apice + cliente.getName() + apice + virgola + apice + cliente.getSurname() + apice + virgola + apice + cliente.getEmail() + apice + virgola +
                apice + cliente.getPassword() + apice + ")";

        try{
            conn.prepareStatement(query).execute();
            return true;
        } catch(SQLException e){
            if(e.getErrorCode() == MYSQL_DUPLICATE_PK ){
                return false;        }
        }
        return true;
    }

    public static void insertBook(String TITOLO, String AUTORE, String ANNO_PUBBLICAZIONE, String NUM_PAGINE, int QTA_DISP, String CATEGORIA, String SOTTOCATEGORIA, String ISBN, float PREZZO, String DESCRIZIONE) throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String apice = "'";
        String virgola = ",";
        String query = "INSERT INTO LIBRO (ID,ANNO_PUBBLICAZIONE,CATEGORIA,NUM_PAGINE,ISBN,TITOLO,AUTORE,QTA_DISP,PREZZO,SOTTOCATEGORIA,DESCRIZIONE) values ( default" +
                virgola + apice + ANNO_PUBBLICAZIONE + apice + virgola + apice + CATEGORIA.replace("'", "''")  + apice + virgola + apice + NUM_PAGINE + apice + virgola +
                apice + ISBN+ apice + virgola + apice + TITOLO.replace("'", "''")  + apice + virgola + apice + AUTORE.replace("'", "''")  + apice + virgola +
                apice + QTA_DISP+ apice +virgola + apice + PREZZO + apice + virgola + apice + SOTTOCATEGORIA.replace("'", "''")   +  apice + virgola + apice + DESCRIZIONE.replace("'", "''") + apice + ")";
        conn.prepareStatement(query).execute();

    }


    public static ResultSet showBooksToUser(String SOTTOCATEGORIA) throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String apice = "'";
        String virgola = ",";
        String query = "SELECT titolo,autore,PREZZO,QTA_DISP from Libro where SOTTOCATEGORIA=" +
                apice + SOTTOCATEGORIA + apice;

        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }


    public static ResultSet showBooksToAdmin() throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String apice = "'";
        String virgola = ",";
        String query = "SELECT titolo,ISBN,autore,ANNO_PUBBLICAZIONE,NUM_PAGINE,CATEGORIA,SOTTOCATEGORIA,PREZZO,QTA_DISP from Libro where not AUTORE='A'ORDER BY TITOLO";
        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }











    public static ResultSet showBooksDetails(String titolo) throws SQLException, ClassNotFoundException {
        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String apice = "'";
        String virgola = ",";
        String query = "SELECT titolo,autore,isbn,PREZZO,QTA_DISP,descrizione,anno_pubblicazione,Num_pagine from Libro where titolo=" +
                apice + titolo + apice;

        Statement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs;
        } else return null;
    }


    public static void eliminaLibro(String bookTitle) throws SQLException, ClassNotFoundException {

        String myDriver = "com.mysql.cj.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/bsv2";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");
        String apice = "'";
        String virgola = ",";
        String query = "DELETE FROM libro WHERE titolo = '"+bookTitle.replace("'", "''")+apice;


        conn.prepareStatement(query).execute();

    }
}
