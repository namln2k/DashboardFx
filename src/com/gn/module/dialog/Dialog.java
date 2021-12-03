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
package com.gn.module.dialog;

import com.gn.App;
import com.gn.database.DbUtil;
import com.gn.global.Formatter;
import com.gn.model.Partner;
import com.gn.model.TableData;
import com.gn.model.Transaction;
import com.gn.module.dashboard.Dashboard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
public class Dialog implements Initializable {

    private static TableData target;
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

    private final DbUtil dbUtil = new DbUtil();

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

    private void setValues(TableData data) {
        txfProject.setText(data.getProject());
        if (data.getMoney() != 0) {
            txfMoney.setText(String.valueOf(data.getMoney()));
        }
        dpkTime.setValue(Formatter.toLocalDate(data.getTime()));
        com.gn.global.ComboBox.setValue(cbxPartner, data.getPartner());
        txfAction.setText(data.getAction());
        txaContent.setText(data.getContent());
        com.gn.global.ComboBox.setValue(cbxStatus, data.getStatus());
    }

    private Transaction getValues() {
        String project = this.txfProject.getText();
        String partner = this.cbxPartner.getValue();
        long money = Long.valueOf(this.txfMoney.getText());
        Date time = Date.from(this.dpkTime.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String action = this.txfAction.getText();
        String content = this.txaContent.getText();

        Transaction transaction = new Transaction(App.member.getMemberId(), 1, project, time, money, action, content, 1);

        return transaction;
    }

    private void prepareComboBoxes() {
        List<Partner> partnerList = dbUtil.getListPartner();
        for (Partner partner : partnerList) {
            cbxPartner.getItems().add(partner.getName());
        }

        cbxStatus.getItems().addAll("Pending", "Completed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (target.getIndex() == 0) {
            target = new TableData();
            lblTitle.setText("Thêm giao dịch");
            btnProcess.setText("Thêm");
        } else {
            lblTitle.setText("Thông tin giao dịch");
            btnProcess.setText("Cập nhật");
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
        Transaction transaction = getValues();

        if (target.getIndex() == 0) {
            dbUtil.addTransaction(transaction);
        } else {
            dbUtil.updateTransaction(transaction);
        }

        closeDialog();
    }
}
