import java.util.ArrayList;

// Import statements go here.  For example,
// import java.awt.Color;
// import java.util.ArrayList;
// import java.util.Random;

/**
 *  Programming Project 1: Indexing Substrings<br>
 *
 *  This class bundles a particular substring along with the list of its locations.
 *
 *  <br> <br>
 *  Created: <br>
 *     6 April 2017, Danielle Sarafian<br>
 *     With assistance from:  [people who helped (including instructor/TAs)]<br>
 *  Modifications: <br>
 *     15 April 2017, Danielle Sarafian, update toString method and add getSubstring method<br>
 *     17 April 2017, Danielle Sarafian, add comments for clarification<br>
 *
 *  @author Danielle Sarafian   [with assistance from Dr. Gerry Howser]
 *  @version 17 April 2017
 */
public class SubstringLocs
{
  // State: instance variables and shared class variables go here.
	
	// String of substrings
	private String substring;
	
	// Each object will have an arraylist of locations
	private ArrayList<Location> locations;
	
	private String sequence;
	private String finalString;
		
  // Constructors

    /**
     * Constructs a new object of this class.
     * 
     *      @param   substring    the DNA substring
     *      @param	 sequence	  the DNA sequence that the substring is coming from
     */
    public SubstringLocs(String substring, String sequence)
    {
        // initialize instance variables
    	this.sequence = sequence;
        this.substring = substring;
        locations = new ArrayList<Location>();
    }
    
  // Methods
    
    /**
     * Adds a location to a substring.
     * 
     *      @param	loc	the Location object for the substring location
     *      			that is to be added
     */
    public void addLocation(Location loc)
    {
    	// Add to locations ArrayList
    	locations.add(loc);
    }
    
    /**
     * Gets the substring for the current SubstringLocs object
     * 
     * 		@return		the substring of the current StringLocs object
     */
    public String getSubstring()
    {
    	return substring;
    }
    
    /**
     * Gets the sequence that the substring came from.
     * 
     * 		@return the sequence the substring came from
     */
    public String getSequence()
    {
    	return sequence;
    }
    
    /**
     * Gets the substring locations array
     * 
     * 		@return locations array
     */
    public ArrayList<Location> getLocations()
    {
    	return locations;
    }
    
    /**
     *Formats the information for a substring and its locations as a String.
     * 
     *      @return     the information for a substring and its locations
     */
    public String toString()
    {
    	// Initialize a String object
    	String allLocations = "";

    	// Go through each location object in the ArrayList
    	for (Location loc : locations)
    	{
    		// Convert the Location object to a String
    		String locString = loc.toString();
    		
    		// Update the String of locations
    		allLocations = allLocations + locString;
    	}
    	
    	// Label the Locations with the substring
    	finalString = substring + allLocations;
    	return finalString;
    }

}