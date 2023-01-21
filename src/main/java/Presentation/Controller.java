package Presentation;

import Business.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Controller {
    private final UIManager uInterface;
    private TrialManager trialM;
    private EditionManager editionM;
    private ExecutionManager execM;

    public Controller() {
        uInterface = new UIManager();
    }
    /**
     * controls the main menu of the code
     */
    public void run() throws IOException {
        boolean end = false;
        String typePersistence;
        //here the user selects which persistence wants to use
        do{
            typePersistence = uInterface.choosePersistence();
        }while((!typePersistence.equals("I")) && (!typePersistence.equals("II")));
        editionM = new EditionManager(typePersistence);
        trialM = new TrialManager(typePersistence);
        execM = new ExecutionManager(typePersistence);
        do {
            char roleUser = UIManager.printFirstInterface();
            switch (roleUser) {
                case 'A' -> {
                    int optionManagement;
                    do {
                        optionManagement = UIManager.managementInterface();
                        switch (optionManagement) {
                            case 1 -> useTrials();
                            case 2 -> useEditions();
                            case 3 -> {
                                end = true;
                                exit();
                            }
                            default -> uInterface.defaultErrorMsg();
                        }
                    } while (optionManagement != 3);
                }
                case 'B' -> {
                    executeConductor();
                    end = true;
                }
                default -> uInterface.defaultErrorMsg();
            }
        }while (!end);
    }
    /**
     * calls functions to save all the information into files
     */
    private void exit() throws IOException {
        trialM.endPersistenceTrial();
        editionM.endPersistenceEdition();
        uInterface.lastMsg();
    }
    /**
     * runs the entire code related to Editions (create, list, duplicate and delete)
     */
    private void useEditions() {
        char optionEditionManagement;
        do{
            optionEditionManagement = UIManager.trialEditionInterface();
            int errorNum;
            switch (optionEditionManagement) {
                case 'a':
                    boolean trialOrderGreater = false;
                    int part = 0;
                    int editionYear = uInterface.getEditionInt(part);
                    boolean yearTrue = editionM.checkYear(editionYear);
                    if(yearTrue){
                        errorNum = 1;
                        uInterface.displayErrorEdition(errorNum);
                    }else{
                        part = 1;
                        int initNumPlayers = uInterface.getEditionInt(part);
                        boolean playersTrue = editionM.checkPlayers(initNumPlayers);
                        if(playersTrue){
                            errorNum = 2;
                            uInterface.displayErrorEdition(errorNum);
                        }else{
                            part = 2;
                            int initNumTrials = uInterface.getEditionInt(part);
                            boolean numTrialsTrue = editionM.checkNumTrials(initNumTrials);
                            if(numTrialsTrue){
                                errorNum = 3;
                                uInterface.displayErrorEdition(errorNum);
                            }else{
                                ArrayList<String> allTrialNames = trialM.getAllTrialNames();
                                ArrayList<String> trialNameEditionOrder = new ArrayList<>();
                                ArrayList<Integer> trialTypeEditionOrder = new ArrayList<>();
                                uInterface.trialEditionFill(allTrialNames);
                                int counter = 1;
                                while(counter <= initNumTrials){
                                    int trialOrderEdition = uInterface.pickTrial(counter, initNumTrials);
                                    if(trialOrderEdition > initNumTrials){
                                        counter = initNumTrials + 1;
                                        errorNum = 4;
                                        uInterface.displayErrorEdition(errorNum);
                                        trialOrderGreater = true;
                                    }else{
                                        //on this part we assign the TrialNames selected by the user to the Edition
                                        // and also the Trial types in the order picked by the user
                                        trialNameEditionOrder.add(trialM.getTrial4EditionName(trialOrderEdition - 1));
                                        trialTypeEditionOrder.add(trialM.getTrial4EditionType(trialOrderEdition - 1));
                                        counter++;
                                    }
                                }
                                if(!trialOrderGreater){
                                    editionM.editionCreation(editionYear, initNumPlayers, initNumTrials, trialNameEditionOrder, trialTypeEditionOrder);
                                    uInterface.finalMsgCreateEdition();
                                }
                            }
                        }
                    }
                    break;
                case 'b':
                    int phase = 0;
                    ArrayList<Integer> allEditionYears = editionM.getAllEditionYears();
                    int editionToList = uInterface.multipleEditionInterface(allEditionYears, phase);
                    if (editionToList != (allEditionYears.size() + 1)) {
                        Edition editionToPrint = editionM.getEdition(editionToList - 1);
                        uInterface.listOneEdition(editionToPrint);
                    }
                    break;
                case 'c':
                    phase = 1;
                    allEditionYears = editionM.getAllEditionYears();
                    int editionToDuplicate = uInterface.multipleEditionInterface(allEditionYears, phase);
                    int newEditionYear = uInterface.newYearInterface();
                    yearTrue = editionM.checkYear(newEditionYear);
                    if(yearTrue) {
                        errorNum = 1;
                        uInterface.displayErrorEdition(errorNum);
                    }else{
                        int newNumPlayers = uInterface.newPlayersInterface();
                        boolean playersTrue = editionM.checkPlayers(newNumPlayers);
                        if(playersTrue){
                            errorNum = 2;
                            uInterface.displayErrorEdition(errorNum);
                        }else{
                            uInterface.duplicateEditionFinalMsg();
                            editionM.editionDuplication(editionToDuplicate - 1, newNumPlayers, newEditionYear);
                        }
                    }
                    break;
                case 'd':
                    phase = 2;
                    allEditionYears = editionM.getAllEditionYears();
                    if(allEditionYears == null){
                        uInterface.defaultErrorMsg();
                        break;
                    }
                    int editionToDelete = uInterface.multipleEditionInterface(allEditionYears, phase);
                    if(editionToDelete == (allEditionYears.size() + 1)) {
                        break;
                    }
                    else{
                        int confirmationYear = uInterface.confirmYear();
                        boolean checkCorrectYear = editionM.sameYear(confirmationYear, editionToDelete - 1);
                        if(checkCorrectYear){
                            errorNum = 5;
                            uInterface.displayErrorEdition(errorNum);
                        }else{
                            editionM.editionDeletion(editionToDelete - 1);
                            uInterface.deleteEditionInterface();
                        }
                    }
                    break;
                default:
                    if(optionEditionManagement == 'e'){
                        break;
                    }else{
                        uInterface.defaultErrorMsg();
                    }
            }
        }while (optionEditionManagement != 'e');
    }
    /**
     * runs the entire code related to Trials (create, list and delete)
     */
    private void useTrials() {
        int type;
        char optionTrialManagement;
        do{
            optionTrialManagement = UIManager.trialManagementInterface();
            int errorNum;
            switch (optionTrialManagement) {
                case 'a':
                    int typeTrial = uInterface.createTrialInterface();
                    //here we will check which typeTrial it is, so it will depend on the creation method we will use
                    if(typeTrial == 1){
                        type = 1;
                        String tName = uInterface.createTrial1InterfaceName(type);
                        boolean tNameTrue = trialM.checkEqualName(tName);
                        if(tNameTrue){
                            errorNum = 1;
                            uInterface.displayErrorTrial(errorNum);
                        }else{
                            type = 2;
                            String jName = uInterface.createTrial1InterfaceName(type);
                            boolean tJournalTrue = trialM.checkEmpty(jName);
                            if(tJournalTrue){
                                errorNum = 2;
                                uInterface.displayErrorTrial(errorNum);
                            }else{
                                type = 3;
                                String jQuartile = uInterface.createTrial1InterfaceName(type);
                                int qTrue = trialM.checkQuartile(jQuartile);
                                if(qTrue == 1){
                                    errorNum = 3;
                                    uInterface.displayErrorTrial(errorNum);
                                }else{
                                    type = 1;
                                    int accP = uInterface.createTrialInterfaceProb(type);
                                    boolean probTrue = trialM.checkProb(accP);
                                    if(probTrue) {
                                        errorNum = 4;
                                        uInterface.displayErrorTrial(errorNum);
                                    }else{
                                        type = 2;
                                        int revP = uInterface.createTrialInterfaceProb(type);
                                        probTrue = trialM.checkProb(revP);
                                        if(probTrue) {
                                            errorNum = 4;
                                            uInterface.displayErrorTrial(errorNum);
                                        }else{
                                            type = 3;
                                            int rejP = uInterface.createTrialInterfaceProb(type);
                                            probTrue = trialM.checkProb(rejP);
                                            if(probTrue) {
                                                errorNum = 4;
                                                uInterface.displayErrorTrial(errorNum);
                                            }else{
                                                boolean probComplete = trialM.completeProb(accP, rejP, revP);
                                                if (probComplete){
                                                    errorNum = 5;
                                                    uInterface.displayErrorTrial(errorNum);
                                                }else{
                                                    trialM.trialT1Creation(tName, jName, jQuartile, accP, revP, rejP, typeTrial);
                                                    uInterface.finalMsgCreateTrial();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else if(typeTrial == 2){
                        type = 1;
                        String tName = uInterface.createTrial1InterfaceName(type);
                        boolean tNameTrue = trialM.checkEqualName(tName);
                        if(tNameTrue) {
                            errorNum = 1;
                            uInterface.displayErrorTrial(errorNum);
                        }else{
                            String masterName = uInterface.createTrial2InterfaceName();
                            boolean tMasterTrue = trialM.checkEmpty(masterName);
                            if(tMasterTrue) {
                                errorNum = 2;
                                uInterface.displayErrorTrial(errorNum);
                            }
                            else{
                                int numCredits = uInterface.createTrial2InterfaceNum(type);
                                boolean creditsTrue = trialM.checkCredits(numCredits);
                                if(creditsTrue) {
                                    errorNum = 6;
                                    uInterface.displayErrorTrial(errorNum);
                                }
                                else{
                                    type = 2;
                                    int masterProb = uInterface.createTrial2InterfaceNum(type);
                                    boolean probTrue = trialM.checkProb(masterProb);
                                    if(probTrue) {
                                        errorNum = 4;
                                        uInterface.displayErrorTrial(errorNum);
                                    }
                                    else{
                                        trialM.trialT2Creation(masterName, numCredits, masterProb, tName, typeTrial);
                                        uInterface.finalMsgCreateTrial();
                                    }
                                }
                            }
                        }
                    }else if(typeTrial == 3){
                        type = 1;
                        String tName = uInterface.createTrial1InterfaceName(type);
                        boolean tNameTrue = trialM.checkEqualName(tName);
                        if(tNameTrue) {
                            errorNum = 1;
                            uInterface.displayErrorTrial(errorNum);
                        }else {
                            String studyField = uInterface.createTrial3InterfaceName();
                            boolean tFieldTrue = trialM.checkEmpty(studyField);
                            if (tFieldTrue) {
                                errorNum = 2;
                                uInterface.displayErrorTrial(errorNum);
                            } else {
                                int difficulty = uInterface.createTrial3InterfaceNum();
                                boolean difficultyTrue = trialM.checkDifficulty(difficulty);
                                if (difficultyTrue) {
                                    errorNum = 7;
                                    uInterface.displayErrorTrial(errorNum);
                                } else {
                                    trialM.trialT3Creation(studyField,difficulty, tName, typeTrial);
                                    uInterface.finalMsgCreateTrial();
                                }
                            }
                        }
                    }else if(typeTrial == 4){
                        type = 1;
                        String tName = uInterface.createTrial1InterfaceName(type);
                        boolean tNameTrue = trialM.checkEqualName(tName);
                        if(tNameTrue) {
                            errorNum = 1;
                            uInterface.displayErrorTrial(errorNum);
                        }else {
                            String entity = uInterface.createTrial4InterfaceName();
                            boolean tEntityTrue = trialM.checkEmpty(entity);
                            if (tEntityTrue) {
                                errorNum = 2;
                                uInterface.displayErrorTrial(errorNum);
                            } else {
                                long budgetAmount = uInterface.createTrial4InterfaceNum();
                                boolean amountTrue = trialM.checkAmount(budgetAmount);
                                if (amountTrue) {
                                    errorNum = 8;
                                    uInterface.displayErrorTrial(errorNum);
                                } else {
                                    trialM.trialT4Creation(entity, budgetAmount, tName, typeTrial);
                                    uInterface.finalMsgCreateTrial();
                                }
                            }
                        }
                    } else{
                        uInterface.defaultErrorMsg();
                    }
                    break;
                case 'b':
                    ArrayList<String> allTrialNames = trialM.getAllTrialNames();
                    int trialToList = uInterface.listTrialInterface(allTrialNames);
                    if (trialToList != (allTrialNames.size() + 1)) {
                        Trial trialToPrint = trialM.getTrial(trialToList - 1);
                        uInterface.listOneTrial(trialToPrint.toString());
                    }
                    break;
                case 'c':
                    int phase;
                    allTrialNames = trialM.getAllTrialNames();
                    int trialToDelete = uInterface.deleteTrialInterface(allTrialNames);
                    if (trialToDelete != (allTrialNames.size() + 1)) {
                        String nameToDelete = uInterface.checkTrialToDelete();
                        boolean verification = trialM.compareTrialName(trialToDelete - 1, nameToDelete);
                        if (!verification) {
                            phase = 1;
                        } else {
                            phase = 2;
                            trialM.trialDeletion(trialToDelete - 1);
                        }
                        uInterface.deleteTrialInterfaceEnd(phase);
                    }
                    break;
                default:
                    if(optionTrialManagement == 'd'){
                        break;
                    }else{
                        uInterface.defaultErrorMsg();
                    }
            }
        }while (optionTrialManagement != 'd');
    }
    /**
     * runs the entire code related to the execution of the Current Year Edition
     */
    private void executeConductor() throws IOException {
        uInterface.conductorBegin();
        boolean currentEdition = editionM.checkCurrentEdition();
        if(currentEdition){
            uInterface.conductorError();
        }
        else{
            uInterface.executionBegin();
            ArrayList<Integer> evolution = new ArrayList<>();
            int i = 0;
            int disqualifications = 0;
            boolean cont = true;
            int startPlayers;
            if(execM.getCurrentEdition() == null){
                ResearchGroup researchGroup = new ResearchGroup();
                ArrayList<Integer> playerType = new ArrayList<>();
                //in the case we don't have an already created Execution (because of persistence) we create one
                //also we are selecting the names of the players while we add them to the CurrentEdition class
                //as a ResearchGroup
                while(i < editionM.getEditionByYear(2022).getInitNumPlayers()){
                    String p = (uInterface.scanNames(i + 1, editionM.getEditionByYear(2022).getInitNumPlayers()));
                    researchGroup.add(execM.addPlayer(p));
                    playerType.add(1);
                    i++;
                }
                execM.setCurrentEdition(new CurrentEdition(editionM.getEditionByYear(2022), researchGroup, playerType));
            }
            startPlayers = execM.getCurrentEdition().getInitNumPlayers();
            //this while goes through all the Trials we have on the Edition
            while(execM.getCurrentEdition().getNumTrial() < execM.getCurrentEdition().getNumTrials()){
                i = 0;
                int repeat = 0;
                //we created a HashMap to save both String and Score of each player
                //the String is the message to print on the execution (passed or not, etc..)
                Map<Integer, String> executionResult = null;
                Integer it = 0;
                uInterface.trialBeginExec(execM.getCurrentEdition().getNumTrial(), execM.getNameTrial(execM.getCurrentEdition().getNumTrial()));
                int typePrint = execM.getCurrentEdition().getOneTrialTypeSaved(execM.getCurrentEdition().getNumTrial());
                //here we save the CurrentTrial we are going to work by now
                Trial currentTrial = trialM.getTrial(execM.getCurrentEdition().getSavedTrials().get(execM.getCurrentEdition().getNumTrial()));
                //this loop is made to iterate and make all the players execute the Trial
                while(i < execM.getCurrentEdition().getInitNumPlayers()){
                    //this if condition is made because in the case of BudgetRequest Trial we only want to do the execution once
                    if(repeat == 0){
                        executionResult = currentTrial.executeTrial(execM.getCurrentEdition().getPlayersArray(), execM.getCurrentPlayer(i).getScore(), execM.getTypePlayer(i));
                        //here we save the score of the player (key on the HasMap), the value is the String
                        it = executionResult.keySet().iterator().next();
                    }
                    //on this if condition, we check that if the BudgetRequest Trial is already executed, we want to save
                    //to all the Players its corresponding score (if they win their score to add depends on their individual
                    //for each player
                    if(repeat == 1){
                        if(it > 0){
                            it = execM.getCurrentPlayer(i).getScore()/2;
                        }
                    }
                    execM.addPlayerScore(it, i);
                    //here we check that if we are executing the BudgetRequest Trial, and it is the first time
                    //we put the flag repeat, so we don't do another execution and print the message of approved/non-approved
                    if((execM.getCurrentEdition().getTypeTrials().get(execM.getCurrentEdition().getNumTrial()) == 4) && (repeat == 0)){
                        repeat = 1;
                        uInterface.printBudget(executionResult.get(it));
                    }
                    uInterface.printExecution(execM.getCurrentPlayer(i), executionResult.get(it), typePrint, execM.getTypePlayer(i));
                    evolution.add(i, execM.evolvePlayer(i));
                    if(execM.getCurrentPlayer(i).getScore() <= 0){
                        execM.disqualifyUser(i);
                        disqualifications = disqualifications + 1;
                    }else{
                        i++;
                    }
                }
                execM.getCurrentEdition().setNumTrial(execM.getCurrentEdition().getNumTrial() + 1);
                if(execM.getCurrentEdition().getInitNumPlayers() == 0){
                    uInterface.endExecution(startPlayers, disqualifications);
                    execM.deleteFileExec();
                    return;
                }
                if(execM.getCurrentEdition().getNumTrial() != (execM.getCurrentEdition().getNumTrials())){
                    String yaynay = uInterface.continueExecution(evolution, execM.getAllPlayers());
                    while(!((yaynay.equals("yes")) || (yaynay.equals("no")))){
                        uInterface.defaultErrorMsg();
                        yaynay = uInterface.continueExecution(evolution, execM.getAllPlayers());
                    }
                    if(yaynay.equals("no")){
                        cont = false;
                        execM.endPersistenceExecution();
                        break;
                    }
                }
            }
            if(!cont){
                uInterface.saveShut();
            }
            else{
                uInterface.endExecution(startPlayers, disqualifications);
                execM.deleteFileExec();
            }
        }
    }
}
