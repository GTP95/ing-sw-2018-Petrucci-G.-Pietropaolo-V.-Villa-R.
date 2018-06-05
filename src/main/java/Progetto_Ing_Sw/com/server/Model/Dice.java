package Progetto_Ing_Sw.com.server.Model;

public class Dice {
    private int value;
    private int color;
    private boolean checkable;

    public Dice(int value, int color) {
        if(value<1) value=1;    //rendo totale
        if(value>6) value=6;    //la funzione
        this.value = value;
        this.color = color;
        this.checkable=true;
    }

    public int getValue() {
        return value;
    }
    public int getColor() {
        return color;
    }

    public void setValue(int value) {this.value = value;}
    public void setColor(int color) {this.color = color;}
    public void setCheckable(boolean checkable) {this.checkable = checkable;}
}
