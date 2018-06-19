package Progetto_Ing_Sw.com.server.Model;

public class AdjacencyBreakerException extends Throwable {

    String message;

    AdjacencyBreakerException(){
        message="You're not following Adjacency Breaker rules";   //messaggio di default
    }

    AdjacencyBreakerException(String message){
        this.message=message;
    }
}
