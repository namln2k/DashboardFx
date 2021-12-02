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

import com.gn.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/10/2018
 * Version 1.0
 */
public class Dashboard implements Initializable {

    @FXML
    private TableView<Transaction> table_view;
    @FXML
    private TableColumn<Transaction, Integer> col_stt;
    @FXML
    private TableColumn<Transaction, String> col_username;
    @FXML
    private TableColumn<Transaction, String> col_fullname;
    @FXML
    private TableColumn<Transaction, String> col_project;
    @FXML
    private TableColumn<Transaction, String> col_partner;
    @FXML
    private TableColumn<Transaction, Long> col_money;
    @FXML
    private TableColumn<Transaction, Date> col_time;
    @FXML
    private TableColumn<Transaction, String> col_action;
    @FXML
    private TableColumn<Transaction, String> col_content;
    @FXML
    private TableColumn<Transaction, String> col_status;
    @FXML
    private Button btn_delete;

    ObservableList<Transaction> table_data = FXCollections.observableArrayList();

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //            table_data.add(new Transaction(1, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(2, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(3, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(4, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(5, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(6, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(7, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));
//            table_data.add(new Transaction(8, "ten_dang_nhap", "Le Nhat Nam", "du_an", "doi_tac", 1000000, new SimpleDateFormat("dd/MM/yyyy").parse("25/11/2021"), "hanh_dong", "noi_dung", "trang_thai"));

        col_stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        col_project.setCellValueFactory(new PropertyValueFactory<>("project"));
        col_partner.setCellValueFactory(new PropertyValueFactory<>("partner"));
        col_money.setCellValueFactory(new PropertyValueFactory<>("money"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_action.setCellValueFactory(new PropertyValueFactory<>("action"));
        col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        table_view.setItems(table_data);
    }

    @FXML
    private void deleteRow(ActionEvent event) {
//        if (table_view.getSelectionModel().getSelectedItem() != null) {
//            System.out.println(table_view.getSelectionModel().getSelectedItem().getStt());
//        }
    }

    @FXML
    void openDialog(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gn/module/dialog/dialog.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 900, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
