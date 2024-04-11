package hodpital_managament_system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationModel {
    private Connection connection;

    public AuthenticationModel(Connection connection) {
        this.connection = connection;
    }

    public boolean login(String username, String email, String password, String tableName) throws SQLException {
        String query = "SELECT username,email, password FROM " + tableName + " WHERE username = ? AND email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        }
    }

    public boolean registerDoctor(String username, String email, String password, String gender, String dob,
                                  String mobileno, String city, String specialization, String consultantFees) throws SQLException {
        String query = "INSERT INTO doctor_credential (username, email, password, gender, dob, mobileno, city, specialization, consultantfees) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, gender);
            statement.setString(5, dob);
            statement.setString(6, mobileno);
            statement.setString(7, city);
            statement.setString(8, specialization);
            statement.setString(9, consultantFees);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }
    }

    public boolean registerUser(String username, String email, String password, String gender, String dob,
                                String mobileno, String city) throws SQLException {
        String query = "INSERT INTO user_credential (username, email, password, gender, dob, mobileno, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, gender);
            statement.setString(5, dob);
            statement.setString(6, mobileno);
            statement.setString(7, city);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }
    }
}
