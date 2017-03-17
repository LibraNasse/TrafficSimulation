/**                                                              Light
 * Represents a traffic signal

 */
public class Light {
    private int period;
    private int green;
    private int internaltime;

    public Light(int p, int g) {
    	this.period = p;
    	this.green = g;
    	this.internaltime=0;
    }

    /**
     * Steps the clock of the signal
     */
    public void step() {
    	internaltime ++;
    
    }

    /**
     * @return true if the signal is green otherwise false
     */
    public boolean isGreen() {
    	    	
       	if( (internaltime%period)< green)
    	{
    	    return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * @return A String-representation of the signal that shows its color
     */
    public String toString()  
    {
    	if(this.isGreen())
    		return "G:";
    	else
    		return "R:";
    }	
}
