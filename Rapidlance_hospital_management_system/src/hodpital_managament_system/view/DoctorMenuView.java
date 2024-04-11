package hodpital_managament_system.view;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DoctorMenuView {
    public void displayAppointmentList(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Display appointment list header
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s | ", metaData.getColumnName(i));
        }
        System.out.println();

        // Display appointment list data
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s | ", resultSet.getString(i));
            }
            System.out.println();
        }
    }

    public void displaySuggestedTablets(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Display suggested tablets header
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s | ", metaData.getColumnName(i));
        }
        System.out.println();

        // Display suggested tablets data
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s | ", resultSet.getString(i));
            }
            System.out.println();
        }
    }

    public void displayNurseAvailability(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Display nurse availability header
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s | ", metaData.getColumnName(i));
        }
        System.out.println();

        // Display nurse availability data
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s | ", resultSet.getString(i));
            }
            System.out.println();
        }
    }

    // Other methods related to the DoctorMenuView...
}
