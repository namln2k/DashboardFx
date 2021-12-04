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
package com.gn.module.dashboard;

import com.gn.database.DbUtil;
import com.gn.model.TableData;
import com.gn.module.dialog.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/10/2018
 * Version 1.0
 */
public class Dashboard implements Initializable {

    private final DbUtil dbUtil = new DbUtil();

    @FXML
    private TableView<TableData> tableView;
    @FXML
    private TableColumn<TableData, Integer> colIndex;
    @FXML
    private TableColumn<TableData, String> colUsername;
    @FXML
    private TableColumn<TableData, String> colFullName;
    @FXML
    private TableColumn<TableData, String> colProject;
    @FXML
    private TableColumn<TableData, String> colPartner;
    @FXML
    private TableColumn<TableData, Long> colMoney;
    @FXML
    private TableColumn<TableData, Date> colTime;
    @FXML
    private TableColumn<TableData, String> colAction;
    @FXML
    private TableColumn<TableData, String> colContent;
    @FXML
    private TableColumn<TableData, String> colStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colProject.setCellValueFactory(new PropertyValueFactory<>("project"));
        colPartner.setCellValueFactory(new PropertyValueFactory<>("partner"));
        colMoney.setCellValueFactory(new PropertyValueFactory<>("money"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        updateView();

        tableView.setRowFactory(tv -> {
            TableRow<TableData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    TableData rowData = row.getItem();
                    Dialog.setTarget(rowData);
                    try {
                        openDialog();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    public void updateView() {
        ObservableList<TableData> tableData = FXCollections.observableArrayList();
        tableData.addAll(dbUtil.getDataTable());

        tableView.setItems(tableData);
    }

    @FXML
    private void deleteTransaction(ActionEvent event) {
        TableData selectedRow = tableView.getSelectionModel().getSelectedItem();

        dbUtil.deleteTransaction(selectedRow.getTransactionId());

        updateView();
    }

    public void openDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gn/module/dialog/dialog.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 900, 600);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        if (!stage.isShowing()) {
            updateView();
        }
    }

    @FXML
    void addTransaction(ActionEvent event) throws IOException {
        Dialog.setTarget(new TableData());
        openDialog();
    }
}
