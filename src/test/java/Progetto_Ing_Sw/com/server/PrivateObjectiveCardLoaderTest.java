package Progetto_Ing_Sw.com.server;

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
        PrivateObjectiveCard card=privateObjectiveCardLoader.cardLoader("../java/Resources/Cards.Cards.PrivateObjectiveCards");
        System.out.println(card.getColor());
    }

}
