package hodpital_managament_system.view;

import java.util.Scanner;
public class AuthenticationView {
    private Scanner scanner;

    public AuthenticationView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayLoginSuccess(String username) {
        System.out.println("Login successful. Welcome, " + username);
    }

    public void displayLoginFailure() {
        System.out.println("Invalid username, email, or password. Please try again.");
    }

    public void displayRegistrationSuccess(String username) {
        System.out.println("Registration successful. Welcome, " + username);
    }

    public void displayRegistrationFailure() {
        System.out.println("Registration failed. Please try again.");
    }

    public String getUsername() {
        System.out.println("Enter Username: ");
        return scanner.nextLine();
    }

    public String getEmail() {
        System.out.println("Enter Email: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("Enter Password: ");
        return scanner.nextLine();
    }

    public String getGender() {
        System.out.println("Enter Gender: ");
        return scanner.nextLine();
    }

    public String getDob() {
        System.out.println("Enter DOB (YYYY/MM/DD): ");
        return scanner.nextLine();
    }

    public String getMobileNo() {
        System.out.println("Enter Mobile Number: ");
        return scanner.nextLine();
    }

    public String getCity() {
        System.out.println("Enter City: ");
        return scanner.nextLine();
    }

    public String getSpecialization() {
        System.out.println("Enter Specialization: ");
        return scanner.nextLine();
    }

    public String getConsultantFees() {
        System.out.println("Enter Consultant Fees: ");
        return scanner.nextLine();
    }
}
