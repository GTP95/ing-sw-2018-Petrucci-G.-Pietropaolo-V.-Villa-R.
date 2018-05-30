package Progetto_Ing_Sw.com.server.Model;

public class MatrixCell {

    private int color;
    private int shade;
    private Dice diceContained;//indica il dado contenuto
    private boolean used; //indica se la cella è libera o meno
    private boolean respectful; //variabile usata per visualizzare se la cella rispetta le condizioni

    //costruttore di default= inizializza la cella a blank
    public MatrixCell() {
        this.color = Color.BLANK;
        this.used=false;
    }

    public int getColor() {return color;}
    public int getShade() {return shade;}
    public boolean isUsed() {return used;}

    public void setColor(int color) {this.color = color;}
    public void setShade(int shade) {this.shade = shade;}


}
