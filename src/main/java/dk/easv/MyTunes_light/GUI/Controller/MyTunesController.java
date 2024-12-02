package dk.easv.MyTunes_light.GUI.Controller;

import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.GUI.Model.SongModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {

    @FXML
    private TableView<Song> tblViewSongs;
    @FXML
    private TableColumn<Integer, Song> songDurationCol;
    @FXML
    private TableColumn<String, Song> songGenreCol;
    @FXML
    private TableColumn<String, Song> songArtistCol;
    @FXML
    private TableColumn<String, Song> songTitleCol;

    private SongModel songModel;
    private boolean isPlaying = false;
    private ObservableList observableListSongs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            observableListSongs = songModel.getSongs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        songTitleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        songGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        songDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        songArtistCol.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tblViewSongs.setItems(observableListSongs);
    }

    public MyTunesController() throws Exception {
        tblViewSongs = new TableView<>();
        songModel = new SongModel();
    }
}
