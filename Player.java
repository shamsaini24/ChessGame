/**
 * 
 */
package ca.bcit.comp2526.a2a;

import java.io.Serializable;

/**
 * @author sham2
 *
 */
public class Player implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7367535552851153390L;
    public boolean turn;
    public int color;
    
    public Player(int color) {
        this.color = color;
        
        if(color == 1) {
            turn = true;
        } else {
            turn = false;
        }
    }

    /**
     * @return the turn
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
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
}
