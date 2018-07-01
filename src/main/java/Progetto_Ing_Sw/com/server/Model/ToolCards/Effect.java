package Progetto_Ing_Sw.com.server.Model.ToolCards;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.PlaceDiceException;
import Progetto_Ing_Sw.com.server.Model.WindowBoard;

public abstract class Effect {
    public abstract WindowBoard applyEffect(WindowBoard localBoard, String command, Dice dice, int row, int column, int favorTokensUsed) throws PlaceDiceException;

}
