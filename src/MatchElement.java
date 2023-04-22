// Import statements go here.  For example,
// import java.awt.Color;
// import java.util.ArrayList;
// import java.util.Random;

/**
 *  Programming Project 4: BLAST<br>
 *
 *	This class stores information for each match 
 *	longer than a given threshold.
 *
 *  <br> <br>
 *  Created: <br>
 *    30 May 2017, Danielle Sarafian<br>

 *  Modifications: <br>
 *     [the date], [your name(s)], [the reason]<br>
 *
 *  @author Danielle Sarafian
 *  @version 30 May 2017
 */
public class MatchElement
{
	private int length;
	private int qIndex;
	private Location dbLoc;
	
  // Constructors

    /**
     * Constructs a new object of this class.
     *      @param length the length of the match
     *      @param qIndex the index of the match in the query
     *      @param dbLoc the location of the match in the database (the key)
     */
    public MatchElement(int length, int qIndex, Location dbLoc)
    {
    	this.length = length;
    	this.qIndex = qIndex;
    	this.dbLoc = dbLoc;
    }

  // Methods

    /**
     * Gets the length of the match
     *      @return the length of the match
     */
    public int getMatchLength()
    {
        return length;
    }

    /**
     * Gets the index of where the query starts
     * 
     *      @return     the index where the query starts
     */
    public int getQueryIndex()
    {
        return qIndex;
    }
    
    /**
     * Gets the location of the match in the database
     */
    public Location getMatchLoc()
    {
    	return dbLoc;
    }
    
    /**
     * Makes the MatchElement into a String
     * 		@return the match element as a String
     */
    public String toString()
    {
    	String info = new String("\nMatch length: " + length +
    			"\nQuery index: " + qIndex + "\nMatch location "
    					+ "in the DNA database: " + dbLoc);
    	return info;
    }
}
