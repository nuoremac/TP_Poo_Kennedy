package repository;

import model.ElectronicDevice;
import model.Owner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDeviceRepository implements DeviceRepository {
    private static final String FILE_PATH = "data/devices.json";
    private final Gson gson = new Gson();

    @Override
    public void saveDevice(ElectronicDevice device) {
        List<ElectronicDevice> devices = findAllDevices();
        // Remove existing device with same serial number if it exists
        devices.removeIf(d -> d.getSerialNumber().equals(device.getSerialNumber()));
        devices.add(device);
        saveAllDevices(devices);
    }

    @Override
    public ElectronicDevice findDeviceBySerial(String serialNumber) {
        return findAllDevices().stream()
                .filter(d -> d.getSerialNumber().equals(serialNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ElectronicDevice> findAllStolenDevices() {
        return findAllDevices().stream()
                .filter(ElectronicDevice::getIsStolen)
                .collect(Collectors.toList());
    }

    @Override
    public List<ElectronicDevice> findAllDevices() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            List<ElectronicDevice> devices = gson.fromJson(reader,
                    new TypeToken<List<ElectronicDevice>>(){}.getType());
            return devices != null ? devices : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void updateDevice(ElectronicDevice updatedDevice) {
        List<ElectronicDevice> devices = findAllDevices();
        boolean found = false;

        for (int i = 0; i < devices.size(); i++) {
            if (devices.get(i).getSerialNumber().equals(updatedDevice.getSerialNumber())) {
                devices.set(i, updatedDevice);
                found = true;
                break;
            }
        }

        if (!found) {
            devices.add(updatedDevice);
        }

        saveAllDevices(devices);
    }

    private void saveAllDevices(List<ElectronicDevice> devices) {
        // Create data directory if it doesn't exist
        new File("data").mkdirs();

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(devices, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}