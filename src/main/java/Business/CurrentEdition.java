package Business;

import java.util.ArrayList;

public class CurrentEdition extends Edition{

    private int currentTrial;
    private ArrayList <Integer> pType;
    private final ResearchGroup playersArray;

    //constructor
    public CurrentEdition(int numTrials, int year, int initNumPlayers, ArrayList<String> savedTrials, ArrayList<Integer> typeTrials, int currentTrial, ArrayList<Integer> pType, ResearchGroup playersArray){
        super(year, initNumPlayers, numTrials, savedTrials, typeTrials);
        this.currentTrial = currentTrial;
        this.pType = pType;
        this.playersArray = playersArray;
    }
    public CurrentEdition(Edition e, ResearchGroup players, ArrayList<Integer> playerType) {
        super(e);
        currentTrial = 0;
        this.playersArray = players;
        this.pType = playerType;
    }
    public CurrentEdition(int year, int initNumPlayers, int numTrials, ArrayList<String> savedTrials, ArrayList<Integer> typeTrials, int numTrial, ArrayList<String> players, ArrayList <Integer> pi_count, ArrayList<Integer> playerType) {
        super(year, initNumPlayers, numTrials, savedTrials, typeTrials);
        this.currentTrial = numTrial;
        this.pType = playerType;
        this.playersArray = new ResearchGroup();
        for(int i = 0; i < initNumPlayers; i++){
            if(pType.get(i) == 1){
                this.playersArray.add(new Engineer (players.get(i), pi_count.get(i)));
            }else if(pType.get(i) == 2){
                this.playersArray.add(new Master (players.get(i), pi_count.get(i)));
            }else{
                this.playersArray.add(new Doctor (players.get(i), pi_count.get(i)));
            }
        }
    }
    //getters
    /**
     * @return integer representing the trial we are currently executing
     */
    public int getNumTrial() {
        return currentTrial;
    }
    /**
     * @return ArrayList of Players (the ResearchGroup)
     */
    public ResearchGroup getPlayersArray() {
        return playersArray;
    }
    /**
     * @return ArrayList of all the types of the Players
     */
    public ArrayList<Integer> getpType() {
        return pType;
    }
    //setters
    /**
     * inserts desired integer representing the number of Trials stored in the Current Edition
     * @param numTrial number of Trials stored in the Current Edition
     */
    public void setNumTrial(int numTrial) {
        this.currentTrial = numTrial;
    }
    /**
     * sets ArrayList of type of Players
     * @param pType ArrayList of type of Players
     */
    public void setpType(ArrayList<Integer> pType) {
        this.pType = pType;
    }
}
