package Persistence;

import Business.*;
import java.io.*;
import java.util.ArrayList;

public class TrialDaoCsv implements TrialDAO{
    @Override
    public void updateTrialFile(ArrayList<Trial> allTrials) throws IOException {
        FileWriter f = new FileWriter("trial.csv");
        BufferedWriter b = new BufferedWriter(f);
        for (Trial t : allTrials){
            b.append(t.parseString());
            b.newLine();
        }
        b.flush();
        b.close();
    }
    @Override
    public void readFileTrial(ArrayList<Trial> allTrials){
        File csvFileTrial = new File("src/main/java/Persistence/trial.csv");
        String[] data;
        if (csvFileTrial.isFile()) {
            try{
                BufferedReader csvReader = new BufferedReader(new FileReader("trial.csv"));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    data = row.split(",");
                    int typeTrial = Integer.parseInt(data[0]);
                    if(typeTrial == 1){
                        parseFileTrialType1(data, allTrials, typeTrial);
                    }else if(typeTrial == 2){
                        parseFileTrialType2(data, allTrials, typeTrial);
                    }else if(typeTrial == 3){
                        parseFileTrialType3(data, allTrials, typeTrial);
                    }else{
                        parseFileTrialType4(data, allTrials, typeTrial);
                    }
                }
                csvReader.close();
            }catch(Exception ignored){
            }
        }
    }
    /**
     * inserts the written information into the ArrayList of Trials (Budget Request)
     * @param data String with all the info from the CSV
     * @param allTrials ArrayList to save all the Trials
     * @param typeTrial type of the Trial
     */
    private void parseFileTrialType4(String[] data, ArrayList<Trial> allTrials, int typeTrial) {
        String nameTrial = data[1];
        String nameEntity = data[2];
        int moneyBudget = Integer.parseInt(data[3]);
        BudgetRequest t = new BudgetRequest(nameEntity, moneyBudget, nameTrial, typeTrial);
        allTrials.add(t);
    }
    /**
     * inserts the written information into the ArrayList of Trials (Doctoral Thesis)
     * @param data String with all the info from the CSV
     * @param allTrials ArrayList to save all the Trials
     * @param typeTrial type of the Trial
     */
    private void parseFileTrialType3(String[] data, ArrayList<Trial> allTrials, int typeTrial) {
        String nameTrial = data[1];
        String nameField = data[2];
        int difficulty = Integer.parseInt(data[3]);
        DoctoralThesis t = new DoctoralThesis(nameField, difficulty, nameTrial, typeTrial);
        allTrials.add(t);
    }
    /**
     * inserts the written information into the ArrayList of Trials (Master Studies)
     *@param data String with all the info from the CSV
     *@param allTrials ArrayList to save all the Trials
     *@param typeTrial type of the Trial
     */
    private void parseFileTrialType2(String[] data, ArrayList<Trial> allTrials, int typeTrial) {
        String nameTrial = data[1];
        String nameStudy = data[2];
        int numCredits = Integer.parseInt(data[3]);
        int probability = Integer.parseInt(data[4]);
        MasterStudies t = new MasterStudies(nameStudy, numCredits, probability, nameTrial, typeTrial);
        allTrials.add(t);
    }
    /**
     * inserts the written information into the ArrayList of Trials (Paper Publication)
     * @param data String with all the info from the CSV
     *@param allTrials ArrayList to save all the Trials
     *@param typeTrial type of the Trial
     */
    private void parseFileTrialType1(String[] data, ArrayList<Trial> allTrials, int typeTrial) {
        String nameTrial = data[1];
        String namePublication = data[2];
        String quartile = data[3];
        int probAccept = Integer.parseInt(data[4]);
        int probRevision = Integer.parseInt(data[5]);
        int probReject = Integer.parseInt(data[6]);
        PaperSubmission t = new PaperSubmission(nameTrial, namePublication, quartile, probAccept, probRevision, probReject, typeTrial);
        allTrials.add(t);
    }
    @Override
    public void deleteTrialFile() throws IOException {
        FileWriter fw = new FileWriter("trial.csv", false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
}
