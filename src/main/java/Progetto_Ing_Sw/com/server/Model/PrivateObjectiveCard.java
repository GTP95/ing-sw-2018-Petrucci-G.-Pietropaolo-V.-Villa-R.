package Progetto_Ing_Sw.com.server.Model;

/**
 * this class implements the PrivateObjectiveCards
 */
public class PrivateObjectiveCard extends Card {
    private int color;

    public PrivateObjectiveCard(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
