package Progetto_Ing_Sw.com.server;

public class PublicObjectiveCard extends Card {

    private String title;
    private String description;
    private int victoryPoints;
    //TODO:effect


    public PublicObjectiveCard(String title, String description, int victoryPoints) {
        this.title = title;
        this.description = description;
        this.victoryPoints = victoryPoints;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

}
