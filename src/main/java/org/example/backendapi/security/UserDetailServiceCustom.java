package org.example.backendapi.security;

import org.example.backendapi.DAO.AccountDao;
import org.example.backendapi.Dto.Account;
import org.example.backendapi.Entity.AccountEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class UserDetailServiceCustom implements UserDetailsService {
    private AccountDao accountDao;

    public UserDetailServiceCustom(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountDao.get(username);
        if (accountOptional.isPresent()) {
            UserDetailCustom userDetailCustom = new UserDetailCustom();
            userDetailCustom.setAccount(accountOptional.get());
            return userDetailCustom;
        }
        throw new UsernameNotFoundException("không tìm thấy: "+username);
    }
}
