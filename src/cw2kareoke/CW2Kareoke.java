/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;


import static cw2kareoke.DrawerPane.newDrawerPane;
import static cw2kareoke.DrawerPane.newDrawerPane;
import static cw2kareoke.DrawerPane.newDrawerPane;
import static cw2kareoke.DrawerPane.newDrawerPane;
import static cw2kareoke.FileChooserTest.getFile;
import static cw2kareoke.LibraryPane.newLibraryPane;
import static cw2kareoke.SongParseTest.loadSongs;
import static cw2kareoke.VidPlayer.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author Elton John Fernandes
 */
public class CW2Kareoke extends Application {
    
    int ID = 0;
    LinkedList<Song> PlayList = new LinkedList<Song>(); 
    File songSrc = null;
    String songFolder = null;
    Song currSong = null;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        
        ///INITIALIZATION///
        
        //Loading the songs from user selection
        
        while(songSrc == null)
        {
            songSrc = getFile();
        }
        
        //gets the folder that has all the songs
        //since the getFile() function only allows .txt files, the below code will work
        songFolder = songSrc.getAbsolutePath().substring(0, songSrc.getAbsolutePath().length()-4) 
                + File.separator;
        
        //Loads the songs into a tree map
        TreeMap<String, Song> songTM = null;
          
        //tries to load the files, gives an error and shuts down the application if failed
        try
        {
        songTM = loadSongs(songSrc); 
        }
        catch(Exception ex)
        {
            Alert lSAlert = new Alert(AlertType.ERROR, "The provided file is corrupted or malformed"
                    + "\nPlease provide a valid File\nThe Application will now shut down",
            ButtonType.OK);
            lSAlert.showAndWait();
            System.exit(-1);
        }

        ///GUI Elements///
        
        //Master of all GUI elements
        StackPane root = new StackPane();
        
        
        //Main video Player
        MediaView mainPlayerView = newMainPlayerView();
        //gets the Media Player for later use
        MediaPlayer defaultPlayer = mainPlayerView.getMediaPlayer();

        
        //Controls for the main video player
        HBox vidCtrlBox = newVideoControlPane(defaultPlayer, PlayList);
        
        
        //A VBox to House all the Song Tabs and adding in the placeholder for when no songs are there
        VBox PLBox = new VBox(PLSongTab.getNoSongDispLbl());       
        
        //A ScrollPane to house the PlayListBox and limiting it's max width
        ScrollPane PLScrll = new ScrollPane(PLBox);
        PLScrll.setMaxWidth(547);
        
        //A borderpane as the base of other GUI elements
        BorderPane GUIBase = new BorderPane(mainPlayerView, null, null, vidCtrlBox, null);
        BorderPane.setMargin(mainPlayerView, new Insets(10, 10, 10, 10));
        
        //Getting the Library Pane
        VBox LibraryPane = newLibraryPane(songTM, ID, PLBox, PlayList);
        
        //getting a drawer pane
        StackPane DrawerPane = newDrawerPane(root, PLScrll, LibraryPane, 567, 567);
        DrawerPane.setPickOnBounds(false);
        
        
        root.getChildren().addAll(GUIBase, DrawerPane);
        
        
        ///Event Handlers///

        //no event handlers present
        
        
        ///Listeners///
                
        //Listerner for the width of the root element
        
        root.widthProperty().addListener((o, old, width) -> {
            
            //resizes the video player to fit, stops if the resize goes out of bounds
            
            int PlayerWidth = (int) (width.intValue() / 2.05);
            int PleyerHeight = (int) mainPlayerView.getFitHeight();
            
            if(root.getHeight() -50 > PleyerHeight )
            {
                mainPlayerView.setFitHeight(PlayerWidth);
                mainPlayerView.setFitWidth(width.intValue()); 
            }
            else
            {
                mainPlayerView.setFitHeight(PlayerWidth - 51);
                mainPlayerView.setFitWidth(width.intValue() - 51);
            }
            
            //just some tests
            /*
            System.out.println("///////////////////////////////////////////");
            System.out.println("Current Song:" + currSong);
            for (Song song : PlayList) {
            System.out.println(song.toString());
            }
            System.out.println("///////////////////////////////////////////");
            */
        });
        
        //MediaControl mc = new MediaControl();
        
        //Listener for the end of media
        
        
        mainPlayerView.getMediaPlayer().setOnEndOfMedia(() -> {
            
            //checks if playlist is empty, defaults back to default video player
            if(PlayList.isEmpty())
            {
                if(!( mainPlayerView.getMediaPlayer().equals(defaultPlayer) ))
                {
                    mainPlayerView.setMediaPlayer(defaultPlayer);
                }
                defaultPlayer.seek(Duration.ZERO);
                defaultPlayer.play();
                
            }
            // if playlist not empty, creates a media player for the next song in playlist
            else
            {
                //getting the current song
                currSong = PlayList.removeFirst();
                
                //making a file of the current sonfg and converting that to a URI 
                File currSFile = new File(songFolder+currSong.getFileName().getName());
                
                //making a media player for the current song
                MediaPlayer currMPlay = null;
                
                try
                {
                currMPlay = new MediaPlayer(new Media(currSFile.toURI().toString()));
                }
                catch(MediaException mEx)   //catches media exception, displays warning moves to next file
                {
                    String mExNotif = "The Video File for "+ currSong.getFileName() +" could not be found or played."
                            + "\nThe File may be missing or corrupted\n The Next Song will be Played";
                    
                    Alert mExAlert = new Alert(AlertType.WARNING, mExNotif, ButtonType.OK);
                    mExAlert.showAndWait();
                    
                    File f = new File("resources\\video_error.mp4");
                    currMPlay = new MediaPlayer(new Media(f.toURI().toString()));                  
                    currMPlay.play();
                    //System.out.println("here");  //super pro debugs
                }
                //System.out.println("but not here");   //super pro debugs part 2
                
                //switches back to the default player on the end of media
                currMPlay.setOnEndOfMedia(() -> {
                        
                        //removing the Song Tab from the GUI, Adding the NSD if no mo songs
                        PLBox.getChildren().remove(0);
                        if(PLBox.getChildren().isEmpty())
                            PLBox.getChildren().add(PLSongTab.getNoSongDispLbl());

                        //resets the current Song to null
                        currSong = null;
                        
                        //setting the media player back to default player, at the last second
                        //so that it can loop or play next song.
                        
                        try{
                        changeMediaPlayer(vidCtrlBox, defaultPlayer, PlayList);
                        }
                        catch(FileNotFoundException e)
                        {
                            System.out.println("File not Found");
                        }
                        
                        mainPlayerView.setMediaPlayer(defaultPlayer);
                        defaultPlayer.seek(Duration.seconds(10));
                        defaultPlayer.play();
                    
                });
                
                //swapping the mediaPlayer of the vidConBox to current media Player
                try{
                changeMediaPlayer(vidCtrlBox, currMPlay, PlayList);
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("File not Found");
                }
                mainPlayerView.setMediaPlayer(currMPlay);
                currMPlay.play();
                
            }
            
            System.out.println("Media End");
            
             
        });
        

        
        
        ///Final Showing///
        
        Scene scene = new Scene(root, 950, 750);
        
        primaryStage.setTitle("Super Ultra Awesome Kareoke");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
