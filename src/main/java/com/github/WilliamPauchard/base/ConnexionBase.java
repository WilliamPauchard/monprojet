package main.java.com.github.WilliamPauchard.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author William PAUCHARD
 */
public class ConnexionBase {
    
    private static String nomBase = "empldept";
    
    public static String getNomBase(){
        return nomBase;
    };
    
    public static Connection connect (String nomBase) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties p = new Properties();
        p.put("user","root");p.put("password","");p.put("charSet","UTF-8");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomBase, p);
    }
}
