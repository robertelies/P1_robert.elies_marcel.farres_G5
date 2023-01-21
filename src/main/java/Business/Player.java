package Business;

public class Player {

    private String name;
    private int score;

    //constructor
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
    //getters
    /**
     * @return the name of the Player
     */
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    //setters
    /**
     * sets the name of the Player
     * @param name the name of the Player
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * sets the score of the Player
     * @param score the score of the Player
     */
    public void setScore(int score) {
        this.score = score;
    }
}
