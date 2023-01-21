package Business;

import java.util.HashMap;
import java.util.Map;

public class DoctoralThesis extends Trial{

    private final String field;
    private final int difficulty;

    //constructor
    public DoctoralThesis(String field, int difficulty, String nameTrial, int typeTrial){
        this.nameTrial = nameTrial;
        this.difficulty = difficulty;
        this.field = field;
        this.typeTrial = typeTrial;
    }
    //getters
    /**
     * @return String representing the name of the Field of the doctoral thesis to defend
     */
    public String getField() {
        return field;
    }
    /**
     * @return int representing the difficulty degree of defence of the Thesis
     */
    public int getDifficulty() {
        return difficulty;
    }
    //methods
    @Override
    public String toString() {
        String s;
        s = "Trial: " + getNameTrial() + " (Doctoral thesis defense)\nField: " +
                getField() + "\nDifficulty: " +
                getDifficulty() + "\n";
        return s;
        }
    @Override
    public String parseString() {
        return (this.getTypeTrial() + (",") + this.getNameTrial() + (",") + (this.getField()) + (",")  + (this.getDifficulty()));
    }
    @Override
    public Map<Integer, String> executeTrial(ResearchGroup playersArray, int score, int type) {
        Map<Integer, String> result = new HashMap<>();
        int pi;
        String situation;
        int sum = 0;
        for(int i = 1; i < difficulty; i++){
            sum += ((2*i)-1);
        }
        if(score > (Math.sqrt(sum))){
            situation = " was successful. Congrats! ";
            if(type == 2){
                pi = 10;
            }
            else{
                pi = 5;
            }
        }else{
            situation = " was not successful. Sorry... ";
            pi = -5;
        }
        result.put(pi, situation);
        return result;
    }
}
