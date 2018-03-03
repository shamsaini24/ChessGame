/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.io.Serializable;

/**
 * @author sham2
 *
 */
public class Pawn extends Piece implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6934483937342497935L;
    boolean firstTurn = true;
    public Pawn(boolean alive, int xcoord, int ycoord, int color) {
        super(alive, xcoord, ycoord, color);
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
        if(color == 0) {//BLACK
            if(firstTurn) {
                if(endX == startX && endY == (startY + 1) || endY == (startY + 2) ) {
                    firstTurn = false;
                    return true;
                } else {
                    return false;
                }
                   
            } else {
                if(endX == startX && endY == (startY + 1)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if(color == 1) {//WHITE
            if(firstTurn) {
                if(endX == startX && endY == (startY - 1) || endY == (startY - 2) ) {
                    firstTurn = false;
                    return true;
                } else {
                    return false;
                }
                   
            } else {
                if(endX == startX && endY == (startY - 1)) {
                    return true;
                } else {
                    return false;
                }
                
            }
        }
        return false;
        
    }

    @Override
    boolean isPathClear(Space start, Space end) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();
        if(color == 1) {//WHITE
            if(firstTurn) {
                for(int y = endY + 1; y >= startY - 1 ; y--) {
                    Space path = Board.getSpace(endX, y);
                    if(path.hasPiece()) {
                        return false;
                    }
                }
            } else {
                return true;
            }
       } else {//BLACK
           if(firstTurn) {
               for(int y = endY - 1; y >= startY + 1 ; y++) {
                   Space path = Board.getSpace(endX, y);
                   if(path.hasPiece()) {
                       return false;
                   }
               }
           } else {
               return true;
           }
       }
        return true;
    }

   

}
    
