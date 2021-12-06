package com.gn.module.login;

import animatefx.animation.Pulse;
import com.gn.App;
import com.gn.GNAvatarView;
import com.gn.database.DbUtil;
import com.gn.global.plugin.ViewManager;
import com.gn.model.Member;
import com.gn.module.main.Main;
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

    private final RotateTransition rotateTransition = new RotateTransition();

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
