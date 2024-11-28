module com.example.spotify_clone {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;


    opens dk.easv.spotify_clone to javafx.fxml;
    exports dk.easv.spotify_clone;
    exports dk.easv.spotify_clone.GUI.Controller;
    opens dk.easv.spotify_clone.GUI.Controller to javafx.fxml;
}