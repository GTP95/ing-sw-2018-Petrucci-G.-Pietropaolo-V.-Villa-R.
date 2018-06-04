package Progetto_Ing_Sw.com.client;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PublicObjectiveCardDisplayer extends Stage {
    Scene PublicObjectiveCardDisplayer;

    PublicObjectiveCardDisplayer(){
        this.setTitle("Public Objective Card"); //TODO Get Title from current Public Objective Card
        this.setResizable(false);

        //ImageView della Public Objective Card
        ImageView PublicObjectiveCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/PublicObjectiveDefault.png");
        ImageView PublicObjectiveCardImage = new ImageView();


        //I Label che contengono le varie descrizioni della carta //TODO get attributes from file
        Label PublicObjectiveCardTitle = new Label("Column Shade Variety");PublicObjectiveCardTitle.setTranslateY(245);PublicObjectiveCardTitle.setTranslateX(50);PublicObjectiveCardTitle.setId("PublicObjectiveCardTitle");
        Label PublicObjectiveCardDescription = new Label("Columns with no repeated values");PublicObjectiveCardDescription.setTranslateY(295);PublicObjectiveCardDescription.setTranslateX(50);PublicObjectiveCardDescription.setId("PublicObjectiveCardDescription");
        Label PublicObjectiveCardPoints = new Label("4");PublicObjectiveCardPoints.setTranslateX(-170);PublicObjectiveCardPoints.setTranslateY(270);PublicObjectiveCardPoints.setId("PublicObjectiveCardNumber");

        StackPane PublicObjectiveCardD = new StackPane();
        PublicObjectiveCardD.getChildren().addAll(PublicObjectiveCardSample,PublicObjectiveCardDescription,PublicObjectiveCardPoints,PublicObjectiveCardTitle);

        PublicObjectiveCardDisplayer = new Scene(PublicObjectiveCardD,500,711);
        PublicObjectiveCardDisplayer.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        this.setScene(PublicObjectiveCardDisplayer);
    }
}
