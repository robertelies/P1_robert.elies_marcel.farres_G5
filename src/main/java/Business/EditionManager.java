package Business;

import Persistence.EditionDAO;
import Persistence.EditionDaoCsv;
import Persistence.EditionDaoJson;
import java.io.IOException;
import java.util.ArrayList;

public class EditionManager {

    private final EditionDAO daoE;
    ArrayList <Edition> allEditions;

    //constructor
    public EditionManager(String type){
        allEditions= new ArrayList<>();
        if(type.equals("I")){
            daoE = new EditionDaoCsv();
        }else{
            daoE = new EditionDaoJson();
        }
        daoE.readFileEdition(allEditions);
    }
    //methods
    //VALIDATIONS
    /**
     * proves that the year given by the user has the correct parameters (above or equal current year)
     * @param editionYear year of the Edition
     * @return true if year is less than the current one
     */
    public boolean checkYear(int editionYear) {
        if(editionYear < 2022){
            return true;
        }else{
            for (Edition e : allEditions){
                if(e.getYear() == editionYear){
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * proves that the number of players has the correct parameters
     * @param initNumPlayers number of Players
     * @return if number of players is greater than 0 and less or equal than 5
     */
    public boolean checkPlayers(int initNumPlayers) {
        return (initNumPlayers < 1) || (initNumPlayers > 5);
    }
    /**
     * proves that the number of Trials has the correct parameters
     * @param initNumTrials number of Trials
     * @return if number of Trials is greater than 2 and less or equal than 12
     */
    public boolean checkNumTrials(int initNumTrials) {
        return (initNumTrials < 3) || (initNumTrials > 12);
    }
    /**
     * proves that the year from the Edition to delete is correct
     * @param confirmationYear year that must be the same to the one we want to delete
     * @param i is the number of the Edition we want to check
     * @return if confirmation year is the same as the year of the Edition
     */
    public boolean sameYear(int confirmationYear, int i) {
        return confirmationYear != allEditions.get(i).getYear();
    }
    /**
     * @return that exists an Edition from the current year
     */
    public boolean checkCurrentEdition() {
        for(Edition e: allEditions){
            if(e.getYear() == 2022){
                return false;
            }
        }
        return true;
    }
    //CREATE
    /**
     * creates a new Edition with all the parameters (year, number of players, number of Trials and name of the Trials)
     * @param editionYear year of the Edition
     * @param initNumPlayers number of players of the Edition
     * @param initNumTrials number of Trials of the Edition
     * @param trialNameEditionOrder name of the Trials in the order picked by the user
     */
    public void editionCreation(int editionYear, int initNumPlayers, int initNumTrials, ArrayList<String> trialNameEditionOrder, ArrayList<Integer> trialTypeEditionOrder) {
        Edition e = new Edition(editionYear, initNumPlayers, initNumTrials, trialNameEditionOrder, trialTypeEditionOrder);
        allEditions.add(e);
    }
    //GET
    /**
     * @return the Edition desired (position i in the ArrayList of Editions)
     */
    public Edition getEdition(int i) {
        return allEditions.get(i);
    }
    /**
     * @return the Edition desired with a singular year
     */
    public Edition getEditionByYear(int year){
        for(Edition e: allEditions) if(e.getYear() == year) return e;
        return null;
    }
    /**
     * @return ArrayList of Integers containing all the years from the Editions saved
     */
    public ArrayList<Integer> getAllEditionYears() {
        ArrayList<Integer> allEditionYears = new ArrayList<>();
        for (Edition e : allEditions){
            allEditionYears.add(e.getYear());
        }
        return allEditionYears;
    }
    //DELETE
    /**
     * erases a specific Edition (position i in the ArrayList of Editions
     * @param i is the position where it is stored the Edition we want to delete
     */
    public void editionDeletion(int i) {
        allEditions.remove(i);
    }
    //DUPLICATE
    /**
     * duplicates the Edition desired (position i in the ArrayList of Editions)
     * @param editionToDuplicate Edition that we want to duplicate
     * @param newNumPlayers new number of Players
     * @param newEditionYear new year of the Edition to duplicate
     */
    public void editionDuplication(int editionToDuplicate, int newNumPlayers, int newEditionYear) {
        Edition e = new Edition(newEditionYear, newNumPlayers, allEditions.get(editionToDuplicate));
        allEditions.add(e);
    }
    //CSV
    /**
     * calls function to save information from ArrayList of Editions into Editions.csv
     * and Editions.json at the same time (interface used)
     */
    public void endPersistenceEdition() throws IOException {
        daoE.deleteEditionFile();
        daoE.updateEdition(allEditions);
    }
}

