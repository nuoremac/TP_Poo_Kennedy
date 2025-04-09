package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import service.*;
import model.*;

public class CheckDevicePanel extends JPanel {
    private final DeviceService deviceService;
    private JTextField serialField;
    private JTextArea resultArea;

    public CheckDevicePanel(DeviceService deviceService) {
        this.deviceService = deviceService;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Enter Device Serial/IMEI:"));
        serialField = new JTextField(20);
        inputPanel.add(serialField);

        JButton checkButton = new JButton("Check Status");
        checkButton.addActionListener(this::checkDevice);
        inputPanel.add(checkButton);

        add(inputPanel, BorderLayout.NORTH);

        // Results area
        resultArea = new JTextArea(10, 50);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    private void checkDevice(ActionEvent e) {
        String serialNumber = serialField.getText().trim();

        if (serialNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a serial number");
            return;
        }

        boolean isStolen = deviceService.checkIfStolen(serialNumber);

        if (isStolen) {
            resultArea.setText("WARNING: This device has been reported as stolen!\n\n");
            ElectronicDevice device = deviceService.getDeviceDetails(serialNumber);
            resultArea.append("Device Type: " + device.getDeviceType() + "\n");
            resultArea.append("Brand/Model: " + device.getBrand() + " " + device.getModel() + "\n");
            resultArea.append("Reported Stolen On: " + device.getTheftDate() + "\n");
            resultArea.append("Location: " + device.getTheftLocation() + "\n");
            resultArea.append("\nIf found, please contact owner:\n");
            resultArea.append("Name: " + device.getOwner().getName() + "\n");
            resultArea.append("Contact: " + device.getOwner().getPhoneNumber());
        } else {
            resultArea.setText("This device is not reported as stolen in our database.");
        }
    }
}