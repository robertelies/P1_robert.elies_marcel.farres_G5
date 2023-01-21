package Business;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class PaperSubmission extends Trial{

    private final String namePublication;
    private final String quartile;
    private final int probAccept;
    private final int probRevision;
    private final int probReject;

    //constructor
    public PaperSubmission (String nameTrial, String namePublication, String quartile, int probAccept, int probRevision, int probReject, int typeTrial){
        this.nameTrial = nameTrial;
        this.namePublication = namePublication;
        this.quartile = quartile;
        this.probAccept = probAccept;
        this.probRevision = probRevision;
        this.probReject = probReject;
        this.typeTrial = typeTrial;
    }
    //getters
    /**
     * @return String representing the name of the publication given to the Trial
     */
    public String getNamePublication(){
        return  namePublication;
    }
    /**
     * @return String representing the Quartile given to the Trial
     */
    public String getQuartile(){
        return quartile;
    }
    /**
     * @return integer representing probability of acceptance given to the Trial
     */
    public int getProbAccept(){
        return probAccept;
    }
    /**
     * @return integer representing probability of revision given to the Trial
     */
    public int getProbRevision(){
        return probRevision;
    }
    /**
     * @return integer representing probability of reject given to the Trial
     */
    public int getProbReject(){
        return probReject;
    }
    //methods
    @Override
    public String toString() {
        String s;
        s = "Trial: " + getNameTrial() + " (Paper publication)\nJournal: " +
                getNamePublication() + " (" + getQuartile() + ")\nChances: " +
                getProbAccept() + "% acceptance, " + getProbRevision() + "% revision, " + getProbReject() + "% rejection\n";
            return s;
    }
    @Override
    public String parseString() {
        return (this.getTypeTrial() + (",") + this.getNameTrial() + (",") + (this.getNamePublication()) + (",") + (this.getQuartile()) + (",") + (this.getProbAccept()) + (",") + (this.getProbRevision()) + (",") + (this.getProbReject()));
    }
    @Override
    public Map<Integer, String> executeTrial(ResearchGroup playersArray, int score, int type) {
        Random r = new Random();
        Map<Integer, String> result = new HashMap<>();
        int max = 100;
        int min = 0;
        int rev = 1;
        int pi = 0;
        int random;
        StringBuilder situation = new StringBuilder();
        do{
            random = r.nextInt(max - min) + min;
            if(random <= getProbAccept()){
                situation.append("Accepted! ");
                rev = 0;
                switch(type){
                    case 1:
                    case 2:
                        if(Objects.equals(getQuartile(), "Q1")){
                            pi = 4;
                        }else if(Objects.equals(getQuartile(), "Q2")){
                            pi = 3;
                        }else if(Objects.equals(getQuartile(), "Q3")){
                            pi = 2;
                        }else{
                            pi = 1;
                        }
                        break;
                    case 3:
                        if(Objects.equals(getQuartile(), "Q1")){
                            pi = 8;
                        }else if(Objects.equals(getQuartile(), "Q2")){
                            pi = 6;
                        }else if(Objects.equals(getQuartile(), "Q3")){
                            pi = 4;
                        }else{
                            pi = 2;
                        }
                        break;
                }
            }else if((random > getProbAccept()) && (random <= (getProbReject() + getProbAccept()))) {
                situation.append("Rejected. ");
                rev = 0;
                switch(type){
                    case 1:
                        if (Objects.equals(getQuartile(), "Q1")){
                            pi = -5;
                        }else if (Objects.equals(getQuartile(), "Q2")){
                            pi = -4;
                        }else if (Objects.equals(getQuartile(), "Q3")){
                            pi = -3;
                        }else{
                            pi = -2;
                        }
                        break;
                    case 2:
                    case 3:
                        if (Objects.equals(getQuartile(), "Q1")) {
                            pi = -2;
                        }else if (Objects.equals(getQuartile(), "Q2")) {
                            pi = -2;
                        }else if (Objects.equals(getQuartile(), "Q3")) {
                            pi = -1;
                        }else{
                            pi = -1;
                        }
                        break;
                }
            }else {
                situation.append("Revisions... ");
            }
        }while(rev == 1);
        result.put(pi, situation.toString());
        return result;
    }
}
