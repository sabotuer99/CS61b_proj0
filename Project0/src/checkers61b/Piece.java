package checkers61b;

public class Piece {

	private boolean isFire;
	private boolean isKing = false;
	private String type;
	private boolean hasCaptured = false;
	private int x; 
	private int y;
	private Board board;
	
	public Piece(boolean isFire, Board b, int x, int y, String type){
		this.isFire = isFire;
		this.type = type;
		this.x = x;
		this.y = y;
		this.board = b;
	}
	
	public boolean isFire(){
		return isFire;
	}
	
	public int side(){
		return isFire() ? 0 : 1;
	}
	
	public boolean isKing(){
		return this.isKing;
	}
	
	public boolean isBomb(){
		return "bomb".equals(type);
	}
	
	public boolean isShield(){
		return "shield".equals(type);
	}
	
	public void move(int x, int y){
		//simple move, no capture applicable
		if(Math.abs(this.x - x) == 1 && Math.abs(this.y - y) == 1){
			movePiece(x, y);
		} else {		
			board.remove((x + this.x)/2, (y + this.y)/2);
			movePiece(x, y);
			hasCaptured = true;
		}
		
		if((side() == 0 && y == 7) || (side() == 1 && y == 0))
			this.isKing = true;
	}
	
	private void movePiece(int x, int y){
		board.remove(this.x, this.y);
		this.x = x;
		this.y = y;
		board.place(this, this.x, this.y);
	}
	
	public boolean hasCaptured(){
		return hasCaptured;
	}
	
	public void doneCapturing(){
		hasCaptured = false;
	}
	
}
