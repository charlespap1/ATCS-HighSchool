public class SudokuSolver
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		SudokuBoard s = new SudokuBoard("HardSudoku.csv");
		long tStart = System.currentTimeMillis();
		System.out.println(solve(s));
		System.out.println("Time to solve: " + (System.currentTimeMillis() - tStart));
		System.out.println("");
		SudokuBoard s1 = new SudokuBoard("BlankBoard.csv");
		tStart = System.currentTimeMillis();
		System.out.println(solve(s1));
		System.out.println("Time to solve: " + (System.currentTimeMillis() - tStart));
		System.out.println("");
		SudokuBoard s2 = new SudokuBoard("Ones.csv");
		tStart = System.currentTimeMillis();
		System.out.println(solve(s2));
		System.out.println("Time to solve: " + (System.currentTimeMillis() - tStart));
	}
	
	/**
		method that creates a stack, finds most constrained spot, pushs all possibilities 
		for that spot onto the stack, and then narrows down continuing the process until 
		the board is solved
		@param s initial SudokuBoard that is being solved
		@return completed/solved SudokuBoard
	*/
	public static SudokuBoard solve(SudokuBoard s)
	{
		if(s.getSolvable() == false)
		{
			System.out.println("This board is NOT solvable!!!");
			return s;
		}
		Stack<SudokuBoard> solutions = new LinkedList<SudokuBoard>();
		solutions.push(s);
		while(solutions.peek().isSolved() == false)
		{
			SudokuBoard curr = solutions.pop();
			int[] constraints = curr.getMostConstrained();
			for(int i = 1; i < 10; i++)
			{
				if(curr.canPlace(constraints[0],constraints[1], i))
				{
					SudokuBoard nBoard = new SudokuBoard(curr);
					nBoard.place(constraints[0], constraints[1], i);
					solutions.push(nBoard);
				}
			}
		}
		return solutions.pop();
	}
}