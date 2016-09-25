/**
 * A program that plays mancala against a human player using a rough 
 * implementation of hill-climbing optimization
 * 
 * Layout: 
 * 00 01 02 03 04 05 06 07 08 09 10 11 12 13
 * 00-05: human's cups
 * 06: human's goal
 * 07-12: AI's cups
 * 13: AI's goal
 * 
 * Physical layout:
 *             AI
 *            
 * 13   12 11 10 09 08 07   06
 * 13   00 01 02 03 04 05   06
 * 
 *           Human
 *           
 * @author Faelan
 * @date_created 11/15/14
 */


import java.util.LinkedList;
import java.util.Scanner;

public class MancalaAgent
{
	Board myBoard;
	
	public MancalaAgent()
	{
		myBoard = new Board();

		Scanner in = new Scanner(System.in);
		boolean done = false;
		
		while(!done)
		{
			myBoard.printBoard();
			System.out.println();
			System.out.println("Input your move:");
			int move = in.nextInt();
			
			myBoard = humanMove(myBoard, move);
			myBoard.printBoard();
			System.out.println();
			
			System.out.print("Agent's move:");
			myBoard = agentMove(myBoard);
			
			if(myBoard.board.get(6).myStones + myBoard.board.get(13).myStones > 47)
				done = true;
		}
		myBoard.printBoard();
		in.close();
		
		for(int a = 0; a < 6; a++)
		{
			int aStone = myBoard.board.get(a).myStones;
			myBoard.board.get(a).setStones(0);
			myBoard.board.get(6).setStones(myBoard.board.get(6).myStones + aStone);
		}
		
		for(int h = 7; h < 13; h++)
		{
			int hStone = myBoard.board.get(h).myStones;
			myBoard.board.get(h).setStones(0);
			myBoard.board.get(13).setStones(myBoard.board.get(13).myStones + hStone);
		}
		
		if(myBoard.agentGoal.myStones > myBoard.humanGoal.myStones)
			System.out.println("\nToo bad! You lose!\n");
		else if(myBoard.agentGoal.myStones < myBoard.humanGoal.myStones)
			System.out.println("\nCongratulations! You win!\n");
		else
			System.out.println("\nIt's a tie!\n");
		myBoard.printBoard();
	}

	public Board agentMove(Board input)
	{
		TreeNode root = new TreeNode(0, input);
		System.out.println();
		for(int i = 1; i < 7; i++)
		{
			LinkedList<Cup> l = new LinkedList<Cup>();
			for(int j = 0; j < 14; j++)
			{
				int v = input.board.get(j).myStones;
				Cup c = new Cup(v);
				l.add(c);
			}
			Board b = new Board(l);
			Board c = makeMove(b, i + 6);
			TreeNode n = new TreeNode(1, c);
			c.calcValue();
			n.setValue(c.value);

			n.parentNode = root;
			if(i == 1)
				root.node1 = n;
			else if(i == 2) 
				root.node2 = n;
			else if(i == 3) 
				root.node3 = n;
			else if(i == 4) 
				root.node4 = n;
			else if(i == 5) 
				root.node5 = n;
			else
				root.node6 = n;
		}
		
		for(int j = 1; j < 7; j++)
		{
			Board bb = new Board();
			if(j == 1)
				bb = root.node1.board;
			else if(j == 2) 
				bb = root.node2.board;
			else if(j == 3) 
				bb = root.node3.board;
			else if(j == 4) 
				bb = root.node4.board;
			else if(j == 5) 
				bb = root.node5.board;
			else
				bb = root.node6.board;
			
			for(int i = 1; i < 7; i++)
			{
				LinkedList<Cup> l = new LinkedList<Cup>();
				for(int k = 0; k < 14; k++)
				{
					int v = bb.board.get(k).myStones;
					Cup c = new Cup(v);
					l.add(c);
				}
				Board b = new Board(l);
				Board c = humanMove(b, i - 1);
				TreeNode n = new TreeNode(2, c);
				c.calcValue();
				n.setValue(c.value);

				n.parentNode = root;
				if(j == 1 && i == 1)
					root.node1.node1 = n;
				else if(j == 1 && i == 2) 
					root.node1.node2 = n;
				else if(j == 1 && i == 3) 
					root.node1.node3 = n;
				else if(j == 1 && i == 4) 
					root.node1.node4 = n;
				else if(j == 1 && i == 5) 
					root.node1.node5 = n;
				else if(j == 1 && i == 6) 
					root.node1.node6 = n;
				
				else if(j == 2 && i == 1)
					root.node2.node1 = n;
				else if(j == 2 && i == 2) 
					root.node2.node2 = n;
				else if(j == 2 && i == 3) 
					root.node2.node3 = n;
				else if(j == 2 && i == 4) 
					root.node2.node4 = n;
				else if(j == 2 && i == 5) 
					root.node2.node5 = n;
				else if(j == 2 && i == 6) 
					root.node2.node6 = n;
				
				else if(j == 3 && i == 1)
					root.node3.node1 = n;
				else if(j == 3 && i == 2) 
					root.node3.node2 = n;
				else if(j == 3 && i == 3) 
					root.node3.node3 = n;
				else if(j == 3 && i == 4) 
					root.node3.node4 = n;
				else if(j == 3 && i == 5) 
					root.node3.node5 = n;
				else if(j == 3 && i == 6) 
					root.node3.node6 = n;
				
				else if(j == 4 && i == 1)
					root.node4.node1 = n;
				else if(j == 4 && i == 2) 
					root.node4.node2 = n;
				else if(j == 4 && i == 3) 
					root.node4.node3 = n;
				else if(j == 4 && i == 4) 
					root.node4.node4 = n;
				else if(j == 4 && i == 5) 
					root.node4.node5 = n;
				else if(j == 4 && i == 6) 
					root.node4.node6 = n;
				
				else if(j == 5 && i == 1)
					root.node5.node1 = n;
				else if(j == 5 && i == 2) 
					root.node5.node2 = n;
				else if(j == 5 && i == 3) 
					root.node5.node3 = n;
				else if(j == 5 && i == 4) 
					root.node5.node4 = n;
				else if(j == 5 && i == 5) 
					root.node5.node5 = n;
				else if(j == 5 && i == 6) 
					root.node5.node6 = n;
				
				else if(j == 6 && i == 1)
					root.node6.node1 = n;
				else if(j == 6 && i == 2) 
					root.node6.node2 = n;
				else if(j == 6 && i == 3) 
					root.node6.node3 = n;
				else if(j == 6 && i == 4) 
					root.node6.node4 = n;
				else if(j == 6 && i == 5) 
					root.node6.node5 = n;
				else
					root.node6.node6 = n;
			}
		}
		
		root.node2.board.calcValue();
		for(int i = 1; i < 7; i++)
		{
			int min = 100000;
			TreeNode tn = new TreeNode();
			
			if(i == 1)
				tn = root.node1;
			else if(i == 2) 
				tn = root.node2;
			else if(i == 3) 
				tn = root.node3;
			else if(i == 4) 
				tn = root.node4;
			else if(i == 5) 
				tn = root.node5;
			else if(i == 6) 
				tn = root.node6;
			
			for(int j = 1; j < 7; j++)
			{
				Board d = new Board();
				if(i == 1)
					d = tn.node1.board;
				else if(i == 2) 
					d = tn.node2.board;
				else if(i == 3) 
					d = tn.node3.board;
				else if(i == 4) 
					d = tn.node4.board;
				else if(i == 5) 
					d = tn.node5.board;
				else if(i == 6) 
					d = tn.node6.board;
				
				d.calcValue();
				if(d.value < min && root.board.board.get(i + 6).myStones != 0)
					min = d.value;
			}
			
			if(i == 1)
				root.node1.value = min;
			else if(i == 2) 
				root.node2.value = min;
			else if(i == 3) 
				root.node3.value = min;
			else if(i == 4) 
				root.node4.value = min;
			else if(i == 5) 
				root.node5.value = min;
			else if(i == 6) 
				root.node6.value = min;
		}

		int max = -10000;
		for(int i = 1; i < 7; i++)
		{
			Board d;
			if(i == 1)
				d = root.node1.board;
			else if(i == 2) 
				d = root.node2.board;
			else if(i == 3) 
				d = root.node3.board;
			else if(i == 4) 
				d = root.node4.board;
			else if(i == 5) 
				d = root.node5.board;
			else
				d = root.node6.board;
			
			if(d.value > max && root.board.board.get(i + 6).myStones != 0)
				max = d.value;
		}
		
		int move = 0;
		if(max == root.node1.value && root.board.board.get(7).myStones != 0)
			move = 1;
		else if(max == root.node2.value && root.board.board.get(8).myStones != 0)
			move = 2;
		else if(max == root.node3.value && root.board.board.get(9).myStones != 0)
			move = 3;
		else if(max == root.node4.value && root.board.board.get(10).myStones != 0)
			move = 4;
		else if(max == root.node5.value && root.board.board.get(11).myStones != 0)
			move = 5;
		else if(root.board.board.get(12).myStones != 0)
			move = 6;
		
		System.out.println(move);
		
		if(move == 0)
			return root.board;
		else
		{
			Board r = makeMove(root.board, move + 6);
			return r;
		}
	}

	public Board makeMove(Board input, int move)
	{ //temporarily removed moving again
		int numStones = input.board.get(move).myStones;
		input.board.get(move).setStones(0);
		int e = 1 + move;
		
		for(int i = 1; i <= numStones; i++)
		{
			if(e == 6)
				e = 7;
			if(e == 14)
				e = 0;
			Cup c = input.board.get(e);
			input.board.get(e).setStones(c.myStones + 1);
			e++;
		}
		
		int opposite = 0;
		if(input.board.get(e - 1).myStones == 1 && e - 1 > 6 && e - 1 < 13)
		{
			if(e - 1 == 7)
				opposite = 5;
			else if(e - 1 == 8)
				opposite = 4;
			else if(e - 1 == 9)
				opposite = 3;
			else if(e - 1 == 10)
				opposite = 2;
			else if(e - 1 == 11)
				opposite = 1;
			else if(e - 1 == 12)
				opposite = 0;
			
			int newStones = input.board.get(13).myStones +
			input.board.get(opposite).myStones;
			input.board.get(13).setStones(newStones);
			input.board.get(opposite).setStones(0);
		}
		return input;
	}

	public Board humanMove(Board input, int move)
	{ //temporarily removed moving again
		int numStones = input.board.get(move).myStones;
		input.board.get(move).setStones(0);
		int e = 1 + move;
		
		for(int i = 1; i <= numStones; i++)
		{
			if(e == 13)
				e = 0;
			Cup c = input.board.get(e);
			input.board.get(e).setStones(c.myStones + 1);
			e++;
		}

		int opposite = 0;
		if(input.board.get(e - 1).myStones == 1 && e - 1 < 6)
		{
			if(e - 1 == 5)
				opposite = 7;
			else if(e - 1 == 4)
				opposite = 8;
			else if(e - 1 == 3)
				opposite = 9;
			else if(e - 1 == 2)
				opposite = 10;
			else if(e - 1 == 1)
				opposite = 11;
			else if(e - 1 == 0)
				opposite = 12;
			
			int newStones = input.board.get(6).myStones +
			input.board.get(opposite).myStones;
			input.board.get(6).setStones(newStones);
			input.board.get(opposite).setStones(0);
		}
		return input;
	}
}
