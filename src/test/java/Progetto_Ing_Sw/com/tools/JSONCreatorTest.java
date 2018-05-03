package Progetto_Ing_Sw.com.tools;

import Progetto_Ing_Sw.com.server.PrivateObjectiveCard;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONCreatorTest {
    private Gson gson;
    private JSONCreator jsonCreator;

    @Before
    public void testInit(){
        gson=new Gson();
        jsonCreator=new JSONCreator(gson);
    }

    @Test
    public void genJSON(){
        PrivateObjectiveCard card=new PrivateObjectiveCard(0);
        jsonCreator.printJSON(card);
    }

    @Test
    public void saveJSONtoFile(){   //java.io.FileNotFoundException
        PrivateObjectiveCard card=new PrivateObjectiveCard(0);
        jsonCreator.saveJSON(card, "PrivateObjectiveCardRed.json");
    }

    @Test
    public void readJSONfromFile(){
        saveJSONtoFile();
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("PrivateObjectiveCardRed.json"));
            PrivateObjectiveCard privateObjectiveCardRed=gson.fromJson(bufferedReader, PrivateObjectiveCard.class);
            Assert.assertEquals(0, privateObjectiveCardRed.getColor());
        } catch (FileNotFoundException e) {
            Assert.fail("The file was not created");
        }

    }
}
