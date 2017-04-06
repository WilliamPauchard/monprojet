package domaine;

/**
 *
 * @author William PAUCHARD
 */
public class Lieu {
    
    private int noLieu;
    private String nomLieu;

    public Lieu(int noLieu, String nomLieu) {
        this.noLieu = noLieu;
        this.nomLieu = nomLieu;
    }
    
    public boolean equals (Object o) {
        return ((Lieu)o).noLieu == noLieu;
    }
    
    public String toString () {
        return nomLieu;
    }
}
