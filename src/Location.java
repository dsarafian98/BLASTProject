// Import statements go here.  For example,
// import java.awt.Color;
// import java.util.ArrayList;
// import java.util.Random;

/**
 *  Programming Project 4: BLAST<br>
 *
 *  This class represents a pairing of a sequence number and a 
 *  position within that sequence.
 *  
 *  <br> <br>
 *  Created: <br>
 *     5 April 2017, Danielle Sarafian<br>
 *  Modifications: <br>
 *     6 April 2017, Danielle Sarafian, rewrite toString method<br>
 *     1 June 2017, Danielle Sarafian, add getSeqNum and getStartIndex methods
 *
 *  @author Danielle Sarafian
 *  @version 1 June 2017
 */
public class Location
{
	// State: instance variables and shared class variables go here.
	int seq;
	int startLoc;

	// Constructors

	/**
	 * Constructs a new object of this class.
	 *      @param   sequence    the number of the DNA sequence string
	 *      @param	 startLoc	 the starting location of the DNA string
	 */
	public Location(int sequence, int startLoc)
	{
		// initialize instance variables
		this.seq = sequence;
		this.startLoc = startLoc;
	}

	// Methods
	/**
	 * Gets the sequence number
	 * 		@return the sequence number
	 */
	public int getSeqNum()
	{
		return seq;
	}
	
	/**
	 * Gets the start location
	 * 		@return the index where the substring starts
	 */
	public int getStartIndex()
	{
		return startLoc;
	}
	
	/**
	 * Turns this location into a String.
	 *      @return the location as a String
	 */
	public String toString()
	{
		String location = new String("(" + Integer.toString(seq) + "," + Integer.toString(startLoc) + ")");
		return location;
	}
}