package Progetto_Ing_Sw.com.tools;
import Progetto_Ing_Sw.com.server.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;


public final class JSONCreator {
    private static final Gson gson=new GsonBuilder().create();

    private JSONCreator() {};

    public static String generateJSON(Object object){   //utile per debug
        String JSON=gson.toJson(object);
        return JSON;
    }

    public static void printJSON(Object object){        //utile per debug
        System.out.println(generateJSON(object));
    }

    public static void saveJSON(Object object, String path) throws IOException{ //path deve includere il nome del file
            FileWriter writer = new FileWriter(path);
            writer.write(gson.toJson(object));
            writer.close();


    }

    public static PrivateObjectiveCard privateObjectiveCardLoader(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        PrivateObjectiveCard card = gson.fromJson(bufferedReader, PrivateObjectiveCard.class);
        return card;

    }

    public static PublicObjectiveCard publicObjectiveCardLoader(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        PublicObjectiveCard card = gson.fromJson(bufferedReader, PublicObjectiveCard.class);
        return card;
    }

    public static GameBoardCard gameBoardCardLoader(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        GameBoardCard card = gson.fromJson(bufferedReader, GameBoardCard.class);
        return card;
    }

    public static ToolCard toolCardLoader(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        ToolCard card = gson.fromJson(bufferedReader, ToolCard.class);
        return card;
    }

}
