package org.example.backendapi.Controller;

import org.example.backendapi.Dto.Device;
import org.example.backendapi.Dto.ListDeviceReturn;
import org.example.backendapi.Service.AccountService;
import org.example.backendapi.Service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private DeviceService deviceService;
    private AccountService accountService;

    public UserController(DeviceService deviceService, AccountService accountService) {
        this.deviceService = deviceService;
        this.accountService = accountService;
    }

    @GetMapping("/devices")
    public ResponseEntity<?> getDevicesOwned(){
        List<Device> devices = accountService.getDevicesByAccount();
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/return-device")
    public ResponseEntity<?> returnDevice(@RequestBody ListDeviceReturn devices){
        accountService.returnDevice(devices);
        return ResponseEntity.ok().build();
    }
}
