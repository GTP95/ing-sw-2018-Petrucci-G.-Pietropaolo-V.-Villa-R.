package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.Card;

public class ClientPublicObjectiveCard extends Card {

    private String title;
    private String description;
    private int victoryPoints;
    private String image;
    //TODO:effect


    public ClientPublicObjectiveCard(String title, String description, int victoryPoints) {
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
