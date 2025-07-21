import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ParkingSystem extends JFrame {
    JTextField vehicleNumberField;
    JComboBox<String> vehicleTypeBox;
    JTextArea outputArea;

    public ParkingSystem() {
        setTitle("🚗 Parking Management System");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Vehicle Entry"));

        inputPanel.add(new JLabel("Vehicle Number:"));
        vehicleNumberField = new JTextField();
        inputPanel.add(vehicleNumberField);

        inputPanel.add(new JLabel("Vehicle Type:"));
        vehicleTypeBox = new JComboBox<>(new String[]{"Car", "Bike", "Truck"});
        inputPanel.add(vehicleTypeBox);

        JButton parkBtn = new JButton("🚙 Park Vehicle");
        JButton exitBtn = new JButton("⬅️ Vehicle Exit");
        JButton viewBtn = new JButton("📄 View Parked Vehicles");
        JButton clearBtn = new JButton("🧹 Clear Fields");

        inputPanel.add(parkBtn);
        inputPanel.add(exitBtn);
        inputPanel.add(viewBtn);
        inputPanel.add(clearBtn);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        parkBtn.addActionListener(e -> parkVehicle());
        exitBtn.addActionListener(e -> vehicleExit());
        viewBtn.addActionListener(e -> viewVehicles());
        clearBtn.addActionListener(e -> clearFields());

        setVisible(true);
    }

    void parkVehicle() {
        String number = vehicleNumberField.getText().trim();
        String type = (String) vehicleTypeBox.getSelectedItem();

        if (number.isEmpty()) {
            outputArea.setText("⚠️ Vehicle number cannot be empty.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO parking_slots(vehicle_number, vehicle_type) VALUES (?, ?)")) {

            stmt.setString(1, number);
            stmt.setString(2, type);
            stmt.executeUpdate();
            outputArea.setText("✅ Vehicle Parked Successfully!");

        } catch (SQLException ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    void vehicleExit() {
        String number = vehicleNumberField.getText().trim();

        if (number.isEmpty()) {
            outputArea.setText("⚠️ Please enter vehicle number to exit.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE parking_slots SET out_time = CURRENT_TIMESTAMP WHERE vehicle_number = ? AND out_time IS NULL")) {

            stmt.setString(1, number);
            int updated = stmt.executeUpdate();
            outputArea.setText(updated > 0 ? "🚪 Vehicle Exit Recorded." : "❌ No active vehicle found.");

        } catch (SQLException ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    void viewVehicles() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM parking_slots WHERE out_time IS NULL")) {

            StringBuilder sb = new StringBuilder("📋 Parked Vehicles:
");
            while (rs.next()) {
                sb.append("🆔 ").append(rs.getInt("slot_id"))
                  .append(" | ").append(rs.getString("vehicle_number"))
                  .append(" (").append(rs.getString("vehicle_type")).append(")
");
            }
            outputArea.setText(sb.toString());

        } catch (SQLException ex) {
            outputArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    void clearFields() {
        vehicleNumberField.setText("");
        vehicleTypeBox.setSelectedIndex(0);
        outputArea.setText("");
    }
}
