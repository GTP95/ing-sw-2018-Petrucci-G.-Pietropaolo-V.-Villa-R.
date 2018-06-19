package Progetto_Ing_Sw.com.server.Model;

public class OrthogonalColorException extends Throwable {

    String message;

    OrthogonalColorException(){
        message="You're not following orthogonal color's rules";   //messaggio di default
    }

    OrthogonalColorException(String message){
        this.message=message;
    }
}
