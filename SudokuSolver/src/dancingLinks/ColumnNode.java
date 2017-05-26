package dancingLinks;

public class ColumnNode extends DancingNode{
	private int S;
	private String N;
	
	public ColumnNode(String n, int size){
		super();
		S = size;
		N = n;
		this.setC(this);
	}
	
	public void cover(){
		unlinkLR();
		for(DancingNode row = this.getD(); row != this; row = row.getD())
			for(DancingNode colRight = row.getR(); colRight != row; colRight = colRight.getR()){
				colRight.unlinkUD();
				colRight.getC().S--;
			}
	}
	
	public void uncover(){
		for (DancingNode row = this.getU(); row != this; row = row.getU())
			for(DancingNode colLeft = row.getL(); colLeft != row; colLeft = colLeft.getL()){
				colLeft.getC().S++;
				relinkUD();
			}
		relinkLR();
	}
	
	// Getters and setters
	
	public int getS() {
		return S;
	}

	public void setS(int s) {
		S = s;
	}

	public String getN() {
		return N;
	}

	public void setN(String n) {
		N = n;
	}
}
