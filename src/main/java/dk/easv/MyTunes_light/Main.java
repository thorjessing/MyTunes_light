package dk.easv.MyTunes_light;

import dk.easv.MyTunes_light.BE.Playlist;
import dk.easv.MyTunes_light.BE.Song;
import dk.easv.MyTunes_light.DAL.DBConnecter;
import dk.easv.MyTunes_light.DAL.dao.PlaylistDAO;
import dk.easv.MyTunes_light.DAL.dao.SongDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.foreign.PaddingLayout;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 835, 500);
        stage.setTitle("MyTunes");
        stage.setScene(scene);
        stage.show();

        PlaylistDAO playlistDAO = new PlaylistDAO();

        for (Playlist p : playlistDAO.getAllPlaylistWithSongs()) {
            System.out.println("--------- " + p.getName() + " (" + p.getSongs().size() + ") -----------");
            for (Song song : p.getSongs()) {
                System.out.println(song.getDuration() + " " + song.getLengthString());
            }
        }
        /*
        SongDAO songDAO = new SongDAO();

        List<Song> alleSange = songDAO.getAllSongs();
        System.out.println("Antal sange: " + alleSange.size());

        for (Song song : alleSange)
            System.out.println(song);*/
    }

    public static void main(String[] args) {
        launch();
    }
}