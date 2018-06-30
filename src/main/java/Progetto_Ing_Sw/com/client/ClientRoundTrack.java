package Progetto_Ing_Sw.com.client;

import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.IllegalDiceException;
import Progetto_Ing_Sw.com.server.Model.Table;

import java.util.ArrayList;

public class ClientRoundTrack {   //Implementata come singleton
    private static ClientRoundTrack ourInstance = new ClientRoundTrack();
    private int roundNumber;    //lasciato per compatibilità
    private ArrayList<ArrayList<Dice>> diceRemained;    //ciascun indice contiene un ArrayList con dentro i dadi avanzati in quel turno

    public static ClientRoundTrack getInstance() {
        return ourInstance;
    }

    private ClientRoundTrack() {
        roundNumber=0;  //Il primo turno dev'essere il numero 0 per avere equivalenza con gli indici, vedi sopra
        diceRemained=new ArrayList<ArrayList<Dice>>();
        for (int index=0;index<10;index++)
            diceRemained.add(new ArrayList<>());
    }


    public ArrayList<Dice> getDiceRemained(int roundNumber){    //restituisce una copia dell'ArrayList contenete i dadi avanzati al turno numero roundNumber
        ArrayList<Dice> diceArrayList=new ArrayList<>();        //non viene restituito un riferimento all'oggetto originale per evitare modifiche all'esterno di questa classe

        for(Dice dice : diceRemained.get(roundNumber)){
            diceArrayList.add(dice);
        }

        return diceArrayList;
    }





}
