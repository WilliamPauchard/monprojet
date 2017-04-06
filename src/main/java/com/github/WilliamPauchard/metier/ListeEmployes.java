
package main.java.com.github.WilliamPauchard.metier;

import main.java.com.github.WilliamPauchard.base.EmployeDao;
import main.java.com.github.WilliamPauchard.domaine.Employe;
import java.util.Observer;

/**
 *
 * @author Artrit
 * Modèle: une liste d'employes
 * Fonctionnalités centrales
 */
public class ListeEmployes extends ListeObjects {
    /** Constructeur */
    public ListeEmployes (Observer observer) {
        super(observer);
        liste = EmployeDao.getAllEmployes();
        //setChanged(); notifyObservers(new Action(Action.CHARGEMENT));
    } // Constructeur
    
    /** Retourne l'employé courant, null si la position courante est NO_POS */
    public Employe getEmployeCourant () {return (Employe)super.getCourant();}

    /** Retourne l'employé d'indice k, null si k n'est pas correctement défini */
    public Employe getEmploye (int k) {return (Employe)super.get(k);}
}  

