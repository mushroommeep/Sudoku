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
		this.unlinkLR();
		for(DancingNode row = this.getD(); row != this; row = row.getD())
			for(DancingNode colRight = row.getR(); colRight != row; colRight = colRight.getR()){
				colRight.unlinkUD();
				int nbN = colRight.getC().getS() -1;
				colRight.getC().setS(nbN);
			}
	}
	
	public void uncover(){
		for (DancingNode row = this.getU(); row != this; row = row.getU())
			for(DancingNode colLeft = row.getL(); colLeft != row; colLeft = colLeft.getL()){
				int nbN = colLeft.getC().getS() +1;
				colLeft.getC().setS(nbN);
				colLeft.relinkUD();
			}
		this.relinkLR();
	}
	
	public int getS() {
		return S;
	}

	public void setS(int s) {
		S = s;
	}

	public String getN() {
		return N;
	}
}
