
public class Solver extends VerifySudoku
{
	int counter=0;
	boolean hasNoEmptyCell=true;
	int qmat[][];
	Solver()
	{
		qmat=new int[9][9];
	}
	
	// This is the backtracking algorithm for solving the Sudoku
	//It also gives the number of Solutions of the half- Sudoku.
	public void solveSudoku(int[][] mat,int order)
	{
		int row=-1;
		int col=-1;
		hasNoEmptyCell=true;
		for(int i=0;i<order;i++)
		{
			for(int j=0;j<order;j++)
			{
				if(mat[i][j]==0)
				{
					hasNoEmptyCell=false; //Means the matrix has an empty cell
					row=i;
					col=j;
					break;
					}
				}
			if(!hasNoEmptyCell)
				break;
			}
		if(hasNoEmptyCell)  //This is the condition from where the recursion starts returning.
		{
			//Counter will help to keep track of number of solutions
			counter+=1;
			for(int i=0;i<order;i++)
			{
				for(int j=0;j<order;j++)
				{
					//qmat is used for storing just one of the solutions of sudoku
					qmat[i][j]=mat[i][j];
				}
			}
			return;
		}
		for(int i=1;i<=order;i++)
		{
			if(CheckValidityOfSudoku(mat,order,row,col,i)) //Calling the CheckValidityOfSudoku function since Solver extends VerifySudoku
			{
				mat[row][col]=i;
				solveSudoku(mat,order);
				  //This will be executed only when the recursion starts returning 
				mat[row][col]=0;
			}
		}
		return;
		}
}