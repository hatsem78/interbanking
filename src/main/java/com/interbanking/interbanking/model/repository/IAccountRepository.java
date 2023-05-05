package com.interbanking.interbanking.model.repository;

import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.EAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByName(EAccount name);
}
