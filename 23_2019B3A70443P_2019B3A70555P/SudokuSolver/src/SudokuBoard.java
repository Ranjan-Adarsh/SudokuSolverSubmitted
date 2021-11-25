import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class SudokuBoard extends JPanel {
	/**
	 * @Author adarsh
	 */
	private static final long serialVersionUID = 1L;
	NineSquare[] sudokuGridSquare=new NineSquare[9];
	Color[] bg= {Color.red.brighter(), Color.gray};
	public SudokuBoard(){
		setLayout(new GridLayout(3,3));
		for(int j=0;j<sudokuGridSquare.length;j++)
		{
			sudokuGridSquare[j]= new NineSquare(bg[j%2]);
			add(sudokuGridSquare[j]);
		}
	}	
}

