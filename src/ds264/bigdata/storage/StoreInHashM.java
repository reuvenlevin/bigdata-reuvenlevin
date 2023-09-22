package ds264.bigdata.storage;

import ds264.bigdata.Storeable;

import java.util.HashMap;
import java.util.Objects;

/**
 * Implement all Storeable operations using HashMap.
 */
public class StoreInHashM implements Storeable {
    HashMap<String, String> issues;


    /**
     * Constructor - allocate storage for desired number of rows.
     *
     * @param maxrows
     */
    public StoreInHashM(int maxrows) {
        issues = new HashMap<>(maxrows, 0.75f);
    }

    @Override
    public Boolean addRow(String issueID, String issueDesc) {
        // Check if the ID only contains digits or empty input
        if(!issueID.matches("[0-9]+") || issueID.equals(" ")){
            throw new IllegalArgumentException("Invalid input. Please provide a valid Issue ID");
        }
        issues.put(issueID, issueDesc);
        return true;    //TODO add any error checking ?
    }

    @Override
    public int getNumRows() {
        return issues.size();
    }

    @Override
    public String getDescByID(String issueID) {
        return issues.get(issueID);
    }

    @Override
    public void existingNumber(String issueID) {
        if(issues.containsKey(issueID)){
            throw new IllegalArgumentException("ID already exists. please enter a new ID");

            }

    }
    @Override
    public Boolean deleteRow(String issueID) {
        if(!issues.containsKey(issueID)){
            return false;
        }
        issues.remove(issueID);
        return true;
    }

}
