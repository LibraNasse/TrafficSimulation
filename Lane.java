
	/**                                                             Lane
	 * Represents a lane of vehicles using an array
	 */
	public class Lane {

	    private Vehicle[] theLane;

	    /**
	     *  Constructs a  Lane-object with place for n vehicles
	     */
	    public Lane() {
	    	theLane = new Vehicle[15];
	    }

	    public Lane(int n) {
	    	theLane = new Vehicle[n];
	    }

	    
	    /**
	     * Step all vehicles (except the one in place 0) one step
	     * (if possible). (The vehicle at position 0 is removed before calling
	     * this method using the method below).
	     */
	    public void step() {
	    	for (int i=0; i<theLane.length-1; i++)
	    	  {
	    		if (theLane[i] == null) 
	    		{
	    		    theLane[i] = theLane[i+1];
	    			theLane[i+1]= null;
	    		    
	    		}
    		    
	    		/*if ((theLane[i] == null) && (theLane[i+1]!=null))
	    		{
	    		    theLane[i] = theLane[i+1];
	    			theLane[i+1]= null;
	    		    i--;*/
	    		}
	    	  
	    }

  
	    public void set (int n){
	    	theLane = new Vehicle[n];
	    	
	    }
	    /**
	     * Removes the first vehicle
	     * @return The Vehicle at the first place or null if it is empty
	     */
	    public Vehicle removeFirst() {
	    	
	    		Vehicle first = theLane[0];
	    		theLane[0] = null;
	    		return first;
	    		
	    	}

	    /**
	     * Returns the first vehicle without removing it
	     * @return A reference to the vehicle in the first place or null if it is
	     * empty
	     */
	    public Vehicle getFirst() {
	    	
	    	return theLane[0];
	    }

	    /**
	     * @return true if the last place if empty, else false
	     */
	    public boolean lastFree() {
	    	if(theLane[theLane.length-1]!=null)
	    	    return false;
	    	else
	    		return true;
	    	}

	    /**
	     * Stores a vehicle at the end of the lane
	     * @param v The vehicle that is to be stored
	     */
	    public void putLast(Vehicle v) {
	    	
	    	  if(lastFree())
	            theLane[theLane.length-1]=v;
	    	
	    		
	    }

	    /**
	     * @return A string representation of a lane and its vehicles
	     */
	    public String toString() 
	    {
	    	String q="[";
	    	for(int i=0;i<theLane.length;i++)
	    	{
	    		if(theLane[i]==null)
	    			q = q + " ";
	    		else
	    			q = q + theLane[i].getdestination() ;
	    	}
	    	return q+"]";
	    }
	}



