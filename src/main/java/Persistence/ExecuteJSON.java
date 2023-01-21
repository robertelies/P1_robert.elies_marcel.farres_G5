package Persistence;

import Business.CurrentEdition;
import Business.ResearchGroup;
import java.util.ArrayList;

public class ExecuteJSON {
    private int numTrials;
    private int year;
    private int initNumPlayers;
    private ArrayList<String> savedTrials;
    private ArrayList<Integer> typeTrials;
    private int currentTrial;
    private ArrayList<Integer> pType;
    private ResearchGroup playersArray;

    //methods
    /**
     * this function gets all the data from the EXECUTE.JSON file persistence and sets it on the CurrentEdition class
     * this is done because from JSON we cannot fill directly to classes with inheritance
     * @return a new CurrentEdition class with all the data from the JSON
     */
    public CurrentEdition parseExecution() {
        return new CurrentEdition(this.numTrials, this.year, this.initNumPlayers, this.savedTrials, this.typeTrials, this.currentTrial, this.pType, this.playersArray);
    }
}
