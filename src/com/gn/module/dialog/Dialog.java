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

import com.gn.global.*;
import com.gn.model.TableData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  08/10/2018
 * Version 2.0
 */
public class Dialog implements Initializable {

    private static TableData target = new TableData();
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
    private TextField txfStatus;
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

    private void setValues(TableData data) {
        txfProject.setText(target.getProject());
        if (target.getMoney() != 0) {
            txfMoney.setText(String.valueOf(target.getMoney()));
        }
        dpkTime.setValue(Formatter.toLocalDate(target.getTime()));
        com.gn.global.ComboBox.setValue(cbxPartner, target.getPartner());
        txfAction.setText(target.getAction());
        txaContent.setText(target.getContent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (target.getIndex() == 0) {
            lblTitle.setText("Thêm giao dịch");
            btnProcess.setText("Thêm");
        } else {
            lblTitle.setText("Thông tin giao dịch");
            btnProcess.setText("Cập nhật");
        }
        setValues(target);
    }

    @FXML
    private void resetDialog() {
        setValues(target);
    }

    @FXML
    private void processDialog() {
//        setValues(target);
    }
}
