package Progetto_Ing_Sw.com.server.Model;

/**
 * <h1>MatrixCell</h1>
 *  The class MatrixCell implements the main unit of the matrix, the cell.
 *  This class has a key role in the game, because has all the variables used for positioning dice and check
 *  placement rules;
 *
 *  @author Roberto Villa
 */
public class MatrixCell {

    private int color;
    private int shade;
    private Dice diceContained;//indica il dado contenuto
    private boolean used; //indica se la cella è libera o meno
    private boolean onBorder;//variabile usata per dire se la cella è sul bordo


    public MatrixCell() {
        this.color = Color.BLANK;
        this.used=false;
        this.onBorder=false;
        this.diceContained=null;
    }

    public int getColor() {return color;}
    public int getShade() {return shade;}
    public boolean isUsed() {return used;}
    public boolean isOnBorder() {return onBorder;}
    public Dice getDiceContained() {return diceContained;}

    public void setColor(int color) {this.color = color;}
    public void setShade(int shade) {this.shade = shade;}
    public void setOnBorder(boolean onBorder) {this.onBorder = onBorder;}
    public void setUsed(boolean used) {this.used = used;}
    public void setDiceContained(Dice diceContained) {this.diceContained = diceContained;}
}
