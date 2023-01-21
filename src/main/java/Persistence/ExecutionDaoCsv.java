package Persistence;

import Business.CurrentEdition;
import java.io.*;
import java.util.ArrayList;

public class ExecutionDaoCsv implements ExecutionDAO{
    @Override
    public CurrentEdition readFileExecution() {
        File csvFileExecution = new File("src/main/java/Persistence/execution.csv");
        String[] data;
        if (csvFileExecution.isFile()) {
            try{
                BufferedReader csvReader = new BufferedReader(new FileReader("execution.csv"));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    data = row.split(",");
                    return parseFileExecution(data);
                }
                csvReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * inserts the written information into the Current Edition
     * @param data has all the information taken from the JSON file
     * @return The CurrentEdition created
     */
    private CurrentEdition parseFileExecution(String[] data) {
        int i = 0;
        int j = 0;
        ArrayList<String> savedTrials = new ArrayList<>();
        ArrayList<Integer> typeTrials = new ArrayList<>();
        ArrayList <String> players = new ArrayList<>();
        ArrayList <Integer> pi_count = new ArrayList<>();
        ArrayList <Integer> pType = new ArrayList<>();
        int year = Integer.parseInt(data[0]);
        int initNumPlayers = Integer.parseInt(data[1]);
        int numTrials = Integer.parseInt(data[2]);
        while(i < numTrials){
            savedTrials.add(data[i + 3]);
            i++;
        }
        while(j < numTrials){
            typeTrials.add(Integer.parseInt(data[i + 3]));
            i++;
            j++;
        }
        int currentTrial = Integer.parseInt(data[i + 3]);
        j = 0;
        while(j < initNumPlayers){
            pi_count.add(Integer.parseInt(data[i + 4]));
            i++;
            j++;
        }
        j = 0;
        while(j < initNumPlayers){
            players.add(data[i + 4]);
            j++;
            i++;
        }
        j = 0;
        while(j < initNumPlayers){
            pType.add(Integer.parseInt(data[i + 4]));
            i++;
            j++;
        }
        return new CurrentEdition(year, initNumPlayers, numTrials, savedTrials,typeTrials, currentTrial, players, pi_count, pType);
    }
    @Override
    public void deleteExecutionFile() throws IOException {
        FileWriter fw = new FileWriter("execution.csv", false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
    @Override
    public void updateExecution(CurrentEdition currentEdition) throws IOException {
        FileWriter f = new FileWriter("execution.csv");
        BufferedWriter b = new BufferedWriter(f);
        b.append(String.valueOf(currentEdition.getYear())).append(",").append(String.valueOf(currentEdition.getInitNumPlayers())).append(",").append(String.valueOf(currentEdition.getNumTrials()));
        for(String s : currentEdition.getSavedTrials()) b.append(",").append(s);
        for(Integer i : currentEdition.getTypeTrials()) b.append(",").append(String.valueOf(i));
        b.append(",").append(String.valueOf(currentEdition.getNumTrial()));
        for(int i = 0; i < currentEdition.getInitNumPlayers(); i++){
            b.append(",").append(String.valueOf(currentEdition.getPlayersArray().get(i).getScore()));
        }
        for(int i = 0; i < currentEdition.getInitNumPlayers(); i++){
            b.append(",").append(currentEdition.getPlayersArray().get(i).getName());
        }
        for(Integer t : currentEdition.getpType()) b.append(",").append(String.valueOf(t));
        b.newLine();
        b.flush();
        b.close();
    }
}
