
/**                                                              Simulation
 * Run a  simulation
 */
public class Simulation {

    /**
     * Create a TrafficSystem, steps it and calls the print methods
     */
    public static void main(String [] args) 
    {
    	int LiveTime=500;
    	TrafficSystem tf = new TrafficSystem();
	    int stamp=0;
        while (stamp<LiveTime) 
        {
        	try 
        	{ 
        	
        		Thread.sleep(100);
        	} 
        	catch (InterruptedException e) 
        	{ 
        		System.out.println(e.getMessage());
        	}
        	tf.step();
        	stamp++;
        }
        tf.printStatistics();
	
    }
}
