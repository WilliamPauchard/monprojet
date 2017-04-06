package main.java.com.github.WilliamPauchard.domaine;

/**
 *
 * @author William PAUCHARD
 */
public class Fonction {
    
    private int noFonction;
    private String nomFonction;

    public Fonction(int noFonction, String nomFonction) {
        this.noFonction = noFonction;
        this.nomFonction = nomFonction;
    }

    public int getNoFonction() {
        return noFonction;
    }

    public String getNomFonction() {
        return nomFonction;
    }
    
    public String toString() {
        return nomFonction;
    }
    
    public boolean equals(Object o){
        return ((Fonction)o).noFonction==(noFonction);
    }
}
