package Progetto_Ing_Sw.com.tools;
import Progetto_Ing_Sw.com.server.Model.GameBoardCard;
import Progetto_Ing_Sw.com.server.Model.PrivateObjectiveCard;
import Progetto_Ing_Sw.com.server.Model.PublicObjectiveCard;
import Progetto_Ing_Sw.com.server.Model.ToolCard;
import com.google.gson.*;

import java.io.*;


public final class JSONCreator {
    private static final Gson gson=new GsonBuilder().create();

    private JSONCreator() {};

    public static String generateJSON(Object object){
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

    public static PrivateObjectiveCard privateObjectiveCardLoaderFromFile(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        PrivateObjectiveCard card = gson.fromJson(bufferedReader, PrivateObjectiveCard.class);
        return card;

    }

    public static PublicObjectiveCard publicObjectiveCardLoaderFromFile(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        PublicObjectiveCard card = gson.fromJson(bufferedReader, PublicObjectiveCard.class);
        return card;
    }

    public static GameBoardCard gameBoardCardLoaderFromFile(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        GameBoardCard card = gson.fromJson(bufferedReader, GameBoardCard.class);
        return card;
    }

    public static ToolCard toolCardLoaderFromFile(String path) throws FileNotFoundException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        ToolCard card = gson.fromJson(bufferedReader, ToolCard.class);
        return card;
    }

    public static PrivateObjectiveCard privateObjectiveCardLoaderFromString(String json){
        PrivateObjectiveCard card = gson.fromJson(json, PrivateObjectiveCard.class);
        return card;

    }

    public static PublicObjectiveCard publicObjectiveCardLoaderFromString(String json){
        PublicObjectiveCard card = gson.fromJson(json, PublicObjectiveCard.class);
        return card;
    }

    public static GameBoardCard gameBoardCardLoaderFromString(String json){
        GameBoardCard card = gson.fromJson(json, GameBoardCard.class);
        return card;
    }

    public static ToolCard toolCardLoaderFromString(String json){
        ToolCard card = gson.fromJson(json, ToolCard.class);
        return card;
    }

    public static int parseIntFieldFromFile(String path, String fieldName) throws FileNotFoundException {
        JsonElement jelement = new JsonParser().parse(new FileReader(path));
        JsonObject jobject = jelement.getAsJsonObject();
        int number=jobject.get(fieldName).getAsInt();
        return number;
    }

    public static int[][] parseMatrixFieldFromFile(String path, String fieldName, int rows, int columns) throws FileNotFoundException {

        JsonElement jelement = new JsonParser().parse(new FileReader(path));
        JsonObject jobject = jelement.getAsJsonObject();
        JsonArray matrixTexture=jobject.getAsJsonArray(fieldName);
        int [][] matrix = new int [rows][columns];

        for(int r=0;r<matrixTexture.size();r++){
            JsonArray row=matrixTexture.get(r).getAsJsonArray();
            for(int c=0; c<row.size();c++){
                matrix[r][c]=row.get(c).getAsInt();
            }
        }
        return matrix;
    }


    public static String parseStringFieldFromFile(String path, String fieldName) throws FileNotFoundException{
        JsonElement jelement = new JsonParser().parse(new FileReader(path));
        JsonObject jobject = jelement.getAsJsonObject();
        String string=jobject.get(fieldName).getAsString();
        return string;
    }

    public static long parseLongFieldFromFile(String path, String fieldName) throws FileNotFoundException{
        JsonElement jelement = new JsonParser().parse(new FileReader(path));
        JsonObject jobject = jelement.getAsJsonObject();
        long number=jobject.get(fieldName).getAsLong();
        return number;
    }

}
