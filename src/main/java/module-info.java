module com.example.MyTunes_light {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;


    opens dk.easv.MyTunes_light to javafx.fxml;
    exports dk.easv.MyTunes_light;
    exports dk.easv.MyTunes_light.GUI.Controller;
    opens dk.easv.MyTunes_light.GUI.Controller to javafx.fxml;
}