package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import model.*;
import repository.*;
import service.*;

public class ReportStolenPanel extends JPanel {
    private final DeviceService deviceService;

    // Form fields
    private JTextField serialField;
    private JComboBox<String> typeCombo;
    private JTextField brandField;
    private JTextField modelField;
    private JTextField theftDateField;
    private JTextField locationField;
    private JTextField ownerNameField;
    private JTextField ownerEmailField;
    private JTextField ownerPhoneField;

    public ReportStolenPanel(DeviceService deviceService) {
        this.deviceService = deviceService;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(0, 2, 10, 10));

        // Device Information
        add(new JLabel("Serial/IMEI Number:"));
        serialField = new JTextField();
        add(serialField);

        add(new JLabel("Device Type:"));
        typeCombo = new JComboBox<>(new String[]{"Phone", "Laptop", "Tablet", "Other"});
        add(typeCombo);

        add(new JLabel("Brand:"));
        brandField = new JTextField();
        add(brandField);

        add(new JLabel("Model:"));
        modelField = new JTextField();
        add(modelField);

        // Theft Information
        add(new JLabel("Theft Date (YYYY-MM-DD):"));
        theftDateField = new JTextField();
        add(theftDateField);

        add(new JLabel("Theft Location:"));
        locationField = new JTextField();
        add(locationField);

        // Owner Information
        add(new JLabel("Your Name:"));
        ownerNameField = new JTextField();
        add(ownerNameField);

        add(new JLabel("Your Email:"));
        ownerEmailField = new JTextField();
        add(ownerEmailField);

        add(new JLabel("Your Phone:"));
        ownerPhoneField = new JTextField();
        add(ownerPhoneField);

        // Submit button
        add(new JLabel());
        JButton submitButton = new JButton("Report as Stolen");
        submitButton.addActionListener(this::submitReport);
        add(submitButton);
    }

    private void submitReport(ActionEvent e) {
        try {
            // Validate and parse all fields
            LocalDate theftDate = LocalDate.parse(theftDateField.getText());

            // Create owner
            Owner owner = new Owner(ownerNameField.getText(), ownerEmailField.getText(),ownerPhoneField.getText());
            // Report as stolen
            deviceService.reportDeviceAsStolen(
                    serialField.getText(),
                    (String) typeCombo.getSelectedItem(),
                    brandField.getText(),
                    modelField.getText(),
                    theftDate,
                    locationField.getText(),
                    owner
            );

            JOptionPane.showMessageDialog(this, "Device reported as stolen successfully!");
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        // Clear all fields
        serialField.setText("");
        brandField.setText("");
        modelField.setText("");
        theftDateField.setText("");
        locationField.setText("");
        ownerNameField.setText("");
        ownerEmailField.setText("");
        ownerPhoneField.setText("");
    }
}