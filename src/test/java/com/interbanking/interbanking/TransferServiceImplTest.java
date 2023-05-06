package com.interbanking.interbanking;

import com.interbanking.interbanking.model.entity.Account;
import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.entity.EAccount;
import com.interbanking.interbanking.model.entity.Transfer;
import com.interbanking.interbanking.model.service.AccountServiceImpl;
import com.interbanking.interbanking.model.service.CompanyServiceImpl;
import com.interbanking.interbanking.model.service.TransferServiceImpl;
import com.interbanking.interbanking.utils.SiteLocale;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceImplTest {

    @MockBean
    private TransferServiceImpl transferService;

    @MockBean
    private CompanyServiceImpl companyService;

    @MockBean
    private AccountServiceImpl accountService;

    @Before
    public void setUp() throws Exception {
        this.transferService = Mockito.mock(TransferServiceImpl.class);
        this.companyService = Mockito.mock(CompanyServiceImpl.class);
        this.accountService = Mockito.mock(AccountServiceImpl.class);
    }

    @Test
    public void saveTransferTest() {

        when(companyService.save(any(Company.class))).then(new Answer<Company>() {
            Long secuencia = 8L;

            @Override
            public Company answer(InvocationOnMock invocation) {
                Company company = invocation.getArgument(0);
                company.setId(secuencia++);
                return company;
            }
        });

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        Set<Company> setCompany = Collections.singleton(companyService.save(company));

        when(accountService.save(any(Account.class))).then(new Answer<Account>() {
            Integer secuencia = 8;

            @Override
            public Account answer(InvocationOnMock invocation) throws Throwable {
                Account account = invocation.getArgument(0);
                account.setId(secuencia++);
                return account;
            }
        });

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        accountService.save(account);

        when(transferService.save(any(Transfer.class))).then(new Answer<Transfer>() {
            Long secuencia = 8L;

            @Override
            public Transfer answer(InvocationOnMock invocation) throws Throwable {
                Transfer transfer = invocation.getArgument(0);
                transfer.setId(secuencia++);
                return transfer;
            }
        });

        Transfer transfer = new Transfer(
                account,
                300.00,
                new Date(),
                setCompany
        );


        Transfer result = transferService.save(transfer);


        assertEquals(result, transfer);
    }

    @Test
    public void findByDateToCompanyIdTest() {

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        when(companyService.findById(company.getId())).thenReturn(Optional.of(company));

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        when(accountService.findByName(EAccount.DEBIT_ACCOUNT)).thenReturn(Optional.of(account));

        Date date = new Date();

        Transfer transfer = new Transfer(
                account,
                300.00,
                date,
                Collections.singleton(company)
        );

        when(transferService.findByDateToCompanyId(
                date,
                company.getId()
        )).thenReturn(Optional.of(transfer));

        Optional<Transfer> found = transferService.findByDateToCompanyId(
                date,
                company.getId()
        );

        AssertionsForInterfaceTypes.assertThat(found).isEqualTo(Optional.of(transfer));

    }

    @Test
    public void findByLastMonthTest(){

        LocalDateTime beforeDate = SiteLocale.now("AR").minusDays(30).atStartOfDay();
        System.out.println(beforeDate);

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        when(companyService.findById(company.getId())).thenReturn(Optional.of(company));

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        when(accountService.findByName(EAccount.DEBIT_ACCOUNT)).thenReturn(Optional.of(account));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);

        Transfer transfer = new Transfer(
                account,
                300.00,
                cal.getTime(),
                Collections.singleton(company)
        );

        transfer.setId(1L);

        Transfer transfer2 = new Transfer(
                account,
                300.00,
                cal.getTime(),
                Collections.singleton(company)
        );

        transfer.setId(2L);

        List<Transfer> transferList = new ArrayList<>();
        transferList.add(transfer);
        transferList.add(transfer2);

        when(transferService.findByLastMonth(cal.getTime())).thenReturn(transferList);

        List<Transfer> found = transferService.findByLastMonth(cal.getTime());

        assertEquals(found, transferList);

    }

    @Test
    public void findByLastMonthNotFindTest(){

        LocalDateTime beforeDate = SiteLocale.now("AR").minusDays(30).atStartOfDay();
        System.out.println(beforeDate);

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        when(companyService.findById(company.getId())).thenReturn(Optional.of(company));

        Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);

        when(accountService.findByName(EAccount.DEBIT_ACCOUNT)).thenReturn(Optional.of(account));

        Date date = new Date();

        Transfer transfer = new Transfer(
                account,
                300.00,
                date,
                Collections.singleton(company)
        );

        transfer.setId(1L);

        Transfer transfer2 = new Transfer(
                account,
                300.00,
                date,
                Collections.singleton(company)
        );

        transfer.setId(2L);

        List<Transfer> transferList = new ArrayList<>();
        transferList.add(transfer);
        transferList.add(transfer2);


        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);


        when(transferService.findByLastMonth(cal.getTime())).thenReturn(transferList);

        List<Transfer> found = transferService.findByLastMonth(cal.getTime());

        assertEquals(found, transferList);

    }


}
