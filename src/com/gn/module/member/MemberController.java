package com.gn.module.member;

import com.gn.database.DbUtil;
import com.gn.model.Account;
import com.gn.model.Member;
import com.gn.model.TableData;
import com.gn.module.dialog.DialogAccount;
import com.gn.module.dialog.DialogMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
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
                    DialogMember.setTarget(rowData);
                    try {
                        openDialog("dialog_member.fxml", 900, 600);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    public void openDialog(String type, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gn/module/dialog/" + type));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, width, height);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        if (!stage.isShowing()) {
            updateView();
        }
    }

    @FXML
    public void addMember() throws IOException {
        DialogAccount.setTarget(new Account());

        openDialog("dialog_account.fxml", 642, 405);
    }

    @FXML
    public void deleteMember() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xóa?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Member selectedRow = tableView.getSelectionModel().getSelectedItem();
            dbUtil.deleteMember(selectedRow.getMemberId());
            updateView();
        }
    }
}