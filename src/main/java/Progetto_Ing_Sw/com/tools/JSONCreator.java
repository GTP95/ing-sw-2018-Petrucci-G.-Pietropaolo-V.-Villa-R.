package Progetto_Ing_Sw.com.tools;
import com.google.gson.Gson;

import java.io.*;


public class JSONCreator {
    private Gson gson;

    public JSONCreator(Gson gson) {
        this.gson = gson;
    }

    public String generateJSON(Object object){
        String JSON=gson.toJson(object);
        return JSON;
    }

    public void printJSON(Object object){
        System.out.println(generateJSON(object));
    }

    public void saveJSON(Object object, String path){ //path deve includere il nome del file
        File file=new File(path);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(generateJSON(object));
        } catch (IOException e) {
            e.printStackTrace();    //TODO: controllare alternative
        }

    }


}
