package domaine;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author William PAUCHARD
 */
public class Employe implements Comparable {

    private static final String SEP_DATE = "/";

    private int noEmpl;
    private String empNom;
    private String empPrenom;
    private Fonction fonction;
    private Date dateEmpl;
    private Departement dep;

    //Constructeur
    public Employe(int noEmpl, String empNom, String empPrenom, Fonction fonction, Date dateEmpl, Departement dep) {
        this.noEmpl = noEmpl;
        this.empNom = empNom;
        this.empPrenom = empPrenom;
        this.fonction = fonction;
        this.dateEmpl = dateEmpl;
        this.dep = dep;
    }//

    //Getteresultset
    public String toString() {
        return empNom + " " + empPrenom;
    }

    public int getNoEmpl() {
        return noEmpl;
    }

    public String getEmpNom() {
        return empNom;
    }

    public String getEmpPrenom() {
        return empPrenom;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public Date getDateEmpl() {
        return dateEmpl;
    }

    public Departement getDep() {
        return dep;
    }

    public int compareTo(Object o) {
        if (this.empNom.compareTo(((Employe) o).getEmpNom()) < 0) {
            return -1;
        } else if (this.empNom.compareTo(((Employe) o).getEmpNom()) == 0) {
            if (this.empPrenom.compareTo(((Employe) o).getEmpPrenom()) < 0) {
                return -1;
            } else if (this.empPrenom.compareTo(((Employe) o).getEmpPrenom()) == 0) {
                return 0;
            }
        }
        return 1;
    }

    public static int getNouveauNoEmpl() {
        try {
            Connection conn = base.ConnexionBase.connect(base.ConnexionBase.getNomBase());
            PreparedStatement statement = conn.prepareStatement("SELECT MAX(NoEmpl)AS 'Numero' from employe");
            ResultSet resultset = statement.executeQuery();
            resultset.next();
            int noEmp = resultset.getInt("Numero") + 1;
            statement.close();
            return noEmp;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getNouveauEmpl : " + e.getMessage());
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("getNouveauEmpl : " + e.getMessage());
            return -1;
        }
    }

    public static Date parseDate(String date) {
        java.util.Date dateEngagement;
        try {
            DateFormat dateformat = new SimpleDateFormat("dd/mm/yyyy");
            dateEngagement = dateformat.parse(date);
            return dateEngagement;       
        } 
        catch (ParseException e) {return null;}
    }
}
