package com.gn.module.main;

import com.gn.App;
import com.gn.global.plugin.ViewManager;
import com.gn.module.profile.Profile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

    @FXML
    public ScrollPane body;
    @FXML
    public Label title;
    @FXML
    public Label lblUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set main scene to dashboard
        body.setContent(ViewManager.getInstance().get("dashboard"));
    }

    @FXML
    private void dashboard() {
        // View dashboard scene
        title.setText("Dashboard");
        body.setContent(ViewManager.getInstance().get("dashboard"));
    }

    @FXML
    private void profile() throws IOException {
        title.setText("Profile");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gn/module/profile/profile.fxml"));
        Node profileNode = loader.load();

        Profile profileController = loader.getController();
        profileController.setValues(App.member);
        body.setContent(profileNode);
    }

    @FXML
    private void partner() {
        // View partner scene
        title.setText("Partner");
        body.setContent(ViewManager.getInstance().get("partner"));
    }

    @FXML
    private void member() {
        // View member scene
        title.setText("Member");
        body.setContent(ViewManager.getInstance().get("member"));
    }
}
