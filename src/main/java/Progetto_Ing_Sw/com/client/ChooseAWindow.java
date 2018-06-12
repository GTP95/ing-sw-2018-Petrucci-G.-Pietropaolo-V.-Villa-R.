package Progetto_Ing_Sw.com.client;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ChooseAWindow extends Stage {
    Scene Window;

    ChooseAWindow(){
        this.setTitle("Choose a Window");
        //this.setMaxHeight(500);
        //this.setMaxWidth(500);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.alwaysOnTopProperty();

        BackgroundImage Board1 = new BackgroundImage( new Image("Progetto_Ing_Sw/com/client/GUI/Board1.JPG"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background Board1BG = new Background(Board1);

        BackgroundImage Board2 = new BackgroundImage( new Image("Progetto_Ing_Sw/com/client/GUI/Board2.JPG"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background Board2BG = new Background(Board2);

        BackgroundImage Board3 = new BackgroundImage( new Image("Progetto_Ing_Sw/com/client/GUI/Board3.JPG"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background Board3BG = new Background(Board3);

        BackgroundImage Board4 = new BackgroundImage( new Image("Progetto_Ing_Sw/com/client/GUI/Board4.JPG"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background Board4BG = new Background(Board4);


        //Bottoni che rappresentano le finestre da scegliere
        Button Window1BTN = new Button();Window1BTN.setTranslateX(1000);Window1BTN.setPrefSize(386,313);Window1BTN.setBackground(Board1BG);
        Button Window2BTN = new Button();Window2BTN.setTranslateX(1000);Window2BTN.setPrefSize(386,313);Window2BTN.setBackground(Board2BG);
        Button Window3BTN = new Button();Window3BTN.setTranslateX(1000);Window3BTN.setPrefSize(386,313);Window3BTN.setBackground(Board3BG);
        Button Window4BTN = new Button();Window4BTN.setTranslateX(1000);Window4BTN.setPrefSize(386,313);Window4BTN.setBackground(Board4BG);

        final Rectangle rectPath = new Rectangle (0, 0, 300, 300);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);

        final Rectangle rectPath2 = new Rectangle (1000, 0, 300, 300);
        rectPath2.setArcHeight(10);
        rectPath2.setArcWidth(10);
        rectPath2.setFill(Color.GREEN);

        //Animazioni Window1BTN
        TranslateTransition Enter1 = new TranslateTransition(Duration.millis(500), Window1BTN);
        Enter1.setFromX(1000);
        Enter1.setToX(0);
        Enter1.setAutoReverse(false);
        Enter1.play();

        TranslateTransition Exit1 = new TranslateTransition(Duration.millis(500), Window1BTN);
        Exit1.setFromX(0);
        Exit1.setToX(-1000);
        Exit1.setAutoReverse(false);

        //Animazioni Window2BTN
        TranslateTransition Enter2 = new TranslateTransition(Duration.millis(500), Window2BTN);
        Enter2.setFromX(1000);
        Enter2.setToX(0);
        Enter2.setAutoReverse(false);

        TranslateTransition Exit2 = new TranslateTransition(Duration.millis(500), Window2BTN);
        Exit2.setFromX(0);
        Exit2.setToX(-1000);
        Exit2.setAutoReverse(false);

        //Animazioni Window3BTN
        TranslateTransition Enter3 = new TranslateTransition(Duration.millis(500), Window3BTN);
        Enter3.setFromX(1000);
        Enter3.setToX(0);
        Enter3.setAutoReverse(false);

        TranslateTransition Exit3 = new TranslateTransition(Duration.millis(500), Window3BTN);
        Exit3.setFromX(0);
        Exit3.setToX(-1000);
        Exit3.setAutoReverse(false);

        //Animazioni WindowBTN4
        TranslateTransition Enter4 = new TranslateTransition(Duration.millis(500), Window4BTN);
        Enter4.setFromX(1000);
        Enter4.setToX(0);
        Enter4.setAutoReverse(false);

        TranslateTransition Exit4 = new TranslateTransition(Duration.millis(500), Window4BTN);
        Exit4.setFromX(0);
        Exit4.setToX(-1000);
        Exit4.setAutoReverse(false);

        /*Path path = new Path();
        path.getElements().add(new MoveTo(100,100));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);*/

        Text ChooseAWindow = new Text("Choose a Window");ChooseAWindow.setStyle("-fx-font: 40 \"Castellar\";-fx-fill: white");ChooseAWindow.setTranslateY(-250);

        Button Play = new Button("Next");Play.setTranslateY(250);Play.setId("NextBTN");Play.setPrefSize(150,150);
        Button Play2 = new Button("Next");Play2.setTranslateY(250);Play2.setVisible(false);Play2.setId("NextBTN");Play2.setPrefSize(150,150);
        Button Play3 = new Button("Next");Play3.setTranslateY(250);Play3.setVisible(false);Play3.setId("NextBTN");Play3.setPrefSize(150,150);
        Button Play4 = new Button("Next");Play4.setTranslateY(250);Play4.setVisible(false);Play4.setId("NextBTN");Play4.setPrefSize(150,150);

        Play.setOnAction(event -> {Exit1.play();Enter2.play();Play.setVisible(false);Play2.setVisible(true);});
        Play2.setOnAction(event -> {Exit2.play();Enter3.play();Play2.setVisible(false);Play3.setVisible(true);});
        Play3.setOnAction(event -> {Exit3.play();Enter4.play();Play3.setVisible(false);Play4.setVisible(true);});
        Play4.setOnAction(event -> {Exit4.play();Enter1.play();Play4.setVisible(false);Play.setVisible(true);});

        /*Button Stop = new Button("Stop");Stop.setTranslateY(200);
        Stop.setOnAction(event -> {translateTransition.pause();});*/


        StackPane Animation = new StackPane();
        Animation.setId("ChooseAWindow");
        Animation.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        Animation.getChildren().addAll(ChooseAWindow,Window4BTN,Window3BTN,Window2BTN,Window1BTN,Play,Play2,Play3,Play4);

        Window = new Scene(Animation,720,720);

        this.setScene(Window);
        this.show();

    }
}
