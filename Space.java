/**
 * 
 */
package ca.bcit.comp2526.a2a;

import javafx.scene.control.Button;

/**
 * @author sham2
 *
 */
public class Space extends Button{
    Piece currentpiece;
    int color;
    int x;
    int y;
    
    public Space(int x, int y) {
        this.x = x;
        this.y = y;
        currentpiece = null;
    }
    /**
     * 
     * @param newpiece, the piece to be placed and assigned on the space.
     * @param color, an int. 0 is black, 1 is white.
     */
    public void setPiece(Piece newpiece, int color) {
        if(color == 1) {//WHITE
            if(newpiece.getClass() == new Pawn(true, 0, 0).getClass()) {
                setText("\u2659");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Bishop(true, 0, 0).getClass()) {
                setText("\u2657");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new King(true, 0, 0).getClass()) {
                setText("\u2654");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Queen(true, 0, 0).getClass()) {
                setText("\u2655");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Rook(true, 0, 0).getClass()) {
                setText("\u2656");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Knight(true, 0, 0).getClass()) {
                setText("\u2658");
                currentpiece = newpiece; 
            }
        } else {//BLACK
            
            if(newpiece.getClass() == new Pawn(true, 0, 0).getClass()) {
                setText("\u265F");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Bishop(true, 0, 0).getClass()) {
                setText("\u265D");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new King(true, 0, 0).getClass()) {
                setText("\u265A");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Queen(true, 0, 0).getClass()) {
                setText("\u265B");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Rook(true, 0, 0).getClass()) {
                setText("\u265C");
                currentpiece = newpiece; 
            }
            if(newpiece.getClass() == new Knight(true, 0, 0).getClass()) {
                setText("\u265E");
                currentpiece = newpiece; 
            }
        }
       

    }
    /**
     * Checks if the space currently has a piece on it or not.
     * @return boolean, True if space has a piece, false if null.
     */
    public boolean hasPiece() {
        if(currentpiece == null) {
            return false;
        }
        return true;
        
    }
    
    public void removePiece() {
        currentpiece = null;
        setText("");
        
    }

    /**
     * @return the currentpiece
     */
    public Piece getCurrentpiece() {
        return currentpiece;
    }

    /**
     * @param currentpiece the currentpiece to set
     */
    public void setCurrentpiece(Piece currentpiece) {
        this.currentpiece = currentpiece;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
}
