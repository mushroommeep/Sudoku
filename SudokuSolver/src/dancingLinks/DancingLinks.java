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
	
	public void search(List<DancingNode> answer){
		
		if(head.getR() == head){
			getResults(answer);
			return;
		}
		else{ ColumnNode c = chooseColumn();
			if(c.getS() > 0){
				c.cover();
				for(DancingNode row = c.getD(); row != c; row = row.getD()){
					answer.add(row);
					for(DancingNode col = row.getR(); col != row; col = col.getR()){
						col.getC().cover();
					}
					search(answer);
					answer.remove(answer.size()-1);
					for(DancingNode col = row.getL(); col != row; col = col.getL()){
						col.getC().uncover();
					}
				}
				c.uncover();
			}
		}
		return; 
	}
	
	 private void getRowName(DancingNode row) {
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
	}
}
