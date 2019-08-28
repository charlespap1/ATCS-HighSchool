import java.util.Scanner;
import java.io.*;

public class SudokuBoard
{
	/**
		field storing values that represent the board
	*/
	private String[][] board;
	
	/**
		boolean field storing true if board can be solved, false if not
	*/
	private boolean solvable;
	
	/**
		default constructor that initializes the board and sets all values to "-" as well as solvable to true
	*/
	public SudokuBoard()
	{
		board = new String[9][9];
		for(int r = 0; r < 9; r++)
		{
			for(int c = 0; c < 9; c++)
			{
				board[r][c] = "-";
			}
		}
		solvable = true;
	}
	
	/**
		copy constructor that copies the board array and solvable fields
		@param s SudokuBoard that is being copied
	*/
	public SudokuBoard(SudokuBoard s)
	{
		board = new String[9][9];
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				board[row][col] = s.get(row,col);
			}
		}
		solvable = s.getSolvable();
	}
	
	/**
		constructor that takes in the name of a csv file and uses the values to initialize the board
		@param fileName name of the csv file being read from
	*/
	public SudokuBoard(String fileName)
	{
		board = new String[9][9];
		File file = new File(fileName);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + fileName );
			System.exit(1);
		}
		int count = 0;
		while(input.hasNextLine())
		{
			String line = input.nextLine();
			String[] s = line.split(",");
			for(int i = 0; i < 9; i++)
			{
				board[count][i] = s[i];
			}
			count++;
		}
		if(isSolvable())
		{
			solvable = true;
		}
		else
		{
			solvable = false;
		}
	}
	
	/**
		returns the field solvable
		@return boolean value that represents whether the board is solvable or not
	*/
	public boolean getSolvable()
	{
		return solvable;
	}
	
	/**
		checks to see if board is solvable
		@return true if board is valid, false if not
	*/
	public boolean isSolvable()
	{
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(!(board[row][col].equals("-")))
				{
					int val = Integer.parseInt(board[row][col]);
					board[row][col] = "-";
					if(canPlace(row,col,val) == false)
					{
						board[row][col] = Integer.toString(val);
						return false;
					}
					else
					{
						board[row][col] = Integer.toString(val);
					}
				}
			}
		}
		return true;
	}
	
	/**
		places a specific value at a specific row and col in board as a String
		@param r int representing row the value will be placed in
		@param c int representing col the value will be placed in
		@param n int representing the value being placed in board
	*/
	public void place(int r, int c, int n)
	{
		board[r][c] = Integer.toString(n);
	}
	
	/**
		gets the String value stored at a specific location in board
		@param r int value representing location for row
		@param c int value representing location for column
		@return String value at specified location
	*/
	public String get(int r, int c)
	{
		return board[r][c];
	}
	
	/**
		removes value from specific location by replacing with "-"
		@param r int value representing location of row being removed
		@param c int value representing location of col being removed
	*/
	public void remove(int r, int c)
	{
		board[r][c] = "-";
	}
	
	/**
		checks whether a certain value can be placed at a specific location. Checks row col and grid
		@param r int representing row being checked
		@param c int representing col being checked
		@param n int value trying to see if can be placed
		@return true if it can be placed, false if not
	*/
	public boolean canPlace(int r, int c, int n)
	{
		String valN = Integer.toString(n);
		if(!(board[r][c].equals("-")))
		{
			return false;
		}
		else
		{
			if(checkRow(r, valN) == false)
			{
				return false;
			}
			if(checkCol(c, valN) == false)
			{
				return false;
			}
			if(checkGrid(r,c,valN) == false)
			{
				return false;
			}
			return true;
		}
	}
	
	/**
		checks to see if certain value can be placed in specified row
		@param row int value representing the row being checked
		@param val String value that is being checked for within row
		@return true if no other occurrences of that string in row, false if there are
	*/
	public boolean checkRow(int row, String val)
	{
		for(int index = 0; index < board[row].length; index++)
		{
			if(board[row][index].equals(val))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
		checks to see if certain value can be placed in specified col
		@param col int value representing the col being checked
		@param val String value that is being checked for within col
		@return true if no other occurrences of that string in col, false if there are
	*/
	public boolean checkCol(int col, String val)
	{
		for(int index = 0; index < board.length; index++)
		{
			if(board[index][col].equals(val))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
		checks to see if certain value can be placed in specified grid
		@param r int value representing the row being checked
		@param c int value representing the col being checked
		@param val String value that is being checked for within certain grid
		@return true if no other occurrences of that string in grid, false if there are
	*/
	public boolean checkGrid(int r, int c, String val)
	{
		int[] grid = getGrid(r,c);
		int row = grid[0];
		int col = grid[1];
		for(int rIndex = 0; rIndex < 3; rIndex++)
		{
			for(int cIndex = 0; cIndex < 3; cIndex++)
			{
				if(board[row + rIndex][col + cIndex].equals(val))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
		gets row and col of grid that specific point is in
		@param row int value representing the row finding the grid for
		@param c int value representing the col finding the grid for
		@return int[] with a row and col value marking edge of grid
	*/
	public int[] getGrid(int row, int col)
	{
		int r = 0;
		int c = 0;
		if(row < 3)
		{
			r = 0;
		}
		if(row < 6 && row > 2)
		{
			r = 3;
		}
		if(row > 5)
		{
			r = 6;
		}
		if(col < 3)
		{
			c = 0;
		}
		if(col < 6 && col > 2)
		{
			c = 3;
		}
		if(col > 5)
		{
			c = 6;
		}
		int[] rc = new int[2];
		rc[0] = r;
		rc[1] = c;
		return rc;
	}
	
	/**
		gets the constraint ammount or how many values can be placed at a specific location
		@param r int value representing the row being checked
		@param c int value representing the col being checked
		@return how many values can be placed in a spot in board(aka constraint ammt.)
	*/
	public int getConstraintAmmt(int r, int c)
	{
		int count = 9;
		for(int index = 1; index <= 9; index++)
		{
			if(canPlace(r,c,index) == false)
			{
				count--;
			}
		}
		return count;
	}
	
	/**
		gets the row and col in board of the most constrained spot - least possible moves
		@return int[] with row and col of the most constrained spot
	*/
	public int[] getMostConstrained()
	{
		int mcAmmt = 100;
		int[] mcCoords = new int[2];
		for(int rIndex = 0; rIndex < board.length; rIndex++)
		{
			for(int cIndex = 0; cIndex < board[rIndex].length; cIndex++)
			{
				int temp = getConstraintAmmt(rIndex,cIndex);
				if(temp < mcAmmt && temp != 0)
				{
					mcAmmt = temp;
					mcCoords[0] = rIndex;
					mcCoords[1] = cIndex;
				}
			}
		}
		return mcCoords;
	}
	
	/**
		checks to see if board is solved
		@return true if the board is full, false otherwise
	*/
	public boolean isSolved()
	{
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(board[row][col].equals("-"))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
		gives String representation of the board
		@return String value of what board will look like printed out
	*/
	public String toString()
	{
		String output = "";
		output += "_________________________________________";
		output += "\n";
		output+= "-----------------------------------------";
		for(int i = 0; i < board.length; i++)
		{
			if(i%3 == 0 && i != 0)
			{
				output += "\n";
				output+= "-----------------------------------------";
			}
			output += "\n";
			output += "|";
			for(int j = 0; j < board[i].length; j++)
			{
				if(j%3 == 0)
				{
					output+= "|";
				}
				output += " " + board[i][j] + " |";
			}
			output+= "|";
			output+= "\n";
			output+= "_________________________________________";
		}
		output += "\n";
		output+= "-----------------------------------------";
		
		return output;
	}
}