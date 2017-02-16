
import java.util.*;

public class Driver{

	public static void main(String [] args){
		Game master;
		if(args.length ==0)
			master = new Game(false);
		else
			master = new Game(true);
		
		master.start();



	}


}
