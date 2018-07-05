package Progetto_Ing_Sw.com.client;

import java.util.Objects;

public class ClientDice {
    private int value;
    private int color;
    //private boolean usable;
    private boolean adjacencyBreaker;
    private boolean colorBreaker;
    private boolean numberBreaker;

    public ClientDice(int value, int color) {
        if(value<1) value=1;    //rendo totale
        if(value>6) value=6;    //la funzione
        this.value = value;
        this.color = color;
        //this.usable=true;
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

    public int increaseValue(int value){

        if(value==6){
        }else{value++;}
        return value;
    }
    public int decreaseValue(int value){

        if(value==1){
        }else{value--;}
        return value;
    }

    @Override
    public boolean equals(Object o) {       //usata per i test
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDice dice = (ClientDice) o;
        return value == dice.value &&
                color == dice.color;
    }

    @Override
    public int hashCode() {     //generata automaticamente da inetllij

        return Objects.hash(value, color);
    }
}
