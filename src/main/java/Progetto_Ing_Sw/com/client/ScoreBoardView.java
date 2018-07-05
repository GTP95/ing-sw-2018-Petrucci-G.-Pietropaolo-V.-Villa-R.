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
    Text PublicObjectiveCards, PublicObjectiveCard1Title, PublicObjectiveCard2Title,PublicObjectiveCard3Title,PublicObjectiveCard1Score,PublicObjectiveCard2Score,PublicObjectiveCard3Score, PrivateObjectiveText,PrivateObjectiveScore, FavorTokensLeft, FavorTokensScore, OpenSpacesText,OpenSpacesScore, TotalText, TotalScoreText;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");
    Scene ScoreBoard;


    ScoreBoardView(int ScorePub1,int ScorePub2,int ScorePub3,int ScorePrivate, int ScoreTokens, int ScoreOpenSpace){
        this.setTitle("Final Score");
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);



        PublicObjectiveCard1Title = new Text(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getTitle()+": ");
        PublicObjectiveCard1Title.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Castellar';");

        PublicObjectiveCard1Score = new Text(Integer.toString(ScorePub1));
        PublicObjectiveCard1Score.setStyle("-fx-fill: white;" +"-fx-font: 50 'Centaur';");

        HBox PublicObjectiveCard1 = new HBox(10);
        PublicObjectiveCard1.getChildren().addAll(PublicObjectiveCard1Title,PublicObjectiveCard1Score);

        PublicObjectiveCard2Title = new Text(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getTitle()+": ");
        PublicObjectiveCard2Title.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Castellar';");

        PublicObjectiveCard2Score = new Text(Integer.toString(ScorePub2));
        PublicObjectiveCard2Score.setStyle("-fx-fill: white;" +"-fx-font: 50 'Centaur';");

        HBox PublicObjectiveCard2 = new HBox(10);
        PublicObjectiveCard2.getChildren().addAll(PublicObjectiveCard2Title,PublicObjectiveCard2Score);

        PublicObjectiveCard3Title = new Text(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getTitle()+": ");
        PublicObjectiveCard3Title.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Castellar';");

        PublicObjectiveCard3Score = new Text(Integer.toString(ScorePub3));
        PublicObjectiveCard3Score.setStyle("-fx-fill: white;" +"-fx-font: 50 'Centaur';");

        HBox PublicObjectiveCard3 = new HBox(10);
        PublicObjectiveCard3.getChildren().addAll(PublicObjectiveCard3Title,PublicObjectiveCard3Score);

        //PRIVATE OBJECTIVE POINTS
        PrivateObjectiveText = new Text("Private Objective: ");
        PrivateObjectiveText.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Castellar';");

        PrivateObjectiveScore = new Text(Integer.toString(ScorePrivate));
        PrivateObjectiveScore.setStyle("-fx-fill: white;" +"-fx-font: 50 'Centaur';");

        HBox PrivateObjective = new HBox(10);
        PrivateObjective.getChildren().addAll(PrivateObjectiveText,PrivateObjectiveScore);

        //FAVOR TOKENS POINTS
        FavorTokensLeft = new Text("Favor Tokens Left: ");
        FavorTokensLeft.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Castellar';");

        FavorTokensScore = new Text(Integer.toString(ScoreTokens));
        FavorTokensScore.setStyle("-fx-fill: white;" +"-fx-font: 50 'Centaur';");

        HBox FavorTokens = new HBox(10);
        FavorTokens.getChildren().addAll(FavorTokensLeft,FavorTokensScore);

        //-1 POINTS FOR OPENS SPACES
        OpenSpacesText = new Text("Open Spaces: ");
        OpenSpacesText.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Castellar';");

        OpenSpacesScore = new Text(Integer.toString(ScoreOpenSpace));
        OpenSpacesScore.setStyle("-fx-fill: white;" +"-fx-font: 50 'Centaur';");

        HBox OpenSpaces = new HBox(10);
        OpenSpaces.getChildren().addAll(OpenSpacesText,OpenSpacesScore);

        //TOTAL POINTS SCORED
        TotalText = new Text("Total: ");
        TotalText.setStyle("-fx-fill: white;" +"-fx-font-weight: bold;" +"-fx-font: 30 'Centaur';");

        TotalScoreText = new Text(Integer.toString(ScorePub1 + ScorePub2 + ScorePub3 + ScorePrivate + ScoreTokens + ScoreOpenSpace));
        TotalScoreText.setStyle("-fx-fill: white;" +"-fx-font: 40 'Centaur';");

        HBox Total = new HBox(10);
        Total.getChildren().addAll(TotalText,TotalScoreText);


        VBox ScoreBoardVBox = new VBox(30);
        ScoreBoardVBox.setPrefSize(720,720);
        ScoreBoardVBox.setTranslateX(360);
        ScoreBoardVBox.getChildren().addAll(PublicObjectiveCard1,PublicObjectiveCard2,PublicObjectiveCard3,PrivateObjective,FavorTokens,OpenSpaces,Total);

        ScoreBoard = new Scene(ScoreBoardVBox,1280,720);
        ScoreBoard.setFill(Color.rgb(0, 0, 0, 0.75));
        ScoreBoard.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

        this.setScene(ScoreBoard);
    }

}
