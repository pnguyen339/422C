
import java.util.*;
import java.lang.System;
import java.lang.String;
public class Mastermind implements Gameplay{
	
		private ArrayList<String> hist;
		private int NumPeg;
		private boolean debug;
		private String secretCode;
		private int numGuess;	
		private ArrayList<String> colors;
	

	Mastermind(boolean i)
	{
		debug = i;
	}

	public void output(int i, String s){

		switch(i){
			case 1: System.out.println("Welcome to Mastermind."); break;			
			
			case 2: System.out.println("You have " +numGuess+ " guess(es) left.");
					System.out.println("Enter your guess:"); break;
		
			case 3: System.out.println(s+" -> " + checkBlack(s) + "b_"+ checkWhite(s)+ "w"); break;

			case 4: for(String temp : hist){
						System.out.println(temp + " -> "+checkBlack(temp)+"b_"+ checkWhite(temp)+"w");
					}
					break;
			case 5: System.out.println(s); break;

			case 6: System.out.println("INVALID_GUESS"); break;

			case 7: System.out.println("You win!"); break;
			
			case 8:	System.out.println("Do you want to play a new game? (Y/N)"); break;

			case 9: System.out.println("You lose! The parttern was " + secretCode); break;

			case 10: System.out.println("Secret code: "+ secretCode); break;
			default: break;

		}

	}
	
	public boolean isValid(String o)
	{
		if(o.compareTo("HISTORY") == 0)
		{
			output(4,o);
			return false;
		}
		if(o.length() != NumPeg)
		{
			output(6,null);
			return false;
		}
		else
		{
			for(int i = 0; i < o.length(); i++)
			{
					if(colors.contains(o.charAt(i)))
					{	
						output(6,null);
						return false;
					}
			}
			return true;
		}
	}

	private int checkWhite(String temp)
	{	
		int count = 0;
		for(int i =0; i<temp.length(); i++){
			for(int y=0; y<secretCode.length(); y++){
				if(i!=y && temp.charAt(i) == secretCode.charAt(y))
					count++;
			}
		}
		return count;

	}

	private int checkBlack(String temp)
	{
		int count = 0;
		for(int i=0; i<secretCode.length(); i++)
		{
			if(temp.charAt(i) == secretCode.charAt(i))
				count++;
		}
		return count;

	}
	
	public String getUserInput(Scanner o)
	{
		String y = o.nextLine(); 
		return y;

	}
	
	public void play(){
		GameConfiguration config = new GameConfiguration();
		Scanner sc = new Scanner(System.in);
		NumPeg = config.pegNumber;
		secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		colors = new ArrayList<String>(Arrays.asList(config.colors));
		numGuess= config.guessNumber;

		output(1,null);
		output(8,null);
		String x = getUserInput(sc);
		boolean finishGame = false;		
		
		if(x.compareTo("Y")==0	)
		{
			if(debug)
				output(10,null);
			
			while(!finishGame)		
			{					
				do{
					output(2,null);
					x=getUserInput(sc);
				}while(!isValid(x));
				
				output(3,x);
				if(checkBlack(x) == NumPeg)
				{
					finishGame = true;
					output(7,null);
					output(8,null);
					if(getUserInput(sc).compareTo("Y")==0)
					{	
						finishGame =false;
						secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
						if(debug)
							output(10,null);

					}
				}
				
				numGuess--;

				if(numGuess == 0)
				{
					finishGame = true;
					output(9,null);
					output(8,null);
					if(getUserInput(sc).compareTo("Y")==0)
					{
						finishGame =false;
						secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
						if(debug)
							output(10,null);

					}
				}

			}
		}
	}
			

}
