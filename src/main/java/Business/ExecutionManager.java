package Business;

import Persistence.ExecutionDAO;
import Persistence.ExecutionDaoCsv;
import Persistence.ExecutionDaoJson;
import java.io.IOException;
import java.util.*;

public class ExecutionManager {

    private CurrentEdition currentEdition;
    private final ExecutionDAO daoEx;

    //constructor
    public ExecutionManager(String type) {
        if(type.equals("I")){
            daoEx = new ExecutionDaoCsv();
        }else{
            daoEx = new ExecutionDaoJson();
        }
        setCurrentEdition(daoEx.readFileExecution());
    }
    //methods
    //GET
    /**
     * @return the Current Edition saved
     */
    public CurrentEdition getCurrentEdition() {
        return currentEdition;
    }
    /**
     * @param i position of the name of the Trial in the ArrayList
     * @return the name of the desired Trial
     */
    public String getNameTrial(int i) {
        return currentEdition.getSavedTrials().get(i);
    }
    //SET
    /**
     * inserts the Current Edition into the CurrentEdition entity
     * @param currentEdition Edition to execute (2022)
     */
    public void setCurrentEdition(CurrentEdition currentEdition) {
        this.currentEdition = currentEdition;
    }
    //DELETE
    /**
     * erases a player from the Current Edition because it has lost all his points (score <= 0)
     * @param i is the position of the user to disqualify
     */
    public void disqualifyUser(int i) {
        ArrayList<Player> newPlayers = currentEdition.getPlayersArray().getPlayersArray();
        newPlayers.remove(i);
        currentEdition.getPlayersArray().setPlayersArray(newPlayers);
        ArrayList<Integer> newType = currentEdition.getpType();
        newType.remove(i);
        currentEdition.setpType(newType);
        int newNumPlayers = currentEdition.getInitNumPlayers();
        newNumPlayers = newNumPlayers - 1;
        currentEdition.setInitNumPlayers(newNumPlayers);
    }
    //CSV
    /**
     * calls function to save information from Current Edition into Execute.csv
     */
    public void endPersistenceExecution() throws IOException {
        daoEx.deleteExecutionFile();
        daoEx.updateExecution(currentEdition);
    }
    /**
     * calls function to erase the previous info from the Current Edition saved in the CSV file
     */
    public void deleteFileExec() throws IOException {
        daoEx.deleteExecutionFile();
    }
    //PLAYER
    /**
     * @param p the name of the Player
     * @return a new Player created from scratch (they begin all engineers)
     */
    public Player addPlayer(String p) {
        return new Engineer(p, 5);
    }
    /**
     * adds a score into a Player from the ResearchGroup
     * @param it the score to add
     * @param i the position of the Player in the ResearchGroup
     */
    public void addPlayerScore(Integer it, int i) {
        Player editPlayer = getCurrentPlayer(i);
        editPlayer.setScore(it + editPlayer.getScore());
        currentEdition.getPlayersArray().set(i, editPlayer);
    }
    /**
     * @param i the desired position of the ResearchGroup
     * @return the Player from the ResearchGroup
     */
    public Player getCurrentPlayer(int i) {
        return currentEdition.getPlayersArray().get(i);
    }
    /**
     * this function evolves Players if they have enough score
     * @param i position of the Player in the ResearchGroup
     * @return the new (or not) type of the Player
     */
    public int evolvePlayer(int i) {
        if(getCurrentPlayer(i).getScore() >= 10){
            currentEdition.getpType().set(i, (currentEdition.getpType().get(i) + 1));
            if(currentEdition.getpType().get(i) == 2){
                Master newMaster = new Master(getCurrentPlayer(i).getName(), getCurrentPlayer(i).getScore());
                currentEdition.getPlayersArray().set(i, newMaster);
                getCurrentPlayer(i).setScore(5);
                return 2;
            }else{
                Doctor newDoctor = new Doctor(getCurrentPlayer(i).getName(), getCurrentPlayer(i).getScore());
                currentEdition.getPlayersArray().set(i, newDoctor);
                getCurrentPlayer(i).setScore(5);
                return 3;
            }
        }
        else return 1;
    }
    /**
     * @return all the players from the ResearchGroup
     */
    public ResearchGroup getAllPlayers() {
        return currentEdition.getPlayersArray();
    }
    /**
     * @param i position of the Player in the ResearchGroup
     * @return the type of the desired Player in the ResearchGroup
     */
    public int getTypePlayer(int i) {
        return currentEdition.getpType().get(i);
    }

}
