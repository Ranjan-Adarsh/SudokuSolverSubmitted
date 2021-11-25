import java.awt.Dimension;
import javax.swing.text.PlainDocument;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;



public class NineSquare extends JPanel {
	
	/**
	 * @author adarsh
	 */
	public static final long serialVersionUID = 1L;
	private static final int BORDER_WIDTH=3;
	public JTextField c,n,s,e,w,nw,ne,sw,se;
	public JTextField[] sudokuFields= new JTextField[] {
			//For the cells direction with respect to center
			nw,n,ne,w,c,e,sw,s,se
	};
	public NineSquare(Color bgC)
	{
		//Setting layput of the small grids
		setLayout(new GridLayout(3,3));
		initSudokuGui();
		//Setting background of the Sudoku Grid
		setBackground(bgC);
	}
	public Dimension getPreferreddDimension()
	{
		//Setting preferred dimension
		return new Dimension(500,500);

	}
	private void initSudokuGui()
	{
		for (int k=0;k<sudokuFields.length;k++)
		{
			sudokuFields[k]= new JTextField(1);
			sudokuFields[k].setDocument(new NumericalDocument());
			add(sudokuFields[k]);
		}
		//Setting Borders of the cells
		setBorder(BorderFactory.createMatteBorder(BORDER_WIDTH,BORDER_WIDTH,BORDER_WIDTH,BORDER_WIDTH,Color.BLACK));
	}
	
	
	public static class NumericalDocument extends PlainDocument
	{
		/**
		 * @author adarsh
		 */
		private static final long serialVersionUID = 1L;
		String digits="0123456789";
		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (getLength() == 0 && str.length() == 1 && digits.contains(str)) {
            	//The given code stores an input in matrix only when the input is a single digit integer from range 0-9.
                super.insertString(offs, str, a);
            }
            else {
            	//If an invalid input is eneterd it will prompt a beep in the system
                Toolkit.getDefaultToolkit().beep();
            }
        }
	}
}