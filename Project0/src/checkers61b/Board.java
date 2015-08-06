package checkers61b;

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private int currentSide = 0; //fire team starts
    private Piece selectedPiece;
    private int selectedPieceX, selectedPieceY;
    private boolean hasMoved = false;

    public Board(boolean shouldBeEmpty){
    	pieces = new Piece[8][8];
    	if(!shouldBeEmpty){
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
    	if (!inBounds(x, y))
    		return null;
    	return pieces[x][y];
    }
    
    public boolean canSelect(int x, int y){
    	//can only select even squares, in bounds
    	if (!inBounds(x, y))
    		return false;
    	
    	boolean result = false;
    	Piece piece = pieceAt(x,y);
    	
    	//if no piece is selected, or a piece is selected but hasn't moved
    	//then a select may be possible...
    	if( selectedPiece == null || (selectedPiece != null && !hasMoved)){
    		
        	if(	piece != null && piece.side() == currentSide ){
        		result = true;
        	}
    		
            if(selectedPiece != null){
    			//if the piece is a king, it can move any direction
    			//fire pieces can only make positive move, water negative move
    			if(selectedPiece.isKing()){
    				result = canMoveOrCapture(x, y, result, piece, 0);
    				result = canMoveOrCapture(x, y, result, piece, 1);
    			} else {
    				result = canMoveOrCapture(x, y, result, piece, currentSide);
    			}
            }
    	}
    	
    	if(selectedPiece != null && hasMoved){
			if(selectedPiece.isKing()){
				result = canCapture(x, y, result, piece, 0);
				result = canCapture(x, y, result, piece, 1);
			} else {
				result = canCapture(x, y, result, piece, currentSide);
			}
    	}
    	
    	return result;
    }

	private boolean inBounds(int x, int y) {
		if(x >= 8 || x < 0 || y >= 8 || y < 0 || (x+y)%2 != 0)
    		return false;
		return true;
	}

	private boolean canMoveOrCapture(int x, int y, boolean result, Piece piece, int side) {
		int direction = getDirection(side);
		
		//can select empty space diagonally
		if( piece == null && Math.abs(x - selectedPieceX) == 1 && y - selectedPieceY == 1 * direction ){
			result = true;
		}
		
		result = canCapture(x, y, result, piece, side);
		return result;
	}

	private int getDirection(int side) {
		int direction;
		if(side == 0)
			direction = 1;
		else
			direction = -1;
		return direction;
	}

	private boolean canCapture(int x, int y, boolean result, Piece piece, int side) {
		int direction = getDirection(side);
		
		if( piece == null && Math.abs(x - selectedPieceX) == 2 && y - selectedPieceY == 2 * direction ){
			Piece captureCandidate = pieceAt((x + selectedPieceX)/2, (y + selectedPieceY)/2);
			if(captureCandidate != null && captureCandidate.side() != currentSide){
				result = true;
			}
		}
		return result;
	}
    
    public void select(int x, int y){
    	if(selectedPiece == null){
        	selectedPiece = pieceAt(x, y);
        	selectedPieceX = x;
        	selectedPieceY = y;
    	} else {
    		selectedPiece.move(x, y);
        	selectedPieceX = x;
        	selectedPieceY = y;
        	hasMoved = true;
    	}

    }
    
    public void place(Piece p, int x, int y){
    	if(!inBounds(x,y))
    		return;
    	pieces[x][y] = p;
    }
    
    public Piece remove(int x, int y){
    	if(!inBounds(x,y)){
    		System.out.println("Can't remove, out of bounds");
    		return null;
    	}
    	
    	Piece p = pieceAt(x, y);
    	
    	if(p == null){
    		System.out.println("No piece to remove");
    		return null;
    	} 
    	else {
    		pieces[x][y] = null;
    		return p;
    	}
    		
    }
    
    public boolean canEndTurn(){
    	return hasMoved;
    }
    
    public void endTurn(){
    	//flip sides
    	currentSide = currentSide ^ 1;
    	
    	hasMoved = false;
    	selectedPiece.doneCapturing();
    	selectedPiece = null;
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
