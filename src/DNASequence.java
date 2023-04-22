// Import statements go here.  For example,
// import java.awt.Color;
// import java.util.ArrayList;
// import java.util.Random;

/**
 *  Programming Project 4: BLAST<br>
 *
 *  This class represents a DNA sequence - it contains the GI, the description,
 *  and the (partial) DNA sequence.
 *
 *  <br> <br>
 *  Created: <br>
 *    30 May 2017, Danielle Sarafian<br>
 *
 *  @author Danielle Sarafian
 *  @version 30 May 2017
 */
public class DNASequence
{
	String gi;
	String description;
	String seq;
	
  // Constructors

    /**
     * Constructs a new object of this class.
     *      @param   gi the GenIdent number for this DNA sequence
     *      @param description the description of this DNA sequence
     *      @param sequence this DNA sequence
     *      
     */
    public DNASequence(String gi, String description, String sequence)
    {
        this.gi = gi;
        this.description = description;
        this.seq = sequence;
    }

  // Methods

    /**
     * Gets the DNA sequence
     *      @return the DNA sequence
     */
    public String getSeq()
    {
        return seq;
    }
    
    /**
     * Gets the GI
     *		@return the DNA sequence's GI
     */
    public String getGI()
    {
    	return gi;
    }
    
    /**
     * Gets the description
     * 		@return the description of the DNA sequence
     */
    public String getDescription()
    {
    	return description;
    }

    /**
     * Formats the DNA sequence and info into a String
     * 
     * @return the DNA sequence and info as a String
     */
    public String toString()
    {
    	String allInfo = new String("GI: " + gi + "\nDescription: " + description + "\nSequence: " + seq);
		return allInfo;
    }

}
