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
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, Integer> colIndex;
    @FXML
    private TableColumn<Member, String> colName;
    @FXML
    private TableColumn<Member, String> colPhoneNumber;
    @FXML
    private TableColumn<Member, String> colAddress;
    @FXML
    private TableColumn<Member, String> colEmail;
    @FXML
    private TableColumn<Member, String> colCareer;
    @FXML
    private TableColumn<Member, String> colGender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIndex.setCellValueFactory(new PropertyValueFactory<>("MemberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCareer.setCellValueFactory(new PropertyValueFactory<>("career"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

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