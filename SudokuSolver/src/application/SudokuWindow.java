package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuWindow {
	private int[][] board;
	private int size = Sudoku.getSize();

	public SudokuWindow(Stage stage) {
		board = new int[size][size];
		stage.setTitle("Sudoku Solver");
		VBox root = new VBox();
	    Scene scene = new Scene(root, 400, 450);
	    scene.getStylesheets().add(SudokuWindow.class.getResource("application.css").toExternalForm());
	    createSudokuBoard(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void createSudokuBoard(VBox root){
		List<ComboBox<Integer>> combo = new ArrayList<>();
		GridPane pane = new GridPane();
		for(int row = 1; row <= size; row++){
		    for(int col = 1; col <= size; col++){
		    	ComboBox<Integer> temp = new ComboBox<>();
		    	combo.add(temp);
		    	temp.getItems().addAll(0,1,2,3,4,5,6,7,8,9);
		    	temp.setEditable(false);
		    	temp.setMinSize(35, 35);
		    	temp.setMaxSize(35, 35);
		    	if((col % 3)==0){
		    		GridPane.setMargin(temp, new Insets(0,5,0,0));
		    	}
		    	if((row % 3)==0){
		    		GridPane.setMargin(temp, new Insets(0,0,5,0));
			    }	
		    	pane.add(temp, col, row);
		    }	
		 }
		 pane.setAlignment(Pos.CENTER);
		 Button btn = new Button("SOLVE");
		 btn.setStyle("-fx-font: 20 arial;");
		 btn.setMinWidth(50);
		 btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	for(int i = 0; i < size; i++)
	            		for(int j = 0; j < size; j++){
	            			if(combo.get(i*size+j).getValue() == null || combo.get(i*size+j).getValue() == 0){
	            				board[i][j] = 0;
	            			}else
	            				board[i][j] = combo.get(i*size+j).getValue();
	            	}
	            	Sudoku su = new Sudoku(board);
	                board = su.solve();
	                for(int i = 0; i < size; i++)
	                	for(int j = 0; j < size; j++)
	                		combo.get(i*size+j).setValue(board[i][j]);
	            }
	        });
	        
		 root.getChildren().addAll(pane,btn);
		 root.setAlignment(Pos.CENTER);		    
	}	
}
