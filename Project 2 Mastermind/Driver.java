
import java.util.*;

public class Driver{

	public static void main(String [] args){
		Game master;
		if(args == null)
			master = new Game(false);
		else
			master = new Game(true);
		
		master.start();



	}


}
