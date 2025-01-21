package org.example.backendapi.DAO;

import org.example.backendapi.Dto.Device;
import org.example.backendapi.Dto.DeviceBorrow;

import java.util.List;

public interface DeviceDao extends Dao<Device>{
    void addDeviceBorrowed(DeviceBorrow deviceBorrow);
    List<Device> getDevicesByName(String deviceName);
    void DeleteDeviceBorrowed(DeviceBorrow deviceBorrow);

}
