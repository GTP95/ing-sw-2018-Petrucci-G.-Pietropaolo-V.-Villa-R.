package Progetto_Ing_Sw.com.server.Model;

import java.util.Objects;

/**
 * this class implements the Dice
 */
public class Dice {
    private int value;
    private int color;
    private boolean adjacencyBreaker;
    private boolean colorBreaker;
    private boolean numberBreaker;

    /**
     * this method create a dice, setting value and color, that are input
     * @param value this is the value that is going to be set at the dice
     * @param color this is the color that is going to be set at the dice
     */
    public Dice(int value, int color) {
        if(value<1) value=1;    //rendo totale
        if(value>6) value=6;    //la funzione
        this.value = value;
        this.color = color;
        this.adjacencyBreaker=false;
        this.colorBreaker=false;
        this.numberBreaker=false;
    }

    public int getValue() {
        return value;
    }
    public int getColor() {
        return color;
    }
    public boolean isAdjacencyBreaker() {return adjacencyBreaker;}
    public boolean isColorBreaker() {return colorBreaker;}
    public boolean isNumberBreaker() {return numberBreaker;}

    public void setValue(int value) {this.value = value;}
    public void setColor(int color) {this.color = color;}
    public void setAdjacencyBreaker(boolean adjacencyBreaker) {this.adjacencyBreaker = adjacencyBreaker;}
    public void setColorBreaker(boolean colorBreaker) {this.colorBreaker = colorBreaker;}
    public void setNumberBreaker(boolean numberBreaker) {this.numberBreaker = numberBreaker;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return value == dice.value &&
                color == dice.color;
    }

    @Override
    public int hashCode() {     //generata automaticamente da inetllij TODO: chiedere se è da togliere

        return Objects.hash(value, color);
    }
}
