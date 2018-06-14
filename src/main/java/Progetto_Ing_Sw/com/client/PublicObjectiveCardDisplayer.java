package Progetto_Ing_Sw.com.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PublicObjectiveCardDisplayer extends Stage{

    Scene PublicObjectiveCardDisplayer;

    PublicObjectiveCardDisplayer(String Title, String Description, int Value){
        this.setTitle(Title);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);

        //ImageView della Public Objective Card
        ImageView PublicObjectiveCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/PublicObjectiveDefault.png");
        ImageView PublicObjectiveCardImage = new ImageView();

        Button GoBack = new Button();GoBack.setTranslateX(-450);GoBack.setId("BackButton2");GoBack.setMinSize(256,256);
        GoBack.setOnAction(event -> this.close());



        //I Label che contengono le varie descrizioni della carta
        Label PublicObjectiveCardTitle = new Label(Title);PublicObjectiveCardTitle.setTranslateY(245);PublicObjectiveCardTitle.setTranslateX(50);PublicObjectiveCardTitle.setId("PublicObjectiveCardTitle");
        Label PublicObjectiveCardDescription = new Label(Description);PublicObjectiveCardDescription.setTranslateY(295);PublicObjectiveCardDescription.setTranslateX(50);PublicObjectiveCardDescription.setId("PublicObjectiveCardDescription");
        Label PublicObjectiveCardPoints = new Label(Integer.toString(Value));PublicObjectiveCardPoints.setTranslateX(-170);PublicObjectiveCardPoints.setTranslateY(270);PublicObjectiveCardPoints.setId("PublicObjectiveCardNumber");

        StackPane PublicObjectiveCardD = new StackPane();
        PublicObjectiveCardD.getChildren().addAll(PublicObjectiveCardSample,PublicObjectiveCardDescription,PublicObjectiveCardPoints,PublicObjectiveCardTitle,GoBack);

        PublicObjectiveCardDisplayer = new Scene(PublicObjectiveCardD,1280,720);
        PublicObjectiveCardDisplayer.setFill(Color.rgb(0, 0, 0, 0.75));
        PublicObjectiveCardDisplayer.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());


        this.setScene(PublicObjectiveCardDisplayer);
        //this.show();
    }
}
