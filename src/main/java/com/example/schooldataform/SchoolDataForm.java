package com.example.schooldataform;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class SchoolDataForm extends Application {
    private static final ArrayList<Person> persons = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {


        Image topBanner = new Image(this.getClass().getResource("/school.jpg").toExternalForm());
        ImageView topBannerView = new ImageView(topBanner);

        topBannerView.setPreserveRatio(true);
        topBannerView.setFitWidth(500);
        topBannerView.setFitHeight(100);

        StackPane topBannerPane = new StackPane(topBannerView);
        topBannerPane.setStyle("-fx-background-color: #f0f0f0;");
        topBannerPane.setAlignment(Pos.CENTER);
        topBannerView.fitWidthProperty().bind(topBannerPane.widthProperty());

        HBox topBannerBox = new HBox(topBannerView);
        topBannerBox.setStyle("-fx-alignment: center; -fx-background-color:  #D3D3D3;");

        TextField nameField = new TextField();
        TextField fatherNameField = new TextField();
        DatePicker dobPicker = new DatePicker();
        TextField cnicField = new TextField();

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Karachi", "Lahore", "Islamabad", "Peshawar", "Quetta", "Faisalabad", "Multan");
        cityComboBox.setPromptText("Select City");

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);

        Button uploadImageButton = new Button("Upload Image");
        ImageView uploadImageView = new ImageView();
        uploadImageView.setFitWidth(100);
        uploadImageView.setFitHeight(100);
        uploadImageView.setPreserveRatio(true);

        uploadImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                uploadImageView.setImage(new Image(file.toURI().toString()));
            }
        });

        Button saveButton = new Button("Save");
        Button discardButton = new Button("Discard");

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : "Not Selected";
            String cnic = cnicField.getText();
            String city = cityComboBox.getValue() != null ? cityComboBox.getValue() : "Not Selected";
            String gender = genderGroup.getSelectedToggle() != null ?
                    ((RadioButton) genderGroup.getSelectedToggle()).getText() : "Not Selected";

            Person newPerson = new Person(name, fatherName, dob, cnic, city, gender);
            persons.add(newPerson);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Form Saved");
            alert.setHeaderText("Data Saved Successfully!");
            alert.setContentText("Person Details:\n" + newPerson);
            alert.showAndWait();

            nameField.clear();
            fatherNameField.clear();
            dobPicker.setValue(null);
            cnicField.clear();
            cityComboBox.setValue(null);
            if (genderGroup.getSelectedToggle() != null) {
                genderGroup.getSelectedToggle().setSelected(false);
            }
            uploadImageView.setImage(null);
        });

        discardButton.setOnAction(e -> {
            nameField.clear();
            fatherNameField.clear();
            dobPicker.setValue(null);
            cnicField.clear();
            cityComboBox.setValue(null);
            genderGroup.getSelectedToggle().setSelected(false);
            uploadImageView.setImage(null);
        });

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        // formGrid.add(topBannerView, 0,0,1,1);
        formGrid.add(new Label("Name:"), 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(new Label("Father Name:"), 0, 1);
        formGrid.add(fatherNameField, 1, 1);
        formGrid.add(new Label("Date of Birth:"), 0, 2);
        formGrid.add(dobPicker, 1, 2);
        formGrid.add(new Label("CNIC Number:"), 0, 3);
        formGrid.add(cnicField, 1, 3);
        formGrid.add(new Label("City:"), 0, 4);
        formGrid.add(cityComboBox, 1, 4);
        formGrid.add(new Label("Gender:"), 0, 5);
        HBox genderBox = new HBox(10, maleButton, femaleButton);
        formGrid.add(genderBox, 1, 5);
        formGrid.add(new Label("Upload Image:"), 0, 6);
        formGrid.add(uploadImageButton, 1, 6);
        formGrid.add(uploadImageView, 2, 6);

        HBox buttonBox = new HBox(10, saveButton, discardButton);
        formGrid.add(buttonBox, 1, 7);

        VBox root = new VBox(10, topBannerBox, formGrid);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setTitle("School Data Form");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
