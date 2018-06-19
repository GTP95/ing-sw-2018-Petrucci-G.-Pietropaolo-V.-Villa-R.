package Progetto_Ing_Sw.com.server.Model;

public class OrthogonalValueException extends Throwable {

    String message;

    OrthogonalValueException(){
        message="You're not following orthogonal value's rule";   //messaggio di default
    }

    OrthogonalValueException(String message){
        this.message=message;
    }
}
