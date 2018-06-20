package Progetto_Ing_Sw.com.server.Model;

public class PlaceDiceException extends  Exception {
    String message;

    public PlaceDiceException(String message){
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
