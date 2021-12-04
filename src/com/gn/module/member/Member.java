package com.gn.module.member;

import com.gn.database.DbUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Member implements Initializable {

    private final DbUtil dbUtil = new DbUtil();

    @FXML
    private TableView<com.gn.model.Member> tableView;
    @FXML
    private TableColumn<com.gn.model.Member, Integer> colIndex;
    @FXML
    private TableColumn<com.gn.model.Member, String> colName;
    @FXML
    private TableColumn<com.gn.model.Member, String> colPhoneNumber;
    @FXML
    private TableColumn<com.gn.model.Member, String> colAddress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIndex.setCellValueFactory(new PropertyValueFactory<>("MemberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        updateView();

        tableView.setRowFactory(tv -> {
            TableRow<com.gn.model.Member> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    com.gn.model.Member rowData = row.getItem();
//                    TODO: Mở dialog thông tin Member
                }
            });
            return row;
        });
    }

    public void updateView() {
        ObservableList<com.gn.model.Member> tableData = FXCollections.observableArrayList();
//        TODO: Thêm hàm getListMember() trong DbUtil
//              Sau đó bỏ comment ở 2 dòng dưới đi

//        tableData.addAll(dbUtil.getListMember());

//        tableView.setItems(tableData);

    }

    @FXML
    public void addMember() {

    }

    @FXML
    public void deleteMember() {
        com.gn.model.Member selectedRow = tableView.getSelectionModel().getSelectedItem();
//        TODO: Gọi hàm deleteMember trong dbUtil

    }
}