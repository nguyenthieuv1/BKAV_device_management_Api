package org.example.backendapi.Service.impl;


import org.example.backendapi.DAO.AccountDao;
import org.example.backendapi.DAO.Dao;
import org.example.backendapi.Dto.*;
import org.example.backendapi.Service.AccountService;
import org.example.backendapi.security.UserDetailCustom;
import org.example.backendapi.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountDao accountDao, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public Account getAccountByUsername(String username) {
        Optional<Account> account = accountDao.get(username);
        if (account.isPresent()){
            return account.get();
        }
        throw new RuntimeException("Account not found!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public List<Device> getDevicesByAccount() {
        String username = getAccount().getUsername();
        return accountDao.getDevicesBorrowedByAccount(username);
    }

    @Override
    public void returnDevice(ListDeviceReturn deviceReturns) {
        String username = getAccount().getUsername();
        accountDao.requestDeviceBorrowedByAccount(username,deviceReturns);
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        String username = getAccount().getUsername();
        accountDao.changePassword(username,changePassword);
    }

    public UserDetailCustom getAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailCustom){
            return (UserDetailCustom) authentication.getPrincipal();
        }
        throw new RuntimeException("authentication null !!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void create(Account dto) {
        saveOrUpdate(dto);
    }

    public void saveOrUpdate(Account dto) {
        String passwordEncode = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(passwordEncode);
        accountDao.save(dto);
    }

    @Override
    public void update(Account dto) {
        saveOrUpdate(dto);
    }

    @Override
    public void delete(Long id) {
        Account account = new Account();
        account.setId(id);
        accountDao.delete(account);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.getAll();
    }

    @Override
    public Account findById(int id) {
        Optional<Account> account = accountDao.get(id);
        return account.orElse(null);
    }

    @Override
    public Account findByName(String name) {
        return null;
    }
}
