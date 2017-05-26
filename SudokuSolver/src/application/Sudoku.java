package application;

import java.util.ArrayList;

import dancingLinks.DancingLinks;

public class Sudoku {
	private static final int size = 9;

	public static int getSize() {
		return size;
	}

	private static final int COLS = 324;
	private static final int COLUMN_OFFSET = 2*size*size;
	private static final int ROW_OFFSET = size*size;
	private static final int REGION_OFFSET = 3*size*size;
	private static final int boxSize = (int) Math.sqrt(size);
	
	private int[] board;
	
	public Sudoku(int[] board){
		this.board = board;
	}
	
	public boolean[] constraintSet(boolean[] rowValues, int row, int col, int num){ 
		for(int i = 0; i < rowValues.length; i++)
			rowValues[i]= false;
			
		rowValues[row*size + col] = true;  
		rowValues[ROW_OFFSET + col*size + num - 1] = true;
		rowValues[COLUMN_OFFSET + row*size + num - 1] = true;	
		int box =  boxSize*(row / boxSize) + (col / boxSize);
		rowValues[REGION_OFFSET + box*size + num - 1] = true;
		
		return rowValues;
	}
	
	public DancingLinks convertSToDL(){
		DancingLinks dlBoard =  new DancingLinks();
		
		for(int x=0; x < size; x++) {
		      for(int y=0; y < size; y++) {
		        dlBoard.addColumnNode((new StringBuilder(x + "-" + y)).toString());
		      }
		}
		
		for(int row = 0; row < size; row++) {
		      for(int num = 1; num <= size; num++) {
		        dlBoard.addColumnNode((new StringBuilder(num + "inRow" + row)).toString());
		      }
		}
		
		    
	    for(int col = 0; col < size; col++) {
	      for(int num = 1; num <= size; num++) {
	        dlBoard.addColumnNode((new StringBuilder(num + "inCol" + col)).toString());
	      }
	    }
	    
	    for(int x=0; x < boxSize; x++) {
	      for(int y=0; y < boxSize; y++) {
	        for(int num=1; num <= size; num++) {
	          dlBoard.addColumnNode((new StringBuilder(num + "inBox" + (2*x+y)% size)).toString());
	        }
	      }
	    }
	   
	    
		
	    boolean[] rowValues = new boolean[COLS];
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				if(board[col*size + row] == 0){
					for(int num = 1; num <= size; num++){
						dlBoard.addRow(constraintSet(rowValues, row, col, num));
					}
				}else{
					dlBoard.addRow( constraintSet(rowValues, row, col, board[col*size + row]));
				}
			}
		}
		return dlBoard;
	}
	
	 public void solve() {
		    DancingLinks model = convertSToDL();
		    model.search(new ArrayList<>());
	 }
}
