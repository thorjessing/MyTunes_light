package dk.easv.MyTunes_light.GUI.Controller;

import dk.easv.MyTunes_light.BE.Playlist;
import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.BLL.MediaHandler;
import dk.easv.MyTunes_light.GUI.Model.PlaylistModel;
import dk.easv.MyTunes_light.GUI.Model.SongModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {

    @FXML
    private TableView<Song> tblViewSongs;
    @FXML
    private TableColumn<Song, String> songDurationCol;
    @FXML
    private TableColumn<Song, String> songGenreCol;
    @FXML
    private TableColumn<Song, String> songArtistCol;
    @FXML
    private TableColumn<Song, String> songTitleCol;

    @FXML
    private TableView<Playlist> tblViewPlaylist; // Korrekt Playlist TableView

    private MediaHandler mediaHandler;

    private SongModel songModel;
    private PlaylistModel playlistModel;
    private ObservableList<Song> observableListSongs;
    private ObservableList<Playlist> observableListPlaylists;
    private ObservableList<Song> observablePlaylistSongs;

    @FXML
    private TableColumn<Playlist, String> playlistNameCol;
    @FXML
    private TableColumn<Playlist, Integer> playlistSongsCol;
    @FXML
    private TableColumn<Playlist, String> playlistDurationCol;
    @FXML
    private ListView<Song> listViewPlaylistSongs;
    @FXML
    private Button PauseBTN;
    @FXML
    private Slider slidrVol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            observableListSongs = songModel.getSongs();
            observableListPlaylists = playlistModel.getPlaylists();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        observablePlaylistSongs = FXCollections.observableArrayList();

        populateSongs();
        populatePlaylists();
        volume();
    }

    public MyTunesController() throws Exception {
        songModel = new SongModel();
        playlistModel = new PlaylistModel(); // Tilføjet initialisering
        mediaHandler = new MediaHandler();
    }

    //definere kolonner typer og smider data i songs
    private void populateSongs() {
        songTitleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        songGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        songDurationCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        songArtistCol.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tblViewSongs.setItems(observableListSongs);
    }

    //definerer kolonner typer og smider data i playlister
    private void populatePlaylists() {
        playlistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        playlistSongsCol.setCellValueFactory(new PropertyValueFactory<>("songCount"));
        playlistDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tblViewPlaylist.setItems(observableListPlaylists);

        listViewPlaylistSongs.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Song song, boolean empty) {
                super.updateItem(song, empty);
                setText(empty || song == null ? null : song.getArtist() + " - " + song.getName());
            }
        });


        registerPlaylistChange();
    }

    private void registerPlaylistChange() {
        tblViewPlaylist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, playlist) -> {
            if (playlist != null) {
                observablePlaylistSongs.setAll(playlist.getSongs());
                listViewPlaylistSongs.setItems(observablePlaylistSongs);
            }
        });

    }


    @FXML
    private void handleCreatePlaylist() {
        String playlistName = "New Playlist"; // Prompt kan tilføjes her.
        Playlist newPlaylist = new Playlist(playlistName);
        playlistModel.addPlaylist(newPlaylist);
    }

    @FXML
    private void handleAddSongToPlaylist() {
        Song selectedSong = tblViewSongs.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewPlaylist.getSelectionModel().getSelectedItem();

        if (selectedSong != null && selectedPlaylist != null) {
            playlistModel.addSongToPlaylist(selectedPlaylist, selectedSong);
        }
    }

    @FXML
    private void handleRemoveSongFromPlaylist() {
        Song selectedSong = tblViewSongs.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = tblViewPlaylist.getSelectionModel().getSelectedItem();

        if (selectedSong != null && selectedPlaylist != null) {
            playlistModel.removeSongFromPlaylist(selectedPlaylist, selectedSong);
        }
    }

    private MediaPlayer getSongMediaPlayer() {
        Song song = listViewPlaylistSongs.getSelectionModel().getSelectedItem();
        if (song == null) return null;

        return song.getMediaPlayer();
    }

    private void volume() {
        MediaPlayer currentSong = getSongMediaPlayer();
        slidrVol.setMin(0);
        slidrVol.setMax(100);

        slidrVol.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (currentSong == null) return;

                currentSong.setVolume(newValue.doubleValue() / 100);
            }
        });
    }

    @FXML
    private void playSongBtn(ActionEvent actionEvent) {
        MediaPlayer currentSong = getSongMediaPlayer();
        currentSong.setVolume(slidrVol.getValue() / 100);

        mediaHandler.playSong(currentSong, false);

        boolean isPlaying = currentSong.getStatus().equals(MediaPlayer.Status.PLAYING);
        PauseBTN.setText(isPlaying ? ">" : "II");
    }
}


