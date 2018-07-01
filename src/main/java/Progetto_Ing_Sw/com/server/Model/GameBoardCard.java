package Progetto_Ing_Sw.com.server.Model;

/**
 * <h1>GameBoardCard</h1>
 * The class GameBoardCard implements the card that the player have to choose
 * when the game starts. This card represents the window-texture that he has to
 * follow during the match (this feature is build with a integer-matrix)
 * The card and it's otherSide have a code, used both to find the card in the
 * deck
 *
 *@author Roberto Villa
 */
public class GameBoardCard extends Card {

    private String title;
    private int gameBoardCode;
    private int difficulty;
    private String otherSide;
    private int otherSideCode;
    private int [][] matrixScheme;


 /*   public GameBoardCard(String title, int gameBoardCode, int difficulty, String otherSide, int otherSideCode) {
        this.title = title;
        this.gameBoardCode = gameBoardCode;
        this.difficulty = difficulty;
        this.otherSide = otherSide;
        this.otherSideCode = otherSideCode;
    }*/


    public String getTitle() {return title;}
    public int getGameBoardCode() {return gameBoardCode;}
    public int getDifficulty() {return difficulty;}
    public String getOtherSide() {return otherSide;}
    public int getOtherSideCode() {return otherSideCode;}
    public int[][] getMatrixScheme() {return matrixScheme;}
}