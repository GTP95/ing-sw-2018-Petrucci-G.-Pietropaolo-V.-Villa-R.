package Progetto_Ing_Sw.com.server.Model;

//TODO --> controllare effetti delle carte e applicare modifiche alla struttura

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
*/

public class WindowBoard implements WindowBoardObserver{

    private GameBoardCard gameBoardCard;
    private int [][] Matrix; //righe - colonne

    //crea una matrice di vuota delle dimensioni volute
    public WindowBoard(int rows, int columns) {
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

    //setta le caselle che sono sui bordi, restituiendo la matrice settata correttaemente
    public ArrayList<ArrayList<MatrixCell>> setBorders(ArrayList<ArrayList<MatrixCell>> Matrix){

        for(int row=0;row<Matrix.size();row++){
            for (int column =0;column<Matrix.get(row).size();column++){

                //CONTROLLO BORDO SUP******************************************************************************
                if(row==0){//controllo sul bordo superiore
                    for (int c=0;c<Matrix.get(row).size();c++){
                        if (column==c){
                            Matrix.get(row).get(c).setOnBorder(true);
                        }
                    }
                }else if(row==Matrix.size()-1){//controllo sul bordo inferiore
                    for (int c=0;c<Matrix.get(row).size();c++){
                        if(column==c){
                            Matrix.get(row).get(c).setOnBorder(true);
                        }
                    }
                }
                //CONTROLLO BORDO INF******************************************************************************
                if(column==0){
                    for(int r=0;r<Matrix.size();r++){
                        Matrix.get(r).get(column).setOnBorder(true);
                    }
                }else if(column==Matrix.get(row).size()-1){
                    for(int r=0;r<Matrix.size();r++){
                        Matrix.get(r).get(column).setOnBorder(true);
                    }
                }
            }
        }
        return Matrix;
    }

    //**TEST METHOD** per verificare che le caselle sono sui bordi
    public void areOnBorders(ArrayList<ArrayList<MatrixCell>> Matrix){
        for(int r=0;r<Matrix.size();r++){
            for (int c=0;c<Matrix.get(r).size();c++){
                if(Matrix.get(r).get(c).isOnBorder()==true){
                    System.out.println("CELL ["+(r+1)+"]["+(c+1)+"] is on border");
                }else{
                    System.out.println("CELL ["+(r+1)+"]["+(c+1)+"] is NOT on border");
                }
            }
        }
    }

    //controlla che un dato abbia la stessa numerazione della cella, da TRUE se vero
    public boolean checkShade(MatrixCell matrixCell, Dice dice){

        boolean shadeState=false;
        switch (matrixCell.getShade()){
            case (6):
                if (dice.getValue() == 1) {
                    shadeState = true;
                }
                break;
            case (7):
                if (dice.getValue() == 2) {
                    shadeState = true;
                }
                break;
            case (8):
                if (dice.getValue() == 3) {
                    shadeState = true;
                }
                break;
            case (9):
                if (dice.getValue() == 4) {
                    shadeState = true;
                }
                break;
            case (10):
                if (dice.getValue() == 5) {
                    shadeState = true;
                }
                break;
            case (11):
                if (dice.getValue() == 6) {
                    shadeState = true;
                }
                break;
        }
        return shadeState;
    }

    //metodo di inserimento di oggetti di tipo dato nella matrice di arraylist
    public ArrayList<ArrayList<MatrixCell>> insertDiceARRLIST(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column, Dice dice){

                if(matrixNotEmpty(Matrix)==false){ //CONTROLLO PRIMO TURNO

                    for(int r=0;r<row;r++) {
                        for (int c = 0; c < column; c++) {

                            if (Matrix.get(r).get(c).isOnBorder() == true && c == column - 1 && r == row - 1) { //CONTROLLO BORDI, CELLA CORRETTA

                                if (Matrix.get(r).get(c).getColor() == Color.BLANK) { //CONROLLO BIANCO
                                    Matrix.get(r).get(c).setDiceContained(dice);
                                    Matrix.get(r).get(c).setUsed(true);
                                    System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA -  white -)");
                                    break;
                                }

                                else if (Matrix.get(r).get(c).getColor() != Color.BLANK){ //CONTROLLO NON BIANCO

                                    if(checkShade(Matrix.get(r).get(c), dice) == true && Matrix.get(r).get(c).getColor() == Color.SHADE){//CONTROLLO SFUMATURA
                                        Matrix.get(r).get(c).setDiceContained(dice);
                                        Matrix.get(r).get(c).setUsed(true);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade ON -)");
                                        break;
                                    }else if(checkShade(Matrix.get(r).get(c), dice) == false && Matrix.get(r).get(c).getColor() == Color.SHADE){
                                        System.out.println("Il dado inserito non rispetta la sfumatura data");
                                        break;
                                    }
                                    if(Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() == dice.getColor()){//CONTROLLO COLORE
                                        Matrix.get(r).get(c).setDiceContained(dice);
                                        Matrix.get(r).get(c).setUsed(true);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade OFF/color ON -)");
                                        break;
                                    }else if(Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() != dice.getColor()){
                                        System.out.println("Il dado inserito non rispetta il colore della cella");
                                        break;
                                    }

                                }
                            }
                            else if (Matrix.get(r).get(c).isOnBorder() == false && c == column - 1 && r == row - 1) {
                            System.out.println("Prima mossa illegale, non stai partendo dai bordi");
                            break;
                            }
                        }
                    }
                }
                else if (matrixNotEmpty(Matrix)==true) {
                    for (int r = 0; r < row; r++) {
                        for (int c = 0; c < column; c++) {

                            if (Matrix.get(r).get(c).isUsed() == false && c == column - 1 && r == row - 1) {

                                if (Matrix.get(r).get(c).getColor() == Color.BLANK) {
                                    Matrix.get(r).get(c).setDiceContained(dice);
                                    Matrix.get(r).get(c).setUsed(true);
                                    System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA-  white -)");
                                    break;

                                } else if (Matrix.get(r).get(c).getColor() != Color.BLANK) {

                                    if (checkShade(Matrix.get(r).get(c), dice) == true && Matrix.get(r).get(c).getColor() == Color.SHADE) {//CONTROLLO SFUMATURA
                                        Matrix.get(r).get(c).setDiceContained(dice);
                                        Matrix.get(r).get(c).setUsed(true);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA - not white/shade ON -)");
                                        break;
                                    } else if (checkShade(Matrix.get(r).get(c), dice) == false && Matrix.get(r).get(c).getColor() == Color.SHADE) {
                                        System.out.println("Il dado inserito non rispetta la sfumatura data");
                                        break;
                                    }
                                    if (Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() == dice.getColor()) {//CONTROLLO COLORE
                                        Matrix.get(r).get(c).setDiceContained(dice);
                                        Matrix.get(r).get(c).setUsed(true);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                        break;
                                    } else if (Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() != dice.getColor()) {
                                        System.out.println("Il dado inserito non rispetta il colore della cella");
                                        break;
                                    }
                                }
                            }else if (Matrix.get(r).get(c).isUsed() == true && c == column - 1 && r == row - 1) {
                                    System.out.println("La casella è già occupata! Salti il turno");
                                    break;
                                }
                            }
                        }
                    }
        return Matrix;
    }

    //controlla la regola di adiacenza dei dadi, restituisce TRUE se il dado inserito nella posizione immessa ha almeno un dado adiacente
    public boolean checkAdjacency(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column){
        boolean adjacencyState=false;

        System.out.println(">>> CELL INPUT (adjacency) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<Matrix.size();r++){
            for (int c=0;c<Matrix.get(r).size();c++){
                if(c==column-1 && r==row-1){
                    if(Matrix.get(r).get(c).isUsed()==true){//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        if((r-1)<0 && (c-1)<0){} //r-1,c-1
                        else if( ((r-1>0)||(r-1==0)) && ((c-1>0)||(c-1==0)) && Matrix.get(r-1).get(c-1).isUsed()){

                            int row_adj=r-1;
                            int col_adj=c-1;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && Matrix.get(r-1).get(c).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r-1)<0 && (c+1)>Matrix.get(r).size()){} //r-1,c+1
                        else if( ((r-1>0)||(r-1==0)) && (c+1)<Matrix.get(r).size() && Matrix.get(r-1).get(c+1).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c+1;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && Matrix.get(r).get(c-1).isUsed()){
                            int row_adj=r;
                            int col_adj=c-1;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((c+1)>Matrix.get(r).size()){} //r,c+1
                        else if(((c+1)<Matrix.get(r).size()) && Matrix.get(r).get(c+1).isUsed()){
                            int row_adj=r;
                            int col_adj=c+1;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r+1)>Matrix.size() && (c-1)<0){} //r+1,c-1
                        else if(((r+1)<Matrix.size()) && ((c-1>0)||(c-1==0)) && Matrix.get(r+1).get(c-1).isUsed() ){
                            int row_adj=r+1;
                            int col_adj=c-1;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r+1)>Matrix.size()){} //r+1,c
                        else if(((r+1)<Matrix.get(r).size()) && Matrix.get(r+1).get(c).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r+1)>Matrix.size() && (c+1)>Matrix.get(r).size()){} //r+1,c+1
                        else if(((r+1)<Matrix.get(r).size()) && ((c+1)<Matrix.get(r).size()) && Matrix.get(r+1).get(c+1).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c+1;

                            System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                    }else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi adiacenti");
                    }
                }
            }
        }
        return adjacencyState;
    }

    //controlla la regola di ortogonalità dei colori, restituisce TRUE se il dado inserito nella posizione immessa non ha dadi ortogonali dello stesso colore
    public boolean checkOrtogonalColor(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column){
        boolean correctColor=false;

        System.out.println(">>> CELL INPUT (ortogonal color) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<Matrix.size();r++){
            for (int c=0;c<Matrix.get(r).size();c++){
                if(c==column-1 && r==row-1){
                    if(Matrix.get(r).get(c).isUsed()==true){//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        //CASI DA CONTROLLARE
                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && Matrix.get(r-1).get(c).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getColor()==Matrix.get(r-1).get(c).getDiceContained().getColor()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOPRA");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getColor()!=Matrix.get(r-1).get(c).getDiceContained().getColor())){
                                correctColor=true;
                            break;
                            }
                        }

                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && Matrix.get(r).get(c-1).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getColor()==Matrix.get(r).get(c-1).getDiceContained().getColor()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore A SINISTRA");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getColor()!=Matrix.get(r).get(c-1).getDiceContained().getColor())){
                                correctColor=true;
                                break;
                            }
                        }
                        if((c+1)>Matrix.get(r).size()){} //r,c+1
                        else if(((c+1)<Matrix.get(r).size()) && Matrix.get(r).get(c+1).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getColor()==Matrix.get(r).get(c+1).getDiceContained().getColor()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore A DESTRA");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getColor()!=Matrix.get(r).get(c+1).getDiceContained().getColor())){
                                correctColor=true;
                                break;
                            }
                        }
                        if((r+1)>Matrix.size()){} //r+1,c
                        else if(((r+1)<Matrix.get(r).size()) && Matrix.get(r+1).get(c).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getColor()==Matrix.get(r+1).get(c).getDiceContained().getColor()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOTTO");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getColor()!=Matrix.get(r+1).get(c).getDiceContained().getColor())){
                                correctColor=true;
                                break;
                            }
                        }

                        //CASI LIMITE DA INCLUDERE PER CORRETTEZZA
                        if((r-1)<0 && (c-1)<0){} //r-1,c-1
                        else if( ((r-1>0)||(r-1==0)) && ((c-1>0)||(c-1==0)) && Matrix.get(r-1).get(c-1).isUsed()){

                            int row_adj=r-1;
                            int col_adj=c-1;

                            System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                        if((r-1)<0 && (c+1)>Matrix.get(r).size()){} //r-1,c+1
                        else if( ((r-1>0)||(r-1==0)) && (c+1)<Matrix.get(r).size() && Matrix.get(r-1).get(c+1).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c+1;

                            System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                        if((r+1)>Matrix.size() && (c-1)<0){} //r+1,c-1
                        else if(((r+1)<Matrix.size()) && ((c-1>0)||(c-1==0)) && Matrix.get(r+1).get(c-1).isUsed() ){
                            int row_adj=r+1;
                            int col_adj=c-1;

                            System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                        if((r+1)>Matrix.size() && (c+1)>Matrix.get(r).size()){} //r+1,c+1
                        else if(((r+1)<Matrix.get(r).size()) && ((c+1)<Matrix.get(r).size()) && Matrix.get(r+1).get(c+1).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c+1;

                            System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                    }else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi con colori uguali");
                    }
                }
            }
        }
        return correctColor;
    }

    //controlla la regola di ortogonalità dei numeri, restituisce TRUE se il dado inserito nella posizione immessa non ha dadi ortogonali con lo stesso valore
    public boolean checkOrtogonalValue(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column){
        boolean correctNumber=false;

        System.out.println(">>> CELL INPUT (ortogonal value) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<Matrix.size();r++){
            for (int c=0;c<Matrix.get(r).size();c++){
                if(c==column-1 && r==row-1){
                    if(Matrix.get(r).get(c).isUsed()==true){//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && Matrix.get(r-1).get(c).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getValue()==Matrix.get(r-1).get(c).getDiceContained().getValue()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore SOPRA");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getValue()!=Matrix.get(r-1).get(c).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }

                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && Matrix.get(r).get(c-1).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getValue()==Matrix.get(r).get(c-1).getDiceContained().getValue()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore A SINISTRA");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getValue()!=Matrix.get(r).get(c-1).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }
                        if((c+1)>Matrix.get(r).size()){} //r,c+1
                        else if(((c+1)<Matrix.get(r).size()) && Matrix.get(r).get(c+1).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getValue()==Matrix.get(r).get(c+1).getDiceContained().getValue()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore A DESTRA");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getValue()!=Matrix.get(r).get(c+1).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }
                        if((r+1)>Matrix.size()){} //r+1,c
                        else if(((r+1)<Matrix.get(r).size()) && Matrix.get(r+1).get(c).isUsed()){

                            if(Matrix.get(r).get(c).getDiceContained().getValue()==Matrix.get(r+1).get(c).getDiceContained().getValue()){
                                System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOTTO");
                                break;
                            }
                            else if((Matrix.get(r).get(c).getDiceContained().getValue()!=Matrix.get(r+1).get(c).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }
                    }else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi con colori uguali");
                    }
                }
            }
        }
        return correctNumber;
    }

    //******************************REAL GAMEPLAY*************************************************************//

    //metodo finale di inserimento dei dadi
    public ArrayList<ArrayList<MatrixCell>> insertDice(ArrayList<ArrayList<MatrixCell>> Matrix, int row, int column, Dice dice){

        if(matrixNotEmpty(Matrix)==false){ //CONTROLLO PRIMO TURNO

            for(int r=0;r<row;r++) {
                for (int c = 0; c < column; c++) {

                    if (Matrix.get(r).get(c).isOnBorder() == true && c == column - 1 && r == row - 1) { //CONTROLLO BORDI, CELLA CORRETTA

                        if (Matrix.get(r).get(c).getColor() == Color.BLANK) { //CONROLLO BIANCO
                            Matrix.get(r).get(c).setDiceContained(dice);
                            Matrix.get(r).get(c).setUsed(true);
                            System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA -  white -)");
                            break;
                        }

                        else if (Matrix.get(r).get(c).getColor() != Color.BLANK){ //CONTROLLO NON BIANCO

                            if(checkShade(Matrix.get(r).get(c), dice) == true && Matrix.get(r).get(c).getColor() == Color.SHADE){//CONTROLLO SFUMATURA
                                Matrix.get(r).get(c).setDiceContained(dice);
                                Matrix.get(r).get(c).setUsed(true);
                                System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade ON -)");
                                break;
                            }else if(checkShade(Matrix.get(r).get(c), dice) == false && Matrix.get(r).get(c).getColor() == Color.SHADE){
                                System.out.println("Il dado inserito non rispetta la sfumatura data");
                                break;
                            }
                            if(Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() == dice.getColor()){//CONTROLLO COLORE
                                Matrix.get(r).get(c).setDiceContained(dice);
                                Matrix.get(r).get(c).setUsed(true);
                                System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade OFF/color ON -)");
                                break;
                            }else if(Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() != dice.getColor()){
                                System.out.println("Il dado inserito non rispetta il colore della cella");
                                break;
                            }

                        }
                    }
                    else if (Matrix.get(r).get(c).isOnBorder() == false && c == column - 1 && r == row - 1) {
                        System.out.println("Prima mossa illegale, non stai partendo dai bordi");
                        break;
                    }
                }
            }
        }
        else if (matrixNotEmpty(Matrix)==true)
        { //Mosse successive alla prima

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {

                    if (Matrix.get(r).get(c).isUsed() == false && c == column - 1 && r == row - 1)
                    { //CASO CELLA LIBERA

                            if (Matrix.get(r).get(c).getColor() == Color.BLANK)
                            {//CASO CELLA BIANCA

                                Matrix.get(r).get(c).setDiceContained(dice);
                                Matrix.get(r).get(c).setUsed(true);

                                if(checkAdjacency(Matrix,r+1,c+1))
                                {//CONTROLLO ADIACENZA OK

                                    if (checkOrtogonalColor(Matrix, r + 1, c + 1) || checkOrtogonalValue(Matrix, r + 1, c + 1))
                                    {//CONTROLLO COLORE/NUMERO OK

                                        if (checkOrtogonalColor(Matrix, r + 1, c + 1))
                                        {//CONTROLLO COLORE OK
                                            System.out.println("DADO INSERITO CORRETTAMENTE (after  adj+color control (MOSSA SUCCESSIVA-  white -)");
                                            break;
                                        }
                                        else if (checkOrtogonalValue(Matrix, r + 1, c + 1))
                                        {//CONTROLLO NUMERO OK
                                            System.out.println("DADO INSERITO CORRETTAMENTE (after  adj+number control (MOSSA SUCCESSIVA-  white -)");
                                            break;
                                        }
                                    }
                                    else if (checkOrtogonalColor(Matrix, r + 1, c + 1) == false || checkOrtogonalValue(Matrix, r + 1, c + 1)==false)
                                    { // NO COLORE/NUMERO

                                        if (checkOrtogonalColor(Matrix, r + 1, c + 1)==false)
                                        {//CONTROLLO COLORE NO
                                            System.out.println("Non stai rispettando l'ortogonalità dei colori (MOSSA SUCCESSIVA-  white -)");
                                            Matrix.get(r).get(c).setDiceContained(null);
                                            Matrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                        else if (checkOrtogonalValue(Matrix, r + 1, c + 1)==false)
                                        {//CONTROLLO NUMERO NO
                                            System.out.println("Non stai rispettando l'ortogonalità dei numeri (MOSSA SUCCESSIVA-  white -)");
                                            Matrix.get(r).get(c).setDiceContained(null);
                                            Matrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                    }
                                }
                                else if(checkAdjacency(Matrix,r+1,c+1)==false)
                                { // NO ADIACENZA
                                    System.out.println("Non stai rispettando le regole di adiacenza");
                                    Matrix.get(r).get(c).setDiceContained(dice);
                                    break;
                                }

                            } else if (Matrix.get(r).get(c).getColor() != Color.BLANK)
                            {//CASO CELLA NON BIANCA

                                if (checkShade(Matrix.get(r).get(c), dice) == true && Matrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA OK

                                    Matrix.get(r).get(c).setDiceContained(dice);
                                    Matrix.get(r).get(c).setUsed(true);

                                    if(checkAdjacency(Matrix,r+1,c+1))
                                    {//CONTROLLO ADIACENZA

                                        if (checkOrtogonalColor(Matrix, r + 1, c + 1) || checkOrtogonalValue(Matrix, r + 1, c + 1))
                                        {///CONTROLLO COLORE/NUMERO OK

                                            if (checkOrtogonalColor(Matrix, r + 1, c + 1))
                                            {//CONTROLLO COLORE OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+color control (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                break;
                                            }
                                            else if (checkOrtogonalValue(Matrix, r + 1, c + 1))
                                            {//CONTROLLO NUMERO OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+number control (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                break;
                                            }
                                        }
                                        else if (checkOrtogonalColor(Matrix, r + 1, c + 1) == false || checkOrtogonalValue(Matrix, r + 1, c + 1)==false )
                                        {// COLORE/NUMERO NO

                                            if (checkOrtogonalColor(Matrix, r + 1, c + 1)==false)
                                            {//CONTROLLO COLORE NO
                                                System.out.println("Non stai rispettando le regole di ortogonalità dei colori (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                Matrix.get(r).get(c).setDiceContained(null);
                                                Matrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                            else if (checkOrtogonalValue(Matrix, r + 1, c + 1)==false)
                                            {//CONTROLLO NUMERO NO
                                                System.out.println("Non stai rispettando l'ortogonalità dei numeri (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                Matrix.get(r).get(c).setDiceContained(null);
                                                Matrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                        }
                                    }
                                    else if(checkAdjacency(Matrix,r+1,c+1)==false)
                                    { // ADIACENZA NO
                                        System.out.println("Non stai rispettando le regole di adiacenza");
                                        Matrix.get(r).get(c).setDiceContained(null);
                                        Matrix.get(r).get(c).setUsed(false);
                                        break;
                                    }
                                }
                                else if (checkShade(Matrix.get(r).get(c), dice) == false && Matrix.get(r).get(c).getColor() == Color.SHADE)
                                {//NO SFUMATURA
                                    System.out.println("Il dado inserito non rispetta la sfumatura data");
                                    break;
                                }

                                //-------

                                if (Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() == dice.getColor())
                                {//CONTROLLO COLORE CELLA
                                    Matrix.get(r).get(c).setDiceContained(dice);
                                    Matrix.get(r).get(c).setUsed(true);

                                    if(checkAdjacency(Matrix,r+1,c+1))
                                    {//CONTROLLO ADIACENZA

                                        if (checkOrtogonalColor(Matrix, r + 1, c + 1) || checkOrtogonalValue(Matrix, r + 1, c + 1) )
                                        {//CONTROLLO COLORE/NUMERO

                                            if (checkOrtogonalColor(Matrix, r + 1, c + 1))
                                            {//CONTROLLO COLORE OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+color control (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                break;
                                            }
                                            else if (checkOrtogonalValue(Matrix, r + 1, c + 1))
                                            {//CONTROLLO NUMERO OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+number control (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                break;
                                            }

                                        }
                                        else if (checkOrtogonalColor(Matrix, r + 1, c + 1) == false || checkOrtogonalValue(Matrix, r + 1, c + 1)==false)
                                        {//NO COLORE/NUMERO

                                            if (checkOrtogonalColor(Matrix, r + 1, c + 1)==false)
                                            {//CONTROLLO COLORE NO
                                                System.out.println("Non stai rispettando le regole di ortogonalità dei colori (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                Matrix.get(r).get(c).setDiceContained(null);
                                                Matrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                            else if (checkOrtogonalValue(Matrix, r + 1, c + 1)==false)
                                            {//CONTROLLO NUMERO NO
                                                System.out.println("Non stai rispettando l'ortogonalità dei numeri (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                Matrix.get(r).get(c).setDiceContained(null);
                                                Matrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                        }
                                    }
                                    else if(checkAdjacency(Matrix,r+1,c+1)==false)
                                    {// NO ADIACENZA
                                        System.out.println("Non stai rispettando le regole di adiacenza");
                                        Matrix.get(r).get(c).setDiceContained(null);
                                        Matrix.get(r).get(c).setUsed(false);
                                        break;
                                    }
                                }
                                else if (Matrix.get(r).get(c).getColor() != Color.SHADE && Matrix.get(r).get(c).getColor() != dice.getColor())
                                {//NO COLORE CELLA
                                    System.out.println("Il dado inserito non rispetta il colore della cella");
                                    Matrix.get(r).get(c).setDiceContained(dice);
                                    break;
                                }
                            }
                    }
                    else if (Matrix.get(r).get(c).isUsed() == true && c == column - 1 && r == row - 1)
                    {//CASO CELLA OCCUPATA
                        System.out.println("La casella è già occupata! Salti il turno");
                        break;
                    }
                }
            }
        }
        return Matrix;
    }

}

