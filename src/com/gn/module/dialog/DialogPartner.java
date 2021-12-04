package com.gn.module.dialog;

import com.gn.App;
import com.gn.database.DbUtil;
import com.gn.global.Formatter;
import com.gn.model.Partner;
import com.gn.model.TableData;
import com.gn.model.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class DialogPartner implements Initializable {

    private static Partner target;
    @FXML
    private TextField txfName;
    @FXML
    private TextField txfPhone;
    @FXML
    private TextField txfAddress;
    @FXML
    private Button btnProcess;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblTitle;

    private DbUtil dbUtil = new DbUtil();

    public static Partner getTarget() {
        return target;
    }

    public static void setTarget(Partner targetObject) {
        target = targetObject;
    }

    private void setValues(Partner data) {
        txfName.setText(data.getName());
        txfAddress.setText(data.getAddress());
        txfPhone.setText(data.getPhone());
    }

    private Partner getValues() {
        String name = this.txfName.getText();
        String phone = this.txfPhone.getText();
        String address = this.txfAddress.getText();

        Partner partner = new Partner(name, phone, address);

        return partner;
    }

    @FXML
    private void closeDialog() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void processDialog() {
        Partner partner = getValues();

        if (target.getPartnerId() == 0) {
            dbUtil.addPartner(partner);
        } else {
            dbUtil.updatePartner(partner);
        }

        closeDialog();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (target.getPartnerId() == 0) {
            target = new Partner();
            lblTitle.setText("Thêm đối tác");
            btnProcess.setText("Thêm");
        } else {
            lblTitle.setText("Thông tin đối tác");
            btnProcess.setText("Cập nhật");
        }

        setValues(target);
    }

    @FXML
    public void resetDialog() {
        setValues(target);
    }
}
