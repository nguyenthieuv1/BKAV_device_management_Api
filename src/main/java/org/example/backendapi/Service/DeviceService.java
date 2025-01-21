package org.example.backendapi.Service;

import org.example.backendapi.Dto.Device;
import org.example.backendapi.Dto.DeviceBorrow;

import java.util.List;

public interface DeviceService extends BaseService<Device>{
     void addAccountBorrowDevice(DeviceBorrow deviceBorrow);
     List<Device> findAllByName(String name);
     void confirmDeviceReturn(DeviceBorrow deviceBorrow);
}
