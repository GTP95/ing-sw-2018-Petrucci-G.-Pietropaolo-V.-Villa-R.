package Progetto_Ing_Sw.com.tools;

import Progetto_Ing_Sw.com.server.PrivateObjectiveCard;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

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
        jsonCreator.saveJSON(card, "../../Resources/Cards/Cards/PrivateObjectiveCards/PrivareObjectiveCardRed.json");  //TODO: controllare se funziona su Windows
    }
}
