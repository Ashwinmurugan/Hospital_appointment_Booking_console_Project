package hodpital_managament_system.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserMenuModel {
    private Connection connection;

    public UserMenuModel(Connection connection) {
        this.connection = connection;
    }

    public ResultSet viewDoctorAvailability() throws SQLException {
        String query = "SELECT username, specialization, consultantfees FROM doctor_credential";
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public int bookAppointment(String doctorUsername, String patientName, String symptoms, String dateOfAppointment, double consultantFees) throws SQLException {
        String appointmentQuery = "INSERT INTO appointment_details (doctor_username, patient_name, symptoms, date_of_appointment, consultant_fee, status) VALUES (?, ?, ?, ?, ?, 'Not Treated')";
        try (PreparedStatement appointmentStatement = connection.prepareStatement(appointmentQuery)) {
            appointmentStatement.setString(1, doctorUsername);
            appointmentStatement.setString(2, patientName);
            appointmentStatement.setString(3, symptoms);
            appointmentStatement.setString(4, dateOfAppointment);
            appointmentStatement.setDouble(5, consultantFees);

            return appointmentStatement.executeUpdate();
        }
    }
    public ResultSet viewPatientAppointments(String patientName) throws SQLException {
        String query = "SELECT id, doctor_username, patient_name, symptoms, date_of_appointment, consultant_fee FROM appointment_details WHERE patient_name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, patientName);
        return statement.executeQuery();
    }

    public double getConsultantFees(String doctorUsername) {
        String query = "SELECT consultantfees FROM doctor_credential WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctorUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("consultantfees");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching consultant fee.");
        }

        return 0.0;
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
