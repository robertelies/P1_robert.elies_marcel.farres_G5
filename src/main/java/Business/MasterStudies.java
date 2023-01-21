package Business;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MasterStudies extends Trial{

    private final String nameStudy;
    private final int numCredits;
    private final int probability;

    //constructor
    public MasterStudies(String nameStudy, int numCredits, int probability, String nameTrial, int typeTrial){
        this.nameTrial = nameTrial;
        this.nameStudy = nameStudy;
        this.numCredits = numCredits;
        this.probability = probability;
        this.typeTrial = typeTrial;
    }
    //getters
    /**
     * @return String representing the name of the study in the Master studies
     */
    public String getNameStudy() {
        return nameStudy;
    }
    /**
     * @return int representing the number of credits in the master
     */
    public int getNumCredits() {
        return numCredits;
    }
    /**
     * @return int representing percentage of passing the Master
     */
    public int getProbability() {
        return probability;
    }
    //methods
    @Override
    public String toString() {
        String s;
        s = "Trial: " + getNameTrial() + " (Master Studies)\nMaster: " +
                getNameStudy() + "\nECTS: " +
                getNumCredits() + ", with a " + getProbability() + "% chance to pass each one\n";
        return s;
    }
    @Override
    public String parseString() {
        return (this.getTypeTrial() + (",") + this.getNameTrial() + (",") + (this.getNameStudy()) + (",")  + (this.getNumCredits()) + (",") + (this.getProbability()));
    }
    @Override
    public Map<Integer, String> executeTrial(ResearchGroup playersArray, int i, int type) {
        Random r = new Random();
        Map<Integer, String> result = new HashMap<>();
        int max = 100;
        int min = 0;
        int countPassed = 0;
        int countTotal = 0;
        int pi;
        int random;
        String situation;
        while(countTotal < getNumCredits()){
            random = r.nextInt(max - min) + min;
            countTotal++;
            if(random <= getProbability()) {
                countPassed++;
            }
        }
        if(countPassed >= (countTotal/2)){
            situation = " passed " + countPassed + "/" + getNumCredits() + " ECTS. Congrats! ";
            if(type == 1){
                pi = 10;
            }
            else{
                pi = 3;
            }
        }else{
            situation = " passed " + countPassed + "/" + getNumCredits() + " ECTS. Sorry... ";
            pi = -3;
        }
        result.put(pi, situation);
        return result;
    }
}
