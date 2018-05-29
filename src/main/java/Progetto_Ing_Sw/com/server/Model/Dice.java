package Progetto_Ing_Sw.com.server.Model;

public class Dice {
    private int value;
    private int color;
    private boolean usable;

    public Dice(int value, int color) {
        if(value<1) value=1;    //rendo totale
        if(value>6) value=6;    //la funzione
        this.value = value;
        this.color = color;
        this.usable=true;
    }

    public int getValue() {
        return value;
    }

    public int getColor() {
        return color;
    }
}
