package Persistence;

import Business.CurrentEdition;
import java.io.IOException;

public interface ExecutionDAO {
    /**
     * is in charge of reading and saving all the data from the file (csv or json)
     * @return the CurrentEdition filled with information
     */
    CurrentEdition readFileExecution();
    /**
     * deletes all the data from the file
     */
    void deleteExecutionFile() throws IOException;
    /**
     * is in charge of writing all the data from the program
     * @param currentEdition Edition to execute (2022)
     */
    void updateExecution(CurrentEdition currentEdition) throws IOException;
}
