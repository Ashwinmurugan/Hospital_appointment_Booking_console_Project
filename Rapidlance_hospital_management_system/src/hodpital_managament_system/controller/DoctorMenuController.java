// DoctorMenuController.java
package hodpital_managament_system.controller;

import hodpital_managament_system.model.DoctorMenuModel;
import hodpital_managament_system.view.DoctorMenuView;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

public class DoctorMenuController {
    private DoctorMenuModel model;
    private DoctorMenuView view;

    public DoctorMenuController(DoctorMenuModel model, DoctorMenuView view, Scanner scanner) {
        this.model = model;
        this.view = view;
    }
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

    public void showDoctorMenu(String doctorName) {
        while (true) {
            // Display Doctor Menu options
            System.out.println("Doctor Menu:");
            System.out.println("1. View Appointment List");
            System.out.println("2. View Suggested Tablets");
            System.out.println("3. Update Patient Status");
            System.out.println("4. View Nurse Availability");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = getScanner().nextInt();
            getScanner().nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    viewAppointmentList(doctorName);
                    break;

                case 2:
                    viewSuggestedTablets();
                    break;

                case 3:
                    updateAppointmentStatus(doctorName);
                    break;

                case 4:
                    viewNurseAvailability();
                    break;

                case 5:
                    System.out.println("Exiting Doctor Menu. Goodbye!");
                    return;  // Exit the menu loop

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void viewAppointmentList(String doctorName) {
        try {
            view.displayAppointmentList(model.viewAppointmentList(doctorName));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching appointment list.");
        }
    }

    private void viewSuggestedTablets() {
        try {
            view.displaySuggestedTablets(model.viewSuggestedTablets());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching suggested tablets.");
        }
    }

    private void updateAppointmentStatus(String doctorName) {

        try{
            model.updateAppointmentStatus(doctorName, getScanner());
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }

    }


    private void viewNurseAvailability() {
        try {
            view.displayNurseAvailability(model.viewNurseAvailability());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching nurse availability.");
        }
    }

    // Other methods related to the DoctorMenuController...
}
