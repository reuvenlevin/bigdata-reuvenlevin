package ds264.bigdata.storage;

import ds264.bigdata.Storeable;

import java.math.BigInteger;


/**
 * Implement all Storeable operations using Arrays. Use 2 parallel arrays, one for the issue ID
 * and one for the corresponding issue description at that same index.
 */
public class StoreInArray implements Storeable {
    protected String[] descriptions;
    protected String[] IDs;

    int nextIndex;  // next free index into array, also represents number of items in array

    /**
     * Constructor - allocate storage for desired number of rows, initialize counters.
     *
     * @param maxrows
     */
    public StoreInArray(int maxrows) {
        descriptions = new String[maxrows];
        IDs = new String[maxrows];
        nextIndex = 0;
    }

    @Override
    public Boolean addRow(String issueID, String issueDesc) {
        // Adding capacity to ensure row is added
        if(nextIndex >= IDs.length) {
            if(!getStorage()){
                return false;
            }
            getStorage();
        }

        IDs[nextIndex] = issueID;
        descriptions[nextIndex] = issueDesc;
        nextIndex++;

        return true; //TODO add bounds check?/Covered on line 32
    }

    @Override
    public int getNumRows() {
        return nextIndex;
    }

    @Override
    public String getDescByID(String issueID) {
        // Search IDs array for index of desired issue ID
        for (int ix = 0; ix < nextIndex; ix++) {
            if (issueID.equals(IDs[ix])) {
                return descriptions[ix];    // get the description at corresponding index
            }
        }
        return null;    // couldn't be found
    }
    // Loop through array to check if ID already exists in array. If it does, an exception is thrown,
    public void existingNumber(String issueID)  {
        for (int i = 0; i < nextIndex; i++){
            if(issueID.equals(IDs[i])){
                throw new IllegalArgumentException("ID already exists. please enter a new ID");

            }
        }
    }
    /**
     * Create variable newCapacity to enlarge arrays sizes
     * If newCapacity reaches greater than Integer.MAX_VALUE capacity it would become negative
     * Create new arrays and copy over elements
     * Assign new arrays to new arrays
     * @return true if storage was added. Otherwise, returns false
     */
    public Boolean getStorage(){
        int newCapacity = IDs.length * 2;
        if(newCapacity < 0){
            return false;
        }
        String[] newIDs = new String[newCapacity];
        String[] newDescriptions = new String[newCapacity];
        System.arraycopy(IDs, 0, newIDs, 0, nextIndex);
        System.arraycopy(descriptions, 0, newDescriptions, 0, nextIndex);

        IDs = newIDs;
        descriptions = newDescriptions;
        return true;
    }
    /**
     * Find the index of the ID going to be deleted
     * Create new arrays and copy over elements and skip the index
     * Assign new arrays to new arrays
     * @return true if ID was deleted. Otherwise, If index is not found, returns false
     */
    public Boolean deleteRow(String issueID){
        int index = -1;
        for(int i = 0; i < IDs.length; i++){
            if(issueID.equals(IDs[i])){
                index = i;
                break;
            }
        }
        if(index == -1){
            return false;
        }
        String[] newID = new String[IDs.length-1];
        String[] newDesc = new String[IDs.length-1];
        for(int i=0, k =0;i<nextIndex;i++){
            if(i!=index){
                newID[k]=IDs[i];
                newDesc[k]=descriptions[i];
                k++;
            }
        }
        IDs = newID;
        descriptions = newDesc;
        nextIndex--;
        return true;

    }
}
