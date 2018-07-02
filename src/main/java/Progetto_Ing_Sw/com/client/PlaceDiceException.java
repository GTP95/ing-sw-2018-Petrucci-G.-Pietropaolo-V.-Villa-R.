package Progetto_Ing_Sw.com.client;

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
