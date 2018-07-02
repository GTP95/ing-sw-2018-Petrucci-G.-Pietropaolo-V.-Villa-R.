package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;

public abstract class Effect {
    public abstract WindowBoard applyEffect() throws PlaceDiceException;
    public abstract String getTitle();
    public abstract boolean isFirstUsage();
    public abstract void setFirstUsage(boolean value);

}
