package teht3;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class Form extends VBox {
    private Label localeLabel = new Label();
    private ComboBox<String> localeField = new ComboBox<>();
    private Label firstNameLabel = new Label();
    private TextField firstNameField = new TextField();
    private Label lastNameLabel = new Label();
    private TextField lastNameField = new TextField();
    private Label emailLabel = new Label();
    private TextField emailField = new TextField();
    private Button submitButton = new Button();

    public Form() {
        localeField.getItems().addAll("English", "Farsi", "Japanese");
        localeField.getSelectionModel().selectFirst();
    
        updateLabels(localeField.getValue());
    
        localeField.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateLabels(newValue);
        });
    
        submitButton.setOnAction(e -> submitForm());
        getChildren().addAll(localeLabel, localeField, firstNameLabel, firstNameField, lastNameLabel, lastNameField, emailLabel, emailField, submitButton);
    }

    private void updateLabels(String locale) {
        Map<String, Locale> localeMap = new HashMap<>();
        localeMap.put("English", Locale.ENGLISH);
        localeMap.put("Farsi", new Locale("fa"));
        localeMap.put("Japanese", Locale.JAPANESE);
    
        ResourceBundle bundle = ResourceBundle.getBundle("translations", localeMap.get(locale));
    
        localeLabel.setText(bundle.getString("Locale"));
        firstNameLabel.setText(bundle.getString("FirstName"));
        lastNameLabel.setText(bundle.getString("LastName"));
        emailLabel.setText(bundle.getString("Email"));
        submitButton.setText(bundle.getString("Submit"));
    }

    private void submitForm() {
        String locale = localeField.getValue();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
    
        Map<String, String> localeToTableSuffix = new HashMap<>();
        localeToTableSuffix.put("English", "en");
        localeToTableSuffix.put("Farsi", "fa");
        localeToTableSuffix.put("Japanese", "ja");
    
        String tableName = "employee_" + localeToTableSuffix.get(locale);
    
        String sql = "INSERT INTO " + tableName + " (first_name, last_name, email) VALUES (?, ?, ?)";
    
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
    
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
    
            pstmt.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}