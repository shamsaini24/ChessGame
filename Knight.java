/**
 * 
 */
package ca.bcit.comp2526.a2a;

/**
 * @author sham2
 *
 */
public class Knight extends Piece{

    /**
     * 
     */
    private static final long serialVersionUID = -8610017507692214564L;

    public Knight(boolean alive, int xcoord, int ycoord, int color) {
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
        int sumrow = startY - endY;
        int sumcol = startX - endX;
        if((sumrow == 2 && sumcol == 1)|| (sumrow == -2 && sumcol == -1) || (sumrow == 2 && sumcol == -1) ||(sumrow == 2 && sumcol == 1) ) {
            return true;
        } else if((sumrow == 1 && sumcol == 2) || (sumrow == -1 && sumcol == -2) || (sumrow == -1 && sumcol == 2) || (sumrow == 1 && sumcol == -2)) {
            return true;
        }
        return false;
        
    }

    @Override
    //The knight can jump over pieces, therefore the path will always be clear.
    boolean isPathClear(Space start, Space end) {
        return true;
    }

}
