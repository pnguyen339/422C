import java.util.*;



public class Game{
	private Mastermind ga;
	Game(boolean i){
		ga = new Mastermind(i);
	}

	public void start()
	{
		ga.play();
	}

}
