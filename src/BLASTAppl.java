/**
 *  Programming Project 4: BLAST<br>
 *
 *  The <code>BLASTAppl</code> class provides a main method
 *  for a program that reads a DNA query and compares it with
 *  DNA sequence data.
 *
 *  <br> <br>
 *  Created: <br>
 *    30 May 2017, Danielle Sarafian<br>

 *  Modifications: <br>
 *     2 June 2017, Danielle Sarafian, create queryReader object and read queries<br>
 * 	   
 *
 *  @author Danielle Sarafian
 *  @version 30 May 2017
 */
public class BLASTAppl
{
    /**
     *  The main function initiates execution of this program.
     *    @param    String[] args not used in this program
     **/
    public static void main(String[] args)
    {
    	//Debug.turnOn();
    	
    	ValidatedInputReader inputReader = new ValidatedInputReader();
    	int threshold = inputReader.getInteger("Please enter the threshold for matches.", 40,
    			"Please enter an integer for the minimum length of matches.");
     	Simulation simulation = new Simulation();
    	//simulation.run("testData.txt", 1, "testQueries.txt", threshold, 3);
    	simulation.run("Viruses.txt", 17, "Queries.txt", threshold, 11);
    }//end main

}//end class
