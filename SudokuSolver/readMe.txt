Charles Papandreou
Mr. Burkhart
Advanced Topics in Computer Science 1
8 November, 2016

In order to run my Sudoku Solver, compile and run the SudokuSolver.java file. The output 
is 3 test cases: The solution to a blank board, the solution to a normal board, and the 
output from an unsolvable board. Working with stacks and creating multiple boards was 
initially a confusing concept to wrap my head around but once I did it made a lot of sense 
and seems like a creative and efficient way to go about solving a SudokuBoard. The aspects 
of time and efficiency in this lab added a unique challenge.

1. In terms of design decisions, I decided to add an additional boolean field called 
solvable which allowed me to check once at the creation of the board if it was solvable or 
not and then not need to re-call the isSolvable() method repeatedly for efficiency sake.

2. I performed the solving from a static method in a separate 'runner' class.

3. Aside from the added solvable field, in general I tried to minimize my usage of any type
 of loop, in particular nested loops. Whenever I could possibly find a way to avoid using nested 
 loops I chose to do so.
 
Overall, I felt that this was a thought provoking project that I really enjoyed spending 
my time completing.