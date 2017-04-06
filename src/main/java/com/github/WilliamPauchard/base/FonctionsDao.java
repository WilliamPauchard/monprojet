package main.java.com.github.WilliamPauchard.base;

import java.sql.PreparedStatement;
import main.java.com.github.WilliamPauchard.domaine.Fonction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William PAUCHARD
 */
public class FonctionsDao {
    
    private static ArrayList allFonctions = new ArrayList();

    public static ArrayList getAllFonctions() {
        return allFonctions;
    }
    
    public static void chargerFonctions (Connection con) throws SQLException {
        PreparedStatement statement = con.prepareStatement("SELECT * FROM fonction");
        ResultSet resultset = statement.executeQuery();
        while (resultset.next()) {
            int noFonction = resultset.getInt("NoFonc");
            String nomFonction = resultset.getString("NomFonc");
            Fonction fonction = new Fonction(noFonction, nomFonction);
            allFonctions.add(fonction);
        }
    }
    
    public static Fonction getFonction (int noFonction){
        Fonction fonct = new Fonction (noFonction, null);
        for (int i=0;i<allFonctions.size();i++){
            if (fonct.equals(allFonctions.get(i))) {
                return (Fonction)allFonctions.get(i);
            }
        }
        return null;
    }
}
