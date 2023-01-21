package Business;

import java.util.Map;

public abstract class Trial {

    public String nameTrial;
    public int typeTrial;

    //methods
    /**
     * @return String with all the information printed as the functionality "List Trial" specifies
     */
    public abstract String toString();
    /**
     * @return a String that will compose the CSV of Trials
     */
    public abstract String parseString();
    /**
     * runs the desired Trial by checking if the player gets his paper Accepted or Rejected and assigning him
     * a score
     * @return both String of situation (accepted/rejected/revision + etc)
     * and also the score together in a HashMap
     * @param playersArray ArrayList containing all the Players
     * @param type is the type of the Player
     * @param score the score of the Player
     */
    public abstract Map<Integer, String> executeTrial(ResearchGroup playersArray, int score, int type);
    //getters
    /**
     * @return String representing the name given to the Trial
     */
    public String getNameTrial() {
        return nameTrial;
    }
    /**
     * @return int representing the type of Trial it is. (from 1 to 4)
     * 1 = paperSumbission, 2=masterStudies, 3=doctoralThesis, 4=budgetRequest
     */
    public int getTypeTrial() {
        return typeTrial;
    }
    //setters
    /**
     * sets a number corresponding the type of the Trial
     * @param typeTrial type of the Trial (PaperPublication, MasterStudies, DoctoralThesis, BudgetRequest)
     */
    public void setTypeTrial(int typeTrial) {
        this.typeTrial = typeTrial;
    }
}
