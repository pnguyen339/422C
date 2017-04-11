// Insert header here
package assignment6;

import java.util.Map;
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
    newClient.simulate();


  }  
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */

  private Map<String, Integer> offices;
  private Theater myTheater;

  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
    this.offices = office;
    this.myTheater = theater;
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
    int i = 1;
    for (String key : map.keySet()) {
      Thread newThread = new Client(key,i);
      runningThread.add(newThread);
      i++;
    }

    for(Thread each : runningThread) {
      each.run();
    }

    return runningThread;

  }

  static class Client implements Runnable{
    private int clientNum;
    private String name;
    
    public Client(String nameO,int num) {
      clientNum = num;
      name = nameO;
    }

    public void run() {
      Seat nextBest = myTheater.bestAvailableSeat();
      if(nextBest != null){
        Ticket custTicket = myTheater.printTicket(name, nextBest, clientNum);
        custTicket.toString();
      }
    }
  }
	
	
}
