/**
 * 
 */
package ca.bcit.comp2526.a2a;

/**
 * @author sham2
 *
 */
public class King extends Piece{

    /**
     * 
     */
    private static final long serialVersionUID = -2346578235749597566L;

    public King(boolean alive, int xcoord, int ycoord, int color) {
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
        if(Math.abs(endX - startX) < 2 && Math.abs(endY - startY) < 2) {
            return true;
        }
        return false;
    }

    @Override
    /**
     * The king can only move one space at a time, therefore no path checking required.
     * @return returns true
     */
    boolean isPathClear(Space start, Space end) {
        return true;
    }

}
