package Persistence;

import Business.Edition;
import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EditionDaoJson implements EditionDAO{
   @Override
    public void deleteEditionFile() throws IOException {
        new FileWriter("edition.json", false).close();
    }
    @Override
    public void readFileEdition(ArrayList<Edition> allEditions) {
        Gson gson = new Gson();
        File jsonFileEdition = new File("src/main/java/Persistence/edition.json");
        if (jsonFileEdition.isFile()) {
            try{
                BufferedReader jsonReader = new BufferedReader(new FileReader("edition.json"));
                Edition[] editionArr = gson.fromJson(jsonReader, Edition[].class);
                allEditions.addAll(Arrays.asList(editionArr));
                jsonReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void updateEdition(ArrayList<Edition> allEditions){
        FileWriter f;
        try{
            Gson gson = new Gson();
            f = new FileWriter("edition.json");
            Edition[] arr = new Edition[allEditions.size()];
            for(int i = 0; i < allEditions.size(); i++){
                arr[i] = allEditions.get(i);
            }
            String jsonString = gson.toJson(arr, Edition[].class);
            f.write(jsonString);
            f.close();
        }catch(Exception e){
            e.printStackTrace();
        }
   }
}
