package com.interbanking.interbanking;

import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.entity.EAccount;
import com.interbanking.interbanking.model.entity.Transfer;
import com.interbanking.interbanking.model.service.CompanyServiceImpl;
import com.interbanking.interbanking.model.service.TransferServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;

import com.interbanking.interbanking.model.service.AccountServiceImpl;


import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static com.interbanking.interbanking.utils.SiteLocale.getLastMonth;

@Slf4j
@Configuration
class LoadDatabase {

    @Autowired
    CommandLineRunner initDatabase(
            AccountServiceImpl accountService,
            CompanyServiceImpl companyService,
            TransferServiceImpl transferService
    ) {
        final Account debit = createAccountIfNotFound(accountService, EAccount.DEBIT_ACCOUNT);
        final Account credit = createAccountIfNotFound(accountService, EAccount.CREDIT_CCOUNT);

        final Company company = createCompanyIfNotFound(companyService, "20297453114", "prueba1", new Date());
        final Company company2 = createCompanyIfNotFound(companyService, "20297453124", "prueba2", new Date());
        final Company company3 = createCompanyIfNotFound(companyService, "20297453134", "prueba3", new Date());
        final Company company4 = createCompanyIfNotFound(companyService, "20297453144", "prueba4", new Date());

        final Transfer transfer = createTransferIfNotFound(transferService, debit, 300.00, getLastMonth(1).getTime(), Collections.singleton(company));
        final Transfer transfer1 = createTransferIfNotFound(transferService, debit, 1300.00, getLastMonth(1).getTime(), Collections.singleton(company2));
        final Transfer transfer2 = createTransferIfNotFound(transferService, credit, 1500.00, getLastMonth(1).getTime(), Collections.singleton(company3));
        final Transfer transfer3 = createTransferIfNotFound(transferService, debit, 3500.00, getLastMonth(1).getTime(), Collections.singleton(company4));
        final Transfer transfer4 = createTransferIfNotFound(transferService, credit, 200.00, getLastMonth(1).getTime(), Collections.singleton(company));

        return null;
    }

    @Transactional
    Transfer createTransferIfNotFound(
            TransferServiceImpl transferService,
            Account account,
            Double amount,
            Date transferDate,
            Set<Company> companyId
    ) {
        Transfer transfer = new Transfer(account, amount, transferDate, companyId);
        transferService.save(transfer);

        return transfer;
    }

    @Transactional
    Company createCompanyIfNotFound(CompanyServiceImpl companyService, String cuit, String businessName, Date date) {
        Company company = companyService.findByCuit(cuit).orElse(null);
        if (company == null) {
            company = new Company(cuit, businessName, date);
            companyService.save(company);
        }
        return company;
    }

    @Transactional
    Account createAccountIfNotFound(AccountServiceImpl accountService, EAccount name) {
        Account account = accountService.findByName(name).orElse(null);
        if (account == null) {
            account = new Account(name);
            accountService.save(account);
        }
        return account;
    }


}
