/**
 * 
 */
package ca.bcit.comp2526.a2a;

/**
 * @author sham2
 *
 */
public class Bishop extends Piece {

    /**
     * 
     */
    private static final long serialVersionUID = 1877650859296785635L;
    public Bishop(boolean alive, int xcoord, int ycoord, int color) {
        super(alive, xcoord, ycoord, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    void move() {
        // TODO Auto-generated method stub
        
    }


    @Override
    /**
     * Checks if the space the piece is being moved to is valid.
     * @return a boolean, if the space is a valid spot it can move.
     */
    boolean isValid(Space newSpace, Space curSpace) {
        int startX = curSpace.getX();
        int startY = curSpace.getY();
        int endX = newSpace.getX();
        int endY = newSpace.getY();
        if(Math.abs(endX - startX) == Math.abs(endY - startY)) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * Checks the path from the start to end.
     * @return boolean, determines if path is clear or not.
     */
    boolean isPathClear(Space start, Space end) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        // determines if direction is NW and checks it
        if(endX < startX && endY < startY) {
            return checkNW(startX, startY, endX, endY);
        }
        // determines if direction is NE and checks it
        if(endX > startX && endY < startY) {
            return checkNE(startX, startY, endX, endY);
        }
        // determines if direction is SW and checks it
        if(endX < startX && endY > startY) {
            return checkSW(startX, startY, endX, endY);
        }
        // determines if direction is SE and checks it
        if(endX > startX && endY > startY) {
            return checkSE(startX, startY, endX, endY);
        }
        return true;
    }
    private boolean checkSW(int startX, int startY, int endX, int endY) {
        int y = endY -1;
        for(int x = endX + 1; (x <= startX - 1) && (y >= startY + 1); x++) {
            Space path = Board.getSpace(x, y);
            if(path.hasPiece()) {
                return false;
            }
            y--;
        }
        return true;
        
    }
    private boolean checkSE(int startX, int startY, int endX, int endY) {
        int y = endY -1;
        for(int x = endX - 1; (x >= startX + 1) && (y >= startY + 1 ); x--) {
            Space path = Board.getSpace(x, y);
            if(path.hasPiece()) {
                return false;
            }
            y--;
        }
        return true;
        
    }
    private boolean checkNW(int startX, int startY, int endX, int endY) {
        int y = endY +1;
        for(int x = endX + 1; (x <= startX - 1) && (y <= startY - 1); x++) {
            Space path = Board.getSpace(x, y);
            if(path.hasPiece()) {
                return false;
            }
            y++;
        }
        return true;
        
    }
    private boolean checkNE(int startX, int startY, int endX, int endY) {
        int y = endY + 1;
        for(int x = endX - 1; (x >= startX + 1) && (y <= startY -1); x--) {
            Space path = Board.getSpace(x, y);
            System.out.println("X CHECK=" + x);
            if(path.hasPiece()) {
                return false;
            }
            y++;
        }
        return true;
        
    }

}
