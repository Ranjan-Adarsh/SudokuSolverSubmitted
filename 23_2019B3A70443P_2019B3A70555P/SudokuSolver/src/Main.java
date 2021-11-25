//Make a menu type in main method.
//import java.io.*;
import java.util.*;
public class Main {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("If you want to continue in console version Press 1\nIf You want to enter the GUI press 2\nPress any other key to quit");
		String mode;
		Scanner sc= new Scanner(System.in);
		mode=(sc.next()).trim();
		if(mode.equals("1"))
		{
		int N,K;
		boolean run=true;
		while(run)
		{	
			System.out.println("\nWelcome to Sudoku. This will be a fun challenge for you. Ready for some action?\nPress 1 to begin a new game. Press any other key to quit!");
			
			String i="";
			i=sc.next();
			//System.out.print(i);
			if(i.equals("1"))
			{
				K=(int)(Math.random()*31+20);
				N = 9; //N decides the NXN Sudoku but this needs modification in other places also
				int[][] board =new int[N][N];
				Generator sudoku=new Generator(N,K,sc);
				board=sudoku.choice();
				if(board!=null)
				{
					Solver s=new Solver();
					s.solveSudoku(board, N);
					if(s.counter==1){
						new Grid(s.qmat,N);
						sc.reset();
					}
					else
					{
						new Grid(s.qmat,N);
						System.out.println("\nNumber of solutions: "+s.counter);
						sc.reset();
					}
				}
				}
			else {
				
				System.out.println("Thank you for playing!!");
				run=false;
				
			}
			
		}
		sc.close();
		System.exit(0);
	}
	else if(mode.equals("2"))
	{
		GUIFrame g=new GUIFrame();
		g.run();
	}
	else
		System.exit(0);
	}

}