package hodpital_managament_system.view;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMenuView {
    public void displayDoctorAvailability(ResultSet resultSet) throws SQLException {
        System.out.println("Doctor Availability:");
        System.out.println("----------------------+----------------------+----------------------+------------------");
        System.out.printf("%-20s | %-20s | %-20s%n", "Doctor Username", "Specialization", "Consultant Fees");
        System.out.println("----------------------+----------------------+----------------------+------------------");

        while (resultSet.next()) {
            String doctorUsername = resultSet.getString("username");
            String specialization = resultSet.getString("specialization");
            double consultantFees = resultSet.getDouble("consultantfees");

            System.out.printf("%-20s | %-20s | %-20.2f%n", doctorUsername, specialization, consultantFees);
            System.out.println("----------------------+----------------------+----------------------+------------------");
        }
    }

    public void displayAppointmentBookingStatus(int rowsAffected) {
        if (rowsAffected > 0) {
            System.out.println("Appointment booked successfully. Thank you!");
        } else {
            System.out.println("Failed to book appointment. Please try again.");
        }
    }

    public void displayPatientAppointments(ResultSet resultSet) throws SQLException {
        System.out.println("Patient Appointments:");
        System.out.println("----------------------+----------------------+----------------------+----------------------+------------------+------------------------");
        System.out.printf("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n", "Appointment ID", "Doctor Username", "Patient Name", "Symptoms", "Date of Appointment", "Consultant Fee");
        System.out.println("----------------------+----------------------+----------------------+----------------------+------------------+------------------------");

        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("id");
            String doctorUsername = resultSet.getString("doctor_username");
            String patientName = resultSet.getString("patient_name");
            String symptoms = resultSet.getString("symptoms");
            String dateOfAppointment = resultSet.getString("date_of_appointment");
            double consultantFee = resultSet.getDouble("consultant_fee");

            System.out.printf("%-20d | %-20s | %-20s | %-20s | %-20s | %-20.2f%n", appointmentId, doctorUsername, patientName, symptoms, dateOfAppointment, consultantFee);
            System.out.println("----------------------+----------------------+----------------------+----------------------+------------------+------------------------");
        }
    }
}
