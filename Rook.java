package ca.bcit.comp2526.a2a;

/**
 * @author sham2
 *
 */
public class Rook extends Piece{

    /**
     * 
     */
    private static final long serialVersionUID = -2689782406231182261L;
    public Rook(boolean alive, int xcoord, int ycoord, int color) {
        super(alive, xcoord, ycoord, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    void move() {
        // TODO Auto-generated method stub
        
    }

    @Override
    boolean isValid(Space newSpace, Space curSpace) {
        int startX = curSpace.getX();
        int startY = curSpace.getY();
        int endX = newSpace.getX();
        int endY = newSpace.getY();
        if(endX == startX || endY == startY) {
            return true;
        }
        return false;
    }


    @Override
    boolean isPathClear(Space start, Space end) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        if(endX == startX) {
            return checkY(startY, endY, endX);
            
        } else if(endY == startY) {
            return checkX(startX, endX, endY);
        }
        return true;
    }
    
    private boolean checkX(int startX, int endX, int endY) {
        if(startX < endX) {
            for(int i = endX - 1; i >= startX + 1; i--) {
                System.out.println("X CHECK=" + i);
                Space path = Board.getSpace(i, endY);
                if(path.hasPiece()) {
                    return false;
                }
            }
        } else{
            for(int i = endX + 1; i <= startX - 1; i++) {
                Space path = Board.getSpace(i, endY);
                if(path.hasPiece()) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkY(int startY, int endY, int endX) {
        if(startY < endY) {
            for(int i = endY - 1; i >= startY + 1; i--) {
                Space path = Board.getSpace(endX, i);
                if(path.hasPiece()) {
                    return false;
                }
            }
        } else{
            for(int i = endY + 1; i <= startY -1; i++) {
                Space path = Board.getSpace(endX, i);
                if(path.hasPiece()) {
                    return false;
                }
            }
        }
        return true;
        
    }

}
