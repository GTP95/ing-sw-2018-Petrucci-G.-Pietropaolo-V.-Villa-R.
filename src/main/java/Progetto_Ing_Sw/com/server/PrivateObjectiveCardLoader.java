package Progetto_Ing_Sw.com.server;

import com.google.gson.Gson;

public class PrivateObjectiveCardLoader {
    Gson gson;

    public PrivateObjectiveCardLoader(Gson gson) {
        this.gson = gson;
    }

public PrivateObjectiveCard cardLoader(String path){
        PrivateObjectiveCard card=gson.fromJson(path, PrivateObjectiveCard.class);
        return card;
}
}
