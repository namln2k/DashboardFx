package com.gn.module.dialog;

import com.gn.App;
import com.gn.database.DbUtil;
import com.gn.global.Formatter;
import com.gn.model.Partner;
import com.gn.model.TableData;
import com.gn.model.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class DialogTransaction implements Initializable {

    private static TableData target;
    private final DbUtil dbUtil = new DbUtil();
    @FXML
    private TextField txfProject;
    @FXML
    private ComboBox<String> cbxPartner;
    @FXML
    private TextField txfMoney;
    @FXML
    private DatePicker dpkTime;
    @FXML
    private TextField txfAction;
    @FXML
    private ComboBox<String> cbxStatus;
    @FXML
    private TextArea txaContent;
    @FXML
    private Button btnProcess;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblTitle;

    public TableData getTarget() {
        return target;
    }

    public static void setTarget(TableData targetObject) {
        target = targetObject;
    }

    @FXML
    private void closeDialog() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private Transaction getValues() {
        String project = this.txfProject.getText();
        int partnerId = dbUtil.getPartnerId(this.cbxPartner.getValue());
        long money = Long.valueOf(this.txfMoney.getText());
        Date time = Date.from(this.dpkTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String action = this.txfAction.getText();
        String content = this.txaContent.getText();
        int status = this.cbxStatus.getValue().equals("Completed") ? 1 : 0;

        Transaction transaction = new Transaction(App.member.getMemberId(), partnerId, project, time, money, action, content, status);

        return transaction;
    }

    private void setValues(TableData data) {
        txfProject.setText(data.getProject());
        if (data.getMoney() != 0) {
            txfMoney.setText(String.valueOf(data.getMoney()));
        }
        dpkTime.setValue(Formatter.dateToLocalDate(data.getTime()));
        com.gn.global.ComboBox.setValue(cbxPartner, data.getPartner());
        txfAction.setText(data.getAction());
        txaContent.setText(data.getContent());
        com.gn.global.ComboBox.setValue(cbxStatus, data.getStatus());
    }

    private void prepareComboBoxes() {
        List<String> partners = new ArrayList<>();
        for (Partner partner : dbUtil.getListPartner()) {
            partners.add(partner.getName());
        }
        com.gn.global.ComboBox.prepareComboBox(cbxPartner, partners);
        com.gn.global.ComboBox.prepareComboBox(cbxStatus, Arrays.asList("Pending", "Completed"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (target.getIndex() == 0) {
            target = new TableData();
            lblTitle.setText("Th??m giao d???ch");
            btnProcess.setText("Th??m");
        } else {
            lblTitle.setText("Th??ng tin giao d???ch");
            btnProcess.setText("C???p nh???t");
        }

        prepareComboBoxes();

        setValues(target);
    }

    @FXML
    private void resetDialog() {
        setValues(target);
    }

    @FXML
    private void processDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "B???n c?? ch???c ch???n mu???n c???p nh???t?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Transaction transaction = getValues();

            if (target.getIndex() == 0) {
                dbUtil.addTransaction(transaction);
            } else {
                dbUtil.updateTransaction(transaction);
            }

            closeDialog();
        }
    }
}
