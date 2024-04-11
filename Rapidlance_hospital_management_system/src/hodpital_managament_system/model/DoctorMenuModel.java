// DoctorMenuModel.java
package hodpital_managament_system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DoctorMenuModel {
    private Connection connection;

    public DoctorMenuModel(Connection connection) {
        this.connection = connection;
    }

    public ResultSet viewAppointmentList(String docName) throws SQLException {
        String query = "SELECT * FROM appointment_details WHERE doctor_username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, docName);
        return statement.executeQuery();
    }

    public ResultSet viewSuggestedTablets() throws SQLException {
        String query = "SELECT * FROM suggested_tablet";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public ResultSet viewNurseAvailability() throws SQLException {
        String query = "SELECT * FROM available_nurse";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public void updateAppointmentStatus(String doctorName, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient Name to update status: ");
        String patientName = scanner.nextLine();
        if (!patientExists(patientName, doctorName)) {
            System.out.println("Patient with name '" + patientName + "' not found for Doctor '" + doctorName + "'.");
            return;
        }
        System.out.print("Enter status (undergoing Treatment/come next week/Treatment done): ");
        String newStatus = scanner.nextLine();
        String updateQuery = "UPDATE appointment_details SET status = ? WHERE patient_name = ? AND doctor_username = ?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, newStatus);
            updateStatement.setString(2, patientName);
            updateStatement.setString(3, doctorName);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Status updated successfully for patient: " + patientName);
            } else {
                System.out.println("Failed to update status. Please try again.");
            }
        }
        catch(SQLException e) {
            System.out.println(e.toString());
        }
    }

    private boolean patientExists(String patientName, String doctorName) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM appointment_details WHERE patient_name = ? AND doctor_username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patientName);
            statement.setString(2, doctorName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }

        return false;
    }

    // Other methods related to the DoctorMenuModel...
}
