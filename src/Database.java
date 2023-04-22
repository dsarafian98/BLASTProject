import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

/**
 *  Programming Project 4: BLAST<br>
 *
 *	This class contains the DNA sequence data, it contains 
 *	the HashMap that holds all possible nn-letter words contained 
 *	in this DNA data, it has a method to find matches of substrings 
 *	of a query sequence, a method to extend those matches, and possibly
 *	 a method to display the matches.
 *
 *  <br> <br>
 *  Created: <br>
 *    30 May 2017, Danielle Sarafian<br>

 *  Modifications: <br>
 *    31 May 2017, Danielle Sarafian, write add, addHashtable, toString, and printHashtable<br>
 *    1 June 2017, Danielle Sarafian, write findMatches and extendMatch [with assistance from Andrew Parsons] <br>
 *    2 June 2017, Danielle Sarafian, edit findMatches and extendMatch [with assistance from Dr. Gerry Howser] <br>
 *    3 June 2017, Danielle Sarafian, edit findMatches and extendMatch<br>
 *    4 June 2017, Danielle Sarafian, edit findMatches and extendMatch<br>
 *
 *  @author Danielle Sarafian
 *  @version 4 June 2017
 */
public class Database
{
	// State: instance variables and shared class variables go here.
	private Hashtable<String, SubstringLocs> hashtable;
	private ArrayList<DNASequence> seqList;

	// Constructors

	/**
	 * Constructs a new object of this class.
	 */
	public Database()
	{
		hashtable = new Hashtable<String, SubstringLocs>();
		seqList = new ArrayList<DNASequence>();
	}

	// Methods

	/**
	 * Adds a DNASequence object to the arraylist of sequences
	 * 
	 * 		@param seq a DNASequence object to be added to an arraylist
	 * 		@param seqNum the number of the sequence
	 * 		@param threshold the minimum match length
	 * 		@param 	subSeqLength the length to make the subsequences
	 */
	public void add(DNASequence seq, int seqNum, int threshold, int subSeqLength)
	{
		seqList.add(seq);
		addToHashtable(seq, seqNum, threshold, subSeqLength);
	}

	/**
	 * Adds segments to hashtable
	 * 
	 *      @param  newSeq the new sequence to add
	 *      @param	seqNum the number of the sequence
	 *      @param 	threshold the minimum match length
	 *      @param 	subSeqLength the length to make the subsequences
	 */
	private void addToHashtable(DNASequence newSeq, int seqNum, int threshold, int subSeqLength)
	{
		// get the full sequence
		String sequence = newSeq.getSeq();

		for (int beginIndex = 0; beginIndex+subSeqLength  < sequence.length(); beginIndex++)
		{
			// get substring
			String subSeq = sequence.substring(beginIndex, beginIndex + subSeqLength);

			// make new location
			Location loc = new Location(seqNum, beginIndex);

			// check that the location is in the hashtable
			if (hashtable.get(subSeq) == null)
			{
				// make a new substringLocs object
				SubstringLocs newSubLocs = new SubstringLocs(subSeq, sequence);

				// add the location to the new object's array of locations
				newSubLocs.addLocation(loc);

				// add the subsequence and new substringLocs object to the hashtable
				hashtable.put(subSeq, newSubLocs);
			}
			else
			{
				// get the sequence from the hashtable and add its location to the
				// substringLocs object's arraylist of locations
				hashtable.get(subSeq).addLocation(loc);
			}
		}
	}

	/**
	 * Finds matches for the given query.
	 * 		@param query what to find matches for
	 * 		@param threshold the minimum match length
	 * 		@param subSeqLength the length to make the subsequences
	 * 		@param queryNum the number of the query
	 * 		@return ArrayList of MatchElements of matches found
	 */
	public ArrayList<MatchElement> findMatches(String query, int threshold, int subSeqLength, int queryNum)
	{
		// create empty list of matches
		ArrayList <MatchElement> matches = new ArrayList<MatchElement>();

		for (int startIndex = 0; startIndex + subSeqLength < query.length(); startIndex++)
		{
			// make a substring for each of the segments in the query
			String querySubSeq = query.substring(startIndex, startIndex + subSeqLength);

			// get substringloc objects
			SubstringLocs locs = hashtable.get(querySubSeq);

			if (locs != null)
			{
				// go through all locations in the locations arraylist
				for (Location singleLoc : locs.getLocations())
				{
					// get the length of the extended match
					int length = extendMatch(singleLoc, query, locs.getSequence(), startIndex);

					// check if the length is greater than the threshold
					if (length > threshold)
					{
						// make new match element
						MatchElement match = new MatchElement(length, queryNum, singleLoc);
						//Debug.println("Match: " + match);

						// add match to list of matches
						matches.add(match);
					}
				}
			}
		}
		return matches;
	}

	/**
	 * Finds how far the match can be extended
	 * 
	 * 		@param loc the location the match begins at in the virus
	 * 		@param querySeq the entire query sequence
	 * 		@param virusSeq the entire virus sequence
	 * 		@param qStart the index where the query starts
	 * 		@return length of the extended match
	 */	
	public int extendMatch (Location loc, String querySeq, String virusSeq, int qStart)
	{
		// set initial length to 0
		int length = 0;
		
		// set beginning virus index
		int virusIndex = loc.getStartIndex();
		
		// set beginning query index
		int queryIndex = qStart;
		
		// check that indices are within the sequence
		while (queryIndex < querySeq.length() && virusIndex < virusSeq.length())
		{
			// check if characters are equal
			if ((querySeq.charAt(queryIndex)) == (virusSeq.charAt(virusIndex)))
			{
				// increase virus index
				virusIndex++;
				
				// increase query index
				queryIndex++;
				
				// increase match length
				length++;
			}
			else
			{
				// return length
				return length;
			}
		}
		// return length
		return length;
	}

	/**
	 * Prints the hashtable	
	 */
	public void printHashtable()
	{
		Set<String> keys = hashtable.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext())
		{
			String key = iterator.next();
			System.out.println("Key: " + key + "\n\tValues: " + hashtable.get(key));
		}
	}
}
