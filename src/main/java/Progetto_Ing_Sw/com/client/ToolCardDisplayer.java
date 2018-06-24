package Progetto_Ing_Sw.com.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ToolCardDisplayer extends Stage {
    Scene ToolCardDisplay;
    Scene GrozingPliers;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    ToolCardDisplayer(String Title, int Number, String Description, String Info, String CardColor, boolean FirstUsage){
        this.setTitle(Title);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);



        //ImageView della tool card
        ImageView ToolCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/BaseToolCard.png");
        ImageView ToolCardImage = new ImageView(Info);

        //Bottoni Utili
        Button Pay1Icon = new Button("Pay 1 Token to Buy this Card");Pay1Icon.setTranslateX(450);
        Pay1Icon.setMinSize(256,256);
        Pay1Icon.setId("Pay1Button");
        Pay1Icon.setContentDisplay(ContentDisplay.TOP);
        //TODO BUY

        Button Pay2Icon = new Button("Pay 2 Tokens to Buy this Card");
        Pay2Icon.setTranslateX(450);
        Pay2Icon.setMinSize(256,256);
        Pay2Icon.setId("Pay2Button");

        Button GoBack = new Button();
        GoBack.setTranslateX(-450);
        GoBack.setId("BackButton2");
        GoBack.setMinSize(256,256);
        GoBack.setOnAction(event -> this.close());

        //I Label che contengono le varie descrizioni della Carta
        Label ToolCardTitle = new Label(Title);
        ToolCardTitle.setTranslateX(50);
        ToolCardTitle.setTranslateY(-320);

        Label ToolCardNumber = new Label(Integer.toString(Number));
        ToolCardNumber.setTranslateX(55);
        ToolCardNumber.setTranslateY(-270);

        Label ToolCardDescription = new Label(Description);
        ToolCardDescription.setTranslateY(240);
        ToolCardDescription.setTextAlignment(TextAlignment.CENTER);

        Label ToolCardColor = new Label();ToolCardColor.setId(CardColor);
        ToolCardColor.setPrefSize(130,100);
        ToolCardColor.setTranslateY(-280);
        ToolCardColor.setTranslateX(-180);

        StackPane ToolCardD = new StackPane();ToolCardD.setId("ToolCardDisplayer");ToolCardD.setPrefSize(500,711);
        if (FirstUsage==true){
            Pay1Icon.setVisible(false);
            Pay2Icon.setVisible(true);
        }
        else {
            Pay1Icon.setVisible(true);
            Pay2Icon.setVisible(false);
        }
        ToolCardD.getChildren().addAll(ToolCardColor,ToolCardSample,ToolCardTitle,ToolCardNumber,ToolCardDescription,ToolCardImage,Pay1Icon,Pay2Icon,GoBack);

        ToolCardDisplay = new Scene(ToolCardD,1280,720);
        ToolCardDisplay.setFill(Color.rgb(0, 0, 0, 0.75));
        ToolCardDisplay.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());


        ToolCardTitle.setStyle("-fx-font-weight: bold;");


        this.setScene(ToolCardDisplay);
        //this.show();


        //Grozing Pliers Scene

        GrozingPliers = new Scene(ToolCardD,1280,720);
        GrozingPliers.setFill(Color.rgb(0, 0, 0, 0.75));
        GrozingPliers.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());




    }
}
