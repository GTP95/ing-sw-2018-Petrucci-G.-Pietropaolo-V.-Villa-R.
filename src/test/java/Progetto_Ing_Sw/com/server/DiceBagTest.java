package Progetto_Ing_Sw.com.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class DiceBagTest {
    private DiceBag diceBag;
    @Before
    public void before() {
        diceBag = new DiceBag();
    }

@Test
    public void drawTest(){
        Dice dice=null;
        int color;
        dice=diceBag.diceDraw();
        Assert.assertNotEquals(null,dice);
        color=dice.getColor();
        System.out.print("Color: " + dice.getColor());  //lasciare qui altrimenti se il test fallisce non si sa quale colore ha generato l'errore
        switch (color){
            case 0:
                Assert.assertEquals(17, diceBag.getNumDiceRed());   //controlla il decremento dell'attributo relativo al colore del dado
                break;
            case 1:
                Assert.assertEquals(17, diceBag.getNumDiceBlue());
                break;
            case 2:
                Assert.assertEquals(17, diceBag.getNumDicePurple());
                break;
            case 3:
                Assert.assertEquals(17, diceBag.getNumDiceYellow());
                break;
            case 4:
                Assert.assertEquals(17, diceBag.getNumDiceGreen());
                break;
    }
    System.out.println(" Value: " + dice.getValue());
}

@Test
    public void multipleDrawTest(){ //testa estrazione multipla con un numero casuale di dadi da estrarre
        SplittableRandom splittableRandom=new SplittableRandom();
        int numDiceRed=0, numDiceBlue=0, numDicePurple=0, numDiceYellow=0, numDiceGreen=0;
        int numDiceToDraw=splittableRandom.nextInt(1,91);   //l'estremo superiore è escluso, estrae tra 1 e 90 compresi
        ArrayList<Dice> diceDrawn=diceBag.diceDraw(numDiceToDraw);
        Assert.assertEquals(numDiceToDraw,diceDrawn.size());    //L'ArrayList deve contenere il numero di dadi richiesti
        for(Dice dice : diceDrawn){     //conta i dadi estratti divisi per colore
            switch(dice.getColor()){
                case 0:
                    numDiceRed++;
                    break;
                case 1:
                    numDiceBlue++;
                    break;
                case 2:
                    numDicePurple++;
                    break;
                case 3:
                    numDiceYellow++;
                    break;
                case 4:
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
        int numDiceRed = 0, numDiceBlue = 0, numDicePurple = 0, numDiceYellow = 0, numDiceGreen = 0;
        ArrayList<Dice> diceDrawn = diceBag.diceDraw(90);
        Assert.assertEquals(90, diceDrawn.size());    //L'ArrayList deve contenere tutti i dadi
        for (Dice dice : diceDrawn) {     //conta i dadi estratti divisi per colore
            switch (dice.getColor()) {
                case 0:
                    numDiceRed++;
                    break;
                case 1:
                    numDiceBlue++;
                    break;
                case 2:
                    numDicePurple++;
                    break;
                case 3:
                    numDiceYellow++;
                    break;
                case 4:
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

}
