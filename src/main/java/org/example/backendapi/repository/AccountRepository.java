package org.example.backendapi.repository;

import org.example.backendapi.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    AccountEntity findByUsername(String username);


}
