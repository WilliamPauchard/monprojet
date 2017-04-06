package main.java.com.github.WilliamPauchard.metier;


public class Action {
  
  /* Les action possibles */
  public static final int CHARGEMENT = 1, SELECTION = 2, ADJONCTION = 3, SUPPRESSION = 4, MODIFICATION = 5;
  
  private int action;   /* L'action: une des valeurs ci-dessus */
  private int pos = -1; /* La position concernée */
  
  public Action (int action) {this.action = action;}
  public Action (int action, int pos) {this(action); this.pos = pos;}
  
  public int getAction () {return action;}
  public int getPos () {return pos;}

} // Action