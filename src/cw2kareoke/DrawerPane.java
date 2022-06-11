/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;


import static cw2kareoke.LibraryPane.newLibraryPane;
import static cw2kareoke.SongParseTest.loadSongs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Elton John Fernandes
 */
public class DrawerPane extends Application {
    
    private static final Duration ANIMATION_SPEED = Duration.seconds(0.5);

    private enum ExpandState { EXPANDED, HIDDEN, CHANGING }

    private static ExpandState expandState_R = ExpandState.HIDDEN;
    private static ExpandState expandState_L = ExpandState.HIDDEN;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, Exception {
/*
        File songFile = new File("resources/sample_song_data.txt");
        TreeMap<String, Song> songTM = loadSongs(songFile);
        
        int ID = 0;
        
        VBox wew = new VBox(new Label("YYYYYYYYYY"), new Button("weoghoweughowei"));
        
        VBox LibPane = newLibraryPane(songTM, ID, null, null);
        
        StackPane root = new StackPane();
        //root.getChildren().add(newDrawerPane(root, new Button("weoghoweughowei"), new Button("weoghoweughowei")));
        root.getChildren().add(newDrawerPane(root, new Button("weoghoweughowei"), LibPane));
        
        Scene scene = new Scene(root, 600, 600);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        */
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static StackPane newDrawerPane(Pane Master, Node content_R, Node content_L)
    {        
        //creates GUI Elements for the Right Drawer
        Label PLLbl = new Label("PlayList");
        Tab PlayListTab = new Tab();
        PlayListTab.setGraphic(PLLbl);
        PlayListTab.setClosable(false);
        
        //checking if arguments were given and assigning a place holder if not
        if(content_R == null)
            PlayListTab.setContent(new Label("No Content Yet"));
        else
            PlayListTab.setContent(content_R);
            
        TabPane Drawer_R = new TabPane(PlayListTab);
        Drawer_R.setRotateGraphic(true);
        Drawer_R.setSide(Side.LEFT);
        
        
        //listener to keep the X pos consistent when resizing window 
        Master.widthProperty().addListener((o, old, width) -> {
            
            Drawer_R.setTranslateX(width.doubleValue()- 39);
            
        });
        
        //Checks if mouse entered the drawer, slides the drawer out
        PLLbl.setOnMouseEntered(e -> {
            if (expandState_R == ExpandState.HIDDEN) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_R);
                
                double tabWidth_R = PlayListTab.getContent().getBoundsInLocal().getWidth();
                
                transition.setByX(-tabWidth_R);

                expandState_R = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_R = ExpandState.EXPANDED;
                });

                transition.play();
            }
        });

        //Checks if mouse exited the drawer, slides the drawer in
        Drawer_R.setOnMouseExited(e -> {
            if (expandState_R == ExpandState.EXPANDED) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_R);
                
                double tabWidth_R = PlayListTab.getContent().getBoundsInLocal().getWidth();
                
                transition.setByX(tabWidth_R);

                expandState_R = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_R = ExpandState.HIDDEN;
                });

                transition.play();
            }
        });
        
        //creates GUI Elements for the Left Drawer
        Label LibLbl = new Label("Library");
        Tab LibraryTab = new Tab();
        LibraryTab.setGraphic(LibLbl);
        LibraryTab.setClosable(false);
        
        if(content_L == null)
            LibraryTab.setContent(new Label("No Content Yet"));
        else
            LibraryTab.setContent((content_L));


        TabPane Drawer_L = new TabPane(LibraryTab);
        Drawer_L.setRotateGraphic(true);
        Drawer_L.setSide(Side.RIGHT);
        Drawer_L.setMaxWidth(Region.USE_PREF_SIZE);
        
        //listener to keep the X pos consistent when resizing window 
        LibraryTab.getContent().boundsInLocalProperty().addListener((o, old, newBnd) -> {            
            
            double tabConWidth = newBnd.getWidth();
            double loc = (-Master.getWidth() + (tabConWidth))/2 - tabConWidth + 19;
            
            System.out.println("LibTab= " + tabConWidth);
            
            Drawer_L.setTranslateX(loc);
        });
        
        //listener to keep the X pos consistent when resizing window 
        Master.widthProperty().addListener((o, old, width) -> {
            
            double tabConWidth = LibraryTab.getContent().getBoundsInLocal().getWidth();
            double loc = (-Master.getWidth() + (tabConWidth))/2 - tabConWidth + 19;
            
            System.out.println("LibTab from master= " + tabConWidth);
            
            Drawer_L.setTranslateX(loc);
            /*System.out.println("loc = " + loc);/*     //super pro debuging
            System.out.println("Tab Width= " + tabConWidth );*/
        });
        
        //Checks if mouse entered the drawer, slides the drawer out
        LibLbl.setOnMouseEntered(e -> {
            if (expandState_L == ExpandState.HIDDEN) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_L);
                
                double tabWidth_L = LibraryTab.getContent().getBoundsInLocal().getWidth();
                
                transition.setByX(tabWidth_L);

                expandState_L = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_L = ExpandState.EXPANDED;
                });
                
                //System.out.println("LibLbl Enter");   //pro debuging
                
                transition.play();
            }
        });

        //Checks if mouse exited the drawer, slides the drawer in
        Drawer_L.setOnMouseExited(e -> {
            if (expandState_L == ExpandState.EXPANDED) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_L);
                
                double tabWidth_L = LibraryTab.getContent().getBoundsInLocal().getWidth();
                
                transition.setByX(-tabWidth_L);

                expandState_L = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_L = ExpandState.HIDDEN;
                });
                //System.out.println("Mouse Exit");     // pro yo debug
                transition.play();
            }
        });
        
        
        StackPane DrawerPane = new StackPane(Drawer_R, Drawer_L);
        return DrawerPane;
    }
    
    public static StackPane newDrawerPane(Pane Master, Node content_R, Node content_L, double contentWidth_R, double contentWidth_L)
    {        
        //creates GUI Elements for the Right Drawer
        Label PLLbl = new Label("PlayList");
        Tab PlayListTab = new Tab();
        PlayListTab.setGraphic(PLLbl);
        PlayListTab.setClosable(false);
        
        //checking if arguments were given and assigning a place holder if not
        if(content_R == null)
            PlayListTab.setContent(new Label("No Content Yet"));
        else
            PlayListTab.setContent(content_R);
            
        TabPane Drawer_R = new TabPane(PlayListTab);
        Drawer_R.setRotateGraphic(true);
        Drawer_R.setSide(Side.LEFT);
        
        //listener to keep the X pos consistent when resizing window 
        Master.widthProperty().addListener((o, old, width) -> {
            
            Drawer_R.setTranslateX(width.doubleValue()- 39);
            
        });
        
        //Checks if mouse entered the drawer, slides the drawer out
        PLLbl.setOnMouseEntered(e -> {
            if (expandState_R == ExpandState.HIDDEN) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_R);
                
                transition.setByX(-contentWidth_R);

                expandState_R = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_R = ExpandState.EXPANDED;
                });

                transition.play();
            }
        });

        //Checks if mouse exited the drawer, slides the drawer in
        Drawer_R.setOnMouseExited(e -> {
            if (expandState_R == ExpandState.EXPANDED) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_R);
                
                transition.setByX(contentWidth_R);

                expandState_R = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_R = ExpandState.HIDDEN;
                });

                transition.play();
            }
        });
        
        //creates GUI Elements for the Left Drawer
        Label LibLbl = new Label("Library");
        Tab LibraryTab = new Tab();
        LibraryTab.setGraphic(LibLbl);
        LibraryTab.setClosable(false);
        
        if(content_L == null)
            LibraryTab.setContent(new Label("No Content Yet"));
        else
            LibraryTab.setContent((content_L));


        TabPane Drawer_L = new TabPane(LibraryTab);
        Drawer_L.setRotateGraphic(true);
        Drawer_L.setSide(Side.RIGHT);
        Drawer_L.setMaxWidth(Region.USE_PREF_SIZE);
        
        //listener to keep the X pos consistent when resizing window 
        LibraryTab.getContent().boundsInLocalProperty().addListener((o, old, newBnd) -> {            
            
            double tabConWidth = newBnd.getWidth();
            double loc = (-Master.getWidth() + (tabConWidth))/2 - tabConWidth + 19;
            
            System.out.println("LibTab= " + tabConWidth);
            
            Drawer_L.setTranslateX(loc);
        });
        
        //listener to keep the X pos consistent when resizing window 
        Master.widthProperty().addListener((o, old, width) -> {
            
            double tabConWidth = LibraryTab.getContent().getBoundsInLocal().getWidth();
            double loc = (-Master.getWidth() + (tabConWidth))/2 - tabConWidth + 19;
            
            System.out.println("LibTab from master= " + tabConWidth);
            
            Drawer_L.setTranslateX(loc);
            System.out.println("loc = " + loc);/*
            System.out.println("Tab Width= " + tabConWidth );*/
        });
        
        //Checks if mouse entered the drawer, slides the drawer out
        LibLbl.setOnMouseEntered(e -> {
            if (expandState_L == ExpandState.HIDDEN) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_L);

                transition.setByX(contentWidth_L);

                expandState_L = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_L = ExpandState.EXPANDED;
                });
                
                System.out.println("LibLbl Enter");
                
                transition.play();
            }
        });

        //Checks if mouse exited the drawer, slides the drawer in
        Drawer_L.setOnMouseExited(e -> {
            if (expandState_L == ExpandState.EXPANDED) {
                TranslateTransition transition =
                    new TranslateTransition(ANIMATION_SPEED, Drawer_L);

                transition.setByX(-contentWidth_L);

                expandState_L = ExpandState.CHANGING;
                transition.setOnFinished(ae -> {
                    expandState_L = ExpandState.HIDDEN;
                });
                System.out.println("Mouse Exit");
                transition.play();
            }
        });
        
        
        StackPane DrawerPane = new StackPane(Drawer_R, Drawer_L);
        return DrawerPane;
    }
    
}
