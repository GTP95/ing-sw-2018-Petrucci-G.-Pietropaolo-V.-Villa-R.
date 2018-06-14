package Progetto_Ing_Sw.com.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ToolCardDisplayer extends Stage {
    Scene ToolCardDisplay;

    ToolCardDisplayer(String Title, int Number, String Description){
        this.setTitle(Title);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);


        //ImageView della tool card
        ImageView ToolCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/BaseToolCard.png");
        ImageView ToolCardImage = new ImageView("Progetto_Ing_Sw/com/client/GUI/ToolCards/1ToolCard.png");


        //I Label che contengono le varie descrizioni della Carta
        Label ToolCardTitle = new Label(Title);ToolCardTitle.setTranslateX(50);ToolCardTitle.setTranslateY(-320);ToolCardTitle.setId("ToolCardDsiplayer");
        Label ToolCardNumber = new Label(Integer.toString(Number));ToolCardNumber.setTranslateX(55);ToolCardNumber.setTranslateY(-270);ToolCardNumber.setId("ToolCardDsiplayer");
        Label ToolCardDescription = new Label(Description);ToolCardDescription.setTranslateY(240);ToolCardDescription.setId("ToolCardDsiplayer");;ToolCardDescription.setTextAlignment(TextAlignment.CENTER);
        Label ToolCardColor = new Label();ToolCardColor.setStyle("-fx-background-color: #d6150c;" + "-fx-background-image: url('GUI/cross.png');");ToolCardColor.setPrefSize(130,100);ToolCardColor.setTranslateY(-280);ToolCardColor.setTranslateX(-180);

        StackPane ToolCardD = new StackPane();ToolCardD.setId("ToolCardDisplayer");
        ToolCardD.getChildren().addAll(ToolCardColor,ToolCardSample,ToolCardTitle,ToolCardNumber,ToolCardDescription,ToolCardImage);

        ToolCardDisplay = new Scene(ToolCardD,500,711);
        ToolCardDisplay.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        ToolCardTitle.setStyle("-fx-font-weight: bold;");

        this.setScene(ToolCardDisplay);

    }
}
