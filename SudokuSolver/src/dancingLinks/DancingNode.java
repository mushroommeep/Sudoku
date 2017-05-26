package dancingLinks;

public class DancingNode {
	private DancingNode L, R, U, D;
	private ColumnNode C;
	
	public DancingNode(){
		L = R = U = D = this;
	}
	
	public void linkRight(DancingNode node){
		 this.R = node;
		 node.L = this;
	}
	
	public void linkDown(DancingNode node){
		this.D = node;
		node.U = this;
	}
	
	public void unlinkLR(){
		this.R.L = this.L;
		this.L.R = this.R;
	}
	
	public void relinkLR(){
		this.R.L = this.L.R = this;
	}
	
	public void unlinkUD(){
		this.D.U = this.U;
		this.U.D = this.D;
	}
	
	public void relinkUD(){
		this.U.D = this.D.U = this;
	}
	
	
	// Getters and setters
	
	public DancingNode getL() {
		return L;
	}

	
	public void setL(DancingNode l) {
		L = l;
	}

	public DancingNode getR() {
		return R;
	}

	
	public void setR(DancingNode r) {
		R = r;
	}

	public DancingNode getU() {
		return U;
	}

	public void setU(DancingNode u) {
		U = u;
	}

	public DancingNode getD() {
		return D;
	}

	public void setD(DancingNode d) {
		D = d;
	}

	public ColumnNode getC() {
		return C;
	}

	public void setC(ColumnNode c) {
		C = c;
	}
	
}
