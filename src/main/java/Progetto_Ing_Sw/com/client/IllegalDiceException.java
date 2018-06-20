package Progetto_Ing_Sw.com.client;

public class IllegalDiceException extends Exception {
   private String message;

    IllegalDiceException(){
        message="Selected dice is not available";   //messaggio di default
    }

    public IllegalDiceException(String message){
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
