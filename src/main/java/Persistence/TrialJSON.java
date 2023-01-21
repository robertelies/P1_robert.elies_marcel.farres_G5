package Persistence;

import Business.*;
import java.util.ArrayList;

public class TrialJSON {
    private String nameEntity;
    private long moneyRequest;
    private String field;
    private int difficulty;
    private String nameStudy;
    private int numCredits;
    private int probability;
    private String namePublication;
    private String quartile;
    private int probAccept;
    private int probRevision;
    private int probReject;
    private String nameTrial;
    private int typeTrial;

    //methods
    /**
     * this function parses all the information of one cell of the Trial array that takes all the info from the TRIAL.JSON
     * and adds it to the allTrials ArrayList
     * @param allTrials ArrayList of all the Trials
     */
    public void parseTrialsJson(ArrayList<Trial> allTrials) {
        switch (this.typeTrial) {
            case 1 -> allTrials.add(new PaperSubmission(this.nameTrial, this.namePublication, this.quartile, this.probAccept, this.probRevision, this.probReject, this.typeTrial));
            case 2 -> allTrials.add(new MasterStudies(this.nameStudy, this.numCredits, this.probability, this.nameTrial, this.typeTrial));
            case 3 -> allTrials.add(new DoctoralThesis(this.field, this.difficulty, this.nameTrial, this.typeTrial));
            case 4 -> allTrials.add(new BudgetRequest(this.nameEntity, this.moneyRequest, this.nameTrial, this.typeTrial));
        }
    }
}
