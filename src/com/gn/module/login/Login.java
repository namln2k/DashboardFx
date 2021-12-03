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
package com.gn.module.login;

import animatefx.animation.Pulse;
import com.gn.App;
import com.gn.GNAvatarView;
import com.gn.database.DbUtil;
import com.gn.global.plugin.ViewManager;
import com.gn.model.Member;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/11/2018
 * Version 1.0.2
 */
public class Login implements Initializable {

    @FXML
    private GNAvatarView avatar;
    @FXML
    private HBox boxUsername;
    @FXML
    private HBox boxPassword;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;

    @FXML
    private Label lblPassword;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblError;

    private RotateTransition rotateTransition = new RotateTransition();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

        addEffect(password);
        addEffect(username);

    }

    private void addEffect(Node node) {
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            rotateTransition.play();
            Pulse pulse = new Pulse(node.getParent());
            pulse.setDelay(Duration.millis(100));
            pulse.setSpeed(5);
            pulse.play();
            node.getParent().setStyle("-icon-color : -success; -fx-border-color : -success");
        });

        node.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!node.isFocused())
                node.getParent().setStyle("-icon-color : -dark-gray; -fx-border-color : transparent");
            else node.getParent().setStyle("-icon-color : -success; -fx-border-color : -success");
        });
    }

    @FXML
    private void loginAction() throws IOException {
        Pulse pulse = new Pulse(login);
        pulse.setDelay(Duration.millis(20));
        pulse.play();
        enter();
    }

    private void enter() {
        String username = this.username.getText();
        String password = this.password.getText();
        DbUtil dbUtil = new DbUtil();
        Member user = dbUtil.getAccountMember(username, password);
        if (user.getMemberId() != 0) {
            App.member = user;
            App.decorator.setContent(ViewManager.getInstance().get("main"));
        } else {
            lblError.setVisible(true);
        }
    }
}
