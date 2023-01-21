package Business;

import java.util.ArrayList;

public class ResearchGroup {

    private ArrayList<Player> playersArray;

    //constructor
    public ResearchGroup(ArrayList<Player> playersArray) {
        this.playersArray = playersArray;
    }
    public ResearchGroup(){
        this.playersArray = new ArrayList<>();
    }
    //getter
    /**
     * @return the ArrayList of Players
     */
    public ArrayList<Player> getPlayersArray() {
        return playersArray;
    }
    //setter
    /**
     * sets an ArrayList to the ArrayList of Players
     * @param playersArray the ArrayList to be placed
     */
    public void setPlayersArray(ArrayList<Player> playersArray) {
        this.playersArray = playersArray;
    }
    //methods
    /**
     * adds a new Player
     * @param newPlayer the Player to add
     */
    public void add(Player newPlayer) {
        this.playersArray.add(newPlayer);
    }
    /**
     * @param i position of the Player in the ArrayList of Players
     * @return Player
     */
    public Player get(int i) {
        return this.playersArray.get(i);
    }
    /**
     * sets a Player into the ArrayList (desired position)
     * @param i position to add the Player in
     * @param editPlayer Player to add in
     */
    public void set(int i, Player editPlayer) {
        this.playersArray.set(i, editPlayer);
    }
}
