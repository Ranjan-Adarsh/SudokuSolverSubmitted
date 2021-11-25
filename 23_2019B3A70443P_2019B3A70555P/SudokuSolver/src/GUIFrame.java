//This is the GUI FRame made using swing
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.*;
public class GUIFrame {
	
	public void run(){
		
		JFrame SudokuFrame=new JFrame("");
		JPanel SudokuPanel=new JPanel();
		SudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SudokuBoard ques=new SudokuBoard();
		SudokuPanel.add(ques); //This is the input Sudoku Board.
		JButton sol=new JButton("Solve");
		JButton gen=new JButton("Generate Auto");
		JButton res=new JButton("Reset");
		JButton fill=new JButton("Fill Sudoku");
		Box box=Box.createVerticalBox();
		box.add(gen);
		box.add(Box.createVerticalStrut(10));
		box.add(fill);
		box.add(Box.createVerticalStrut(10));
		box.add(sol);
		box.add(Box.createVerticalStrut(10));
		box.add(res);
		SudokuPanel.add(box);
		SudokuBoard solved=new SudokuBoard();
		SudokuPanel.add(solved);
		JLabel a=new JLabel("Ouput Indicator");
		JLabel b=new JLabel("Input Area");
		final JTextField tf=new JTextField();
		final JTextArea inp=new JTextArea();
		tf.setBounds(520,250,150,20);
		inp.setBounds(100,50, 150,550);
		b.setBounds(100,20,100,20);
		a.setBounds(520,230,100,20);
		SudokuFrame.add(tf);
		box.add(Box.createVerticalStrut(10));
		SudokuFrame.add(b);
		SudokuFrame.add(a);
		SudokuFrame.add(inp);
		SudokuFrame.add(SudokuPanel);
		SudokuFrame.pack();
		SudokuFrame.setVisible(true);
		//SudokuFrame.setResizable(false);
		int mat[]=new int[9];
		int sendMat[][]=new int[9][9];
		int getMat[][]=new int[9][9];
		
		//Event Listener for Solving of Sudoku
		sol.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String s="";
				int R,C;
				for(int i=0;i<9;i++)
				{
					R=(i/3)*3;
					C=(i%3)*3;
					for(int j=0;j<9;j++)
					{
						if(ques.sudokuGridSquare[i].sudokuFields[j].getText().equals(""))
						{
							mat[j]=0;
						}
						else
							mat[j]=Integer.parseInt(ques.sudokuGridSquare[i].sudokuFields[j].getText());
					sendMat[R+(j/3)][C+(j%3)]=mat[j];
					}
				}
				
				//Till Here Whtever is enetered in The GUI left matrix, its stored in sendMat
				for(int i=0;i<9;i++)// Function just for testing now
				{
					for(int j=0;j<9;j++)
					{
						s+=sendMat[i][j];
					}
				}
				tf.setText(s);
				//Rest code from here
				VerifySudoku v=new VerifySudoku();
				if(v.CheckValidityOfSudoku(sendMat, 9))
				{
					Solver algo=new Solver();  //Use of Solver Class
					algo.solveSudoku(sendMat, 9);
					if(algo.counter==0) {
						tf.setText("Number of Solutions: 0");
					}
					else {
						//start
						for(int i=0;i<9;i++)
						{
							for(int j=0;j<9;j++)
							{
								getMat[i][j]=algo.qmat[i][j];
							}
						}
						int SquareBox;
						for(int i=0;i<9;i++)
						{
							for(int j=0;j<9;j++)
							{
								SquareBox=(i/3)*3+(j/3);
								if(getMat[i][j]==0)
								{
									solved.sudokuGridSquare[SquareBox].sudokuFields[(i%3)*3+(j%3)].setText("");
								}
								else
									solved.sudokuGridSquare[SquareBox].sudokuFields[(i%3)*3+(j%3)].setText(Integer.toString(getMat[i][j]));
							}
						}
						//end
						tf.setText("Number of Solutions: "+algo.counter);
					}
				}
				else
				{
					tf.setText("Invalid Sudoku");
					
				}
			}
		});
		
		//Event Listener for Generate Auto of Sudoku
		gen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int N=9;
				int K=(int)(Math.random()*31+20);
				Generator sudoku=new Generator(N,K);
				sudoku.fillValues();
				for(int i=0;i<N;i++)
				{
					for(int j=0;j<N;j++)
					{
						sendMat[i][j]=sudoku.mat[i][j];
					}
				}
				int SquareBox;
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						SquareBox=(i/3)*3+(j/3);
						if(sendMat[i][j]==0)
						{
							ques.sudokuGridSquare[SquareBox].sudokuFields[(i%3)*3+(j%3)].setText("");
						}
						else
							ques.sudokuGridSquare[SquareBox].sudokuFields[(i%3)*3+(j%3)].setText(Integer.toString(sendMat[i][j]));
					}
				}
			}
		});
		
		//Event Listener for reset
		res.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						for(int i=0;i<9;i++)
						{
							for(int j=0;j<9;j++)
							{
								ques.sudokuGridSquare[i].sudokuFields[j].setText("");
								solved.sudokuGridSquare[i].sudokuFields[j].setText("");
							}
						}
						tf.setText("");
						inp.setText("");
					}
					
				});
		
		fill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try {
				String[] text=inp.getText().split("\\n");
				int noOfRows=inp.getLineCount();
				String[] line;
				int r,c,v;
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						sendMat[i][j]=0;
					}
				}
				for(int i=0;i<noOfRows;i++)
				{
					line=text[i].split(" ");
					r=Integer.parseInt(line[0]);
					c=Integer.parseInt(line[1]);
					v=Integer.parseInt(line[2]);
					sendMat[r][c]=v;
					
				}
				VerifySudoku ver=new VerifySudoku();
				if(ver.CheckValidityOfSudoku(sendMat, 9))
				{
				int SquareBox;
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						SquareBox=(i/3)*3+(j/3);
						if(sendMat[i][j]==0)
						{
							ques.sudokuGridSquare[SquareBox].sudokuFields[(i%3)*3+(j%3)].setText("");
						}
						else
							ques.sudokuGridSquare[SquareBox].sudokuFields[(i%3)*3+(j%3)].setText(Integer.toString(sendMat[i][j]));
					}
				}
				}
				else {
					tf.setText("Invalid Sudoku");
				}
				
			}
				catch(Exception d)
				{
					tf.setText("Inavlid Sudoku");
				}
			}
		});
		
		
		
	}  
}

