package com.gn.module.partner;

import com.gn.database.DbUtil;
import com.gn.model.Member;
import com.gn.model.Partner;
import com.gn.model.TableData;
import com.gn.module.dialog.DialogPartner;
import com.gn.module.dialog.DialogTransaction;
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

public class PartnerController implements Initializable {

    private final DbUtil dbUtil = new DbUtil();

    @FXML
    private TableView<Partner> tableView;
    @FXML
    private TableColumn<Partner, Integer> colIndex;
    @FXML
    private TableColumn<Partner, String> colName;
    @FXML
    private TableColumn<Partner, String> colPhoneNumber;
    @FXML
    private TableColumn<Partner, String> colAddress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        updateView();

        tableView.setRowFactory(tv -> {
            TableRow<Partner> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Partner rowData = row.getItem();
                    DialogPartner.setTarget(rowData);
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
        ObservableList<Partner> tableData = FXCollections.observableArrayList();
        tableData.addAll(dbUtil.getListPartner());

        tableView.setItems(tableData);
    }

    public void openDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gn/module/dialog/dialog_partner.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 900, 408);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        if (!stage.isShowing()) {
            updateView();
        }
    }

    @FXML
    public void addPartner() throws IOException {
        DialogPartner.setTarget(new Partner());
        openDialog();
    }

    @FXML
    public void deletePartner() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "B???n c?? ch???c ch???n mu???n x??a?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Partner selectedRow = tableView.getSelectionModel().getSelectedItem();
            dbUtil.deletePartner(selectedRow.getPartnerId());
            updateView();
        }
    }
}