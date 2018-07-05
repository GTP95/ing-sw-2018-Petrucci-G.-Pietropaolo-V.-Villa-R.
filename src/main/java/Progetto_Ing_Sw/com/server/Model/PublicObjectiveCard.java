package Progetto_Ing_Sw.com.server.Model;

/**
 * this class implements the PublicObjectiveCards
 */
public class PublicObjectiveCard extends Card {

    private String title;
    private String description;
    private int victoryPoints;
    private String image;


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

    public String getImagePath(){
        return image;
    }
}
