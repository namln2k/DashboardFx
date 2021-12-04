/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.module.main;

import com.gn.App;
import com.gn.global.plugin.ViewManager;
import com.gn.module.partner.Partner;
import com.gn.module.profile.Profile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.Global.load;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
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
