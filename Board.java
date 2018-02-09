/**
 * 
 */
package ca.bcit.comp2526.a2a;

import javafx.application.Application;
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

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primarystage) throws Exception {
        final int appWidth = 500;
        final int appHeight = 500;
        GridPane grid = new GridPane();
        int x = 0;
        int y = 0;
        
        for (int column = 0; column < 8; column++) {
            for(int row = 0; row < 8; row++) {
                Space space = new Space(column, row);
                space.setPrefSize(50,50);
                space.setOnAction(e -> processButtonPress(space));

                
                if((row % 2 == 1 && column % 2 == 1) || (row % 2 == 0 && column % 2 == 0)){
                    space.setStyle("-fx-background-color: black; ");
                    grid.add(space, column, row);

                } else {
                    space.setStyle("-fx-background-color: white; ");
                    grid.add(space, column, row);
                }
            }

        }
        
        for(int column = 0; column < 7; column++) {
            space.setPiece();
        }
        
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, appWidth, appHeight);

        primarystage.setTitle("Chess Board");
        primarystage.setScene(scene);
        primarystage.show();
    }
    Space getSpace(int x, int y) {
        return ;
        
    }
    
    public void processButtonPress(Button e) {
        e.setText("Button clicked");
    }
    public static void main(String[] args) {
        launch(args);
    }

}
