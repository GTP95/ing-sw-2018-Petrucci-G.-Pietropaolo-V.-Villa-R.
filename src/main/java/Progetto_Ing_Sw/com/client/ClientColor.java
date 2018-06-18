package Progetto_Ing_Sw.com.client;

public final class ClientColor {
    public static final int BLANK=0;
    public static final int RED=1;
    public static final int BLUE=2;
    public static final int PURPLE=3;
    public static final int YELLOW=4;
    public static final int GREEN=5;
    public static final int SHADE=23;


    public String IntToColor(int i){
        String color = new String();
        switch (i){
            case ClientColor.RED: color= "red";
                break;
            case ClientColor.BLUE: color = "blue";
                break;
            case ClientColor.PURPLE: color = "purple";
                break;
            case ClientColor.YELLOW: color = "yellow";
                break;
            case ClientColor.GREEN: color = "green";
                break;
        }
        return color;
    }
}


