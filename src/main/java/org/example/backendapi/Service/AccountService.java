package org.example.backendapi.Service;

import org.example.backendapi.Dto.*;

import java.util.List;

public interface AccountService extends BaseService<Account> {

    String generateToken(UserDto dto);
    Account getAccountByUsername(String username);
    List<Device> getDevicesByAccount();
    void returnDevice(ListDeviceReturn deviceReturns);
    void changePassword(ChangePassword changePassword);
}
