package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.EAccount;

import java.util.Optional;

public interface IAccount {
    Optional<Account> findByName(EAccount name);
}
