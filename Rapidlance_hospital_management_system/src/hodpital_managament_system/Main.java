package hodpital_managament_system;

import hodpital_managament_system.controller.DoctorMenuController;
import hodpital_managament_system.controller.UserMenuController;
import hodpital_managament_system.model.DoctorMenuModel;
import hodpital_managament_system.model.UserMenuModel;
import hodpital_managament_system.view.DoctorMenuView;
import hodpital_managament_system.view.UserMenuView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "password");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your role (doctor/user): ");
            String role = scanner.nextLine().toLowerCase();

            if ("doctor".equals(role)) {
                DoctorMenuModel model = new DoctorMenuModel(connection);
                DoctorMenuView view = new DoctorMenuView();
                DoctorMenuController controller = new DoctorMenuController(model, view, scanner);

                System.out.print("Enter Doctor's Name: ");
                String doctorName = scanner.nextLine();

                controller.showDoctorMenu(doctorName);
            } else if ("user".equals(role)) {
                UserMenuModel model = new UserMenuModel(connection);
                UserMenuView view = new UserMenuView();
                UserMenuController controller = new UserMenuController(model, view, scanner, connection);

                controller.showUserMenu();
            } else {
                System.out.println("Invalid role. Please specify 'doctor' or 'user'.");
            }

            connection.close();
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database.");
        }
    }
}
