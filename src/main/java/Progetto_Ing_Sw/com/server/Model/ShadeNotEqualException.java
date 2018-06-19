package Progetto_Ing_Sw.com.server.Model;

public class ShadeNotEqualException extends Throwable {
    String message;

    ShadeNotEqualException(){
        message="The number of your dice doesn't match the shade of the cell ";   //messaggio di default
    }

    ShadeNotEqualException(String message){
        this.message=message;
    }
}
