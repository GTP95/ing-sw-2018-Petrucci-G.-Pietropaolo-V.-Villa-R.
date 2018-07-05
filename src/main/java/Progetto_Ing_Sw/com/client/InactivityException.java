package Progetto_Ing_Sw.com.client;


public class InactivityException extends Exception {
    private String message;
    InactivityException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
