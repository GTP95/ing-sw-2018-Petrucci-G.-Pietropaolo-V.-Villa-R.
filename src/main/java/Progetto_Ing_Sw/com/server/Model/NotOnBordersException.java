package Progetto_Ing_Sw.com.server.Model;

public class NotOnBordersException extends Throwable {

    String message;

    NotOnBordersException(){
        message="You're not starting from borders!";   //messaggio di default
    }

    NotOnBordersException(String message){
        this.message=message;
    }
}
