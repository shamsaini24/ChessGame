/**
 * 
 */
package ca.bcit.comp2526.a2a;

import com.sun.glass.events.MouseEvent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author sham2
 *
 */
public class Board extends Application {
    final static Space[][] spaces = new Space[8][8];
    private Node eventsource;
    private Space firstClick = null;
    private Space secondClick = null;
    private static int clickCount = 0;
    GridPane grid = new GridPane();
    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primarystage) throws Exception {
        final int appWidth = 550;
        final int appHeight = 550;
        
        for (int column = 0; column < spaces.length; column++) {
            for(int row = 0; row < spaces.length ; row++) {
                Space space = new Space(column, row);
                space.setPrefSize(75,75);
                space.setPadding(Insets.EMPTY);
                space.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if(firstClick == null && clickCount == 0) {
                            clickCount++;
                            eventsource = (Node) event.getSource();
                            int x1 = GridPane.getColumnIndex(eventsource);
                            int y1 = GridPane.getRowIndex(eventsource);
                            System.out.println("X1" + x1);
                            System.out.println("Y1" + y1);
                            firstClick = getSpace(x1, y1);
                            if(!firstClick.hasPiece()) {
                                firstClick = null;
                                clickCount = 0;
                            }
                        } else if(secondClick == null && clickCount == 1) {
                            clickCount++;
                            eventsource = (Node) event.getSource();
                            int x2 = GridPane.getColumnIndex(eventsource);
                            int y2 = GridPane.getRowIndex(eventsource);
                            System.out.println("X2" + x2);
                            System.out.println("Y2" + y2);
                            secondClick = getSpace(x2, y2);
                            if(firstClick == secondClick) {
                                firstClick = null;
                                secondClick = null;
                                clickCount = 0;
                            }
                            
                            if(clickCount == 2 && firstClick != null && secondClick != null) {
                                Piece newpiece = firstClick.getCurrentpiece();
                                int newcolor = newpiece.getColor();
                                System.out.println("Eroor");
                                secondClick.setPiece(newpiece);
                                System.out.println(newcolor);
                                firstClick.removePiece();
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
                    grid.add(space, column, row);

                } else {
                    space.setStyle("-fx-background-color: white; -fx-font: 57 arial;");
                    grid.add(space, column, row);
                }
            }

        }
        initalizePieces();        
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, appWidth, appHeight);
        primarystage.setTitle("Chess Board");
        primarystage.setScene(scene);
        primarystage.show();
    }
    public Space getSpace(int x, int y) {
        return spaces[x][y];
    }
    
    public void processSpacePress(ActionEvent e) {
        if(secondClick == null && clickCount == 1) {
            eventsource = (Node) e.getSource();
            int x2 = GridPane.getColumnIndex(eventsource);
            int y2 = GridPane.getRowIndex(eventsource);
            secondClick = getSpace(x2, y2);     
            
            if(secondClick.hasPiece()) {
                Piece newpiece = firstClick.getCurrentpiece();
                int newcolor = newpiece.getColor();
                System.out.println("Eroor");
                secondClick.setPiece(newpiece);
            }
        }
        
        
        
    }
    
    private static void initalizePieces() {
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
        
        spaces[0][7].setPiece(new Rook(true, 0, 0, white));
        spaces[1][7].setPiece(new Knight(true, 1, 0, white));
        spaces[2][7].setPiece(new Bishop(true, 2, 0, white));
        spaces[3][7].setPiece(new King(true, 3, 0, white));
        spaces[4][7].setPiece(new Queen(true, 4, 0, white));
        spaces[5][7].setPiece(new Bishop(true, 5, 0, white));
        spaces[6][7].setPiece(new Knight(true, 6, 0, white));
        spaces[7][7].setPiece(new Rook(true, 7, 0, white));
        
        for(int column = 0; column < 8; column++) {
            spaces[column][6].setPiece(new Pawn(true, column, 6, white));
        }
    }
    public static void main(String[] args) {
        launch(args);
        }

}
