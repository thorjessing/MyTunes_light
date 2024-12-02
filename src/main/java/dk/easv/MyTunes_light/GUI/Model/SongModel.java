package dk.easv.MyTunes_light.GUI.Model;

import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.BLL.SongManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class SongModel {

    private ObservableList<Song> songs;
    private SongManager songManager;

    public SongModel() throws Exception {

        songManager = new SongManager();
    }

    public ObservableList<Song> getSongs() throws Exception {
        songs = FXCollections.observableArrayList();
        songs.addAll(songManager.getAllSongs());
        return songs;
    }
}
