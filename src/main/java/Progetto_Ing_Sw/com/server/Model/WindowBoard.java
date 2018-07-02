package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.tools.JSONCreator;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * <h1>WindowBoard</h1>
 * The class WindowBoard implements the main object that the player uses
 * during all the match. It represents the moment in which the player,
 * after choosing the GameBoardCard, inserts it in the Window-like
 * structure and begins to place the dice.
 *
 *@author Roberto Villa
 */
public class WindowBoard implements WindowBoardObserver{

    private int [][] Matrix; //righe - colonne
    private ArrayList<ArrayList<MatrixCell>> usedMatrix;
    private String localTitle;
    private int difficulty;

    public String getTitle() {return localTitle;}
    public void setTitle(String title) {this.localTitle = title;}

    /**
     * This method is used to create the integer matrix-object
     * using two main parameters, rows and columns. It also create
     * the empty ArrayList-matrix that it's used inside other methods.
     * @param rows This is the first parameter, it represents the number of row
     * @param columns This is the first parameter, it represents the number of row
     */
    public WindowBoard(int rows, int columns) {

        Matrix = new int[rows][columns];
        ArrayList<ArrayList<MatrixCell>> usedMatrix = new ArrayList<>();

    }

    /**
     * This method is used to build the main object, using the ArrayList-matrix
     * (of MatrixCell, another object), by importing parameters of the GameBoardCard from Json file
     * @param gameBoardCard It's the gameBoardCard that has been selected by the player, used for build the main matrix
     */
    public WindowBoard(GameBoardCard gameBoardCard){
        Matrix=gameBoardCard.getMatrixScheme();
        usedMatrix=fromIntToArrayList(Matrix,Matrix.length,Matrix[0].length);
        setBorders();
        difficulty=gameBoardCard.getDifficulty();
    }

    /**
     * This is the method that return the main object of the class
     * @return This returns the ArrayList-Matrix Object
     */
    public ArrayList<ArrayList<MatrixCell>> getUsedMatrix(){return usedMatrix;}

    /**
     * This method is used to set the ArrayList-Matrix parameter as the used object
     * @param usedMatrix is the ArrayList-Matrix that is gonna be used
     */
    public void setUsedMatrix(ArrayList<ArrayList<MatrixCell>> usedMatrix) {this.usedMatrix = usedMatrix;}

      //stampa matrici SOLO 4righeX5colonne
    public void printMatrix(int Matrix[][], int rows, int columns){

        for(int i=0;i<rows;i++){
            System.out.println("---------------------");
            System.out.println("| "+Matrix[i][columns-5]+" | "+Matrix[i][columns-4]+" | "+Matrix[i][columns-3]+" | "+Matrix[i][columns-2]+" | "+Matrix[i][columns-1]+" |");
        }
        System.out.println("---------------------");
    }

    /**
     * This method imports, from Json file, the Integer-matrix object, used initially to create the
     * ArrayList-Matrix object. It has also rows and columns, to avoid future problem with bigger matrix.
     * @param rows This parameter represents the number of rows of the matrix
     * @param columns This parameter represents the number of columns of the matrix
     * @param cardCode This parameter represent the number of the GameBoardCard that is going to be imported
     * @return int[][] This return the Integer-matrix object
     */
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

    /**
     * This method is used to set the title at the main WindowBoard object, importing from Json using the
     * GameBoardCard's code.
     * @param cardCode This parameter represent the code of the GameBoardCard that is going to be imported
     */
    public void importNameFromFile(int cardCode){

        String localTitle = new String();

        switch (cardCode){
            case (1):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/KaleidoscopicDream.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (2):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Firmitas.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (3):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/FractalDrops.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (4):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/RipplesOfLight.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (5):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/LuxMundi.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (6):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/LuxAstram.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (7):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Gravitas.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (8):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/WaterOfLife.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (9):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/SunCatcher.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (10):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/ShadowThief.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (11):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/AuroraeMagnificus.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (12):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/AuroraSagradis.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (13):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/SymphonyOfLight.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (14):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Virtus.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (15):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Firelight.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (16):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/SunsGlory.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (17):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Batllo.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (18):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Bellesguard.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (19):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/FulgorDelCielo.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (20):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/LuzCelestial.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (21):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/ChromaticSplendor.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (22):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Comitas.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (23):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/ViaLux.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
            case (24):try { localTitle= JSONCreator.parseStringFieldFromFile("Resources/Cards/GameBoardCards/Industria.json", "title");}
            catch(FileNotFoundException e){e.printStackTrace();}
                break;
        }
        setTitle(localTitle);
    };


    /**
     * This method is used to create an ArrayList-matrix from an Integer-matrix, using rows and columns and switch-case
     * @param Matrix Integer-matrix object that is going to be converted
     * @param rows This parameter represents the number of rows of the matrix
     * @param columns This parameter represents the number of rows of the matrix
     * @return ArrayList<ArrayList<MatrixCell>> It returns the main WindowBoard object that is the ArrayList-matrix
     */
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

    /**
     * This method is used to check if the matrix is empty, so it's easy to verify that is the first phase
     * @return it return TRUE if the matrix is not empty
     */
    public boolean matrixNotEmpty(){
        boolean cellState=false;
        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){
                if(usedMatrix.get(r).get(c).isUsed()!=false){
                    cellState=true;
                    break;}
            }
        }
        return cellState;
    }

    /**
     * This method sets the borders of the ArrayList-matrix. It's necessary to implement the placement restrictions
     * @return It returns the WindowBoard (ArrayList-matrix) with Borders set
     */
    public ArrayList<ArrayList<MatrixCell>> setBorders(){

        for(int row=0;row<usedMatrix.size();row++){
            for (int column =0;column<usedMatrix.get(row).size();column++){

                //CONTROLLO BORDO SUP******************************************************************************
                if(row==0){//controllo sul bordo superiore
                    for (int c=0;c<usedMatrix.get(row).size();c++){
                        if (column==c){
                            usedMatrix.get(row).get(c).setOnBorder(true);
                        }
                    }
                }else if(row==usedMatrix.size()-1){//controllo sul bordo inferiore
                    for (int c=0;c<usedMatrix.get(row).size();c++){
                        if(column==c){
                            usedMatrix.get(row).get(c).setOnBorder(true);
                        }
                    }
                }
                //CONTROLLO BORDO INF******************************************************************************
                if(column==0){
                    for(int r=0;r<usedMatrix.size();r++){
                        usedMatrix.get(r).get(column).setOnBorder(true);
                    }
                }else if(column==usedMatrix.get(row).size()-1){
                    for(int r=0;r<usedMatrix.size();r++){
                        usedMatrix.get(r).get(column).setOnBorder(true);
                    }
                }
            }
        }
        return usedMatrix;
    }

    //metodo per verificare che ci siano caselle che sono settate correttamente sui bordi
    public boolean areOnBorders(){

        boolean onBorders=false;

        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){
                if(usedMatrix.get(r).get(c).isOnBorder()==true){
                    onBorders=true;
                }else{}
            }
        }
        return onBorders;
    }

    /**
     * This method controls that the dice placed in the current matrixCell has the number that match the actual
     * matrixCell's shade
     * @param matrixCell This parameter represents the matrixCell where i place the dice
     * @param dice the dice that is going to be placed
     * @return it returns TRUE if the number of the dice matches the shade of the MatrixCell
     */
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

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void placeDice(Dice dice, int r, int c){
        usedMatrix.get(r).get(c).setDiceContained(dice);
        usedMatrix.get(r).get(c).setUsed(true);
    }

    private void removeDice(int r, int c){
        usedMatrix.get(r).get(c).setDiceContained(null);
        usedMatrix.get(r).get(c).setUsed(false);
    }

    private void applyColorBreaker(int r, int c) throws PlaceDiceException {

        if(checkAdjacency(r+1,c+1))
        {//CONTROLLO ADIACENZA OK
            if (checkOrthogonalValue(r + 1, c + 1))
            {//CONTROLLO NUMERO OK
                //break;
            }else if (checkOrthogonalValue(r + 1, c + 1)==false)
            {   //CONTROLLO NUMERO NO
                removeDice(r,c);
                throw new PlaceDiceException("Wrong orthogonal value");
            }
        }else if(checkAdjacency(r+1,c+1)==false)
        { // NO ADIACENZA
            removeDice(r,c);
            throw new PlaceDiceException("No adjacent dice");
        }

    }

    private void applyNumberBreaker(int r, int c) throws PlaceDiceException {

        if(checkAdjacency(r+1,c+1))
        {//CONTROLLO ADIACENZA OK
            if (checkOrthogonalColor(r + 1, c + 1))
            {//CONTROLLO COLORE OK
                //break;
            }
            else if (checkOrthogonalColor(r + 1, c + 1)==false)
            { // NO COLORE
                removeDice(r,c);
                throw new PlaceDiceException("Wrong orthogonal color");
            }
        }
        else if(checkAdjacency(r+1,c+1)==false)
        { // NO ADIACENZA
            removeDice(r,c);
            throw new PlaceDiceException("No adjacent dice");
        }

    }

    private void applyNormalRules (int r, int c) throws PlaceDiceException {

        if(checkAdjacency(r+1,c+1))
        {//CONTROLLO ADIACENZA OK

            if (checkOrthogonalColor(r + 1, c + 1) && checkOrthogonalValue(r + 1, c + 1))
            {//CONTROLLO COLORE/NUMERO OK
                //break;
            }
            else if (checkOrthogonalColor(r + 1, c + 1) == false || checkOrthogonalValue(r + 1, c + 1)==false)
            { // NO COLORE/NUMERO

                if (checkOrthogonalColor(r + 1, c + 1)==false)
                {//CONTROLLO COLORE NO
                    removeDice(r,c);
                    throw new PlaceDiceException("Wrong orthogonal color");
                }
                else if (checkOrthogonalValue(r + 1, c + 1)==false)
                {//CONTROLLO NUMERO NO
                    removeDice(r,c);
                    throw new PlaceDiceException("Wrong orthogonal value");
                }
            }
        }
        else if(checkAdjacency(r+1,c+1)==false)
        { // NO ADIACENZA
            removeDice(r,c);
            throw new PlaceDiceException("No adjacent dice");
        }

    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * This method is used to insert Dice in the ArrayList-Matrix without following the placement restrictions;
     * it's used to test placement restrictions' methods
     * @param row This parameter represents the row's coordinate
     * @param column This parameter represents the column's coordinate
     * @param dice This parameter represents the dice selected to be placed in the matrix
     * @return This returns a WindowBoard with the dice in the correct position
     */
    public ArrayList<ArrayList<MatrixCell>> insertDiceARRLIST(int row, int column, Dice dice){

                if(matrixNotEmpty()==false){ //CONTROLLO PRIMO TURNO

                    for(int r=0;r<row;r++) {
                        for (int c = 0; c < column; c++) {

                            if (usedMatrix.get(r).get(c).isOnBorder() == true && c == column - 1 && r == row - 1) { //CONTROLLO BORDI, CELLA CORRETTA

                                if (usedMatrix.get(r).get(c).getColor() == Color.BLANK) { //CONROLLO BIANCO
                                    placeDice(dice,r,c);
                                    System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA -  white -)");
                                    break;
                                }

                                else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK){ //CONTROLLO NON BIANCO

                                    if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE){//CONTROLLO SFUMATURA
                                        placeDice(dice,r,c);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade ON -)");
                                        break;
                                    }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE){
                                        System.out.println("Il dado inserito non rispetta la sfumatura data");
                                        break;
                                    }
                                    if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor()){//CONTROLLO COLORE
                                        placeDice(dice,r,c);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade OFF/color ON -)");
                                        break;
                                    }else if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor()){
                                        System.out.println("Il dado inserito non rispetta il colore della cella");
                                        break;
                                    }

                                }
                            }
                            else if (usedMatrix.get(r).get(c).isOnBorder() == false && c == column - 1 && r == row - 1) {
                            System.out.println("Prima mossa illegale, non stai partendo dai bordi");
                            break;
                            }
                        }
                    }
                }
                else if (matrixNotEmpty()==true) {
                    for (int r = 0; r < row; r++) {
                        for (int c = 0; c < column; c++) {

                            if (usedMatrix.get(r).get(c).isUsed() == false && c == column - 1 && r == row - 1) {

                                if (usedMatrix.get(r).get(c).getColor() == Color.BLANK) {
                                    placeDice(dice,r,c);
                                    System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA-  white -)");
                                    break;

                                } else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK) {

                                    if (checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE) {//CONTROLLO SFUMATURA
                                        placeDice(dice,r,c);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA - not white/shade ON -)");
                                        break;
                                    } else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE) {
                                        System.out.println("Il dado inserito non rispetta la sfumatura data");
                                        break;
                                    }
                                    if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor()) {//CONTROLLO COLORE
                                        placeDice(dice,r,c);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                        break;
                                    } else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor()) {
                                        System.out.println("Il dado inserito non rispetta il colore della cella");
                                        break;
                                    }
                                }
                            }else if (usedMatrix.get(r).get(c).isUsed() == true && c == column - 1 && r == row - 1) {
                                    System.out.println("La casella è già occupata! Salti il turno");
                                    break;
                                }
                            }
                        }
                    }
        return usedMatrix;
    }


    /**
     * This method is used to control that the dice placed in the cell with the input-coordinates has a dice adjacent.
     * It returns TRUE if this condition is verified
     * @param row This parameter represents the row's coordinate
     * @param column This parameter represents the columns's coordinate
     * @return it returns TRUE if the dice in the cell has a dice adjacent
     */
    public boolean checkAdjacency(int row, int column){
        boolean adjacencyState=false;

        //System.out.println(">>> CELL INPUT (adjacency) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){

                if(c==column-1 && r==row-1) //analizzo le caselle adiacenti a quella cercata
                {
                    if(usedMatrix.get(r).get(c).isUsed()==true)
                    {//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        if((r-1)<0 && (c-1)<0){} //r-1,c-1
                        else if( ((r-1>0)||(r-1==0)) && ((c-1>0)||(c-1==0)) && usedMatrix.get(r-1).get(c-1).isUsed()){

                            int row_adj=r-1;
                            int col_adj=c-1;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && usedMatrix.get(r-1).get(c).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r-1)<0 && (c+1)>usedMatrix.get(r).size()){} //r-1,c+1
                        else if( ((r-1>0)||(r-1==0)) && (c+1)<usedMatrix.get(r).size() && usedMatrix.get(r-1).get(c+1).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c+1;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && usedMatrix.get(r).get(c-1).isUsed()){
                            int row_adj=r;
                            int col_adj=c-1;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((c+1)>usedMatrix.get(r).size()){} //r,c+1
                        else if(((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r).get(c+1).isUsed()){
                            int row_adj=r;
                            int col_adj=c+1;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size() && (c-1)<0){} //r+1,c-1
                        else if(((r+1)<usedMatrix.size()) && ((c-1>0)||(c-1==0)) && usedMatrix.get(r+1).get(c-1).isUsed() ){
                            int row_adj=r+1;
                            int col_adj=c-1;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size()){} //r+1,c
                        else if(((r+1)<usedMatrix.size()) && usedMatrix.get(r+1).get(c).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size() && (c+1)>usedMatrix.get(r).size()){} //r+1,c+1
                        else if(((r+1)<usedMatrix.size()) && ((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r+1).get(c+1).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c+1;

                            //System.out.println("CELL ADJACENT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            adjacencyState=true;
                            break;
                        }
                    }
                    else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi adiacenti");
                    }
                }
            }
        }
        return adjacencyState;
    }

    /**
     * This method is used to control that the dice placed with the input-coordinates has a dice with its same color
     * in an orthogonal position. It returns FALSE if the condition is verified
     * @param row This parameter represents the row's coordinate
     * @param column This parameter represents the columns's coordinate
     * @return it returns FALSE if the dice in the cell has a dice with its same color in an orthogonal position
     */
    public boolean checkOrthogonalColor(int row, int column){

        boolean correctColor=true;

        //System.out.println(">>> CELL INPUT (ortogonal color) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){
                if(c==column-1 && r==row-1){
                    if(usedMatrix.get(r).get(c).isUsed()==true){//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        //CASI DA CONTROLLARE
                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && usedMatrix.get(r-1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r-1).get(c).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOPRA");
                                correctColor=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r-1).get(c).getDiceContained().getColor())){}
                        }

                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && usedMatrix.get(r).get(c-1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r).get(c-1).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore A SINISTRA");
                                correctColor=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r).get(c-1).getDiceContained().getColor())){}
                        }
                        if((c+1)>usedMatrix.get(r).size()){} //r,c+1
                        else if(((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r).get(c+1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r).get(c+1).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore A DESTRA");
                                correctColor=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r).get(c+1).getDiceContained().getColor())){}
                        }
                        if((r+1)>usedMatrix.size()){} //r+1,c
                        else if(((r+1)<usedMatrix.size()) && usedMatrix.get(r+1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r+1).get(c).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOTTO");
                                correctColor=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r+1).get(c).getDiceContained().getColor())){}
                        }
                    }
                    else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi con colori uguali");
                    }
                }
            }
        }
        return correctColor;
    }

    /**
     * This method is used to control that the dice placed with the input-coordinates has a dice with its same value
     * in an orthogonal position. It returns FALSE if the condition is verified
     * @param row This parameter represents the row's coordinate
     * @param column This parameter represents the columns's coordinate
     * @return it returns FALSE if the dice in the cell has a dice with its same value in an orthogonal position
     */
    public boolean checkOrthogonalValue(int row, int column){

        boolean correctNumber=true;

        //System.out.println(">>> CELL INPUT (ortogonal value) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){
                if(c==column-1 && r==row-1){
                    if(usedMatrix.get(r).get(c).isUsed()==true){//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && usedMatrix.get(r-1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r-1).get(c).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore SOPRA");
                                correctNumber=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r-1).get(c).getDiceContained().getValue())){}
                        }

                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && usedMatrix.get(r).get(c-1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r).get(c-1).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore A SINISTRA");
                                correctNumber=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r).get(c-1).getDiceContained().getValue())){}
                        }
                        if((c+1)>usedMatrix.get(r).size()){} //r,c+1
                        else if(((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r).get(c+1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r).get(c+1).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore A DESTRA");
                                correctNumber=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r).get(c+1).getDiceContained().getValue())){}
                        }
                        if((r+1)>usedMatrix.size()){} //r+1,c
                        else if(((r+1)<usedMatrix.size()) && usedMatrix.get(r+1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r+1).get(c).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOTTO");
                                correctNumber=false;
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r+1).get(c).getDiceContained().getValue())){}
                        }
                    }else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi con colori uguali");
                    }
                }
            }
        }
        return correctNumber;
    }

    //*******COPERTURA FINO A QUI AL 100%***********************

    public void printMatrixArrayList(){

        for(int i=0;i<usedMatrix.size();i++){
            System.out.println("-----------------------------------------");
            System.out.println("| "+usedMatrix.get(i).get(0).isUsed()+" | "+usedMatrix.get(i).get(1).isUsed()+" | "+usedMatrix.get(i).get(2).isUsed()+" | "+usedMatrix.get(i).get(3).isUsed()+" | "+usedMatrix.get(i).get(4).isUsed()+" |");
        }
        System.out.println("----------------------------------------");
    }

    public void printDiceColorValueArrayList(){

        System.out.println();
        Color color = new Color();
        System.out.println("***********************************************");
        for(int r=0;r<usedMatrix.size();r++) {
            for (int c = 0; c < usedMatrix.get(r).size(); c++) {
                    if(usedMatrix.get(r).get(c).isUsed()){
                        System.out.println("DICE IN (row = "+(r+1)+" & column= "+(c+1)+") ==> ["+color.IntToColor(usedMatrix.get(r).get(c).getDiceContained().getColor())+" , "+usedMatrix.get(r).get(c).getDiceContained().getValue()+"]");
                    }
            }
        }
        System.out.println("***********************************************");
    }


    /**
     * This method is the main method of the game: with the input-coordinates, the method place a dice in the matrix.
     * After that, this action is controlled by placement restrictions' methods described before. If something isn't correct,
     * this method throws a PlaceDiceException (with a single message for each type of exception)
     * @param row This parameter represents the row's coordinate
     * @param column This parameter represents the column's coordinate
     * @param dice This parameter represents the dice that is going to be placed
     * @return It returns a WindowBoard object with the Dice placed if the placement restrictions are verified
     * @throws PlaceDiceException
     */
    public ArrayList<ArrayList<MatrixCell>> insertDice(int row, int column, Dice dice) throws PlaceDiceException {

        if(matrixNotEmpty()==false)
        { //CONTROLLO PRIMO TURNO

            for(int r=0;r<row;r++)
            {
                for (int c = 0; c < column; c++)
                {
                    if (usedMatrix.get(r).get(c).isOnBorder() == true && c == column - 1 && r == row - 1)
                    {//CONTROLLO BORDI, CELLA CORRETTA

                        if (usedMatrix.get(r).get(c).getColor() == Color.BLANK) { //CONROLLO BIANCO
                            placeDice(dice,r,c);
                            break;
                        }
                        else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK)
                        { //CONTROLLO NON BIANCO

                            ///++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE
                            if(dice.isColorBreaker())
                            {
                                if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA
                                    placeDice(dice,r,c);
                                    break;
                                }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {
                                    throw new PlaceDiceException("Shade is different");
                                }
                                //CASO LIMITE COLOR BREAKER - CONTROLLO COLORE
                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE)
                                { //se è una cella colorata inserisco comunque il dado
                                    placeDice(dice,r,c);
                                    break;
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE

                            ///++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO
                            if(dice.isNumberBreaker())
                            {
                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                {//CONTROLLO COLORE
                                    placeDice(dice,r,c);
                                    break;
                                }else if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                {
                                    throw new PlaceDiceException("Color is different");
                                }
                                //CASO LIMITE NUMBER BREAKER + CONTROLLO SFUMATURA
                                if(usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                { //se è una sfumatura inserisco comunque il dado
                                    placeDice(dice,r,c);
                                    break;
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO

                            //CONTROLLI NORMALI

                            if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//CONTROLLO SFUMATURA
                                placeDice(dice,r,c);
                                break;
                            }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {
                                throw new PlaceDiceException("Shade is different");
                            }
                            if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                            {//CONTROLLO COLORE
                                placeDice(dice,r,c);
                                break;
                            }else if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                            {
                                throw new PlaceDiceException("Color is different");
                            }
                        }
                    }
                    else if (usedMatrix.get(r).get(c).isOnBorder() == false && c == column - 1 && r == row - 1)
                    {
                       throw new PlaceDiceException("Should be placed on a border");
                    }
                }
            }
        }
        else if (matrixNotEmpty()==true)
        { //Mosse successive alla prima

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {

                    if (usedMatrix.get(r).get(c).isUsed() == false && c == column - 1 && r == row - 1)
                    { //CASO CELLA LIBERA

                        if (usedMatrix.get(r).get(c).getColor() == Color.BLANK)
                        {//CASO CELLA BIANCA

                            placeDice(dice,r,c);

                            //++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA
                            if(dice.isAdjacencyBreaker())
                            {
                                System.out.println("ADIACENCY - BREAKER (white)");

                                if(checkAdjacency(r+1,c+1)==false){}
                                else if (checkAdjacency(r+1,c+1))
                                {
                                    removeDice(r,c);
                                    throw new PlaceDiceException("Adjacent dice");
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA

                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE
                            if(dice.isColorBreaker())
                            {
                                System.out.println("COLOR - BREAKER (white)");
                                applyColorBreaker(r,c);
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE

                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO
                            if(dice.isNumberBreaker())
                            {
                                System.out.println("NUMBER - BREAKER (white)");
                                applyNumberBreaker(r,c);
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO

                            //CONTROLLI NORMALI

                            if(!dice.isNumberBreaker() && !dice.isColorBreaker() && !dice.isAdjacencyBreaker()) {
                                applyNormalRules(r, c);
                            }

                        }
                        else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK)
                        {//CASO CELLA NON BIANCA

                            placeDice(dice,r,c);

                            //++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO
                            if(dice.isNumberBreaker())
                            {
                                System.out.println("NUMBER - BREAKER (NOT white)");

                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                {//CONTROLLO COLORE
                                    applyNumberBreaker(r,c);
                                }
                                else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                {//NO COLORE CELLA
                                    removeDice(r,c);
                                    throw new PlaceDiceException("wrong color");
                                }

                                if(usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA (caso limite)

                                    System.out.println("NUMBER - BREAKER (NOT white) --> SPECIAL CASE");
                                    applyNumberBreaker(r,c);
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO

                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE
                            if(dice.isColorBreaker())
                            {
                                System.out.println("COLOR - BREAKER (NOT white)");

                                if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA
                                    applyColorBreaker(r,c);

                                }else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//NO SFUMATURA
                                    removeDice(r,c);
                                    throw new PlaceDiceException("Wrong shade");
                                }

                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE)
                                {//CONTROLLO COLORE (caso limite)

                                    System.out.println("COLOR - BREAKER (NOT white) --> SPECIAL CASE");
                                    applyColorBreaker(r,c);
                                }
                            }
                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE

                            ///++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA
                            if(dice.isAdjacencyBreaker())
                            {
                                System.out.println("ADJACENCY - BREAKER (NOT white)");

                                if(checkAdjacency(r+1,c+1)==false)
                                {//EFFETTIVAMENTE NON ADIACENTE A NULLA
                                    if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                    {//CONTROLLO SFUMATURA
                                        //break;
                                    }
                                    else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                    {//NO SFUMATURA
                                        removeDice(r,c);
                                        throw new PlaceDiceException("Wrong shade");
                                    }

                                    if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                    {//CONTROLLO COLORE
                                        break;
                                    }
                                    else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                    {//NO COLORE CELLA
                                        removeDice(r,c);
                                        throw new PlaceDiceException("Wrong color");
                                    }
                                }
                                else if (checkAdjacency(r+1,c+1))
                                {
                                    removeDice(r,c);
                                    throw new PlaceDiceException("Adjacent dice");
                                }
                            }
                            ///++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA

                            //CONTROLLI NORMALI

                            if(!dice.isNumberBreaker() && !dice.isColorBreaker() && !dice.isAdjacencyBreaker())
                            {

                            if (checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//CONTROLLO SFUMATURA OK
                                applyNormalRules(r,c);
                            }
                            else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//NO SFUMATURA
                                removeDice(r,c);
                                throw new PlaceDiceException("Shade is different");
                            }

                            //-------

                            if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                            {//CONTROLLO COLORE CELLA
                                applyNormalRules(r,c);
                            }
                            else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                            {//NO COLORE CELLA
                                removeDice(r,c);
                                throw new PlaceDiceException("Color is not equal");
                            }

                            }
                        }
                    }
                    else if (usedMatrix.get(r).get(c).isUsed() == true && c == column - 1 && r == row - 1)
                    {//CASO CELLA OCCUPATA
                       throw new PlaceDiceException("This cell is occupied");
                    }
                }
            }
        }
        return usedMatrix;
    }

    /**
     * This method calculates points for each dice that have the same color of the Private Objective;
     * Points are gained by summing each dice values
     * @param PrivateObjectiveColor This parameter represents the color of the Private Objective Card
     * @return it returns the number of points gained  by all the dice with the Private Objective's color
     */
    public int calculatePointsFromPrivateObjective(int PrivateObjectiveColor){
        int points = 0;

        for(int r=0;r<usedMatrix.size();r++) {
            for (int c = 0; c < usedMatrix.get(r).size(); c++) {
                if(usedMatrix.get(r).get(c).isUsed()){
                    if(usedMatrix.get(r).get(c).getDiceContained().getColor()==PrivateObjectiveColor) {
                        points=points+usedMatrix.get(r).get(c).getDiceContained().getValue();
                    }
                }
            }
        }

        return points;
    }

    /**
     * This method calculates points that have to be subtracted from Victory Points
     * @return it returns the total of Subtracted-Points
     */
    public int calculatePointsFromEmptySpaces(){
        int points = 0;

        for(int r=0;r<usedMatrix.size();r++) {
            for (int c = 0; c < usedMatrix.get(r).size(); c++) {
                if(!usedMatrix.get(r).get(c).isUsed()){
                    points++;
                }
            }
        }

        return points;
    }

}

