import java.util.Random;

/**                                                             TrafficSystem
 * <pre>
 * Defines the lanes and signals that is to be studied. Collects statistics
 *
 * Model for traffic simulation
 * ============================
 *
 *  The following classes are used:
 *
 *     Vehicle Represents a vehicle
 *             Time of arrival and destination are set when create.
 *
 *     Light   Represents the light signals
 *             See below
 *  
 *     Lane    Represents a piece of a road
 *             A lane is represented by an array where each element
 *             either is empty or contain a reference to a 
 *             vehicle-object.
 * 
 *     TrafficSystem
 *             Defines the components, ie the lanes  and signals that
 *             build the system. See below
 *
 *     Simulation
 *            main-method the controls the simulation
 *
 *
 *  The situation that is to be simulated looks schematically like
 *
 *          C           X                               E
 *   W    s1<----r1-----<---------r0---------------------
 *   S    s2<----r2-----< 
 *
 *  A lane (a piece of a road) r0 split into two files r1 and r2 at X.
 *  The signal s1 controls the lane r1 and the signal s2 the lane r2.
 * 
 *  Vehicles are create at E. The probability that a vehicle arrives to E
 *  at a certain time is called "the intensity of arrival".
 *
 *  At a time step the vehicles move one step forward  (if possible).
 *  At C, the vehicles are removed from the system if the resp signal is green.
 *  At X, vehicles are move from r0 to either r1 or r2 depending of its
 *  destination (if there are space for them).
 *
 * </pre> 
*/

public class TrafficSystem {
    
    // Attributes that describe the elements of the system
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;
    private int TimeTransmitS1,MaxTimeTransmitS1,SizeTimeTransmitS1,BlocktimeS1 ;
    private int TimeTransmitS2,MaxTimeTransmitS2,SizeTimeTransmitS2,BlocktimeS2 ;
    // Various attributes for simulation parameters (intensity of arrivals
    // destinations...)
    
    // Various attributes for collection  of statistics
    
    private int time ;

    public TrafficSystem() {
    	r0=new Lane(15);
    	r1=new Lane(10);
    	r2=new Lane(10);
    	s1=new Light(7,2);
    	s2=new Light(7,3);
    	TimeTransmitS1=0;
    	MaxTimeTransmitS1=0;
    	SizeTimeTransmitS1=0;
    	TimeTransmitS2=0;
    	MaxTimeTransmitS2=0;
    	SizeTimeTransmitS2=0;
    	BlocktimeS1=0;
    	BlocktimeS2=0;
    	time = 0;
    	
    	
    }

    public void set ()
    {
    	    Random Temp = new Random();
			if(Temp.nextBoolean())
			{
				Vehicle tmpV=new Vehicle(time);
				//tmpV.setDestination();
			    r0.putLast(tmpV);
			}
    	
    }
    
    /**
     * Defines how vehicles should mod in the system.
     * Steps the system one time step using the step methods in the
     * components
     * Creates vehicles, add and remove into the different lanes.
     */
    public void step() {
    	s1.step();
    	s2.step();
    	
    	
    	if ((s1.isGreen()) && (r1.getFirst()!=null))
    	{
    		Vehicle tmpV = r1.removeFirst();
    		int vehicleLife=time-tmpV.getbornTime();
    		TimeTransmitS1+=vehicleLife;
    		MaxTimeTransmitS1= (MaxTimeTransmitS1<vehicleLife) ? vehicleLife:MaxTimeTransmitS1;
    		SizeTimeTransmitS1++;       	
    	}
		r1.step();
    	if ((s2.isGreen()) && (r2.getFirst()!=null))
    	{
    		Vehicle tmpV = r2.removeFirst();
    		int vehicleLife=time-tmpV.getbornTime();
    		TimeTransmitS2+=vehicleLife;
    		MaxTimeTransmitS2= (MaxTimeTransmitS2<vehicleLife) ? vehicleLife:MaxTimeTransmitS2;
    		SizeTimeTransmitS2++;       	
    	}
		r2.step();
    	if ((r0.getFirst()!=null) && (r0.getFirst().getdestination()=='W'))
    	{
    		if (r1.lastFree())
    		{
        		r1.putLast(r0.removeFirst());    			
    		} else
    		{
    			BlocktimeS1++;
    		}
    	}
    	else if((r0.getFirst()!=null) && (r0.getFirst().getdestination()=='S'))
 	    {
    		if (r2.lastFree())
    		{
        		r2.putLast(r0.removeFirst());    			
    		} else
    		{
    			BlocktimeS2++;
    		}
 	    }
		r0.step();	
		print();
    	time++;
    	this.set();
    }

    /**
     * Print the collected statistics so far
     */
    public void printStatistics() 
    {
    	System.out.println("Lane 1");
    	System.out.println("\tThe average waiting time: "+ ((SizeTimeTransmitS1==0)? "No vehicle passed from S1":TimeTransmitS1/SizeTimeTransmitS1));
    	System.out.println("\tThe Maximum waiting time: "+ MaxTimeTransmitS1);
    	System.out.println("\tWaiting time on junction: "+ BlocktimeS1);
    	System.out.println();
    	System.out.println("Lane 2");
    	System.out.println("\tThe average waiting time: "+ ((SizeTimeTransmitS2==0)? "No vehicle passed from S2":TimeTransmitS2/SizeTimeTransmitS2));
    	System.out.println("\tThe Maximum waiting time: "+ MaxTimeTransmitS2);
    	System.out.println("\tWaiting time on junction: "+ BlocktimeS2);
    }

    /**
     * Prints a graphical representation of the current traffic situation
     * using the toString-methods in the components.
     */
    private void print() 
    {
    	System.out.print(s1.toString());
    	System.out.print(r1.toString());
		System.out.print(r0.toString());
    	System.out.println();
    	System.out.print(s2.toString());
    	System.out.print(r2.toString()); 
    	System.out.println(); 
    	System.out.println();
    }
}
