package com.gn.module.member;

import com.gn.database.DbUtil;
import com.gn.model.Member;
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

public class MemberController implements Initializable {

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
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        updateView();

        tableView.setRowFactory(tv -> {
            TableRow<Member> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Member rowData = row.getItem();
//                    TODO: Mở dialog thông tin Member
                }
            });
            return row;
        });
    }

    public void updateView() {
        ObservableList<Member> tableData = FXCollections.observableArrayList();
        tableData.addAll(dbUtil.getListMember());
        tableView.setItems(tableData);
    }

    @FXML
    public void addMember() {

    }

    @FXML
    public void deleteMember() {
        Member selectedRow = tableView.getSelectionModel().getSelectedItem();
        dbUtil.deleteMember(selectedRow.getMemberId());
        updateView();
    }
}