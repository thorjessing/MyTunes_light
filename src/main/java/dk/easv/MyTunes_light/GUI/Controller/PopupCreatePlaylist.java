package dk.easv.MyTunes_light.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.awt.desktop.SystemEventListener;

public class PopupCreatePlaylist {
    @FXML
    private void btnCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnSave(ActionEvent actionEvent) {

    }

}
