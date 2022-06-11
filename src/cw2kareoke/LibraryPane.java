/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;

import static cw2kareoke.PLSongTab.getSongTab;
import static cw2kareoke.PLSongTab.getSongTab;
import static cw2kareoke.PLSongTab.getSongTab;
import static cw2kareoke.PLSongTab.getSongTab;
import static cw2kareoke.PLSongTab.getSongTab;
import static cw2kareoke.PLSongTab.getSongTab;
import static cw2kareoke.SongParseTest.loadSongs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Elton John Fernandes
 */
public class LibraryPane extends Application {
    
    private enum libState {SEARCH, MASTER};
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, Exception {
        /*
        File songFile = new File("resources/sample_song_data.txt");
        TreeMap<String, Song> songTM = loadSongs(songFile);
        
        int ID = 0;
        
        Scene scene = new Scene(newLibraryPane(songTM, ID, null, null), 300, 250);
        
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
    
    public static VBox newLibraryPane(TreeMap<String,Song> songTM, int ID, VBox PartnerPL, LinkedList<Song> PartnerList) throws FileNotFoundException
    {
        
        /*//Old Code for New Ideas
        //a enum for keeping track of whether the master or search is being shown
        //libState showing = libState.MASTER;
        
        //Search Pane 
        //VBox SrchLibVB = null;*/
        
        //Master Pane - used when search box is empty
        VBox MasterLibVB = new VBox();
        
        //int ID = 0; //ID for ref sake
        
        //goes through the tree map and adds songs to the MasterVBox
        for(Map.Entry<String,Song> entry : songTM.entrySet()) 
        {
            Song rcvSong = entry.getValue();
            MasterLibVB.getChildren().add(getSongTab(rcvSong, ID, PartnerPL, 2, PartnerList));  
            ID++;
        }
        
        //Creates a Scroll Pane that never scrolls horizontally
        ScrollPane LibSP = new ScrollPane(MasterLibVB);
        LibSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //LibSP.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        //Creating the Search Field
        TextField SearchTF = new TextField();
        SearchTF.setPromptText("Search Songs");
        SearchTF.setTooltip(new Tooltip("Enter a Song Name to Search"));
        SearchTF.setMaxSize(400, 10);
        
        VBox.setMargin(SearchTF, new Insets(20, 35, 20, 35));
        
        //Runs whenever a Key is released in the searchTF
        SearchTF.setOnKeyReleased(new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                
                String srcStr = SearchTF.getText();
                System.out.println("srcStr=" + srcStr);
                int ID1 = 0;
                
                //checks if the srchTF is empty, displays the main Lib
                if(srcStr.isEmpty())
                {
                    System.out.println(srcStr);
                    LibSP.setContent(MasterLibVB);
                    //SrchLibVB.getChildren().clear();
                    System.out.println("MASTER");
                }
                //if search TF is not empty, it displays the search rsult
                else
                {   
                    try {
                        LibSP.setContent(getSrcRsPane(songTM, srcStr, ID1, PartnerPL, PartnerList));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(LibraryPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    LibSP.setVvalue(0.0);
                    //System.out.println("SEARCH");     //pro debuggin
                    //Add Multithreading if time permits
                    //Update: Time did not permit T-T
                }
            
                
                //System.out.println("Key Detect");     //greater pro debugging hath known no man
            }
        });
        
        //Puts the Search Pane and the Lib Scroll Pane in a VBox and returns it
        VBox LibraryPane = new VBox(SearchTF, LibSP);
        
        //Setting a BG color to prevent Transparency issues
        LibraryPane.setBackground(new Background(new BackgroundFill(Color.web("#F4F4F4"), CornerRadii.EMPTY, Insets.EMPTY)));
        LibraryPane.setMinWidth(567);
        //Tells the VBox to always grow for the LibSP
        VBox.setVgrow(LibSP, Priority.ALWAYS);
        
        return LibraryPane;
    }
    
    
    public static GridPane endOSrcDispLbl() 
    {
        //Simply creates an EndofsearchLabel
        
        GridPane NoSongDispPane = new GridPane();
        
        Label NSDLbl = new Label("\n\n       End of Search Results.");
        NSDLbl.setTextFill(Color.web("#CAC9C7"));
        GridPane.setHalignment(NSDLbl, HPos.CENTER);
        
        NoSongDispPane.setVgap(8);
        NoSongDispPane.setHgap(15);
        
        NoSongDispPane.add(NSDLbl, 0, 0);
        
        
        //Creating Collumn Constraints for the GridPane
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setMinWidth(547);   //547 if you wanna be accurate
        cc1.setMaxWidth(547);

        NoSongDispPane.getColumnConstraints().add(cc1);  //This is added first so it applies to the first column
        
        NoSongDispPane.setId("EoSD");
        
        
        return NoSongDispPane;
    }
    
    
    public static VBox getSrcRsPane(TreeMap<String,Song> songTM, String srcStr, int ID, VBox PartnerPL, LinkedList PartnerList) throws FileNotFoundException
    {
        VBox SrchLibVB = new VBox();
                        
        //goes through all the songs, find those that match, adds them to VB then increments ID
        for(Map.Entry<String,Song> entry : songTM.entrySet()) 
        {
            Song rcvSong = entry.getValue();
            
            if(rcvSong.getTitle().contains(srcStr))
                SrchLibVB.getChildren().add(getSongTab(rcvSong, ID, PartnerPL, 2, PartnerList));
            
            ID++;

        }
        
        //Adds the end of search label to the end
        SrchLibVB.getChildren().add(endOSrcDispLbl());
        
        return SrchLibVB;
    }
    
    
}
