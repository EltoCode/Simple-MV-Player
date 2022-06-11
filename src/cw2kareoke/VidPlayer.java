/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Elton John Fernandes
 */
public class VidPlayer extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        
        
        /*
        
        File f = new File("resources\\video_default.mp4");
        URI u = f.toURI();
        Media karVid = new Media(u.toString());
        MediaPlayer mainPlayer = new MediaPlayer(karVid);
        MediaView mainPlayerView = new MediaView(mainPlayer);
        mainPlayer.setAutoPlay(true);
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                //mainPlayer.play();
            }
            
            
        });
        
        //Starting the Video Control Box//
        
        //HBox vidConBox = new HBox();
        
        //All Icons 
        ImageView PlayIco = new ImageView(new Image(new FileInputStream("resources/PlayIco.png")));
        ImageView PauseIco = new ImageView(new Image(new FileInputStream("resources/PauseIco.png")));
        ImageView NextIco = new ImageView(new Image(new FileInputStream("resources/NextIco.png")));
        ImageView MuteIco = new ImageView(new Image(new FileInputStream("resources/MuteIco.png")));
        ImageView UnmuteIco = new ImageView(new Image(new FileInputStream("resources/UnmuteIco.png")));
        
        //Pause Play Button
        
        
        Button ppBtn = new Button("", PauseIco);
        ppBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                //System.out.println("ye");     //ye indeed I say unto you, this was pro debuggin
                //pauses if playing, plays if paused
                if(mainPlayer.getStatus()==Status.PLAYING)
                {
                    mainPlayer.pause();
                    ppBtn.setGraphic(PlayIco);
                }
                else if(mainPlayer.getStatus()==Status.PAUSED)
                {
                    mainPlayer.play();
                    ppBtn.setGraphic(PauseIco);
                }
            }   
        });
        //Next Song Button
        Button NextBtn = new Button("", NextIco);
        NextBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                System.out.println("SKIP");
                
                //Insert Skip Code here.
            }   
        });
        //Mute Button
        Button MuteBtn = new Button("", UnmuteIco);
        MuteBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                if(mainPlayer.isMute())
                {
                    mainPlayer.setMute(false);
                    MuteBtn.setGraphic(UnmuteIco);
                }
                else
                {
                    mainPlayer.setMute(true);
                    MuteBtn.setGraphic(MuteIco);
                }
            }   
        });
        //Volume Slider
       Slider volumeSlider = new Slider(0, 1, 1);
       volumeSlider.setOrientation(Orientation.HORIZONTAL);
       volumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mainPlayer.setVolume(volumeSlider.getValue());
            }
        });
       
       //Control Seperator
       Separator conSeperator1 = new Separator(Orientation.VERTICAL);
       Separator conSeperator2 = new Separator(Orientation.VERTICAL);
       Separator conSeperator3 = new Separator(Orientation.VERTICAL);
       
       HBox vidConBox = new HBox(10);
       
       vidConBox.setPadding(new Insets(10, 10, 10, 10));
       vidConBox.setAlignment(Pos.CENTER);
       
       vidConBox.getChildren().addAll(ppBtn, conSeperator1, NextBtn, conSeperator2, new Text("\t\t\t\t\t\t\t"), 
               conSeperator3, MuteBtn, volumeSlider);
        
       GridPane playerPane = new GridPane();
       playerPane.add(mainPlayerView, 0, 0, 3, 3);
       playerPane.add(new Text(""), 0, 3);
       playerPane.add(vidConBox, 2, 3, 3, 3);
       /*playerPane.add(ppBtn, 0, 4);
       playerPane.add(NextBtn, 1, 4);
       playerPane.add(MuteBtn, 3, 4);
       playerPane.add(volumeSlider, 4, 4);*//*
       
       Scene scene = new Scene(playerPane, 1080, 1080);
       
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
    
    
    public static MediaView newMainPlayerView()
    {
        //creates a new Media View with the default media player
        File f = new File("resources\\video_default.mp4");
        URI u = f.toURI();
        Media karVid = new Media(u.toString());
        MediaPlayer mainPlayer = new MediaPlayer(karVid);
        MediaView mainPlayerView = new MediaView(mainPlayer);
        mainPlayer.setAutoPlay(true);
        mainPlayerView.setFitHeight(480);
        mainPlayerView.setFitWidth(854);
        mainPlayerView.setPreserveRatio(true);
        
        
        return mainPlayerView;
    }
    
    
    //creates a Control Panel for the given media player
    public static HBox newVideoControlPane(MediaPlayer mainPlayer, LinkedList<Song> PlayList) throws FileNotFoundException
    {
        
        //Starting the Video Control Box//
        
        //HBox vidConBox = new HBox();
        
        //All Icons 
        ImageView PlayIco = new ImageView(new Image(new FileInputStream("resources/PlayIco.png")));
        ImageView PauseIco = new ImageView(new Image(new FileInputStream("resources/PauseIco.png")));
        ImageView NextIco = new ImageView(new Image(new FileInputStream("resources/NextIco.png")));
        ImageView MuteIco = new ImageView(new Image(new FileInputStream("resources/MuteIco.png")));
        ImageView UnmuteIco = new ImageView(new Image(new FileInputStream("resources/UnmuteIco.png")));
        
        //giving MuteIco an ID to keep state
        MuteIco.setId("Mute");
        
        //Pause Play Button
        
        
        Button ppBtn = new Button("", PauseIco);
        ppBtn.setId("ppBtn");
        ppBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //pauses if playing, plays if paused
                if(mainPlayer.getStatus()==Status.PLAYING)
                {
                    mainPlayer.pause();
                    ppBtn.setGraphic(PlayIco);
                }
                else if(mainPlayer.getStatus()==Status.PAUSED)
                {
                    mainPlayer.play();
                    ppBtn.setGraphic(PauseIco);
                }
            }   
        });
        //Next Song Button
        Button NextBtn = new Button("", NextIco);
        NextBtn.setId("NextBtn");
        NextBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                //System.out.println("SKIP");       //never skip pro debugging
                
                //gets the default file
                File f = new File("resources\\video_default.mp4");
                URI u = f.toURI();
                
                //checks if the current file is the default song and play list is empty, displays the alert
                if(PlayList.isEmpty() && mainPlayer.getMedia().getSource().contentEquals(u.toString()))
                {
                    Alert noMoSong = new Alert(AlertType.INFORMATION,"No more Songs in PlayList.",ButtonType.OK);
                    noMoSong.show();
                }
                //else skips to the end of video
                else
                {
                    //seeks to the end and plays
                    mainPlayer.seek(mainPlayer.getTotalDuration());
                    mainPlayer.play();
                    ppBtn.setGraphic(PauseIco);
                }
                
            }   
        });
        //Mute Button
        Button MuteBtn = new Button("", UnmuteIco);
        MuteBtn.setId("MuteBtn");
        MuteBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                //mutes if unmute, unmutes if mute
                if(mainPlayer.isMute())
                {
                    mainPlayer.setMute(false);
                    MuteBtn.setGraphic(UnmuteIco);
                }
                else
                {
                    mainPlayer.setMute(true);
                    MuteBtn.setGraphic(MuteIco);
                }
            }   
        });
        //Volume Slider
       Slider volumeSlider = new Slider(0, 1, 1);
       volumeSlider.setId("volumeSlider");
       volumeSlider.setOrientation(Orientation.HORIZONTAL);
       volumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mainPlayer.setVolume(volumeSlider.getValue());
            }
        });
       
       //GUI Elements follow
       
       //Control Seperator
       Separator conSeperator1 = new Separator(Orientation.VERTICAL);
       Separator conSeperator2 = new Separator(Orientation.VERTICAL);
       Separator conSeperator3 = new Separator(Orientation.VERTICAL);
       
       HBox vidConBox = new HBox(10);
       
       vidConBox.setPadding(new Insets(10, 10, 10, 10));
       vidConBox.setAlignment(Pos.CENTER);
       
       vidConBox.getChildren().addAll(ppBtn, conSeperator1, NextBtn, conSeperator2, new Text("\t\t\t\t\t\t\t"), 
               conSeperator3, MuteBtn, volumeSlider);
       
       return vidConBox; 
       
    }
    
    //assigns a given control panel for a new media player
    public static void changeMediaPlayer(HBox vidConBox, MediaPlayer mainPlayer, LinkedList<Song> PlayList) throws FileNotFoundException
    {
        //Icon Intit
        //All Icons 
        ImageView PlayIco = new ImageView(new Image(new FileInputStream("resources/PlayIco.png")));
        ImageView PauseIco = new ImageView(new Image(new FileInputStream("resources/PauseIco.png")));
        ImageView NextIco = new ImageView(new Image(new FileInputStream("resources/NextIco.png")));
        ImageView MuteIco = new ImageView(new Image(new FileInputStream("resources/MuteIco.png")));
        ImageView UnmuteIco = new ImageView(new Image(new FileInputStream("resources/UnmuteIco.png")));
        
        //giving MuteIco an ID to keep state
        MuteIco.setId("Mute");
        
        //Switching the Add Button
        Button ppBtn = (Button) vidConBox.getChildren().get(0);
        ppBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //pauses if play, plays if pauses
                if(mainPlayer.getStatus()==Status.PLAYING)
                {
                    mainPlayer.pause();
                    ppBtn.setGraphic(PlayIco);
                }
                else if(mainPlayer.getStatus()==Status.PAUSED)
                {
                    mainPlayer.play();
                    ppBtn.setGraphic(PauseIco);
                }
            }   
        });
        
       //Getting and Switching the Next button
       Button NextBtn = (Button) vidConBox.getChildren().get(2);
       NextBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                //System.out.println("SKIP");   //never skip pro deubg day
                
                //gets the default file
                File f = new File("resources\\video_default.mp4");
                URI u = f.toURI();
                
                //checks if the current file is the default song and play list is empty, displays the alert
                if(PlayList.isEmpty() && mainPlayer.getMedia().getSource().contentEquals(u.toString()))
                {
                    Alert noMoSong = new Alert(AlertType.INFORMATION,"No more Songs in PlayList.",ButtonType.OK);
                    noMoSong.show();
                }
                //elses skips to the end of video
                else
                {
                    mainPlayer.seek(mainPlayer.getTotalDuration());
                    mainPlayer.play();
                    ppBtn.setGraphic(PauseIco);
                }
                
            }   
        });
        
       //Getting and Switching the Mute button
       Button MuteBtn = (Button) vidConBox.getChildren().get(6);
       MuteBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                //mutes if unmute, unmutes if mute
                if(mainPlayer.isMute())
                {
                    mainPlayer.setMute(false);
                    MuteBtn.setGraphic(UnmuteIco);
                }
                else
                {
                    mainPlayer.setMute(true);
                    MuteBtn.setGraphic(MuteIco);
                }
            }   
        });
       
       //checks if old player was muted, mutes new player
       if("Mute".equals(MuteBtn.getGraphic().getId()))
           mainPlayer.setMute(true);
       
       //Getting and Switching the Volume Slider
       Slider volumeSlider = (Slider) vidConBox.getChildren().get(7);
       volumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mainPlayer.setVolume(volumeSlider.getValue());
            }
        });
       
       //sets the new player volume to current slider value
       mainPlayer.setVolume(volumeSlider.getValue());
       
    }
}
