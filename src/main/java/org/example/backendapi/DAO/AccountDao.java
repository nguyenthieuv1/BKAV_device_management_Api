package org.example.backendapi.DAO;

import org.example.backendapi.Dto.Account;
import org.example.backendapi.Entity.AccountEntity;
import org.example.backendapi.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
//@Data
public class AccountDao implements Dao<Account> {

    private final AccountRepository accountRepository;

    public AccountDao(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> get(long id) {
        Optional<AccountEntity> accountEntity =  accountRepository.findById(id);
        if (accountEntity.isPresent()) {
            Account account = accountEntity.get().toDto();
            return Optional.of(account);
        }
         throw new RuntimeException("Account not found");
    }

    @Override
    public Optional<Account> get(String keyWord) {
        AccountEntity accountEntity = accountRepository.findByUsername(keyWord);
        Account account = accountEntity.toDto();
        return Optional.of(account);
    }

    @Override
    public List<Account> getAll() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<Account> accounts = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            accounts.add(accountEntity.toDto());
        }
        return accounts;
    }

    @Override
    public void save(Account accountDto) {
        AccountEntity accountEntity = accountDto.toEntity();
        accountRepository.save(accountEntity);
    }


    @Override
    public void update(Account account, String[] params) {
        AccountEntity accountEntity = account.toEntity();
        accountRepository.save(accountEntity);
    }

    @Override
    public void delete(Account account) {
        Long id = account.getId();
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        if (accountEntity.isPresent()) {
            accountRepository.delete(accountEntity.get());
        }
        else{
            throw new RuntimeException("Account not found");
        }
    }



}
