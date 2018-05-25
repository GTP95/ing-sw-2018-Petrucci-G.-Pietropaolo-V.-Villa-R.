package Progetto_Ing_Sw.com.server;

//TODO --> assegnare un tag ai dadi posizionati n modo tale che vengano controllati solo quelli del turno corrente
//TODO --> controllare effetti delle carte e applicare modifiche alla struttura
//TODO --> sistemare raws,columns

import java.util.Scanner;

/*REGOLA PIAZZAMENTO: Quando piazzate un dado nella Vetrata:
1)   [PRIMO DADO]: Il primo dado di ogni giocatore deve essere piazzato in una casella del bordo o in un angolo.
2->) [OGNI ALTRO DADO]: deve essere adiacente a un dado precedentemente piazzato, toccando diagonalmente (diversa riga e colonna)
     oppure ortogonalmente (stessa riga o colonna).
<>   Il dado deve corrispondere alle restrizioni di colore o di valore di quella casella.
     Le caselle bianche non hanno restrizioni.
     Esempio - Una casella rossa può essere riempita solo con un dado rosso, di qualsiasi valore.
     Una casella che mostra un 3 può essere riempita solo con un dado che mostra il 3, di qualsiasi colore.
<>   I dadi non possono essere piazzati ortogonalmente adiacenti a un dado dello stesso colore o valore.
     Esempio - 2 dadi rossi o 2 dadi che mostrano 3 non possono essere piazzati adiacenti l’un l’altro.
     I Giocatori possono scegliere di non prendere dadi.

     //algoritmo che assegna alle celle il loro codice

    public int[][] cellNumbersLegend(int raws, int columns){
        int position=1;
        for (int r = 0; r < raws; r++) {
            position = position + 10;
            for (int c = 0; c < columns; c++, position++) {
                Matrix[r][c] = position;
            }
            position=position-5;
        }
        return Matrix;
    }
*/
public class WindowBoard implements WindowBoardObserver{

    private GameBoardCard gameBoardCard;
    private int [][] Matrix; //righe - colonne

    public WindowBoard(int raws, int columns) {
        Matrix = new int[raws][columns];        //crea una matrice di vuota delle dimensioni volute
    }

    public int[][] buildEmptyMatrix(int raws, int columns){// crea la matrice vuota
        for(int r=0;r<raws;r++){
            for(int c=0;c<columns;c++){
                Matrix[r][c]=0; // riempio le colonne della matrici con 0 --> da sistemare con le carte
            }
        }
        return Matrix;
    }

    public void printMatrix(int Matrix[][], int raws, int columns){

        for(int i=0;i<raws;i++){
            System.out.println("---------------------");//potrebbe creare out of bound exception con meno di 5 colonne; in tal caso da modificare
            System.out.println("| "+Matrix[i][columns-5]+" | "+Matrix[i][columns-4]+" | "+Matrix[i][columns-3]+" | "+Matrix[i][columns-2]+" | "+Matrix[i][columns-1]+" |");
        }
        System.out.println("---------------------");
    }

    public int [][] insertDice(int Matrix[][], int raws, int columns, int cell, int dice){ //inserisce il dado nella cella richiesta

        int [][] tmpMatrix=Matrix;
        int position = 1;
        for (int r = 0; r < raws; r++) {
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

    //MAIN DI PROVA CON INSERIMENTI (non si può fare l'inserimento nei test)
    public static void main(String args[]) {
        int raws=4;       //colonne e righe rimangono costanti
        int columns=5;
        int dice;
        int cell;
        Scanner keyboard= new Scanner(System.in);

        WindowBoard windowBoard = new WindowBoard(raws, columns); //costruisce la matrice con le dimensioni date da me
        int[][] testMatrix = windowBoard.buildEmptyMatrix(raws, columns);//crea una oggetto matrice di 0

        System.out.println("WELCOME TO TEST LAB. PLAY FORZA QUATTRO WITH A FRIEND!");
        System.out.println("****************************************");
        System.out.println("Inserisci il tuo numero nelle caselle osservando questo schema, vince chi ne mette per primo 4 in fila:");


            for (int i = 1; i < 21; i++) {
                if (i % 2 != 0) {
                    System.out.println("GIOCATORE 1:");
                    windowBoard.printMatrix(testMatrix,raws,columns);
                    System.out.println("POSIZIONE:");
                    cell = keyboard.nextInt();
                    System.out.println("DADO:");
                    dice = keyboard.nextInt();
                    windowBoard.insertDice(testMatrix, raws, columns, cell, dice);
                    System.out.println("****************************************");
                } else {
                    System.out.println("GIOCATORE 2:");
                    windowBoard.printMatrix(testMatrix,raws,columns);
                    System.out.println("POSIZIONE:");
                    cell = keyboard.nextInt();
                    System.out.println("DADO:");
                    dice = keyboard.nextInt();
                    windowBoard.insertDice(testMatrix, raws, columns, cell, dice);
                    System.out.println("****************************************");
                }
            }
        System.out.println("END MINIGAME");
        }
}

