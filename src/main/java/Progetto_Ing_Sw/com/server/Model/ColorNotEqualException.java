package Progetto_Ing_Sw.com.server.Model;

public class ColorNotEqualException extends Throwable {

    String message;

    ColorNotEqualException(){
        message="The color of your dice doesn't match the color of the cell ";   //messaggio di default
    }

    ColorNotEqualException(String message){
        this.message=message;
    }
}
