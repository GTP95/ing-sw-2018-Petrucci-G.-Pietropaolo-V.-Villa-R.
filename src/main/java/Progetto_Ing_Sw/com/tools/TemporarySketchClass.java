package Progetto_Ing_Sw.com.tools;

public class TemporarySketchClass {
    /*
    //***TEST*** metodo finale di inserimento dei dadi, con Commenti per vedere in che ramo sono finito--------------------------------------------------
    public ArrayList<ArrayList<MatrixCell>> insertDiceWithText(ArrayList<ArrayList<MatrixCell>> usedMatrix, int row, int column, Dice dice){

        if(matrixNotEmpty(usedMatrix)==false){ //CONTROLLO PRIMO TURNO

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
        else if (matrixNotEmpty(usedMatrix)==true)
        { //Mosse successive alla prima

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {

                    if (usedMatrix.get(r).get(c).isUsed() == false && c == column - 1 && r == row - 1)
                    { //CASO CELLA LIBERA

                            if (usedMatrix.get(r).get(c).getColor() == Color.BLANK)
                            {//CASO CELLA BIANCA

                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);

                                if(checkAdjacency(usedMatrix,r+1,c+1))
                                {//CONTROLLO ADIACENZA OK

                                    if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) || checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                    {//CONTROLLO COLORE/NUMERO OK

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1))
                                        {//CONTROLLO COLORE OK
                                            System.out.println("DADO INSERITO CORRETTAMENTE (after  adj+color control (MOSSA SUCCESSIVA-  white -)");
                                            break;
                                        }
                                        else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                        {//CONTROLLO NUMERO OK
                                            System.out.println("DADO INSERITO CORRETTAMENTE (after  adj+number control (MOSSA SUCCESSIVA-  white -)");
                                            break;
                                        }
                                    }
                                    else if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) == false || checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                    { // NO COLORE/NUMERO

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1)==false)
                                        {//CONTROLLO COLORE NO
                                            System.out.println("Non stai rispettando l'ortogonalità dei colori (MOSSA SUCCESSIVA-  white -)");
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                        else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                        {//CONTROLLO NUMERO NO
                                            System.out.println("Non stai rispettando l'ortogonalità dei numeri (MOSSA SUCCESSIVA-  white -)");
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                    }
                                }
                                else if(checkAdjacency(usedMatrix,r+1,c+1)==false)
                                { // NO ADIACENZA
                                    System.out.println("Non stai rispettando le regole di adiacenza");
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    break;
                                }

                            } else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK)
                            {//CASO CELLA NON BIANCA

                                if (checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//CONTROLLO SFUMATURA OK

                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);

                                    if(checkAdjacency(usedMatrix,r+1,c+1))
                                    {//CONTROLLO ADIACENZA

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) || checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                        {///CONTROLLO COLORE/NUMERO OK

                                            if (checkOrtogonalColor(usedMatrix, r + 1, c + 1))
                                            {//CONTROLLO COLORE OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+color control (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                break;
                                            }
                                            else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                            {//CONTROLLO NUMERO OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+number control (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                break;
                                            }
                                        }
                                        else if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) == false || checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false )
                                        {// COLORE/NUMERO NO

                                            if (checkOrtogonalColor(usedMatrix, r + 1, c + 1)==false)
                                            {//CONTROLLO COLORE NO
                                                System.out.println("Non stai rispettando le regole di ortogonalità dei colori (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                usedMatrix.get(r).get(c).setDiceContained(null);
                                                usedMatrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                            else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                            {//CONTROLLO NUMERO NO
                                                System.out.println("Non stai rispettando l'ortogonalità dei numeri (MOSSA SUCCESSIVA - not white/shade ON -)");
                                                usedMatrix.get(r).get(c).setDiceContained(null);
                                                usedMatrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                        }
                                    }
                                    else if(checkAdjacency(usedMatrix,r+1,c+1)==false)
                                    { // ADIACENZA NO
                                        System.out.println("Non stai rispettando le regole di adiacenza");
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        break;
                                    }
                                }
                                else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                                {//NO SFUMATURA
                                    System.out.println("Il dado inserito non rispetta la sfumatura data");
                                    break;
                                }

                                //-------

                                if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                                {//CONTROLLO COLORE CELLA
                                    usedMatrix.get(r).get(c).setDiceContained(dice);
                                    usedMatrix.get(r).get(c).setUsed(true);

                                    if(checkAdjacency(usedMatrix,r+1,c+1))
                                    {//CONTROLLO ADIACENZA

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) || checkOrtogonalValue(usedMatrix, r + 1, c + 1) )
                                        {//CONTROLLO COLORE/NUMERO

                                            if (checkOrtogonalColor(usedMatrix, r + 1, c + 1))
                                            {//CONTROLLO COLORE OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+color control (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                break;
                                            }
                                            else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                            {//CONTROLLO NUMERO OK
                                                System.out.println("DADO INSERITO CORRETTAMENTE after  adj+number control (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                break;
                                            }

                                        }
                                        else if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) == false || checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                        {//NO COLORE/NUMERO

                                            if (checkOrtogonalColor(usedMatrix, r + 1, c + 1)==false)
                                            {//CONTROLLO COLORE NO
                                                System.out.println("Non stai rispettando le regole di ortogonalità dei colori (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                usedMatrix.get(r).get(c).setDiceContained(null);
                                                usedMatrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                            else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                            {//CONTROLLO NUMERO NO
                                                System.out.println("Non stai rispettando l'ortogonalità dei numeri (MOSSA SUCCESSIVA - not white/shade OFF/color ON -)");
                                                usedMatrix.get(r).get(c).setDiceContained(null);
                                                usedMatrix.get(r).get(c).setUsed(false);
                                                break;
                                            }
                                        }
                                    }
                                    else if(checkAdjacency(usedMatrix,r+1,c+1)==false)
                                    {// NO ADIACENZA
                                        System.out.println("Non stai rispettando le regole di adiacenza");
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        break;
                                    }
                                }
                                else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                                {//NO COLORE CELLA
                                    System.out.println("Il dado inserito non rispetta il colore della cella");
                                    break;
                                }
                            }
                    }
                    else if (usedMatrix.get(r).get(c).isUsed() == true && c == column - 1 && r == row - 1)
                    {//CASO CELLA OCCUPATA
                        System.out.println("La casella è già occupata! Salti il turno");
                        break;
                    }
                }
            }
        }
        return usedMatrix;
    }
    public ArrayList<ArrayList<MatrixCell>> insertDiceWithoutExlusions(ArrayList<ArrayList<MatrixCell>> usedMatrix, int row, int column, Dice dice){

        if(matrixNotEmpty(usedMatrix)==false){ //CONTROLLO PRIMO TURNO

            for(int r=0;r<row;r++) {
                for (int c = 0; c < column; c++) {

                    if (usedMatrix.get(r).get(c).isOnBorder() == true && c == column - 1 && r == row - 1) { //CONTROLLO BORDI, CELLA CORRETTA

                        if (usedMatrix.get(r).get(c).getColor() == Color.BLANK) { //CONROLLO BIANCO
                            usedMatrix.get(r).get(c).setDiceContained(dice);
                            usedMatrix.get(r).get(c).setUsed(true);
                            break;
                        }

                        else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK){ //CONTROLLO NON BIANCO

                            if(checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE){//CONTROLLO SFUMATURA
                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);
                                break;
                            }else if(checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE){
                                break;
                            }
                            if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor()){//CONTROLLO COLORE
                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);
                                break;
                            }else if(usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor()){
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
        else if (matrixNotEmpty(usedMatrix)==true)
        { //Mosse successive alla prima

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {

                    if (usedMatrix.get(r).get(c).isUsed() == false && c == column - 1 && r == row - 1)
                    { //CASO CELLA LIBERA

                        if (usedMatrix.get(r).get(c).getColor() == Color.BLANK)
                        {//CASO CELLA BIANCA

                            usedMatrix.get(r).get(c).setDiceContained(dice);
                            usedMatrix.get(r).get(c).setUsed(true);

                            if(checkAdjacency(usedMatrix,r+1,c+1))
                            {//CONTROLLO ADIACENZA OK

                                if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) || checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                {//CONTROLLO COLORE/NUMERO OK

                                    if (checkOrtogonalColor(usedMatrix, r + 1, c + 1))
                                    {//CONTROLLO COLORE OK
                                        break;
                                    }
                                    else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                    {//CONTROLLO NUMERO OK
                                        break;
                                    }
                                }
                                else if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) == false || checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                { // NO COLORE/NUMERO

                                    if (checkOrtogonalColor(usedMatrix, r + 1, c + 1)==false)
                                    {//CONTROLLO COLORE NO
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        break;
                                    }
                                    else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                    {//CONTROLLO NUMERO NO
                                        usedMatrix.get(r).get(c).setDiceContained(null);
                                        usedMatrix.get(r).get(c).setUsed(false);
                                        break;
                                    }
                                }
                            }
                            else if(checkAdjacency(usedMatrix,r+1,c+1)==false)
                            { // NO ADIACENZA
                                System.out.println("Non stai rispettando le regole di adiacenza");
                                usedMatrix.get(r).get(c).setDiceContained(null);
                                break;
                            }

                        } else if (usedMatrix.get(r).get(c).getColor() != Color.BLANK)
                        {//CASO CELLA NON BIANCA

                            if (checkShade(usedMatrix.get(r).get(c), dice) == true && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//CONTROLLO SFUMATURA OK

                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);

                                if(checkAdjacency(usedMatrix,r+1,c+1))
                                {//CONTROLLO ADIACENZA

                                    if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) || checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                    {///CONTROLLO COLORE/NUMERO OK

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1))
                                        {//CONTROLLO COLORE OK
                                            break;
                                        }
                                        else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                        {//CONTROLLO NUMERO OK
                                            break;
                                        }
                                    }
                                    else if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) == false || checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false )
                                    {// COLORE/NUMERO NO

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1)==false)
                                        {//CONTROLLO COLORE NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                        else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                        {//CONTROLLO NUMERO NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                    }
                                }
                                else if(checkAdjacency(usedMatrix,r+1,c+1)==false)
                                { // ADIACENZA NO
                                    System.out.println("Non stai rispettando le regole di adiacenza");
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    break;
                                }
                            }
                            else if (checkShade(usedMatrix.get(r).get(c), dice) == false && usedMatrix.get(r).get(c).getColor() == Color.SHADE)
                            {//NO SFUMATURA
                                System.out.println("Il dado inserito non rispetta la sfumatura data");
                                break;
                            }

                            //-------

                            if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() == dice.getColor())
                            {//CONTROLLO COLORE CELLA
                                usedMatrix.get(r).get(c).setDiceContained(dice);
                                usedMatrix.get(r).get(c).setUsed(true);

                                if(checkAdjacency(usedMatrix,r+1,c+1))
                                {//CONTROLLO ADIACENZA

                                    if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) || checkOrtogonalValue(usedMatrix, r + 1, c + 1) )
                                    {//CONTROLLO COLORE/NUMERO

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1))
                                        {//CONTROLLO COLORE OK
                                            break;
                                        }
                                        else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1))
                                        {//CONTROLLO NUMERO OK
                                            break;
                                        }

                                    }
                                    else if (checkOrtogonalColor(usedMatrix, r + 1, c + 1) == false || checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                    {//NO COLORE/NUMERO

                                        if (checkOrtogonalColor(usedMatrix, r + 1, c + 1)==false)
                                        {//CONTROLLO COLORE NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                        else if (checkOrtogonalValue(usedMatrix, r + 1, c + 1)==false)
                                        {//CONTROLLO NUMERO NO
                                            usedMatrix.get(r).get(c).setDiceContained(null);
                                            usedMatrix.get(r).get(c).setUsed(false);
                                            break;
                                        }
                                    }
                                }
                                else if(checkAdjacency(usedMatrix,r+1,c+1)==false)
                                {// NO ADIACENZA
                                    usedMatrix.get(r).get(c).setDiceContained(null);
                                    usedMatrix.get(r).get(c).setUsed(false);
                                    break;
                                }
                            }
                            else if (usedMatrix.get(r).get(c).getColor() != Color.SHADE && usedMatrix.get(r).get(c).getColor() != dice.getColor())
                            {//NO COLORE CELLA
                                break;
                            }
                        }
                    }
                    else if (usedMatrix.get(r).get(c).isUsed() == true && c == column - 1 && r == row - 1)
                    {//CASO CELLA OCCUPATA
                        System.out.println("La casella è già occupata! Salti il turno");
                        break;
                    }
                }
            }
        }
        return usedMatrix;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
