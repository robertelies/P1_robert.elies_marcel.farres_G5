package Business;

import java.util.HashMap;
import java.util.Map;

public class BudgetRequest extends Trial{

    private final String nameEntity;
    private final long moneyRequest;

    //constructor
    public BudgetRequest(String nameEntity, long moneyRequest, String nameTrial, int typeTrial){
        this.nameTrial = nameTrial;
        this.moneyRequest = moneyRequest;
        this.nameEntity = nameEntity;
        this.typeTrial = typeTrial;
    }
    //getters
    /**
     * @return String representing the name of the entity to request the budget from
     */
    public String getNameEntity() {
        return nameEntity;
    }
    /**
     * @return long int representing the amount of money requested to the entity
     */
    public long getMoneyRequest() {
        return moneyRequest;
    }
    //methods
    @Override
    public String toString() {
        String s;
        s = "Trial: " + getNameTrial() + " (Budget request)\nEntity: " +
                getNameEntity() + "\nBudget: " +
                getMoneyRequest() + " €\n";
        return s;
    }
    @Override
    public String parseString() {
        return (this.getTypeTrial() + (",") + this.getNameTrial() + (",") + (this.getNameEntity()) + (",")  + (this.getMoneyRequest()));
    }
    @Override
    public Map<Integer, String> executeTrial(ResearchGroup playersArray, int score, int type) {
        int adder = 0;
        Map<Integer, String> result = new HashMap<>();
        int pi;
        String situation;
        for (Player p: playersArray.getPlayersArray()) {
            adder += p.getScore();
        }
        if(adder > (int)(Math.log(moneyRequest) / Math.log(2))){
            situation = "The research group got the budget!\n";
            pi = score/2;
        }
        else{
            situation = "The research group did not got the budget!\n";
            pi = -2;
        }
        result.put(pi, situation);
        return result;
    }
}
