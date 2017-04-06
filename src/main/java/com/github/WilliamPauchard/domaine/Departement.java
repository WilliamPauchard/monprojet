package main.java.com.github.WilliamPauchard.domaine;

/**
 *
 * @author William PAUCHARD
 */
public class Departement {
    
    private int noDep;
    private String nomDep;
    private Lieu lieu;

    public Departement(int noDep, String nomDep, Lieu lieu) {
        this.noDep = noDep;
        this.nomDep = nomDep;
        this.lieu=lieu;
    }

    public String getNomDep() {
        return nomDep;
    }

    public Lieu getLieu() {
        return lieu;
    }
 
    public String toString() {
        return nomDep;
    }

    public int getNoDep() {
        return noDep;
    }
    
    public boolean equals (Object o) {
        return ((Departement)o).noDep == noDep;
    }
}
