package Progetto_Ing_Sw.com.server;

import Progetto_Ing_Sw.com.tools.JSONCreator;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

public class PrivateObjectiveCardLoaderTest {
    private Gson gson;
    private PrivateObjectiveCardLoader privateObjectiveCardLoader;

    @Before
    public void prepareEnviroment(){
        gson=new Gson();
        privateObjectiveCardLoader=new PrivateObjectiveCardLoader(gson);
    }

    @Test
    public void checkColor(){
        PrivateObjectiveCard card=privateObjectiveCardLoader.cardLoader("/home/giacomo/Sviluppo/Java/ing-sw-2018-Petrucci-G.-Pietropaolo-V.-Villa-R./src/main/Resources/Cards/Cards/PrivateObjectiveCards/PrivateObjectiveCardRed.json");   //TODO: use relative path
        System.out.println(card.getColor());
    }



}
