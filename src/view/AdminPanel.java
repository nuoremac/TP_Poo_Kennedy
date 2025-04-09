package view;

import model.ElectronicDevice;
import service.DeviceService;
import service.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminPanel extends JPanel {
    private final DeviceService deviceService;
    private final AuthService authService ;
    private JTable devicesTable;
    private DefaultTableModel tableModel;

    public AdminPanel(DeviceService deviceService,AuthService authService) {
        this.deviceService = deviceService;
        this.authService = authService ;

        LoginDialog loginDialog = new LoginDialog(
                (Frame)SwingUtilities.getWindowAncestor(this),
                authService
        );

        loginDialog.setVisible(true);

        if (loginDialog.isAuthenticated()) {
            initComponents();
            loadStolenDevices();
        } else {
            showAccessDenied();
        }
        initComponents();
        loadStolenDevices();
    }

    private void showAccessDenied() {
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Access Denied - Please contact system administrator",
                JLabel.CENTER), BorderLayout.CENTER);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Create table model
        String[] columnNames = {"Serial", "Type", "Brand", "Model", "Stolen Date", "Location"};
        tableModel = new DefaultTableModel(columnNames, 0);
        devicesTable = new JTable(tableModel);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(e -> loadStolenDevices());

        JButton exportButton = new JButton("Export to CSV");
        exportButton.addActionListener(this::exportToCSV);

        JButton clearAllButton = new JButton("Clear All");
        clearAllButton.addActionListener(this::clearAllDevices);
        clearAllButton.setForeground(Color.RED);

        buttonPanel.add(refreshButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(clearAllButton);

        add(new JScrollPane(devicesTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadStolenDevices() {
        try {
            tableModel.setRowCount(0); // Clear existing data
            List<ElectronicDevice> stolenDevices = deviceService.findAllStolenDevices();
            if(stolenDevices.isEmpty()){
                JOptionPane.showMessageDialog(this, "No stolen devices found");
            }else {
                for (ElectronicDevice device : stolenDevices) {
                    Object[] rowData = {
                            device.getSerialNumber(),
                            device.getDeviceType(),
                            device.getBrand(),
                            device.getModel(),
                            device.getTheftDate(),
                            device.getTheftLocation()
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading devices: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportToCSV(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Implement CSV export logic here
            // You would write the table data to a CSV file
            JOptionPane.showMessageDialog(this, "Export functionality to be implemented");
        }
    }

    private void clearAllDevices(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to clear ALL device records?",
                "Confirm Clear",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Implement clear all functionality
            JOptionPane.showMessageDialog(this, "Clear all functionality to be implemented");
        }
    }
}