package com.gn.module.dialog;

import com.gn.database.DbUtil;
import com.gn.global.Formatter;
import com.gn.model.Member;
import com.gn.model.Partner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class DialogMember implements Initializable {

    private static Member target;

    @FXML
    public Label lblTitle;
    @FXML
    public TextField tfxFullName;
    @FXML
    public ComboBox cbxGender;
    @FXML
    public DatePicker dpkBirthday;
    @FXML
    public TextField txfAddress;
    @FXML
    public TextField txfTaxCode;
    @FXML
    public TextField txfCareer;
    @FXML
    public TextField txfEmail;
    @FXML
    public TextField txfLink;
    @FXML
    public TextArea txaIntro;
    @FXML
    public Button btnProcess;
    @FXML
    public Button btnReset;
    @FXML
    public Button btnCancel;
    @FXML
    public TextField txfBrief;
    @FXML
    public TextField txfPhone;

    DbUtil dbUtil = new DbUtil();

    public static Member getTarget() {
        return target;
    }

    private void setValues(Member data) {
        tfxFullName.setText(data.getFullName());
        txfAddress.setText(data.getAddress());
        txfCareer.setText(data.getCareer());
        txfBrief.setText(data.getBrief());
        txaIntro.setText(data.getIntro());
        txfEmail.setText(data.getEmail());
        txfTaxCode.setText(data.getTaxCode());
        dpkBirthday.setValue(Formatter.toLocalDate(data.getBirthday()));
        txfLink.setText(data.getSite());
        txfPhone.setText(data.getPhone());
    }

    private Member getValues() {
        String fullName = this.tfxFullName.getText() == null ? "" : this.tfxFullName.getText();
        String address = this.txfAddress.getText() == null ? "" : this.txfAddress.getText();
        String career = this.txfCareer.getText() == null ? "" : this.txfCareer.getText();
        String brief = this.txfBrief.getText() == null ? "" : this.txfBrief.getText();
        String intro = this.txaIntro.getText() == null ? "" : this.txaIntro.getText();
        String email = this.txfEmail.getText() == null ? "" : this.txfEmail.getText();
        String taxCode = this.txfTaxCode.getText() == null ? "" : this.txfTaxCode.getText();
        Date birthday = Date.from(this.dpkBirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String gender = this.cbxGender.getValue().toString() == null ? "" : this.cbxGender.getValue().toString();
        String phone = this.txfPhone.getText() == null ? "" : this.txfPhone.getText();
        String site = this.txfLink.getText() == null ? "" : this.txfLink.getText();

        Member member = new Member(fullName, gender, birthday, phone, address, taxCode, career, email, site, brief, intro);

        return member;
    }

    public static void setTarget(Member target) {
        DialogMember.target = target;
    }

    @FXML
    public void processDialog(ActionEvent actionEvent) {
        Member member = getValues();

        member.setAccountId(target.getAccountId());
        dbUtil.updateMember(member);

        closeDialog();
    }

    @FXML
    public void resetDialog(ActionEvent actionEvent) {
        setValues(target);
    }

    @FXML
    public void closeDialog() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void prepareComboBoxes() {
        com.gn.global.ComboBox.prepareComboBox(cbxGender, Arrays.asList("Nam", "Nữ"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.setText("Thông tin thành viên");
        btnProcess.setText("Cập nhật");

        prepareComboBoxes();
        setValues(target);
    }
}
