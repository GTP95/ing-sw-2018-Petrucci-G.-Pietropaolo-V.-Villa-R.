package Progetto_Ing_Sw.com.client;


import Progetto_Ing_Sw.com.server.Model.ToolCards.GlazingHammer;
import Progetto_Ing_Sw.com.server.Model.ToolCards.TapWheel;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

/**
 * This Class that extends Stage contains all the UI elements used to show the Player the selected Tool Card and use its
 * effects.
 *
 * The Stage is composed of two Scenes:
 * the first shows the Player all the info on the Tool Card selected in TableGUI;
 * the second Scene is different depending on the Tool Card and offers all the UI elements necessary for the player to use
 * the effects of a Tool Card.
 *
 * The second Scene is built according to the selected card using a switch case that adds the right Nodes to the Scene.
 * @author Vincenzo Pietropaolo
 */
public class ToolCardDisplayer extends Stage {
    Scene ToolCardDisplay;
    Scene ToolCarddisplayerScene2;
    ClientDice DieToInsert,DieToInsert2;
    int valoredado =0;
    int ColorChosen;
    String GrozingCommand;
    Label DieChoosen;
    ArrayList<ToggleButton> DiceButtons;
    ArrayList<ArrayList<Pane>> GridBlocks;
    Random randomValue;
    Random randomColor;
    TranslateTransition DiceOutOfBagTransition;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");



    /**
     * This method is used to import the Draft Pool in this Stage
     * @return
     */
    public VBox getDraftPool(){
        VBox GrozingPool = new VBox(5);
        GrozingPool.setTranslateX(50);
        GrozingPool.setPadding(new Insets(4, 4, 4, 4));
        GrozingPool.setAlignment(Pos.CENTER);
        GrozingPool.setMaxWidth(100);

        ToggleGroup  Dicegroup = new ToggleGroup();
        DiceButtons = new ArrayList<>();
        for (ClientDice dice : LocalModel.getInstance().getDrawnDice()) {
            ToggleButton Die = new ToggleButton();
            Die.setPrefSize(75, 75);
            Die.setMaxSize(75, 75);
            Die.setId("Die");
            Die.setText("DiePresente");
            Die.setToggleGroup(Dicegroup);
            GrozingPool.getChildren().addAll(Die);
            DiceButtons.add(Die);
        }
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
                            DieChoosen.setId(Integer.toString(DieToInsert.getValue()) + new ClientColor().IntToColor(DieToInsert.getColor()));
                            System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                        }
                );
            }
            Tooltip t = new Tooltip(("Color: " + new ClientColor().IntToColor(LocalColor) + "\n" + "Value: " + Integer.toString(LocalValue)));
            Tooltip.install(DiceButtons.get(i), t);
        }

        return GrozingPool;
    }

    public GridPane CreateAGrid (ClientGameBoardCard gameBoardCard){
        int rows = 4;
        int columns = 5;

        GridPane Board = new GridPane();
        Board.setTranslateY(-20);
        Board.setAlignment(Pos.CENTER);
        Board.setId("TheGrid");
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            Board.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(75);
            Board.getRowConstraints().add(row);
        }

        int [][] matrixTexture = gameBoardCard.getMatrixScheme();

        for (int r = 0; r < matrixTexture.length; r++) {
            for (int c = 0; c < matrixTexture[r].length; c++) {
                Pane block = new Pane();
                block.setId("Block");
                switch (matrixTexture[r][c]) {
                    case (0):
                        break;
                    case (1):
                        block.setStyle("-fx-background-color: red;");
                        break;
                    case (2):
                        block.setStyle("-fx-background-color: #46ddff;");
                        break;
                    case (3):
                        block.setStyle("-fx-background-color: #a800a8;");
                        break;
                    case (4):
                        block.setStyle("-fx-background-color: Yellow;");
                        break;
                    case (5):
                        block.setStyle("-fx-background-color: #009d1d;");
                        break;
                    case (6):
                        block.setId("Shade1");
                        break;
                    case (7):
                        block.setId("Shade2");
                        break;
                    case (8):
                        block.setId("Shade3");
                        break;
                    case (9):
                        block.setId("Shade4");
                        break;
                    case (10):
                        block.setId("Shade5");
                        break;
                    case (11):
                        block.setId("Shade6");
                        break;
                }
                Board.add(block, c, r);

            }
        }
        return Board;
    }

    public GridPane getWindowBoardDiceBoard () {
        int rows = 4;
        int columns = 5;
        GridPane Board = new GridPane();
        Board.setTranslateY(-20);
        Board.setAlignment(Pos.CENTER);
        Board.setId("TheGrid");
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            Board.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(75);
            Board.getRowConstraints().add(row);
        }
        GridBlocks = new ArrayList<>();
        for (int r = 0; r < LocalModel.getInstance().getWindowBoard().getUsedMatrix().size(); r++) {
            //ArrayList<Pane> PaneRow = new ArrayList<>();
            for (int c = 0; c < LocalModel.getInstance().getWindowBoard().getUsedMatrix().get(r).size(); c++) {
                Pane block = new Pane();
                if (LocalModel.getInstance().getWindowBoard().getUsedMatrix().get(r).get(c).isUsed()) {
                    block.setId(Integer.toString(LocalModel.getInstance().getWindowBoard().getUsedMatrix().get(r).get(c).getDiceContained().getValue())
                            + new ClientColor().IntToColor(LocalModel.getInstance().getWindowBoard().getUsedMatrix().get(r).get(c).getDiceContained().getColor()));
                    block.setStyle("-fx-opacity: 0.90;" + "-fx-background-size: 60 60");
                } else {
                    block.setId("DieBlock");
                }
                Board.add(block, c, r);
            }
        }
            return Board;
    }



    /**
     * This stage needs the following parameters to build th right Tool Card
     * @param Title
     * @param Number
     * @param Description
     * @param Info
     * @param CardColor
     * @param FirstUsage
     */
    ToolCardDisplayer(String Title, int Number, String Description, String Info, String CardColor, boolean FirstUsage,int index) {
        this.setTitle(Title);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(windowIcon);

        LocalModel.getInstance().registerAsObserver(this);

        //ImageView della tool card
        ImageView ToolCardSample = new ImageView("Progetto_Ing_Sw/com/client/GUI/BaseToolCard.png");
        ImageView ToolCardImage = new ImageView(Info);

        //Bottoni Utili
        Button Pay1Icon = new Button("");
        Pay1Icon.setTranslateX(450);
        Pay1Icon.setMinSize(256, 256);
        Pay1Icon.setId("Pay1Button");
        Pay1Icon.setContentDisplay(ContentDisplay.TOP);


        Button Pay2Icon = new Button("");
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
            Pay2Icon.setOnAction(event -> {
                Pay2Icon.setVisible(false);
                GoBack.setVisible(false);
                SceneTransition.play();
                SceneTransition.setOnFinished(event1 -> {
                    this.setScene(ToolCarddisplayerScene2);
                    ToolCardD.setTranslateX(0);
            });
        });
        }
        else {
            Pay1Icon.setVisible(true);
            Pay2Icon.setVisible(false);
            Pay1Icon.setOnAction(event -> {

                Pay1Icon.setVisible(false);
                GoBack.setVisible(false);
                SceneTransition.play();
                SceneTransition.setOnFinished(event1 -> {
                    this.setScene(ToolCarddisplayerScene2);
                    ToolCardD.setTranslateX(0);
                });

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

        Button ToolCard1BTN = new Button();
        ToolCard1BTN.setMinSize(500,711);
        ToolCard1BTN.setStyle("-fx-background-color: transparent;");

        StackPane ToolCardD1 = new StackPane();
        ToolCardD1.setId("ToolCardDisplayer");
        ToolCardD1.setMaxSize(500, 711);
        ToolCardD1.setTranslateX(-350);
        ToolCardD1.getChildren().addAll(ToolCardColor1, ToolCardSample1, ToolCardTitle1, ToolCardNumber1, ToolCardDescription1, ToolCardImage1,ToolCard1BTN);

        TranslateTransition ReturnToFirstScene = new TranslateTransition(Duration.millis(700),ToolCardD1);
        ReturnToFirstScene.setFromX(-350);
        ReturnToFirstScene.setToX(0);
        ReturnToFirstScene.setAutoReverse(false);

        ToolCard1BTN.setOnAction(event -> {
            ReturnToFirstScene.play();
            ReturnToFirstScene.setOnFinished(event1 -> {
                this.setScene(ToolCardDisplay);
                ToolCardD1.setTranslateX(-350);
            });
            Pay1Icon.setVisible(true);
            GoBack.setVisible(true);
        });


        StackPane ToolCardDisplayerSecond = new StackPane();

        DieChoosen = new Label();
        DieChoosen.setMaxSize(100, 100);
        DieChoosen.setStyle("-fx-background-size: 100 100;");
        DieChoosen.setId("trasparent");
        DieChoosen.setTranslateX(430);


        //----------------------------------------------------------------------------------INIZIO GROZING PLIERS----------------------------------------------------------------------------------//
        Text TitleInfo = new Text("Choose a Die to Increase or Decrease");
        TitleInfo.setStyle("-fx-fill: white;");
        TitleInfo.setTranslateX(350);
        TitleInfo.setTranslateY(-325);

        VBox GrozingPool = getDraftPool();

        Button AcceptGrozing = new Button();
        AcceptGrozing.setVisible(false);
        AcceptGrozing.setId("NextBTN");
        AcceptGrozing.setMaxSize(150, 150);
        AcceptGrozing.setTranslateX(600);
        AcceptGrozing.setOnAction(event -> {
            LocalModel.getInstance().useGrozingPliers(DieToInsert,GrozingCommand);
        });

        Button IncreaseValue = new Button();
        IncreaseValue.setVisible(true);
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
        DecreaseValue.setVisible(true);
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

        StackPane GrozingPliers = new StackPane();
        GrozingPliers.setPrefSize(1280, 720);

        //----------------------------------------------------------------------------------FINE GROZING PLIERS----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO EGLOMISE BRUSH----------------------------------------------------------------------------------//
        Text EglomiseBrushInfo = new Text("Choose a Die and a position to move it to");
        EglomiseBrushInfo.setStyle("-fx-fill: white;");
        EglomiseBrushInfo.setTranslateX(350);
        EglomiseBrushInfo.setTranslateY(-325);


        GridPane EglomiseBrushGrid = CreateAGrid(LocalModel.getInstance().getChoosenGameBoardCard());
        EglomiseBrushGrid.setTranslateX(350);

        GridPane EglomiseBrushDieGrid = getWindowBoardDiceBoard();
        EglomiseBrushDieGrid.setTranslateX(350);

        Text fromRowEglomise = new Text("From\nRow");
        fromRowEglomise.setTranslateY(275);
        fromRowEglomise.setTranslateX(100);
        fromRowEglomise.setStyle("-fx-fill: white;");
        TextField FromRowBrush = new TextField();
        FromRowBrush.setMaxSize(100,50);
        FromRowBrush.setTranslateX(100);
        FromRowBrush.setTranslateY(340);

        Text fromColumnEglomise = new Text("From\nColumn");
        fromColumnEglomise.setTranslateY(275);
        fromColumnEglomise.setTranslateX(220);
        fromColumnEglomise.setStyle("-fx-fill: white;");
        TextField FromColumnBrush = new TextField();
        FromColumnBrush.setMaxSize(100,50);
        FromColumnBrush.setTranslateX(220);
        FromColumnBrush.setTranslateY(340);

        Text toRowEglomise = new Text("To\nRow");
        toRowEglomise.setTranslateY(275);
        toRowEglomise.setTranslateX(340);
        toRowEglomise.setStyle("-fx-fill: white;");
        TextField ToRowBrush = new TextField();
        ToRowBrush.setMaxSize(100,50);
        ToRowBrush.setTranslateX(340);
        ToRowBrush.setTranslateY(340);

        Text toColumnEglomise = new Text("To\nColumn");
        toColumnEglomise.setTranslateY(275);
        toColumnEglomise.setTranslateX(460);
        toColumnEglomise.setStyle("-fx-fill: white;");
        TextField ToColumnBrush = new TextField();
        ToColumnBrush.setMaxSize(100,50);
        ToColumnBrush.setTranslateX(460);
        ToColumnBrush.setTranslateY(340);

        Button AcceptEglomiseBrush = new Button();
        AcceptEglomiseBrush.setId("NextBTN");
        AcceptEglomiseBrush.setMaxSize(150, 150);
        AcceptEglomiseBrush.setTranslateX(600);
        AcceptEglomiseBrush.setOnAction(event ->
                LocalModel.getInstance().useEglomiseBrush(
                        Integer.parseInt(FromRowBrush.getText()),
                        Integer.parseInt(FromColumnBrush.getText()),
                        Integer.parseInt(ToRowBrush.getText()),
                        Integer.parseInt(ToColumnBrush.getText())
                )
        );



        StackPane EglomiseBrush = new StackPane();
        EglomiseBrush.setPrefSize(1280,720);
        //----------------------------------------------------------------------------------FINE EGLOMISE BRUSH----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO COPPER FOIL BURNISHER----------------------------------------------------------------------------------//
        Text CopperFoilBurnisherInfo = new Text("Choose a Die and a position to move it to");
        CopperFoilBurnisherInfo.setStyle("-fx-fill: white;");
        CopperFoilBurnisherInfo.setTranslateX(380);
        CopperFoilBurnisherInfo.setTranslateY(-325);


        GridPane CopperFoilBurnisherGrid = CreateAGrid(LocalModel.getInstance().getChoosenGameBoardCard());
        CopperFoilBurnisherGrid.setTranslateX(350);

        GridPane CopperFoilBurnisherDieGrid = getWindowBoardDiceBoard();
        CopperFoilBurnisherDieGrid.setTranslateX(350);

        Text fromRowCopperFoil = new Text("From\nRow");
        fromRowCopperFoil.setTranslateY(275);
        fromRowCopperFoil.setTranslateX(100);
        fromRowCopperFoil.setStyle("-fx-fill: white;");
        TextField FromRowBurnisher = new TextField();
        FromRowBurnisher.setMaxSize(100,50);
        FromRowBurnisher.setTranslateX(100);
        FromRowBurnisher.setTranslateY(340);

        Text fromColumnCopperFoil = new Text("From\nColumn");
        fromColumnCopperFoil.setTranslateY(275);
        fromColumnCopperFoil.setTranslateX(220);
        fromColumnCopperFoil.setStyle("-fx-fill: white;");
        TextField FromColumnBurnisher = new TextField();
        FromColumnBurnisher.setMaxSize(100,50);
        FromColumnBurnisher.setTranslateX(220);
        FromColumnBurnisher.setTranslateY(340);

        Text toRowCopperFoil = new Text("To\nRow");
        toRowCopperFoil.setTranslateY(275);
        toRowCopperFoil.setTranslateX(340);
        toRowCopperFoil.setStyle("-fx-fill: white;");
        TextField ToRowBurnisher = new TextField();
        ToRowBurnisher.setMaxSize(100,50);
        ToRowBurnisher.setTranslateX(340);
        ToRowBurnisher.setTranslateY(340);

        Text toColumnCopperFoil = new Text("To\nColumn");
        toColumnCopperFoil.setTranslateY(275);
        toColumnCopperFoil.setTranslateX(460);
        toColumnCopperFoil.setStyle("-fx-fill: white;");
        TextField ToColumnBurnisher = new TextField();
        ToColumnBurnisher.setMaxSize(100,50);
        ToColumnBurnisher.setTranslateX(460);
        ToColumnBurnisher.setTranslateY(340);

        Button AcceptCopperFoilBurnisher = new Button();
        AcceptCopperFoilBurnisher.setId("NextBTN");
        AcceptCopperFoilBurnisher.setMaxSize(150, 150);
        AcceptCopperFoilBurnisher.setTranslateX(600);
        AcceptCopperFoilBurnisher.setOnAction(event ->
                LocalModel.getInstance().useCopperFoilBurnisher(
                        Integer.parseInt(FromRowBurnisher.getText()),
                        Integer.parseInt(FromColumnBurnisher.getText()),
                        Integer.parseInt(ToRowBurnisher.getText()),
                        Integer.parseInt(ToColumnBurnisher.getText())
                )
        );

        StackPane CopperFoilBurnisher = new StackPane();
        CopperFoilBurnisher.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------FINE COPPER FOIL BURNISHER----------------------------------------------------------------------------------//
        //----------------------------------------------------------------------------------INIZIO LATHEKIN----------------------------------------------------------------------------------//
        Text LathekinInfo = new Text("Choose 2 Dice and two places to move them to");
        LathekinInfo.setStyle("-fx-fill: white;");
        LathekinInfo.setTranslateX(350);
        LathekinInfo.setTranslateY(-325);


        GridPane LathekinGrid = CreateAGrid(LocalModel.getInstance().getChoosenGameBoardCard());
        LathekinGrid.setTranslateX(350);

        GridPane LathekinDieGrid = getWindowBoardDiceBoard();
        LathekinDieGrid.setTranslateX(350);

        Text fromRowLathekin = new Text("From\nRow");
        fromRowLathekin.setTranslateY(175);
        fromRowLathekin.setTranslateX(100);
        fromRowLathekin.setStyle("-fx-fill: white;");
        TextField FromRow1Lathekin = new TextField();
        FromRow1Lathekin.setMaxSize(100,50);
        FromRow1Lathekin.setTranslateX(100);
        FromRow1Lathekin.setTranslateY(280);
        TextField FromRow2Lathekin = new TextField();
        FromRow2Lathekin.setMaxSize(100,50);
        FromRow2Lathekin.setTranslateX(100);
        FromRow2Lathekin.setTranslateY(340);


        Text fromColumnLathekin = new Text("From\nColumn");
        fromColumnLathekin.setTranslateY(175);
        fromColumnLathekin.setTranslateX(220);
        fromColumnLathekin.setStyle("-fx-fill: white;");
        TextField FromColumn1Lathekin = new TextField();
        FromColumn1Lathekin.setMaxSize(100,50);
        FromColumn1Lathekin.setTranslateX(220);
        FromColumn1Lathekin.setTranslateY(280);
        TextField FromColumn2Lathekin = new TextField();
        FromColumn2Lathekin.setMaxSize(100,50);
        FromColumn2Lathekin.setTranslateX(220);
        FromColumn2Lathekin.setTranslateY(340);

        Text toRowLathekin = new Text("To\nRow");
        toRowLathekin.setTranslateY(175);
        toRowLathekin.setTranslateX(340);
        toRowLathekin.setStyle("-fx-fill: white;");
        TextField ToRow1Lathekin = new TextField();
        ToRow1Lathekin.setMaxSize(100,50);
        ToRow1Lathekin.setTranslateX(340);
        ToRow1Lathekin.setTranslateY(280);
        TextField ToRow2Lathekin = new TextField();
        ToRow2Lathekin.setMaxSize(100,50);
        ToRow2Lathekin.setTranslateX(340);
        ToRow2Lathekin.setTranslateY(340);

        Text toColumnLathekin = new Text("To\nColumn");
        toColumnLathekin.setTranslateY(175);
        toColumnLathekin.setTranslateX(460);
        toColumnLathekin.setStyle("-fx-fill: white;");
        TextField ToColumn1Lathekin = new TextField();
        ToColumn1Lathekin.setMaxSize(100,50);
        ToColumn1Lathekin.setTranslateX(460);
        ToColumn1Lathekin.setTranslateY(280);
        TextField ToColumn2Lathekin = new TextField();
        ToColumn2Lathekin.setMaxSize(100,50);
        ToColumn2Lathekin.setTranslateX(460);
        ToColumn2Lathekin.setTranslateY(340);


        Button AcceptLathekin = new Button();
        AcceptLathekin.setId("NextBTN");
        AcceptLathekin.setMaxSize(150, 150);
        AcceptLathekin.setTranslateX(600);
        AcceptLathekin.setOnAction(event ->
                LocalModel.getInstance().useLathekin(
                        Integer.parseInt(FromRow1Lathekin.getText()),
                        Integer.parseInt(FromColumn1Lathekin.getText()),
                        Integer.parseInt(ToRow1Lathekin.getText()),
                        Integer.parseInt(ToColumn1Lathekin.getText()),
                        Integer.parseInt(FromRow2Lathekin.getText()),
                        Integer.parseInt(FromColumn2Lathekin.getText()),
                        Integer.parseInt(ToRow2Lathekin.getText()),
                        Integer.parseInt(ToColumn2Lathekin.getText())
                )
        );



        StackPane Lathekin = new StackPane();
        Lathekin.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------INIZIO GRINDING STONE----------------------------------------------------------------------------------//
        Text GrindingStoneInfo = new Text("Choose a Die to Flip");
        GrindingStoneInfo.setStyle("-fx-fill: white;");
        GrindingStoneInfo.setTranslateX(380);
        GrindingStoneInfo.setTranslateY(-325);

        VBox GrindingStonePool = getDraftPool();

        Button AcceptGridingStone = new Button();
        AcceptGridingStone.setId("NextBTN");
        AcceptGridingStone.setMaxSize(150, 150);
        AcceptGridingStone.setVisible(false);
        AcceptGridingStone.setTranslateX(600);
        AcceptGridingStone.setOnAction(event ->
            LocalModel.getInstance().useGrindingStone(DieToInsert)
        );

        Button FlipButton = new Button("Flip Die");
        FlipButton.setId("transparent");
        FlipButton.setStyle("-fx-font: 25 'Castellar';");
        FlipButton.setTranslateX(430);
        FlipButton.setTranslateY(100);
        FlipButton.setVisible(true);
        FlipButton.setOnAction(event -> {
            switch (valoredado){
                case(1):DieChoosen.setId(Integer.toString(6)+ new ClientColor().IntToColor(DieToInsert.getColor()));
                break;
                case(2):DieChoosen.setId(Integer.toString(5)+ new ClientColor().IntToColor(DieToInsert.getColor()));
                break;
                case (3):DieChoosen.setId(Integer.toString(4)+ new ClientColor().IntToColor(DieToInsert.getColor()));
                break;
                case (4):DieChoosen.setId(Integer.toString(3)+ new ClientColor().IntToColor(DieToInsert.getColor()));
                break;
                case(5):DieChoosen.setId(Integer.toString(2)+ new ClientColor().IntToColor(DieToInsert.getColor()));
                break;
                case(6):DieChoosen.setId(Integer.toString(1)+ new ClientColor().IntToColor(DieToInsert.getColor()));
            }
            FlipButton.setDisable(true);
            AcceptGridingStone.setVisible(true);
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
                            FlipButton.setVisible(true);
                            DieChoosen.setId(Integer.toString(DieToInsert.getValue()) + new ClientColor().IntToColor(DieToInsert.getColor()));
                            System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                        }
                );
            }
            Tooltip t = new Tooltip(("Color: " + new ClientColor().IntToColor(LocalColor) + "\n" + "Value: " + Integer.toString(LocalValue)));
            Tooltip.install(DiceButtons.get(i), t);
        }

        StackPane GridingStone = new StackPane();
        GridingStone.setPrefSize(1280,720);



        //----------------------------------------------------------------------------------FINE GRINDING STONE----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO FLUX REMOVER----------------------------------------------------------------------------------//

        Text FluxRemoverInfo = new Text("Choose to return to the Dice Bag");
        FluxRemoverInfo.setStyle("-fx-fill: white;");
        FluxRemoverInfo.setTranslateX(380);
        FluxRemoverInfo.setTranslateY(-325);

        VBox FluxRemoverPool = getDraftPool();

        Label DiceBag = new Label();
        DiceBag.setPrefSize(400,400);
        DiceBag.setId("DiceBag");
        DiceBag.setTranslateX(430);
        DiceBag.setTranslateY(365);


        Button AcceptFluxRemover = new Button();
        AcceptFluxRemover.setId("NextBTN");
        AcceptFluxRemover.setMaxSize(150, 150);
        AcceptFluxRemover.setTranslateX(600);
        AcceptFluxRemover.setVisible(false);
        AcceptFluxRemover.setOnAction(event -> {
            LocalModel.getInstance().getDiceToUseWithEffect().setValue(valoredado);
            LocalModel.getInstance().notifyFluxRemoverDiceValueSet();
        });

        Button IncreaseFluxRemover = new Button();
        IncreaseFluxRemover.setVisible(false);
        IncreaseFluxRemover.setMaxSize(200, 200);
        IncreaseFluxRemover.setId("IncreaseBTN");
        IncreaseFluxRemover.setTranslateY(-100);
        IncreaseFluxRemover.setTranslateX(430);
        IncreaseFluxRemover.setOnAction(event -> {
            DieChoosen.setId(Integer.toString(valoredado++) + new ClientColor().IntToColor(LocalModel.getInstance().getDiceToUseWithEffect().getColor()));
            valoredado= valoredado++;
            if (valoredado==6){
                IncreaseFluxRemover.setVisible(false);
            }
            else if (valoredado<6){IncreaseFluxRemover.setVisible(true);}

        });

        Button DecreaseFluxRemover = new Button();
        DecreaseFluxRemover.setVisible(false);
        DecreaseFluxRemover.setMaxSize(200, 200);
        DecreaseFluxRemover.setId("DecreaseBTN");
        DecreaseFluxRemover.setTranslateY(100);
        DecreaseFluxRemover.setTranslateX(430);
        DecreaseFluxRemover.setOnAction(event -> {
            DieChoosen.setId(Integer.toString(valoredado--) + new ClientColor().IntToColor(LocalModel.getInstance().getDiceToUseWithEffect().getColor()));
            valoredado= valoredado--;
            if (valoredado==1){
                DecreaseFluxRemover.setVisible(false);
            }
            else if (valoredado>1){DecreaseFluxRemover.setVisible(true);}

        });

        Button AcceptBag = new Button();
        AcceptBag.setId("NextBTN");
        AcceptBag.setMaxSize(150, 150);
        AcceptBag.setTranslateX(150);
        AcceptBag.setOnAction(event -> {
            AcceptBag.setDisable(true);
            TranslateTransition DiceBagTransition = new TranslateTransition(Duration.millis(500),DieChoosen);
            DiceBagTransition.setFromY(0);
            DiceBagTransition.setToY(720);
            DiceBagTransition.setAutoReverse(false);
            DiceBagTransition.play();
            DiceBagTransition.setOnFinished(event1 ->LocalModel.getInstance().useFluxRemover(DieToInsert));
            DiceOutOfBagTransition = new TranslateTransition(Duration.millis(500),DieChoosen);
            DiceOutOfBagTransition.setFromY(720);
            DiceOutOfBagTransition.setToY(0);
            DiceOutOfBagTransition.setAutoReverse(false);
            DiceOutOfBagTransition.setOnFinished(event1 -> {
                AcceptFluxRemover.setVisible(true);
                IncreaseFluxRemover.setVisible(true);
                DecreaseFluxRemover.setVisible(true);
            });

        });

        StackPane FluxRemover = new StackPane();
        FluxRemover.setPrefSize(1280,720);


        //----------------------------------------------------------------------------------FINE FLUX REMOVER----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO CORK-BACKED STRAIGHTEDGE----------------------------------------------------------------------------------//
        Text CorkBackedStraightedgeInfo = new Text("Choose a Die and a position to move it to");
        CorkBackedStraightedgeInfo.setStyle("-fx-fill: white;");
        CorkBackedStraightedgeInfo.setTranslateX(350);
        CorkBackedStraightedgeInfo.setTranslateY(-325);


        GridPane CorkBackedStraightedgeGrid = CreateAGrid(LocalModel.getInstance().getChoosenGameBoardCard());
        CorkBackedStraightedgeGrid.setTranslateX(350);

        GridPane CorkBackedStraightedgeDieGrid = getWindowBoardDiceBoard();
        CorkBackedStraightedgeDieGrid.setTranslateX(350);

        VBox CorkBackedDraftPool = getDraftPool();

        Text toRowCorkBacked = new Text("To\nRow");
        toRowCorkBacked.setTranslateY(275);
        toRowCorkBacked.setTranslateX(340);
        toRowCorkBacked.setStyle("-fx-fill: white;");
        TextField ToRowCorkBacked = new TextField();
        ToRowCorkBacked.setMaxSize(100,50);
        ToRowCorkBacked.setTranslateX(340);
        ToRowCorkBacked.setTranslateY(340);

        Text toColumnCorkBacked = new Text("To\nColumn");
        toColumnCorkBacked.setTranslateY(275);
        toColumnCorkBacked.setTranslateX(460);
        toColumnCorkBacked.setStyle("-fx-fill: white;");
        TextField ToColumnCorkBacked = new TextField();
        ToColumnCorkBacked.setMaxSize(100,50);
        ToColumnCorkBacked.setTranslateX(460);
        ToColumnCorkBacked.setTranslateY(340);

        Button AcceptCorkBackedStraightedge = new Button();
        AcceptCorkBackedStraightedge.setId("NextBTN");
        AcceptCorkBackedStraightedge.setMaxSize(150, 150);
        AcceptCorkBackedStraightedge.setTranslateX(600);
        AcceptCorkBackedStraightedge.setOnAction(event ->
                LocalModel.getInstance().useCorkBackedStraightEdge(
                        DieToInsert,
                        Integer.parseInt(ToRowCorkBacked.getText()),
                        Integer.parseInt(ToColumnCorkBacked.getText())
                )
        );


        StackPane CorkBackedStraightedge = new StackPane();
        CorkBackedStraightedge.setPrefSize(1280,720);
        //----------------------------------------------------------------------------------FINE CORK-BACKED STRAIGHTEDGE----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO FLUX BRUSH----------------------------------------------------------------------------------//

        Text FluxBrushInfo = new Text("Choose a Die to Re-Roll");
        FluxBrushInfo.setStyle("-fx-fill: white;");
        FluxBrushInfo.setTranslateX(380);
        FluxBrushInfo.setTranslateY(-325);

        VBox FluxBrushPool = getDraftPool();



        Button ReRollButton = new Button("Re-Roll");
        ReRollButton.setStyle("-fx-font: 25 'Castellar';");
        ReRollButton.setTranslateX(430);
        ReRollButton.setTranslateY(100);
        ReRollButton.setVisible(true);
        ReRollButton.setOnAction(event -> {
            ReRollButton.setDisable(true);
            LocalModel.getInstance().useFluxBrush(DieToInsert);
        });


        StackPane FluxBrush = new StackPane();
        FluxBrush.setPrefSize(1280,720);


        //----------------------------------------------------------------------------------FINE FLUX BRUSH----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO GLAZING HAMMER----------------------------------------------------------------------------------//

        Text GlazingHammerInfo = new Text("Press Re-Roll to re-roll all Dice");
        GlazingHammerInfo.setStyle("-fx-fill: white;");
        GlazingHammerInfo.setTranslateX(380);
        GlazingHammerInfo.setTranslateY(-325);



        FlowPane GlazingHammerPool = new FlowPane();
        GlazingHammerPool.setTranslateX(400);
        GlazingHammerPool.setVgap(5);
        GlazingHammerPool.setHgap(5);
        GlazingHammerPool.setPadding(new Insets(4, 4, 4, 4));
        GlazingHammerPool.setAlignment(Pos.CENTER);
        GlazingHammerPool.setMaxSize(400,400);

        ToggleGroup  Dicegroup1 = new ToggleGroup();
        DiceButtons = new ArrayList<>();
        for (ClientDice dice : LocalModel.getInstance().getDrawnDice()) {
            ToggleButton Die = new ToggleButton();
            Die.setPrefSize(75, 75);
            Die.setMaxSize(75, 75);
            Die.setId("Die");
            Die.setText("DiePresente");
            Die.setToggleGroup(Dicegroup1);
            GlazingHammerPool.getChildren().addAll(Die);
            DiceButtons.add(Die);
        }
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
                            DieChoosen.setId(Integer.toString(DieToInsert.getValue()) + new ClientColor().IntToColor(DieToInsert.getColor()));
                            System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                        }
                );
            }
            Tooltip t = new Tooltip(("Color: " + new ClientColor().IntToColor(LocalColor) + "\n" + "Value: " + Integer.toString(LocalValue)));
            Tooltip.install(DiceButtons.get(i), t);
        }

        Button GlazingHammerReRollButton = new Button("Re-Roll ");
        GlazingHammerReRollButton.setStyle("-fx-font: 25 'Castellar';");
        GlazingHammerReRollButton.setTranslateX(400);
        GlazingHammerReRollButton.setTranslateY(150);
        GlazingHammerReRollButton.setVisible(true);
        GlazingHammerReRollButton.setOnAction(event -> {
            GlazingHammerReRollButton.setDisable(true);
            LocalModel.getInstance().useGlazingHammers();
        });

        StackPane GlazingHammer = new StackPane();
        GlazingHammer.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------FINE GLAZING HAMMER----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO RUNNING PLIERS----------------------------------------------------------------------------------//

        Text RunningPliersInfo = new Text("Press continue to get an Extra Turn");
        RunningPliersInfo.setStyle("-fx-fill: white;");
        RunningPliersInfo.setTranslateX(380);
        RunningPliersInfo.setTranslateY(-325);


        Button RunningPliersAccept = new Button();
        RunningPliersAccept.setId("NextBTN");
        RunningPliersAccept.setMaxSize(150, 150);
        RunningPliersAccept.setTranslateX(300);
        RunningPliersAccept.setOnAction(event -> LocalModel.getInstance().useRunningPliers());


        StackPane RunningPliers = new StackPane();
        RunningPliers.setPrefSize(1280,720);
        //----------------------------------------------------------------------------------FINE RUNNING PLIERS----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO LENS CUTTER----------------------------------------------------------------------------------//
        Text LensCutterInfo = new Text("Choose what Dice you want to swap with the Round Track");
        LensCutterInfo.setStyle("-fx-fill: white;");
        LensCutterInfo.setTranslateX(350);
        LensCutterInfo.setTranslateY(-325);


        Button LensCutterSwap = new Button();
        LensCutterSwap.setId("SwapBTN");
        LensCutterSwap.setMaxSize(150,150);
        LensCutterSwap.setTranslateX(250);
        LensCutterSwap.setOnAction(event -> LocalModel.getInstance().useLensCutter(DieToInsert2,DieToInsert));

        FlowPane LensCutterPool = new FlowPane();
        LensCutterPool.setStyle("-fx-border-radius: 5 5 5 5;"+"-fx-border-color: white;"+"-fx-border-width: 4 4 4 4");
        LensCutterPool.setTranslateX(50);
        LensCutterPool.setVgap(5);
        LensCutterPool.setHgap(5);
        LensCutterPool.setPadding(new Insets(4, 4, 4, 4));
        LensCutterPool.setAlignment(Pos.CENTER);
        LensCutterPool.setMaxSize(200,400);

        ToggleGroup  Dicegroup2 = new ToggleGroup();
        ArrayList<ToggleButton> LensCutterDiceButtons = new ArrayList<>();
        for (ClientDice dice : LocalModel.getInstance().getDrawnDice()) {
            ToggleButton Die = new ToggleButton();
            Die.setPrefSize(75, 75);
            Die.setMaxSize(75, 75);
            Die.setId("Die");
            Die.setText("DiePresente");
            Die.setToggleGroup(Dicegroup2);
            LensCutterPool.getChildren().addAll(Die);
            LensCutterDiceButtons.add(Die);
        }
        for (int i = 0; i < LensCutterDiceButtons.size(); i++) {
            int LocalValue = LocalModel.getInstance().getDrawnDice().get(i).getValue();
            int LocalColor = LocalModel.getInstance().getDrawnDice().get(i).getColor();
            if (LocalValue == 0) {
                LensCutterDiceButtons.get(i).setVisible(false);
            } else {
                LensCutterDiceButtons.get(i).setText("");
                LensCutterDiceButtons.get(i).setId(Integer.toString(LocalValue) + new ClientColor().IntToColor(LocalColor));
                LensCutterDiceButtons.get(i).setOnAction(event -> {
                            DieToInsert = new ClientDice(LocalValue, LocalColor);
                            valoredado = DieToInsert.getValue();
                            DieChoosen.setId(Integer.toString(DieToInsert.getValue()) + new ClientColor().IntToColor(DieToInsert.getColor()));
                            System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                        }
                );
            }
            Tooltip t = new Tooltip(("Color: " + new ClientColor().IntToColor(LocalColor) + "\n" + "Value: " + Integer.toString(LocalValue)));
            Tooltip.install(LensCutterDiceButtons.get(i), t);
        }

        FlowPane LensCutterRoundTrack = new FlowPane();
        LensCutterRoundTrack.setStyle("-fx-border-radius: 5 5 5 5;"+"-fx-border-color: white;"+"-fx-border-width: 4 4 4 4");
        LensCutterRoundTrack.setTranslateX(425);
        LensCutterRoundTrack.setVgap(5);
        LensCutterRoundTrack.setHgap(5);
        LensCutterRoundTrack.setPadding(new Insets(4, 4, 4, 4));
        LensCutterRoundTrack.setAlignment(Pos.CENTER);
        LensCutterRoundTrack.setMaxSize(200,400);

        ToggleGroup  Dicegroup3 = new ToggleGroup();
        ArrayList<ToggleButton>RoundTrackDiceButtons = new ArrayList<>();
        for (int i=0;i<LocalModel.getInstance().getRoundNumber();i++) {
            for (int j=0;j<LocalModel.getInstance().getRoundTrack().getDiceRemained(i).size();j++) {
                int LocalValue = LocalModel.getInstance().getRoundTrack().getDiceRemained(i).get(j).getValue();
                int LocalColor = LocalModel.getInstance().getRoundTrack().getDiceRemained(i).get(j).getColor();
                ToggleButton Die = new ToggleButton();
                Die.setPrefSize(75, 75);
                Die.setMaxSize(75, 75);
                Die.setId(Integer.toString(LocalValue) + new ClientColor().IntToColor(LocalColor));
                Die.setOnAction(event -> {
                    DieToInsert2 = new ClientDice(LocalValue, LocalColor);
                    System.out.println("Die Chosen Round Track: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                });
                if (LocalValue == 0) {
                    continue;
                    //Die.setVisible(false);
                }
                else {
                    Die.setToggleGroup(Dicegroup3);
                    LensCutterRoundTrack.getChildren().addAll(Die);
                    RoundTrackDiceButtons.add(Die);
                }
            }
        }

        StackPane LensCutter = new StackPane();
        LensCutter.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------FINE LENS CUTTER----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO TAP WHEEL----------------------------------------------------------------------------------//

        Text TapWheelInfo = new Text("Choose a Color and 2 Dice you want to Move");
        TapWheelInfo.setStyle("-fx-fill: white;");
        TapWheelInfo.setTranslateX(350);
        TapWheelInfo.setTranslateY(-325);

        GridPane TapWheelGrid = CreateAGrid(LocalModel.getInstance().getChoosenGameBoardCard());
        TapWheelGrid.setTranslateX(350);

        GridPane TapWheelDieGrid = getWindowBoardDiceBoard();
        TapWheelDieGrid.setTranslateX(350);

        Text fromRowTapWheel = new Text("From\nRow");
        fromRowTapWheel.setTranslateY(175);
        fromRowTapWheel.setTranslateX(100);
        fromRowTapWheel.setStyle("-fx-fill: white;");
        TextField FromRow1TapWheel = new TextField();
        FromRow1TapWheel.setMaxSize(100,50);
        FromRow1TapWheel.setTranslateX(100);
        FromRow1TapWheel.setTranslateY(280);
        TextField FromRow2TapWheel = new TextField();
        FromRow2TapWheel.setMaxSize(100,50);
        FromRow2TapWheel.setTranslateX(100);
        FromRow2TapWheel.setTranslateY(340);


        Text fromColumnTapWheel = new Text("From\nColumn");
        fromColumnTapWheel.setTranslateY(175);
        fromColumnTapWheel.setTranslateX(220);
        fromColumnTapWheel.setStyle("-fx-fill: white;");
        TextField FromColumn1TapWheel = new TextField();
        FromColumn1TapWheel.setMaxSize(100,50);
        FromColumn1TapWheel.setTranslateX(220);
        FromColumn1TapWheel.setTranslateY(280);
        TextField FromColumn2TapWheel = new TextField();
        FromColumn2TapWheel.setMaxSize(100,50);
        FromColumn2TapWheel.setTranslateX(220);
        FromColumn2TapWheel.setTranslateY(340);

        Text toRowTapWheel = new Text("To\nRow");
        toRowTapWheel.setTranslateY(175);
        toRowTapWheel.setTranslateX(340);
        toRowTapWheel.setStyle("-fx-fill: white;");
        TextField ToRow1TapWheel = new TextField();
        ToRow1TapWheel.setMaxSize(100,50);
        ToRow1TapWheel.setTranslateX(340);
        ToRow1TapWheel.setTranslateY(280);
        TextField ToRow2TapWheel = new TextField();
        ToRow2TapWheel.setMaxSize(100,50);
        ToRow2TapWheel.setTranslateX(340);
        ToRow2TapWheel.setTranslateY(340);

        Text toColumnTapWheel = new Text("To\nColumn");
        toColumnTapWheel.setTranslateY(175);
        toColumnTapWheel.setTranslateX(460);
        toColumnTapWheel.setStyle("-fx-fill: white;");
        TextField ToColumn1TapWheel = new TextField();
        ToColumn1TapWheel.setMaxSize(100,50);
        ToColumn1TapWheel.setTranslateX(460);
        ToColumn1TapWheel.setTranslateY(280);
        TextField ToColumn2TapWheel = new TextField();
        ToColumn2TapWheel.setMaxSize(100,50);
        ToColumn2TapWheel.setTranslateX(460);
        ToColumn2TapWheel.setTranslateY(340);

        Button AcceptTapWheel = new Button();
        AcceptTapWheel.setId("NextBTN");
        AcceptTapWheel.setMaxSize(150,150);
        AcceptTapWheel.setTranslateX(600);
        AcceptTapWheel.setOnAction(event -> LocalModel.getInstance().useTapWheel(
                ColorChosen,
                Integer.parseInt(FromRow1TapWheel.getText()),
                Integer.parseInt(FromColumn1TapWheel.getText()),
                Integer.parseInt(ToRow1TapWheel.getText()),
                Integer.parseInt(ToColumn1TapWheel.getText()),
                Integer.parseInt(FromRow2TapWheel.getText()),
                Integer.parseInt(FromColumn2TapWheel.getText()),
                Integer.parseInt(ToRow2TapWheel.getText()),
                Integer.parseInt(ToColumn2TapWheel.getText())
        )
        );

        FlowPane TapWheelRoundTrack = new FlowPane();
        TapWheelRoundTrack.setStyle("-fx-border-radius: 5 5 5 5;"+"-fx-border-color: white;"+"-fx-border-width: 4 4 4 4");
        TapWheelRoundTrack.setVgap(5);
        TapWheelRoundTrack.setHgap(5);
        TapWheelRoundTrack.setPadding(new Insets(4, 4, 4, 4));
        TapWheelRoundTrack.setAlignment(Pos.CENTER);
        TapWheelRoundTrack.setMaxSize(75,200);

        int red = 0;
        int blue = 0;
        int purple = 0;
        int yellow = 0;
        int green = 0;
        for (int i=0;i<LocalModel.getInstance().getRoundNumber();i++) {
            for (int j = 0; j < LocalModel.getInstance().getRoundTrack().getDiceRemained(i).size(); j++) {
                int LocalColor = LocalModel.getInstance().getRoundTrack().getDiceRemained(i).get(j).getColor();
                switch (LocalColor) {
                    case (ClientColor.RED):
                        red++;
                        break;
                    case (ClientColor.BLUE):
                        blue++;
                        break;
                    case (ClientColor.PURPLE):
                        purple++;
                        break;
                    case (ClientColor.YELLOW):
                        yellow++;
                        break;
                    case (ClientColor.GREEN):
                        green++;
                        break;
                }
            }
        }
        ArrayList<Integer>ColorsArray= new ArrayList<>();
        if (red>0){ColorsArray.add(ClientColor.RED);}
        if (blue>0){ColorsArray.add(ClientColor.BLUE);}
        if (purple>0){ColorsArray.add(ClientColor.PURPLE);}
        if (yellow>0){ColorsArray.add(ClientColor.YELLOW);}
        if (green>0){ColorsArray.add(ClientColor.GREEN);}

        ToggleGroup  DicegroupTapWheel = new ToggleGroup();
        for(int i=0;i<ColorsArray.size();i++) {
            int ColorIndicator = ColorsArray.get(i);
            ToggleButton ColorButton = new ToggleButton();
            ColorButton.setPrefSize(75, 75);
            ColorButton.setMaxSize(75, 75);
            ColorButton.setId(new ClientColor().IntToColor(ColorIndicator));
            ColorButton.setOnAction(event -> {
                ColorChosen = ColorIndicator;
                System.out.println("Color Chosen Round Track: " + Integer.toString(ColorIndicator));
            });
            ColorButton.setToggleGroup(DicegroupTapWheel);
            TapWheelRoundTrack.getChildren().addAll(ColorButton);

        }

        StackPane TapWheel = new StackPane();
        TapWheel.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------FINE TAP WHEEL----------------------------------------------------------------------------------//

        //ToolCardDisplayerSecond.getChildren().addAll(ToolCardD1);
        switch (Title){
            case ("Grozing Pliers"):
                GrozingPliers.getChildren().addAll(TitleInfo, GrozingPool, IncreaseValue, DecreaseValue, DieChoosen,AcceptGrozing);
                ToolCardDisplayerSecond.getChildren().addAll(GrozingPliers,ToolCardD1);
                break;
            case ("Grinding Stone"):
                GridingStone.getChildren().addAll(GrindingStoneInfo,GrindingStonePool,AcceptGridingStone,FlipButton,DieChoosen);
                ToolCardDisplayerSecond.getChildren().addAll(GridingStone,ToolCardD1);
                break;
            case ("Flux Remover"):
                FluxRemover.getChildren().addAll(FluxRemoverInfo,FluxRemoverPool,AcceptFluxRemover,IncreaseFluxRemover,DecreaseFluxRemover,DieChoosen,DiceBag,AcceptBag);
                ToolCardDisplayerSecond.getChildren().addAll(FluxRemover,ToolCardD1);
                break;
            case ("Flux Brush"):
                FluxBrush.getChildren().addAll(FluxBrushInfo,FluxBrushPool,ReRollButton,DieChoosen);
                ToolCardDisplayerSecond.getChildren().addAll(FluxBrush,ToolCardD1);
                break;
            case ("Glazing Hammer"):
                GlazingHammer.getChildren().addAll(GlazingHammerInfo,GlazingHammerPool,GlazingHammerReRollButton);
                ToolCardDisplayerSecond.getChildren().addAll(GlazingHammer,ToolCardD1);
                break;
            case ("Running Pliers"):
                RunningPliers.getChildren().addAll(RunningPliersAccept,RunningPliersInfo);
                ToolCardDisplayerSecond.getChildren().addAll(RunningPliers,ToolCardD1);
                break;
            case("Lens Cutter"):
                LensCutter.getChildren().addAll(LensCutterInfo,LensCutterPool,LensCutterRoundTrack,LensCutterSwap);
                ToolCardDisplayerSecond.getChildren().addAll(LensCutter,ToolCardD1);
                break;
            case ("Eglomise Brush"):
                EglomiseBrush.getChildren().addAll(EglomiseBrushInfo,EglomiseBrushGrid,EglomiseBrushDieGrid,AcceptEglomiseBrush,FromColumnBrush,fromColumnEglomise,fromRowEglomise,FromRowBrush,toColumnEglomise,ToColumnBrush,toRowEglomise,ToRowBrush);
                ToolCardDisplayerSecond.getChildren().addAll(EglomiseBrush,ToolCardD1);
                break;
            case ("Copper Foil Burnisher"):
                CopperFoilBurnisher.getChildren().addAll(CopperFoilBurnisherInfo,CopperFoilBurnisherGrid,CopperFoilBurnisherDieGrid,fromColumnCopperFoil,FromColumnBurnisher,fromRowCopperFoil,FromRowBurnisher,toColumnCopperFoil,ToColumnBurnisher,toRowCopperFoil,ToRowBurnisher,AcceptCopperFoilBurnisher);
                ToolCardDisplayerSecond.getChildren().addAll(CopperFoilBurnisher,ToolCardD1);
                break;
            case ("Cork-backed Straightedge"):
                CorkBackedStraightedge.getChildren().addAll(CorkBackedStraightedgeInfo,CorkBackedStraightedgeGrid,CorkBackedStraightedgeDieGrid,CorkBackedDraftPool,toColumnCorkBacked,ToColumnCorkBacked,toRowCorkBacked,ToRowCorkBacked,AcceptCorkBackedStraightedge);
                ToolCardDisplayerSecond.getChildren().addAll(CorkBackedStraightedge,ToolCardD1);
                break;
            case("Lathekin"):
                Lathekin.getChildren().addAll(LathekinInfo,LathekinGrid,LathekinDieGrid,fromColumnLathekin,fromRowLathekin,toColumnLathekin,toRowLathekin,FromRow1Lathekin,FromColumn1Lathekin,ToRow1Lathekin,ToColumn1Lathekin,FromRow2Lathekin,FromColumn2Lathekin,ToRow2Lathekin,ToColumn2Lathekin,AcceptLathekin);
                ToolCardDisplayerSecond.getChildren().addAll(Lathekin,ToolCardD1);
                break;
            case("Tap Wheel"):
                TapWheel.getChildren().addAll(TapWheelInfo,TapWheelGrid,TapWheelDieGrid,FromColumn1TapWheel,FromColumn2TapWheel,fromColumnTapWheel,FromRow1TapWheel,FromRow2TapWheel,fromRowTapWheel,ToColumn1TapWheel,ToColumn2TapWheel,toColumnTapWheel,ToRow1TapWheel,ToRow2TapWheel,toRowTapWheel,TapWheelRoundTrack,AcceptTapWheel);
                ToolCardDisplayerSecond.getChildren().addAll(TapWheel,ToolCardD1);
                break;



        }




        ToolCardDisplayerSecond.setId("ToolCardDisplayer");


        ToolCarddisplayerScene2 = new Scene(ToolCardDisplayerSecond,1280,720);
        ToolCarddisplayerScene2.setFill(Color.rgb(0, 0, 0, 0.75));
        ToolCarddisplayerScene2.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());




    }

    /**
     * This method closes the Tool Card Displayer Dialog once a Tool Card is used.
     */
    public void closeToolCardMenu(){
        Platform.runLater(()->close());
    }

    /**
     * This method creates a dialog to notify the Player that an exception was called.
     */
    public  void toolCardExceptionCatcher(){
        Platform.runLater(()-> {
            Alert ToolCardException = new Alert(Alert.AlertType.WARNING);
            ToolCardException.initStyle(StageStyle.UNDECORATED);
            ToolCardException.setTitle("Tool Card Exception");
            ToolCardException.setHeaderText(LocalModel.getInstance().returnTrownException().getMessage());
            ToolCardException.setContentText("Press OK to continue");
            ToolCardException.showAndWait();
        });
    }

    public void fluxRemoverDie(){
        Platform.runLater(()-> {
            valoredado = LocalModel.getInstance().getDiceToUseWithEffect().getValue();
            DieChoosen.setId(Integer.toString(valoredado) + new ClientColor().IntToColor(LocalModel.getInstance().getDiceToUseWithEffect().getColor()));
            DiceOutOfBagTransition.play();

        });
    }
}
