package Progetto_Ing_Sw.com.client;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PublicObjectiveCardDisplayer extends Stage{

    Scene PublicObjectiveCardDisplayer;

    PublicObjectiveCardDisplayer(String Title, String Description, int Value){
        this.setTitle(Title);
        this.setResizable(false);

        //ImageView della Public Objective Card
        ImageView PublicObjectiveCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/PublicObjectiveDefault.png");
        ImageView PublicObjectiveCardImage = new ImageView();


        //I Label che contengono le varie descrizioni della carta
        Label PublicObjectiveCardTitle = new Label(Title);PublicObjectiveCardTitle.setTranslateY(245);PublicObjectiveCardTitle.setTranslateX(50);PublicObjectiveCardTitle.setId("PublicObjectiveCardTitle");
        Label PublicObjectiveCardDescription = new Label(Description);PublicObjectiveCardDescription.setTranslateY(295);PublicObjectiveCardDescription.setTranslateX(50);PublicObjectiveCardDescription.setId("PublicObjectiveCardDescription");
        Label PublicObjectiveCardPoints = new Label(Integer.toString(Value));PublicObjectiveCardPoints.setTranslateX(-170);PublicObjectiveCardPoints.setTranslateY(270);PublicObjectiveCardPoints.setId("PublicObjectiveCardNumber");

        StackPane PublicObjectiveCardD = new StackPane();
        PublicObjectiveCardD.getChildren().addAll(PublicObjectiveCardSample,PublicObjectiveCardDescription,PublicObjectiveCardPoints,PublicObjectiveCardTitle);

        PublicObjectiveCardDisplayer = new Scene(PublicObjectiveCardD,500,711);
        PublicObjectiveCardDisplayer.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        this.setScene(PublicObjectiveCardDisplayer);
    }
}
