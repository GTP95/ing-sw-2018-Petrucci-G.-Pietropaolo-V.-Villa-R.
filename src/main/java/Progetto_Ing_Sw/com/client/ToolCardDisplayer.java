package Progetto_Ing_Sw.com.client;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;

public class ToolCardDisplayer extends Stage {
    Scene ToolCardDisplay;
    Scene ToolCarddisplayerScene2;
    ClientDice DieToInsert;
    int valoredado =0;
    String GrozingCommand;
    Label DieChoosen;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    ToolCardDisplayer(String Title, int Number, String Description, String Info, String CardColor, boolean FirstUsage) {
        this.setTitle(Title);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);


        //ImageView della tool card
        ImageView ToolCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/BaseToolCard.png");
        ImageView ToolCardImage = new ImageView(Info);

        //Bottoni Utili
        Button Pay1Icon = new Button("Pay 1 Token to Buy this Card");
        Pay1Icon.setTranslateX(450);
        Pay1Icon.setMinSize(256, 256);
        Pay1Icon.setId("Pay1Button");
        Pay1Icon.setContentDisplay(ContentDisplay.TOP);
        //TODO BUY

        Button Pay2Icon = new Button("Pay 2 Tokens to Buy this Card");
        Pay2Icon.setTranslateX(450);
        Pay2Icon.setMinSize(256, 256);
        Pay2Icon.setId("Pay2Button");

        Button GoBack = new Button();
        GoBack.setTranslateX(-450);
        GoBack.setId("BackButton2");
        GoBack.setMinSize(256, 256);
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

        Label ToolCardColor = new Label();
        ToolCardColor.setId(CardColor);
        ToolCardColor.setPrefSize(130, 100);
        ToolCardColor.setTranslateY(-280);
        ToolCardColor.setTranslateX(-180);

        StackPane ToolCardD = new StackPane();
        ToolCardD.setId("ToolCardDisplayer");
        ToolCardD.setPrefSize(500, 711);
        ToolCardD.getChildren().addAll(ToolCardColor, ToolCardSample, ToolCardTitle, ToolCardNumber, ToolCardDescription, ToolCardImage);

        TranslateTransition SceneTransition = new TranslateTransition(Duration.millis(700), ToolCardD);
        SceneTransition.setFromX(0);
        SceneTransition.setToX(-350);
        SceneTransition.setAutoReverse(false);

        StackPane ToolCardDisplayer = new StackPane();
        ToolCardDisplayer.setId("ToolCardDisplayer");
        ToolCardDisplayer.setPrefSize(1280, 720);
        if (FirstUsage == true) {
            Pay1Icon.setVisible(false);
            Pay2Icon.setVisible(true);
        } else {
            Pay1Icon.setVisible(true);
            Pay2Icon.setVisible(false);
            Pay1Icon.setOnAction(event -> {
                Pay1Icon.setVisible(false);
                GoBack.setVisible(false);
                SceneTransition.play();
                SceneTransition.setOnFinished(event1 -> this.setScene(ToolCarddisplayerScene2));
            });
        }
        ToolCardDisplayer.getChildren().addAll(ToolCardD, Pay1Icon, Pay2Icon, GoBack);


        ToolCardDisplay = new Scene(ToolCardDisplayer, 1280, 720);
        ToolCardDisplay.setFill(Color.rgb(0, 0, 0, 0.75));
        ToolCardDisplay.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());


        ToolCardTitle.setStyle("-fx-font-weight: bold;");


        this.setScene(ToolCardDisplay);
        //this.show();


        //ToolCardDisplayer second Scene

        //I Label che contengono le varie descrizioni della Carta
        ImageView ToolCardSample1 = new ImageView("Progetto_Ing_Sw/com/client/GUI/BaseToolCard.png");
        ImageView ToolCardImage1 = new ImageView(Info);

        Label ToolCardTitle1 = new Label(Title);
        ToolCardTitle1.setTranslateX(50);
        ToolCardTitle1.setTranslateY(-320);

        Label ToolCardNumber1 = new Label(Integer.toString(Number));
        ToolCardNumber1.setTranslateX(55);
        ToolCardNumber1.setTranslateY(-270);

        Label ToolCardDescription1 = new Label(Description);
        ToolCardDescription1.setTranslateY(240);
        ToolCardDescription1.setTextAlignment(TextAlignment.CENTER);

        Label ToolCardColor1 = new Label();
        ToolCardColor1.setId(CardColor);
        ToolCardColor1.setPrefSize(130, 100);
        ToolCardColor1.setTranslateY(-280);
        ToolCardColor1.setTranslateX(-180);

        StackPane ToolCardD1 = new StackPane();
        ToolCardD1.setId("ToolCardDisplayer");
        ToolCardD1.setMaxSize(500, 711);
        ToolCardD1.setTranslateX(-350);
        ToolCardD1.getChildren().addAll(ToolCardColor1, ToolCardSample1, ToolCardTitle1, ToolCardNumber1, ToolCardDescription1, ToolCardImage1);

        StackPane ToolCardDisplayerSecond = new StackPane();


        //----------------------------------------------------------------------------------INIZIO GROZING PLIERS----------------------------------------------------------------------------------
        Text TitleInfo = new Text("Choose a Die to Increase or Decrease");
        TitleInfo.setStyle("-fx-fill: white;");
        TitleInfo.setTranslateX(380);
        TitleInfo.setTranslateY(-325);


        VBox GrozingPool = new VBox(5);
        GrozingPool.setTranslateX(50);
        GrozingPool.setPadding(new Insets(4, 4, 4, 4));
        GrozingPool.setAlignment(Pos.CENTER);
        GrozingPool.setMaxWidth(100);


        ArrayList<ToggleButton> DiceButtons = new ArrayList<>();
        for (ClientDice dice : LocalModel.getInstance().getDrawnDice()) {
            ToggleButton Die = new ToggleButton();
            Die.setPrefSize(75, 75);
            Die.setMaxSize(75, 75);
            Die.setId("Die");
            Die.setText("DiePresente");
            GrozingPool.getChildren().addAll(Die);
            DiceButtons.add(Die);
        }

        DieChoosen = new Label();
        DieChoosen.setMaxSize(100, 100);
        DieChoosen.setStyle("-fx-background-size: 100 100;");
        DieChoosen.setId("transparent");
        DieChoosen.setTranslateX(430);

        Button AcceptGrozing = new Button();
        AcceptGrozing.setVisible(false);
        AcceptGrozing.setId("NextBTN");
        AcceptGrozing.setMaxSize(150, 150);
        AcceptGrozing.setTranslateX(600);

        Button IncreaseValue = new Button();
        IncreaseValue.setVisible(false);
        IncreaseValue.setMaxSize(200, 200);
        IncreaseValue.setId("IncreaseBTN");
        IncreaseValue.setTranslateY(-100);
        IncreaseValue.setTranslateX(430);
        IncreaseValue.setOnAction(event -> {
            final int dadoincrementato = valoredado + 1;
            DieChoosen.setId(Integer.toString(dadoincrementato) + new ClientColor().IntToColor(DieToInsert.getColor()));
            GrozingCommand = "UP";
            AcceptGrozing.setVisible(true);
        });

        Button DecreaseValue = new Button();
        DecreaseValue.setVisible(false);
        DecreaseValue.setMaxSize(200, 200);
        DecreaseValue.setId("DecreaseBTN");
        DecreaseValue.setTranslateY(100);
        DecreaseValue.setTranslateX(430);
        DecreaseValue.setOnAction(event -> {
            final int dadodecrementato = valoredado - 1;
            DieChoosen.setId(Integer.toString(dadodecrementato) + new ClientColor().IntToColor(DieToInsert.getColor()));
            GrozingCommand = "DOWN";
            AcceptGrozing.setVisible(true);
        });

        for (int i = 0; i < DiceButtons.size(); i++) {
            int LocalValue = LocalModel.getInstance().getDrawnDice().get(i).getValue();
            int LocalColor = LocalModel.getInstance().getDrawnDice().get(i).getColor();
            if (LocalValue == 0) {
                DiceButtons.get(i).setVisible(false);
            } else {
                DiceButtons.get(i).setText("");
                DiceButtons.get(i).setId(Integer.toString(LocalValue) + new ClientColor().IntToColor(LocalColor));
                DiceButtons.get(i).setOnAction(event -> {
                            DieToInsert = new ClientDice(LocalValue, LocalColor);
                            valoredado = DieToInsert.getValue();
                            IncreaseValue.setVisible(true);
                            DecreaseValue.setVisible(true);
                            DieChoosen.setId(Integer.toString(DieToInsert.getValue()) + new ClientColor().IntToColor(DieToInsert.getColor()));
                            System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                        }
                );
            }
            Tooltip t = new Tooltip(("Color: " + new ClientColor().IntToColor(LocalColor) + "\n" + "Value: " + Integer.toString(LocalValue)));
            Tooltip.install(DiceButtons.get(i), t);
        }

        StackPane GrozingPliers = new StackPane();
        GrozingPliers.setPrefSize(1280, 720);
        GrozingPliers.getChildren().addAll(TitleInfo, GrozingPool, IncreaseValue, DecreaseValue, DieChoosen, AcceptGrozing);
        //----------------------------------------------------------------------------------FINE GROZING PLIERS----------------------------------------------------------------------------------

        ToolCardDisplayerSecond.getChildren().addAll(ToolCardD1,GrozingPliers);
        ToolCardDisplayerSecond.setId("ToolCardDisplayer");


        ToolCarddisplayerScene2 = new Scene(ToolCardDisplayerSecond,1280,720);
        ToolCarddisplayerScene2.setFill(Color.rgb(0, 0, 0, 0.75));
        ToolCarddisplayerScene2.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());




    }
}
