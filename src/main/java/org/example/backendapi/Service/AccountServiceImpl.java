package org.example.backendapi.Service;


import org.example.backendapi.DAO.Dao;
import org.example.backendapi.Dto.Account;
import org.example.backendapi.Dto.UserDto;
import org.example.backendapi.Entity.AccountEntity;
import org.example.backendapi.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final Dao<Account> accountDao;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(Dao<Account> accountDao, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createAccount(Account dto) {
        String passwordEncode = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(passwordEncode);
        accountDao.save(dto);
    }

    @Override
    public void updateAccount(Account dto) {
        String passwordEncode = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(passwordEncode);
        accountDao.save(dto);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = new Account();
        account.setId(id);
        accountDao.delete(account);
    }

    @Override
    public List<Account> getAllAccount() {
       return accountDao.getAll();
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> account = accountDao.get(id);
        return account.orElse(null);
    }

    @Override
    public String generateToken(UserDto dto) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword())
        );
        if (authentication.isAuthenticated()){
            return jwtUtil.generateToken(dto.getUsername());
        }
        throw new RuntimeException("Authentication failed!!!!!!!!!!!!!!!!!!!!");
    }
}
