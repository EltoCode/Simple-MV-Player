package cw2kareoke;


import java.io.File;
import java.time.LocalTime;

public class Song {
    
    private String title; 
    private String artist;
    LocalTime playTime;
    File fileName;

    public Song(String title, String artist, LocalTime playTime, File fileName) {
        this.title = title;
        this.artist = artist;
        this.playTime = playTime;
        this.fileName = fileName;
    }
    
    public Song(String title, String artist, String playTime, String fileName) {
        this.title = title;
        this.artist = artist;
        this.playTime = LocalTime.ofSecondOfDay(Long.parseLong(playTime));
        this.fileName = new File(fileName);
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public LocalTime getPlayTime() {
        return playTime;
    }

    public File getFileName() {
        return fileName;
    }
    
    
    
    
    @Override
    public String toString()
    {
        return (title+"\t"+artist+"\t"+playTime.toString()+"\t"+fileName.toString());
    }
    

}
