package bookshopparthenope.Model.UserManagement;


import java.sql.SQLException;

/**
 * Classe astratta che modella un utente generico
 */
public abstract class User {

    protected String username;
    protected String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



// Template method

    //Metodo "abstract" primitivo che deve essere implementato
    protected abstract boolean logIn() throws SQLException, ClassNotFoundException;


}
