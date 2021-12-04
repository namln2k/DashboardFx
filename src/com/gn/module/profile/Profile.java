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
package com.gn.module.profile;

import com.gn.model.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/11/2018
 */
public class Profile implements Initializable {

    @FXML private Label lblFullName;

    @FXML private Label lblAddress;

    @FXML private Label lblCareer;

    @FXML private Label lblPhoneNumber;

    @FXML private Text txtAddress;

    @FXML private Label lblEmail;

    @FXML private Label lblSite;

    @FXML private Text txtBrief;

    @FXML private Text txtBirthday;

    @FXML private Text txtGender;

    @FXML private Label lblIntro;

    public void setValues(Member member) {
//        TODO: Xóa các comment đi sau khi đã thêm các trường vào Member
        setLblFullName(member.getFullName());
        setLblAddress(member.getAddress());
//        setLblCareer(member.getCareer());
        setLblPhoneNumber(member.getPhone());
        setTxtAddress(member.getAddress());
//        setLblEmail(member.getEmail());
//        setLblSite(member.getSite());
//        setTxtBrief(member.getBrief());
        setTxtBirthday(member.getBirthday().toString());
        setTxtGender(member.getGender());
//        setLblIntro(member.getIntro());
    }

    public void setLblFullName(String lblFullName) {
        this.lblFullName.setText(lblFullName);
    }

    public void setLblAddress(String lblAddress) {
        this.lblAddress.setText(lblAddress);
    }

    public void setLblCareer(String lblCareer) {
        this.lblCareer.setText(lblCareer);
    }

    public void setLblPhoneNumber(String lblPhoneNumber) {
        this.lblPhoneNumber.setText(lblPhoneNumber);
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress.setText(txtAddress);
    }

    public void setLblEmail(String lblEmail) {
        this.lblEmail.setText(lblEmail);
    }

    public void setLblSite(String lblSite) {
        this.lblSite.setText(lblSite);
    }

    public void setTxtBrief(String txtBrief) {
        this.txtBrief.setText(txtBrief);
    }

    public void setTxtBirthday(String txtBirthday) {
        this.txtBirthday.setText(txtBirthday);
    }

    public void setTxtGender(String txtGender) {
        this.txtGender.setText(txtGender);
    }

    public void setLblIntro(String lblIntro) {
        this.lblIntro.setText(lblIntro);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
