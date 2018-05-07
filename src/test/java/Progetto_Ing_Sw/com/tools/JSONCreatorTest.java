package Progetto_Ing_Sw.com.tools;

import Progetto_Ing_Sw.com.server.Color;
import Progetto_Ing_Sw.com.server.PrivateObjectiveCard;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static Progetto_Ing_Sw.com.tools.JSONCreator.*;

public class JSONCreatorTest {

    @Test
    public void generateJSONTest(){
        PrivateObjectiveCard card=new PrivateObjectiveCard(Color.RED);
        printJSON(card);
    }

    @Test
    public void saveJSONtest(){   //java.io.FileNotFoundException
        PrivateObjectiveCard card=new PrivateObjectiveCard(Color.RED);
        try {
            saveJSON(card, "PrivateObjectiveCardRed.json");
        }
        catch (IOException e){
            e.printStackTrace();    //TODO: controllare alternative
            Assert.fail();
        }

    }

    @Test
    public void privateObjectiveCardLoaderTest() {
        String privateObjectiveCardsPath="Resources/Cards/PrivateObjectiveCards/";

        try {
            PrivateObjectiveCard privateObjectiveCardYellow=privateObjectiveCardLoader(privateObjectiveCardsPath + "PrivateObjectiveCardYellow.json");
            Assert.assertEquals(Color.YELLOW, privateObjectiveCardYellow.getColor());

            PrivateObjectiveCard privateObjectiveCardRed=privateObjectiveCardLoader(privateObjectiveCardsPath + "PrivateObjectiveCardRed.json");
            Assert.assertEquals(Color.RED, privateObjectiveCardRed.getColor());

            PrivateObjectiveCard privateObjectiveCardGreen=privateObjectiveCardLoader(privateObjectiveCardsPath + "PrivateObjectiveCardGreen.json");
            Assert.assertEquals(Color.GREEN, privateObjectiveCardGreen.getColor());

            PrivateObjectiveCard privateObjectiveCardPurple=privateObjectiveCardLoader(privateObjectiveCardsPath + "PrivateObjectiveCardPurple.json");
            Assert.assertEquals(Color.PURPLE, privateObjectiveCardPurple.getColor());

            PrivateObjectiveCard privateObjectiveCardBlue=privateObjectiveCardLoader(privateObjectiveCardsPath + "PrivateObjectiveCardBlue.json");
            Assert.assertEquals(Color.BLUE, privateObjectiveCardBlue.getColor());
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


/*@Test
    public void publicObjectiveCardLoaderTest(){
        try{

        }
}*/

}

