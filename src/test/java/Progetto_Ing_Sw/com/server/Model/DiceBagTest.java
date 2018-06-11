package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.Dice;
import Progetto_Ing_Sw.com.server.Model.DiceBag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.SplittableRandom;

import static Progetto_Ing_Sw.com.server.Model.Color.*;

public class DiceBagTest {
    private DiceBag diceBag;
    @Before
    public void before() {
        diceBag = new DiceBag();
    }

@Test
    public void drawTest(){
        System.out.println("Begin drawTest");
        Dice dice=null;
        int color;
        dice=diceBag.diceDraw();
        Assert.assertNotEquals(null,dice);
        color=dice.getColor();
        System.out.println("drawTest:");
        System.out.print("Color: " + dice.getColor());  //lasciare qui altrimenti se il test fallisce non si sa quale colore ha generato l'errore
        switch (color){
            case RED:
                Assert.assertEquals(17, diceBag.getNumDiceRed());   //controlla il decremento dell'attributo relativo al colore del dado
                break;
            case BLUE:
                Assert.assertEquals(17, diceBag.getNumDiceBlue());
                break;
            case PURPLE:
                Assert.assertEquals(17, diceBag.getNumDicePurple());
                break;
            case YELLOW:
                Assert.assertEquals(17, diceBag.getNumDiceYellow());
                break;
            case GREEN:
                Assert.assertEquals(17, diceBag.getNumDiceGreen());
                break;
    }
    System.out.println(" Value: " + dice.getValue());
}

@Test
    public void multipleDrawTest(){ //testa estrazione multipla con un numero casuale di dadi da estrarre
        System.out.println("Begin multipleDrawTest");
        SplittableRandom splittableRandom=new SplittableRandom();
        int numDiceRed=0, numDiceBlue=0, numDicePurple=0, numDiceYellow=0, numDiceGreen=0;
        int numDiceToDraw=splittableRandom.nextInt(1,91);   //l'estremo superiore è escluso, estrae tra 1 e 90 compresi
        ArrayList<Dice> diceDrawn=diceBag.diceDraw(numDiceToDraw);
        Assert.assertEquals(numDiceToDraw,diceDrawn.size());    //L'ArrayList deve contenere il numero di dadi richiesti
        for(Dice dice : diceDrawn){     //conta i dadi estratti divisi per colore
            switch(dice.getColor()){
                case RED:
                    numDiceRed++;
                    break;
                case Color.BLUE:
                    numDiceBlue++;
                    break;
                case Color.PURPLE:
                    numDicePurple++;
                    break;
                case Color.YELLOW:
                    numDiceYellow++;
                    break;
                case Color.GREEN:
                    numDiceGreen++;
                    break;
            }
        }

        Assert.assertEquals(18-numDiceRed, diceBag.getNumDiceRed());    //controlla che le variabili che tengono
        Assert.assertEquals(18-numDiceBlue, diceBag.getNumDiceBlue());  //il conto dei dadi nel sacchetto
        Assert.assertEquals(18-numDicePurple, diceBag.getNumDicePurple());  //siano state decrementtate correttamente
        Assert.assertEquals(18-numDiceYellow, diceBag.getNumDiceYellow());
        Assert.assertEquals(18-numDiceGreen, diceBag.getNumDiceGreen());
    }

    @Test
    public void drawAllTest() {  //testa l'estrazione di tutti i dadi dal sacchetto
        System.out.println("Begin drawAllTest");
        int numDiceRed = 0, numDiceBlue = 0, numDicePurple = 0, numDiceYellow = 0, numDiceGreen = 0;
        ArrayList<Dice> diceDrawn = diceBag.diceDraw(90);
        Assert.assertEquals(90, diceDrawn.size());    //L'ArrayList deve contenere tutti i dadi
        for (Dice dice : diceDrawn) {     //conta i dadi estratti divisi per colore
            switch (dice.getColor()) {
                case RED:
                    numDiceRed++;
                    break;
                case Color.BLUE:
                    numDiceBlue++;
                    break;
                case Color.PURPLE:
                    numDicePurple++;
                    break;
                case Color.YELLOW:
                    numDiceYellow++;
                    break;
                case Color.GREEN:
                    numDiceGreen++;
                    break;
            }

        }

        Assert.assertEquals(18, numDiceBlue);   //Controlla che siano stati estratti tutti i dadi
        Assert.assertEquals(18, numDiceGreen);  //divisi per colore
        Assert.assertEquals(18, numDicePurple);
        Assert.assertEquals(18, numDiceRed);
        Assert.assertEquals(18, numDiceYellow);

        Assert.assertEquals(0, diceBag.getNumDiceRed());    //controlla che non siano
        Assert.assertEquals(0, diceBag.getNumDiceBlue());  //rimasti dadi nel sacchetto
        Assert.assertEquals(0, diceBag.getNumDicePurple());
        Assert.assertEquals(0, diceBag.getNumDiceYellow());
        Assert.assertEquals(0, diceBag.getNumDiceGreen());
    }

    @Test
    public void returnDiceExceptionTest(){
        System.out.println("Begin returnDiceExceptionTest");
        SplittableRandom splittableRandom=new SplittableRandom();       //Usato per generare valori casuali dei dadi
        Dice diceRed=new Dice(splittableRandom.nextInt(1,7),Color.RED); //Come al solito il primo parametro di nextInt è l'estremo inferiore incluso nell'intervallo mentre il secondo è l'estremo superiore ESCLUSO dall'intervallo (genera interi n tali che 1<=n<7)
        Dice diceBlue=new Dice(splittableRandom.nextInt(1,7), Color.BLUE);
        Dice dicePurple=new Dice(splittableRandom.nextInt(1,7),Color.PURPLE);
        Dice diceYellow=new Dice(splittableRandom.nextInt(1,7), Color.YELLOW);
        Dice diceGreen=new Dice(splittableRandom.nextInt(1,7),Color.GREEN);

        try {
            diceBag.returnDice(diceRed);
            Assert.fail("Dado rosso inserito nonostante il sacchetto sia pieno!");          //Il sacchetto è pieno, non dovrebbe lasciar reinserire il dado
        } catch (IllegalDiceException e) {
            Assert.assertTrue(true);    //Non esiste Assert.pass()...
        }

        try {
            diceBag.returnDice(diceBlue);
            Assert.fail("Dado blu inserito nonostante il sacchetto sia pieno!");          //Il sacchetto è pieno, non dovrebbe lasciar reinserire il dado
        } catch (IllegalDiceException e) {
            Assert.assertTrue(true);    //Non esiste Assert.pass()...
        }

        try {
            diceBag.returnDice(dicePurple);
            Assert.fail("Dado viola inserito nonostante il sacchetto sia pieno!");          //Il sacchetto è pieno, non dovrebbe lasciar reinserire il dado
        } catch (IllegalDiceException e) {
            Assert.assertTrue(true);    //Non esiste Assert.pass()...
        }

        try {
            diceBag.returnDice(diceYellow);
            Assert.fail("Dado giallo inserito nonostante il sacchetto sia pieno!");          //Il sacchetto è pieno, non dovrebbe lasciar reinserire il dado
        } catch (IllegalDiceException e) {
            Assert.assertTrue(true);    //Non esiste Assert.pass()...
        }

        try {
            diceBag.returnDice(diceGreen);
            Assert.fail("Dado verde inserito nonostante il sacchetto sia pieno!");          //Il sacchetto è pieno, non dovrebbe lasciar reinserire il dado
        } catch (IllegalDiceException e) {
            Assert.assertTrue(true);    //Non esiste Assert.pass()...
        }
    }

    @Test
    public void ReturnDiceTest(){
        System.out.println("Begin ReturnDiceTest");
            SplittableRandom splittableRandom=new SplittableRandom();       //Usato per generare valori casuali dei dadi
            Dice diceRed=new Dice(splittableRandom.nextInt(1,7),Color.RED); //Come al solito il primo parametro di nextInt è l'estremo inferiore incluso nell'intervallo mentre il secondo è l'estremo superiore ESCLUSO dall'intervallo (genera interi n tali che 1<=n<7)
            Dice diceBlue=new Dice(splittableRandom.nextInt(1,7), Color.BLUE);
            Dice dicePurple=new Dice(splittableRandom.nextInt(1,7),Color.PURPLE);
            Dice diceYellow=new Dice(splittableRandom.nextInt(1,7), Color.YELLOW);
            Dice diceGreen=new Dice(splittableRandom.nextInt(1,7),Color.GREEN);
            diceBag.diceDraw(90);   //Pesca tutti i dadi per evitare inconsistenze nei valori dei contatori dei dadi per colore

            try{
                diceBag.returnDice(diceRed);
            }
            catch (IllegalDiceException e){
                Assert.fail("Dado rosso non reinserito nonostante il sacchetto sia vuoto!");
            }

        try{
            diceBag.returnDice(diceBlue);
        }
        catch (IllegalDiceException e){
            Assert.fail("Dado blu non reinserito nonostante il sacchetto sia vuoto!");
        }

        try{
            diceBag.returnDice(dicePurple);
        }
        catch (IllegalDiceException e){
            Assert.fail("Dado viola non reinserito nonostante il sacchetto sia vuoto!");
        }

        try{
            diceBag.returnDice(diceYellow);
        }
        catch (IllegalDiceException e){
            Assert.fail("Dado giallo non reinserito nonostante il sacchetto sia vuoto!");
        }

        try{
            diceBag.returnDice(diceGreen);
        }
        catch (IllegalDiceException e){
            Assert.fail("Dado verde non reinserito nonostante il sacchetto sia vuoto!");
        }

    }
}
