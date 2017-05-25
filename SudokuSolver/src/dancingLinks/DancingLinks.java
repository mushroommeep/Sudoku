package dancingLinks;

import java.util.ArrayList;
import java.util.List;

public class DancingLinks {
	private ColumnNode head;
	private List<ColumnNode> cols;
	
	public DancingLinks(){
		head = new ColumnNode(null,0);
		cols = new ArrayList<>();
	}
	
	public void addColumnNode(String name){
		ColumnNode top = new ColumnNode(name, 0);
		DancingNode tail = head.getL();
		tail.linkRight(top);
		top.linkRight(head);
		cols.add(top);
	}
	
	public void addRow(List<Boolean> row){
		DancingNode prev = null;
		for(int i = 0; i < row.size(); i++){
			if(row.get(i)){
				ColumnNode top = cols.get(i);
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
		for(ColumnNode col = head; col != head; col = (ColumnNode) col.getR()){
			if(col.getS() < s){
				curr = col;
				s = curr.getS();
			}
		}
		return curr;
	}
	
	public void search(List<DancingNode> answer){
		ColumnNode c = null;
		if(head.getR() == head){
			
			return;
		}
		else c = chooseColumn();
		c.cover();
		for(DancingNode row = c.getD(); row != c; row = row.getD()){
			answer.add(row);
			for(DancingNode col = row.getR(); col != row; col = col.getR()){
				col.getC().cover();
			}
			search(answer);
			row = answer.remove(answer.size()-1);
			c = row.getC();
			for(DancingNode col = row.getL(); col != row; col = col.getL()){
				col.getC().uncover();
			}
		}
		c.uncover();
		return; 
	}
	
	
}
