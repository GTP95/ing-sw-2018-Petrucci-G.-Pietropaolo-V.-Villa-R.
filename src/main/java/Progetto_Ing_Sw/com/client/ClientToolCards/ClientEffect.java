package Progetto_Ing_Sw.com.client.ClientToolCards;

import Progetto_Ing_Sw.com.server.Model.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;

public abstract class ClientEffect {
    public abstract WindowBoard applyEffect() throws PlaceDiceException;
    public abstract String getTitle();

}
