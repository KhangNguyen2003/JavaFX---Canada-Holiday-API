package com.example.holidayapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View1Controller {
    @FXML
    private ComboBox<String> MonthComboBox;
    @FXML
    private Button SearchBtn;
    @FXML
    private ListView ListBox;

    //ComboBox Section
    String[] monthsArray = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    public void initialize() {
        MonthComboBox.getItems().addAll(monthsArray);
        MonthComboBox.getSelectionModel().select("January");

    }

    ///Btn and ListView
    @FXML
    private void handleSearchButton(ActionEvent event) throws IOException, InterruptedException {
        String selectedMonth = MonthComboBox.getValue();
        int month_digit = MonthComboBox.getSelectionModel().getSelectedIndex() + 1;

        //showSuccessAlert();
        fetchAPI Data = new fetchAPI(month_digit);
        JSONObject jsonObject = Data.getJsonObject();
        JSONArray Data_Holiday = jsonObject.getJSONObject("response").getJSONArray("holidays");

        //Clear the box
        ListBox.getItems().clear();

        // Iterate through the holiday data and add names to the ListView
        for (int i = 0; i < Data_Holiday.length(); i++) {
            JSONObject holidayJson = Data_Holiday.getJSONObject(i);
            String holidayName = holidayJson.getString("name");
            ListBox.getItems().add(holidayName);
        }
    }

    @FXML
    private void handleListItemClicked(MouseEvent event) throws IOException, InterruptedException {
        String selectedItem = (String) ListBox.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            openView2(selectedItem);
        }
    }

    private void openView2(String selectedItem) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View2.fxml"));
        Parent root;
        root = loader.load();
        View2Controller view2Controller = loader.getController();
        view2Controller.initData(selectedItem);

        // Retrieve the selected holiday data
        int month_digit = MonthComboBox.getSelectionModel().getSelectedIndex() + 1;
        fetchAPI Data = new fetchAPI(month_digit);
        JSONObject jsonObject = Data.getJsonObject();
        JSONArray Data_Holiday = jsonObject.getJSONObject("response").getJSONArray("holidays");
        JSONObject selectedHolidayJson = getHolidayJsonByName(selectedItem, Data_Holiday);

        // Pass the selected holiday data to View2Controller
        String description = selectedHolidayJson.getString("description");
        String isoDatetime = selectedHolidayJson.getJSONObject("date").getString("iso");
        String type = selectedHolidayJson.getJSONArray("type").getString(0);

        view2Controller.updateInfo(selectedItem, isoDatetime, description, type);


        Stage stage = new Stage();
        stage.setTitle("View 2");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private JSONObject getHolidayJsonByName(String name, JSONArray holidaysArray) {
        for (int i = 0; i < holidaysArray.length(); i++) {
            JSONObject holidayJson = holidaysArray.getJSONObject(i);
            if (holidayJson.getString("name").equals(name)) {
                return holidayJson;
            }
        }
        return null;
    }

//    private void showSuccessAlert() {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setHeaderText(null);
//        alert.setContentText("Month digit obtained successfully: " + month_digit);
//        alert.showAndWait();
//    }

}