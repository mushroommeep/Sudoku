package dancingLinks;

import java.util.ArrayList;
import java.util.List;

public class DancingLinks {
	private ColumnNode head;
	private List<ColumnNode> cols;
	
	public DancingLinks(){
		head = new ColumnNode("h",0);
		cols = new ArrayList<>();
	}
	
	public void addColumnNode(String name){
		ColumnNode top = new ColumnNode(name, 0);
		DancingNode tail = head.getL();
		tail.linkRight(top);
		top.linkRight(head);
		cols.add(top);
	}
	
	public void addRow(boolean[] row){
		DancingNode prev = null;
		for(int i = 0; i < row.length; i++){
			if(row[i]){
				ColumnNode top = cols.get(i);
				int size = top.getS() + 1;
				top.setS(size);
				DancingNode bottom = top.getU();
				DancingNode node = new DancingNode();
				node.setC(top);
				bottom.linkDown(node);
				node.linkDown(top);	
				if(prev != null){
					DancingNode first = prev.getR();
					prev.linkRight(node);
					node.linkRight(first);
				}
				prev = node;
			}
		}
	}
	
	public ColumnNode chooseColumn(){
		int s = Integer.MAX_VALUE;
		ColumnNode curr = null;
		for(ColumnNode col = (ColumnNode) head.getR(); col != head; col = (ColumnNode) col.getR()){
			if(col.getS() < s){
				curr = col;
				s = curr.getS();
			}
		}
		return curr;
	}
	
	public int search(List<DancingNode> answer, int[][] board){
		int results = 0;
		if(head.getR() == head){
			getResults(answer, board);
			results++;
		}
		else{ ColumnNode c = chooseColumn();
			if(c.getS() > 0){
				c.cover();
				for(DancingNode row = c.getD(); row != c; row = row.getD()){
					answer.add(row);
					for(DancingNode col = row.getR(); col != row; col = col.getR()){
						col.getC().cover();
					}
					results += search(answer, board);
					answer.remove(answer.size()-1);
					for(DancingNode col = row.getL(); col != row; col = col.getL()){
						col.getC().uncover();
					}
				}
				c.uncover();
			}
		}
		return results; 
	}
	
	private void getRowName(DancingNode row, int[][]board){
		int r = 0, c = 0, v = 0;
		String s = row.getC().getN();
		if(s.contains("inRow")){
			v = Integer.parseInt(Character.toString(s.charAt(s.indexOf("i")-1)));
			r = Integer.parseInt(Character.toString(s.charAt(s.indexOf("w")+1)));
		}else if(s.contains("inCol")){
			c = Integer.parseInt(Character.toString(s.charAt(s.indexOf("l")+1)));
		}
		for(DancingNode node = row.getR(); node != row; node = node.getR()){
			s = node.getC().getN();
			if(s.contains("inRow")){
				v = Integer.parseInt(Character.toString(s.charAt(s.indexOf("i")-1)));
				r = Integer.parseInt(Character.toString(s.charAt(s.indexOf("w")+1)));
			}else if(s.contains("inCol")){
				c = Integer.parseInt(Character.toString(s.charAt(s.indexOf("l")+1)));
			}
		}
		board[r][c]=v;
	}
	
	public void getResults(List<DancingNode> res, int[][] board){
		for(DancingNode node : res)
			getRowName(node,board);
	}
	
	 /*private void getRowName(DancingNode row) {
		    System.out.print(row.getC().getN()+ "___");
		    for(DancingNode node = row.getR(); node != row; node = node.getR()){
		    	System.out.print(node.getC().getN()+"____");
		    }
		    System.out.println();
	 }
	 
	public void getResults(List<DancingNode> result){
		for(DancingNode dn : result){
			getRowName(dn);
		}
	}*/
}
