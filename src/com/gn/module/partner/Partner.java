package com.gn.module.partner;

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

public class Partner implements Initializable {

    private final DbUtil dbUtil = new DbUtil();

    @FXML
    private TableView<com.gn.model.Partner> tableView;
    @FXML
    private TableColumn<com.gn.model.Partner, Integer> colIndex;
    @FXML
    private TableColumn<com.gn.model.Partner, String> colName;
    @FXML
    private TableColumn<com.gn.model.Partner, String> colPhoneNumber;
    @FXML
    private TableColumn<com.gn.model.Partner, String> colAddress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIndex.setCellValueFactory(new PropertyValueFactory<>("partnerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        updateView();

        tableView.setRowFactory(tv -> {
            TableRow<com.gn.model.Partner> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    com.gn.model.Partner rowData = row.getItem();
//                    TODO: Mở dialog thông tin Partner
                }
            });
            return row;
        });
    }

    public void updateView() {
        ObservableList<com.gn.model.Partner> tableData = FXCollections.observableArrayList();
        tableData.addAll(dbUtil.getListPartner());

        tableView.setItems(tableData);
    }

    @FXML
    public void addPartner() {

    }

    @FXML
    public void deletePartner() {
        com.gn.model.Partner selectedRow = tableView.getSelectionModel().getSelectedItem();
//        TODO: Gọi hàm deletePartner trong dbUtil
    }
}