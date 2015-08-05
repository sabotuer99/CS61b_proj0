package checkers61b;

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private int currentSide = 0; //fire team starts
    private Piece selectedPiece;
    private boolean hasMoved;

    public Board(boolean shouldBeEmpty){
    	if(!shouldBeEmpty){
    		pieces = new Piece[8][8];
    		boolean isFire = true;
    		//rows
    		for(int i = 0; i < 8; i += 1){
    			if(i >= 3)
    				isFire = false;
    			//columns
    			for(int j = 0; j < 8; j += 1){
    				if((i <= 2 || i >= 5) && (i + j)%2 == 0){
    					String type = "pawn";
    					if(i == 2 || i == 5)
    						type = "bomb";
    					if(i == 1 || i == 6)
    						type = "shield";
    					pieces[j][i] = new Piece(isFire, this, j, i, type);
    				}
    			}
    		}
    	}
    }
    
    public Piece pieceAt(int x, int y){
    	return pieces[x][y];
    }
    
    public boolean canSelect(int x, int y){
    	boolean result = false;
    	Piece piece = pieceAt(x,y);
    	
    	//if no piece is selected, or a piece is selected but hasn't moved
    	//then a select may be possible...
    	if( selectedPiece != null || 
    	   (selectedPiece != null && !hasMoved)){
    		
        	if(	piece != null && piece.side() == currentSide ){
        		result = true;
        	}
    	}
    	
    	return result;
    }
    
    public void select(int x, int y){
    	selectedPiece = pieceAt(x, y);
    }
    
    public void place(int x, int y){
    	
    }
    
    public Piece remove(int x, int y){
    	return null;
    }
    
    public boolean canEndTurn(){
    	return false;
    }
    
    public void endTurn(){
    	
    }
    
    public String winner(){
    	return null;
    }
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //if (pieces[i][j]) {
                //    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                //}
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                //pieces[(int) x][(int) y] = true;
            }            
            StdDrawPlus.show(100);
        }
    }
}
