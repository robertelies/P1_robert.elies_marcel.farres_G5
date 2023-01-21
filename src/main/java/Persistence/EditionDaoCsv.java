package Persistence;

import Business.Edition;
import java.io.*;
import java.util.ArrayList;

public class EditionDaoCsv implements EditionDAO {
    @Override
    public void updateEdition(ArrayList<Edition> allEditions) throws IOException {
        FileWriter f = new FileWriter("edition.csv");
        BufferedWriter b = new BufferedWriter(f);
        for (Edition e : allEditions){
            b.append(String.valueOf(e.getYear())).append(",").append(String.valueOf(e.getInitNumPlayers())).append(",").append(String.valueOf(e.getNumTrials()));
            for(String s : e.getSavedTrials()) b.append(",").append(s);
            for(Integer i : e.getTypeTrials()) b.append(",").append(String.valueOf(i));
            b.newLine();
        }
        b.flush();
        b.close();
    }
    @Override
    public void readFileEdition(ArrayList<Edition> allEditions) {
        File csvFileEdition = new File("src/main/java/Persistence/edition.csv");
        String[] data;
        if (csvFileEdition.isFile()) {
            try{
                BufferedReader csvReader = new BufferedReader(new FileReader("edition.csv"));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    data = row.split(",");
                    parseFileEdition(data, allEditions);
                }
                csvReader.close();
            }catch (Exception ignored){
            }
        }
    }
    /**
     * inserts the written information into the ArrayList of Editions
     * @param data String that has all the info from the CSV file
     * @param allEditions ArrayList that will contain all the Editions
     */
    private void parseFileEdition(String[] data, ArrayList<Edition> allEditions) {
        ArrayList<String> savedTrials = new ArrayList<>();
        ArrayList<Integer> typeTrials = new ArrayList<>();
        int i = 0;
        int j = 0;
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
        Edition e = new Edition(year, initNumPlayers, numTrials, savedTrials, typeTrials);
        allEditions.add(e);
    }
    @Override
    public void deleteEditionFile() throws IOException {
        FileWriter fw = new FileWriter("edition.csv", false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
}
