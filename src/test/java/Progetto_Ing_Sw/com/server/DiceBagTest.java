package Progetto_Ing_Sw.com.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class DiceBagTest {
    private DiceBag diceBag;
    @Before
    public void before() {
        diceBag = new DiceBag();
    }

@Test
    public void drawTest(){
        Dice dice=null;
        dice=diceBag.diceDraw();
    Assert.assertNotEquals(null,dice);
    System.out.print("Color: " + dice.getColor() + " Value: " + dice.getValue());
}

//TODO: testare il decremento delle variabili che tengono il conteggio dei dadi per colore

}
