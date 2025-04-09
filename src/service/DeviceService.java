package service;

import model.ElectronicDevice;
import model.Owner;
import repository.DeviceRepository;

import java.time.LocalDate;
import java.util.List;

public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    public List<ElectronicDevice> findAllStolenDevices(){
        return deviceRepository.findAllStolenDevices();
    }

    public boolean checkIfStolen(String serialNumber) {
        ElectronicDevice device = deviceRepository.findDeviceBySerial(serialNumber);
        return device != null && device.getIsStolen();
    }

    public void reportDeviceAsStolen(String serialNumber, String deviceType, String brand,
                                     String model, LocalDate theftDate, String theftLocation,
                                     Owner owner) {
        ElectronicDevice device = deviceRepository.findDeviceBySerial(serialNumber);

        if (device == null) {
            device = new ElectronicDevice(serialNumber, deviceType, brand, model);
        }

        device.markAsStolen(theftDate, theftLocation, owner);
        deviceRepository.saveDevice(device);
    }

    public ElectronicDevice getDeviceDetails(String serialNumber) {
        return deviceRepository.findDeviceBySerial(serialNumber);
    }
}
