// Insert header here
package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assignment6.Theater.Seat;
import assignment6.Theater.Ticket;

import java.lang.Thread;

public class BookingClient {



  public static void main(String args[]){  
    Theater publicTheater = new Theater(3,5,"Ouija");
    Map<String, Integer> office = new HashMap<String, Integer>();
    office.put("BX1", 3);
    office.put("BX3", 3);
    office.put("BX2", 4);
    office.put("BX5", 3);
    office.put("BX4", 3);
    BookingClient newClient = new BookingClient(office, publicTheater);
    @SuppressWarnings("unused")
	List<Thread> th = newClient.simulate();


  }  
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */

  private Map<String, Integer> offices;
  private Theater myTheater;

  public BookingClient(Map<String, Integer> office, Theater theater) {
    this.offices = office;
    this.myTheater = theater;
  }
  
  public Theater getTheater() {
	  return myTheater;
  }
  
  public  Map<String, Integer> getOff() {
	  return offices;
  }

  /*
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List<Thread> simulate() {
		ArrayList<Thread> runningThread = new ArrayList<Thread>();
		for (String key : offices.keySet()) {
			Thread newThread = new Thread(new Client(key, this));
			runningThread.add(newThread);
			
		}

		for(Thread each : runningThread) {
			each.start();
		}

		return runningThread;

	}

  static class Client implements Runnable{
	    private String name;
	    private BookingClient sys;
	    
	    public Client(String nameO, BookingClient i) {
	      name = nameO;
	      sys = i;
	    }
	
	    public synchronized void bookit() {
	    	
	    }
	    public void run() {
	    	int numCust = this.sys.getOff().get(this.name);
	    	while(numCust > 0 ){
		    	  Seat nextBest = this.sys.getTheater().bestAvailableSeat();
			      if(nextBest != null){
			        Ticket custTicket = this.sys.getTheater().printTicket(name, nextBest, this.sys.getTheater().getNumPeople() +1);
			        if(custTicket != null)
			        	System.out.print(custTicket.toString());
			        try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
			      }
			      else {
			    	System.out.printf("Sorry, we are sold out!");  
			    	return;
			      }
			      numCust--;
			    
		     }
	    }
	
  }
}
