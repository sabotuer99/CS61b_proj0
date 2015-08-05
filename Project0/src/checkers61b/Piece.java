package checkers61b;

public class Piece {

	private boolean isFire;
	private boolean isKing = false;
	private String type;
	
	public Piece(boolean isFire, Board b, int x, int y, String type){
		this.isFire = isFire;
		this.type = type;
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
	
	public boolean isShield(){
		return "shield".equals(type);
	}
	
	public void move(int x, int y){
		
	}
	
	public boolean hasCaptured(){
		return false;
	}
	
	public void doneCapturing(){
		
	}
	
}
