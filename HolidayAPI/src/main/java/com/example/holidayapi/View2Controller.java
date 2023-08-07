package com.example.holidayapi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.BreakIterator;

public class View2Controller {
    @FXML
    private Button ReturnBtn;
    @FXML
    private Label HolidayName;
    @FXML
    private Label API_Datetime;
    @FXML
    private Label API_Des;
    @FXML
    private Label API_Type;

    public void Go2View1(){
        ReturnBtn.getScene().getWindow().hide();
    }

    //Changes the Holiday title.
    public void initData(String selectedItem) {
        HolidayName.setText(selectedItem);
    }

    public void updateInfo(String name, String datetime, String description, String type) {
        HolidayName.setText(name);
        API_Datetime.setText(datetime);
        API_Des.setText(description);
        API_Type.setText(type);
    }
}