package org.example.backendapi.Controller;

import org.example.backendapi.Dto.Account;
import org.example.backendapi.Dto.Device;
import org.example.backendapi.Dto.DeviceBorrow;
import org.example.backendapi.Dto.UserDto;
import org.example.backendapi.Service.AccountService;
import org.example.backendapi.Service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private AccountService accountService;
    private DeviceService deviceService;

    public AdminController(AccountService accountService, DeviceService deviceService) {
        this.accountService = accountService;
        this.deviceService = deviceService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccount() {
        List<Account> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account dto) {
        accountService.create(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(
            @PathVariable Long id,
            @RequestBody Account dto
    ) {
        dto.setId(id);
        accountService.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccountByUsername(@RequestParam String username) {
        return ResponseEntity.ok(accountService.getAccountByUsername(username));
    }

   @PostMapping("/devices")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        deviceService.create(device);
        return ResponseEntity.ok().build();
   }

   @PutMapping("/devices/{id}")
    public ResponseEntity<?> updateDevice(
            @PathVariable Long id,
            @RequestBody Device device
   ) {
        device.setId(id);
        deviceService.update(device);
        return ResponseEntity.ok().build();
   }

   @DeleteMapping("/devices/{id}")
    public ResponseEntity<?> deleteDevice(
            @PathVariable Long id
   ) {
        deviceService.delete(id);
        return ResponseEntity.ok().build();
   }

   @PostMapping("/devices-borrow")
    public ResponseEntity<?> borrowDevice(
           @RequestBody DeviceBorrow deviceBorrow
           ) {
        deviceService.addAccountBorrowDevice(deviceBorrow);
       System.out.println(deviceBorrow);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/device")
    public ResponseEntity<?> getDeviceByName(@RequestParam String name) {
        List<Device> devices = deviceService.findAllByName(name);
        return ResponseEntity.ok(devices);
    }
    @PutMapping("/confirm-device-return")
    public ResponseEntity<?> confirmDeviceReturn(@RequestBody DeviceBorrow deviceBorrow) {
        deviceService.confirmDeviceReturn(deviceBorrow);
        return ResponseEntity.ok().build();
    }

}
