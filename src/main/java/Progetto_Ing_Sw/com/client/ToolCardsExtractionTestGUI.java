package Progetto_Ing_Sw.com.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import javax.swing.text.StyledEditorKit;
//import java.awt.*;



public class ToolCardsExtractionTestGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15, 20, 20, 10));
        borderPane.setId("pane");
        Scene scene = new Scene(borderPane, 505, 800);
        scene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());
        primaryStage.setTitle("Tool Card Generator");

        Text title =new Text("Grozing Pliers");
        title.setScaleX(2);
        title.setScaleY(2);
        BorderPane.setAlignment(title,Pos.TOP_CENTER);
        //title.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(title);
        // Set margin for top area.
        BorderPane.setMargin(title, new Insets(10, 10, 10, 10));


        Button generateBtn = new Button("Draw");
        generateBtn.setPadding(new Insets(5, 5, 5, 5));
        generateBtn.setPrefSize(200,50);
        borderPane.setBottom(generateBtn);
        BorderPane.setAlignment(generateBtn, Pos.BOTTOM_CENTER);

        //Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();


    }


}
