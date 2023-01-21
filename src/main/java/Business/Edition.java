package Business;

import java.util.ArrayList;

public class Edition {

    private int numTrials;
    private int year;
    private int initNumPlayers;
    private ArrayList<String> savedTrials;
    private ArrayList<Integer> typeTrials;

    //constructor
    public Edition(){
    }
    public Edition(int year, int initNumPlayers, int numTrials, ArrayList<String> savedTrials, ArrayList<Integer> typeTrials) {
        this.numTrials = numTrials;
        this.year = year;
        this.initNumPlayers = initNumPlayers;
        this.savedTrials = savedTrials;
        this.typeTrials = typeTrials;
    }
    public Edition( int year, int initNumPlayers, Edition e) {
        this.numTrials = e.getNumTrials();
        this.year = year;
        this.initNumPlayers = initNumPlayers;
        this.savedTrials = e.getSavedTrials();
        this.typeTrials = e.getTypeTrials();
    }
    public Edition(Edition e) {
        this.numTrials = e.getNumTrials();
        this.year = e.getYear();
        this.initNumPlayers = e.getInitNumPlayers();
        this.savedTrials = e.getSavedTrials();
        this.typeTrials = e.getTypeTrials();
    }
    //getters
    /**
     * @return integer representing the number of trials stored in an Edition
     */
    public int getNumTrials(){
        return numTrials;
    }
    /**
     * @return integer representing the year of the Edition
     */
    public int getYear(){
        return year;
    }
    /**
     * @return integer representing the number of players stored in an Edition
     */
    public int getInitNumPlayers() {
        return initNumPlayers;
    }
    /**
     * @return ArrayList of Strings which contain the names in order of the saved Trials
     */
    public ArrayList<String> getSavedTrials(){
        return new ArrayList<>(savedTrials);
    }
    /**
     * @return ArrayList containing all the types of Trials
     */
    public ArrayList<Integer> getTypeTrials(){
        return new ArrayList<>(typeTrials);
    }
    /**
     * @param i position in ArrayList of the Trial type
     * @return a desired type of Trial from the ArrayList of Type of Trials
     */
    public Integer getOneTrialTypeSaved(int i){return typeTrials.get(i);}
    //setters
    /**
     * inserts desired integer representing the number of players stored in an Edition
     * @param initNumPlayers number of Player of the Edition
     */
    public void setInitNumPlayers(int initNumPlayers) {
        this.initNumPlayers = initNumPlayers;
    }
}
