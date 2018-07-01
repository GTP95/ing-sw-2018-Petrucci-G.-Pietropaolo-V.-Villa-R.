package Progetto_Ing_Sw.com.tools;
import Progetto_Ing_Sw.com.client.*;
import Progetto_Ing_Sw.com.server.Model.*;
import Progetto_Ing_Sw.com.server.Model.ToolCards.Effect;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;


public final class JSONCreator {
    private static final Gson gson=new GsonBuilder().create();
    private static final Type arrayListOfPlayers=new TypeToken<ArrayList<ClientPlayer>>(){}.getType();
    private static final Type arrayListOfDice=new TypeToken<ArrayList<ClientDice>>(){}.getType();
    private static final Type arrayListOfPublicObjectiveCards=new TypeToken<ArrayList<ClientPublicObjectiveCard>>(){}.getType();
    private static final Type arrayListOfToolCards=new TypeToken<ArrayList<ClientToolCard>>(){}.getType();
    private static final Type arrayListOfGameBoardCards=new TypeToken<ArrayList<ClientGameBoardCard>>(){}.getType();

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

    public static boolean parseBooleanFieldFromFile(String path, String fieldName) throws FileNotFoundException {
        JsonElement jelement = new JsonParser().parse(new FileReader(path));
        JsonObject jobject = jelement.getAsJsonObject();
        boolean variable=jobject.get(fieldName).getAsBoolean();
        return variable;
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

    public static ArrayList<ClientPlayer> playerArrayListLoaderFromString(String json){
        ArrayList<ClientPlayer> clientPlayerArrayList =gson.fromJson(json, arrayListOfPlayers);
        return clientPlayerArrayList;
    }

    public static ArrayList<ClientDice> diceArrayListLoaderFromString(String json){
        ArrayList<ClientDice> clientDiceArrayList=gson.fromJson(json, arrayListOfDice);
        return clientDiceArrayList;
    }

    public static ArrayList<ClientPublicObjectiveCard> publicObjectiveCardArrayListLoaderFromString(String json){
        ArrayList<ClientPublicObjectiveCard> clientPublicObjectiveCards=gson.fromJson(json, arrayListOfPublicObjectiveCards);
        return clientPublicObjectiveCards;
    }

    public static ArrayList<ClientToolCard> toolCardArrayListloaderFromString(String json){
        ArrayList<ClientToolCard> clientToolCards=gson.fromJson(json, arrayListOfToolCards);
        return clientToolCards;
    }

    public static ArrayList<ClientGameBoardCard> gameBoardCardArrayListLoaderFromString(String json){
        ArrayList<ClientGameBoardCard> clientGameBoardCards=gson.fromJson(json, arrayListOfGameBoardCards);
        return clientGameBoardCards;
    }

    public static Dice diceLoaderFromString(String json){
        Dice dice=gson.fromJson(json, Dice.class);
        return dice;
    }

    public static ClientDice clientDiceLoaderFromString(String json){
        ClientDice dice=gson.fromJson(json, ClientDice.class);
        return dice;
    }

    public static ClientWindowBoard clientWindowBoardLoaderFromString(String json){
        ClientWindowBoard windowBoard=gson.fromJson(json, ClientWindowBoard.class);
        return windowBoard;
    }

    public static ClientPrivateObjectiveCard clientPrivateObjectiveCardLoaderFromString(String json){
        ClientPrivateObjectiveCard clientPrivateObjectiveCard=gson.fromJson(json, ClientPrivateObjectiveCard.class);
        return clientPrivateObjectiveCard;
    }

    public static ClientToolCard clientToolCardLoaderFromString(String json){
        ClientToolCard toolCard=gson.fromJson(json, ClientToolCard.class);
        return toolCard;
    }

    public static ClientGameBoardCard clientGameBoardCardLoaderFromString(String json){
        ClientGameBoardCard gameBoardCard=gson.fromJson(json,ClientGameBoardCard.class);
        return gameBoardCard;
    }

    public static ClientPublicObjectiveCard clientPublicObjectiveCardLoaderFromString(String json){
        ClientPublicObjectiveCard card=gson.fromJson(json, ClientPublicObjectiveCard.class);
        return card;
    }

    public static ClientRoundTrack clientRoundTrackLoaderFromString(String json){
        ClientRoundTrack roundTrack=gson.fromJson(json,ClientRoundTrack.class);
        return roundTrack;
    }

    public static Effect toolCardWithEffectLoaderFromString(String json){
        Effect card=gson.fromJson(json, Effect.class);
        return card;
    }

   /* public static ClientPlayer clientPlayerLoaderFromString(String json){
        JsonElement jsonElement=new JsonParser().parse(json);
        JsonObject jsonObject=jsonElement.getAsJsonObject();
        json
        return clientPlayer;
    }*/

}
