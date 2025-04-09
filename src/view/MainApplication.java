package view;

import javax.swing.*;
import java.awt.*;
import service.*;
import repository.*;

public class MainApplication extends JFrame {
    private final AuthService authService ;
    private final DeviceService deviceService;

    public MainApplication(DeviceService deviceService ,AuthService authService) {
        //Initialize services
        this.deviceService = deviceService;
        this.authService = authService;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Stolen Device Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Check Device", new CheckDevicePanel(deviceService));
        tabbedPane.addTab("Report Stolen", new ReportStolenPanel(deviceService));
        tabbedPane.addTab("Admin", new AdminPanel(deviceService,authService));
        add(tabbedPane);
    }

    public static void main(String[] args) {
        // Initialize services
        DeviceRepository repository = new FileDeviceRepository();
        DeviceService service = new DeviceService(repository);
        AuthService authService = new AuthService();

        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication(service,authService);
            app.setVisible(true);
        });
    }
}
