package dancingLinks;

public class DancingNode {
	private DancingNode L, R, U, D;
	private ColumnNode C;
	/**
	 * @return the l
	 */
	public DancingNode getL() {
		return L;
	}

	/**
	 * @param l the l to set
	 */
	public void setL(DancingNode l) {
		L = l;
	}

	/**
	 * @return the r
	 */
	public DancingNode getR() {
		return R;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(DancingNode r) {
		R = r;
	}

	/**
	 * @return the u
	 */
	public DancingNode getU() {
		return U;
	}

	/**
	 * @param u the u to set
	 */
	public void setU(DancingNode u) {
		U = u;
	}

	/**
	 * @return the d
	 */
	public DancingNode getD() {
		return D;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(DancingNode d) {
		D = d;
	}

	/**
	 * @return the c
	 */
	public ColumnNode getC() {
		return C;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(ColumnNode c) {
		C = c;
	}
	
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
}
