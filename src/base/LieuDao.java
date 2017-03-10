package base;

import domaine.Lieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William PAUCHARD
 */
public class LieuDao {
    
    private static ArrayList allLieux = new ArrayList();

    public static ArrayList getAllLieux() {
        return allLieux;
    }
    
    public static void chargerLieux (Connection con)throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT * FROM lieu");
        ResultSet resultset = statement.executeQuery();
        while (resultset.next()) {
            String lieuNom = resultset.getString("NomLieu");
            int noLieu = resultset.getInt("NoLieu");
            Lieu lieu = new Lieu(noLieu, lieuNom);
            allLieux.add(lieu);
        }
    }
    
    public static Lieu getLieu (int noLieu) {
        Lieu lieu = new Lieu(noLieu, null);
        for (int i =0;i<allLieux.size();i++) {
            if (lieu.equals(allLieux.get(i))) {
                return (Lieu)allLieux.get(i);
            }
        }
        return null;
    }
}
