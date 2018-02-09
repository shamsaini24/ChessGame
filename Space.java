/**
 * 
 */
package ca.bcit.comp2526.a2a;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author sham2
 *
 */
public class Space extends Button{
    public Label output = new Label("");
    Piece currentpiece;
    int color;
    int x;
    int y;
    
    public Space(int x, int y) {
        this.x = x;
        this.y = y;
        currentpiece = null;
    }
    
    void setPiece(Piece newpiece) {
        switch(newpiece) {
        case Pawn: 
            currentpiece = newpiece.
        }
        currentpiece = newpiece;
    }
    
    
}
