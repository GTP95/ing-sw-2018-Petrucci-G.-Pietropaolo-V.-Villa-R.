package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCard;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScoreBoardView extends Stage {
    Text PublicObjectiveCards, PublicObjectiveCard1Title, PublicObjectiveCard2Title,PublicObjectiveCard3Title,PublicObjectiveCard1Score,PublicObjectiveCard2Score,PublicObjectiveCard3Score, PrivateObjectiveText,PrivateObjectiveScore, FavorTokensLeft, FavorTokensScore, OpenSpacesText,OpenSpacesScore, TotalText, TotalScore;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");
    Scene ScoreBoard;


    ScoreBoardView(){
        this.setTitle("Final Score");
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);



        PublicObjectiveCard1Title = new Text("Public Objective Card 1: ");
        PublicObjectiveCard1Title.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 22 'Centaur';");

        PublicObjectiveCard1Score = new Text("0");
        PublicObjectiveCard1Score.setStyle("-fx-fill: white;" +"-fx-font: 30 'Centaur';");

        HBox PublicObjectiveCard1 = new HBox(10);
        PublicObjectiveCard1.getChildren().addAll(PublicObjectiveCard1Title,PublicObjectiveCard1Score);

        PublicObjectiveCard2Title = new Text("Public Objective Card 1: ");
        PublicObjectiveCard2Title.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 22 'Centaur';");

        PublicObjectiveCard2Score = new Text("0");
        PublicObjectiveCard2Score.setStyle("-fx-fill: white;" +"-fx-font: 30 'Centaur';");

        HBox PublicObjectiveCard2 = new HBox(10);
        PublicObjectiveCard2.getChildren().addAll(PublicObjectiveCard2Title,PublicObjectiveCard2Score);

        PublicObjectiveCard3Title = new Text("Public Objective Card 1: ");
        PublicObjectiveCard3Title.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 22 'Centaur';");

        PublicObjectiveCard3Score = new Text("0");
        PublicObjectiveCard3Score.setStyle("-fx-fill: white;" +"-fx-font: 30 'Centaur';");

        HBox PublicObjectiveCard3 = new HBox(10);
        PublicObjectiveCard3.getChildren().addAll(PublicObjectiveCard3Title,PublicObjectiveCard3Score);

        //PRIVATE OBJECTIVE POINTS
        PrivateObjectiveText = new Text("Private Objective: ");
        PrivateObjectiveText.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 22 'Centaur';");

        PrivateObjectiveScore = new Text("0");
        PrivateObjectiveScore.setStyle("-fx-fill: white;" +"-fx-font: 30 'Centaur';");

        HBox PrivateObjective = new HBox(10);
        PrivateObjective.getChildren().addAll(PrivateObjectiveText,PrivateObjectiveScore);

        //FAVOR TOKENS POINTS
        FavorTokensLeft = new Text("Favor Tokens Left: ");
        FavorTokensLeft.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 22 'Centaur';");

        FavorTokensScore = new Text("0");
        FavorTokensScore.setStyle("-fx-fill: white;" +"-fx-font: 30 'Centaur';");

        HBox FavorTokens = new HBox(10);
        FavorTokens.getChildren().addAll(FavorTokensLeft,FavorTokensScore);

        //-1 POINTS FOR OPENS SPACES
        OpenSpacesText = new Text("Open Spaces: ");
        OpenSpacesText.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 22 'Centaur';");

        OpenSpacesScore = new Text("0");
        OpenSpacesScore.setStyle("-fx-fill: white;" +"-fx-font: 30 'Centaur';");

        HBox OpenSpaces = new HBox(10);
        OpenSpaces.getChildren().addAll(OpenSpacesText,OpenSpacesScore);

        //TOTAL POINTS SCORED
        TotalText = new Text("Total: ");
        TotalText.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Centaur';");

        TotalScore = new Text("0");
        TotalScore.setStyle("-fx-fill: white;" +"-fx-font: 40 'Centaur';");

        HBox Total = new HBox(10);
        Total.getChildren().addAll(TotalText,TotalScore);


        VBox ScoreBoardVBox = new VBox(50);
        ScoreBoardVBox.setPrefSize(1280,720);
        ScoreBoardVBox.setAlignment(Pos.CENTER);
        ScoreBoardVBox.getChildren().addAll(PublicObjectiveCard1,PublicObjectiveCard2,PublicObjectiveCard3,PrivateObjective,FavorTokens,OpenSpaces,Total);

        ScoreBoard = new Scene(ScoreBoardVBox,1280,720);
        ScoreBoard.setFill(Color.rgb(0, 0, 0, 0.75));
        ScoreBoard.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        this.setScene(ScoreBoard);
    }

    /**
     * This method updates the value of the Public Objective Score on the GUI
     */
    public void updatePublicObjectiveScore(){
        Platform.runLater(()->{
            PublicObjectiveCard1Title.setText("X");
            PublicObjectiveCard1Score.setText("0");//TODO PRENDERSI I PUNTEGGI
            PublicObjectiveCard2Title.setText("X");
            PublicObjectiveCard2Score.setText("0");
            PublicObjectiveCard3Title.setText("X");
            PublicObjectiveCard3Score.setText("0");
        });
    }

    /**
     * This method updates the value of the Private Objective Score on the GUI
     */
    public void updatePrivateObjectiveScore(){
        Platform.runLater(()->{
            PrivateObjectiveScore.setText("0");
        });
    }

    /**
     * This method updates the value of the Favor Tokens Score on the GUI
     */
    public void updateFavorTokensScore(){
        Platform.runLater(()->{
            FavorTokensScore.setText("0");
        });
    }

    /**
     * This method updates the value of the Open Spaces Score on the GUI
     */
    public void updateOpenSpacesScore(){
        Platform.runLater(()->{
            OpenSpacesScore.setText("0");
        });
    }

    /**
     * This method updates the value of the Total Score on the GUI
     */
    public void updateTotalScore(){
        Platform.runLater(()->{
            TotalScore.setText("0");
        });
    }
}
