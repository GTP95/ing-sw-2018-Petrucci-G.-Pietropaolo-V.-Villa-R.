package Progetto_Ing_Sw.com.server;

public class MatrixCell {

    private int color;
    private int shade;

    //costruttore di default= inizializza la cella a blank
    public MatrixCell() {this.color = Color.BLANK;}

    public int getColor() {return color;}
    public int getShade() {return shade;}

    public void setColor(int color) {this.color = color;}
    public void setShade(int shade) {this.shade = shade;}
}
