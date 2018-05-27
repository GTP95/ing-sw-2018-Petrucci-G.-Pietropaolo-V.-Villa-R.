package Progetto_Ing_Sw.com.server;

public class InvalidUsernameException extends Exception {
final String message;

public InvalidUsernameException(String message){
    this.message=message;
}

    @Override
    public String getMessage() {
        return message;
    }
}
