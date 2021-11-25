
import java.io.IOException;

import java.util.*;

public class Generator {  //Generator already has the solution, so it will remember it.
	int[] mat[]; 
	int[] qmat[];
    int N; // number of columns/rows.
    int SRN; // square root of N
    int K; // No. Of missing digits
    Scanner sc;
 
    // Constructor
    Generator(int N, int K,Scanner sc)
    {
        this.N = N;
        this.K = K;
        this.sc=sc;
        // Compute square root of N
        Double SRNd = Math.sqrt(N);
        SRN = SRNd.intValue();
        mat = new int[N][N];
        qmat=new int[N][N];
    }
    Generator(int N,int K)
    {
    	this.N = N;
        this.K = K;
        Double SRNd = Math.sqrt(N);
        SRN = SRNd.intValue();
        mat = new int[N][N];
        qmat=new int[N][N];
    }
 
    public int[][] cloneArray(int [][]src)
    {
    	//Deep copy of an array wasn't being made so made this function
    	int dest[][]=new int[src.length][];
    	for(int i=0;i<src.length;i++)
    	{
    		dest[i]=Arrays.copyOf(src[i],src[i].length);
    	}
    	return dest;
    }
    public int[][] choice()
    {
    	System.out.println("1. Generate Sudoku automatically.");
    	System.out.println("2. Make a custom Sudoku.");
    	System.out.println("Enter your Choice: ");
    	int ch=sc.nextInt();
    	if(ch==1)
    	{
    		fillValues();
    	}
    	else if(ch==2)
			try {
				inputElements();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
		{
    		System.out.println("Invalid Input");
    		mat=null;
		}
    	return mat;
    }
 // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<SRN; i++)
            for (int j = 0; j<SRN; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;
 
        return true;
    }
    
    // check in the row for existence
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<N; j++)
           if (mat[i][j] == num)
                return false;
        return true;
    }
 
    // check in the row for existence
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }
    
 // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%SRN, j-j%SRN, num));
    }
    // Sudoku Generator
    public void fillValues()
    {
        // Fill the diagonal of SRN x SRN matrices
    	//Since these diagonal box first filled won't have any conflicts
        fillDiagonal();
        // Fill remaining blocks
        //After successful making of diagonal blocks
        fillRemaining(0, SRN);
        //Cloning the made up sudoku
        qmat=cloneArray(mat);
        // Remove Randomly K digits to make game
        removeKDigits();
    }
 
    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal()
    {
 
        for (int i = 0; i<N; i=i+SRN)
 
            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }
 
    
 
    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<SRN; i++)
        {
            for (int j=0; j<SRN; j++)
            {
                do
                {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));
 
                mat[row+i][col+j] = num;
            }
        }
    }
 
    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }
 
    // A recursive function to fill remaining matrix
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>=N && i<N-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=N && j>=N)
            return true;
 
        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N-SRN)
        {
            if (j==(int)(i/SRN)*SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == N-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }
 
        for (int num = 1; num<=N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                mat[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;
 
                mat[i][j] = 0;
            }
        }
        return false;
    }
 
    // Remove the K no. of digits to
    // complete game
    public void removeKDigits()
    {
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(N*N)-1;
 
            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId/N);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;
 
            // System.out.println(i+" "+j);
            if (mat[i][j] != 0)
            {
                count--;
                mat[i][j] = 0;
            }
        }
        new Grid(mat,N);
        System.out.println("\n\nGrid prepared Successfully");
    }
    public void inputElements() throws IOException
    {
    	String s;
    	int inputMat[];
    	inputMat=new int[3];
    	int p;
    	StringTokenizer st;
    	System.out.println("Enter the number of elements you want to fill in half-Sudoku: ");
    	int n=sc.nextInt();
    	sc.nextLine();
    	System.out.println("Now enter the elements in format 'Row Column Value'");
    	while(n>0 && n<=81)
    	{
    		s=sc.nextLine();
    		s=s.trim();
    		st=new StringTokenizer(s);
    		if(st.countTokens()==3)
    		{
    			p=0;
    			while(st.hasMoreTokens())
    			{
    				inputMat[p]=Integer.parseInt(st.nextToken());
    				p++;
    			}
    			this.mat[inputMat[0]][inputMat[1]]=inputMat[2];		
    		}
    		else
    		{
    			System.out.println("Invalid Input");
    			mat=null;
    			return;
    		}
    	n--;
    	}
    	VerifySudoku v=new VerifySudoku();
    	if(v.CheckValidityOfSudoku(mat, N))
    	{
    		System.out.println("Half-Sudoku is valid.\n\n");
    		new Grid(mat,N);
    		System.out.println();
    	}
    	else
    	{
    		System.out.println("Invalid Sudoku");
    		mat=null;
    	}
    }

}
