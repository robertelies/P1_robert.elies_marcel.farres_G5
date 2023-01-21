package Business;

import Persistence.TrialDAO;
import Persistence.TrialDaoCsv;
import Persistence.TrialDaoJson;
import java.io.IOException;
import java.util.ArrayList;

public class TrialManager {

    private final TrialDAO daoT;
    ArrayList <Trial> allTrials;

    //constructor
    public TrialManager(String type) {
        allTrials = new ArrayList<>();
        if(type.equals("I")){
            daoT = new TrialDaoCsv();
        }else{
            daoT = new TrialDaoJson();
        }
        daoT.readFileTrial(allTrials);
    }
    //methods
    //VALIDATIONS
    /**
     * proves that the Trials do not have the same names or the names are empty
     * @param tName is the name of the Trial
     * @return if the name has already been used or the name is empty
     */
    public boolean checkEqualName(String tName) {
        if(tName.equals("")) {
            return true;
        }
        for (Trial t : allTrials){
            if(t.getNameTrial().equals(tName)){
                return true;
            }
        }
        return false;
    }
    /**
     * proves that the Trial's journals do not have empty names
     * @param jName is the name of the Journal
     * @return if the name is empty or not
     */
    public boolean checkEmpty(String jName) {
        return jName.equals("");
    }
    /**
     * @param jQuartile Quartile of the PaperSubmission
     * @return if the quartiles are are written in correct form (Q1 || Q2 || Q3 || Q4)
     */
    public int checkQuartile(String jQuartile) {
        int value = 0;
        if(!(jQuartile.equals("Q1") || jQuartile.equals("Q2") || jQuartile.equals("Q3") || jQuartile.equals("Q4"))){
            value = 1;
        }
        return value;
    }
    /**
     * @param accP equals probability of acceptance
     * @param rejP equals probability of rejection
     * @param revP equals probability of revision
     * @return if all probabilities add up to 100
     */
    public boolean completeProb(int accP, int rejP, int revP) {
        return accP + rejP + revP != 100;
    }
    /**
     * @param i is the position of the Trial in the allTrials array
     * @param nameToDelete the name of the Trial to delete
     * @return that the name of the Trial to delete coincides with the name of the position's Trial to delete
     */
    public boolean compareTrialName(int i, String nameToDelete) {
        return allTrials.get(i).getNameTrial().equals(nameToDelete);
    }
    /**
     * @proves that the probability given by the user has the correct parameters
     * @param probability probability of any kind given
     * @return if the probability is more or equal than 0 and less or equal than 100
     */
    public boolean checkProb(int probability) {
        return (probability < 0) || (probability > 100);
    }
    /**
     * @return false if the number of credits are more or equal than 60 and less or equal than 100
     */
    public boolean checkCredits(int numCredits) {
        return (numCredits < 60) || (numCredits > 120);
    }
    /**
     * @return false if the number of difficulty is more or equal than 1 and less or equal than 100
     */
    public boolean checkDifficulty(int difficulty) {
        return (difficulty < 1) || (difficulty > 10);
    }
    /**
     * @return false if the amount of budget is more or equal than 1000 and less or equal than 2000000000
     */
    public boolean checkAmount(long budgetAmount) {
        return (budgetAmount < 1000) || (budgetAmount > 2000000000);
    }
    //GET
    /**
     * @return ArrayList of Strings containing all the names from the Trials saved
     */
    public ArrayList<String> getAllTrialNames() {
        ArrayList<String> allTrialNames = new ArrayList<>();
        for (Trial t : allTrials){
            allTrialNames.add(t.getNameTrial());
        }
        return allTrialNames;
    }
    /**
     * @return the Trial desired (position i in the ArrayList of Trials)
     */
    public Trial getTrial(int i) {
        return allTrials.get(i);
    }
    /**
     * @return the Edition desired with a singular name
     */
    public Trial getTrial(String name) {
        for(Trial t: allTrials) if(t.getNameTrial().equals(name)) return t;
        return null;
    }
    /**
     * @return the Trial name from a specific trial (position i in ArrayList of Trials)
     */
    public String getTrial4EditionName(int i) {
        int counter = 0;
        for (Trial t : allTrials){
            if(counter == i){
                return t.getNameTrial();
            }
            counter++;
        }
        return null;
    }
    /**
     * this function gets the desired type of the desired Trial
     * @param i its the number of the position in the ArrayList of allTrials
     * @return the Integer containing the type of the Trial
     */
    public Integer getTrial4EditionType(int i) {
        int counter = 0;
        for (Trial t : allTrials){
            if(counter == i){
                return t.getTypeTrial();
            }
            counter++;
        }
        return null;
    }
    //CREATE
    /**
     * creates a new Trial with all the parameters:
     * @param tName = name
     * @param jName = name of Journal
     * @param jQuartile = quartile
     * @param accP = probability of acceptance
     * @param revP = probability of revision
     * @param rejP = probability of reject
     * @param typeTrial = type of Trial
     */
    public void trialT1Creation(String tName, String jName, String jQuartile, int accP, int revP, int rejP, int typeTrial) {
        PaperSubmission t = new PaperSubmission(tName, jName, jQuartile, accP, revP, rejP, typeTrial);
        allTrials.add(t);
    }
    /**
     * creates a new Trial with all the parameters:
     * @param masterName = name of Master
     * @param numCredits = number of credits
     * @param masterProb = probability of passing
     * @param tName = name
     * @param typeTrial = type of Trial
     */
    public void trialT2Creation(String masterName, int numCredits, int masterProb, String tName, int typeTrial) {
        MasterStudies t = new MasterStudies(masterName, numCredits, masterProb, tName, typeTrial);
        allTrials.add(t);
    }
    /**
     * creates a new Trial with all the parameters:
     * @param studyField = name of study Field
     * @param difficulty = difficulty number
     * @param tName = name
     * @param typeTrial = type of trial
     */
    public void trialT3Creation(String studyField, int difficulty, String tName, int typeTrial) {
        DoctoralThesis t = new DoctoralThesis(studyField, difficulty, tName, typeTrial);
        allTrials.add(t);
    }
    /**
     * creates a new Trial with all the parameters:
     * @param entity = name of entity
     * @param budgetAmount = budget amount
     * @param tName = name
     * @param typeTrial = type of Trial
     */
    public void trialT4Creation(String entity, long budgetAmount, String tName, int typeTrial) {
        BudgetRequest t = new BudgetRequest(entity, budgetAmount, tName, typeTrial);
        allTrials.add(t);
    }
    //DELETE
    /**
     * erases a specific Trial (position i in the ArrayList of Trials)
     * @param i the number of the position of the Trial in the allTrials ArrayList
     */
    public void trialDeletion(int i) {
        allTrials.remove(i);
    }
    //CSV
    /**
     * calls function to save information from ArrayList of Trials into Trials.csv
     */
    public void endPersistenceTrial() throws IOException {
        daoT.deleteTrialFile();
        daoT.updateTrialFile(allTrials);
    }
}
