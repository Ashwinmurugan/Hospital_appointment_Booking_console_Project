package hodpital_managament_system.controller;

import hodpital_managament_system.model.UserMenuModel;
import hodpital_managament_system.view.UserMenuView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMenuController {
    private UserMenuModel model;
    private UserMenuView view;
    private Scanner scanner;
    private Connection connection; // You need to declare the connection

    public UserMenuController(UserMenuModel model, UserMenuView view, Scanner scanner, Connection connection) {
        this.model = model;
        this.view = view;
        this.scanner = scanner;
        this.connection = connection;
    }

    public void showUserMenu() {
        while (true) {
            // Display User Menu options
            System.out.println("User Menu:");
            System.out.println("1. View Doctor Availability");
            System.out.println("2. Book Appointment");
            System.out.println("3. View Patient Appointments");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    viewDoctorAvailability();
                    break;
                case 2:
                    bookAppointment();
                    break;
                case 3:
                    viewPatientAppointments();
                    break;
                case 4:
                    System.out.println("Exiting User Menu... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void viewDoctorAvailability() {
        try {
            view.displayDoctorAvailability(model.viewDoctorAvailability());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching doctor availability.");
        }
    }

    private void bookAppointment() {
        System.out.print("Enter Doctor's Username for Booking Appointment: ");
        String doctorUsername = scanner.nextLine();

        if (!model.doctorExists(doctorUsername)) {
            System.out.println("Doctor with username '" + doctorUsername + "' does not exist.");
            return;
        }

        System.out.print("Enter patient Name for confirmation: ");
        String patientName = scanner.nextLine();

        System.out.print("Do you want to book an appointment with " + doctorUsername + "? (yes/no): ");
        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes")) {
            System.out.print("Enter Symptoms: ");
            String symptoms = scanner.nextLine();

            System.out.print("Enter Date of Appointment (YYYY-MM-DD): ");
            String dateOfAppointmentString = scanner.nextLine();

            try {
                java.sql.Date dateOfAppointment = java.sql.Date.valueOf(dateOfAppointmentString);

                java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
                if (dateOfAppointment.before(today)) {
                    System.out.println("Invalid date. Please enter a date today or in the future.");
                    return;
                }

                double consultantFees = model.getConsultantFees(doctorUsername);

                System.out.println("Appointment Details:");
                System.out.println("Doctor: " + doctorUsername);
                System.out.println("Patient: " + patientName);
                System.out.println("Symptoms: " + symptoms);
                System.out.println("Date of Appointment: " + dateOfAppointment);
                System.out.println("Consultant Fee: " + consultantFees);

                String appointmentQuery = "INSERT INTO appointment_details (doctor_username, patient_name, symptoms, date_of_appointment, consultant_fee, status) VALUES (?, ?, ?, ?, ?, 'Not Treated')";

                try (PreparedStatement appointmentStatement = connection.prepareStatement(appointmentQuery)) {
                    appointmentStatement.setString(1, doctorUsername);
                    appointmentStatement.setString(2, patientName);
                    appointmentStatement.setString(3, symptoms);
                    appointmentStatement.setDate(4, dateOfAppointment);
                    appointmentStatement.setDouble(5, consultantFees);

                    int rowsAffected = appointmentStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Appointment details stored successfully.");
                    } else {
                        System.out.println("Failed to store appointment details.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred during appointment booking.");
                }

                System.out.println("Appointment booked successfully. Thank you!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
            }
        } else {
            System.out.println("Appointment booking canceled.");
        }
    }

    private void viewPatientAppointments() {
        System.out.print("Enter patient Name: ");
        String patientName = scanner.nextLine();

        try {
            view.displayPatientAppointments(model.viewPatientAppointments(patientName));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching patient appointments.");
        }
    }
}
