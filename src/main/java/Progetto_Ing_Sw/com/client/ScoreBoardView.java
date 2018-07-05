package Progetto_Ing_Sw.com.client;

import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScoreBoardView extends Stage {
    Text PublicObjectiveCards, PublicObjectiveCard1, PublicObjectiveCard2,PublicObjectiveCard3, PrivateObjective, FavorTokens, OpenSpaces, Total;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");


    ScoreBoardView(){
        this.setTitle("Final Score");
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);

        PublicObjectiveCards = new Text("Public Objectives");
        PublicObjectiveCards.setStyle(
                "-fx-fill: white;"
                +"-fx-font-weight: bold;"
                +"-fx-font: 30 'Castellar';"
        );

        PublicObjectiveCard1 = new Text("Public Objective Card 1: ");
        PublicObjectiveCard1.setStyle(
                "-fx-fill: white;"
                +"-fx-font-weight: bold;"
                +"-fx-font: 22 'Centaur';"
        );

        PublicObjectiveCard2 = new Text("Public Objective Card 1: ");
        PublicObjectiveCard2.setStyle(
                "-fx-fill: white;"
                        +"-fx-font-weight: bold;"
                        +"-fx-font: 22 'Centaur';"
        );

        PublicObjectiveCard3 = new Text("Public Objective Card 1: ");
        PublicObjectiveCard3.setStyle(
                "-fx-fill: white;"
                        +"-fx-font-weight: bold;"
                        +"-fx-font: 22 'Centaur';"
        );
    }
}
