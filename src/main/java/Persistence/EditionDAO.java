package Persistence;

import Business.Edition;
import java.io.IOException;
import java.util.ArrayList;

public interface EditionDAO {
    /**
     * deletes all the data from the file
     */
    void deleteEditionFile() throws IOException;
    /**
     * is in charge of reading and saving all the data from the file (csv or json)
     * @param allEditions ArrayList that has all the Editions
     */
    void readFileEdition(ArrayList<Edition> allEditions);
    /**
     * is in charge of writing all the data from the program
     * @param allEditions ArrayList that contains all the Editions
     */
    void updateEdition(ArrayList<Edition> allEditions) throws IOException;
}
