package Progetto_Ing_Sw.com.client.ClientToolCards;


import Progetto_Ing_Sw.com.client.ClientWindowBoard;
import Progetto_Ing_Sw.com.client.PlaceDiceException;

public abstract class ClientEffect {
    public abstract ClientWindowBoard applyEffect() throws PlaceDiceException;
    public abstract String getTitle();

}
