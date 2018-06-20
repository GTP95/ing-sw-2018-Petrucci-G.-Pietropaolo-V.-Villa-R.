package Progetto_Ing_Sw.com.server.Model;

public class GameBoardCard extends Card {

    private String title;
    private int gameBoardCode;
    private int difficulty;
    private String otherSide;
    private int otherSideCode;
    private int [][] matrixScheme;


    public GameBoardCard(String title, int gameBoardCode, int difficulty, String otherSide, int otherSideCode) {
        this.title = title;
        this.gameBoardCode = gameBoardCode;
        this.difficulty = difficulty;
        this.otherSide = otherSide;
        this.otherSideCode = otherSideCode;
    }
    

    public String getTitle() {return title;}
    public int getGameBoardCode() {return gameBoardCode;}
    public int getDifficulty() {return difficulty;}
    public String getOtherSide() {return otherSide;}
    public int getOtherSideCode() {return otherSideCode;}
    public int[][] getMatrixScheme() {return matrixScheme;}
}