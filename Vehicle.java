import java.util.Random;

/**                                                            Vehicle
 * Representerar ett fordon
 */
public class Vehicle {

    private int bornTime;
    private char destination;  

    // Konstruktor och get-metoder
    //

public Vehicle(int time)
{  bornTime = time;
   Random Temp = new Random();
   destination= (Temp.nextBoolean()?'W':'S');
}

public Vehicle(int b, char d){
	bornTime = b;
    destination= d;
}

public void setDestination(){
	Random Temp = new Random();
	this.destination= (Temp.nextBoolean()?'W':'S');
}

public void setVehicle(int b, char d){
	bornTime = b;
    destination = d;
}
public void TimeIncrement(int y){
	this.bornTime++;
}

 public int getbornTime(){
	 return bornTime;
	
}

 public char getdestination(){
	 return destination;
 }
 
    public String toString() {
    	if(this.destination == 'W')
    		return "W";
    	else 
    		return "S";
}	
}
