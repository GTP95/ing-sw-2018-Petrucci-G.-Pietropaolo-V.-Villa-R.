package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.RoundTrack;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class RoundTrackView extends Stage {
    VBox Round1Dice,Round2Dice,Round3Dice,Round4Dice,Round5Dice,Round6Dice,Round7Dice,Round8Dice,Round9Dice,Round10Dice;
    VBox Round1, Round2, Round3,Round4,Round5,Round6,Round7, Round8, Round9, Round10;
    Scene RoundTrackScene;
    ArrayList<Label> Round1Array,Round2Array,Round3Array,Round4Array,Round5Array,Round6Array,Round7Array,Round8Array,Round9Array,Round10Array;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    public void LabelToDiceCSS(ArrayList<Label> arrayListLabel,int round){
        System.err.println("General Kenobi!");
            for (int i=0; i<arrayListLabel.size(); i++ ){
                int LocalValue = LocalModel.getInstance().getRoundTrack().getDiceRemained(round-1).get(i).getValue();
                int LocalColor = LocalModel.getInstance().getRoundTrack().getDiceRemained(round-1).get(i).getColor();
                if (LocalValue==0){
                    arrayListLabel.get(i).setVisible(false);
                }
                else {
                    arrayListLabel.get(i).setId(Integer.toString(LocalValue) + new ClientColor().IntToColor(LocalColor));
                }
                Tooltip t= new Tooltip(("Color: "+new ClientColor().IntToColor(LocalColor)+"\n"+"Value: "+Integer.toString(LocalValue)));
                Tooltip.install(arrayListLabel.get(i),t);
            }
    }

    RoundTrackView(){
        this.setTitle("Round Track Board");
        this.setWidth(1280);
        this.setHeight(720);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);

        LocalModel.getInstance().registerAsObserver(this);

        Label Round1Image = new Label();
        Round1Image.setId("Round1");
        Round1Image.setStyle("-fx-background-size: 100 100");
        Round1Image.setMinSize(100,100);

        Label Round2Image = new Label();
        Round2Image.setId("Round2");
        Round2Image.setStyle("-fx-background-size: 100 100");
        Round2Image.setMinSize(100,100);

        Label Round3Image = new Label();
        Round3Image.setId("Round3");
        Round3Image.setStyle("-fx-background-size: 100 100");
        Round3Image.setMinSize(100,100);

        Label Round4Image = new Label();
        Round4Image.setId("Round4");
        Round4Image.setStyle("-fx-background-size: 100 100");
        Round4Image.setMinSize(100,100);

        Label Round5Image = new Label();
        Round5Image.setId("Round5");
        Round5Image.setStyle("-fx-background-size: 100 100");
        Round5Image.setMinSize(100,100);

        Label Round6Image = new Label();
        Round6Image.setId("Round6");
        Round6Image.setStyle("-fx-background-size: 100 100");
        Round6Image.setMinSize(100,100);

        Label Round7Image = new Label();
        Round7Image.setId("Round7");
        Round7Image.setStyle("-fx-background-size: 100 100");
        Round7Image.setMinSize(100,100);

        Label Round8Image = new Label();
        Round8Image.setId("Round8");
        Round8Image.setStyle("-fx-background-size: 100 100");
        Round8Image.setMinSize(100,100);

        Label Round9Image = new Label();
        Round9Image.setId("Round9");
        Round9Image.setStyle("-fx-background-size: 100 100");
        Round9Image.setMinSize(100,100);

        Label Round10Image = new Label();
        Round10Image.setId("Round10");
        Round10Image.setStyle("-fx-background-size: 100 100");
        Round10Image.setMinSize(100,100);



        //Round 1 Pool
        Round1Dice = new VBox(0);
        Round1Dice.setPrefSize(100,675);
        Round1Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(true);
            Round1Array.add(Die);
            Round1Dice.getChildren().addAll(Die);
        }



        //Round 2 Pool
        Round2Dice = new VBox(0);
        Round2Dice.setPrefSize(100,675);
        Round2Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(true);
            Round2Array.add(Die);
            Round2Dice.getChildren().addAll(Die);
        }

        //Round 3 Pool
        Round3Dice = new VBox(0);
        Round3Dice.setPrefSize(100,675);
        Round3Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(true);
            Round3Array.add(Die);
            Round3Dice.getChildren().addAll(Die);
        }

        //Round 4 Pool
        Round4Dice = new VBox(0);
        Round4Dice.setPrefSize(100,675);
        Round4Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round4Array.add(Die);
            Round4Dice.getChildren().addAll(Die);
        }

        //Round 5 Pool
        Round5Dice = new VBox(0);
        Round5Dice.setPrefSize(100,675);
        Round5Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round5Array.add(Die);
            Round5Dice.getChildren().addAll(Die);
        }

        //Round 6 Pool
        Round6Dice = new VBox(0);
        Round6Dice.setPrefSize(100,675);
        Round6Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round6Array.add(Die);
            Round6Dice.getChildren().addAll(Die);
        }

        //Round 7 Pool
        Round7Dice = new VBox(0);
        Round7Dice.setPrefSize(100,675);
        Round7Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round7Array.add(Die);
            Round7Dice.getChildren().addAll(Die);
        }

        //Round 8 Pool
        Round8Dice = new VBox(0);
        Round8Dice.setPrefSize(100,675);
        Round8Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round8Array.add(Die);
            Round8Dice.getChildren().addAll(Die);
        }

        //Round 9 Pool
        Round9Dice = new VBox(0);
        Round9Dice.setPrefSize(100,675);
        Round9Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round9Array.add(Die);
            Round9Dice.getChildren().addAll(Die);
        }

        //Round 10 Pool
        Round10Dice = new VBox(0);
        Round10Dice.setPrefSize(100,675);
        Round10Array = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getClientPlayerArrayList().size()*2+1;i++){
            Label Die = new Label();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setVisible(false);
            Round10Array.add(Die);
            Round10Dice.getChildren().addAll(Die);
        }



        Round1 = new VBox(30);
        Round1.getChildren().addAll(Round1Dice,Round1Image);

        Round2 = new VBox(30);
        Round2.getChildren().addAll(Round2Dice,Round2Image);

        Round3 = new VBox(30);
        Round3.getChildren().addAll(Round3Dice,Round3Image);

        Round4 = new VBox(30);
        Round4.getChildren().addAll(Round4Dice,Round4Image);

        Round5 = new VBox(30);
        Round5.getChildren().addAll(Round5Dice,Round5Image);

        Round6 = new VBox(30);
        Round6.getChildren().addAll(Round6Dice,Round6Image);

        Round7 = new VBox(30);
        Round7.getChildren().addAll(Round7Dice,Round7Image);

        Round8 = new VBox(30);
        Round8.getChildren().addAll(Round8Dice,Round8Image);

        Round9 = new VBox(30);
        Round9.getChildren().addAll(Round9Dice,Round9Image);

        Round10 = new VBox(30);
        Round10.getChildren().addAll(Round10Dice,Round10Image);

        Button goBack = new Button();
        goBack.setId("BackBTN");
        goBack.setMaxSize(100,100);
        goBack.setOnAction(event -> this.close());

        HBox RoundTrackHBox = new HBox(20);
        RoundTrackHBox.getChildren().addAll(goBack,Round1,Round2,Round3,Round4,Round5,Round6,Round7,Round8,Round9,Round10);

        StackPane RoundTrackStack = new StackPane();
        RoundTrackStack.setPrefSize(1280,720);
        RoundTrackStack.setAlignment(RoundTrackHBox,Pos.CENTER);
        RoundTrackStack.getChildren().add(RoundTrackHBox);

        RoundTrackScene = new Scene(RoundTrackStack,1280,720);
        RoundTrackScene.setFill(Color.rgb(0, 0, 0, 0.75));
        RoundTrackScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());



        this.setScene(RoundTrackScene);
    }

    public void updateRoundTrack (){
        Platform.runLater(()-> {
            System.err.println("Hello There!");
            for (int i = 1; i <= LocalModel.getInstance().getRoundNumber(); i++) {
                switch (i) {
                    //case (10): LabelToDiceCSS(Round10Array,10);
                    case (9):
                        LabelToDiceCSS(Round9Array, 9);
                    case (8):
                        LabelToDiceCSS(Round8Array, 8);
                    case (7):
                        LabelToDiceCSS(Round7Array, 7);
                    case (6):
                        LabelToDiceCSS(Round6Array, 6);
                    case (5):
                        LabelToDiceCSS(Round5Array, 5);
                    case (4):
                        LabelToDiceCSS(Round4Array, 4);
                    case (3):
                        LabelToDiceCSS(Round3Array, 3);
                    case (2):
                        LabelToDiceCSS(Round2Array, 2);
                    case (1):
                        System.err.println("You are a bold one!");
                        LabelToDiceCSS(Round1Array, 1);
                }
            }
        });
    }
}
