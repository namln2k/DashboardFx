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

import com.gn.global.plugin.ViewManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
public class Main implements Initializable {

    @FXML
    private VBox views;
    @FXML
    public ScrollPane body;
    @FXML
    public Label title;

    public static Main ctrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = this;

        // Set main scene to dashboard
        body.setContent(ViewManager.getInstance().get("dashboard"));
    }

    @FXML
    private void dashboard() {
        // View dashboard
        title.setText("Dashboard");
        body.setContent(ViewManager.getInstance().get("dashboard"));
    }

    @FXML
    private void profile() {
        // View profile
        title.setText("Profile");
        body.setContent(ViewManager.getInstance().get("profile"));
    }
}
