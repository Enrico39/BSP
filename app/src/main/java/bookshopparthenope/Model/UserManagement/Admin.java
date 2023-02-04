package bookshopparthenope.Model.UserManagement;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe che estende User per modellare un Admin dello shop
 */
public class Admin extends User{



    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }





    //Implementazione metodo login
    @Override
    public boolean logIn() throws SQLException, ClassNotFoundException {
        ResultSet set = DBService.logInAdmin(username, password);

        if (set != null) {
                this.username = set.getString(1);
                this.password = set.getString(2);
                return true;
        }
        return false;

    }
}
