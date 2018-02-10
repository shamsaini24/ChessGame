/**
 * 
 */
package ca.bcit.comp2526.a2a;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author sham2
 *
 */
public class Board extends Application {
    final static Space[][] spaces = new Space[8][8];
    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primarystage) throws Exception {
        final int appWidth = 550;
        final int appHeight = 550;
        GridPane grid = new GridPane();
        
        for (int column = 0; column < spaces.length; column++) {
            for(int row = 0; row < spaces.length ; row++) {
                Space space = new Space(column, row);
                space.setPrefSize(75,75);
                space.setPadding(Insets.EMPTY);
                space.setOnAction(e -> processButtonPress(space));
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
    
    public void processButtonPress(Button e) {
        e.setText("Button clicked");
    }
    
    private static void initalizePieces() {
        for(int column = 0; column < 8; column++) {
            spaces[column][1].setPiece(new Pawn(true, column, 1), 0);
        }
        spaces[0][0].setPiece(new Rook(true, 0, 0), 0);
        spaces[1][0].setPiece(new Knight(true, 1, 0), 0);
        spaces[2][0].setPiece(new Bishop(true, 2, 0), 0);
        spaces[3][0].setPiece(new King(true, 3, 0), 0);
        spaces[4][0].setPiece(new Queen(true, 4, 0), 0);
        spaces[5][0].setPiece(new Bishop(true, 5, 0), 0);
        spaces[6][0].setPiece(new Knight(true, 6, 0), 0);
        spaces[7][0].setPiece(new Rook(true, 7, 0), 0);
        
        spaces[0][7].setPiece(new Rook(true, 0, 0), 1);
        spaces[1][7].setPiece(new Knight(true, 1, 0), 1);
        spaces[2][7].setPiece(new Bishop(true, 2, 0), 1);
        spaces[3][7].setPiece(new King(true, 3, 0), 1);
        spaces[4][7].setPiece(new Queen(true, 4, 0), 1);
        spaces[5][7].setPiece(new Bishop(true, 5, 0), 1);
        spaces[6][7].setPiece(new Knight(true, 6, 0), 1);
        spaces[7][7].setPiece(new Rook(true, 7, 0), 1);


        
        for(int column = 0; column < 8; column++) {
            spaces[column][6].setPiece(new Pawn(true, column, 6), 1);
        }
    }
    public static void main(String[] args) {
        launch(args);
        }

}
