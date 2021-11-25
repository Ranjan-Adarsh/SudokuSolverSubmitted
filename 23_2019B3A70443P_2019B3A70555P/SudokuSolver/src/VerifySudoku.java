//Check if the whole Sudoku is Valid 
public class VerifySudoku {
	int mat[][];
	int order;
	int SRN;
	boolean CheckValidityOfSudoku(int mat[][],int order)
	{
		this.order=order;
		SRN=(int)Math.sqrt(order);
		this.mat=new int[order][order];
		int val;
		for(int i=0;i<order;i++)
		{
			for(int j=0;j<order;j++)
			this.mat[i][j]=mat[i][j];
		}
		
		for(int i=0;i<order;i++)
		{
			for(int j=0;j<order;j++)
			{
				val=this.mat[i][j];
				this.mat[i][j]=0;
				if((unUsedInRow(i,val) && unUsedInCol(j,val) && unUsedInBox(i-i%SRN,j-j%SRN,val)) == false)
					return false;
				this.mat[i][j]=val;
			}
		}
		return true;	
	}
	
	boolean CheckValidityOfSudoku(int mat[][],int order,int row, int col,int val)
	{
		this.order=order;
		SRN=(int)Math.sqrt(order);
		this.mat=new int[order][order];
		for(int i=0;i<order;i++)
		{
			for(int j=0;j<order;j++)
			this.mat[i][j]=mat[i][j];
		}
		
		if((unUsedInRow(row,val) && unUsedInCol(col,val) && unUsedInBox(row-row%SRN,col-col%SRN,val)) == false)
			return false;
		return true;
	}
	boolean unUsedInBox(int rowStart, int colStart, int num)
    {
		if(num>=0 && num<=9)
		{
			if(num!=0)
			{
				for (int i = 0; i<SRN; i++)
					for (int j = 0; j<SRN; j++)
						if (mat[rowStart+i][colStart+j]==num)
							return false;
			}
        return true;
		}
		else 
			return false;
    }
	boolean unUsedInRow(int i,int num)
    {
		if(num>=0 && num<=9)
		{
			if(num!=0)
			{
				for (int j = 0; j<order; j++)
					if (mat[i][j] == num)
						return false;
			}
        return true;
		}
		else 
			return false;
    }
	boolean unUsedInCol(int j,int num)
    {
		if(num==0)
			return true;
        for (int i = 0; i<order; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

}
