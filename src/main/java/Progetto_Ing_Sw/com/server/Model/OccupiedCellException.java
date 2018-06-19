package Progetto_Ing_Sw.com.server.Model;

public class OccupiedCellException extends Throwable{

    String message;

    OccupiedCellException(){
        message="This cell is already occupied!";   //messaggio di default
    }

    OccupiedCellException(String message){
        this.message=message;
    }
}
