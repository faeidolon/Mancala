import java.util.LinkedList;

public class TreeNode
{
	int value;
	int level;
	Board board;
	TreeNode parentNode, choiceNode;
	TreeNode node1, node2, node3, node4, node5, node6;

	public TreeNode()
	{ }

	public TreeNode(int myLevel, Board myBoard)
	{
		level = myLevel;
		board = myBoard;
	}

	public void setValue(int myVal)
	{
		value = myVal;
	}
}
