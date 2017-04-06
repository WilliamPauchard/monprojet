package main.java.com.github.WilliamPauchard.base;

import java.sql.PreparedStatement;
import main.java.com.github.WilliamPauchard.domaine.Departement;
import main.java.com.github.WilliamPauchard.domaine.Employe;
import main.java.com.github.WilliamPauchard.domaine.Fonction;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author William PAUCHARD
 */
public class EmployeDao {

    private static ArrayList allEmploye = new ArrayList();

    public static void chargerEmployes() {
        try {
            Connection con = ConnexionBase.connect(ConnexionBase.getNomBase());
            LieuDao.chargerLieux(con);
            DepartementsDao.chargerDepartement(con);
            FonctionsDao.chargerFonctions(con);
            PreparedStatement statement = con.prepareStatement("SELECT * FROM employe ORDER BY nomEmpl,prenomEmpl ASC");
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                int noEmpl = resultset.getInt("NoEmpl");
                String nomEmpl = resultset.getString("NomEmpl");
                String prenomEmpl = resultset.getString("PrenomEmpl");
                Fonction fonction = FonctionsDao.getFonction(resultset.getInt("NoFonc"));
                Date dateEmpl = resultset.getDate("DateEmpl");
                Departement d = DepartementsDao.getDepartement(resultset.getInt("NoDept"));
                allEmploye.add(new Employe(noEmpl, nomEmpl, prenomEmpl, fonction, dateEmpl, d));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static int getNoEmplBdd (String nom, String prenom) {
        try {
            Connection con = ConnexionBase.connect(ConnexionBase.getNomBase());
            PreparedStatement statement = con.prepareStatement("SELECT NoEmpl FROM employe where NomEmpl =? AND PrenomEmpl=?");
            statement.setString(1,nom);statement.setString(2,prenom);
            ResultSet resultset = statement.executeQuery();
            resultset.next();
            return resultset.getInt("NoEmpl");
        }
        catch (SQLException e ){e.printStackTrace();System.out.println("getNoEmplBdd "+e.getMessage());return -1;}
        catch (ClassNotFoundException e ){e.printStackTrace();System.out.println("getNoEmplBdd "+e.getMessage());return -1;}
    }

    public static Employe getEmploye(int index) {
        return (Employe) allEmploye.get(index);
    }

    public static ArrayList getAllEmployes() {
        return allEmploye;
    }

    public static void supprimerEmploye(int index) {
        Employe emp = (Employe) allEmploye.get(index);
        allEmploye.remove(index);
        try {
            Connection con = main.java.com.github.WilliamPauchard.base.ConnexionBase.connect(main.java.com.github.WilliamPauchard.base.ConnexionBase.getNomBase());
            PreparedStatement statement = con.prepareStatement("DELETE FROM employe where NoEmpl=? ");
            statement.setInt(1, emp.getNoEmpl());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("supprimerEmploye : ");
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("supprimerEmploye : ");
            e.getMessage();
        }
    }

    public static int insererEmploye(String nomEmp, String prenomEmp, String dateEngagement, int indexFonction, int indexDepartement) {
        Fonction fct = FonctionsDao.getFonction(indexFonction+1);
        Departement dept = DepartementsDao.getDepartement(indexDepartement+1);
        java.util.Date date = Employe.parseDate(dateEngagement);
        insererDansLaBdd(nomEmp, prenomEmp, fct, date, dept);
        int noEmp = getNoEmplBdd(nomEmp,prenomEmp);
        Employe emp = new Employe(noEmp, nomEmp, prenomEmp, fct, date, dept);
        int pos = trouverPosition(allEmploye, emp);
        decalage(allEmploye, pos);
        allEmploye.add(pos, emp);
        return pos;
    }

    private static void insererDansLaBdd( String nomEmp, String prenomEmp, Fonction fct, Date date, Departement dept) {
        try {
            Connection con = main.java.com.github.WilliamPauchard.base.ConnexionBase.connect(main.java.com.github.WilliamPauchard.base.ConnexionBase.getNomBase());
            PreparedStatement statement = con.prepareStatement("INSERT INTO employe (nomEmpl,PrenomEmpl,NoFonc,DateEmpl,NoDept) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1,nomEmp);
            statement.setString(2,prenomEmp);
            statement.setInt(3,fct.getNoFonction()+1);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            statement.setDate(4,sqlDate);
            statement.setInt(5,dept.getNoDep()+1);
            statement.executeUpdate();
            statement.close();
        } 
        catch (SQLException e) {e.printStackTrace();System.out.println("insérerDansLaBdd : ");e.getMessage();} 
        catch (ClassNotFoundException e) {e.printStackTrace();System.out.println("insérerDansLaBdd : ");e.getMessage();}
    }

    private static void decalage(ArrayList liste, int index) {
        liste.add(liste.get(liste.size() - 1));
        for (int i = liste.size() - 2; i > index; i--) {
            liste.set(i + 1, liste.get(i));
        }
    }

    private static int trouverPosition(ArrayList liste, Employe emp) {
        Employe empDiscriminant = (Employe) liste.get(0);
        for (int i = 0; i < liste.size(); i++) {
            if (emp.compareTo((Employe) liste.get(i)) < 0) {
                return i;
            }
        }
        return liste.size();
    }
}
