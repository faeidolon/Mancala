import java.util.LinkedList;

public class Board 
{
	Cup agentGoal, cup6, cup5, cup4, cup3, cup2, cup1;
	Cup humanGoal, cupn6, cupn5, cupn4, cupn3, cupn2, cupn1;
	LinkedList<Cup> board;
	int value;
	
	public Board(LinkedList<Cup> inBoard)
	{
		board = inBoard;
		agentGoal = inBoard.get(13);
		cup6 = inBoard.get(12);
		cup5 = inBoard.get(11);
		cup4 = inBoard.get(10);
		cup3 = inBoard.get(9);
		cup2 = inBoard.get(8);
		cup1 = inBoard.get(7);

		humanGoal = inBoard.get(6);
		cupn1 = inBoard.get(5);
		cupn2 = inBoard.get(4);
		cupn3 = inBoard.get(3);
		cupn4 = inBoard.get(2);
		cupn5 = inBoard.get(1);
		cupn6 = inBoard.get(0);
	}
	
	public Board()
	{
		board = new LinkedList<Cup> ();
		agentGoal = new Cup(0);
		cup6 = new Cup(4);
		cup5 = new Cup(4);
		cup4 = new Cup(4);
		cup3 = new Cup(4);
		cup2 = new Cup(4);
		cup1 = new Cup(4);
		
		humanGoal = new Cup(0);
		cupn1 = new Cup(4, cup1);
		cupn2 = new Cup(4, cup2);
		cupn3 = new Cup(4, cup3);
		cupn4 = new Cup(4, cup4);
		cupn5 = new Cup(4, cup5);
		cupn6 = new Cup(4, cup6);
		
		cup6.setOpposite(cupn6);
		cup5.setOpposite(cupn5);
		cup4.setOpposite(cupn4);
		cup3.setOpposite(cupn3);
		cup2.setOpposite(cupn2);
		cup1.setOpposite(cupn1);
		
		board.add(cupn6);
		board.add(cupn5);
		board.add(cupn4);
		board.add(cupn3);
		board.add(cupn2);
		board.add(cupn1);
		board.add(humanGoal);

		board.add(cup1);
		board.add(cup2);
		board.add(cup3);
		board.add(cup4);
		board.add(cup5);
		board.add(cup6);
		board.add(agentGoal);
	}
	
	public void printBoard()
	{
		String myOutput = "";
		
		if(board.get(13).myStones < 10)
			myOutput = myOutput + "        " + "0" + board.get(13).getStones() + "        \n";
		else
			myOutput = myOutput + "        " + board.get(13).getStones() + "        \n";
		
		for(int i = 12; i > 6; i--)
		{
				Cup currentCup = board.get(i);
				if(currentCup.myStones < 10)
					myOutput = myOutput + "0" + currentCup.getStones() + " ";
				else
					myOutput = myOutput + currentCup.getStones() + " ";
		}
		
		myOutput += "\n";

		for(int i = 0; i < 6; i++)
		{
				Cup currentCup = board.get(i);
				if(currentCup.myStones < 10)
					myOutput = myOutput + "0" + currentCup.getStones() + " ";
				else
					myOutput = myOutput + currentCup.getStones() + " ";
		}
		
		if(board.get(6).myStones < 10)
			myOutput = myOutput + "\n        " + "0" + board.get(6).getStones() + "        ";
		else
			myOutput = myOutput + "\n        " + board.get(6).getStones() + "        ";
		
		System.out.println(myOutput);
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int myValue)
	{
		value = myValue;
	}
	
	public void calcValue()
	{
		value = 0;
		value = board.get(6).myStones * 2;
		if(board.get(5).myStones == 1)
			value += 3;
		value -= board.get(13).myStones * 2;
		if(board.get(12).myStones == 1)
			value -= 3;
	}
}
