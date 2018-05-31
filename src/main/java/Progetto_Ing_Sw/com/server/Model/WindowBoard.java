package Progetto_Ing_Sw.com.server.Model;

//TODO --> assegnare un tag ai dadi posizionati n modo tale che vengano controllati solo quelli del turno corrente
//TODO --> controllare effetti delle carte e applicare modifiche alla struttura
//TODO --> sistemare raws,columns
//TODO --> usare la classe color come costante (0,4 colori(nel Json) / 5,11 ==> 12 dado generico)

import Progetto_Ing_Sw.com.tools.JSONCreator;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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

     [Algoritmo che assegna alle celle il loro codice]
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

    /*MAIN DI PROVA CON INSERIMENTI (non si può fare l'inserimento nei test, da de-commentare per usarlo)
    public static void main(String args[]) {
        int rows=4;       //colonne e righe rimangono costanti
        int columns=5;
        int dice;
        int cell;
        Scanner keyboard= new Scanner(System.in);

        WindowBoard windowBoard = new WindowBoard(rows, columns); //costruisce la matrice con le dimensioni date da me
        int[][] fileMatrix = windowBoard.importFromFile(rows,columns);
        windowBoard.printMatrix(fileMatrix,rows,columns);
        int[][] testMatrix = windowBoard.buildEmptyMatrix(rows, columns);//crea una oggetto matrice di 0

        System.out.println("WELCOME TO TEST LAB. PLAY FORZA QUATTRO WITH A FRIEND!");
        System.out.println("****************************************");
        System.out.println("Inserisci il tuo numero nelle caselle osservando questo schema, vince chi ne mette per primo 4 in fila:");


            for (int i = 1; i < 21; i++) {
                if (i % 2 != 0) {
                    System.out.println("GIOCATORE 1:");
                    windowBoard.printMatrix(testMatrix,rows,columns);
                    System.out.println("POSIZIONE:");
                    cell = keyboard.nextInt();
                    System.out.println("DADO:");
                    dice = keyboard.nextInt();
                    windowBoard.insertDice(testMatrix, rows, columns, cell, dice);
                    System.out.println("****************************************");
                } else {
                    System.out.println("GIOCATORE 2:");
                    windowBoard.printMatrix(testMatrix,rows,columns);
                    System.out.println("POSIZIONE:");
                    cell = keyboard.nextInt();
                    System.out.println("DADO:");
                    dice = keyboard.nextInt();
                    windowBoard.insertDice(testMatrix, rows, columns, cell, dice);
                    System.out.println("****************************************");
                }
            }
        System.out.println("END MINIGAME");
        }
*/

public class WindowBoard implements WindowBoardObserver{

    private GameBoardCard gameBoardCard;
    private int [][] Matrix; //righe - colonne

    //crea una matrice di vuota delle dimensioni volute
    public WindowBoard(int rows, int columns) {//TODO fronte/retro
        Matrix = new int[rows][columns];
    }

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

    //importa la matrice da file Json tramite Switch/case
    public int [][] importFromFile(int rows, int columns, int cardCode) {

        switch (cardCode){
            case (1):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/KaleidoscopicDream.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (2):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Firmitas.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (3):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/FractalDrops.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (4):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/RipplesOfLight.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (5):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/LuxMundi.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (6):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/LuxAstram.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (7):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Gravitas.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (8):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/WaterOfLife.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (9):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/SunCatcher.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (10):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/ShadowThief.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (11):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/AuroraeMagnificus.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (12):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/AuroraSagradis.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (13):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/SymphonyOfLight.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (14):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Virtus.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (15):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Firelight.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (16):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/SunsGlory.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (17):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Batllo.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (18):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Bellesguard.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (19):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/FulgorDelCielo.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (20):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/LuzCelestial.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (21):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/ChromaticSplendor.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (22):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Comitas.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (23):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/ViaLux.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (24):try { Matrix= JSONCreator.parseMatrixFieldFromFile("Resources/Cards/GameBoardCards/Industria.json", "matrixScheme",rows,columns);}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
        }
        return Matrix;
    }

    //trasforma la matrice da interi a dadi, per poter ragionare sulle mosse
    public ArrayList<ArrayList<MatrixCell>> fromIntToDice(int Matrix[][], int rows, int columns){

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
                        arrayRow.add(cell);
                        break;
                    case (7):
                        cell.setShade(Shade.TWO);
                        arrayRow.add(cell);
                        break;
                    case (8):
                        cell.setShade(Shade.THREE);
                        arrayRow.add(cell);
                        break;
                    case (9):
                        cell.setShade(Shade.FOUR);
                        arrayRow.add(cell);
                        break;
                    case (10):
                        cell.setShade(Shade.FIVE);
                        arrayRow.add(cell);
                        break;
                    case (11):
                        cell.setShade(Shade.SIX);
                        arrayRow.add(cell);
                        break;
                }
            }
            matrixArrays.add(arrayRow);
        }
        return matrixArrays;
    }

    //******************************REGOLE DI INSERIMENTO*************************************************************//

    //verifica che la matrice non sia vuota, restituendo TRUE in tal caso
    public boolean matrixNotEmpty(ArrayList<ArrayList<MatrixCell>> Matrix){
        boolean cellState=false;
        for(int r=0;r<Matrix.size();r++){
            for (int c=0;c<Matrix.get(r).size();c++){
                if(Matrix.get(r).get(c).isUsed()!=false){
                    cellState=true;
                    break;}
            }
        }
        return cellState;
    }

    //setta le caselle che sonp sui bordi, restituiendo la matrice settata correttaement
    public ArrayList<ArrayList<MatrixCell>> setBorders(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column){
        int rows=Matrix.size(); //4
        int columns=Matrix.get(row).size();//5

        //CONTROLLO BORDO SUP******************************************************************************
        if(row==0){//controllo sul bordo superiore
            for (int c=0;c<columns;c++){
                    if (column==c){
                        Matrix.get(row).get(c).setOnBorder(true);
                    }
            }
        }else if(row==rows-1){//controllo sul bordo inferiore
            for (int c=0;c<columns;c++){
                if(column==c){
                    Matrix.get(row).get(c).setOnBorder(true);
                }
            }
        }
        //CONTROLLO BORDO INF******************************************************************************
        if(column==0){
            for(int r=0;r<rows;r++){
                    Matrix.get(r).get(column).setOnBorder(true);
                }
        }else if(column==columns-1){
            for(int r=0;r<rows;r++){
                Matrix.get(r).get(column).setOnBorder(true);
            }
        }
        return Matrix;
    }

    //metodo di TEST per verificare che le caselle sono sui bordi
    public void areOnBorders(ArrayList<ArrayList<MatrixCell>> Matrix){
        for(int r=0;r<Matrix.size();r++){
            for (int c=0;c<Matrix.get(r).size();c++){
                if(Matrix.get(r).get(c).isOnBorder()==true){
                    System.out.println("CELL ["+(r+1)+"]["+(c+1)+"] is on border");
                }
            }
        }
    }

    public ArrayList<ArrayList<MatrixCell>> insertDiceARRLIST(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column){
        //da completare
        return Matrix;
    }

}

