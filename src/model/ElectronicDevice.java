package model;
import java.time.LocalDate;


public class ElectronicDevice {
    private  String serialNumber;
    private  String deviceType ;
    private String brand ;
    private String model ;
    private  boolean isStolen ;
    private LocalDate theftDate ;
    private String theftLocation ;
    private Owner owner ;

    //constructors
    public ElectronicDevice (String serialNumber,String deviceType,String brand ,String model){
        this.brand=brand;
        this.deviceType=deviceType;
        this.model=model;
        this.serialNumber = serialNumber;
        this.isStolen=false;
    }

    //Getters

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean getIsStolen() {
        return isStolen;
    }

    public LocalDate getTheftDate() {
        return theftDate;
    }
    public String getTheftLocation(){
        return theftLocation;
    }
    public Owner getOwner(){
        return  owner;
    }

    //setters

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setTheftDate(LocalDate theftDate) {
        this.theftDate = theftDate;
    }

    public void setTheftLocation(String theftLocation) {
        this.theftLocation = theftLocation;
    }

    public void setStolen(boolean stolen) {
        isStolen = stolen;
    }
    //Business method
    public void markAsStolen (LocalDate theftDate,String theftLocation, Owner owner){
        this.isStolen=true;
        this.theftDate=theftDate;
        this.owner=owner;
        this.theftLocation=theftLocation;
    }

}
