package Progetto_Ing_Sw.com.client;

public class ClientDice {
    private int value;
    private int color;
    private boolean usable;

    public ClientDice(int value, int color) {
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
