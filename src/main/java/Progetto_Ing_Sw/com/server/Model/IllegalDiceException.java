package Progetto_Ing_Sw.com.server.Model;

public class IllegalDiceException extends Throwable {
    String message;

    IllegalDiceException(){
        message="Selected dice is not available";   //messaggio di default
    }

    IllegalDiceException(String message){
        this.message=message;
    }
}
