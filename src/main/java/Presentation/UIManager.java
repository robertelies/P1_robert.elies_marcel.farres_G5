package Presentation;

import Business.Edition;
import Business.Player;
import Business.ResearchGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UIManager {

    //MENU INTERFACES
    /**
     * prints the very first interface
     * @return the option selected
     */
    public static char printFirstInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to The Trials. Who are you?\n");
        System.out.println("\tA) The Composer");
        System.out.println("\tB) This year’s Conductor\n");
        System.out.println("Enter a role:");
        return scanner.next().charAt(0);
    }
    /**
     * prints the main menu interface
     * @return the option
     */
    public static int managementInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEntering management mode...\n");
        System.out.println("\t1) Manage Trials");
        System.out.println("\t2) Manage Editions\n");
        System.out.println("\t3) Exit\n");
        System.out.println("Enter an option: ");
        return scanner.nextInt();
    }
    /**
     * prints the last message of the program
     */
    public void lastMsg() {
        System.out.println("\nShutting down...");
    }
    //ERROR MESSAGES
    /**
     * prints a generic error message
     */
    public void defaultErrorMsg() {
        System.out.println("ERROR, read cleverly please and pick a correct input\n\n");
    }
    /**
     * prints a Trial-type error message
     * it depends on the number of error it prints either one error message or another (depending on the error)
     * @param errorNum is the type of error
     */
    public void displayErrorTrial(int errorNum) {
        if(errorNum == 1){
            System.out.println("ERROR, read cleverly please the name should neither be empty or repeated\n\n");
        }
        if(errorNum == 2){
            System.out.println("ERROR, read cleverly please the name should not be empty\n\n");
        }
        if(errorNum == 3){
            System.out.println("ERROR, read cleverly please and pick a correct input [Q1 - Q2 - Q3 - Q4]\n\n");
        }
        if(errorNum == 4){
            System.out.println("ERROR, read cleverly please and pick a correct input [0 - 100]\n\n");
        }
        if(errorNum == 5){
            System.out.println("ERROR, all probability fields should add 100\n\n");
        }
        if(errorNum == 6){
            System.out.println("ERROR, read cleverly please and pick a correct input [60 - 120]\n\n");
        }
        if(errorNum == 7){
            System.out.println("ERROR, read cleverly please and pick a correct input [1 - 10]\n\n");
        }
        if(errorNum == 8){
            System.out.println("ERROR, read cleverly please and pick a correct input [1000 - 2000000000]\n\n");
        }
    }
    /**
     * prints a Edition-type error message
     * it depends on the number of error it prints either one error message or another (depending on the error)
     * @param errorNum is the type of error
     */
    public void displayErrorEdition(int errorNum) {
        if(errorNum == 1){
            System.out.println("ERROR, year must be greater than or equal to the current one and unique across all editions\n\n");
        }
        if(errorNum == 2){
            System.out.println("ERROR, read cleverly please and pick a correct input [1 - 5]\n\n");
        }
        if(errorNum == 3){
            System.out.println("ERROR, read cleverly please and pick a correct input [3 - 12]\n\n");
        }
        if(errorNum == 4){
            System.out.println("ERROR, please pick a correct Trial\n\n");
        }
        if(errorNum == 5){
            System.out.println("ERROR, confirmation failed\n");
        }
    }
    //TRIAL PART
    /**
     * prints the Trial menu interface
     * @return the option
     */
    public static char trialManagementInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\ta) Create Trial");
        System.out.println("\tb) List Trials");
        System.out.println("\tc) Delete Trial\n");
        System.out.println("\td) Back\n");
        System.out.println("Enter an option: ");
        return scanner.next().charAt(0);
    }
    /**
     * prints the first part of the Create Trial interface
     * @return the Trial type
     */
    public int createTrialInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t--- Trial types ---\n");
        System.out.println("\t1) Paper publication");
        System.out.println("\t2) Master studies");
        System.out.println("\t3) Doctoral thesis defense");
        System.out.println("\t4) Budget request\n");
        System.out.println("Enter the trial’s type: ");
        int typeTrial = scanner.nextInt();
        String trash = scanner.nextLine();
        return typeTrial;
    }
    /**
     * @param type selects which type of String we are scanning
     * @return the name or journal or quartile
     */
    public String createTrial1InterfaceName(int type) {
        Scanner scanner = new Scanner(System.in);
        if(type == 1){
            System.out.println("\nEnter the trial’s name: ");
            return scanner.nextLine();
        }else if(type == 2){
            System.out.println("Enter the journal’s name: ");
            return scanner.nextLine();
        }else{
            System.out.println("Enter the journal’s quartile: ");
            return scanner.nextLine();
        }
    }
    /**
     * @param type selects which probability we are scanning
     * @return probability integer
     */
    public int createTrialInterfaceProb(int type) {
        Scanner scanner = new Scanner(System.in);
        if(type == 1){
            System.out.println("Enter the acceptance probability: ");
            return scanner.nextInt();
        }
        else if(type == 2){
            System.out.println("Enter the revision probability: ");
            return scanner.nextInt();
        }else{
            System.out.println("Enter the rejection probability: ");
            return scanner.nextInt();
        }
    }
    /**
     * @return the scan from the user for the Master's name
     */
    public String createTrial2InterfaceName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the master’s name: ");
        return scanner.nextLine();
    }
    /**
     * @param type tells which attribute are we scanning from the user
     * @return the scan from the user of either credits number or probability
     */
    public int createTrial2InterfaceNum(int type) {
        Scanner scanner = new Scanner(System.in);
        if (type == 1) {
            System.out.println("Enter the master’s ECTS number: ");
        }else{
            System.out.println("Enter the credit pass probability: ");
        }
        return scanner.nextInt();
    }
    /**
     * @return the scan from the user for the Thesis name of study
     */
    public String createTrial3InterfaceName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the thesis field of study: ");
        return scanner.nextLine();
    }
    /**
     * @return the scan from the user of difficulty
     */
    public int createTrial3InterfaceNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the defense difficulty: ");
        return scanner.nextInt();
    }
    /**
     * @return the scan from the user for the Entity name
     */
    public String createTrial4InterfaceName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the entity’s name: ");
        return scanner.nextLine();
    }
    /**
     * @return the scan from the user of budget amount
     */
    public long createTrial4InterfaceNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the budget amount: ");
        return scanner.nextLong();
    }
    /**
     * prints that the Trial was successfully created
     */
    public void finalMsgCreateTrial() {
        System.out.println("\nThe trial was created successfully!\n");
    }
    /**
     * @param allTrialNames a ArrayList of String containing all Trial names
     * @return the Trial to list
     */
    public int listTrialInterface(List<String> allTrialNames) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        System.out.println("Here are the current trials, do you want to see more details or go back?\n");
        while(i < allTrialNames.size()){
            System.out.println("\t" + (i + 1) + ")" + allTrialNames.get(i));
            i++;
        }
        System.out.println("\n\t" + (i + 1) +") Back");
        System.out.println("\nEnter an option:");
        return scanner.nextInt();
    }
    /**
     * prints the Trial to List
     * @param toString is the String containing all the information to print of the Trial (created in Trial class)
     */
    public void listOneTrial(String toString) {
        System.out.println(toString);
    }
    /**
     * @param allTrialNames an ArrayList of String containing all Trial names
     * @return the Trial to delete
     */
    public int deleteTrialInterface(List<String> allTrialNames) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        System.out.println("Which trial do you want to delete?\n");
        while(i < allTrialNames.size()){
            System.out.println("\t" + (i + 1) + ")" + allTrialNames.get(i));
            i++;
        }
        System.out.println("\n");
        System.out.println("\t" + (i + 1) +") Back");
        System.out.println("\nEnter an option:");
        return scanner.nextInt();
    }
    /**
     * @return the Trial name of the Trial to delete (for confirmation)
     */
    public String checkTrialToDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the trial’s name for confirmation:");
        return scanner.nextLine();
    }
    /**
     * prints that the Trial was deleted successfully
     * @param phase type int that says if the deletion was successful (the names concided) or if not
     */
    public void deleteTrialInterfaceEnd(int phase) {
        if(phase == 1){
            System.out.println("ERROR, confirmation failed\n");
        }else{
            System.out.println("The trial was successfully deleted.\n");
        }
    }
    //EDITION PART
    /**
     * prints the Edition menu interface
     * @return the option
     */
    public static char trialEditionInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\ta) Create Edition");
        System.out.println("\tb) List Editions");
        System.out.println("\tc) Duplicate Edition");
        System.out.println("\td) Delete Edition\n");
        System.out.println("\te) Back\n");
        System.out.println("Enter an option: ");
        return scanner.next().charAt(0);
    }
    /**
     * prints the create Edition interface
     * @param part int that decides which parameter needs to be scanned
     * @return they year or number of players or number of Trials
     */
    public int getEditionInt(int part) {
        Scanner scanner = new Scanner(System.in);
        if(part == 0){
            System.out.println("\nEnter the edition’s year: ");
            return scanner.nextInt();
        }else if(part == 1){
            System.out.println("Enter the initial number of players: ");
            return scanner.nextInt();
        }else{
            System.out.println("Enter the number of trials: ");
            return scanner.nextInt();
        }
    }
    /**
     * prints all the Trials from the ArrayList of Trials
     * @param allTrialNames ArrayList of String containing all Trial names
     */
    public void trialEditionFill(List<String> allTrialNames) {
        int i = 0;
        System.out.println("\t--- Trials ---\n");
        while(i < allTrialNames.size()){
            System.out.println("\t" + (i + 1) + ")" + allTrialNames.get(i));
            i++;
        }
        System.out.println("\n");
    }
    /**
     * @param counter position of the Trial in the allTrials ArrayList
     * @param initNumTrials total number of Trials
     * @return the specific trial that chooses the suer (saves position in the Trial ArrayList)
     */
    public int pickTrial(int counter, int initNumTrials) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pick a trial (" + counter + "/" + initNumTrials + "): ");
        return scanner.nextInt();
    }
    /**
     * prints that the Edition was successfully created
     */
    public void finalMsgCreateEdition() {
        System.out.println("\nThe edition was created successfully!\n");
    }
    /**
     * prints the Edition selected by the user including
     * year, number of players and the trials names from the trials from that Edition
     * @param editionToPrint Edtition sent to Print in display
     */
    public void listOneEdition(Edition editionToPrint) {
        System.out.println("\nYear: " + editionToPrint.getYear() + "\nPlayers: " +
                editionToPrint.getInitNumPlayers() + "\nTrials: ");
        int i = 1;
        while(i <= editionToPrint.getNumTrials()){
            System.out.println("\t" + i + "- " + editionToPrint.getSavedTrials().get(i - 1));
            i++;
        }
        System.out.println("\n");
    }
    /**
     * prints the menu interface to work with Editions
     * @param allEditionYears integer ArrayList with all the years of all the Editions
     * @param phase int that selects which option to work with on the Edition editor
     * @return the option the user wants to do
     */
    public int multipleEditionInterface(ArrayList<Integer> allEditionYears, int phase) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        if(phase == 0){
            System.out.println("Here are the current editions, do you want to see more details or go back?\n");
            while(i < allEditionYears.size()){
                System.out.println("\t" + (i + 1) + ") The Trials " + allEditionYears.get(i));
                i++;
            }
            System.out.println("\n\t" + (i + 1) +") Back");
            System.out.println("\nEnter an option:");
            return scanner.nextInt();
        }else if(phase == 1){
            System.out.println("Which edition do you want to clone?\n");
            while(i < allEditionYears.size()){
                System.out.println("\t" + (i + 1) + ") The Trials " + allEditionYears.get(i));
                i++;
            }
            System.out.println("\n\t" + (i + 1) +") Back");
            System.out.println("\nEnter an option:");
            return scanner.nextInt();
        }else{
            System.out.println("Which edition do you want to delete?\n");
            while(i < allEditionYears.size()){
                System.out.println("\t" + (i + 1) + ") The Trials " + allEditionYears.get(i));
                i++;
            }
            System.out.println("\n\t" + (i + 1) +") Back");
            System.out.println("\nEnter an option:");
            return scanner.nextInt();
        }
    }
    /**
     * prints that the Edition was deleted successfully
     */
    public void deleteEditionInterface() {
        System.out.println("\nThe edition was successfully deleted.\n");
    }
    /**
     * @return the year of the Edition to confirm it is the correct one to delete
     */
    public int confirmYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the edition’s year for confirmation: ");
        return scanner.nextInt();
    }
    /**
     * @return the new year the user wants to put for the duplication of the Edition
     */
    public int newYearInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the new edition’s year: ");
        return scanner.nextInt();
    }
    /**
     * @return the new number of players the user wants to put for the duplication of the Edition
     */
    public int newPlayersInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new edition’s initial number of players: ");
        return scanner.nextInt();
    }
    /**
     * prints that the Edition was correctly duplicated
     */
    public void duplicateEditionFinalMsg() {
        System.out.println("\nThe edition was cloned successfully!\n");
    }
    //CONDUCTOR PART
    /**
     * prints the first message of the Execution interface
     */
    public void conductorBegin() {
        System.out.println("\nEntering execution mode...\n");
    }
    /**
     * prints error message if the Current Edition dues not exist
     */
    public void conductorError() {
        System.out.println("No edition is defined for the current year (2022).\nShutting down...\n");
    }
    /**
     * @param num sorted number of the Player
     * @param numPlayers total number of Players
     * @return the names of the players
     */
    public String scanNames(int num, int numPlayers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the player’s name (" + num + "/" + numPlayers + "): ");
        return scanner.nextLine();
    }
    /**
     * prints the Execution interface title when executing
     */
    public void executionBegin() {
        System.out.println("--- The Trials 2022 ---\n");
    }
    /**
     * prints last message when we want to save the execution to end it later
     */
    public void saveShut() {
        System.out.println("\nSaving & Shutting down...\n");
    }
    /**
     * prints either if the players win or lost
     * @param numPlayers total number of players
     * @param disqualifications int indicating how many disqualifications were done
     */
    public void endExecution(int numPlayers, int disqualifications) {
        if(disqualifications == numPlayers){
            System.out.println("\nTHE TRIALS 2021 HAVE ENDED - PLAYERS LOST\n");
        }else{
            System.out.println("\nTHE TRIALS 2021 HAVE ENDED - PLAYERS WON\n");
        }
        System.out.println("Shutting down...");
    }
    /**
     * @param evolution ArrayList containing if any Player has an evolution of type
     * @param allPlayers ArrayList containing all the Players
     * @return the decision of the user to continue with execution or not
     */
    public String continueExecution(ArrayList<Integer> evolution, ResearchGroup allPlayers) {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < allPlayers.getPlayersArray().size(); i++){
            if(evolution.get(i) == 2){
                System.out.println(allPlayers.getPlayersArray().get(i).getName() + " is now a master (with 5 PI). ");
            }
            if(evolution.get(i) == 3){
                System.out.println(allPlayers.getPlayersArray().get(i).getName() + " is now a doctor (with 5 PI). ");
            }
        }
        System.out.println("Continue the execution? [yes/no]: ");
        return scanner.nextLine();
    }
    /**
     * prints the execution results of a singular trial for a singular player
     * @param currentPlayer the current player that is executing the Trial
     * @param s String that has the message for the execution (failed or passed)
     * @param typePrint int that indicates which type of Trial we are executing
     * @param typePlayer int that indicates which type of Player is executing the Trial
     */
    public void printExecution(Player currentPlayer, String s, int typePrint, int typePlayer) {
        String name = currentPlayer.getName();
        if(typePlayer == 2){
            name = "Master " + name;
        }else if(typePlayer == 3){
            name = name + ", PhD";
        }
        if(typePrint == 1){
            if(currentPlayer.getScore() <= 0){
                System.out.println(name + " is submitting... " + s + "PI count: 0 - Disqualified!");
            }
            else{
                System.out.println(name + " is submitting... " + s + "PI count: " + currentPlayer.getScore());
            }
        }else if (typePrint == 2){
            if(currentPlayer.getScore() <= 0){
                System.out.println(name + s + "PI count: 0 - Disqualified!");
            }
            else{
                System.out.println(name + s + "PI count: " + currentPlayer.getScore());
            }
        }else if (typePrint == 3){
            if(currentPlayer.getScore() <= 0){
                System.out.println(name + s + "PI count: 0 - Disqualified!");
            }else{
                System.out.println(name + s + "PI count: " + currentPlayer.getScore());
            }
        }else{
            if(currentPlayer.getScore() <= 0){
                System.out.println(name + " PI count: 0 - Disqualified!");
            }else{
                System.out.println(name + " PI count: " + currentPlayer.getScore());
            }
        }
    }
    /**
     * prints the number of the Trial and its name before executing it
     * @param numTrial number of the Trial in the Edition to execute
     * @param nameTrial name of the Trial in the Edition to execute
     */
    public void trialBeginExec(int numTrial, String nameTrial) {
        System.out.println("Trial #" + (numTrial + 1) + " – " + nameTrial);
    }
    /**
     * function that Prints if the ResearchGroup has got or not the Budget (once)
     * @param s the string to print
     */
    public void printBudget(String s) {
        System.out.println("\n" + s);
    }
    //JSON PART
    /**
     * we print the two types of persistence (CSV, JSON)
     * @return the Type of Persistence
     */
    public String choosePersistence() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The IEEE needs to know where your allegiance lies.");
        System.out.println("\n\tI) People’s Front of Engineering (CSV)\n\tII) Engineering People’s Front (JSON)\n");
        System.out.println("Pick a faction: ");
        return scanner.nextLine();
    }
}