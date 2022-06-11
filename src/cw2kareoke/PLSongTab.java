/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Elton John Fernandes
 */



public class PLSongTab extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        /*
        
        GridPane SongPane = new GridPane();
        
        Label SongTitleLbl = new Label("The Yay Song");
        SongTitleLbl.setFont(new Font("Arial", 22));
        
        Label SongPlayTimeLbl = new Label("4:12");
        SongPlayTimeLbl.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 12));
               
        Label SongArtistLbl = new Label("YayGuys");
        SongArtistLbl.setFont(Font.font("Arial Narrow", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18));
        
        Button trashSongBtn = new Button(null, new ImageView(
                new Image(new FileInputStream("resources/trashbin.png"))));
        
        Separator tabSprtr = new Separator(Orientation.HORIZONTAL);
        
        SongPane.add(SongTitleLbl, 0, 0);
        SongPane.add(SongPlayTimeLbl, 0, 1);
        SongPane.add(SongArtistLbl, 1, 1);
        SongPane.add(trashSongBtn, 3, 0, 1, 2);
        SongPane.add(tabSprtr, 0, 2, 4, 1);
        
        SongPane.setVgap(8);
        SongPane.setHgap(15);
        
        
        //SongPane.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(SongPane, 300, 250);
        
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
    
    //depreciated
    public static GridPane getSongTab(String songTitle, String songPlayTime, String songArtist, int ID, VBox Owner) throws FileNotFoundException
    {
        GridPane SongPane = new GridPane();
        
        
        //Declaring a Label for Song Title
        Label SongTitleLbl = new Label(songTitle);
        SongTitleLbl.setFont(new Font("Arial", 22));
        
        //Declaring a Label for Song Play Time
        Label SongPlayTimeLbl = new Label(songPlayTime);
        SongPlayTimeLbl.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 12));
         
        //Declaring a Label for Song Artist 
        Label SongArtistLbl = new Label(songArtist);
        SongArtistLbl.setFont(Font.font("Arial Narrow", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18));
        
        //Declaring a Button to thrash for Song
        Button trashSongBtn = new Button(null, new ImageView(
                new Image(new FileInputStream("resources/trashbin.png"))));
        //setting an ID for the button 
        trashSongBtn.setId(ID + "-SongPane.button");
        
        //setting an ID for the GridPane
        String SongPaneID = ID + "-SongPane";
        SongPane.setId(SongPaneID);
        
        Separator tabSprtr = new Separator(Orientation.HORIZONTAL);
        
        SongPane.add(SongTitleLbl, 0, 0);
        SongPane.add(SongPlayTimeLbl, 0, 1);
        SongPane.add(SongArtistLbl, 1, 1);
        SongPane.add(trashSongBtn, 3, 0, 1, 2);
        SongPane.add(tabSprtr, 0, 2, 4, 1);
        
        SongPane.setVgap(8);
        SongPane.setHgap(15);
        
        
        //Creating Collumn Constraints for the GridPane
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setMinWidth(300);
        cc1.setMaxWidth(300);
        
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setMinWidth(150);
        cc2.setMaxWidth(150);
        
        //Adding the Collumn Constraints to the GridPane, They affect the column in the way they are added
        SongPane.getColumnConstraints().add(cc1);  //This is added first so it applies to the first column
        SongPane.getColumnConstraints().add(cc2);  //This is added second so it applies to the second column
        
        //action event for trash button
        trashSongBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //Owner.getChildren().add(PLSongTab.getNoSongDispLbl());
                
                System.out.println("del works");        //pro debugging
                //SongPane.getChildren().clear();    //old command, clears entirety
                Owner.getChildren().remove(Owner.lookup("#"+SongPaneID));
                
               
                //checks if no songs, adds no song disp
                if(Owner.getChildren().isEmpty())
                {
                    Owner.getChildren().add(PLSongTab.getNoSongDispLbl());

                }
            }
        });  
        
        
        return SongPane;
    }
    
    //creates a Label for the no songs
    public static GridPane getNoSongDispLbl() 
    {
        GridPane NoSongDispPane = new GridPane();
        
        Label NSDLbl = new Label("\n\nNo Songs yet...\n\tHow about adding some from the Library?");
        NSDLbl.setTextFill(Color.web("#CAC9C7"));
        GridPane.setHalignment(NSDLbl, HPos.CENTER);
        
        NoSongDispPane.setVgap(8);
        NoSongDispPane.setHgap(15);
        //NoSongDispPane.setPadding(new Insets(10,10,10,100));
        
        //NoSongDispPane.setAlignment(Pos.CENTER);
        
        NoSongDispPane.add(NSDLbl, 0, 0);
        
        
        //Creating Collumn Constraints for the GridPane
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setMinWidth(450);   //547 if you wanna be accurate
        cc1.setMaxWidth(450);
        /*
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setMinWidth(450);
        cc2.setMaxWidth(450);
        */
        //Adding the Collumn Constraints to the GridPane, They affect the column in the way they are added
        NoSongDispPane.getColumnConstraints().add(cc1);  //This is added first so it applies to the first column
        //NoSongDispPane.getColumnConstraints().add(cc2);  //This is added second so it applies to the second column
        
        NoSongDispPane.setId("NSD");
        
        
        return NoSongDispPane;
    }
    
    
    public static GridPane getSongTab(Song fromSong, int ID, VBox Owner, int ButtonType, LinkedList PlayList) throws FileNotFoundException
    {
        //getting the data of the song object
        String songTitle = fromSong.getTitle();
        String songPlayTime = fromSong.getPlayTime().toString();
        String songArtist = fromSong.getArtist();
                
        GridPane SongPane = new GridPane();
        
        
        //Declaring a Label for Song Title
        Label SongTitleLbl = new Label(songTitle);
        SongTitleLbl.setFont(new Font("Arial", 22));
        
        //Declaring a Label for Song Play Time
        Label SongPlayTimeLbl = new Label(songPlayTime);
        SongPlayTimeLbl.setFont(Font.font("Arial Rounded MT Bold", FontWeight.EXTRA_BOLD, 12));
         
        //Declaring a Label for Song Artist 
        Label SongArtistLbl = new Label(songArtist);
        SongArtistLbl.setFont(Font.font("Arial Narrow", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 18));
        
        
        //setting an ID for the GridPane
        String SongPaneID = ID + "-SongPane";
        SongPane.setId(SongPaneID);
        
        Separator tabSprtr = new Separator(Orientation.HORIZONTAL);
        
        //adding the objects to thr SongPane
        SongPane.add(SongTitleLbl, 0, 0);
        SongPane.add(SongPlayTimeLbl, 0, 1);
        SongPane.add(SongArtistLbl, 1, 1);

        SongPane.add(tabSprtr, 0, 2, 4, 1);
        
        SongPane.setVgap(8);
        SongPane.setHgap(15);
        
        
        //Creating Collumn Constraints for the GridPane
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setMinWidth(300);
        cc1.setMaxWidth(300);
        
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setMinWidth(150);
        cc2.setMaxWidth(150);
        
        //Adding the Collumn Constraints to the GridPane, They affect the column in the way they are added
        SongPane.getColumnConstraints().add(cc1);  //This is added first so it applies to the first column
        SongPane.getColumnConstraints().add(cc2);  //This is added second so it applies to the second column
        
        //creating a button with deletion
        if(ButtonType == 1)
        {
            //Declaring a Button to thrash for Song
            Button trashSongBtn = new Button(null, new ImageView(
                    new Image(new FileInputStream("resources/trashbin.png"))));
            //setting an ID for the button 
            trashSongBtn.setId(ID + "-SongPane.button");
            
            
            SongPane.add(trashSongBtn, 3, 0, 1, 2);
            
            //action event for trash button
            trashSongBtn.setOnAction(new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent event) {
                
                    //Owner.getChildren().add(PLSongTab.getNoSongDispLbl());
                
                    System.out.println("del works");        //pro debugging
                    //SongPane.getChildren().clear();    //old command, clears entirety
                    
                    //Removes the Song Tab from the VBox
                    Owner.getChildren().remove(Owner.lookup("#"+SongPaneID));
                    //Removes the Song OBJ from the List
                    PlayList.remove(fromSong);
               
                    //checks if no songs, adds no song disp
                    if(Owner.getChildren().isEmpty())
                    {
                        Owner.getChildren().add(PLSongTab.getNoSongDispLbl());
                    
                    }
               }
            });  
        
        }
        
        //creating a button with addition
        if(ButtonType == 2)
        {
            //Declaring a Button to thrash for Song
            Button addSongBtn = new Button(null, new ImageView(
                    new Image(new FileInputStream("resources/plus_add.png"))));
            //setting an ID for the button 
            addSongBtn.setId(ID + "-SongPane.button");
            
            
            SongPane.add(addSongBtn, 3, 0, 1, 2);
            
            //action event for trash button
            addSongBtn.setOnAction(new EventHandler<ActionEvent>() {
            
                @Override
                public void handle(ActionEvent event) {
                
                    //Adds The Song to the LinkedList
                    //System.out.println("Song ="+ fromSong.toString());    //pro debug
                    PlayList.add(fromSong);
                    //System.out.println("PlayList:" + PlayList.getFirst().toString() );    //pro debug x2
                    
                    //Adds The Song Tba to the PlayList.
                    try {
                        Owner.getChildren().add(getSongTab(fromSong, ID, Owner, 1, PlayList));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PLSongTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Removes the NSD if present
                    GridPane NSDPane = (GridPane) Owner.lookup("#NSD");
                    if(NSDPane != null)
                    {
                        Owner.getChildren().remove(NSDPane);
                        //System.out.println("NSD removed");    //NSD removed but pro debuggin remains
                    }
                    
                    
                    //System.out.println("Addworks");   pro debuggin works
                    
                    
               }
            });  
        
        }
        
        return SongPane;
    }
    
}
