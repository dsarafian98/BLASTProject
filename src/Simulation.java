import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *  Programming Project 4: BLAST<br>
 *
 *	This class gets the DNA data and the query data 
 *	from the data readers. It has a run method that 
 *	iterates through each query and displays any matches 
 *	with substrings of the DNA data that are longer than 
 *	a given threshold.
 *
 *  <br> <br>
 *  Created: <br>
 *    30 May 2017, Danielle Sarafian<br>

 *  Modifications: <br>
 *     3 June 2017, Danielle Sarafian, write run<br>
 *     4 June 2017, Danielle Sarafian, write printLocations<br>
 *     5 June 2017, Danielle Sarafian, edit printLocations<br>
 *
 *  @author Danielle Sarafian
 *  @version 5 June 2017
 */
public class Simulation
{
	// Constructors

	/**
	 * Constructs a new object of this class.
	 */
	public Simulation()
	{
	}

	// Methods

	/**
	 * Runs a simulation of matching the virus DNA and the queries
	 * 		@param virusDNAfile the file that contains the virus DNA
	 * 		@param queryFile the file that contains the query information
	 * 		@param threshold the smallest length that the match can be
	 * 		@param subSeqLength the length to make the subsequences
	 */
	public void run(String virusDNAfile, int numLinesNucleoInfo, String queryFile, int threshold, int subSeqLength)
	{
		// count queries
		int queryNum = 0;
		
		// new DNADataReader object
		DNADataReader dataReader = new DNADataReader(virusDNAfile, numLinesNucleoInfo, threshold, subSeqLength);
		
		// new QueryReader object
		QueryReader queryReader = new QueryReader(queryFile);
		
		// create database of viruses
		Database database = dataReader.readData();
		
		// create list of queries
		List<String> queries = queryReader.readData();
		
		// go through all queries
		for (String query : queries)
		{	
			// list of matches
			ArrayList<MatchElement> matches = database.findMatches(query, threshold, subSeqLength, queryNum);
			
			// print the query string
			System.out.println("\nQuery: " + query);
			
			// print matches, but not duplicates
			printLocations(matches);
			
			// increase query number
			queryNum++;
		}
	}
	
	/**
	 * Prints all locations and makes sure there aren't duplicates
	 * 		@param list the arrayList of MatchElements for a query
	 */
	private void printLocations(ArrayList<MatchElement> list) 
	{
		// list of sequence numbers
		ArrayList<Integer> seqNums = new ArrayList<Integer>();
		
		// location
		Location testLoc;
		int minIndex = 0;
		int maxIndex = 0;
		
		// go through each element in the list
		for (MatchElement element : list)
		{			
			testLoc = element.getMatchLoc();
			
			// get sequence number
			int seqNum = testLoc.getSeqNum();
			
			// check if sequence number is in the list of sequence numbers
			if (seqNums.contains(seqNum))
			{
				// check that this item is not in one of the matches already found
				if ((!(testLoc.getStartIndex() > minIndex && testLoc.getStartIndex() <= maxIndex)))
				{
					minIndex = 0;
					maxIndex = 0;
					System.out.println(element);
					System.out.println("");
				}

			}
			else
			{
				System.out.println(element);
				System.out.println("");
				seqNums.add(seqNum);
				maxIndex = testLoc.getStartIndex() + element.getMatchLength();
				minIndex = testLoc.getStartIndex();
			}
		}
	}
}
