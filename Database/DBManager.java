package Database;

import java.sql.*;
import java.util.ArrayList;
import Codes.StudyMaterial; // Correct import statement

public class DBManager {
    private String url = "jdbc:mysql://localhost:3306/finalhour_db";
    private String username = "zachy";
    private String password = "2469";
    private Connection connection;

    // Constructor to establish the database connection
    public DBManager() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }

    // Method to create the study_materials table if it doesn't exist
    public void createStudyMaterialsTable() {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS study_materials (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    file_path VARCHAR(255) NOT NULL,
                    section VARCHAR(50) NOT NULL,
                    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Study materials table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to save a study material to the database
    public void saveStudyMaterial(String name, String filePath, String section) {
        String insertSQL = "INSERT INTO study_materials (name, file_path, section) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, filePath);
            pstmt.setString(3, section);
            pstmt.executeUpdate();
            System.out.println("Study material saved: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load all study materials from the database
    public ArrayList<StudyMaterial> loadAllStudyMaterials() {
        ArrayList<StudyMaterial> materials = new ArrayList<>();
        String selectSQL = "SELECT name, file_path, section FROM study_materials";  // Removed "saved_at"

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String filePath = rs.getString("file_path");
                String section = rs.getString("section");

                // Create StudyMaterial object without the timestamp
                StudyMaterial material = new StudyMaterial(name, filePath, section);
                materials.add(material);
                System.out.println("Loaded study material: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
