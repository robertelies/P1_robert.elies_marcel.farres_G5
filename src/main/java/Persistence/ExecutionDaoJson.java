package Persistence;

import Business.CurrentEdition;
import com.google.gson.Gson;
import java.io.*;


public class ExecutionDaoJson implements ExecutionDAO{
    @Override
    public CurrentEdition readFileExecution() {
        Gson gson = new Gson();
        File jsonFileEdition = new File("src/main/java/Persistence/execution.json");
        if (jsonFileEdition.isFile()) {
            try{
                BufferedReader jsonReader = new BufferedReader(new FileReader("execution.json"));
                ExecuteJSON ex = gson.fromJson(jsonReader, ExecuteJSON.class);
                jsonReader.close();
                return ex.parseExecution();
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }
    @Override
    public void deleteExecutionFile() throws IOException {
        new FileWriter("execution.json", false).close();
    }
    @Override
    public void updateExecution(CurrentEdition currentEdition) {
        FileWriter f;
        try{
            Gson gson = new Gson();
            f = new FileWriter("execution.json");
            String jsonString = gson.toJson(currentEdition, CurrentEdition.class);
            f.write(jsonString);
            f.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
