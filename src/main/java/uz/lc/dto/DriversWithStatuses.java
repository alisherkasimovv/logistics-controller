package uz.lc.dto;

import java.util.ArrayList;
import java.util.List;

public class DriversWithStatuses {

    private int numberOfFreeDrivers;
    private int numberOfNonFreeDrivers;
    private List<DriverDTO> freeDrivers;
    private List<DriverDTO> nonFreeDrivers;

    public DriversWithStatuses() {
        freeDrivers = new ArrayList<>();
        nonFreeDrivers = new ArrayList<>();
    }

    public int getNumberOfFreeDrivers() {
        return numberOfFreeDrivers;
    }

    public void setNumberOfFreeDrivers(int numberOfFreeDrivers) {
        this.numberOfFreeDrivers = numberOfFreeDrivers;
    }

    public int getNumberOfNonFreeDrivers() {
        return numberOfNonFreeDrivers;
    }

    public void setNumberOfNonFreeDrivers(int numberOfNonFreeDrivers) {
        this.numberOfNonFreeDrivers = numberOfNonFreeDrivers;
    }

    public List<DriverDTO> getFreeDrivers() {
        return freeDrivers;
    }

    public void setFreeDrivers(DriverDTO freeDriver) {
        this.freeDrivers.add(freeDriver);
    }

    public List<DriverDTO> getNonFreeDrivers() {
        return nonFreeDrivers;
    }

    public void setNonFreeDrivers(DriverDTO nonFreeDriver) {
        this.nonFreeDrivers.add(nonFreeDriver);
    }
}
