package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.tools.JSONCreator;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class WindowBoard implements WindowBoardObserver{

    private int [][] Matrix; //righe - colonne
    private ArrayList<ArrayList<MatrixCell>> usedMatrix;

    //crea una matrice di vuota delle dimensioni volute
    public WindowBoard(int rows, int columns) {

        Matrix = new int[rows][columns];
        ArrayList<ArrayList<MatrixCell>> usedMatrix = new ArrayList<>();

    }

    public WindowBoard(GameBoardCard gameBoardCard){
        Matrix=gameBoardCard.getMatrixScheme();
        usedMatrix=fromIntToArrayList(Matrix,Matrix.length,Matrix[0].length);
        setBorders();
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

    //******************************REGOLE DI INSERIMENTO*************************************************************//

    //verifica che la matrice non sia vuota, restituendo TRUE in tal caso

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

    //setta le caselle che sono sui bordi, restituiendo la matrice settata correttaemente
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

    //**TEST METHOD** per verificare che le caselle sono sui bordi
    public void areOnBorders(){
        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){
                if(usedMatrix.get(r).get(c).isOnBorder()==true){
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
    public ArrayList<ArrayList<MatrixCell>> insertDiceARRLIST(int row, int column, Dice dice){

                if(matrixNotEmpty()==false){ //CONTROLLO PRIMO TURNO

                    for(int r=0;r<row;r++) {
                        for (int c = 0; c < column; c++) {

                            if (usedMatrix.get(r).get(c).isOnBorder() == true && c == column - 1 && r == row - 1) { //CONTROLLO BORDI, CELLA CORRETTA

                                if (usedMatrix.get(r).get(c).getColor() == Color.BLANK) { //CONROLLO BIANCO
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);
                                    System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA -  white -)");
                                    break;
                                }

                                else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK){ //CONTROLLO NON BIANCO

                                    if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE){//CONTROLLO SFUMATURA
                                        usedMatrix.get(r).get(c).setDiceContained(dice);
                                        usedMatrix.get(r).get(c).setUsed(true);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (PRIMA MOSSA - not white/shade ON -)");
                                        break;
                                    }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE){
                                        System.out.println("Il dado inserito non rispetta la sfumatura data");
                                        break;
                                    }
                                    if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor()){//CONTROLLO COLORE
                                        usedMatrix.get(r).get(c).setDiceContained(dice);
                                        usedMatrix.get(r).get(c).setUsed(true);
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
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);
                                    System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA-  white -)");
                                    break;

                                } else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK) {

                                    if (checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE) {//CONTROLLO SFUMATURA
                                        usedMatrix.get(r).get(c).setDiceContained(dice);
                                        usedMatrix.get(r).get(c).setUsed(true);
                                        System.out.println("DADO INSERITO CORRETTAMENTE (MOSSA SUCCESSIVA - not white/shade ON -)");
                                        break;
                                    } else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE) {
                                        System.out.println("Il dado inserito non rispetta la sfumatura data");
                                        break;
                                    }
                                    if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor()) {//CONTROLLO COLORE
                                        usedMatrix.get(r).get(c).setDiceContained(dice);
                                        usedMatrix.get(r).get(c).setUsed(true);
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

    //controlla la regola di adiacenza dei dadi, restituisce TRUE se il dado inserito nella posizione immessa ha almeno un dado adiacente
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

    //controlla la regola di ortogonalità dei colori, restituisce TRUE se il dado inserito nella posizione immessa non ha dadi ortogonali dello stesso colore
    public boolean checkOrthogonalColor(int row, int column){
        boolean correctColor=false;

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
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r-1).get(c).getDiceContained().getColor())){
                                correctColor=true;
                            break;
                            }
                        }

                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && usedMatrix.get(r).get(c-1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r).get(c-1).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore A SINISTRA");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r).get(c-1).getDiceContained().getColor())){
                                correctColor=true;
                                break;
                            }
                        }
                        if((c+1)>usedMatrix.get(r).size()){} //r,c+1
                        else if(((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r).get(c+1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r).get(c+1).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore A DESTRA");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r).get(c+1).getDiceContained().getColor())){
                                correctColor=true;
                                break;
                            }
                        }
                        if((r+1)>usedMatrix.size()){} //r+1,c
                        else if(((r+1)<usedMatrix.size()) && usedMatrix.get(r+1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getColor()==usedMatrix.get(r+1).get(c).getDiceContained().getColor()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOTTO");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getColor()!=usedMatrix.get(r+1).get(c).getDiceContained().getColor())){
                                correctColor=true;
                                break;
                            }
                        }

                        //CASI LIMITE DA INCLUDERE PER CORRETTEZZA
                        if((r-1)<0 && (c-1)<0){} //r-1,c-1
                        else if( ((r-1>0)||(r-1==0)) && ((c-1>0)||(c-1==0)) && usedMatrix.get(r-1).get(c-1).isUsed()){

                            int row_adj=r-1;
                            int col_adj=c-1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                        if((r-1)<0 && (c+1)>usedMatrix.get(r).size()){} //r-1,c+1
                        else if( ((r-1>0)||(r-1==0)) && (c+1)<usedMatrix.get(r).size() && usedMatrix.get(r-1).get(c+1).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c+1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size() && (c-1)<0){} //r+1,c-1
                        else if(((r+1)<usedMatrix.size()) && ((c-1>0)||(c-1==0)) && usedMatrix.get(r+1).get(c-1).isUsed() ){
                            int row_adj=r+1;
                            int col_adj=c-1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctColor=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size() && (c+1)>usedMatrix.get(r).size()){} //r+1,c+1
                        else if(((r+1)<usedMatrix.size()) && ((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r+1).get(c+1).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c+1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
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
    public boolean checkOrthogonalValue(int row, int column){
        boolean correctNumber=false;

        //System.out.println(">>> CELL INPUT (ortogonal value) ["+(row)+"]["+(column)+"]");

        for(int r=0;r<usedMatrix.size();r++){
            for (int c=0;c<usedMatrix.get(r).size();c++){
                if(c==column-1 && r==row-1){
                    if(usedMatrix.get(r).get(c).isUsed()==true){//controllo che effettivamente la cella selezionata sia occupata, non necessario ma per sicurezza

                        if((r-1)<0){} //r-1,c
                        else if( ((r-1>0)||(r-1==0)) && usedMatrix.get(r-1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r-1).get(c).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore SOPRA");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r-1).get(c).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }

                        if((c-1)<0){} //r,c-1
                        else if(((c-1>0)||(c-1==0)) && usedMatrix.get(r).get(c-1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r).get(c-1).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore A SINISTRA");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r).get(c-1).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }
                        if((c+1)>usedMatrix.get(r).size()){} //r,c+1
                        else if(((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r).get(c+1).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r).get(c+1).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso valore A DESTRA");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r).get(c+1).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }
                        if((r+1)>usedMatrix.size()){} //r+1,c
                        else if(((r+1)<usedMatrix.size()) && usedMatrix.get(r+1).get(c).isUsed()){

                            if(usedMatrix.get(r).get(c).getDiceContained().getValue()==usedMatrix.get(r+1).get(c).getDiceContained().getValue()){
                                //System.out.println("La mossa non è valida, ho un dado ortogonale con lo stesso colore SOTTO");
                                break;
                            }
                            else if((usedMatrix.get(r).get(c).getDiceContained().getValue()!=usedMatrix.get(r+1).get(c).getDiceContained().getValue())){
                                correctNumber=true;
                                break;
                            }
                        }

                        //CASI LIMITE DA INCLUDERE PER CORRETTEZZA
                        if((r-1)<0 && (c-1)<0){} //r-1,c-1
                        else if( ((r-1>0)||(r-1==0)) && ((c-1>0)||(c-1==0)) && usedMatrix.get(r-1).get(c-1).isUsed()){

                            int row_adj=r-1;
                            int col_adj=c-1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctNumber=true;
                            break;
                        }
                        if((r-1)<0 && (c+1)>usedMatrix.get(r).size()){} //r-1,c+1
                        else if( ((r-1>0)||(r-1==0)) && (c+1)<usedMatrix.get(r).size() && usedMatrix.get(r-1).get(c+1).isUsed()){
                            int row_adj=r-1;
                            int col_adj=c+1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctNumber=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size() && (c-1)<0){} //r+1,c-1
                        else if(((r+1)<usedMatrix.size()) && ((c-1>0)||(c-1==0)) && usedMatrix.get(r+1).get(c-1).isUsed() ){
                            int row_adj=r+1;
                            int col_adj=c-1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctNumber=true;
                            break;
                        }
                        if((r+1)>usedMatrix.size() && (c+1)>usedMatrix.get(r).size()){} //r+1,c+1
                        else if(((r+1)<usedMatrix.size()) && ((c+1)<usedMatrix.get(r).size()) && usedMatrix.get(r+1).get(c+1).isUsed()){
                            int row_adj=r+1;
                            int col_adj=c+1;

                            //System.out.println("CELL IRRILEVANT ["+(row_adj+1)+"]["+(col_adj+1)+"]");
                            correctNumber=true;
                            break;
                        }

                    }else{
                        System.out.println("La cella ["+(r+1)+"]["+(c+1)+"] non ha nessun dado al suo interno, non posso controllare se ha dadi con colori uguali");
                    }
                }
            }
        }
        return correctNumber;
    }

    public void printMatrixArrayList(){

        for(int i=0;i<usedMatrix.size();i++){
            System.out.println("-----------------------------------------");
            System.out.println("| "+usedMatrix.get(i).get(0).isUsed()+" | "+usedMatrix.get(i).get(1).isUsed()+" | "+usedMatrix.get(i).get(2).isUsed()+" | "+usedMatrix.get(i).get(3).isUsed()+" | "+usedMatrix.get(i).get(4).isUsed()+" |");
        }
        System.out.println("----------------------------------------");
    }

    //******************************REAL GAMEPLAY*************************************************************//

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
                            usedMatrix.get(r).get(c).setDiceContained(dice);
                            usedMatrix.get(r).get(c).setUsed(true);
                            break;
                        }
                        else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK)
                        { //CONTROLLO NON BIANCO

                            ///++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE
                            if(dice.isColorBreaker())
                            {
                                if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);
                                    break;
                                }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {
                                    throw new PlaceDiceException("Shade is different");
                                }
                                //CASO LIMITE COLOR BREAKER - CONTROLLO COLORE
                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE)
                                { //se è una cella colorata inserisco comunque il dado
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);
                                    break;
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE

                            ///++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO
                            if(dice.isNumberBreaker())
                            {
                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                {//CONTROLLO COLORE
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);
                                    break;
                                }else if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                {
                                    throw new PlaceDiceException("Color is different");
                                }
                                //CASO LIMITE NUMBER BREAKER + CONTROLLO SFUMATURA
                                if(usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                { //se è una sfumatura inserisco comunque il dado
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);
                                    break;
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO

                            //CONTROLLI NORMALI

                            if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//CONTROLLO SFUMATURA
                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);
                                break;
                            }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {
                                throw new PlaceDiceException("Shade is different");
                            }
                            if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                            {//CONTROLLO COLORE
                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);
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

                            usedMatrix.get(r).get(c).setDiceContained(dice);
                            usedMatrix.get(r).get(c).setUsed(true);

                            //++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA
                            if(dice.isAdjacencyBreaker())
                            {
                                System.out.println("ADIACENCY - BREAKER (white)");

                                if(checkAdjacency(r+1,c+1)==false)
                                {
                                    //non ho alcun effetto, infatti in questo caso ho una cella bianca staccata da tutto il resto
                                    break;
                                }
                                else if (checkAdjacency(r+1,c+1))
                                {
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("No adjacent dice");
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA

                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE
                            if(dice.isColorBreaker())
                            {
                                System.out.println("COLOR - BREAKER (white)");

                                if(checkAdjacency(r+1,c+1))
                                {//CONTROLLO ADIACENZA OK
                                    if (checkOrthogonalValue(r + 1, c + 1))
                                    {//CONTROLLO NUMERO OK
                                        break;
                                    }else if (checkOrthogonalValue(r + 1, c + 1)==false)
                                    {   //CONTROLLO NUMERO NO
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("Wrong orthogonal value");
                                    }
                                }else if(checkAdjacency(r+1,c+1)==false)
                                { // NO ADIACENZA
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("No adjacent dice");
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE

                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO
                            if(dice.isNumberBreaker())
                            {
                                System.out.println("NUMBER - BREAKER (white)");

                                if(checkAdjacency(r+1,c+1))
                                {//CONTROLLO ADIACENZA OK
                                    if (checkOrthogonalColor(r + 1, c + 1))
                                    {//CONTROLLO COLORE OK
                                        break;
                                    }
                                    else if (checkOrthogonalColor(r + 1, c + 1)==false)
                                    { // NO COLORE
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("Wrong orthogonal color");
                                    }
                                }
                                else if(checkAdjacency(r+1,c+1)==false)
                                { // NO ADIACENZA
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("No adjacent dice");
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO

                            //CONTROLLI NORMALI
                            if(checkAdjacency(r+1,c+1))
                            {//CONTROLLO ADIACENZA OK

                                if (checkOrthogonalColor(r + 1, c + 1) && checkOrthogonalValue(r + 1, c + 1))
                                {//CONTROLLO COLORE/NUMERO OK

                                    if (checkOrthogonalColor(r + 1, c + 1))
                                    {//CONTROLLO COLORE OK
                                        break;
                                    }
                                    else if (checkOrthogonalValue( r + 1, c + 1))
                                    {//CONTROLLO NUMERO OK
                                        break;
                                    }
                                }
                                else if (checkOrthogonalColor(r + 1, c + 1) == false || checkOrthogonalValue(r + 1, c + 1)==false)
                                { // NO COLORE/NUMERO

                                    if (checkOrthogonalColor(r + 1, c + 1)==false)
                                    {//CONTROLLO COLORE NO
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("Wrong orthogonal color");
                                    }
                                    else if (checkOrthogonalValue(r + 1, c + 1)==false)
                                    {//CONTROLLO NUMERO NO
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("Wrong orthogonal value");
                                    }
                                }
                            }
                            else if(checkAdjacency(r+1,c+1)==false)
                            { // NO ADIACENZA
                                usedMatrix.get(r).get(c).setDiceContained(null);
                                usedMatrix.get(r).get(c).setUsed(false);
                                throw new PlaceDiceException("No adjacent dice");
                            }

                        }
                        else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK)
                        {//CASO CELLA NON BIANCA

                            usedMatrix.get(r).get(c).setDiceContained(dice);
                            usedMatrix.get(r).get(c).setUsed(true);

                            //++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO
                            if(dice.isNumberBreaker())
                            {
                                System.out.println("NUMBER - BREAKER (NOT white)");

                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                {//CONTROLLO COLORE
                                    if(checkAdjacency(r+1,c+1))
                                    {//CONTROLLO ADIACENZA OK
                                        if (checkOrthogonalColor(r + 1, c + 1))
                                        {//CONTROLLO COLORE ORTOGONALE OK
                                            break;
                                        }
                                        else if (checkOrthogonalColor(r + 1, c + 1)==false)
                                        { // NO COLORE ORTOGONALE
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal color");
                                        }
                                    }
                                    else if(checkAdjacency(r+1,c+1)==false)
                                    { // NO ADIACENZA
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("No adjacent dice");
                                    }
                                }
                                else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                {//NO COLORE CELLA
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("wrong color");
                                }

                                if(usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA (caso limite)

                                    System.out.println("NUMBER - BREAKER (NOT white) --> SPECIAL CASE");

                                    if(checkAdjacency(r+1,c+1))
                                    {//CONTROLLO ADIACENZA OK
                                        if (checkOrthogonalColor(r + 1, c + 1))
                                        {//CONTROLLO COLORE ORTOGONALE OK
                                            break;
                                        }
                                        else if (checkOrthogonalColor(r + 1, c + 1)==false)
                                        { // NO COLORE ORTOGONALE
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal color");
                                        }
                                    }
                                    else if(checkAdjacency(r+1,c+1)==false)
                                    { // NO ADIACENZA
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("No adjacent dice");
                                    }
                                }
                            }
                            //++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO NUMERO

                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO COLORE
                            if(dice.isColorBreaker())
                            {
                                System.out.println("COLOR - BREAKER (NOT white)");

                                if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA
                                    if(checkAdjacency(r+1,c+1))
                                    {//CONTROLLO ADIACENZA OK
                                        if (checkOrthogonalValue(r + 1, c + 1))
                                        {//CONTROLLO NUMERO ORTOGONALE OK
                                            break;
                                        }
                                        else if (checkOrthogonalValue(r + 1, c + 1)==false)
                                        {   //CONTROLLO NUMERO ORTOGONALE NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal value");
                                        }
                                    }
                                    else if(checkAdjacency(r+1,c+1)==false)
                                    { // NO ADIACENZA
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("No adjacent dice");
                                    }
                                }else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//NO SFUMATURA
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("Wrong shade");
                                }

                                if(usedMatrix.get(r).get(c).getColor() != Color.SHADE)
                                {//CONTROLLO COLORE (caso limite)

                                    System.out.println("COLOR - BREAKER (NOT white) --> SPECIAL CASE");

                                    if(checkAdjacency(r+1,c+1))
                                    {//CONTROLLO ADIACENZA OK
                                        if (checkOrthogonalValue(r + 1, c + 1))
                                        {//CONTROLLO NUMERO OK
                                            break;
                                        }
                                        else if (checkOrthogonalValue(r + 1, c + 1)==false)
                                        {   //CONTROLLO VALORE NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal value");
                                        }
                                    }
                                    else if(checkAdjacency(r+1,c+1)==false)
                                    { // NO ADIACENZA
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("No adjacent dice");
                                    }
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
                                        break;
                                    }
                                    else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                    {//NO SFUMATURA
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("Wrong shade");
                                    }

                                    if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                    {//CONTROLLO COLORE
                                        break;
                                    }
                                    else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                    {//NO COLORE CELLA
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        throw new PlaceDiceException("Wrong color");
                                    }
                                }
                                else if (checkAdjacency(r+1,c+1))
                                {
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("No adjacent dice");
                                }
                            }
                            ///++++++++++++++++++++++++++++++++++++++++++++++++++++EVITO CONTROLLO ADIACENZA

                            //CONTROLLI NORMALI

                            if (checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//CONTROLLO SFUMATURA OK

                                if(checkAdjacency(r+1,c+1))
                                {//CONTROLLO ADIACENZA

                                    if (checkOrthogonalColor(r + 1, c + 1) && checkOrthogonalValue(r + 1, c + 1))
                                    {///CONTROLLO COLORE/NUMERO OK
                                        break;
                                    }
                                    else if (checkOrthogonalColor(r + 1, c + 1) == false || checkOrthogonalValue(r + 1, c + 1)==false )
                                    {// COLORE/NUMERO NO

                                        if (checkOrthogonalColor(r + 1, c + 1)==false)
                                        {//CONTROLLO COLORE NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal color");
                                        }
                                        else if (checkOrthogonalValue(r + 1, c + 1)==false)
                                        {//CONTROLLO NUMERO NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal value");
                                        }
                                    }
                                }
                                else if(checkAdjacency(r+1,c+1)==false)
                                { // ADIACENZA NO
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("No adjacent dice");
                                }
                            }
                            else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//NO SFUMATURA
                                usedMatrix.get(r).get(c).setDiceContained(null);
                                usedMatrix.get(r).get(c).setUsed(false);
                                throw new PlaceDiceException("Shade is different");
                            }

                            //-------

                            if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                            {//CONTROLLO COLORE CELLA

                                if(checkAdjacency(r+1,c+1))
                                {//CONTROLLO ADIACENZA

                                    if (checkOrthogonalColor(r + 1, c + 1) && checkOrthogonalValue(r + 1, c + 1) )
                                    {//CONTROLLO COLORE/NUMERO
                                        break;
                                    }
                                    else if (checkOrthogonalColor(r + 1, c + 1) == false || checkOrthogonalValue(r + 1, c + 1)==false)
                                    {//NO COLORE/NUMERO

                                        if (checkOrthogonalColor(r + 1, c + 1)==false)
                                        {//CONTROLLO COLORE NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal color");
                                        }
                                        else if (checkOrthogonalValue(r + 1, c + 1)==false)
                                        {//CONTROLLO NUMERO NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            throw new PlaceDiceException("Wrong orthogonal value");
                                        }
                                    }
                                }
                                else if(checkAdjacency(r+1,c+1)==false)
                                {// NO ADIACENZA
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    throw new PlaceDiceException("There is no adjacent dice");
                                }
                            }
                            else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                            {//NO COLORE CELLA
                                usedMatrix.get(r).get(c).setDiceContained(null);
                                usedMatrix.get(r).get(c).setUsed(false);
                                throw new PlaceDiceException("Color is not equal");
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

}

