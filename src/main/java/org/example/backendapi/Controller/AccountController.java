package org.example.backendapi.Controller;

import org.example.backendapi.Dto.Account;
import org.example.backendapi.Dto.UserDto;
import org.example.backendapi.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto dto) {
        String token = accountService.generateToken(dto);
        return ResponseEntity.ok(token);
    }


}
