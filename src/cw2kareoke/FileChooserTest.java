/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Elton John Fernandes
 */
public class FileChooserTest extends Application {
    
    @Override
    
    public void start(Stage primaryStage) {
        /*
        File songFile = null;
        
        while(songFile == null)
        {
            songFile = getFile();
        }
        
        System.out.println(songFile.getAbsolutePath());
        
        StackPane root = new StackPane();
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static File getFile()
    {
        //File to be loaded and returned
        File SongFile = null;
        
        //sets and alert to get a file, closes program if asked by user
        Alert getFileAlert = new Alert(AlertType.INFORMATION,"Select A File to be loaded.\nSelect Close to Exit the Application", ButtonType.OK, ButtonType.CLOSE);
        getFileAlert.showAndWait();
        if(getFileAlert.getResult() == ButtonType.OK)
        {
            //USes a File choser to get only TXT Files
            FileChooser fSel = new FileChooser();
            fSel.setTitle("Open Song File");
            fSel.getExtensionFilters().add(new ExtensionFilter("TXT Files", "*.txt"));
            SongFile = fSel.showOpenDialog(null);
        }
        else
        {
            System.out.println("Program Terminated by User");
            System.exit(1);
        }
        return SongFile;
    }
    
}
