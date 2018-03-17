package ca.bcit.comp2526.a2a;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
/**
 * @author sham2
 *
 */
public class Game extends Application {
    Group root = new Group();
    Board newGame = new Board();    
    Board loadGame = null;
    
    /*
     * Initalizes all streams for serialization.
     */
    FileInputStream f2;
    ObjectInputStream in;
    FileInputStream f1;
    ObjectInputStream in1;
    FileInputStream f3;
    ObjectInputStream in3;
    
    /*
     * Arrays used to save the current states of the spaces and
     * their pieces.
     */
    private Space spaces[][];
    private Piece pieces[];

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primarystage) throws Exception {
        final int appWidth = 760;
        final int appHeight = 650;
        pieces = new Piece[32];
        
      //Top Menu Bar
        MenuBar menuBar = new MenuBar();
     
        Menu menu1 = new Menu("Menu");
     
        MenuItem menuItemA = new MenuItem("New");
        menuItemA.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override public void handle(ActionEvent e) {
                root.getChildren().add(newGame);
            }
        });
        MenuItem menuItemB = new MenuItem("Load");
        menuItemB.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override public void handle(ActionEvent e) {
              //Read them back
                try {
                f3 = new FileInputStream("pieces.ser");
                in3 = new ObjectInputStream(f3);
                f1 = new FileInputStream("spaces.ser");
                in1 = new ObjectInputStream(f1);
                f2 = new FileInputStream("Board.ser");
                in = new ObjectInputStream(f2);
                loadGame = (Board)in.readObject();
                spaces = (Space[][]) in1.readObject();
                pieces = (Piece[]) in3.readObject();
                in.close();
                f2.close();
                in1.close();
                f1.close();
                in3.close();
                f3.close();
                }catch(IOException err){
                        err.printStackTrace();
                }catch(ClassNotFoundException err){
                        err.printStackTrace();
                }
                if(loadGame != null ) {
                    System.out.println("game loaded");
                    loadGame.redrawBoard(spaces);
                    loadGame.redrawPieces(pieces);
                    loadGame.setLayoutY(25);//shifts the loaded game down below the menu bar.
                    root.getChildren().remove(newGame);
                    root.getChildren().add(loadGame);

                }

            }
        });
     
        MenuItem menuItemC = new MenuItem("Save");
        menuItemC.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent e) {
                spaces = Board.getSpaces();
                int pieceCounter = 0;
                
                //A for loop that gets the piece information from all the spaces.
                for (int column = 0; column < spaces.length; column++) {
                    for(int row = 0; row < spaces.length ; row++) {
                        if(spaces[column][row].hasPiece()) {
                           Space pieceSpace = Board.getSpace(column, row);
                           Piece piece = pieceSpace.getCurrentpiece();
                           pieces[pieceCounter] = piece;
                           pieceCounter++;
                        }
                    }
                }
                
                try{
                    FileOutputStream f3 = new FileOutputStream("pieces.ser");
                    ObjectOutput out3 = new ObjectOutputStream(f3);
                    FileOutputStream f2 = new FileOutputStream("spaces.ser");
                    ObjectOutput out2 = new ObjectOutputStream(f2);
                    FileOutputStream f = new FileOutputStream("Board.ser");
                    ObjectOutput out = new ObjectOutputStream(f);
                    
                    out.writeObject(newGame);
                    out2.writeObject(spaces);
                    out3.writeObject(pieces);
                    out.flush();
                    out.close();
                    out2.flush();
                    out2.close();
                    out3.flush();
                    out3.close();
                    f.close();
                    f2.close();
                    f3.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
     
        menu1.getItems().add(menuItemA);
        menu1.getItems().add(menuItemB);
        menu1.getItems().add(menuItemC);
        menuBar.getMenus().add(menu1);
        newGame.setLayoutY(25);
        menuBar.prefWidthProperty().bind(primarystage.widthProperty());
        root.getChildren().add(menuBar);
        Scene scene = new Scene(root, appWidth, appHeight);
        primarystage.setTitle("Chess Board");
        primarystage.setScene(scene);
        primarystage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }

}