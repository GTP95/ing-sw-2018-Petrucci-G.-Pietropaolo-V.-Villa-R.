package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.ToolCards.GlazingHammer;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import java.util.Random;

public class ToolCardDisplayer extends Stage {
    Scene ToolCardDisplay;
    Scene ToolCarddisplayerScene2;
    ClientDice DieToInsert;
    int valoredado =0;
    String GrozingCommand;
    Label DieChoosen;
    ArrayList<ToggleButton> DiceButtons;
    Random randomValue;
    Random randomColor;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

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
        DieChoosen.setId("trasnparent");
        DieChoosen.setTranslateX(430);


        //----------------------------------------------------------------------------------INIZIO GROZING PLIERS----------------------------------------------------------------------------------//
        Text TitleInfo = new Text("Choose a Die to Increase or Decrease");
        TitleInfo.setStyle("-fx-fill: white;");
        TitleInfo.setTranslateX(380);
        TitleInfo.setTranslateY(-325);



        VBox GrozingPool = getDraftPool();



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



        StackPane GrozingPliers = new StackPane();
        GrozingPliers.setPrefSize(1280, 720);

        //----------------------------------------------------------------------------------FINE GROZING PLIERS----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO ENGLOMISE BRUSH----------------------------------------------------------------------------------//



        //----------------------------------------------------------------------------------FINE ENGLOMISE BRUSH----------------------------------------------------------------------------------//

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
        DiceBag.setTranslateY(300);




        Button AcceptBag = new Button();
        AcceptBag.setId("NextBTN");
        AcceptBag.setMaxSize(150, 150);
        AcceptBag.setTranslateX(150);
        AcceptBag.setOnAction(event -> {
            TranslateTransition DiceBagTransition = new TranslateTransition(Duration.millis(500),DieChoosen);
            DiceBagTransition.setFromY(720);
            DieChoosen.setVisible(true);
            DiceBagTransition.setToY(0);
            DiceBagTransition.setAutoReverse(false);
            DiceBagTransition.play();
        });

        Button AcceptFluxRemover = new Button();
        AcceptFluxRemover.setId("NextBTN");
        AcceptFluxRemover.setMaxSize(150, 150);
        AcceptFluxRemover.setTranslateX(600);

        Button IncreaseFluxRemover = new Button();
        IncreaseFluxRemover.setVisible(false);
        IncreaseFluxRemover.setMaxSize(200, 200);
        IncreaseFluxRemover.setId("IncreaseBTN");
        IncreaseFluxRemover.setTranslateY(-100);
        IncreaseFluxRemover.setTranslateX(430);
        IncreaseFluxRemover.setOnAction(event -> {
            DieChoosen.setId(Integer.toString(valoredado++) + new ClientColor().IntToColor(DieToInsert.getColor()));
            valoredado= valoredado++;
            if (valoredado==6){
                IncreaseFluxRemover.setVisible(false);
            }
            else {IncreaseFluxRemover.setVisible(true);}
            AcceptFluxRemover.setVisible(true);
        });

        Button DecreaseFluxRemover = new Button();
        DecreaseFluxRemover.setVisible(false);
        DecreaseFluxRemover.setMaxSize(200, 200);
        DecreaseFluxRemover.setId("IncreaseBTN");
        DecreaseFluxRemover.setTranslateY(-100);
        DecreaseFluxRemover.setTranslateX(430);
        DecreaseFluxRemover.setOnAction(event -> {
            DieChoosen.setId(Integer.toString(valoredado--) + new ClientColor().IntToColor(DieToInsert.getColor()));
            valoredado= valoredado--;
            if (valoredado==1){
                DecreaseFluxRemover.setVisible(false);
            }
            else {DecreaseFluxRemover.setVisible(true);}
            AcceptFluxRemover.setVisible(true);
        });

        StackPane FluxRemover = new StackPane();
        FluxRemover.setPrefSize(1280,720);


        //----------------------------------------------------------------------------------FINE FLUX REMOVER----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO FLUX BRUSH----------------------------------------------------------------------------------//

        Text FluxBrushInfo = new Text("Choose a Die to Re-Roll");
        FluxBrushInfo.setStyle("-fx-fill: white;");
        FluxBrushInfo.setTranslateX(380);
        FluxBrushInfo.setTranslateY(-325);

        VBox FluxBrushPool = getDraftPool();

        Button FluxBrushAccept = new Button();
        FluxBrushAccept.setTranslateX(600);
        FluxBrushAccept.setMaxSize(150,150);
        FluxBrushAccept.setVisible(false);
        FluxBrushAccept.setId("NextBTN");

        Button ReRollButton = new Button("Re-Roll");
        ReRollButton.setStyle("-fx-font: 25 'Castellar';");
        ReRollButton.setTranslateX(430);
        ReRollButton.setTranslateY(100);
        ReRollButton.setVisible(true);
        ReRollButton.setOnAction(event -> {
            //TODO random con roberto
            FluxBrushAccept.setVisible(true);
            ReRollButton.setDisable(true);
        });


        StackPane FluxBrush = new StackPane();
        FluxBrush.setPrefSize(1280,720);


        //----------------------------------------------------------------------------------FINE FLUX BRUSH----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO GLAZING HAMMER----------------------------------------------------------------------------------//

        Text GlazingHammerInfo = new Text("Press Re-Roll to re-roll all Dice");
        GlazingHammerInfo.setStyle("-fx-fill: white;");
        GlazingHammerInfo.setTranslateX(380);
        GlazingHammerInfo.setTranslateY(-325);

        Button GlazingHammerAccept = new Button();
        GlazingHammerAccept.setId("NextBTN");
        GlazingHammerAccept.setMaxSize(150, 150);
        GlazingHammerAccept.setTranslateX(600);


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
            //TODO random con roberto
            GlazingHammerAccept.setVisible(true);
            GlazingHammerReRollButton.setDisable(true);
        });

        StackPane GlazingHammer = new StackPane();
        GlazingHammer.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------FINE GLAZING HAMMER----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO RUNNING PLIERS----------------------------------------------------------------------------------//

        Text RunningPliersInfo = new Text("Choose Your Extra Move");
        RunningPliersInfo.setStyle("-fx-fill: white;");
        RunningPliersInfo.setTranslateX(380);
        RunningPliersInfo.setTranslateY(-325);

        VBox RunningPliersPool = getDraftPool();


        Button RunningPliersAccept = new Button();
        RunningPliersAccept.setId("NextBTN");
        RunningPliersAccept.setMaxSize(150, 150);
        RunningPliersAccept.setTranslateX(600);
        RunningPliersAccept.setVisible(false);

        if (!DieChoosen.getId().equals("transparent")){RunningPliersAccept.setVisible(true);}

        StackPane RunningPliers = new StackPane();
        RunningPliers.setPrefSize(1280,720);
        //----------------------------------------------------------------------------------FINE RUNNING PLIERS----------------------------------------------------------------------------------//

        //----------------------------------------------------------------------------------INIZIO LENS CUTTER----------------------------------------------------------------------------------//
        Text LensCutterInfo = new Text("Choose what Dice you want to swap with the Round Track");
        LensCutterInfo.setStyle("-fx-fill: white;");
        LensCutterInfo.setTranslateX(380);
        LensCutterInfo.setTranslateY(-325);

        Button LensCutterAccept = new Button();
        LensCutterAccept.setId("NextBTN");
        LensCutterAccept.setMaxSize(150,150);
        LensCutterAccept.setTranslateX(600);
        LensCutterAccept.setVisible(false);

        Button LensCutterSwap = new Button();
        LensCutterSwap.setId("SwapBTN");
        LensCutterSwap.setMaxSize(150,150);
        LensCutterSwap.setTranslateX(175);
        //TODO ACTION SWAP DICE

        FlowPane LensCutterPool = new FlowPane();
        LensCutterPool.setStyle("-fx-border-radius: 5 5 5 5;"+"-fx-border-color: white;"+"-fx-border-width: 4 4 4 4");
        LensCutterPool.setTranslateX(-25);
        LensCutterPool.setVgap(5);
        LensCutterPool.setHgap(5);
        LensCutterPool.setPadding(new Insets(4, 4, 4, 4));
        LensCutterPool.setAlignment(Pos.CENTER);
        LensCutterPool.setMaxSize(200,200);

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
        LensCutterRoundTrack.setTranslateX(350);
        LensCutterRoundTrack.setVgap(5);
        LensCutterRoundTrack.setHgap(5);
        LensCutterRoundTrack.setPadding(new Insets(4, 4, 4, 4));
        LensCutterRoundTrack.setAlignment(Pos.CENTER);
        LensCutterRoundTrack.setMaxSize(200,200);

        ToggleGroup  Dicegroup3 = new ToggleGroup();
        ArrayList<ToggleButton>RoundTrackDiceButtons = new ArrayList<>();
        for (ClientDice dice : LocalModel.getInstance().getDrawnDice()) {
            ToggleButton Die = new ToggleButton();
            Die.setPrefSize(75, 75);
            Die.setMaxSize(75, 75);
            Die.setId("Die");
            Die.setText("DiePresente");
            Die.setToggleGroup(Dicegroup3);
            LensCutterRoundTrack.getChildren().addAll(Die);
            RoundTrackDiceButtons.add(Die);
        }
        for (int i = 0; i < RoundTrackDiceButtons.size(); i++) {
            int LocalValue = LocalModel.getInstance().getDrawnDice().get(i).getValue();//TODO PRENDI I DADI DALLA ROUND TRACK
            int LocalColor = LocalModel.getInstance().getDrawnDice().get(i).getColor();
            if (LocalValue == 0) {
                RoundTrackDiceButtons.get(i).setVisible(false);
            } else {
                RoundTrackDiceButtons.get(i).setText("");
                RoundTrackDiceButtons.get(i).setId(Integer.toString(LocalValue) + new ClientColor().IntToColor(LocalColor));
                RoundTrackDiceButtons.get(i).setOnAction(event -> {
                            DieToInsert = new ClientDice(LocalValue, LocalColor);
                            valoredado = DieToInsert.getValue();
                            DieChoosen.setId(Integer.toString(DieToInsert.getValue()) + new ClientColor().IntToColor(DieToInsert.getColor()));
                            System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));
                        }
                );
            }
            Tooltip t = new Tooltip(("Color: " + new ClientColor().IntToColor(LocalColor) + "\n" + "Value: " + Integer.toString(LocalValue)));
            Tooltip.install(RoundTrackDiceButtons.get(i), t);
        }

        StackPane LensCutter = new StackPane();
        LensCutter.setPrefSize(1280,720);

        //----------------------------------------------------------------------------------FINE LENS CUTTER----------------------------------------------------------------------------------//

        //ToolCardDisplayerSecond.getChildren().addAll(ToolCardD1);
        switch (Title){
            case ("Grozing Pliers"):
                GrozingPliers.getChildren().addAll(TitleInfo, GrozingPool, IncreaseValue, DecreaseValue, DieChoosen, AcceptGrozing);
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
            case ("Flux Brusher"):
                FluxBrush.getChildren().addAll(FluxBrushAccept,FluxBrushInfo,FluxBrushPool,ReRollButton,DieChoosen);
                ToolCardDisplayerSecond.getChildren().addAll(FluxBrush,ToolCardD1);
                break;
            case ("Glazing Hammer"):
                GlazingHammer.getChildren().addAll(GlazingHammerInfo,GlazingHammerAccept,GlazingHammerPool,GlazingHammerReRollButton);
                ToolCardDisplayerSecond.getChildren().addAll(GlazingHammer,ToolCardD1);
                break;
            case ("Running Pliers"):
                RunningPliers.getChildren().addAll(RunningPliersAccept,RunningPliersInfo,RunningPliersPool,DieChoosen);
                ToolCardDisplayerSecond.getChildren().addAll(RunningPliers,ToolCardD1);
                break;
            case("Lens Cutter"):
                LensCutter.getChildren().addAll(LensCutterAccept,LensCutterInfo,LensCutterPool,LensCutterRoundTrack,LensCutterSwap);
                ToolCardDisplayerSecond.getChildren().addAll(LensCutter,ToolCardD1);
                break;
        }




        ToolCardDisplayerSecond.setId("ToolCardDisplayer");


        ToolCarddisplayerScene2 = new Scene(ToolCardDisplayerSecond,1280,720);
        ToolCarddisplayerScene2.setFill(Color.rgb(0, 0, 0, 0.75));
        ToolCarddisplayerScene2.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());




    }
}
