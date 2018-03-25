package sudoku;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sudoku {

	private JFrame frame;
	private GameState game;
	private JPanel panel;
	private JPanel boardPanel;
	private JPanel MenuPanel;
	private JButton[][] BoardButtonArray;
	private JButton[][] MenuButtonArray;
	private int count;
	private JButton playAgain;
	private JLabel youWin; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku window = new Sudoku();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sudoku() {
		createGameState();
		initialize();
		createBoardButtons();
		createNumberSelectionMenu();
		boardEventHandlers();
		menuEventHandlers();
	}

	private void createGameState()
	{
		game = new GameState();
		count = 0;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(34, 177, 76));
		frame.setBounds(0, 0, 2000, 2000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(174, 0, 2000, 2000);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(34, 177, 76));
		panel.setLayout(null);
		
		JLabel Logo = new JLabel("");
		Logo.setBounds(0, 0, 1681, 352);
		Image img = new ImageIcon(this.getClass().getResource("/SudokuLogo.png")).getImage();
		Logo.setIcon(new ImageIcon(img));
		panel.add(Logo);
		
		boardPanel = new JPanel();
		boardPanel.setBounds(50, 600, 910, 910);
		boardPanel.setBackground(new Color(0, 0, 0));
		boardPanel.setLayout(new GridLayout(9,9,2,2));
		panel.add(boardPanel);
		
		JLabel countLabel= new JLabel("Times You Checked Answer = 0");
		countLabel.setBounds(1050, 1300, 1200, 200);
		countLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		countLabel.setVisible(true);
		panel.add(countLabel);
		
		youWin = new JLabel("Congratulations You Win!!!");
		youWin.setBounds(50, 1599, 1200, 200);
		panel.add(youWin);
		youWin.setFont(new Font("Tahoma", Font.BOLD, 80));
		youWin.setVisible(false);
		
		JButton CheckAnswer = new JButton("Check Answer");
		CheckAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count++;
				if(checkAnswerMethod())
				{
					youWin.setVisible(true);
					playAgain.setVisible(true);
				}
				else
				{
					countLabel.setText("Times You Checked Answer = " + Integer.toString(count));
				}
					
			}
		});
		CheckAnswer.setBounds(1155, 1200, 400, 100);
		CheckAnswer.setBackground(new Color(255,255,255));
		CheckAnswer.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel.add(CheckAnswer);
		

	}
	
	private void createBoardButtons()
	{
		BoardButtonArray = new JButton[10][10];
		for(int i=0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(game.getBoardIndex(i, j) == 0)
				{
					BoardButtonArray[i][j] = new JButton("");
				}
				else
				{
					BoardButtonArray[i][j] = new JButton(Integer.toString(game.getBoardIndex(i, j)));
				}
				BoardButtonArray[i][j].setFont(new Font("Tahoma", Font.PLAIN, 45));
				BoardButtonArray[i][j].setBounds(0, 0, 98, 98);
				BoardButtonArray[i][j].setBackground(new Color(255,255,255));
				boardPanel.add(BoardButtonArray[i][j]);
			}
		}
	}
	
	private void createNumberSelectionMenu()
	{
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(1200, 750, 310, 310);
		MenuPanel.setBackground(new Color(0, 0, 0));
		MenuPanel.setLayout(new GridLayout(3,3,2,2));
		panel.add(MenuPanel);
		
		JLabel SelectionLabel = new JLabel("Selection NumPad");
		SelectionLabel.setBounds(1175, 650, 400, 100);
		SelectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel.add(SelectionLabel);
		
		playAgain = new JButton("Play Again?");
		playAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetBoard();
			}
		});
		playAgain.setFont(new Font("Tahoma", Font.PLAIN, 45));
		playAgain.setBackground(new Color(255,255,255));
		playAgain.setBounds(1155, 1663, 400, 100);
		playAgain.setVisible(false);
		panel.add(playAgain);
		
		MenuButtonArray = new JButton[10][10];
		int count = 1;
		for(int i=0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				MenuButtonArray[i][j] = new JButton(Integer.toString(count));
				MenuButtonArray[i][j].setFont(new Font("Tahoma", Font.PLAIN, 45));
				MenuButtonArray[i][j].setBounds(0, 0, 98, 98);
				MenuButtonArray[i][j].setBackground(new Color(255,255,255));
				MenuPanel.add(MenuButtonArray[i][j]);
				count++;
			}
		}
	}
	
	private void boardEventHandlers()
	{
		BoardButtonArray[0][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 0))
				{
					clearHighLight();
					colorQuadrantBlue(0,0);
					createHighLight(0,0);
					game.setCurrentHighLight(0,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 1))
				{
					clearHighLight();
					colorQuadrantBlue(0,1);
					createHighLight(0,1);
					game.setCurrentHighLight(0,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 2))
				{
					clearHighLight();
					colorQuadrantBlue(0,2);
					createHighLight(0,2);
					game.setCurrentHighLight(0,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 3))
				{
					clearHighLight();
					colorQuadrantBlue(0,3);
					createHighLight(0,3);
					game.setCurrentHighLight(0,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 4))
				{
					clearHighLight();
					colorQuadrantBlue(0,4);
					createHighLight(0,4);
					game.setCurrentHighLight(0,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 5))
				{
					clearHighLight();
					colorQuadrantBlue(0,5);
					createHighLight(0,5);
					game.setCurrentHighLight(0,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 6))
				{
					clearHighLight();
					colorQuadrantBlue(0,6);
					createHighLight(0,6);
					game.setCurrentHighLight(0,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 7))
				{
					clearHighLight();
					colorQuadrantBlue(0,7);
					createHighLight(0,7);
					game.setCurrentHighLight(0,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[0][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(0, 8))
				{
					clearHighLight();
					colorQuadrantBlue(0,8);
					createHighLight(0,8);
					game.setCurrentHighLight(0,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 0))
				{
					clearHighLight();
					colorQuadrantBlue(1,0);
					createHighLight(1,0);
					game.setCurrentHighLight(1,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 1))
				{
					clearHighLight();
					colorQuadrantBlue(1,1);
					createHighLight(1,1);
					game.setCurrentHighLight(1,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 2))
				{
					clearHighLight();
					colorQuadrantBlue(1,2);
					createHighLight(1,2);
					game.setCurrentHighLight(1,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 3))
				{
					clearHighLight();
					colorQuadrantBlue(1,3);
					createHighLight(1,3);
					game.setCurrentHighLight(1,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 4))
				{
					clearHighLight();
					colorQuadrantBlue(1,4);
					createHighLight(1,4);
					game.setCurrentHighLight(1,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 5))
				{
					clearHighLight();
					colorQuadrantBlue(1,5);
					createHighLight(1,5);
					game.setCurrentHighLight(1,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 6))
				{
					clearHighLight();
					colorQuadrantBlue(1,6);
					createHighLight(1,6);
					game.setCurrentHighLight(1,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 7))
				{
					clearHighLight();
					colorQuadrantBlue(1,7);
					createHighLight(1,7);
					game.setCurrentHighLight(1,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[1][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(1, 8))
				{
					clearHighLight();
					colorQuadrantBlue(1,8);
					createHighLight(1,8);
					game.setCurrentHighLight(1,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 0))
				{
					clearHighLight();
					colorQuadrantBlue(2,0);
					createHighLight(2,0);
					game.setCurrentHighLight(2,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 1))
				{
					clearHighLight();
					colorQuadrantBlue(2,1);
					createHighLight(2,1);
					game.setCurrentHighLight(2,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 2))
				{
					clearHighLight();
					colorQuadrantBlue(2,2);
					createHighLight(2,2);
					game.setCurrentHighLight(2,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 3))
				{
					clearHighLight();
					colorQuadrantBlue(2,3);
					createHighLight(2,3);
					game.setCurrentHighLight(2,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 4))
				{
					clearHighLight();
					colorQuadrantBlue(2,4);
					createHighLight(2,4);
					game.setCurrentHighLight(2,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 5))
				{
					clearHighLight();
					colorQuadrantBlue(2,5);
					createHighLight(2,5);
					game.setCurrentHighLight(2,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 6))
				{
					clearHighLight();
					colorQuadrantBlue(2,6);
					createHighLight(2,6);
					game.setCurrentHighLight(2,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 7))
				{
					clearHighLight();
					colorQuadrantBlue(2,7);
					createHighLight(2,7);
					game.setCurrentHighLight(2,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[2][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(2, 8))
				{
					clearHighLight();
					colorQuadrantBlue(2,8);
					createHighLight(2,8);
					game.setCurrentHighLight(2,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 0))
				{
					clearHighLight();
					colorQuadrantBlue(3,0);
					createHighLight(3,0);
					game.setCurrentHighLight(3,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 1))
				{
					clearHighLight();
					colorQuadrantBlue(3,1);
					createHighLight(3,1);
					game.setCurrentHighLight(3,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 2))
				{
					clearHighLight();
					colorQuadrantBlue(3,2);
					createHighLight(3,2);
					game.setCurrentHighLight(3,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 3))
				{
					clearHighLight();
					colorQuadrantBlue(3,3);
					createHighLight(3,3);
					game.setCurrentHighLight(3,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 4))
				{
					clearHighLight();
					colorQuadrantBlue(3,4);
					createHighLight(3,4);
					game.setCurrentHighLight(3,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 5))
				{
					clearHighLight();
					colorQuadrantBlue(3,5);
					createHighLight(3,5);
					game.setCurrentHighLight(3,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 6))
				{
					clearHighLight();
					colorQuadrantBlue(3,6);
					createHighLight(3,6);
					game.setCurrentHighLight(3,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 7))
				{
					clearHighLight();
					colorQuadrantBlue(3,7);
					createHighLight(3,7);
					game.setCurrentHighLight(3,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[3][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(3, 8))
				{
					clearHighLight();
					colorQuadrantBlue(3,8);
					createHighLight(3,8);
					game.setCurrentHighLight(3,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 0))
				{
					clearHighLight();
					colorQuadrantBlue(4,0);
					createHighLight(4,0);
					game.setCurrentHighLight(4,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 1))
				{
					clearHighLight();
					colorQuadrantBlue(4,1);
					createHighLight(4,1);
					game.setCurrentHighLight(4,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 2))
				{
					clearHighLight();
					colorQuadrantBlue(4,2);
					createHighLight(4,2);
					game.setCurrentHighLight(4,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 3))
				{
					clearHighLight();
					colorQuadrantBlue(4,3);
					createHighLight(4,3);
					game.setCurrentHighLight(4,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 4))
				{
					clearHighLight();
					colorQuadrantBlue(4,4);
					createHighLight(4,4);
					game.setCurrentHighLight(4,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 5))
				{
					clearHighLight();
					colorQuadrantBlue(4,5);
					createHighLight(4,5);
					game.setCurrentHighLight(4,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 6))
				{
					clearHighLight();
					colorQuadrantBlue(4,6);
					createHighLight(4,6);
					game.setCurrentHighLight(4,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 7))
				{
					clearHighLight();
					colorQuadrantBlue(4,7);
					createHighLight(4,7);
					game.setCurrentHighLight(4,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[4][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(4, 8))
				{
					clearHighLight();
					colorQuadrantBlue(4,8);
					createHighLight(4,8);
					game.setCurrentHighLight(4,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});

		BoardButtonArray[5][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 0))
				{
					clearHighLight();
					colorQuadrantBlue(5,0);
					createHighLight(5,0);
					game.setCurrentHighLight(5,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 1))
				{
					clearHighLight();
					colorQuadrantBlue(5,1);
					createHighLight(5,1);
					game.setCurrentHighLight(5,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 2))
				{
					clearHighLight();
					colorQuadrantBlue(5,2);
					createHighLight(5,2);
					game.setCurrentHighLight(5,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 3))
				{
					clearHighLight();
					colorQuadrantBlue(5,3);
					createHighLight(5,3);
					game.setCurrentHighLight(5,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 4))
				{
					clearHighLight();
					colorQuadrantBlue(5,4);
					createHighLight(5,4);
					game.setCurrentHighLight(5,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 5))
				{
					clearHighLight();
					colorQuadrantBlue(5,5);
					createHighLight(5,5);
					game.setCurrentHighLight(5,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 6))
				{
					clearHighLight();
					colorQuadrantBlue(5,6);
					createHighLight(5,6);
					game.setCurrentHighLight(5,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 7))
				{
					clearHighLight();
					colorQuadrantBlue(5,7);
					createHighLight(5,7);
					game.setCurrentHighLight(5,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[5][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(5, 8))
				{
					clearHighLight();
					colorQuadrantBlue(5,8);
					createHighLight(5,8);
					game.setCurrentHighLight(5,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 0))
				{
					clearHighLight();
					colorQuadrantBlue(6,0);
					createHighLight(6,0);
					game.setCurrentHighLight(6,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 1))
				{
					clearHighLight();
					colorQuadrantBlue(6,1);
					createHighLight(6,1);
					game.setCurrentHighLight(6,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 2))
				{
					clearHighLight();
					colorQuadrantBlue(6,2);
					createHighLight(6,2);
					game.setCurrentHighLight(6,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 3))
				{
					clearHighLight();
					colorQuadrantBlue(6,3);
					createHighLight(6,3);
					game.setCurrentHighLight(6,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 4))
				{
					clearHighLight();
					colorQuadrantBlue(6,4);
					createHighLight(6,4);
					game.setCurrentHighLight(6,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 5))
				{
					clearHighLight();
					colorQuadrantBlue(6,5);
					createHighLight(6,5);
					game.setCurrentHighLight(6,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 6))
				{
					clearHighLight();
					colorQuadrantBlue(6,6);
					createHighLight(6,6);
					game.setCurrentHighLight(6,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 7))
				{
					clearHighLight();
					colorQuadrantBlue(6,7);
					createHighLight(6,7);
					game.setCurrentHighLight(6,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[6][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(6, 8))
				{
					clearHighLight();
					colorQuadrantBlue(6,8);
					createHighLight(6,8);
					game.setCurrentHighLight(6,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 0))
				{
					clearHighLight();
					colorQuadrantBlue(7,0);
					createHighLight(7,0);
					game.setCurrentHighLight(7,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 1))
				{
					clearHighLight();
					colorQuadrantBlue(7,1);
					createHighLight(7,1);
					game.setCurrentHighLight(7,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 2))
				{
					clearHighLight();
					colorQuadrantBlue(7,2);
					createHighLight(7,2);
					game.setCurrentHighLight(7,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 3))
				{
					clearHighLight();
					colorQuadrantBlue(7,3);
					createHighLight(7,3);
					game.setCurrentHighLight(7,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 4))
				{
					clearHighLight();
					colorQuadrantBlue(7,4);
					createHighLight(7,4);
					game.setCurrentHighLight(7,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 5))
				{
					clearHighLight();
					colorQuadrantBlue(7,5);
					createHighLight(7,5);
					game.setCurrentHighLight(7,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 6))
				{
					clearHighLight();
					colorQuadrantBlue(7,6);
					createHighLight(7,6);
					game.setCurrentHighLight(7,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 7))
				{
					clearHighLight();
					colorQuadrantBlue(7,7);
					createHighLight(7,7);
					game.setCurrentHighLight(7,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[7][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(7, 8))
				{
					clearHighLight();
					colorQuadrantBlue(7,8);
					createHighLight(7,8);
					game.setCurrentHighLight(7,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 0))
				{
					clearHighLight();
					colorQuadrantBlue(8,0);
					createHighLight(8,0);
					game.setCurrentHighLight(8,0);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 1))
				{
					clearHighLight();
					colorQuadrantBlue(8,1);
					createHighLight(8,1);
					game.setCurrentHighLight(8,1);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 2))
				{
					clearHighLight();
					colorQuadrantBlue(8,2);
					createHighLight(8,2);
					game.setCurrentHighLight(8,2);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 3))
				{
					clearHighLight();
					colorQuadrantBlue(8,3);
					createHighLight(8,3);
					game.setCurrentHighLight(8,3);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 4))
				{
					clearHighLight();
					colorQuadrantBlue(8,4);
					createHighLight(8,4);
					game.setCurrentHighLight(8,4);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 5))
				{
					clearHighLight();
					colorQuadrantBlue(8,5);
					createHighLight(8,5);
					game.setCurrentHighLight(8,5);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 6))
				{
					clearHighLight();
					colorQuadrantBlue(8,6);
					createHighLight(8,6);
					game.setCurrentHighLight(8,6);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 7))
				{
					clearHighLight();
					colorQuadrantBlue(8,7);
					createHighLight(8,7);
					game.setCurrentHighLight(8,7);
				}
				else
				{
					clearHighLight();
				}
			}
		});
		
		BoardButtonArray[8][8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.getEditable(8, 8))
				{
					clearHighLight();
					colorQuadrantBlue(8,8);
					createHighLight(8,8);
					game.setCurrentHighLight(8,8);
				}
				else
				{
					clearHighLight();
				}
			}
		});
	}
	
	private void menuEventHandlers()
	{
		MenuButtonArray[0][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 1);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("1");
				}
			}
		});
		
		MenuButtonArray[0][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 2);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("2");
				}
			}
		});
		
		MenuButtonArray[0][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 3);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("3");
				}
			}
		});
		
		MenuButtonArray[1][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 4);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("4");
				}
			}
		});
		
		MenuButtonArray[1][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 5);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("5");
				}
			}
		});
		
		MenuButtonArray[1][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 6);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("6");
				}
			}
		});
		
		MenuButtonArray[2][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 7);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("7");
				}
			}
		});
		
		MenuButtonArray[2][1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 8);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("8");
				}
			}
		});
		
		MenuButtonArray[2][2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.checkChoosenBox())
				{
					int[] choice = game.getCurrentChoosenBox();
					game.setUserBoard(choice[0], choice[1], 9);
					BoardButtonArray[choice[0]][choice[1]].setForeground(new Color(140, 138, 137));
					BoardButtonArray[choice[0]][choice[1]].setFont(new Font("Tacoma", Font.PLAIN, 45));
					BoardButtonArray[choice[0]][choice[1]].setText("9");
				}
			}
		});
	}
	
	private void clearHighLight()
	{
		for(int i=0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				BoardButtonArray[i][j].setBackground(new Color(255,255,255));
			}
		}
	}
	
	private void createHighLight(int x, int y)
	{
		for(int i=0; i < 9; i++)
		{
			BoardButtonArray[i][y].setBackground(new Color(247,238,136));
		}
		for(int i=0; i < 9; i++)
		{
			BoardButtonArray[x][i].setBackground(new Color(252, 113, 106));
		}
		BoardButtonArray[x][y].setBackground(new Color(252, 181, 105));
		
	}
	
	private void colorQuadrantBlue(int x, int y)
	{
		if(x < 3 && y < 3)
		{
			for(int i=0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 3 && y < 6)
		{
			for(int i=0; i < 3; i++)
			{
				for(int j = 3; j < 6; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 3 && y < 9)
		{
			for(int i=0; i < 3; i++)
			{
				for(int j = 6; j < 9; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 6 && y < 3)
		{
			for(int i=3; i < 6; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 6 && y < 6)
		{
			for(int i=3; i < 6; i++)
			{
				for(int j = 3; j < 6; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 6 && y < 9)
		{
			for(int i=3; i < 6; i++)
			{
				for(int j = 6; j < 9; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 9 && y < 3)
		{
			for(int i=6; i < 9; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 9 && y < 6)
		{
			for(int i=6; i < 9; i++)
			{
				for(int j = 3; j < 6; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		else if(x < 9 && y < 9)
		{
			for(int i=6; i < 9; i++)
			{
				for(int j = 6; j < 9; j++)
				{
					BoardButtonArray[i][j].setBackground(new Color(163, 253, 255));
				}
			}
		}
		
		
	}
	
	private boolean checkAnswerMethod()
	{
		boolean isCorrect = true;
		for(int i=0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(game.getUserBoardIndex(i,j) != 0)
				{
					if(game.getUserBoardIndex(i,j) != game.getKeyIndex(i, j))
					{
						BoardButtonArray[i][j].setForeground(new Color(249, 0, 0));
						isCorrect = false;
					}
					else
					{
						if(game.getEditable(i, j))
						{
							BoardButtonArray[i][j].setForeground(new Color(140, 138, 137));
						}
					}
				}
				else
				{
					isCorrect = false;
				}
		
			}
		}
		return isCorrect;
	}
	
	private void resetBoard()
	{
		youWin.setVisible(false);
		playAgain.setVisible(false);
		game = new GameState();
		panel.remove(boardPanel);
		boardPanel = new JPanel();
		boardPanel.setBounds(50, 600, 910, 910);
		boardPanel.setBackground(new Color(0, 0, 0));
		boardPanel.setLayout(new GridLayout(9,9,2,2));
		panel.add(boardPanel);
		createBoardButtons();
		boardEventHandlers();
	}
}
