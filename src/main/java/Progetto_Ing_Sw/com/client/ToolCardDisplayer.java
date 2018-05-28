package Progetto_Ing_Sw.com.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ToolCardDisplayer extends Stage {
    Scene ToolCardDisplay;

    ToolCardDisplayer(){
        this.setTitle("Tool Card");
        this.setResizable(false);


        //ImageView della tool card
        ImageView ToolCardSample = new ImageView("file:///../GUI/BaseToolCard.png");


        //I Label che contengono le varie descrizioni della Carta
        Label ToolCardTitle = new Label("Cork-backed Straightedge");ToolCardTitle.setTranslateX(50);ToolCardTitle.setTranslateY(-320);ToolCardTitle.setId("ToolCardDsiplayer");
        Label ToolCardNumber = new Label("1");ToolCardNumber.setTranslateX(50);ToolCardNumber.setTranslateY(-275);ToolCardNumber.setId("ToolCardDsiplayer");
        Label ToolCardDescription = new Label("After drafting, \r\n increase or decrease the value \r\n of the drafted die by 1");ToolCardDescription.setTranslateY(240);ToolCardDescription.setId("ToolCardDsiplayer");;ToolCardDescription.setTextAlignment(TextAlignment.CENTER);
        Label ToolCardColor = new Label();ToolCardColor.setStyle("-fx-background-color: #d6150c");ToolCardColor.setPrefSize(130,100);ToolCardColor.setTranslateY(-280);ToolCardColor.setTranslateX(-180);

        StackPane ToolCardD = new StackPane();ToolCardD.setId("ToolCardDisplayer");
        ToolCardD.getChildren().addAll(ToolCardColor,ToolCardSample,ToolCardTitle,ToolCardNumber,ToolCardDescription);

        ToolCardDisplay = new Scene(ToolCardD,500,711);
        ToolCardDisplay.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        ToolCardTitle.setStyle("-fx-font-weight: bold;");

        this.setScene(ToolCardDisplay);
        this.show();


    }
}
