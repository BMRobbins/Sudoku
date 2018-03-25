package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameState {
	 private int[][] key;
	 private int[][] userBoard;
	 private boolean [][] editable;
	 private boolean boxChoosen;
	 private int[] currentChoosenBox;
	
	 public GameState()
	 {
		 createKey();
		 createUserBoard();
		 createEditable();
		 boxChoosen = false;
		 currentChoosenBox = new int[2];
	 }
	 
	 public void setUserBoard(int x, int y, int value)
	 {
		 userBoard[x][y] = value;
	 }
	 
	 public int getUserBoardIndex(int x, int y)
	 {
		 return userBoard[x][y];
	 }
	 
	 private void createKey()
	 {
		 key = new int[10][10];
		 ArrayList<Integer> numList= new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		 Collections.shuffle(numList);
		 
		 // initalize 1st random number
		 initalize1(numList.get(0));
		 initalize2(numList.get(1));
		 initalize3(numList.get(2));
		 initalize4(numList.get(3));
		 initalize5(numList.get(4));
		 initalize6(numList.get(5));
		 initalize7(numList.get(6));
		 initalize8(numList.get(7));
		 initalize9(numList.get(8));
	 }
	 private void initalize1(int randomNum)
	 {
		 // key[row][col]
		 key[0][0] = randomNum;
		 key[1][6] = randomNum;
		 key[2][3] = randomNum;
		 key[3][7] = randomNum;
		 key[4][4] = randomNum;
		 key[5][1] = randomNum;
		 key[6][5] = randomNum;
		 key[7][2] = randomNum;
		 key[8][8] = randomNum;
	 }
	 private void initalize2(int randomNum)
	 {
		 // key[row][col]
		 key[0][1] = randomNum;
		 key[1][7] = randomNum;
		 key[2][4] = randomNum;
		 key[3][8] = randomNum;
		 key[4][5] = randomNum;
		 key[5][2] = randomNum;
		 key[6][6] = randomNum;
		 key[7][3] = randomNum;
		 key[8][0] = randomNum;
	 }
	 private void initalize3(int randomNum)
	 {
		 // key[row][col]
		 key[0][2] = randomNum;
		 key[1][8] = randomNum;
		 key[2][5] = randomNum;
		 key[3][0] = randomNum;
		 key[4][6] = randomNum;
		 key[5][3] = randomNum;
		 key[6][7] = randomNum;
		 key[7][4] = randomNum;
		 key[8][1] = randomNum;
	 }
	 private void initalize4(int randomNum)
	 {
		 // key[row][col]
		 key[0][3] = randomNum;
		 key[1][0] = randomNum;
		 key[2][6] = randomNum;
		 key[3][1] = randomNum;
		 key[4][7] = randomNum;
		 key[5][4] = randomNum;
		 key[6][8] = randomNum;
		 key[7][5] = randomNum;
		 key[8][2] = randomNum;
	 }
	 private void initalize5(int randomNum)
	 {
		 // key[row][col]
		 key[0][4] = randomNum;
		 key[1][1] = randomNum;
		 key[2][7] = randomNum;
		 key[3][2] = randomNum;
		 key[4][8] = randomNum;
		 key[5][5] = randomNum;
		 key[6][0] = randomNum;
		 key[7][6] = randomNum;
		 key[8][3] = randomNum;
	 }
	 private void initalize6(int randomNum)
	 {
		 // key[row][col]
		 key[0][5] = randomNum;
		 key[1][2] = randomNum;
		 key[2][8] = randomNum;
		 key[3][3] = randomNum;
		 key[4][0] = randomNum;
		 key[5][6] = randomNum;
		 key[6][1] = randomNum;
		 key[7][7] = randomNum;
		 key[8][4] = randomNum;
	 }
	 private void initalize7(int randomNum)
	 {
		 // key[row][col]
		 key[0][6] = randomNum;
		 key[1][3] = randomNum;
		 key[2][0] = randomNum;
		 key[3][4] = randomNum;
		 key[4][1] = randomNum;
		 key[5][7] = randomNum;
		 key[6][2] = randomNum;
		 key[7][8] = randomNum;
		 key[8][5] = randomNum;
	 }
	 private void initalize8(int randomNum)
	 {
		 // key[row][col]
		 key[0][7] = randomNum;
		 key[1][4] = randomNum;
		 key[2][1] = randomNum;
		 key[3][5] = randomNum;
		 key[4][2] = randomNum;
		 key[5][8] = randomNum;
		 key[6][3] = randomNum;
		 key[7][0] = randomNum;
		 key[8][6] = randomNum;
	 }
	 private void initalize9(int randomNum)
	 {
		 // key[row][col]
		 key[0][8] = randomNum;
		 key[1][5] = randomNum;
		 key[2][2] = randomNum;
		 key[3][6] = randomNum;
		 key[4][3] = randomNum;
		 key[5][0] = randomNum;
		 key[6][4] = randomNum;
		 key[7][1] = randomNum;
		 key[8][7] = randomNum;
	 }
	 private void createUserBoard()
	 {
		 userBoard = new int[10][10];
		 ArrayList<Integer> randomSpaces = new ArrayList<Integer>(Arrays.asList(4,5));
		 for(int i =0; i < 9; i++)
		 {
			 Collections.shuffle(randomSpaces);
			 userBoard[i] = Arrays.copyOf(key[i],9);
			 chooseBlanks(i, randomSpaces.get(0));
		 }
	 }
	 
	 private void chooseBlanks(int row, int numOfBlanks)
	 {
		 ArrayList<Integer> numList= new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
		 Collections.shuffle(numList);
		 if(numOfBlanks == 4)
		 {
			 userBoard[row][numList.get(0)] = 0;
			 userBoard[row][numList.get(1)] = 0;
			 userBoard[row][numList.get(2)] = 0;
			 userBoard[row][numList.get(3)] = 0;
			 
		 }
		 else
		 {
			 userBoard[row][numList.get(0)] = 0;
			 userBoard[row][numList.get(1)] = 0;
			 userBoard[row][numList.get(2)] = 0;
			 userBoard[row][numList.get(3)] = 0;
			 userBoard[row][numList.get(4)] = 0;
		 }
		 
	 }
	 
	 private void createEditable()
	 {
		 editable = new boolean[10][10];
		 for(int i =0; i < 9; i++)
		 {
			 for(int j = 0; j < 9; j++)
			 {
				 if(userBoard[i][j] == 0)
				 {
					 editable[i][j] = true;
				 }
				 else
				 {
					 editable[i][j] = false;
				 }
			 }
		 }
	 }

	 
	 public Boolean getEditable(int row, int col)
	 {
		 return editable[row][col];
	 }
	 
	 public int getKeyIndex(int row, int col)
	 {
		 return key[row][col];
	 }
	 
	 public int getBoardIndex(int row, int col)
	 {
		 return userBoard[row][col];
	 }
	 
	 public void setCurrentHighLight(int x, int y)
	 {
		 boxChoosen = true;
		 currentChoosenBox[0] = x;
		 currentChoosenBox[1] = y;	 
	 }
	 
	 public void turnOffChoosenBox()
	 {
		 boxChoosen = false;
	 }
	 
	 public boolean checkChoosenBox()
	 {
		 return boxChoosen;
	 }
	 
	 public int[] getCurrentChoosenBox()
	 {
		 return currentChoosenBox;
	 }
}
