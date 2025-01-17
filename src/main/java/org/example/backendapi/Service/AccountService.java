package org.example.backendapi.Service;

import org.example.backendapi.Dto.Account;
import org.example.backendapi.Dto.UserDto;

import java.util.List;

public interface AccountService {
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Long id);
    List<Account> getAllAccount();
    Account getAccountById(Long id);
    String generateToken(UserDto dto);

}
