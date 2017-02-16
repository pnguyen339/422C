
import java.util.*;
import java.lang.System;
import java.lang.String;
//package assignment2
public class Mastermind implements Gameplay{
	
		private ArrayList<String> hist = new ArrayList<String>();
		private int NumPeg;
		private static boolean debug;
		private String secretCode;
		private int numGuess;	
		private String[] colors;
	

	Mastermind(boolean i)
	{
		debug = i;
	}

	public void output(int i, String s){

		switch(i){
			case 1: System.out.println("Welcome to Mastermind."); break;			
			
			case 2: System.out.println("\nYou have " +numGuess+ " guess(es) left.");
					System.out.println("Enter your guess:"); break;
		
			case 3: System.out.println(s+" -> " + checkBlack(s) + "b_"+ checkWhite(s)+ "w"); break;

			case 4: for(String temp : hist){
						System.out.println(temp + " -> "+checkBlack(temp)+"b_"+ checkWhite(temp)+"w");
					}
					break;
			case 5: System.out.println(s); break;

			case 6: System.out.println("INVALID_GUESS"); break;

			case 7: System.out.println("You win!\n"); break;
			
			case 8:	System.out.println("Do you want to play a new game? (Y/N)"); break;

			case 9: System.out.println("You lose! The parttern was " + secretCode+ "\n"); break;

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
			boolean find =false;	
			char[] charA = o.toCharArray();
			for(int i = 0; i < charA.length-1; i++)
			{
				for(int g =0;g< colors.length; g++)
				{		
					if(colors[g].compareTo(Character.toString(charA[i]))==0)
					{
						find = true;	
					}
				}
			}
			if(!find)
			{
				output(6,null);
				return find;
			}
			else
				return find;
		}
	}

	private int checkWhite(String temp)
	{	
		int count = 0;
		for(int i =0; i<temp.length(); i++)
		{
			
			char[] charA = temp.toCharArray();
			for(int y=0; y<secretCode.length(); y++){
				if(i!=y && temp.charAt(i) == secretCode.charAt(y))
				{
					if(charA[i] != 0)
					{
						charA[i] = 0;
						count++;
						break;
					}
				}

			}
		}
		
		int i = checkBlack(temp);
		if(count-i >= 0)	
		{
			return count -i;
		}
		else
			return 0;
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
	
	private void setup(){
		GameConfiguration config = new GameConfiguration();
		NumPeg = config.pegNumber;
		secretCode = SecretCodeGenerator.getInstance().getNewSecretCode();
		colors = config.colors;
		numGuess= config.guessNumber;
		if(!hist.isEmpty())
			hist.clear();
		if(debug)
				output(10,null);
	}
	
	public void play(){
		
		Scanner sc = new Scanner(System.in);
		output(1,null);
		output(8,null);
		String x = getUserInput(sc);
		boolean finishGame = false;		
		setup();
		if(x.compareTo("Y")==0	)
		{
			
			
			while(!finishGame)		
			{					
				do{
					output(2,null);
					x=getUserInput(sc);
				}while(!isValid(x));
				
				hist.add(x);					
				output(3,x);
				
				if(checkBlack(x) == NumPeg)
				{
					finishGame = true;
					output(7,null);
					output(8,null);
					if(getUserInput(sc).compareTo("Y")==0)
					{	
						finishGame =false;
						setup();

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
						setup();

					}
				}

			}
		}
	}
			

}
