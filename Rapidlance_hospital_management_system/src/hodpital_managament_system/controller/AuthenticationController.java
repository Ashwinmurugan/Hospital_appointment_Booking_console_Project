package hodpital_managament_system.controller;

import hodpital_managament_system.model.AuthenticationModel;
import hodpital_managament_system.view.AuthenticationView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthenticationController {
    private Connection connection;
    private Scanner scanner;
    private AuthenticationView view;
    private AuthenticationModel model; // Add this line


    public AuthenticationController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void login() {
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();

        String tableName = getTableName(email);

        try {
            if (model.login(username, email, password, tableName)) {
                view.displayLoginSuccess(username);

                // You can add logic here based on the user type (doctor or user) if needed.
            } else {
                view.displayLoginFailure();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred during login.");
        }
    }

    private String getTableName(String email) {
        // Implement logic to determine the table name based on email
        return email.toLowerCase().contains("doctor") ? "doctor_credential" : "user_credential";
    }

    public boolean doctorExists(String doctorUsername) {
        String query = "SELECT COUNT(*) AS count FROM doctor_credential WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctorUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking if the doctor exists.");
        }

        return false;
    }
}
