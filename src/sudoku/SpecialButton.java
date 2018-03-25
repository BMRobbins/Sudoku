package sudoku;

import javax.swing.JButton;

public class SpecialButton extends JButton{
	private int x;
	private int y;
	private int value;
	
	public SpecialButton(int x, int y, String text)
	{
		super(text);
		this.x = x;
		this.y = y;
		this.value = 0;
	}
	
	public SpecialButton(int x, int y, int value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
	}
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getValue()
	{
		return value;
	}

}
