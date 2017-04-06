package base;

import domaine.Departement;
import domaine.Lieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author William PAUCHARD
 */
public class DepartementsDao {
    
    private static ArrayList departements = new ArrayList();

    public static ArrayList getAllDepartement() {
        return departements;
    }
    
    public static void chargerDepartement (Connection con) throws SQLException{
        PreparedStatement statement = con.prepareStatement("SELECT * FROM departement");
        ResultSet resultset = statement.executeQuery();
        while (resultset.next()) {
            String nomDept = resultset.getString("NomDept");
            int noDept = resultset.getInt("NoDept");
            Lieu lieu = LieuDao.getLieu(resultset.getInt("NoLieu"));
            Departement dept = new Departement(noDept, nomDept, lieu);
            departements.add(dept);
        }
    }
    
    public static Departement getDepartement (int noDept) {
        Departement dept = new Departement (noDept, null,null);
        for (int i=0;i<departements.size();i++){
            if (dept.equals(departements.get(i))) {
                return (Departement)departements.get(i);
            }
        }
        return null;
    }
}
