package application;

import java.util.ArrayList;
import java.util.List;

import dancingLinks.DancingLinks;
import javafx.scene.control.ComboBox;

public class Sudoku {
	private static final int size = 9;
	private static final int COLS = 324;
	private static final int COLUMN_OFFSET = 81;
	private static final int ROW_OFFSET = 162;
	private static final int REGION_OFFSET = 243;
	private List<ComboBox<Integer>> board;
	
	public Sudoku(List<ComboBox<Integer>> board){
		this.board = board;
	}
	
	public List<Boolean> constraintSet(List<Boolean> rowValues, int row, int col, int num){ 
		for(int i = 0; i < rowValues.size(); i++)
			rowValues.set(i, false);
			
		rowValues.set( row*size + col,true);  
		rowValues.set((COLUMN_OFFSET + col*size + num), true);
		rowValues.set((ROW_OFFSET + row*size + num), true);
		int boxSize = (int) Math.sqrt(size);
		int box =  boxSize*(row / boxSize) + (col / boxSize);
		rowValues.set((REGION_OFFSET + box*size + num), true);
		
		return rowValues;
	}
	
	public DancingLinks convertSToDL(){
		DancingLinks dlBoard =  new DancingLinks();
		for(int i = 0; i < COLS; i++)
			dlBoard.addColumnNode(Integer.toString(i));
		
		List<Boolean> rowValues = new ArrayList<>(COLS);
		for(int row = 0; row < size; row++){
			for(int col = 0; col < size; col++){
				if(board.get(row*size + col).getValue() == null){
					for(int num = 0; num < size; num++){
						dlBoard.addRow(constraintSet(rowValues, row, col, num));
					}
				}else{
					dlBoard.addRow( constraintSet(rowValues, row, col, board.get(row*size + col).getValue()));
				}
			}
		}
		return dlBoard;
	}
	
	public List<Integer> solve(){
		return null;
	}
}
