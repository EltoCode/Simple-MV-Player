/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cw2kareoke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Elton John Fernandes
 */
public class SongParseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        File songFile = new File("resources/sample_song_data.txt");
        
        TreeMap<String, Song> songHM = loadSongs(songFile);

        
        String srcStr = "Thou";
        
        //String currKey = songHM.firstKey();
        /*
        while(!songHM.isEmpty())
        {
            if(songHM.get(currKey).getTitle().contains(srcStr))
               System.out.println(songHM.get(currKey));
            if(currKey!=null)
            currKey = songHM.higherKey(currKey);
        }*/
        for(Map.Entry<String,Song> entry : songHM.entrySet()) 
        {
            Song value = entry.getValue();

        }
        
        for(Map.Entry<String,Song> entry : songHM.entrySet()) 
        {
            Song value = entry.getValue();
            
            if(value.getTitle().contains(srcStr))
               System.out.println(value);

        }
        
        //System.out.println(songHM.get());
        
    }
    
    public static TreeMap<String, Song> loadSongs(File fileParse) throws Exception
    {
        //creates a Tree Map to be returned
        TreeMap<String, Song> songHM = new TreeMap<String, Song>();
        
        ///////////////////

        //////////////////
        
        try(BufferedReader IN = new BufferedReader(new FileReader(fileParse))) 
        {           
            String[] songDat = new String[4];
            //reads each line
            for(String songLineDat; (songLineDat = IN.readLine()) != null; ) 
            {
                songDat = songLineDat.split("\t");  //spilts each line
                
                //puts each line as a Song into the TreeMap 
                songHM.put(songLineDat, new Song(songDat[0], songDat[1], songDat[2], songDat[3]));  
                
                //System.out.println(songHM.get(songLineDat).toString());   //pro debugging that was pro
            }

        } catch (IOException ex) {
            Logger.getLogger(SongParseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return songHM;
        
    }

}
