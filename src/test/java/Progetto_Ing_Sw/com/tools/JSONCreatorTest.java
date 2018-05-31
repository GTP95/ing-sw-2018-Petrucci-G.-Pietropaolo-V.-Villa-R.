package Progetto_Ing_Sw.com.tools;

import Progetto_Ing_Sw.com.server.Model.Color;
import Progetto_Ing_Sw.com.server.Model.Player;
import Progetto_Ing_Sw.com.server.Model.PrivateObjectiveCard;
import Progetto_Ing_Sw.com.server.Model.ToolCard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static Progetto_Ing_Sw.com.tools.JSONCreator.*;
import static org.mockito.Mockito.mock;

public class JSONCreatorTest {

    @Test
    public void generateJSONTest(){
        PrivateObjectiveCard card=new PrivateObjectiveCard(Color.RED);
        printJSON(card);
    }

/*    @Test
    public void saveJSONtest(){   //java.io.FileNotFoundException
        PrivateObjectiveCard card=new PrivateObjectiveCard(Color.RED);
        try {
            saveJSON(card, "PrivateObjectiveCardRed.json");
        }
        catch (IOException e){
            e.printStackTrace();    //TODO: controllare alternative
            Assert.fail();
        }

    }*/

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


@Test
    public void playerArrayListLoaderFromStringOnlyOneElementTest() {
        Player readyPlayerOne=mock(Player.class);
        ArrayList<Player> playerArrayList = new ArrayList<>();
        playerArrayList.add(readyPlayerOne);
        String json=playerArrayListToJSON(playerArrayList);
        ArrayList<Player> playerArrayList2=playerArrayListLoaderFromString(json);
        Assert.assertTrue(playerArrayList.equals(playerArrayList2));    //controlla che la lista caricata dal json sia uguale a quella di partenza
}

@Test
    public void serializeAndDeserializePlayerTest(){
        Player readyPlayerOne=mock(Player.class);
        String json=generateJSON(readyPlayerOne);
        Player playerTwo=playerLoaderFromString(json);
        Assert.assertTrue(readyPlayerOne.equals(playerTwo));
    }

}

