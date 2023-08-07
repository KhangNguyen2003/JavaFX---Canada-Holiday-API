module com.example.holidayapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens com.example.holidayapi to javafx.fxml;
    exports com.example.holidayapi;
}