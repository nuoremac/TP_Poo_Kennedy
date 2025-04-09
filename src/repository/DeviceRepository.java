package repository;
import model.ElectronicDevice;
import java.util.List;

public interface DeviceRepository {
    ElectronicDevice findDeviceBySerial(String serialNumber);
    void saveDevice(ElectronicDevice electronicDevice);
    List<ElectronicDevice> findAllStolenDevices() ;
    List<ElectronicDevice> findAllDevices();
    void updateDevice (ElectronicDevice electronicDevice) ;
}
