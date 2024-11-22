module com.example.schooldataform {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.schooldataform to javafx.fxml;
    exports com.example.schooldataform;
}