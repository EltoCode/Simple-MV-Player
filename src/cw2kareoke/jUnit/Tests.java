/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke.jUnit;

import cw2kareoke.Song;
import cw2kareoke.SongParseTest;
import static cw2kareoke.SongParseTest.loadSongs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elton John Fernandes
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        /*
        //Tests to see if all songs from src file are present in tree
       @Test
        public void songPrsntTest()
        {
           // writes Songs to TreeMap
           File songFile = new File("resources/sample_song_data.txt");
           TreeMap<String, Song> songHM = loadSongs(songFile);
           boolean songNotPresent = false;  //sets a flag to false
           
           try(BufferedReader IN = new BufferedReader(new FileReader(songFile))) 
        {   
            //goes through the file again, removes songs as they come from tree map
            //if a TreeMap.remove() returns null, then the song is not present in the TreeMap
            //Thus the flag is set to true
            for(String songLineDat; (songLineDat = IN.readLine()) != null; ) 
            {             
                if(songHM.remove(songLineDat) == null)
                    songNotPresent = true;
            }

        } catch (IOException ex) {
            Logger.getLogger(SongParseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
           //checks to see if the flag is still false
           assertEquals(songNotPresent, false);
           
        }

        
        
        */
    }
    
}
