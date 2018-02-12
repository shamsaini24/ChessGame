/**
 * 
 */
package ca.bcit.comp2526.a2a;

/**
 * @author sham2
 *
 */
public abstract class Piece {
    boolean alive = true;
    int xcoord;
    int ycoord;
    int color;
    abstract void move();
    abstract void isValid();
    
    public Piece(boolean alive , int xcoord, int ycoord, int color) {
        this.alive = alive;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.color = color;
    }
    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }
    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    /**
     * @return the xcoord
     */
    public int getXcoord() {
        return xcoord;
    }
    /**
     * @param xcoord the xcoord to set
     */
    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }
    /**
     * @return the ycoord
     */
    public int getYcoord() {
        return ycoord;
    }
    /**
     * @param ycoord the ycoord to set
     */
    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
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
