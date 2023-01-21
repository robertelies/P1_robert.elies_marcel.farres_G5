package Persistence;

import Business.*;
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;


public class TrialDaoJson implements TrialDAO{
    @Override
    public void updateTrialFile(ArrayList<Trial> allTrials) {
        FileWriter f;
        try{
            Gson gson = new Gson();
            f = new FileWriter("trial.json");
            Trial[] arr = new Trial[allTrials.size()];
            for(int i = 0; i < allTrials.size(); i++){
                arr[i] = allTrials.get(i);
            }
            String jsonString = gson.toJson(arr, Trial[].class);
            f.write(jsonString);
            f.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void readFileTrial(ArrayList<Trial> allTrials) {
        File jsonFileEdition = new File("src/main/java/Persistence/trial.json");
        if (jsonFileEdition.isFile()) {
            try{
                Gson gson = new Gson();
                BufferedReader jsonReader = new BufferedReader(new FileReader("trial.json"));
                TrialJSON[] trialArr = gson.fromJson(jsonReader, TrialJSON[].class);
                for (TrialJSON trialJSON : trialArr) {
                    trialJSON.parseTrialsJson(allTrials);
                }
                jsonReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteTrialFile() throws IOException {
        new FileWriter("trial.json", false).close();
    }
}
