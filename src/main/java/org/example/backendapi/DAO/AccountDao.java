package org.example.backendapi.DAO;

import org.example.backendapi.Dto.Account;
import org.example.backendapi.Dto.ChangePassword;
import org.example.backendapi.Dto.Device;
import org.example.backendapi.Dto.ListDeviceReturn;

import java.util.List;

public interface AccountDao extends Dao<Account>{
    List<Device> getDevicesBorrowedByAccount(String username);
    void requestDeviceBorrowedByAccount(String username, ListDeviceReturn listDeviceReturn);
    void changePassword(String username, ChangePassword changePassword);
}
