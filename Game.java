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
    static Board board = new Board();    
    Board newGame = null;
    FileInputStream f2 = null;
    ObjectInputStream in = null;

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primarystage) throws Exception {
        final int appWidth = 760;
        final int appHeight = 650;
      //Top Menu Bar
        MenuBar menuBar = new MenuBar();
     
        Menu menu1 = new Menu("Menu");
     
        MenuItem menuItemA = new MenuItem("New");
        menuItemA.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override public void handle(ActionEvent e) {
                root.getChildren().add(board);
            }
        });
      //FIX LOADING NEW GAME
        MenuItem menuItemB = new MenuItem("Load");
        menuItemB.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override public void handle(ActionEvent e) {
              //Read them back
                try {
                f2 = new FileInputStream("Board.ser");
                in = new ObjectInputStream(f2);
                newGame = (Board)in.readObject();
                in.close();
                f2.close();

                }catch(IOException err){
                        err.printStackTrace();
                }catch(ClassNotFoundException err){
                        err.printStackTrace();
                }
                if(newGame != null ) {
                    root.getChildren().add(newGame);
                    System.out.println("game loaded");

                }

            }
        });
     
        MenuItem menuItemC = new MenuItem("Save");
        menuItemC.setOnAction(new EventHandler<ActionEvent>() {
         
            @Override public void handle(ActionEvent e) {
                try{
                    FileOutputStream f = new FileOutputStream("Board.ser");
                    ObjectOutput out = new ObjectOutputStream(f);
                    
                    out.writeObject(board);
                    out.flush();
                    out.close();
                    f.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
        });
     
        menu1.getItems().add(menuItemA);
        menu1.getItems().add(menuItemB);
        menu1.getItems().add(menuItemC);
        menuBar.getMenus().add(menu1);
        board.setLayoutY(25);
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