package Progetto_Ing_Sw.com.server.Model;

import java.util.ArrayList;

/**
 * This class implements the RoundTrack, the main object that counts the number of turns and has all the dice remained
 * at the end of the round
 * @author Giacomo Tommaso Petrucci
 */
public class RoundTrack {   //Implementata come singleton
    private static RoundTrack ourInstance = new RoundTrack();
    private int roundNumber;    //Il primo turno è il numero 0 per avere corrispondenza tra turni ed indici di diceRemained
    private ArrayList<ArrayList<Dice>> diceRemained;    //ciascun indice contiene un ArrayList con dentro i dadi avanzati in quel turno

    public static RoundTrack getInstance() {
        return ourInstance;
    }

    private RoundTrack() {
        roundNumber=0;  //Il primo turno dev'essere il numero 0 per avere equivalenza con gli indici, vedi sopra
        diceRemained=new ArrayList<ArrayList<Dice>>();
        for (int index=0;index<10;index++)
            diceRemained.add(new ArrayList<>());
    }

    /**
     * @return it returns the number of the actual round
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     *
     * @param roundNumber this parameter represents the round number that i want to analyze
     * @return it returns a copy of the Dice Remained ArrayList at the roundNumber; it avoids direct access at the object
     */
    public ArrayList<Dice> getDiceRemained(int roundNumber){    //restituisce una copia dell'ArrayList contenete i dadi avanzati al turno numero roundNumber
        ArrayList<Dice> diceArrayList=new ArrayList<>();        //non viene restituito un riferimento all'oggetto originale per evitare modifiche all'esterno di questa classe

        for(Dice dice : diceRemained.get(roundNumber)){
            diceArrayList.add(dice);
        }

        return diceArrayList;
    }

    /**
     * This method adds dice at the round track at the end the RoundNumber
     * @param roundNumber it's the round number that has been chosen
     * @param dice it's the dice added
     */
    public void addRemainedDice(int roundNumber, Dice dice){
        diceRemained.get(roundNumber).add(dice);
    }

    /**
     * this method increments the number of round; if is > 10, this method end the game
     */
    public int incrementRound(){
        if(roundNumber<11) roundNumber++;   //Ci sono al massimo 10 round, mitiga l'effetto di eventuali bug
        return roundNumber;
    }

    /**
     * this method change a dice on the round track with a round in the draft pool
     * @param diceToPlace it's the dice that i have to place in the draft pool
     * @param diceToGet it's the dice that i take from the round track
     * @return it returns a dice from the round track
     * @throws IllegalDiceException
     */
    public Dice swapDice(Dice diceToPlace, Dice diceToGet) throws IllegalDiceException {    //Scambia uno dei dadi pescati con uno presente sulla roundTrack, usato per implementare una toolCard
        Boolean diceToPlaceExists=false;

        for (Dice dice : Table.getOurInstance().getDrawnDice()){    //controlla che il dado che si vuole piazzare sulla roundTrack sia tra quelli pescati
            if(dice.equals(diceToPlace)){
                diceToPlaceExists=true;
                break;
            }
        }
        if(!diceToPlaceExists){
            throw new IllegalDiceException();
        }

        for(int index=0;index<getRoundNumber();index++){    //controlla che il dado che si vuole togliere dalla roundTrack sia effettivamente presente sulla roundTrack
            for(Dice dice : diceRemained.get(index)){
                if(dice.equals(diceToGet)){
                    diceRemained.remove(diceToGet);
                    return diceToGet;
                }
            }
        }
        throw new IllegalDiceException();
    }
}
