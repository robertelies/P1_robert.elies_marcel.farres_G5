package Persistence;

import Business.Trial;
import java.io.IOException;
import java.util.ArrayList;

public interface TrialDAO {
    /**
     * is in charge of writing all the data from the program
     * @param allTrials ArrayList containing all the Trials
     */
    void updateTrialFile(ArrayList<Trial> allTrials) throws IOException;
    /**
     *is in charge of reading and saving all the data from the file (csv or json)
     * @param allTrials ArrayList containing all the Trials
     */
    void readFileTrial(ArrayList<Trial> allTrials);
    /**
     * deletes all the data from the file
     */
    void deleteTrialFile() throws IOException;
}
