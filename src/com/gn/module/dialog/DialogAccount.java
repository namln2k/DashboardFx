package com.gn.module.dialog;

import com.gn.database.DbUtil;
import com.gn.model.Account;
import com.gn.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogAccount implements Initializable {

    private static Account target;
    private final DbUtil dbUtil = new DbUtil();
    @FXML
    public TextField tfxUsername;
    @FXML
    public TextField txfPassword;
    @FXML
    public TextField tfxConfirm;
    @FXML
    public Button btnNext;
    @FXML
    public Button btnCancel;
    @FXML
    public Label lblTitle;
    @FXML
    public SVGPath lblErrorPassword;
    @FXML
    public SVGPath lblErrorConfirm;

    public static Account getTarget() {
        return target;
    }

    public static void setTarget(Account target) {
        DialogAccount.target = target;
    }

    private Account getValue() {
        String username = this.tfxUsername.getText();
        String password = this.txfPassword.getText();
//        TODO 1: Check password và confirm
        boolean isValid = true;

        if (true/*password không hợp lệ*/) {
            this.lblErrorPassword.setVisible(true);
            isValid = false;
        }

        if (true/*confirm không hợp lệ*/) {
            this.lblErrorConfirm.setVisible(true);
            isValid = false;
        }

        Account account;

        if (isValid) {
            account = new Account(username, password);
        } else {
            account = null;
            btnNext.setDisable(true);
        }

        return account;
    }

    @FXML
    private void closeDialog() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.setText("Thêm tài khoản");
    }

    @FXML
    public void nextDialog(ActionEvent actionEvent) throws IOException {
        Account account = getValue();

//        TODO 2: Check account có null hay không
        if (account == null) {
            // Password hoặc confirm không hợp lệ
        }
        else {
            int accountId = dbUtil.addAccount(account);

            // Ẩn các thông báo lỗi
            this.btnNext.setDisable(false);
            this.lblErrorPassword.setVisible(false);
            this.lblErrorConfirm.setVisible(false);

            DialogMember.setTarget(new Member(accountId));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gn/module/dialog/dialog_member.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent, 900, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            closeDialog();
        }
    }
}
