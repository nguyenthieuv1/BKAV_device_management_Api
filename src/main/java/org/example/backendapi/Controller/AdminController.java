package org.example.backendapi.Controller;

import org.example.backendapi.Dto.Account;
import org.example.backendapi.Dto.UserDto;
import org.example.backendapi.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private AccountService accountService;

    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccount() {
        List<Account> accounts = accountService.getAllAccount();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account dto) {
        accountService.createAccount(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount( @RequestBody Account dto) {
        System.out.println(dto.toString());
        accountService.updateAccount(dto);
        return ResponseEntity.ok().build();
    }

}
