import java.io.IOException;

/**
 * This class contains code to read DNA sequence data in from
 * a file.  It must be put into a data structure as it is read.
 * The data structure should be chosen by the modifier of this
 * class.
 * 
 * Created: 23 May 2006 <br>
 * 
 * Modifications: <br>
 * 		15 November 2006 <br>
 * 		19 November 2006 to extend DNASeqReader <br>
 * 		30 May 2017, Danielle Sarafian, update readData<br>
 * 		2 June 2017, Danielle Sarafian, add virusNum instance variable [with assistance from Dr. Gerry Howser]<br>
 * 
 * @author Pamela Cutter
 * @author Alyce Brady
 * @author Danielle Sarafian
 * @version 2 June 2017
 */
public class DNADataReader extends DNASeqReader
{
    private String filename;
    private int numLinesNucleoInfo;
    private int virusNum = 0;
    private int threshold;
    private int subSeqLength;


    /** Constructs an object that reads DNA sequence information
     *  from the given file.  The data is assumed to be in the
     *  following format:
     *      GenInfoIdentifier  A string describing the sequence
     *      a group of nucleotide blocks, spanning 1 or more lines
     *  where the number of lines of nucleotide blocks per sequence,
     *  is provided as a parameter. For example, if the parameter were
     *  1, the following would be a valid single sequence.
     *      94717691 Campo. sono. ichnovirus segment W, complete sequence
     *      ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
     *      
     *      @param filename the name of the file to read
     *      @param numLinesNucleoInfo the number of lines nucleo info takes up
     *      @param threshold the minimum length that the match can be
     *      @param 	subSeqLength the length to make the subsequences
     */
    public DNADataReader(String filename, int numLinesNucleoInfo, int threshold, int subSeqLength) 
    {
        super(filename);
        this.filename = filename;
        this.numLinesNucleoInfo = numLinesNucleoInfo;
        this.threshold = threshold;
        this.subSeqLength = subSeqLength;
    }

    /** Reads DNA sequence information from the given file, which must be
     *  in the format specified in the documentation for the DNADataReader
     *  constructor.
     *  Precondition: the file must have been successfully opened for
     *                reading.
     *                @param threshold the minimum match length
     */
    public Database readData()    
    {    	
    	Database database = new Database();
    	
        try 
        {
            // Read complete lines at a time, until end of file.
            String next;
            while ( (next = this.readLine()) != null) 
            {
                //Debug.println(next);
                
                if ( ! next.equals("") )
                    readInfoForOneSequence(next, database);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Could not read line " +
                               (this.getLineNumber() + 1) +
                               " of file " + filename);
            return null ;
        }

        // Debugging: Print out the elements in your data structure.
       //Debug.println("data structure: ");
       //database.printHashtable();
        
        return database;
    }

    /** Reads in the sequence information for a single DNA sequence in the file.
     *      @param firstLine    the first line of info for this sequence
     *      @param queue		the data structure to put the sequence info in
     *      @param threshold the minimum match length
     *      @throws IOException
     */
    private void readInfoForOneSequence(String firstLine, Database database) throws IOException
    {
        // Put the space-separated items from the line into an array.
        String[] items = firstLine.split(" ");

        
        // The first line should contain the GenInfo identifier (GI)
        // and sequence description.
        String gi = items[0];     // GenInfo identifier
        String desc = "";         // Description
        for (int i = 1; i < items.length; i++)
            desc += items[i] + " ";

       // Debug.println("Line " + this.getLineNumber() + ": " +
         //             gi + ", " + desc);

        // Then read in the lines with the sequence information.
        String seq = readSequenceString(numLinesNucleoInfo);

        //Debug.println("Just read " + seq);

        // *** Create a DNA sequence object from gi, desc, and seq. ***
       DNASequence seqObj = new DNASequence (gi, desc, seq);
        
        // *** Add it to your data structure. ***
        //Debug.println("Should be adding an element to the data structure");
        database.add(seqObj, virusNum, threshold, subSeqLength);
        virusNum++;

    }
}