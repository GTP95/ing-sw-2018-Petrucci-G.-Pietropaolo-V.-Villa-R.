package Progetto_Ing_Sw.com.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This Class that extends Stage contains the UI parts used to create a Scene where the Player can view all the informations
 * on a Public Objective Card that they selected in the TableGUI Stage
 *
 * The card layout is built by the system using a Default background for all cards to which additional info and image are
 * attached from the corresponding JSON file extracted
 *
 * @author Vincenzo Pietropaolo
 */
public class PublicObjectiveCardDisplayer extends Stage{
    Scene PublicObjectiveCardDisplayer;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    PublicObjectiveCardDisplayer(String Title, String Description, int Value, String Image){
        this.setTitle(Title);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);

        //ImageView della Public Objective Card
        System.out.println(Image);
        ImageView PublicObjectiveCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/PublicObjectiveDefault.png");
        ImageView PublicObjectiveCardImage = new ImageView(Image);

        Button GoBack = new Button();GoBack.setTranslateX(-450);GoBack.setId("BackButton2");GoBack.setMinSize(256,256);
        GoBack.setOnAction(event -> this.close());



        //I Label che contengono le varie descrizioni della carta
        Label PublicObjectiveCardTitle = new Label(Title);PublicObjectiveCardTitle.setTranslateY(245);PublicObjectiveCardTitle.setTranslateX(50);PublicObjectiveCardTitle.setId("PublicObjectiveCardTitle");
        Label PublicObjectiveCardDescription = new Label(Description);PublicObjectiveCardDescription.setTranslateY(295);PublicObjectiveCardDescription.setTranslateX(50);PublicObjectiveCardDescription.setId("PublicObjectiveCardDescription");
        Label PublicObjectiveCardPoints = new Label(Integer.toString(Value));PublicObjectiveCardPoints.setTranslateX(-170);PublicObjectiveCardPoints.setTranslateY(270);PublicObjectiveCardPoints.setId("PublicObjectiveCardNumber");

        StackPane PublicObjectiveCardD = new StackPane();
        PublicObjectiveCardD.getChildren().addAll(PublicObjectiveCardSample,PublicObjectiveCardImage,PublicObjectiveCardDescription,PublicObjectiveCardPoints,PublicObjectiveCardTitle,GoBack);

        PublicObjectiveCardDisplayer = new Scene(PublicObjectiveCardD,1280,720);
        PublicObjectiveCardDisplayer.setFill(Color.rgb(0, 0, 0, 0.75));
        PublicObjectiveCardDisplayer.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());


        this.setScene(PublicObjectiveCardDisplayer);
        //this.show();
    }
}
