package Progetto_Ing_Sw.com.client;



import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.tools.JSONCreator;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ClientWindowBoard implements WindowBoardObserver{

    private int [][] Matrix; //righe - colonne
    private ArrayList<ArrayList<MatrixCell>> usedMatrix;

    //crea una matrice di vuota delle dimensioni volute
    public ClientWindowBoard(int rows, int columns) {

        Matrix = new int[rows][columns];
        ArrayList<ArrayList<MatrixCell>> usedMatrix = new ArrayList<>();

    }

    public ClientWindowBoard(ClientGameBoardCard gameBoardCard){
        Matrix=gameBoardCard.getMatrixScheme();
        usedMatrix=fromIntToArrayList(Matrix,Matrix.length,Matrix[0].length);
    }

    public ArrayList<ArrayList<MatrixCell>> getUsedMatrix(){return usedMatrix;}
    public void setUsedMatrix(ArrayList<ArrayList<MatrixCell>> usedMatrix) {this.usedMatrix = usedMatrix;}

    // crea la matrice vuota
    public int[][] buildEmptyMatrix(int rows, int columns) {// crea la matrice vuota
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Matrix[r][c] = 0;
            }
        }
        return Matrix;
    }

    //stampa matrici SOLO 4righeX5colonne
    public void printMatrix(int Matrix[][], int rows, int columns){

        for(int i=0;i<rows;i++){
            System.out.println("---------------------");
            System.out.println("| "+Matrix[i][columns-5]+" | "+Matrix[i][columns-4]+" | "+Matrix[i][columns-3]+" | "+Matrix[i][columns-2]+" | "+Matrix[i][columns-1]+" |");
        }
        System.out.println("---------------------");
    }

    //inserisce il dado nella cella richiesta, passando però tutte le celle, POCO EFFICIENTE
    public int [][] insertDiceINT(int Matrix[][], int rows, int columns, int cell, int dice){

        int [][] tmpMatrix=Matrix;
        int position = 1;
        for (int r = 0; r < rows; r++) {
            position = position + 10;
            for (int c = 0; c < columns; c++, position++)
            {
                if(position==cell){
                    if (Matrix[r][c]!=0) {
                        System.out.println("Cella occupata, salti il turno");
                        break;
                    }else {
                        Matrix[r][c] = dice;
                        tmpMatrix = Matrix;
                    }
                }
            }
            position=position-5;
        }
        return tmpMatrix;
    }



    //trasforma la matrice da interi a dadi, per poter ragionare sulle mosse
    public ArrayList<ArrayList<MatrixCell>> fromIntToArrayList(int Matrix[][], int rows, int columns){

        ArrayList<ArrayList<MatrixCell>> matrixArrays = new ArrayList<>();

        for (int r=0; r<rows; r++){
            ArrayList<MatrixCell> arrayRow = new ArrayList<>();
            for(int c=0; c<columns; c++){
                MatrixCell cell = new MatrixCell();
                switch (Matrix[r][c]) {
                    case (0): arrayRow.add(cell);
                        break;
                    case (1):
                        cell.setColor(Color.RED);
                        arrayRow.add(cell);
                        break;
                    case (2):
                        cell.setColor(Color.BLUE);
                        arrayRow.add(cell);
                        break;
                    case (3):
                        cell.setColor(Color.PURPLE);
                        arrayRow.add(cell);
                        break;
                    case (4):
                        cell.setColor(Color.YELLOW);
                        arrayRow.add(cell);
                        break;
                    case (5):
                        cell.setColor(Color.GREEN);
                        arrayRow.add(cell);
                        break;
                    case (6):
                        cell.setShade(Shade.ONE);
                        cell.setColor(Color.SHADE);
                        arrayRow.add(cell);
                        break;
                    case (7):
                        cell.setShade(Shade.TWO);
                        cell.setColor(Color.SHADE);
                        arrayRow.add(cell);
                        break;
                    case (8):
                        cell.setShade(Shade.THREE);
                        cell.setColor(Color.SHADE);
                        arrayRow.add(cell);
                        break;
                    case (9):
                        cell.setShade(Shade.FOUR);
                        cell.setColor(Color.SHADE);
                        arrayRow.add(cell);
                        break;
                    case (10):
                        cell.setShade(Shade.FIVE);
                        cell.setColor(Color.SHADE);
                        arrayRow.add(cell);
                        break;
                    case (11):
                        cell.setShade(Shade.SIX);
                        cell.setColor(Color.SHADE);
                        arrayRow.add(cell);
                        break;
                }
            }
            matrixArrays.add(arrayRow);
        }
        return matrixArrays;
    }


}

