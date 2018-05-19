package Progetto_Ing_Sw.com.tools;

import Progetto_Ing_Sw.com.server.Color;
import Progetto_Ing_Sw.com.server.PrivateObjectiveCard;
import Progetto_Ing_Sw.com.server.ToolCard;
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
    public void createToolcardTest(){
        ToolCard card=new ToolCard("Grozing Pilers", "After drafting, increase or decrease the value of the drafted die by 1", "1 may not change to 6, or 6 to 1",1,false,"Purple");
        try {
            saveJSON(card, "Resources/Cards/ToolCards/GrozingPliers.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void privateObjectiveCardLoaderTest() {
        String privateObjectiveCardsPath="Resources/Cards/PrivateObjectiveCards/";

        try {
            PrivateObjectiveCard privateObjectiveCardYellow= privateObjectiveCardLoaderFromFile(privateObjectiveCardsPath + "PrivateObjectiveCardYellow.json");
            Assert.assertEquals(Color.YELLOW, privateObjectiveCardYellow.getColor());

            PrivateObjectiveCard privateObjectiveCardRed= privateObjectiveCardLoaderFromFile(privateObjectiveCardsPath + "PrivateObjectiveCardRed.json");
            Assert.assertEquals(Color.RED, privateObjectiveCardRed.getColor());

            PrivateObjectiveCard privateObjectiveCardGreen= privateObjectiveCardLoaderFromFile(privateObjectiveCardsPath + "PrivateObjectiveCardGreen.json");
            Assert.assertEquals(Color.GREEN, privateObjectiveCardGreen.getColor());

            PrivateObjectiveCard privateObjectiveCardPurple= privateObjectiveCardLoaderFromFile(privateObjectiveCardsPath + "PrivateObjectiveCardPurple.json");
            Assert.assertEquals(Color.PURPLE, privateObjectiveCardPurple.getColor());

            PrivateObjectiveCard privateObjectiveCardBlue= privateObjectiveCardLoaderFromFile(privateObjectiveCardsPath + "PrivateObjectiveCardBlue.json");
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

