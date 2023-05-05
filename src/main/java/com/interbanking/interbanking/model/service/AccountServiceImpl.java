package com.interbanking.interbanking.model.service;


import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.EAccount;
import com.interbanking.interbanking.model.repository.IAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements IAccount {

    @Autowired
    private IAccountRepository accountRepository;

    public Account save(Account role) {
        return accountRepository.save(role);
    }

    @Override
    public Optional<Account> findByName(EAccount name) {
        return accountRepository.findByName(name);
    }
}
