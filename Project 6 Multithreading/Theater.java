// insert header here
package assignment6;

import java.util.List;

public class Theater {
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
			String row = "";
			ArrayList<Integer> stringRow = new String<Integer>();
			int temp = rowNum;
			while(temp > 0){
				int temp2 = temp %26;
				stringRow.add(temp2);
				temp = temp /26;
			}
			for(int i = stringRow.size() -1 ; i >= 0; i--) {
				if(stringRow.get(i) != 0) {
					row += (String) 64+stringRow.get(i);				}
			}

			row += seatNum;
			return row;
			

		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
	  private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			String line1 = "\n-------------------------------";
			String line2 = "\n|	Show: ";
			String line3 = "\n|	Box Office ID: ";
			String line4 = "\n| Seat: ";
			String line5 = "\n|	Client: ";
			String line6 =   "-------------------------------\n";
			// TODO: Implement this method to return a string that resembles a ticket
			line2 += show;
			line3 += boxOfficeId;
			line4 += seat.toString();
			line5 += client;
			
			int temp2 = line1.length() - line2.length();
			for(int i = 1; i <= temp2; i++) {
				if(i == temp2) {
					line2 += "|";
				}
				line2 += " ";
			}
			
			int temp3 = line1.length() - line3.length();
			for(int i = 1; i <= temp2; i++) {
				if(i == temp2) {
					line3 += "|";
				}
				line3 += " ";
			}
			
			int temp4 = line1.length() - line4.length();
			for(int i = 1; i <= temp2; i++) {
				if(i == temp2) {
					line4 += "|";
				}
				line4 += " ";
			}
			
			int temp5 = line1.length() - line5.length();
			for(int i = 1; i <= temp2; i++) {
				if(i == temp2) {
					line5 += "|";
				}
				line5 += " ";
			}


			return line1+line2+line3+lin4+line5+line6;

		}
	}


	private int totalSeats;
	private String showName;
	private int numRows;
	private int seatsPerRow;
	private ArrayList<Ticket> purchaseLog = new ArrayList<Ticket>(); 
	
	public Theater(int nRows, int sPerRow, String show) {
		showName = show;
		numRows = nRows;
		seatsPerRow = sPerRow;
		totalSeats = numRows*seatsPerRow;	
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {
		if(totalSeats > 0) {
			int nextBestSeat = purchaseLog.size()+1;
			int nextAvailableRows = 1;

			
			while(nextBestSeat > this.seatsPerRow) {
				nextBestSeat -= this.seatsPerRow;
				nextAvailableRows += 1;
			}

			Seat newSeat = new Seat(nextAvailableRows, nextBestSeat);
			return newSeat;

		}
		else
			return null;
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		Ticket newTicket = new Ticket(this.showName,boxOfficeId,seat,client);
		for(Ticket each: purchaseLog) {
			Seat taken = each.getSeat();
			if(taken.getRowNum() == seat.getRowNum() && taken.getSeatNum() == seat.getSeatNum()){
				return null;
			}
		}

		return newTicket;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		List<Ticket> result = purchaseLog.subList(0, purchaseLog.size()-1);
		return result;
	}
}
