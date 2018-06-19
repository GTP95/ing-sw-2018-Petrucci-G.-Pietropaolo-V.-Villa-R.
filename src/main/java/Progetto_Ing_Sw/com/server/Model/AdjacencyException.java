package Progetto_Ing_Sw.com.server.Model;

public class AdjacencyException extends Throwable {

    String message;

    AdjacencyException(){
        message="You're not following adjacency rule";   //messaggio di default
    }

    AdjacencyException(String message){
        this.message=message;
    }
}
