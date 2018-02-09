/**
 * 
 */
package ca.bcit.comp2526.a2a;

/**
 * @author sham2
 *
 */
public abstract class Piece {
    String name;
    abstract void move();
    abstract void isValid();
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
   

}
