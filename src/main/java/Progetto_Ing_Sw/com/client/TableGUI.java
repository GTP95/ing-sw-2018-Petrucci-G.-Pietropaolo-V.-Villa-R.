package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.Dice;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;


public class TableGUI extends Stage{
    ClientGameBoardCard ChoosenGameBoardCard;
    Scene GameplayScene;
    Label ToolCard1Label, ToolCard2Label, ToolCard3Label, ToolCardColor1, ToolCardColor2, ToolCardColor3, PublicObjectiveCard1Label, PublicObjectiveCard2Label, PublicObjectiveCard3Label,Tokens, PrivateObjectiveColor, CurrentPlayer,DiceCover;
    Button ToolCard1BTN, ToolCard2BTN, ToolCard3BTN,PublicObjectiveCard1BTN, PublicObjectiveCard2BTN,PublicObjectiveCard3BTN;
    Text PublicObjectiveCard1Description,PublicObjectiveCard2Description,PublicObjectiveCard3Description,PublicObjectiveCard1Value,PublicObjectiveCard2Value,PublicObjectiveCard3Value;
    ArrayList<Button> OtherPlayersList;
    ArrayList<ToggleButton> DiceButtons;
    ArrayList<Pane> GridBlocks;
    ArrayList<ClientPlayer> OtherPlayersNames;
    ClientDice DieToInsert;
    int Xindex=0, Yindex=0,NumPlayers;
    GridPane griglia;
    static final Image windowIcon = new Image("Progetto_Ing_Sw/com/client/GUI/GameIcon.png");

    TableGUI(ClientGameBoardCard gameBoardCard) {
        this.setTitle("Sagrada Game");
        this.setWidth(1280);
        this.setHeight(720);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.getIcons().add(windowIcon);

        ChoosenGameBoardCard = gameBoardCard;

        LocalModel.getInstance().registerAsObserver(this);

        //INIZIO Gameplay Scene
        int rows = 4;
        int columns = 5;
        NumPlayers = LocalModel.getInstance().getClientPlayerArrayList().size();

        //GridPane per la griglia 5x4
        griglia = new GridPane();
        griglia.setTranslateY(-20);
        griglia.setAlignment(Pos.CENTER);
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(75);
            griglia.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(75);
            griglia.getRowConstraints().add(row);
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

                    griglia.add(block, c, r);

                }
            }


            //Stampa due interi che indicano su che casella sto cliccando
            griglia.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getX() < 71 && e.getX() > 4) {
                    Xindex = 1;
                    System.out.println("Colonna: " + Xindex);
                }


                if (e.getX() < 146 && e.getX() > 79) {
                    Xindex = 2;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 221 && e.getX() > 154) {
                    Xindex = 3;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 296 && e.getX() > 229) {
                    Xindex = 4;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getX() < 371 && e.getX() > 304) {
                    Xindex = 5;
                    System.out.println("Colonna: " + Xindex);
                }

                if (e.getY() > 23 && e.getY() < 91) {
                    Yindex = 1;
                    System.out.println("Riga: " + Yindex);
                }

                if (e.getY() > 98 && e.getY() < 166) {
                    Yindex = 2;
                    System.out.println("Riga: " + Yindex);
                }

                if (e.getY() > 173 && e.getY() < 241) {
                    Yindex = 3;
                    System.out.println("Riga: " + Yindex);
                }

                if (e.getY() > 248 && e.getY() < 316) {
                    Yindex = 4;
                    System.out.println("Riga: " + Yindex);
                }


            });


            //HBox che contiene le informazioni sulla carta
            HBox WindowInfo= new HBox(60);WindowInfo.setId("WindowInfo");WindowInfo.setMaxHeight(45);
            Text WindowTitle = new Text(gameBoardCard.getTitle());WindowTitle.setFill(Paint.valueOf("white"));
            WindowInfo.setTranslateY(150);WindowInfo.setAlignment(Pos.CENTER);
            WindowInfo.getChildren().addAll(WindowTitle);

            //StackPane che fa da cornice alla griglia
            StackPane WindowBoard = new StackPane();WindowBoard.setId("WindowBoard");WindowBoard.setMaxSize(400,360);
            WindowBoard.getChildren().addAll(griglia,WindowInfo);

            //Player Stats
            Tokens = new Label("•");
            Tokens.setText(String.join("", Collections.nCopies(gameBoardCard.getDifficulty(), "  •\n")));
            Tokens.setId("Tokens");
            Tokens.setTranslateX(-220);
            Tokens.setMinWidth(70);

            PrivateObjectiveColor = new Label();
            PrivateObjectiveColor.setId(new ClientColor().IntToColor(LocalModel.getInstance().getPrivateObjectiveCard().getColor()));
            PrivateObjectiveColor.setMinSize(40,40);
            PrivateObjectiveColor.setStyle("-fx-border-color: black;"+"-fx-border-width: 2 2 2 2;" + "-fx-border-radius: 2.5 2.5 2.5 2.5;" + "-fx-background-radius: 3.5 3.5 3.5 3.5;"+ "-fx-background-size: 0.99");
            PrivateObjectiveColor.setTranslateY(100);
            PrivateObjectiveColor.setTranslateX(230);
            PrivateObjectiveColor.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                PrivateObjectiveColor.setStyle("-fx-border-color: black;"
                        + "-fx-background-color: grey;"
                        + "-fx-border-width: 2 2 2 2;"
                        + "-fx-border-radius: 2.5 2.5 2.5 2.5;"
                        + "-fx-background-radius: 3.5 3.5 3.5 3.5;"
                        + "-fx-background-size: 0.99"
                );
            });
            //Tokens.setTranslateY(180);





            //INIZIO MENU TOOL CARD

            //Contenuto del menu ToolCard
            Label ToolCardMenuTitle = new Label("            Tool Cards");
            ToolCardMenuTitle.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: black;" + "-fx-font: 25 \"Centaur\";");

            ToolCard1Label= new Label();ToolCard1Label.setMinSize(268,131);ToolCard1Label.setId("ToolCardLabel");
            ToolCard2Label= new Label();ToolCard2Label.setMinSize(268,131);ToolCard2Label.setId("ToolCardLabel");
            ToolCard3Label= new Label();ToolCard3Label.setMinSize(268,131);ToolCard3Label.setId("ToolCardLabel");

            //Bottoni che riferiscono alle Tool Cards
            ToolCard1BTN = new Button("Tool Card1");ToolCard1BTN.setTranslateY(-40);ToolCard1BTN.setTranslateX(35);
            ToolCard1BTN.setMaxWidth(180);
            ToolCard1BTN.setMinWidth(180);
            ToolCard1BTN.setPrefWidth(180);
            ToolCard1BTN.setId("CardBTN");
            ToolCard1BTN.setOnAction(event -> {
                ToolCard1BTN.setDisable(true);
                ToolCardDisplayer ToolCard1Stage = new ToolCardDisplayer(
                        LocalModel.getInstance().getDrawnToolCards().get(0).getTitle(),
                        LocalModel.getInstance().getDrawnToolCards().get(0).getID(),
                        LocalModel.getInstance().getDrawnToolCards().get(0).getDescription(),
                        LocalModel.getInstance().getDrawnToolCards().get(0).getInfo(),
                        LocalModel.getInstance().getDrawnToolCards().get(0).getColor(),false);
                ToolCard1Stage.showAndWait();
                ToolCard1BTN.setDisable(false);
                    });


            ToolCard2BTN = new Button("Tool Card2");ToolCard2BTN.setTranslateY(-40);ToolCard2BTN.setTranslateX(35);
            ToolCard2BTN.setMaxWidth(180);
            ToolCard2BTN.setId("CardBTN");

            ToolCard2BTN.setOnAction(event -> {
                ToolCard2BTN.setDisable(true);
                ToolCardDisplayer ToolCard2Stage = new ToolCardDisplayer(
                        LocalModel.getInstance().getDrawnToolCards().get(1).getTitle(),
                        LocalModel.getInstance().getDrawnToolCards().get(1).getID(),
                        LocalModel.getInstance().getDrawnToolCards().get(1).getDescription(),
                        LocalModel.getInstance().getDrawnToolCards().get(1).getInfo(),
                        LocalModel.getInstance().getDrawnToolCards().get(1).getColor(),false);
                ToolCard2Stage.showAndWait();
                ToolCard2BTN.setDisable(false);
            });


            ToolCard3BTN = new Button("Tool Card3");ToolCard3BTN.setTranslateY(-40);ToolCard3BTN.setTranslateX(35);
            ToolCard3BTN.setMaxWidth(180);
            ToolCard3BTN.setId("CardBTN");
            ToolCard3BTN.setOnAction(event -> {
                ToolCard3BTN.setDisable(true);
                ToolCardDisplayer ToolCard3Stage = new ToolCardDisplayer(
                        LocalModel.getInstance().getDrawnToolCards().get(2).getTitle(),
                        LocalModel.getInstance().getDrawnToolCards().get(2).getID(),
                        LocalModel.getInstance().getDrawnToolCards().get(2).getDescription(),
                        LocalModel.getInstance().getDrawnToolCards().get(2).getInfo(),
                        LocalModel.getInstance().getDrawnToolCards().get(2).getColor(),false);
                ToolCard3Stage.showAndWait();
                ToolCard3BTN.setDisable(false);
            });

            //Colori
            ToolCardColor1 = new Label();ToolCardColor1.setMinSize(50,50);ToolCardColor1.setId("red");ToolCardColor1.setTranslateX(-85);ToolCardColor1.setTranslateY(-20);
            ToolCardColor2 = new Label();ToolCardColor2.setMinSize(50,50);ToolCardColor2.setId("yellow");ToolCardColor2.setTranslateX(-85);ToolCardColor2.setTranslateY(-20);
            ToolCardColor3 = new Label();ToolCardColor3.setMinSize(50,50);ToolCardColor3.setId("purple");ToolCardColor3.setTranslateX(-85);ToolCardColor3.setTranslateY(-20);


            //StackPane Bottoni su Label
            StackPane ToolCard1 = new StackPane();
            ToolCard1.getChildren().addAll(ToolCardColor1,ToolCard1Label,ToolCard1BTN);
            ToolCard1.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover1 = new TranslateTransition(Duration.millis(500), ToolCard1);
                Hover1.setFromY(0);
                Hover1.setToY(-10);
                Hover1.setAutoReverse(true);
                Hover1.play();
            });

            ToolCard1.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard1);
                Hover2.setFromY(-10);
                Hover2.setToY(0);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            StackPane ToolCard2 = new StackPane();ToolCard2.setTranslateY(75);
            ToolCard2.getChildren().addAll(ToolCardColor2,ToolCard2Label,ToolCard2BTN);
            ToolCard2.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard2);
                Hover2.setFromY(75);
                Hover2.setToY(65);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            ToolCard2.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard2);
                Hover2.setFromY(65);
                Hover2.setToY(75);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            StackPane ToolCard3 = new StackPane();ToolCard3.setTranslateY(150);
            ToolCard3.getChildren().addAll(ToolCardColor3,ToolCard3Label,ToolCard3BTN);
            ToolCard3.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard3);
                Hover2.setFromY(150);
                Hover2.setToY(140);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            ToolCard3.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), ToolCard3);
                Hover2.setFromY(140);
                Hover2.setToY(150);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });


            //StackPane per contenere le carte
            StackPane ToolCardList = new StackPane();
            ToolCardList.setMaxHeight(280);
            ToolCardList.setPrefWidth(240);
            ToolCardList.setTranslateY(-100);
            ToolCardList.getChildren().addAll(ToolCard1, ToolCard2, ToolCard3);

            //VBox del Menu Tool Card
            VBox ToolCardMenu = new VBox(100);
            ToolCardMenu.setId("ToolCardMenu");
            ToolCardMenu.setMaxHeight(280);
            ToolCardMenu.setMaxWidth(240);
            ToolCardMenu.getChildren().addAll(ToolCardMenuTitle, ToolCardList);
            //FINE MENU TOOL CARD




            //INIZIO  PUBLIC OBJECTIVE MENU

            //Bottoni che riferiscono alle Public Objective Cards
            PublicObjectiveCard1BTN = new Button("Public Objective 1");PublicObjectiveCard1BTN.setTranslateX(25);PublicObjectiveCard1BTN.setTranslateY(8);
            PublicObjectiveCard1BTN.setId("CardBTN");
            PublicObjectiveCard1BTN.setMaxWidth(170);
            PublicObjectiveCard1BTN.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    PublicObjectiveCard1BTN.setDisable(true);
                    PublicObjectiveCardDisplayer PublicDisplay1 = new PublicObjectiveCardDisplayer(
                            LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getTitle(),
                            LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getDescription(),
                            LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getVictoryPoints());
                    PublicDisplay1.showAndWait();
                    PublicObjectiveCard1BTN.setDisable(false);
                }
            });


            PublicObjectiveCard2BTN = new Button("Public Objective 2");PublicObjectiveCard2BTN.setTranslateX(25);PublicObjectiveCard2BTN.setTranslateY(8);
            PublicObjectiveCard2BTN.setId("CardBTN");
            PublicObjectiveCard2BTN.setMaxWidth(170);
            PublicObjectiveCard2BTN.setOnAction(event ->  {
                    PublicObjectiveCardDisplayer PublicDisplay2 = new PublicObjectiveCardDisplayer(
                            LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getTitle(),
                            LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getDescription(),
                            LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getVictoryPoints());
                    PublicDisplay2.showAndWait();
                    PublicObjectiveCard2BTN.setDisable(false);
                });


            PublicObjectiveCard3BTN = new Button("Public Objective 3");PublicObjectiveCard3BTN.setTranslateX(25);PublicObjectiveCard3BTN.setTranslateY(8);
            PublicObjectiveCard3BTN.setId("CardBTN");
            PublicObjectiveCard3BTN.setMaxWidth(170);
            PublicObjectiveCard3BTN.setOnAction(event -> {
                PublicObjectiveCard3BTN.setDisable(true);
                PublicObjectiveCardDisplayer PublicDisplay3 = new PublicObjectiveCardDisplayer(
                        LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getTitle(),
                        LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getDescription(),
                        LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getVictoryPoints());
                PublicDisplay3.showAndWait();
                PublicObjectiveCard3BTN.setDisable(false);
            });

            //Label delle Public Objective Cards
            PublicObjectiveCard1Label = new Label();PublicObjectiveCard1Label.setMinSize(268,131);PublicObjectiveCard1Label.setId("PublicObjectiveCardLabel");
            PublicObjectiveCard2Label = new Label();PublicObjectiveCard2Label.setMinSize(268,131);PublicObjectiveCard2Label.setId("PublicObjectiveCardLabel");
            PublicObjectiveCard3Label = new Label();PublicObjectiveCard3Label.setMinSize(268,131);PublicObjectiveCard3Label.setId("PublicObjectiveCardLabel");


            //Text per le descrizioni
            PublicObjectiveCard1Description = new Text("Description");
            PublicObjectiveCard1Description.setStyle(" -fx-font: 12 'Centaur';");
            PublicObjectiveCard1Description.setTranslateY(30);
            PublicObjectiveCard1Description.setTranslateX(10);
            PublicObjectiveCard1Description.setTextAlignment(TextAlignment.CENTER);

            PublicObjectiveCard2Description = new Text("Description");
            PublicObjectiveCard2Description.setStyle(" -fx-font: 12 'Centaur';");
            PublicObjectiveCard2Description.setTranslateY(30);
            PublicObjectiveCard2Description.setTranslateX(10);
            PublicObjectiveCard2Description.setTextAlignment(TextAlignment.CENTER);

            PublicObjectiveCard3Description = new Text("Description");
            PublicObjectiveCard3Description.setStyle(" -fx-font: 12 'Centaur';");
            PublicObjectiveCard3Description.setTranslateY(30);
            PublicObjectiveCard3Description.setTranslateX(10);
            PublicObjectiveCard3Description.setTextAlignment(TextAlignment.CENTER);


            PublicObjectiveCard1Value= new Text("4");
            PublicObjectiveCard1Value.setStyle("-fx-font: 50 'Centaur';" + "-fx-font-weight: bold;");
            PublicObjectiveCard1Value.setTranslateX(-86);
            PublicObjectiveCard1Value.setTranslateY(21);

            PublicObjectiveCard2Value= new Text("X");
            PublicObjectiveCard2Value.setStyle("-fx-font: 50 'Centaur';" + "-fx-font-weight: bold;");
            PublicObjectiveCard2Value.setTranslateX(-86);
            PublicObjectiveCard2Value.setTranslateY(21);

            PublicObjectiveCard3Value= new Text("2");
            PublicObjectiveCard3Value.setStyle("-fx-font: 50 'Centaur';" + "-fx-font-weight: bold;");
            PublicObjectiveCard3Value.setTranslateX(-86);
            PublicObjectiveCard3Value.setTranslateY(21);

            //StackPane per i vari menù

            StackPane PublicObjectiveCard1= new StackPane();PublicObjectiveCard1.setTranslateY(-150);PublicObjectiveCard1.setMaxSize(268,131);
            PublicObjectiveCard1.getChildren().addAll(PublicObjectiveCard1Label,PublicObjectiveCard1Description,PublicObjectiveCard1Value,PublicObjectiveCard1BTN);
            PublicObjectiveCard1.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), PublicObjectiveCard1);
                Hover2.setFromY(-150);
                Hover2.setToY(-140);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            PublicObjectiveCard1.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), PublicObjectiveCard1);
                Hover2.setFromY(-140);
                Hover2.setToY(-150);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            StackPane PublicObjectiveCard2= new StackPane();PublicObjectiveCard2.setTranslateY(-75);PublicObjectiveCard2.setMaxSize(268,131);
            PublicObjectiveCard2.getChildren().addAll(PublicObjectiveCard2Label,PublicObjectiveCard2Description,PublicObjectiveCard2Value,PublicObjectiveCard2BTN);
            PublicObjectiveCard2.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), PublicObjectiveCard2);
                Hover2.setFromY(-75);
                Hover2.setToY(-65);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            PublicObjectiveCard2.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), PublicObjectiveCard2);
                Hover2.setFromY(-65);
                Hover2.setToY(-75);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            StackPane PublicObjectiveCard3= new StackPane();PublicObjectiveCard3.setTranslateY(0);PublicObjectiveCard3.setMaxSize(268,131);
            PublicObjectiveCard3.getChildren().addAll(PublicObjectiveCard3Label,PublicObjectiveCard3Description,PublicObjectiveCard3Value,PublicObjectiveCard3BTN);
            PublicObjectiveCard3.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), PublicObjectiveCard3);
                Hover2.setFromY(0);
                Hover2.setToY(10);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });

            PublicObjectiveCard3.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                TranslateTransition Hover2 = new TranslateTransition(Duration.millis(500), PublicObjectiveCard3);
                Hover2.setFromY(10);
                Hover2.setToY(0);
                Hover2.setAutoReverse(true);
                Hover2.play();
            });



            //StackPane per il menu Public Objective Cards
            StackPane PublicObjectiveCardMenu = new StackPane();
            PublicObjectiveCardMenu.setTranslateY(30);
            PublicObjectiveCardMenu.setTranslateX(-15);
            PublicObjectiveCardMenu.setMaxSize(268,300);
            PublicObjectiveCardMenu.getChildren().addAll(PublicObjectiveCard3, PublicObjectiveCard2, PublicObjectiveCard1);

            //FINE PUBLIC OBJECTIVE MENU


        //INIZIO Draft Area

        FlowPane DraftPool = new FlowPane();
        DraftPool.setId("DraftPool");
        DraftPool.setTranslateX(-10);
        DraftPool.setTranslateY(-10);
        DraftPool.setHgap(5);
        DraftPool.setVgap(5);
        DraftPool.setPadding(new Insets(4,4,4,4));
        DraftPool.setAlignment(Pos.CENTER);
        DraftPool.setMaxSize(200,400);

        DiceCover = new Label();
        DiceCover.setId("grey");
        DiceCover.setMaxSize(DraftPool.getWidth(),DraftPool.getHeight());
        DiceCover.setTranslateX(-10);
        //DiceCover.setTranslateY(-10);
        DiceCover.setVisible(true);




        DiceButtons= new ArrayList<>();
        for (ClientDice dice : LocalModel.getInstance().getDrawnDice()){
            ToggleButton Die = new ToggleButton();
            Die.setPrefSize(75,75);
            Die.setMaxSize(75,75);
            Die.setId("Die");
            Die.setText("DiePresente");
            DraftPool.getChildren().addAll(Die);
            DiceButtons.add(Die);
        }




        //FINE Draft Area

        //INIZIO Round Track

        Button RoundTrack = new Button();RoundTrack.setId("Round1");
        RoundTrack.setMinSize(150,150);
        RoundTrack.setTranslateY(-10);
        RoundTrack.setTranslateX(10);
        //TODO Observer del round

        //FINE Round Track

        //HBox Tabs per gli altri giocatori
        FlowPane OtherPlayerBox = new FlowPane();
        OtherPlayerBox.setTranslateY(10);
        OtherPlayerBox.setHgap(10);
        //OtherPlayerBox.setStyle("-fx-background-color: black;");
        OtherPlayerBox.setMaxSize(600,50);

        //Bottoni altri giocatori
        OtherPlayersNames = (ArrayList<ClientPlayer>) LocalModel.getInstance().getClientPlayerArrayList().clone();
        for (int i=0; i<OtherPlayersNames.size();i++){
            if (OtherPlayersNames.get(i).getName().equals(ClientSettings.getInstance().getUsername())){
                OtherPlayersNames.remove(i);
                break;
            }
        }

        OtherPlayersList = new ArrayList<>();
        for (int i = 0; i < (NumPlayers - 1); i++) {
            Button OtherPlayer = new Button(OtherPlayersNames.get(i).getName());
            OtherPlayer.setId("DefaultButton");
            OtherPlayer.setOnAction(event ->{
                OtherPlayerBoardView OtherBoard = new OtherPlayerBoardView(gameBoardCard);
                OtherBoard.showAndWait();
            });
            OtherPlayerBox.getChildren().addAll(OtherPlayer);
            OtherPlayersList.add(OtherPlayer);
        }




        //Il giocatore in questione
        CurrentPlayer = new Label(ClientSettings.getInstance().getUsername());
        CurrentPlayer.setId("DefaultButton");
        CurrentPlayer.setTranslateY(-10);
        //CurrentPlayer.setMinSize(200,50);

        //Move Button
        Button Move = new Button("     Move");
        Move.setId("DefaultButton");
        Move.setTranslateX(220);
        Move.setTranslateY(-150);
        Move.setOnAction(event -> {
            LocalModel.getInstance().insertDice(DieToInsert,Yindex,Xindex);
        });



            //BorderPane per contenere tutti gli altri
            StackPane GameplayArea = new StackPane();
            GameplayArea.setId("GamemodeSelectionScreen");
            GameplayArea.setAlignment(WindowBoard,Pos.CENTER);
            GameplayArea.setAlignment(Tokens,Pos.CENTER);
            GameplayArea.setAlignment(Move,Pos.CENTER);
            GameplayArea.setAlignment(PrivateObjectiveColor,Pos.CENTER);
            GameplayArea.setAlignment(OtherPlayerBox,Pos.BOTTOM_CENTER);
            GameplayArea.setAlignment(RoundTrack,Pos.BOTTOM_LEFT);
            GameplayArea.setAlignment(ToolCardMenu,Pos.BOTTOM_RIGHT);
            GameplayArea.setAlignment(DraftPool,Pos.TOP_LEFT);
            GameplayArea.setAlignment(DiceCover,Pos.TOP_LEFT);
            GameplayArea.setAlignment(PublicObjectiveCardMenu,Pos.TOP_RIGHT);
            GameplayArea.setAlignment(CurrentPlayer,Pos.TOP_CENTER);
            GameplayArea.getChildren().addAll(OtherPlayerBox,CurrentPlayer,Tokens,Move,WindowBoard,PrivateObjectiveColor,PublicObjectiveCardMenu,ToolCardMenu,DraftPool,DiceCover,RoundTrack);



            GameplayScene = new Scene(GameplayArea, 1280, 720);
            GameplayScene.getStylesheets().addAll(this.getClass().getResource("form.css").toExternalForm());

            //FINE Gameplay Scene


            this.setScene(GameplayScene);
            this.show();
        }

        public void updateTable(){
            updatePublicObjectiveCards();
            updateToolCards();
            updateDice();
        }

        public void updateToolCards(){
            Platform.runLater(()->{
                ToolCard1BTN.setText(LocalModel.getInstance().getDrawnToolCards().get(0).getTitle());
                ToolCardColor1.setId(LocalModel.getInstance().getDrawnToolCards().get(0).getColor());

                ToolCard2BTN.setText(LocalModel.getInstance().getDrawnToolCards().get(1).getTitle());
                ToolCardColor2.setId(LocalModel.getInstance().getDrawnToolCards().get(1).getColor());

                ToolCard3BTN.setText(LocalModel.getInstance().getDrawnToolCards().get(2).getTitle());
                ToolCardColor3.setId(LocalModel.getInstance().getDrawnToolCards().get(2).getColor());
            });
        }

        public void updatePublicObjectiveCards(){
            Platform.runLater(()->{
                PublicObjectiveCard1BTN.setText(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getTitle());
                PublicObjectiveCard1Value.setText(Integer.toString(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getVictoryPoints()));
                PublicObjectiveCard1Description.setText(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(0).getDescription());

                PublicObjectiveCard2BTN.setText(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getTitle());
                PublicObjectiveCard2Value.setText(Integer.toString(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getVictoryPoints()));
                PublicObjectiveCard2Description.setText(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(1).getDescription());

                PublicObjectiveCard3BTN.setText(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getTitle());
                PublicObjectiveCard3Value.setText(Integer.toString(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getVictoryPoints()));
                PublicObjectiveCard3Description.setText(LocalModel.getInstance().getDrawnPublicObjectiveCards().get(2).getDescription());
            });
        }



        public void updateDice(){
            System.err.println("--------------------------------------UPDATE DICE-----------------------------------------------------");
            Platform.runLater(()->{
                for (int i=0; i<DiceButtons.size(); i++ ){
                    int LocalValue = LocalModel.getInstance().getDrawnDice().get(i).getValue();
                    int LocalColor = LocalModel.getInstance().getDrawnDice().get(i).getColor();
                    if (LocalValue==0){
                        DiceButtons.get(i).setVisible(false);
                    }
                    else {
                        DiceButtons.get(i).setText("");
                        DiceButtons.get(i).setId(Integer.toString(LocalValue) + new ClientColor().IntToColor(LocalColor));
                        DiceButtons.get(i).setOnAction(event -> {
                                    DieToInsert = new ClientDice(LocalValue, LocalColor);
                                    System.out.println("Die choosen: " + Integer.toString(LocalValue) + "," + Integer.toString(LocalColor));

                                }
                        );
                    }
                    Tooltip t= new Tooltip(("Color: "+new ClientColor().IntToColor(LocalColor)+"\n"+"Value: "+Integer.toString(LocalValue)));
                    Tooltip.install(DiceButtons.get(i),t);
                }
            });
        }

        public void insertion(){
            LocalModel.getInstance().getWindowBoard().printMatrixArrayList(LocalModel.getInstance().getWindowBoard().getUsedMatrix());

        }

        public void isYourTurn(){
            System.err.println("------------------------------------------TUO TURNO----------------------------------------------------------");
            Alert itsYourTurn = new Alert(Alert.AlertType.INFORMATION);
            itsYourTurn.initStyle(StageStyle.UNDECORATED);
            itsYourTurn.setHeaderText("It's Your Turn boy!");
            itsYourTurn.showAndWait()
            DiceCover.setVisible(false);
            CurrentPlayer.setId("DefaultButtonActivated");
        }

        public void isNotYourTurn(){
            DiceCover.setVisible(true);
            CurrentPlayer.setId("DefaultButton");
        }
}
