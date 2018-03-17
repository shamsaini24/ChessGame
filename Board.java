/**
 * 
 */
package ca.bcit.comp2526.a2a;


import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * @author sham2
 *
 */
public class Board extends GridPane implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3794334970816881312L;
    /**
     * An array of spaces that will be used to manipulate each space and its instance variables.
     */
    static Space[][] spaces;
    /**
     * The instance variables required to make a move.
     */
    private Node eventsource;
    private Space firstClick = null;
    private Space secondClick = null;
    private Piece movePiece = null;
    private static int clickCount = 0;
    
    //Player 1
    Player player1 = new Player(1);
    //Player 2
    Player player2 = new Player(0);
    
    /**
     * Constructs a Board object, initializes the spaces and sets the pieces on the board.
     */
    public Board(){
        initalizeSpaces();
        initalizePieces();
    }
    /**
     * Returns a unique space given x and y coordinates.
     * @param x, the x coordinate
     * @param y, the y coordinate
     * @return A space object.
     */
    public static Space getSpace(int x, int y) {
        return spaces[x][y];
    }
    /**
     * Initializes the spaces both graphically and to the array. Each space is given an event handler and its
     * own row/column number. 
     */
    private void initalizeSpaces() {
        spaces = new Space[8][8];
        for (int column = 0; column < spaces.length; column++) {
            for(int row = 0; row < spaces.length ; row++) {
                Space space = new Space(column, row);
                space.setPrefSize(75,75);
                space.setPadding(Insets.EMPTY);
                space.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        
                        if(firstClick == null && clickCount == 0) {//Gathers the data related to the first click
                            eventsource = (Node) event.getSource();
                            getFirstClick(eventsource);
                            //Checks to see if there is piece on the first click, or if the piece belongs to the player1's color
                            if(movePiece == null || player1.turn && !isSameColor(player1, movePiece)) {
                               firstClick = null;
                               movePiece = null;
                               clickCount = 0;
                               System.out.println("wrong color white");
                               return;
                            //Checks to see if there is piece on the first click, or if the piece belongs to the player1's color
                            } else if(movePiece == null  || player2.turn && !isSameColor(player2, movePiece)) {
                                firstClick = null;
                                movePiece = null;
                                clickCount = 0;
                                System.out.println("Wrong color black");
                                return;
                            }
                          //Gathers the second click's information
                        } else if (secondClick == null && clickCount == 1) {
                            eventsource = (Node) event.getSource();
                            getSecondClick(eventsource);
                            //checks if a move is being made to a space that contains a same color piece.
                            if(secondClick.hasPiece() && secondClick.getCurrentpiece().color == movePiece.getColor()) {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                                System.out.println("Cannot take same color");
                            }
                            
                            //If a move was made to the same spot
                            if (firstClick == secondClick || !movePiece.isValid(secondClick, firstClick)) {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                            }
                            //If all conditions are met, it exectutes a move.
                            if (clickCount == 2 && firstClick != null && secondClick != null && movePiece.isPathClear(firstClick, secondClick)) {
                                Piece newpiece = firstClick.getCurrentpiece();
                                newpiece.setXcoord(secondClick.getX());
                                newpiece.setYcoord(secondClick.getY());
                                int newcolor = newpiece.getColor();
                                secondClick.setPiece(newpiece);
                                System.out.println(newcolor);
                                firstClick.removePiece();
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                                
                                //Toggles the moves on both players.
                                if(player1.isTurn()) {
                                    player1.setTurn(false);
                                    player2.setTurn(true);
                                } else {
                                    player2.setTurn(false);
                                    player1.setTurn(true);
                                }
                                
                            } else {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;


                            }
                        }
                                               
                    }
                    
                });
                spaces[column][row] = space;
                
        
                if((row % 2 == 1 && column % 2 == 1) || (row % 2 == 0 && column % 2 == 0)){
                    space.setStyle("-fx-background-color: grey; -fx-font: 57 arial; ");
                    add(space, column, row);

                } else {
                    space.setStyle("-fx-background-color: white; -fx-font: 57 arial;");
                    add(space, column, row);
                }
            }

        }
    }
    /**
     * Checks to see if the player and the piece share the same color.
     * @param player
     * @param piece
     * @return a boolean
     */
    private boolean isSameColor(Player player, Piece piece) {
        int playerColor = player.getColor();
        int pieceColor = piece.getColor();
        if(playerColor == pieceColor) {
            return true;
        }
        return false;
        
    }
    /**
     * Gathers the instance data for the first click
     * @param eventsource, the node used to obtain the specific space. 
     */
    private void getFirstClick(Node eventsource) {
        clickCount++;
        int x1 = GridPane.getColumnIndex(eventsource);
        int y1 = GridPane.getRowIndex(eventsource);
        System.out.println("X1: " + x1);
        System.out.println("Y1: " + y1);
        firstClick = getSpace(x1, y1);
        movePiece = firstClick.getCurrentpiece();
    }
    /**
     * Gathers the instance data for the second click
     * @param eventsource, the node used to obtain the specific space. 
     */
    private void getSecondClick(Node eventsource) {
        clickCount++;
        int x2 = GridPane.getColumnIndex(eventsource);
        int y2 = GridPane.getRowIndex(eventsource);
        System.out.println("X2: " + x2);
        System.out.println("Y2: " + y2);
        secondClick = getSpace(x2, y2);
    }
    /**
     * Initalizes all the pieces on the board, providing color and coordinates to all pieces.
     */
    public void initalizePieces() {
        int black = 0;
        int white = 1;
        for(int column = 0; column < 8; column++) {
            spaces[column][1].setPiece(new Pawn(true, column, 1, black));
        }
        spaces[0][0].setPiece(new Rook(true, 0, 0, black));
        spaces[1][0].setPiece(new Knight(true, 1, 0, black));
        spaces[2][0].setPiece(new Bishop(true, 2, 0, black));
        spaces[3][0].setPiece(new King(true, 3, 0, black));
        spaces[4][0].setPiece(new Queen(true, 4, 0, black));
        spaces[5][0].setPiece(new Bishop(true, 5, 0, black));
        spaces[6][0].setPiece(new Knight(true, 6, 0, black));
        spaces[7][0].setPiece(new Rook(true, 7, 0, black));
        
        spaces[0][7].setPiece(new Rook(true, 0, 7, white));
        spaces[1][7].setPiece(new Knight(true, 1, 7, white));
        spaces[2][7].setPiece(new Bishop(true, 2, 7, white));
        spaces[3][7].setPiece(new King(true, 3, 7, white));
        spaces[4][7].setPiece(new Queen(true, 4, 7, white));
        spaces[5][7].setPiece(new Bishop(true, 5, 7, white));
        spaces[6][7].setPiece(new Knight(true, 6, 7, white));
        spaces[7][7].setPiece(new Rook(true, 7, 7, white));
        
        for(int column = 0; column < 8; column++) {
            spaces[column][6].setPiece(new Pawn(true, column, 6, white));
        }
    }
    /**
     * Used to redraw a new, updated board when loading in an existing game.
     * @param newSpaces an array of updated spaces.
     */
    public void redrawBoard(Space[][] newSpaces) {
        spaces = new Space[8][8];
        for (int column = 0; column < spaces.length; column++) {
            for(int row = 0; row < spaces.length ; row++) {
                Space space = new Space(column, row);
                space.setPrefSize(75,75);
                space.setPadding(Insets.EMPTY);
                space.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        
                        if(firstClick == null && clickCount == 0) {
                            eventsource = (Node) event.getSource();
                            getFirstClick(eventsource);
                            if(movePiece == null || player1.turn && !isSameColor(player1, movePiece)) {
                               firstClick = null;
                               movePiece = null;
                               clickCount = 0;
                               System.out.println("wrong color white");
                               return;
                            } else if(movePiece == null  || player2.turn && !isSameColor(player2, movePiece)) {
                                firstClick = null;
                                movePiece = null;
                                clickCount = 0;
                                System.out.println("Wrong color black");
                                return;
                            }
  
                        } else if (secondClick == null && clickCount == 1) {
                            eventsource = (Node) event.getSource();
                            getSecondClick(eventsource);
                            if(secondClick.hasPiece() && secondClick.getCurrentpiece().color == movePiece.getColor()) {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                                System.out.println("Cannot take same color");
                            }
                            
                            //If a move was made to the same spot
                            if (firstClick == secondClick || !movePiece.isValid(secondClick, firstClick)) {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                            }
                            
                            if (clickCount == 2 && firstClick != null && secondClick != null && movePiece.isPathClear(firstClick, secondClick)) {
                                Piece newpiece = firstClick.getCurrentpiece();
                                int newcolor = newpiece.getColor();
                                secondClick.setPiece(newpiece);
                                System.out.println(newcolor);
                                firstClick.removePiece();
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                                if(player1.isTurn()) {
                                    player1.setTurn(false);
                                    player2.setTurn(true);
                                } else {
                                    player2.setTurn(false);
                                    player1.setTurn(true);
                                }
                                
                            } else {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;

                            }
                        }
                                               
                    }
                    
                });
                spaces[column][row] = space;
                
                if((row % 2 == 1 && column % 2 == 1) || (row % 2 == 0 && column % 2 == 0)){
                    space.setStyle("-fx-background-color: grey; -fx-font: 57 arial; ");
                    add(space, column, row);
                    
                } else {
                    space.setStyle("-fx-background-color: white; -fx-font: 57 arial;");
                    add(space, column, row);
                }
            }

        }
        
    }
    /**
     * Places the pieces on the board given the new, updated positions.
     * Used when loading
     * @param pieces, an array containing the saved pieces
     */
    public void redrawPieces(Piece[] pieces) {
        for(int i = 0;  i < pieces.length; i++) {
            if(pieces[i] ==  null) {
                continue;
            }
            int x = pieces[i].getXcoord();
            int y = pieces[i].getYcoord();
            Space space = getSpace(x, y);
            space.setPiece(pieces[i]);
        }
    }
    /**
     * @return the spaces
     */
    public static Space[][] getSpaces() {
        return spaces;
    }
    /**
     * @param spaces the spaces to set
     */
    public static void setSpaces(Space[][] spaces) {
        Board.spaces = spaces;
    }
    

}
